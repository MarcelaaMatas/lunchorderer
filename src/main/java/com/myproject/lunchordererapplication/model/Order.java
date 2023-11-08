package com.myproject.lunchordererapplication.model;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
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
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private ZonedDateTime updatedAt;

    @Column(name = "ORDERED")
    private Boolean ordered;

    @ManyToMany
    @JoinTable(
            name = "ORDER_MEAL",
            joinColumns = @JoinColumn(name = "ID_ORDER", referencedColumnName = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_MEAL", referencedColumnName = "MEAL_ID"))
    private Set<Meal> meals;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderExtraInformation> extraInformation;

    public void setAllExtraInformation(Set<OrderExtraInformation> allOrderExtraInformation) {
        this.extraInformation.clear();
        if (allOrderExtraInformation != null) {
            this.extraInformation.addAll(allOrderExtraInformation);
        }
    }

    public void setAllMeals(HashSet<Meal> meals) {
        if (this.meals == null) {
            this.meals = new HashSet<>();
        }
        this.meals.clear();
        if (meals != null) {
            this.meals.addAll(meals);
        }
    }
}
