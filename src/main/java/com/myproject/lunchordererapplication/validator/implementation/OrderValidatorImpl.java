package com.myproject.lunchordererapplication.validator.implementation;

import com.myproject.lunchordererapplication.exceptions.InvalidInputException;
import com.myproject.lunchordererapplication.exceptions.NotFoundException;
import com.myproject.lunchordererapplication.repository.MealRepository;
import com.myproject.lunchordererapplication.repository.OrderRepository;
import com.myproject.lunchordererapplication.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderValidatorImpl implements OrderValidator {

    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;

    @Override
    public void validateCreate(List<Long> mealIds) {
        if (mealIds == null || mealIds.isEmpty()) {
            log.error(InvalidInputException.MEAL_MISSING_ID_MESSAGE);
            throw new InvalidInputException(InvalidInputException.MEAL_MISSING_ID_MESSAGE);
        }

        for (Long mealId : mealIds) {
            if (!mealRepository.existsById(mealId)) {
                log.error("Meal with ID {} not found.", mealId);
                throw new NotFoundException("Meal with ID " + mealId + " not found.");
            }
        }
    }

    @Override
    public void validateUpdate(List<Long> mealIds, Long orderId) {
        validateCreate(mealIds);

        if (orderId == null) {
            log.error(InvalidInputException.ORDER_MISSING_ID_MESSAGE);
            throw new InvalidInputException(InvalidInputException.ORDER_MISSING_ID_MESSAGE);
        }

        if (!orderRepository.existsById(orderId)) {
            log.error("{}: {}", NotFoundException.ORDER_NOT_FOUND_MESSAGE, orderId);
            throw new NotFoundException(NotFoundException.ORDER_NOT_FOUND_MESSAGE);
        }
    }
}
