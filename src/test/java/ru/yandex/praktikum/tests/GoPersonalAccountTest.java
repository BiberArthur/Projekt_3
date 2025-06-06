package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import ru.yandex.praktikum.locators.LoginButton;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.model.driver.WebDriverFactory;
import ru.yandex.praktikum.utils.ConfigLoader;
import ru.yandex.praktikum.model.api.NewUser;
import ru.yandex.praktikum.model.api.LoginRequest;
import ru.yandex.praktikum.model.api.DeleteUserRequest;
import ru.yandex.praktikum.base.apis.UserApi;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GoPersonalAccountTest {
    private WebDriver driver;
    private LoginButton loginButton;
    private ConfigLoader configLoader;
    private UserApi userApi;
    private NewUser newUser;
    private String accessToken;
    private Faker faker;

    @Before
    public void openPage() {
        configLoader = new ConfigLoader();
        String browser = configLoader.getBrowser();
        driver = WebDriverFactory.createDriver(browser);
        driver.get(configLoader.getBaseUrl());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginButton = new LoginButton(driver);
        userApi = new UserApi();
        faker = new Faker();

        String uniqueEmail = faker.internet().emailAddress();
        String password = faker.internet().password();

        newUser = new NewUser(uniqueEmail, password, faker.name().fullName());
        userApi.createUser(newUser);

        LoginRequest loginRequest = new LoginRequest(uniqueEmail, password);
        accessToken = userApi.loginAndGetTokens(loginRequest)[0]; // Save the access token
    }

    @Test
    @Description("Successful account login")
    public void loginUser() {
        loginButton.clickPersonalAccountButton();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton.getLoginButton()));

        Assert.assertTrue("The login button is not displayed!", driver.findElement(loginButton.getLoginButton()).isDisplayed());

        // Using test user data
        loginButton.getLogin(newUser.getEmail(), newUser.getPassword());
        loginButton.clickLoginButton();

        // Waiting for the “Checkout” button to appear after successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton.getLoggetAccount())); // Используем геттер

        // Checking that the “Checkout” button is displayed
        Assert.assertTrue("The ‘Checkout’ button is not displayed!", driver.findElement(loginButton.getLoggetAccount()).isDisplayed());
    }

    @After
    public void teardown() {

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(accessToken);
        userApi.deleteUser(deleteUserRequest);
        driver.quit();
    }
}
