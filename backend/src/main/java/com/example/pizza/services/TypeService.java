package com.example.pizza.services;

import com.example.pizza.entity.PizzaType;
import com.example.pizza.repositories.TypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private TypeRepo typeRepo;

    public TypeService(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }
    public List<PizzaType> getAll(){
        return typeRepo.findAll();
    }
}
