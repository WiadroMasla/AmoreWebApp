package com.tkosmulski.amore.service.exceptions;

public class EmailTakenException extends RuntimeException {

    public EmailTakenException(String email) {
        super("Email " + email + " jest zajęty.");
    }
}
