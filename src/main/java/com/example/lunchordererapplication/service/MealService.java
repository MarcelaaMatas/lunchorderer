package com.example.lunchordererapplication.service;

import com.example.lunchordererapplication.model.Meal;
import java.util.List;

public interface MealService {
    List<Meal> getDailyMenu();
    List<Meal> getMealsByIds(List<Long> mealIds);
}
