package com.example.pizza.repositories;
import com.example.pizza.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {


}
