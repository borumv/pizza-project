package com.example.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages="com.example.pizza", entityManagerFactoryRef="emf")
public class PizzaApplication {

    public static void main(String[] args) {

        SpringApplication.run(PizzaApplication.class, args);
    }

}
