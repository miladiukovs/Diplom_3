package org.example;

import org.example.pages.Browsers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BasePageTest {
    WebDriver driver;

    @Before
    public void setUp() {
        Browsers browsers = new Browsers();
        driver = browsers.getWebDriver();
    }

    @After
    public void clearUp() {
        driver.quit();
    }

    public void checkCurrentLink(String link) {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(link, actual);
    }
}

