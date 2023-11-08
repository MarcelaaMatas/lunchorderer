package com.myproject.lunchordererapplication.mapper;

import com.myproject.lunchordererapplication.dto.MealDto;
import com.myproject.lunchordererapplication.model.Meal;
import java.util.List;

public interface MealMapper {
    MealDto mealToMealDto(Meal meal);
    List<MealDto> mealsToMealDtos(List<Meal> meals);
}
