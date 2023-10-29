package com.example.lunchordererapplication.mapper;

import com.example.lunchordererapplication.dto.OrderDto;
import com.example.lunchordererapplication.model.Order;

public interface OrderMapper {
    OrderDto mapToDto(Order order);
}
