package com.myproject.lunchordererapplication.service;

import com.myproject.lunchordererapplication.model.Meal;
import java.util.List;

public interface MealService {
    List<Meal> getDailyMenu();
    List<Meal> getMealsByIds(List<Long> mealIds);
}
