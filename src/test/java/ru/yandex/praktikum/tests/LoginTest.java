package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.locators.LoginButton;
import ru.yandex.praktikum.model.api.NewUser;
import ru.yandex.praktikum.model.api.LoginRequest;
import ru.yandex.praktikum.model.api.DeleteUserRequest;
import ru.yandex.praktikum.base.apis.UserApi;
import ru.yandex.praktikum.model.driver.WebDriverFactory;
import ru.yandex.praktikum.utils.ConfigLoader;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private LoginButton loginButton;
    private String methodToInvoke;
    private ConfigLoader configLoader;
    private UserApi userApi;
    private String userToken;
    private String userEmail;
    private String userPassword;
    private Faker faker;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"clickRegButton1"},
                {"clickPersonalAccountButton"},
                {"transitionToRegistrationForm"},
                {"loginViaPasswordRecoveryButton"}
        });
    }

    public LoginTest(String methodToInvoke) {
        this.methodToInvoke = methodToInvoke;
    }

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

        // Creating a new user
        userEmail = faker.internet().emailAddress();
        userPassword = faker.internet().password();
        NewUser newUser = new NewUser(userEmail, userPassword, faker.name().fullName());
        userApi.createUser(newUser);

        // User authorization and token receipt
        LoginRequest loginRequest = new LoginRequest(userEmail, userPassword);
        String[] tokens = userApi.loginAndGetTokens(loginRequest); // Getting tokens after login
        this.userToken = tokens[0]; // Save accessToken
    }

    @Test
    @Description("Login to the account from various project locations")
    public void loginTest() throws Exception {

        switch (methodToInvoke) {
            case "clickRegButton1":
                loginButton.clickRegButton1();
                break;
            case "clickPersonalAccountButton":
                loginButton.clickPersonalAccountButton();
                break;
            case "transitionToRegistrationForm":
                loginButton.transitionToRegistrationForm();
                break;
            case "loginViaPasswordRecoveryButton":
                loginButton.loginViaPasswordRecoveryButton();
                break;
            default:
                throw new IllegalArgumentException("Incorrect method: " + methodToInvoke);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton.getLoginButton())); // Wait until the “Sign In” button element is visible

        // Checking that the “Login” button is displayed
        Assert.assertTrue("The login button is not displayed!", driver.findElement(loginButton.getLoginButton()).isDisplayed());

        // Login using the data of the created user
        loginButton.getLogin(userEmail, userPassword); // Enter email and password
        loginButton.clickLoginButton(); // Press the button to log in

        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton.getLoggetAccount()));

        // Checking that the “Checkout” button is displayed
        Assert.assertTrue("The ‘Checkout’ button is not displayed!", driver.findElement(loginButton.getLoggetAccount()).isDisplayed());
        loginButton.clickPersonalAccountButton(); // Click on the personal account button
    }

    @After
    public void teardown() {

        if (userToken != null) {
            DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userToken);
            userApi.deleteUser(deleteUserRequest);
        }
        driver.quit();
    }
}
