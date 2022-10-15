package com.example.pizza.models;

public interface  ModelCreator<T , V extends HaveModel> {
    T toModel(V v);


}
