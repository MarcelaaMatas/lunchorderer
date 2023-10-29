package com.example.lunchordererapplication.service.implementation;

import com.example.lunchordererapplication.exceptions.NotFoundException;
import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.model.Order;
import com.example.lunchordererapplication.repository.MealRepository;
import com.example.lunchordererapplication.repository.OrderRepository;
import com.example.lunchordererapplication.service.OrderService;
import com.example.lunchordererapplication.validator.OrderValidator;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    @Override
    public Long createOrder(List<Long> mealIds) {
        orderValidator.validateCreate(mealIds);
        List<Meal> selectedMeals = mealRepository.findAllById(mealIds);
        Order newOrder = new Order();
        newOrder.setMeals(selectedMeals);
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setUpdatedAt(LocalDateTime.now());
        newOrder.setOrdered(false);

        return orderRepository.save(newOrder).getOrderId();
    }

    @Override
    public Order updateOrder(Long orderId, List<Long> mealIds) {
        orderValidator.validateUpdate(mealIds, orderId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            List<Meal> meals = mealRepository.findAllById(mealIds);
            order.setMeals(meals);
            order.setUpdatedAt(LocalDateTime.now());

            return orderRepository.save(order);
        } else {
            throw new NotFoundException(NotFoundException.ORDER_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<Order> getOrdersForOrdering() {
        return orderRepository.findAllByOrderedFalse();
    }

    @Override
    public void markOrdersAsOrdered(List<Order> orders) {
        orders.forEach(order -> order.setOrdered(true));
        orderRepository.saveAll(orders);
    }


}

