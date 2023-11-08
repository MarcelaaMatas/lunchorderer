package com.myproject.lunchordererapplication.mapper;

import com.myproject.lunchordererapplication.dto.OrderDto;
import com.myproject.lunchordererapplication.model.Order;

public interface OrderMapper {
    OrderDto mapToDto(Order order);
}
