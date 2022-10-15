package com.example.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pizza_price_info")
@Data
public class PizzaSizeCostInfo {
    public PizzaSizeCostInfo() {
    }
    @Id
    @JsonIgnore
    private int id;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pizza_id" , referencedColumnName = "id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "size_id" , referencedColumnName = "id")
    private Size size;


    private int price;





}
