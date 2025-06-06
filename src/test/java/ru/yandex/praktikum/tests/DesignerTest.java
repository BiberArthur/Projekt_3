package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import ru.yandex.praktikum.locators.ForDesigner;
import ru.yandex.praktikum.utils.ConfigLoader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import ru.yandex.praktikum.model.driver.WebDriverFactory;

public class DesignerTest {

    private WebDriver driver;
    private ForDesigner objDesigner;
    private ConfigLoader configLoader;

    @Before
    public void openPage() {
        configLoader = new ConfigLoader();
        String browser = configLoader.getBrowser();
        driver = WebDriverFactory.createDriver(browser);
        driver.get(configLoader.getBaseUrl());
        objDesigner = new ForDesigner(driver);
    }

    @Test
    @Description("Checking the image of the bun")
    public void checkBurgerImage() {
        objDesigner.clickSaucesTab();
        objDesigner.clickBunsTab();
        Assert.assertTrue("The image of the bun is not displayed!", objDesigner.isBurgerImageDisplayed());
    }

    @Test
    @Description("Checking the image of the sauce")
    public void checkSauceImage() {
        objDesigner.clickFillingsTab();
        objDesigner.clickSaucesTab();
        Assert.assertTrue("The image of the sauce is not displayed!", objDesigner.isSauceImageDisplayed());
    }

    @Test
    @Description("Checking the filling image")
    public void checkMeatImage() {
        objDesigner.clickSaucesTab();
        objDesigner.clickFillingsTab();
        Assert.assertTrue("The image of the stuffing is not displayed!", objDesigner.isMeatImageDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





