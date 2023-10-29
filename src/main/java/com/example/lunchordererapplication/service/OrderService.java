package com.example.lunchordererapplication.service;

import com.example.lunchordererapplication.model.Order;
import java.util.List;

public interface OrderService {
    Long createOrder(List<Long> mealIds);
    Order updateOrder(Long orderId, List<Long> mealIds);
    List<Order> getOrdersForOrdering();
    void markOrdersAsOrdered(List<Order> orders);
}

