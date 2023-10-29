package com.example.lunchordererapplication.validator;

import java.util.List;

public interface OrderValidator {
    void validateCreate(List<Long> mealIds);

    void validateUpdate(List<Long> mealIds, Long orderId);
}
