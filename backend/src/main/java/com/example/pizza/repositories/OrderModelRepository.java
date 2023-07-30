package com.example.pizza.repositories;
import com.example.pizza.entity.OrderPizza;
import com.example.pizza.models.OrderModel;
import org.springframework.data.repository.CrudRepository;

public interface OrderModelRepository extends CrudRepository<OrderPizza, Long> {

}
