package com.example.pizza.models;

import com.example.pizza.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AllDataModel {
    private int id;
    private Set<String> imageUrl;
    private String title;
    private Integer size;
    private Integer price;
    private List<Integer> category;
    private int rating;

    public static List<AllDataModel> toModel(Pizza pizza) {
        List<AllDataModel> models = new ArrayList<>();
        List<PizzaSizeCostInfo> pizzaSizeCostInfos = pizza.getPizzaSizeCostInfos();

        for (PizzaSizeCostInfo pizzaSizeCostInfo : pizzaSizeCostInfos) {
            AllDataModel model = new AllDataModel();
            model.setId(pizza.getId());
            model.setImageUrl(
                    pizza.getImages().stream()
                            .map(Image::getImageUrl)
                            .collect(Collectors.toSet()));
            model.setTitle(pizza.getTitle());
            model.setSize(pizzaSizeCostInfo.getSize().getSize());
            model.setPrice(pizzaSizeCostInfo.getPrice());
            model.setCategory(pizza.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
            model.setRating(5);
            models.add(model);
        }
        return models;
    }


}
