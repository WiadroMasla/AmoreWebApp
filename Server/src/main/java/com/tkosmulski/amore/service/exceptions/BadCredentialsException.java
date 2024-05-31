package com.tkosmulski.amore.service.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Nieprawidłowe dane logowania.");
    }
}
