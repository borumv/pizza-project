package com.example.pizza.entity;
import com.example.pizza.entity.enums.STATUS;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private LocalDate date;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPizza> orderedPizzas;

    @Column(name = "final_price")
    private float finalPrice;

    @Enumerated(EnumType.STRING)
    private STATUS status;

}
