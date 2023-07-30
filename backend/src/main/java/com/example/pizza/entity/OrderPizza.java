package com.example.pizza.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_pizzas")
@Data

public class OrderPizza {

    @Builder
    public OrderPizza(Long id, Order order, int pizzaId, String type, int size, int count) {

        this.id = id;
        this.order = order;
        this.pizzaId = pizzaId;
        this.type = type;
        this.size = size;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @Column(name = "pizzaid")
    private int pizzaId;
    @Column(name = "typeid")
    private String type;
    @Column(name = "size")
    private int size;
    private int count;

    public OrderPizza() {


    }
}
