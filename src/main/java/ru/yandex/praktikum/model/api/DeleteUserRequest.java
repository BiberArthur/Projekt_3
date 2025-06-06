package ru.yandex.praktikum.model.api;

public class DeleteUserRequest {
    private String accessToken;

    public DeleteUserRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    public DeleteUserRequest() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

