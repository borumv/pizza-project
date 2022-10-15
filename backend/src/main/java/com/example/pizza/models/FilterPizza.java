package com.example.pizza.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterPizza {
    int categoryId;
    String orderValue;


    public FilterPizza(int category_id) {
        this.categoryId = category_id;
    }

    public FilterPizza(String ordering) {
        this.orderValue = ordering;
    }
}
