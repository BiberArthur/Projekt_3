package ru.yandex.praktikum.tests;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.locators.LoginButton;
import ru.yandex.praktikum.model.driver.WebDriverFactory;
import ru.yandex.praktikum.utils.ConfigLoader;
import ru.yandex.praktikum.model.api.NewUser;
import ru.yandex.praktikum.model.api.LoginRequest;
import ru.yandex.praktikum.model.api.DeleteUserRequest;
import ru.yandex.praktikum.base.apis.UserApi;
import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class LogoutTest {

    private WebDriver driver;
    private LoginButton objLogout;
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

        objLogout = new LoginButton(driver);
        userApi = new UserApi();
        faker = new Faker();

        String uniqueEmail = faker.internet().emailAddress();
        String password = faker.internet().password();

        newUser = new NewUser(uniqueEmail, password, faker.name().fullName());
        userApi.createUser(newUser);

        // Log in and get the tokens
        LoginRequest loginRequest = new LoginRequest(uniqueEmail, password);
        accessToken = userApi.loginAndGetTokens(loginRequest)[0]; // Save the access token

    }

    @Test
    @Description("Checks that the user can successfully log out of the profile")
    public void logoutFromAccount() {
        objLogout.clickRegButton1();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(objLogout.getLoginButton()));

        Assert.assertTrue("The login button is not displayed!", driver.findElement(objLogout.getLoginButton()).isDisplayed());

        objLogout.getLogin(newUser.getEmail(), newUser.getPassword());
        objLogout.clickLoginButton(); // Press the button to log in

        wait.until(ExpectedConditions.visibilityOfElementLocated(objLogout.getLoggetAccount()));
        objLogout.clickPersonalAccountButton();
        objLogout.logOut();

        Assert.assertTrue("The login button is not displayed!", driver.findElement(objLogout.getLoginButton()).isDisplayed());
    }

    @After
    public void teardown() {
        // Deleting a user by token
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(accessToken);
        userApi.deleteUser(deleteUserRequest);
        driver.quit(); // Close the browser
    }
}

