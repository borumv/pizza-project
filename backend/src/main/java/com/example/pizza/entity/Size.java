package com.example.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sizes")
@Data
public class Size {

    @Id
    private int id;

    @Column(name = "size")
    private int size;

    @JsonIgnore
    @OneToMany(mappedBy = "size")
    private List<PizzaSizeCostInfo> types;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "sizes")
//    List<Pizza> pizzaList;

}
