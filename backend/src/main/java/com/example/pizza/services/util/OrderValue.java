package com.example.pizza.services.util;

public enum OrderValue {
    price,
    title,
    rating;

    @Override
    public String toString() {

        return name().toLowerCase();
    }

}
