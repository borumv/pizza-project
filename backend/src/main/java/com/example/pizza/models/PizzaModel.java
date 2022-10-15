package com.example.pizza.models;

import com.example.pizza.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PizzaModel implements ModelCreator<PizzaModel, Pizza>{
    private int id;
    private Set<String> imageUrl;
    private String title;
    private Set<Integer> types;
    private List<SizeAndCostModel> sizeAndCost;
    private List<Integer> category;
    private int rating;

    @Override
    public PizzaModel toModel(Pizza pizza){
        PizzaModel pizzaModel = new PizzaModel();
        pizzaModel.setId(pizza.getId());
        pizzaModel.setImageUrl(pizza.getImages().stream().map(Image::getImageUrl).collect(Collectors.toSet()));
        pizzaModel.setTitle(pizza.getTitle());
        pizzaModel.setTypes(pizza.getTypes().stream().map(PizzaType::getId).collect(Collectors.toSet()));
        pizzaModel.setSizeAndCost(pizza.getPizzaSizeCostInfos()
                .stream().map(item -> new SizeAndCostModel(item.getSize().getSize(), item.getPrice()))
                .collect(Collectors.toList()));
        pizzaModel.setCategory(pizza.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
        pizzaModel.setRating(pizza.getRating());
        return pizzaModel;
    }


}

