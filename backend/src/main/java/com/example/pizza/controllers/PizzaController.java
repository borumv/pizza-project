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

@RestController
@RequestMapping("/api/pizza")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PizzaController {
    private PizzaService pizzaService;
    private TypeService typeService;
    private CategoryService categoryService;

    @Autowired
    public PizzaController(PizzaService pizzaService, TypeService typeService, CategoryService categoryService) {
        this.pizzaService = pizzaService;
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/model/all")
    public List<PizzaModel> getAllModels() {
        return pizzaService.getAll();
    }

    @GetMapping("/types")
    public List<String> getTypes() {
        return typeService.getAll()
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getId(), o1.getId()))
                .map(PizzaType::getDescription)
                .collect(Collectors.toList());
    }
    @GetMapping("/categories")
    public List<String> getCategories() {
        return categoryService.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Category::getId))
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/model/all_pizzes_page={page}limit={limit}")
    @Transactional
    public ExportDataPizzaModel getAllData(@RequestParam(name = "category_id", required = false) Integer category_id,
                                           @RequestParam(name = "orderingValue", required = false, defaultValue = "price") OrderValue orderingValue,
                                           @RequestParam(name = "ordering_type", required = false, defaultValue = "asc") OrderType ordering_type,
                                           @RequestParam(name = "search_value", required = false, defaultValue = "")  String search_value,
                                           @PathVariable int page ,
                                           @PathVariable int limit) {
        List<String> pizzaTypes = getTypes();
        List<String> categories = getCategories();
        PageableModel<PizzaModel, Pizza> pizzaModelList = pizzaService.getPizzaModelWithPageable(category_id, orderingValue, ordering_type, search_value, page, limit);

        return ExportDataPizzaModel.toModel(pizzaTypes, categories, pizzaModelList);
    }

//    @GetMapping("/model/only_pizzes_page={page}limit={limit}")
//    public List<PizzaModel> getAllPizzas(@RequestParam(name = "category_id", required = false) Integer category_id,
//                                         @RequestParam(name = "orderingValue", required = false, defaultValue = "price") OrderValue orderingValue,
//                                         @RequestParam(name = "ordering_type", required = false, defaultValue = "asc") OrderType ordering_type,
//                                         @RequestParam(name = "search_value", required = false, defaultValue = "")  String search_value,
//                                         @PathVariable int page ,
//                                         @PathVariable int limit){
//        return pizzaService.getPizzaModels(category_id, orderingValue, ordering_type, search_value, page, limit);
//    }
    @GetMapping("/model/only_pizzes_page={page}limit={limit}")
    public PageableModel<PizzaModel, Pizza> getAllPizzasWithPage(@RequestParam(name = "category_id", required = false) Integer category_id,
                                                     @RequestParam(name = "orderingValue", required = false, defaultValue = "price") OrderValue orderingValue,
                                                     @RequestParam(name = "ordering_type", required = false, defaultValue = "asc") OrderType ordering_type,
                                                     @RequestParam(name = "search_value", required = false, defaultValue = "")  String search_value,
                                                     @PathVariable int page ,
                                                     @PathVariable int limit){
        return pizzaService.getPizzaModelWithPageable(category_id, orderingValue, ordering_type, search_value, page, limit);
    }
}
