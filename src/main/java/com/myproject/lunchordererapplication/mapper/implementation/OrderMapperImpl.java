package com.myproject.lunchordererapplication.mapper.implementation;

import com.myproject.lunchordererapplication.dto.OrderDto;
import com.myproject.lunchordererapplication.mapper.MealMapper;
import com.myproject.lunchordererapplication.mapper.OrderExtraInformationMapper;
import com.myproject.lunchordererapplication.mapper.OrderMapper;
import com.myproject.lunchordererapplication.model.Order;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapperImpl implements OrderMapper {

    private final MealMapper mealMapper;
    private final OrderExtraInformationMapper orderExtraInformationMapper;

    @Override
    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setMeals(order.getMeals().stream()
                .map(mealMapper::mealToMealDto)
                .collect(Collectors.toSet()));
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());
        orderDto.setOrdered(order.getOrdered());
        orderDto.setExtraInformationSet(
                orderExtraInformationMapper.mapAllToDto(order.getExtraInformation()));
        return orderDto;
    }
}
