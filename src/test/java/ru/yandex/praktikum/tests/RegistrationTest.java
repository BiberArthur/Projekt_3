package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import ru.yandex.praktikum.locators.ForRegistrationPage;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.model.driver.WebDriverFactory;
import ru.yandex.praktikum.utils.ConfigLoader;
import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import ru.yandex.praktikum.base.apis.UserApi;
import ru.yandex.praktikum.model.api.DeleteUserRequest;

public class RegistrationTest {
    private WebDriver driver;
    private ForRegistrationPage objForRegistrationPage;
    private ConfigLoader configLoader;
    private Faker faker;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void openPage() {
        configLoader = new ConfigLoader();
        String browser = configLoader.getBrowser();
        driver = WebDriverFactory.createDriver(browser);
        driver.get(configLoader.getBaseUrl() + "/register");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        objForRegistrationPage = new ForRegistrationPage(driver);
        faker = new Faker();
        userApi = new UserApi();
    }

    @Test
    @Description("Verifies that the user can successfully register through the UI.")
    public void registerUser() {

        objForRegistrationPage.fillRegForm(faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password());

        objForRegistrationPage.clickButtonRegister2();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(objForRegistrationPage.getLoginOKLocator()));

        // Checking that the “Login” button is displayed
        Assert.assertTrue("The ‘Login’ button is not displayed after successful registration!",
                driver.findElement(objForRegistrationPage.getLoginOKLocator()).isDisplayed());

        System.out.println("Пользователь успешно зарегистрирован.");
    }

    @Test
    @Description("Error when entering a short password")
    public void testInvalidPasswordError() {
        objForRegistrationPage.fillRegForm("Stanislaw", "email", "123");
        objForRegistrationPage.clickButtonRegister2();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(objForRegistrationPage.getErrorMessageLocator()));

        Assert.assertTrue("The error message for an invalid password is not displayed!",
                driver.findElement(objForRegistrationPage.getErrorMessageLocator()).isDisplayed());
    }

    @After
    public void teardown() {

        if (accessToken != null) {
            DeleteUserRequest deleteUserRequest = new DeleteUserRequest(accessToken);
            userApi.deleteUser(deleteUserRequest);
        }

        driver.quit();
    }
}