package com.app.user_app.exception;


public class ApplicationUserNotFoundException extends RuntimeException {
    public ApplicationUserNotFoundException(String message) {
        super(message);
    }

}
