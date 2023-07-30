package com.example.pizza.services;

import com.example.pizza.entity.Order;
import com.example.pizza.entity.enums.STATUS;
import com.example.pizza.models.OrderModel;
import com.example.pizza.repositories.OrderModelRepository;
import com.example.pizza.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderModelRepository orderModelRepository;

    public OrderService(OrderRepository orderRepository, OrderModelRepository orderModelRepository) {

        this.orderRepository = orderRepository;
        this.orderModelRepository = orderModelRepository;
    }

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
