package com.example.lunchordererapplication.controller;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.infrastructure.Constants;
import com.example.lunchordererapplication.mapper.MealMapper;
import com.example.lunchordererapplication.service.MealService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MealController {

    private final MealService mealService;
    private final MealMapper mealMapper;

    @GetMapping(Constants.URL_DAILY_MENU)
    public ResponseEntity<List<MealDto>> getDailyMenu() {
        return ResponseEntity.ok(mealMapper.mealsToMealDtos(mealService.getDailyMenu()));
    }
}

