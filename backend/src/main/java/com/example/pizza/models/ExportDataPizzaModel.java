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

    public static ExportDataPizzaModel toModel(List<String> pizzaTypes, List<String> categories, List<PizzaModel> pizzaModelList) {

        ExportDataPizzaModel exportDataPizzaModel = new ExportDataPizzaModel();
        exportDataPizzaModel.setTypes(pizzaTypes);
        exportDataPizzaModel.setCategories(categories);
        exportDataPizzaModel.setPizzaModelList(pizzaModelList);
        return exportDataPizzaModel;
    }
}
