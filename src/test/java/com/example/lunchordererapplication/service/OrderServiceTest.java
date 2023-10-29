package com.example.lunchordererapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.model.Order;
import com.example.lunchordererapplication.repository.MealRepository;
import com.example.lunchordererapplication.repository.OrderRepository;
import com.example.lunchordererapplication.service.implementation.OrderServiceImpl;
import com.example.lunchordererapplication.validator.OrderValidator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderValidator orderValidator;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Meal meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Meal 1");
        meal1.setPrice(10.0);

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Meal 2");
        meal2.setPrice(12.0);

        List<Long> mealIds = List.of(meal1.getMealId(), meal2.getMealId());
        List<Meal> selectedMeals = List.of(meal1, meal2);

        when(mealRepository.findAllById(mealIds)).thenReturn(selectedMeals);

        Order newOrder = new Order();
        newOrder.setOrderId(1L);
        newOrder.setMeals(selectedMeals);
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setUpdatedAt(LocalDateTime.now());
        newOrder.setOrdered(false);

        when(orderRepository.save(Mockito.any())).thenReturn(newOrder);

        Long orderId = orderService.createOrder(mealIds);

        assertNotNull(orderId);
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        List<Long> mealIds = List.of(1L, 2L);

        Order existingOrder = new Order();
        existingOrder.setOrderId(orderId);
        existingOrder.setMeals(null);
        existingOrder.setCreatedAt(LocalDateTime.now());
        existingOrder.setUpdatedAt(LocalDateTime.now());
        existingOrder.setOrdered(false);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(mealRepository.findAllById(mealIds)).thenReturn(new ArrayList<>());
        doReturn(existingOrder).when(orderRepository).save(Mockito.any(Order.class));

        Order updatedOrder = orderService.updateOrder(orderId, mealIds);

        assertNotNull(updatedOrder);
    }

    @Test
    public void testGetOrdersForOrdering() {
        List<Order> orders = new ArrayList<>();

        Order orderedOrder = new Order();
        orderedOrder.setOrderId(1L);
        orderedOrder.setOrdered(false);
        orders.add(orderedOrder);

        Order notOrderedOrder = new Order();
        notOrderedOrder.setOrderId(2L);
        notOrderedOrder.setOrdered(false);
        orders.add(notOrderedOrder);

        when(orderRepository.findAllByOrderedFalse()).thenReturn(orders);

        List<Order> result = orderService.getOrdersForOrdering();

        for (Order order : result) {
            assertFalse(order.getOrdered());
        }
    }

    @Test
    public void testMarkOrdersAsOrdered() {

        List<Order> orders = new ArrayList<>();

        Order orderedOrder = new Order();
        orderedOrder.setOrderId(1L);
        orderedOrder.setOrdered(false);
        orders.add(orderedOrder);

        Order notOrderedOrder = new Order();
        notOrderedOrder.setOrderId(2L);
        notOrderedOrder.setOrdered(false);
        orders.add(notOrderedOrder);

        orderService.markOrdersAsOrdered(orders);

        for (Order order : orders) {
            assertTrue(order.getOrdered());
        }
    }

}
