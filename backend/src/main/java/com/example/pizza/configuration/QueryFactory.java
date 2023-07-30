package com.example.pizza.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class QueryFactory {

    @PersistenceContext
    private EntityManager em;

    public JPAQueryFactory getQueryFactory() {

        return queryFactory;
    }

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {

        queryFactory = new JPAQueryFactory(em);

    }
}
