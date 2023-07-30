package com.example.pizza.repositories;

import com.example.pizza.entity.Pizza;
import com.example.pizza.entity.QPizza;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaQRepository extends ExCustomRepository<Pizza, QPizza, Long> {

    List<Pizza> findAll();

}
