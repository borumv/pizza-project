package com.example.pizza.repositories;

import com.example.pizza.entity.Pizza;
import com.example.pizza.entity.QPizza;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.example.pizza.entity.QPizza.pizza;


@Repository
public interface PizzaQRepository extends ExCustomRepository<Pizza, QPizza, Long> {

    List<Pizza>findAll();


}
