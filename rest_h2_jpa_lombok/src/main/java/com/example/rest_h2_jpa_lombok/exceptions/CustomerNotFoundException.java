package com.example.rest_h2_jpa_lombok.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public static final long serialVerisionUID = 1;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
