package com.myproject.lunchordererapplication.service;

import com.myproject.lunchordererapplication.infrastructure.Constants;
import com.myproject.lunchordererapplication.infrastructure.EmailProperties;
import com.myproject.lunchordererapplication.model.Meal;
import com.myproject.lunchordererapplication.model.Order;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final OrderService orderService;
    private final MealService mealService;
    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    @Scheduled(cron = "0 30 10 * * ?")
    public void sendSummarizedMealListMail() {

        final List<Order> orders = orderService.getOrdersForOrdering();
        if (orders.isEmpty()) {
            return;
        }
        final Map<Long, Long> mealCounter = countMeals(orders);
        final String emailBody = generateSummarizedMealListMailMessage(mealCounter);

        log.info(Constants.SENDING_MAIL_LOG);

        orderService.markOrdersAsOrdered(orders);
        final SimpleMailMessage message = generateSimpleMailMessage(emailBody);
        javaMailSender.send(message);

        log.info(Constants.MAIL_SENT_SUCCESSFULLY_LOG);
    }

    private SimpleMailMessage generateSimpleMailMessage(String emailBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getSender());
        message.setTo(emailProperties.getRecipient());
        message.setText(emailBody);
        message.setSubject(Constants.SUMMARIZED_ORDER_SUBJECT);

        return message;
    }

    private String generateSummarizedMealListMailMessage(Map<Long, Long> mealCounter) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Constants.SUMMARIZED_ORDER_GREETING);
        final Map<Long, Meal> mealMap = convertMealListToMealMap(
                mealService.getMealsByIds(mealCounter.keySet().stream().toList()));

        mealMap.forEach((key, value) -> stringBuilder.append(
                generateIndividualMealRecord(value, mealCounter.get(key))));

        stringBuilder.append(Constants.SUMMARIZED_ORDER_REGARDS);

        return stringBuilder.toString();
    }

    private String generateIndividualMealRecord(Meal meal, Long mealCount) {
        return mealCount + " X " + meal.getMealName() + "\n";
    }

    private Map<Long, Meal> convertMealListToMealMap(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.toMap(Meal::getMealId, meal -> meal));
    }

    private Map<Long, Long> countMeals(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getMeals().stream())
                .collect(Collectors.groupingBy(Meal::getMealId, Collectors.counting()));
    }
}
