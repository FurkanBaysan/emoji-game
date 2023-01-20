package com.etiya.emojigame.core.utils.exceptions;

// The error object to be thrown when the business rules are not valid.
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
