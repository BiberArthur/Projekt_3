package ru.yandex.praktikum.locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForRegistrationPage {
    private WebDriver driver;

    private By registerButton1 = By.className("Auth_link__1fOlj");
    private By fieldName = By.xpath("//label[text()='Имя']/following-sibling::input");
    private By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private By fieldPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private By registerButton2 = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginOK = By.xpath(".//button[text()='Войти']");
    private By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    public ForRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the ‘Register’ button")
    public void clickButtonRegister() {
        driver.findElement(registerButton1).click();
    }

    @Step("Name entry: {name}")
    public void clickName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Enter Email: {email}")
    public void clickEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Password entry")
    public void clickPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click on the ‘Register’ button")
    public void clickButtonRegister2() {
        driver.findElement(registerButton2).click();
    }

    @Step("Get the ‘Login’ button locator")
    public By getLoginOKLocator() {
        return loginOK;
    }

    @Step("Filling in the registration form")
    public void fillRegForm(String name, String email, String password) {
        clickName(name);
        clickEmail(email);
        clickPassword(password);
    }
    @Step("Error message")
    public By getErrorMessageLocator() {
        return errorMessage;
    }
}


