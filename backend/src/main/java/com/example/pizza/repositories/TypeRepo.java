package com.example.pizza.repositories;

import com.example.pizza.entity.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<PizzaType, Integer> {

}
