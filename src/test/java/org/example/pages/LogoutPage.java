package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage extends BasePage{

    // Локаторы элементов на странице входа
    private final By loginButton = By.xpath(".//button[text() = 'Выход']");

    // Конструктор класса
    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Метод для ввода email")
    public void clickLogoutButton() {
        WebElement element = driver.findElement(loginButton);
        element.click();
    }
}
