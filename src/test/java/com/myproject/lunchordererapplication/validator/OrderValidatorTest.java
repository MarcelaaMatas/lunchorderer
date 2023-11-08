package com.myproject.lunchordererapplication.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.myproject.lunchordererapplication.exceptions.InvalidInputException;
import com.myproject.lunchordererapplication.model.Meal;
import com.myproject.lunchordererapplication.repository.MealRepository;
import com.myproject.lunchordererapplication.repository.OrderRepository;
import com.myproject.lunchordererapplication.validator.implementation.OrderValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

public class OrderValidatorTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private OrderRepository orderRepository;

    private OrderValidator orderValidator;
    private Meal meal1;
    private Meal meal2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderValidator = new OrderValidatorImpl(mealRepository, orderRepository);

        meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Meal 1");
        meal1.setPrice(10.0);

        meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Meal 2");
        meal2.setPrice(12.0);
    }

    @Test
    public void testValidateCreateValid() {

        List<Long> mealIds = List.of(meal1.getMealId(), meal2.getMealId());
        List<Meal> selectedMeals = List.of(meal1, meal2);

        when(mealRepository.findAllById(mealIds)).thenReturn(selectedMeals);
        when(mealRepository.existsById(any())).thenReturn(true);

        assertDoesNotThrow(() -> orderValidator.validateCreate(mealIds));
    }

    @Test
    public void testValidateCreateEmptyMealIds() {
        List<Long> mealIds = new ArrayList<>();

        assertThrows(InvalidInputException.class, () -> orderValidator.validateCreate(mealIds));
    }

    @Test
    public void testValidateUpdateValid() {
        Long orderId = 1L;

        List<Meal> meals = new ArrayList<>();
        meals.add(meal1);
        meals.add(meal2);

        List<Long> mealIds = List.of(meal1.getMealId(), meal2.getMealId());

        when(mealRepository.findAllById(mealIds)).thenReturn(meals);
        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(mealRepository.existsById(any())).thenReturn(true);

        assertDoesNotThrow(() -> orderValidator.validateUpdate(mealIds, orderId));
    }

    @Test
    public void testValidateUpdateEmptyMealIds() {
        List<Long> mealIds = new ArrayList<>();
        Long orderId = 1L;

        assertThrows(InvalidInputException.class, () -> orderValidator.validateUpdate(mealIds, orderId));
    }
}
