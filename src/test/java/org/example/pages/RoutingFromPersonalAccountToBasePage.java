package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoutingFromPersonalAccountToBasePage extends BasePage {

    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");

    public RoutingFromPersonalAccountToBasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка перехода на главную по клику на Конструктор")
    public void clickConstructorButton() {
        WebElement element = driver.findElement(constructorButton);
        element.click();
    }

    @Step("Проверка перехода на главную по клику на лого")
    public void clickLogoButton() {
        WebElement element = driver.findElement(logoButton);
        element.click();
    }
}
