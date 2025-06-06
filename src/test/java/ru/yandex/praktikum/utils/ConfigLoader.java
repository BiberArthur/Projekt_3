package ru.yandex.praktikum.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("/Users/arthurbiber/Desktop/projects/Diplom/ST_UI_Tests/config.properties.md")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }
}
