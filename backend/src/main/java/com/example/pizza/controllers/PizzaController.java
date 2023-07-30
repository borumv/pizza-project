package com.example.pizza.controllers;
import com.example.pizza.entity.Category;
import com.example.pizza.entity.Pizza;
import com.example.pizza.entity.PizzaType;
import com.example.pizza.models.ExportDataPizzaModel;
import com.example.pizza.models.PageableModel;
import com.example.pizza.models.PizzaModel;
import com.example.pizza.services.CategoryService;
import com.example.pizza.services.PizzaService;
import com.example.pizza.services.TypeService;
import com.example.pizza.services.util.OrderType;
import com.example.pizza.services.util.OrderValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class that handles pizza-related operations.
 * This class defines endpoints for fetching pizza models, pizza types, pizza categories,
 * and paginated lists of pizzas.
 */
@RestController
@RequestMapping("/api/pizza")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PizzaController {

    private PizzaService pizzaService;
    private TypeService typeService;
    private CategoryService categoryService;

    /**
     * Constructor for the PizzaController class.
     *
     * @param pizzaService    The service that provides pizza-related functionality.
     * @param typeService     The service that provides pizza type-related functionality.
     * @param categoryService The service that provides pizza category-related functionality.
     */
    @Autowired
    public PizzaController(PizzaService pizzaService, TypeService typeService, CategoryService categoryService) {
        this.pizzaService = pizzaService;
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    /**
     * Get a list of all pizza models.
     *
     * @return A list of PizzaModel objects representing all available pizzas.
     */
    @GetMapping("/model/all")
    public List<PizzaModel> getAllModels() {
        return pizzaService.getAll();
    }

    /**
     * Get a list of all pizza types.
     *
     * @return A list of strings representing the descriptions of all pizza types.
     */
    @GetMapping("/types")
    public List<String> getTypes() {
        return typeService.getAll()
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getId(), o1.getId()))
                .map(PizzaType::getDescription)
                .collect(Collectors.toList());
    }

    /**
     * Get a list of all pizza categories.
     *
     * @return A list of strings representing the names of all pizza categories.
     */
    @GetMapping("/categories")
    public List<String> getCategories() {
        return categoryService.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Category::getId))
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get all pizza data along with pizza types and categories, based on specified parameters.
     *
     * @param category_id    The ID of the pizza category (optional).
     * @param orderingValue  The value to order the pizzas by (e.g., price, rating).
     * @param ordering_type  The ordering type (ascending or descending).
     * @param search_value   The search value to filter pizzas by title (optional).
     * @return An ExportDataPizzaModel object containing pizza types, categories, and a list of pizza models.
     */
    @GetMapping("/model/all_pizzes")
    @Transactional
    public ExportDataPizzaModel getAllData(
            @RequestParam(name = "category_id", required = false) Integer category_id,
            @RequestParam(name = "orderingValue", required = false, defaultValue = "price") OrderValue orderingValue,
            @RequestParam(name = "ordering_type", required = false, defaultValue = "asc") OrderType ordering_type,
            @RequestParam(name = "search_value", required = false, defaultValue = "") String search_value) {
        List<String> pizzaTypes = getTypes();
        List<String> categories = getCategories();
        List<PizzaModel> pizzaModelList = pizzaService.getPizzaModels(category_id, orderingValue, ordering_type, search_value);
        return ExportDataPizzaModel.toModel(pizzaTypes, categories, pizzaModelList);
    }

    /**
     * Get a paginated list of pizza models based on specified parameters.
     *
     * @param category_id    The ID of the pizza category (optional).
     * @param orderingValue  The value to order the pizzas by (e.g., price, rating).
     * @param ordering_type  The ordering type (ascending or descending).
     * @param search_value   The search value to filter pizzas by title (optional).
     * @param page           The page number for pagination.
     * @param limit          The maximum number of items per page.
     * @return A PageableModel object containing pizza models with pagination information.
     */
    @GetMapping("/model/only_pizzes_page={page}limit={limit}")
    public PageableModel<PizzaModel, Pizza> getAllPizzasWithPage(
            @RequestParam(name = "category_id", required = false) Integer category_id,
            @RequestParam(name = "orderingValue", required = false, defaultValue = "price") OrderValue orderingValue,
            @RequestParam(name = "ordering_type", required = false, defaultValue = "asc") OrderType ordering_type,
            @RequestParam(name = "search_value", required = false, defaultValue = "") String search_value,
            @PathVariable int page,
            @PathVariable int limit) {
        return pizzaService.getPizzaModelWithPageable(category_id, orderingValue, ordering_type, search_value, page, limit);
    }
}
