package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Browsers {

    WebDriver driver;

    public WebDriver getWebDriver() {
        String browserName = getBrowserName();

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }

    private String getBrowserName() {
        String browser = readFromPropertiesFile();

        if (System.getenv("BROWSER") != null) {
            browser = System.getenv("BROWSER");
        }

        if (System.getProperty("browser") != null) {
            browser = System.getProperty("browser");
        }

        String commandLineBrowser = System.getProperty("browser");
        if (commandLineBrowser != null) {
            browser = commandLineBrowser;
        }

        return browser;
    }

    private String readFromPropertiesFile() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return "chrome";
            }
            properties.load(input);
            return properties.getProperty("browser", "chrome");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "chrome";
    }
}
