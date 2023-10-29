package com.example.lunchordererapplication.mapper.implementation;

import com.example.lunchordererapplication.dto.OrderDto;
import com.example.lunchordererapplication.mapper.MealMapper;
import com.example.lunchordererapplication.mapper.OrderMapper;
import com.example.lunchordererapplication.model.Order;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapperImpl implements OrderMapper {

    private final MealMapper mealMapper;

    @Override
    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setMeals(order.getMeals().stream()
                .map(mealMapper::mealToMealDto)
                .collect(Collectors.toList()));
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());
        orderDto.setOrdered(order.getOrdered());
        return orderDto;
    }
}
