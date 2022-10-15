package com.example.pizza.models;

import com.example.pizza.entity.Size;
import lombok.Data;

@Data
public class SizeAndCostModel {
    private int size;
    private int cost;

    public SizeAndCostModel(int size, int cost) {
        this.size = size;
        this.cost = cost;
    }
}
