package com.example.lunchordererapplication.exceptions;

public class InvalidInputException extends  RuntimeException {

    public final static String ORDER_MISSING_ID_MESSAGE = "Order ID must not be null";
    public final static String MEAL_MISSING_ID_MESSAGE = "Meal IDs must not be empty";

    public InvalidInputException(String message) {
        super(message);
    }
}
