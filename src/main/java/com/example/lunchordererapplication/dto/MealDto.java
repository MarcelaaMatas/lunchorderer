package com.example.lunchordererapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {
    private Long mealId;
    private String mealName;
    private double price;
}
