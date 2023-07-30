package com.example.pizza.services;

import com.example.pizza.entity.Category;
import com.example.pizza.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll() {

        return categoryRepo.findAll();
    }

}
