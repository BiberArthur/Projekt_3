package ru.yandex.praktikum.base.apis;

import io.qameta.allure.Step;
import ru.yandex.praktikum.model.api.NewUser;
import ru.yandex.praktikum.model.api.LoginRequest;
import ru.yandex.praktikum.model.api.DeleteUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import ru.yandex.praktikum.model.api.LogoutUserRequest;
public class UserApi extends BaseApi {

    public UserApi() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("User creation")
    public ValidatableResponse createUser(NewUser createUser) {
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(createUser)
                .post(CREATE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Step("Authorization and receipt of tokens")
    public String[] loginAndGetTokens(LoginRequest loginRequest) {
        ValidatableResponse response = given()
                .log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(loginRequest) // Using loginRequest object
                .post(LOGIN_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

        String accessToken = response.extract().jsonPath().getString("accessToken");
        String refreshToken = response.extract().jsonPath().getString("refreshToken");

        return new String[]{accessToken, refreshToken};
    }

    @Step("Deleting a user")
    public void deleteUser(DeleteUserRequest deleteUserRequest) {
        ValidatableResponse response = given()
                .baseUri(BASE_URL)
                .header("Authorization", deleteUserRequest.getAccessToken())
                .delete(DELETE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(202);
    }
    @Step("Logout")
    public void logout(LogoutUserRequest logoutRequest) {
        ValidatableResponse response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(logoutRequest)
                .post(LOGOUT_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

}

