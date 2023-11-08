package com.myproject.lunchordererapplication.service.implementation;

import com.myproject.lunchordererapplication.exceptions.NotFoundException;
import com.myproject.lunchordererapplication.model.Meal;
import com.myproject.lunchordererapplication.model.Order;
import com.myproject.lunchordererapplication.model.OrderExtraInformation;
import com.myproject.lunchordererapplication.repository.MealRepository;
import com.myproject.lunchordererapplication.repository.OrderRepository;
import com.myproject.lunchordererapplication.service.OrderService;
import com.myproject.lunchordererapplication.validator.OrderValidator;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    @Override
    public Long createOrder(List<Long> mealIds) {
        orderValidator.validateCreate(mealIds);
        final List<Meal> selectedMeals = mealRepository.findAllById(mealIds);

        Order newOrder = new Order();
        newOrder.setAllMeals(new HashSet<>(selectedMeals));
        newOrder.setCreatedAt(ZonedDateTime.now());
        newOrder.setUpdatedAt(ZonedDateTime.now());
        newOrder.setOrdered(false);
        newOrder.setExtraInformation(generateAllOrderExtraInformation(mealIds, newOrder));

        log.info("Creating order with mealIds: {}", mealIds);

        return orderRepository.save(newOrder).getOrderId();
    }

    @Override
    public Order updateOrder(Long orderId, List<Long> mealIds) {
        orderValidator.validateUpdate(mealIds, orderId);
        final Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            final List<Meal> meals = mealRepository.findAllById(mealIds);
            order.setAllMeals(new HashSet<>(meals));
            order.setUpdatedAt(ZonedDateTime.now());
            order.setAllExtraInformation(generateAllOrderExtraInformation(mealIds, order));

            log.info("Updating order with mealIds: {}", mealIds);

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
        log.info("Following orders marked as ordered: {}",
                orders.stream().map(Order::getOrderId).collect(Collectors.toList()));
    }

    private Set<OrderExtraInformation> generateAllOrderExtraInformation(List<Long> mealIds, Order order) {
        Set<OrderExtraInformation> orderExtraInformationSet = new HashSet<>();
        Set<Long> mealIdsSet = new HashSet<>(mealIds);
        for (Long mealId : mealIdsSet) {
            long mealCount = mealIds.stream().filter(id -> id.equals(mealId)).count();
            OrderExtraInformation orderExtraInfo = new OrderExtraInformation();
            orderExtraInfo.setOrder(order);
            orderExtraInfo.setMealId(mealId);
            orderExtraInfo.setMealCount((int) mealCount);
            orderExtraInformationSet.add(orderExtraInfo);
        }
        return orderExtraInformationSet;
    }

}

