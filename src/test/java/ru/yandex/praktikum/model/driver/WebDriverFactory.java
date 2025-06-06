package ru.yandex.praktikum.model.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "yandex":
                WebDriverManager.chromedriver().setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                driver = new ChromeDriver(yandexOptions);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        return driver;
    }
}

