package com.example.pizza.models;

import com.example.pizza.entity.Pizza;
import lombok.Data;

import java.util.List;

@Data
public class ExportDataPizzaModel {
    List<String> types;
    List<String> categories;
    PageableModel<PizzaModel, Pizza> items;
    List<PizzaModel> pizzaModelList;
    int count;

    public static ExportDataPizzaModel toModel(List<String> types, List<String> categories, PageableModel<PizzaModel, Pizza> pizzaPageableModel){
        ExportDataPizzaModel exportDataPizzaModel = new ExportDataPizzaModel();
        exportDataPizzaModel.setTypes(types);
        exportDataPizzaModel.setCategories(categories);
        exportDataPizzaModel.setItems(pizzaPageableModel);

        return exportDataPizzaModel;
    }
}
