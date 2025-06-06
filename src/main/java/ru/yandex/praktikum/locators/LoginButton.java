package ru.yandex.praktikum.locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginButton {
    private WebDriver driver;


    private By regButton1 = By.xpath("//button[text()='Войти в аккаунт']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private By fieldPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private By loggetAccount = By.xpath("//button[text()='Оформить заказ']");
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By logoutButton = By.xpath("//button[text()='Выход']");
    private By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private By loginLink = By.xpath("//a[text()='Войти']");
    private By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private By constructorLink = By.xpath("//p[text()='Конструктор']");
    private By burgerTitle = By.xpath("//h1[text()='Соберите бургер']");
    private By svgLocator = By.cssSelector("svg[xmlns='http://www.w3.org/2000/svg'][width='290'][height='50']");

    public LoginButton(WebDriver driver) {
        this.driver = driver;
    }

    public By getLoginButton() {
        return loginButton;
    }

    public By getLoggetAccount() {
        return loggetAccount;
    }

    public By getBurgerTitle() {
        return burgerTitle;
    }

    @Step("Click on an SVG element")
    public void clickSvgLocator() {
        driver.findElement(svgLocator).click();
    }

    @Step("Switching from the personal cabinet to the designer click on the designer in the header")
    public void goToDesigner() {
        driver.findElement(constructorLink).click();
    }

    @Step("Click on the ‘Sign in to account’ button")
    public void clickRegButton1() {
        driver.findElement(regButton1).click();
    }

    @Step("Go to the registration form and click the login button")
    public void transitionToRegistrationForm() {
        clickRegButton1();
        driver.findElement(registerLink).click();
        driver.findElement(loginLink).click();
    }

    @Step("Login via the password recovery button")
    public void loginViaPasswordRecoveryButton() {
        clickRegButton1();
        driver.findElement(forgotPasswordLink).click();
        driver.findElement(loginLink).click();
    }

    @Step("Entering Email")
    public void clickFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Password entry")
    public void clickFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click on the ‘Sign In’ button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click on the ‘My Account’ button")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Click on the button to exit the personal cabinet")
    public void logOut() {
        driver.findElement(logoutButton).click();
    }

    @Step("Registration using Email and password")
    public void getLogin(String email, String password) {
        clickFieldEmail(email);
        clickFieldPassword(password);
    }
}



