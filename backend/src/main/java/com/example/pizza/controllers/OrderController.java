package com.example.pizza.controllers;

import com.example.pizza.entity.OrderPizza;
import com.example.pizza.models.OrderModel;
import com.example.pizza.services.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping
    public boolean registerOrder(
            @RequestBody
            @Valid OrderModel orderPizza) {

        return orderService.registerOrder(orderPizza);
    }
}
