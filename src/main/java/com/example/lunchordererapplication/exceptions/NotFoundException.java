package com.example.lunchordererapplication.exceptions;

public class NotFoundException extends RuntimeException {
    public final static String ORDER_NOT_FOUND_MESSAGE = "Order with the provided ID does not exist";
    public final static String MEALS_NOT_FOUND_MESSAGE = "One or more selected meals do not exist";
    public NotFoundException(String message) {
        super(message);
    }
}

