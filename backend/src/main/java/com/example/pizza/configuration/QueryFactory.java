package com.example.pizza.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * The QueryFactory class is a Spring @Component, which means it will be automatically detected and managed as a bean by the Spring container.
 * It provides a way to create and access a JPAQueryFactory bean, which is used to build Querydsl queries based on the JPA EntityManager.
 * */
@Component
public class QueryFactory {

    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    /**
     * Initializes the JPAQueryFactory bean using the EntityManager.
     * This method is automatically called after the bean is constructed and the EntityManager is injected.
     */
    @PostConstruct
    public void init() {

        queryFactory = new JPAQueryFactory(em);

    }

    /**
     * Get the initialized JPAQueryFactory bean.
     *
     * @return The JPAQueryFactory used to build Querydsl queries.
     */
    public JPAQueryFactory getQueryFactory() {

        return queryFactory;
    }
}
