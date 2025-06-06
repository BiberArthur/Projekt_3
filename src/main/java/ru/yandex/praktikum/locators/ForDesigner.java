package ru.yandex.praktikum.locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForDesigner {
    private WebDriver driver;

    public ForDesigner(WebDriver driver) {
        this.driver = driver;
    }

    private By burgerTitle = By.xpath("//h1[text()='Соберите бургер']");
    private By bunsTab = By.xpath("//div[.//span[text()='Булки']]");
    private By saucesTab = By.xpath("//div[.//span[text()='Соусы']]");
    private By fillingsTab = By.xpath("//div[.//span[text()='Начинки']]");

    private By burgerImage = By.xpath("//img[@alt='Флюоресцентная булка R2-D3']");
    private By sauceImage = By.xpath("//img[@alt='Соус Spicy-X']");
    private By meatImage = By.xpath("//img[@alt='Мясо бессмертных моллюсков Protostomia']");
    private By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    public By getBurgerTitle() {
        return burgerTitle;
    }

    public By getBunsTab() {
        return bunsTab;
    }

    public By getSaucesTab() {
        return saucesTab;
    }

    public By getFillingsTab() {
        return fillingsTab;
    }

    public By getBurgerImage() {
        return burgerImage;
    }
    @Step("Click on the 'Buns' tab")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }
    @Step("Click on the ‘Sauces’ tab")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }
    @Step("Click on the ‘Toppings’ tab")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }
    @Step("Checking the display of the bun image")
    public boolean isBurgerImageDisplayed() {
        return driver.findElement(burgerImage).isDisplayed();
    }
    @Step("Checking the display of the sauce image")
    public boolean isSauceImageDisplayed() {
        return driver.findElement(sauceImage).isDisplayed();
    }
    @Step("Checking the display of the stuffing image")
    public boolean isMeatImageDisplayed() {
        return driver.findElement(meatImage).isDisplayed();
    }
    @Step("Checking the display of the error message")
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}





