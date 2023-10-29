package com.example.lunchordererapplication.service.implementation;

import com.example.lunchordererapplication.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.lunchordererapplication.repository.MealRepository;
import com.example.lunchordererapplication.service.MealService;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Override
    public List<Meal> getDailyMenu() {
        return mealRepository.findAll();
    }

    @Override
    public List<Meal> getMealsByIds(List<Long> mealIds) {
        return mealRepository.findAllById(mealIds);
    }
}
