package com.example.lunchordererapplication.controller;

import com.example.lunchordererapplication.dto.OrderDto;
import com.example.lunchordererapplication.infrastructure.Constants;
import com.example.lunchordererapplication.mapper.OrderMapper;
import com.example.lunchordererapplication.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping(Constants.URL_ORDER)
    public ResponseEntity<Long> createOrder(@RequestBody List<Long> mealIds) {
        return ResponseEntity.ok(orderService.createOrder(mealIds));
    }

    @PutMapping(Constants.URL_ORDER_UPDATE)
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody List<Long> mealIds) {
        return ResponseEntity.ok(
                orderMapper.mapToDto(orderService.updateOrder(orderId, mealIds)));
    }
}

