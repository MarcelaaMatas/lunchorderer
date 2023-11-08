package com.myproject.lunchordererapplication.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.myproject.lunchordererapplication.dto.MealDto;
import com.myproject.lunchordererapplication.mapper.implementation.MealMapperImpl;
import com.myproject.lunchordererapplication.model.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class MealMapperTest {

    private MealMapper mealMapper;
    private Meal meal;

    @BeforeEach
    public void setUp() {
        mealMapper = new MealMapperImpl();

        meal = new Meal();
        meal.setMealId(1L);
        meal.setMealName("Meal1");
        meal.setPrice(9.99);
    }

    @Test
    public void testMealToMealDto() {

        MealDto mealDto = mealMapper.mealToMealDto(meal);

        compareModelToDto(meal, mealDto);
    }

    @Test
    public void testMealsToMealDtos() {

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Meal2");
        meal2.setPrice(7.99);

        List<Meal> meals = Arrays.asList(meal, meal2);

        List<MealDto> mealDtos = mealMapper.mealsToMealDtos(meals);

        assertEquals(meals.size(), mealDtos.size());

        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            MealDto mealDto = mealDtos.get(i);
            compareModelToDto(meal, mealDto);
        }
    }

    private void compareModelToDto(Meal meal, MealDto mealDto) {
        assertEquals(meal.getMealId(), mealDto.getMealId());
        assertEquals(meal.getMealName(), mealDto.getMealName());
        assertEquals(meal.getPrice(), mealDto.getPrice());
    }
}
