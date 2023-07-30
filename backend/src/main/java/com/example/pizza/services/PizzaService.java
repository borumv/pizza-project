package com.example.pizza.services;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.example.pizza.QPredicates;
import com.example.pizza.configuration.QueryFactory;
import com.example.pizza.entity.Pizza;
import com.example.pizza.models.PageableModel;
import com.example.pizza.models.PizzaModel;
import com.example.pizza.repositories.PizzaQRepository;
import com.example.pizza.repositories.PizzaRepo;
import com.example.pizza.services.util.OrderType;
import com.example.pizza.services.util.OrderValue;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pizza.entity.QPizza.pizza;

@Service
public class PizzaService {

    @PersistenceContext
    private EntityManager em;
    public CriteriaBuilderFactory cbf;
    public PizzaRepo pizzaRepo;
    private QueryFactory queryFactory;

    public PizzaService(CriteriaBuilderFactory cbf, PizzaRepo pizzaRepo,QueryFactory queryFactory) {

        this.cbf = cbf;
        this.pizzaRepo = pizzaRepo;
        this.queryFactory = queryFactory;
    }

    public List<PizzaModel> getAll() {

        return pizzaRepo.findAll().stream()
                .map(item -> item.getModel().toModel(item))
                .collect(Collectors.toList());
    }

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

    private Predicate getPredicates(Integer categoryId, String search_value) {

        return new QPredicates()
                .builder()
                .add(categoryId, id -> pizza.categories.any().id.eq(id))
                .add(search_value, value -> pizza.title.toLowerCase().contains(value.toLowerCase()))
                .build();
    }
}