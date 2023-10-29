package com.example.lunchordererapplication.validator.implementation;

import com.example.lunchordererapplication.exceptions.InvalidInputException;
import com.example.lunchordererapplication.exceptions.NotFoundException;
import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.repository.MealRepository;
import com.example.lunchordererapplication.repository.OrderRepository;
import com.example.lunchordererapplication.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderValidatorImpl implements OrderValidator {

    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;

    @Override
    public void validateCreate(List<Long> mealIds) {
        if (mealIds == null || mealIds.isEmpty()) {
            throw new InvalidInputException(InvalidInputException.MEAL_MISSING_ID_MESSAGE);
        }

        List<Meal> meals = mealRepository.findAllById(mealIds);
        if (meals.size() != mealIds.size()) {
            throw new NotFoundException(NotFoundException.MEALS_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public void validateUpdate(List<Long> mealIds, Long orderId) {
        validateCreate(mealIds);

        if (orderId == null) {
            throw new InvalidInputException(InvalidInputException.ORDER_MISSING_ID_MESSAGE);
        }

        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException(NotFoundException.ORDER_NOT_FOUND_MESSAGE);
        }
    }
}
