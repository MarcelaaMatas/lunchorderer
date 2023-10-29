package com.example.lunchordererapplication.mapper;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.model.Meal;
import java.util.List;

public interface MealMapper {
    MealDto mealToMealDto(Meal meal);
    List<MealDto> mealsToMealDtos(List<Meal> meals);
}
