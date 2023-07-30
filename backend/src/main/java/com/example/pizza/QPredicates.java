package com.example.pizza;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QPredicates {

    List<Predicate> predicates = new ArrayList<>();

    public QPredicates builder(){
        return new QPredicates();
    }

    public <T> QPredicates add(T obj, Function<T, Predicate> predicateFunction){
        if(obj != null){
           predicates.add(predicateFunction.apply(obj));
        }
        return this;
    }

    public Predicate build(){
        return ExpressionUtils.allOf(predicates);
    }
}
