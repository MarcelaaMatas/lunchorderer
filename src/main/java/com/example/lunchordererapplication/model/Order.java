package com.example.lunchordererapplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "ORDERED")
    private Boolean ordered;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDER_MEAL",
            joinColumns = @JoinColumn(name = "ID_ORDER", referencedColumnName = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_MEAL", referencedColumnName = "MEAL_ID"))
    private List<Meal> meals;

}
