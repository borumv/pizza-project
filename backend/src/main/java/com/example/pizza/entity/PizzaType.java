package com.example.pizza.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "types")
@Data
public class PizzaType {
    @Id
    private int id;

    private String description;
}
