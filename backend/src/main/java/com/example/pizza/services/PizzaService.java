package com.example.pizza.services;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.example.pizza.QPredicates;
import com.example.pizza.configuration.QueryFactory;
import com.example.pizza.entity.Pizza;
import com.example.pizza.models.PageableModel;
import com.example.pizza.models.PizzaModel;
import com.example.pizza.repositories.PizzaRepo;
import com.example.pizza.services.util.OrderType;
import com.example.pizza.services.util.OrderValue;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pizza.entity.QPizza.pizza;

/**
 * Service class that provides functionality related to pizza operations.
 * This service interacts with the PizzaRepo and CriteriaBuilderFactory to handle pizza data and its associated models.
 */
@Service
public class PizzaService {

    @PersistenceContext
    private EntityManager em;
    public CriteriaBuilderFactory cbf;
    public PizzaRepo pizzaRepo;
    private QueryFactory queryFactory;

    /**
     * Constructor for the PizzaService class.
     *
     * @param cbf          The criteria builder factory used to construct criteria queries.
     * @param pizzaRepo    The repository that handles data access for Pizza entities.
     * @param queryFactory The query factory used to build complex queries.
     */
    public PizzaService(CriteriaBuilderFactory cbf, PizzaRepo pizzaRepo, QueryFactory queryFactory) {

        this.cbf = cbf;
        this.pizzaRepo = pizzaRepo;
        this.queryFactory = queryFactory;
    }

    /**
     * Retrieves all pizzas and converts them to PizzaModel objects.
     *
     * @return A list of PizzaModel objects representing all pizzas.
     */
    public List<PizzaModel> getAll() {

        return pizzaRepo.findAll().stream()
                .map(item -> item.getModel().toModel(item))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a pageable list of PizzaModel objects based on the provided filter criteria and ordering.
     *
     * @param categoryId   The ID of the category to filter pizzas by.
     * @param orderValue   The field to order pizzas by (e.g., price, title, rating).
     * @param orderType    The order type (ascending or descending) for the ordering.
     * @param search_value The search keyword to filter pizzas by title.
     * @param page         The page number for pagination.
     * @param limit        The maximum number of items per page for pagination.
     * @return A PageableModel containing the list of filtered and ordered PizzaModel objects.
     */
    public PageableModel<PizzaModel, Pizza> getPizzaModelWithPageable(Integer categoryId, OrderValue orderValue, OrderType orderType, String search_value, int page, int limit) {

        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(orderValue, orderType);
        Predicate predicates = getPredicates(categoryId, search_value);
        PagedList<Pizza> query = new BlazeJPAQuery<>(em, cbf)
                .select(pizza)
                .from(pizza)
                .where(predicates)
                .orderBy(orderSpecifier)
                .orderBy(pizza.id.desc())
                .fetchPage(page, limit);
        return new PageableModel<>(query, query.getPage(), query.getTotalSize(), query.getTotalPages());
    }

    /**
     * Retrieves a list of PizzaModel objects based on the provided filter criteria and ordering.
     *
     * @param categoryId   The ID of the category to filter pizzas by.
     * @param orderValue   The field to order pizzas by (e.g., price, title, rating).
     * @param orderType    The order type (ascending or descending) for the ordering.
     * @param search_value The search keyword to filter pizzas by title.
     * @return A list of filtered and ordered PizzaModel objects.
     */
    public List<PizzaModel> getPizzaModels(Integer categoryId, OrderValue orderValue, OrderType orderType, String search_value) {

        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(orderValue, orderType);
        Predicate predicates = getPredicates(categoryId, search_value);
        return queryFactory.getQueryFactory()
                .select(pizza)
                .from(pizza)
                .where(predicates)
                .orderBy(orderSpecifier)
                .groupBy(pizza.id)
                .fetch()
                .stream()
                .map(item -> item.getModel().toModel(item))
                .collect(Collectors.toList());
    }

    /**
     * Builds and returns an OrderSpecifier based on the provided orderValue and orderType.
     *
     * @param orderValue The field to order pizzas by (e.g., price, title, rating).
     * @param orderType  The order type (ascending or descending) for the ordering.
     * @return An OrderSpecifier object for ordering pizzas.
     */
    private OrderSpecifier<?> getOrderSpecifier(OrderValue orderValue, OrderType orderType) {

        switch (orderValue) {
            case price -> {
                return orderType == OrderType.asc
                        ? pizza.pizzaSizeCostInfos.any().price.max().asc()
                        : pizza.pizzaSizeCostInfos.any().price.max().desc();
            }
            case title -> {
                return orderType == OrderType.asc
                        ? pizza.title.asc()
                        : pizza.title.desc();
            }
            case rating -> {
                return orderType == OrderType.asc
                        ? pizza.rating.asc()
                        : pizza.rating.desc();
            }
            default -> {
                return pizza.title.desc();
            }
        }
    }

    /**
     * Builds and returns a Predicate based on the provided categoryId and search_value.
     *
     * @param categoryId   The ID of the category to filter pizzas by.
     * @param search_value The search keyword to filter pizzas by title.
     * @return A Predicate representing the filter criteria for pizzas.
     */
    private Predicate getPredicates(Integer categoryId, String search_value) {

        return new QPredicates()
                .builder()
                .add(categoryId, id -> pizza.categories.any().id.eq(id))
                .add(search_value, value -> pizza.title.toLowerCase().contains(value.toLowerCase()))
                .build();
    }
}