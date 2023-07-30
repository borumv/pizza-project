package com.example.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pizzes_img")
@Data
public class Image {

    @Id
    private int id;

    @Column(name = "imageurl")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    @JsonIgnore
    private Pizza pizza;

}
