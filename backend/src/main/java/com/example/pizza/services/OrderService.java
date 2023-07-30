package com.example.pizza.services;

import com.example.pizza.entity.Order;
import com.example.pizza.entity.enums.STATUS;
import com.example.pizza.models.OrderModel;
import com.example.pizza.repositories.OrderModelRepository;
import com.example.pizza.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Service class that provides functionality related to processing orders.
 * This service interacts with the OrderRepository and OrderModelRepository to handle order data and its associated models.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderModelRepository orderModelRepository;

    /**
     * Constructor for the OrderService class.
     *
     * @param orderRepository      The repository that handles data access for Order entities.
     * @param orderModelRepository The repository that handles data access for OrderModel entities.
     */
    public OrderService(OrderRepository orderRepository, OrderModelRepository orderModelRepository) {

        this.orderRepository = orderRepository;
        this.orderModelRepository = orderModelRepository;
    }

    /**
     * Registers a new order with the provided order model data.
     * The method creates a new Order entity, populates it with the necessary information from the order model,
     * and saves it in the database using the OrderRepository.
     * Additionally, it saves the associated OrderPizza entities from the order model in the database using the
     * OrderModelRepository.
     *
     * @param orderModel The OrderModel containing the details of the order to be registered.
     * @return A boolean value indicating the success or failure of the order registration.
     */
    @Transactional
    public boolean registerOrder(OrderModel orderModel) {

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setStatus(STATUS.PENDING);
        order.setFinalPrice(orderModel.getFinalPrice());
        order.setPhoneNumber(orderModel.getPhoneNumber());
        var save = orderRepository.save(order);
        var pizzas = orderModel.getOrderedPizzas();
        pizzas.forEach(item -> item.setOrder(save));
        orderModelRepository.saveAll(orderModel.getOrderedPizzas());
        return true;
    }
}