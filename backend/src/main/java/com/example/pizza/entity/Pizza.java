package com.example.pizza.entity;

import com.example.pizza.models.HaveModel;
import com.example.pizza.models.PizzaModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizzes")
@Data
public class Pizza implements HaveModel<PizzaModel> {

    public Pizza() {

    }

    @Id
    private int id;

    @Column
    private String title;

    @Column
    private int rating;

    @ManyToMany
    @JoinTable(name = "pizza_category",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "pizza")
    private List<PizzaSizeCostInfo> pizzaSizeCostInfos;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
    private Set<Image> images;

    @ManyToMany
    @JoinTable(name = "pizza_types",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<PizzaType> types;

    @Override
    public PizzaModel getModel() {
        return new PizzaModel();
    }
}
