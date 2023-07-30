package com.example.pizza.repositories;

import com.example.pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

//    List<Pizza> findAllBy(Integer categoryId);
}
