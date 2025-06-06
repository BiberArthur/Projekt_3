package ru.yandex.praktikum.model.api;

public class LoginRequest {
    private String email;
    private String password;

    // Constructor with parameters
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor without parameters
    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

