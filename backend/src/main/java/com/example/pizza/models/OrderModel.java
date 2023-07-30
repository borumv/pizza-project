package com.example.pizza.models;
import com.example.pizza.entity.Order;
import com.example.pizza.entity.OrderPizza;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderModel {


    private List<OrderPizza> orderedPizzas;
    private float finalPrice;
    private String phoneNumber;

    private static OrderModel toModel(Order order){
        return OrderModel.builder()
                .orderedPizzas(order.getOrderedPizzas())
                .finalPrice(order.getFinalPrice())
                .build();
    }
}
