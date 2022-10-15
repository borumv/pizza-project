package com.example.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "categories")
@Data
public class Category {

    @Id
    private int id;

    @Column(name = "name")
    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    List<Pizza> pizzaList;


}
