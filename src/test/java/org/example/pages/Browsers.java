package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browsers {

    WebDriver driver;
    public WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                return driver;
            case "firefox":
                driver = new FirefoxDriver();
                return driver;
        }
        return driver;
    }
}