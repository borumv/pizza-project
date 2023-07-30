package com.example.pizza.controllers;

import com.example.pizza.models.OrderModel;
import com.example.pizza.services.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class that handles order-related operations.
 * This class defines an endpoint for registering orders.
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {

    private OrderService orderService;

    /**
     * Constructor for the OrderController class.
     *
     * @param orderService The service that provides order-related functionality.
     */
    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    /**
     * Register a new order with the provided order data.
     *
     * @param orderPizza The OrderModel object containing the details of the order.
     * @return A boolean value indicating the success or failure of the order registration.
     */
    @PostMapping
    public boolean registerOrder(
            @RequestBody
            @Valid OrderModel orderPizza) {

        return orderService.registerOrder(orderPizza);
    }
}
