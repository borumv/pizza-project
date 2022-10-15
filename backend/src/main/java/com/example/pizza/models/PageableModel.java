package com.example.pizza.models;

import com.example.pizza.entity.Pizza;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data

public class PageableModel <V extends ModelCreator<V, T>, T extends HaveModel<V>> {

    private PageInfo pageInfo;
    List<V> itemsList;

    private static class PageInfo {
        public int number;
        public Long totalResult;
        public int totalPages;

        public PageInfo(int number, Long totalResult, int totalPages) {
            this.number = number;
            this.totalResult = totalResult;
            this.totalPages = totalPages;
        }
    }

    public PageableModel(List<T> itemsList2, int number, Long totalResult, int totalPages) {
        this.pageInfo = new PageInfo(number, totalResult, totalPages);
        this.itemsList = itemsList2.stream().map(item -> item.getModel().toModel(item)).collect(Collectors.toList());

    }



}
