package com.example.lunchordererapplication.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.mapper.implementation.MealMapperImpl;
import com.example.lunchordererapplication.model.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class MealMapperTest {

    private MealMapper mealMapper;

    @BeforeEach
    public void setUp() {
        mealMapper = new MealMapperImpl();
    }

    @Test
    public void testMealToMealDto() {
        Meal meal = new Meal();
        meal.setMealId(1L);
        meal.setMealName("Meal1");
        meal.setPrice(9.99);

        MealDto mealDto = mealMapper.mealToMealDto(meal);

        assertEquals(meal.getMealId(), mealDto.getMealId());
        assertEquals(meal.getMealName(), mealDto.getMealName());
        assertEquals(meal.getPrice(), mealDto.getPrice());
    }

    @Test
    public void testMealsToMealDtos() {
        Meal meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Meal1");
        meal1.setPrice(12.99);

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Meal2");
        meal2.setPrice(7.99);

        List<Meal> meals = Arrays.asList(meal1, meal2);

        List<MealDto> mealDtos = mealMapper.mealsToMealDtos(meals);

        assertEquals(meals.size(), mealDtos.size());

        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            MealDto mealDto = mealDtos.get(i);
            assertEquals(meal.getMealId(), mealDto.getMealId());
            assertEquals(meal.getMealName(), mealDto.getMealName());
            assertEquals(meal.getPrice(), mealDto.getPrice());
        }
    }
}
