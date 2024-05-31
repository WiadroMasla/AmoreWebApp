package com.tkosmulski.amore.dto;

public class LoginDataDto {
    private String email;
    private String password;

    public LoginDataDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
