package com.example.pizza.services;

import com.example.pizza.entity.Category;
import com.example.pizza.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that provides functionality related to the Category entity.
 * This service interacts with the CategoryRepo (Category Repository) to fetch category data.
 */
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    /**
     * Constructor for the CategoryService class.
     *
     * @param categoryRepo The repository that handles data access for Category entities.
     */
    public CategoryService(CategoryRepo categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of Category entities containing all available categories.
     */
    public List<Category> getAll() {

        return categoryRepo.findAll();
    }

}
