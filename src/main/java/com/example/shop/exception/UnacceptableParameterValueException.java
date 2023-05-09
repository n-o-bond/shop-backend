package com.example.shop.exception;

public class UnacceptableParameterValueException extends RuntimeException{

    public UnacceptableParameterValueException(String message) {
        super(message);
    }
}
