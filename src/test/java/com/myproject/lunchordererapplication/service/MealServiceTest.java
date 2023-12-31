package com.myproject.lunchordererapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.myproject.lunchordererapplication.model.Meal;
import com.myproject.lunchordererapplication.repository.MealRepository;
import com.myproject.lunchordererapplication.service.implementation.MealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private RestTemplate restTemplate;
    private MealService mealService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mealService = new MealServiceImpl(mealRepository, restTemplate);
    }

    @Test
    public void testGetDailyMenu() {
        Meal meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Meal 1");
        meal1.setPrice(10.0);

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Meal 2");
        meal2.setPrice(12.0);

        List<Meal> mockMeals = new ArrayList<>();
        mockMeals.add(meal1);
        mockMeals.add(meal2);

        Mockito.when(mealRepository.findAll()).thenReturn(mockMeals);

        List<Meal> result = mealService.getDailyMenu();

        assertEquals(mockMeals, result);
    }

    @Test
    public void testGetDailyMenuWhenNoMealsFound() {
        List<Meal> mockMeals = new ArrayList<>();
        Mockito.when(mealRepository.findAll()).thenReturn(mockMeals);

        List<Meal> result = mealService.getDailyMenu();

        assertEquals(0, result.size());
    }
}
