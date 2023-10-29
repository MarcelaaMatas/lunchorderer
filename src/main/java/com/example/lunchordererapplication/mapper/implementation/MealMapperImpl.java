package com.example.lunchordererapplication.mapper.implementation;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.mapper.MealMapper;
import com.example.lunchordererapplication.model.Meal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapperImpl implements MealMapper {

    @Override
    public MealDto mealToMealDto(Meal meal) {
        MealDto mealDto = new MealDto();
        mealDto.setMealId(meal.getMealId());
        mealDto.setMealName(meal.getMealName());
        mealDto.setPrice(meal.getPrice());
        return mealDto;
    }

    @Override
    public List<MealDto> mealsToMealDtos(List<Meal> meals) {
        return meals.stream()
                .map(this::mealToMealDto)
                .collect(Collectors.toList());
    }
}

