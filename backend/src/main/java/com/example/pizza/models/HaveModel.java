package com.example.pizza.models;

import com.example.pizza.models.ModelCreator;

public interface HaveModel<T extends ModelCreator > {

    T getModel();
}
