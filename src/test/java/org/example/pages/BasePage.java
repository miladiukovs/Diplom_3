package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.RestClient.BASE_URL;

public class BasePage {

    public static final String PAGE_PROFILE = BASE_URL + "account/profile";
    public static final String PAGE_REGISTER = BASE_URL + "register";
    public static final String PAGE_LOGIN = BASE_URL + "login";
    public static final String PAGE_FORGOT_PASSWORD = BASE_URL + "forgot-password";

    protected WebDriver driver;

    private final By buttonLoginToPersonalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By buttonLoginToAccount = By.xpath(".//button[text() = 'Войти в аккаунт']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кнопка открытия личного кабинета")
    public void clickPersonalAccountButton() {
        WebElement element = driver.findElement(buttonLoginToPersonalAccount);
        element.click();
    }
    @Step("Метод для ввода email 'Войти в аккаунт'")
    public void clickLoginToAccountButton() {
        WebElement element = driver.findElement(buttonLoginToAccount);
        element.click();
    }

    @Step("Метод для ожидания элемента")
    public void waitForElementToBeVisible(WebDriver driver, By locator, long timeoutInSeconds) {
        Duration duration = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
