package com.example.lunchordererapplication.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.dto.OrderDto;
import com.example.lunchordererapplication.mapper.implementation.OrderMapperImpl;
import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderMapperTest {

    @Mock
    private MealMapper mealMapper;

    @InjectMocks
    private OrderMapperImpl orderMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapToDto() {
        Meal meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Pohani oslić");
        meal1.setPrice(8.99);

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Salata");
        meal2.setPrice(6.99);

        List<Meal> meals = Arrays.asList(meal1, meal2);

        Order order = new Order();
        order.setOrderId(1L);
        order.setMeals(meals);
        order.setCreatedAt(LocalDateTime.parse("2023-04-10T12:30:00"));
        order.setUpdatedAt(LocalDateTime.parse("2023-04-10T13:15:00"));
        order.setOrdered(false);

        when(mealMapper.mealToMealDto(meal1)).thenReturn(new MealDto(1L, "Pohani oslić", 8.99));
        when(mealMapper.mealToMealDto(meal2)).thenReturn(new MealDto(2L, "Salata", 6.99));

        OrderDto orderDto = orderMapper.mapToDto(order);

        assertEquals(order.getOrderId(), orderDto.getOrderId());
        assertEquals(order.getMeals().size(), orderDto.getMeals().size());
        assertEquals(order.getCreatedAt(), orderDto.getCreatedAt());
        assertEquals(order.getUpdatedAt(), orderDto.getUpdatedAt());
        assertEquals(order.getOrdered(), orderDto.getOrdered());
    }
}
