package com.klymchuk.school.error.exceptions;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String message) {
        super(message);
    }
}
