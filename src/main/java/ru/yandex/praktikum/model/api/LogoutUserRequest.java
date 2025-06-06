package ru.yandex.praktikum.model.api;

public class LogoutUserRequest {

    private String token;

    // Constructor with parameters
    public LogoutUserRequest(String token) {
        this.token = token;
    }

    // Constructor without parameters
    public LogoutUserRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

