package com.erc.exception;

public enum ErrorCode {
    USER_REGISTRATION_ERROR("Email address already exist: %s"),
    INTERNAL_SERVER_ERROR("An unexpected error occured. Please try again later.");

    private final String message;

    ErrorCode(String message){
        this.message = message;

    }

    public String getMessage(Object ...args){
        return String.format(message, args);
    }
}
