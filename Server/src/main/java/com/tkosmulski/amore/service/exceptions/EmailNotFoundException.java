package com.tkosmulski.amore.service.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Email " + email + "not found");
    }
}
