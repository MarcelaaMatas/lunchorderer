package com.example.lunchordererapplication.service;

import static com.example.lunchordererapplication.infrastructure.Constants.MAIL_SENT_SUCCESSFULLY_LOG;
import static com.example.lunchordererapplication.infrastructure.Constants.SENDING_MAIL_LOG;
import static com.example.lunchordererapplication.infrastructure.Constants.SUMMARIZED_ORDER_SUBJECT;

import com.example.lunchordererapplication.infrastructure.Constants;
import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.model.Order;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${email.daily.order.sender}")
    private String sender;
    @Value("${email.daily.order.recipient}")
    private String recipient;

    private final OrderService orderService;
    private final MealService mealService;
    private final JavaMailSender javaMailSender;

    @Scheduled(cron = "0 30 10 * * ?")
    public void sendSummarizedMealListMail() {

        List<Order> orders = orderService.getOrdersForOrdering();
        if (orders.isEmpty()) {
            return;
        }
        Map<Long, Long> mealCounter = countMeals(orders);
        String emailBody = generateSummarizedMealListMailMessage(mealCounter);

        log.info(SENDING_MAIL_LOG);

        orderService.markOrdersAsOrdered(orders);
        SimpleMailMessage message = generateSimpleMailMessage(emailBody);
        javaMailSender.send(message);

        log.info(MAIL_SENT_SUCCESSFULLY_LOG);
    }

    private SimpleMailMessage generateSimpleMailMessage(String emailBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setText(emailBody);
        message.setSubject(SUMMARIZED_ORDER_SUBJECT);

        return message;
    }

    private String generateSummarizedMealListMailMessage(Map<Long, Long> mealCounter) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Constants.SUMMARIZED_ORDER_GREETING);
        Map<Long, Meal> mealMap = convertMealListToMealMap(
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
