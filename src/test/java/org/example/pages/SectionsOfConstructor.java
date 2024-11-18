package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SectionsOfConstructor extends BasePage {

    private final By bunsButton = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']/parent::div");

    public SectionsOfConstructor(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке «Булки»")
    public void clickBunsButton() {
        WebElement element = driver.findElement(bunsButton);
        element.click();
    }

    @Step("Клик по кнопке «Соусы»")
    public void clickSauceButton() {
        WebElement element = driver.findElement(sauceButton);
        element.click();
    }

    @Step("Клик по кнопке «Начинки»")
    public void clickFillingButton() {
        WebElement element = driver.findElement(fillingsButton);
        element.click();
    }

    @Step("Переход в 'булки' успешно сработал")
    public boolean isBunsSelected() {
        return ExpectedConditions.attributeContains(bunsButton, "class", "tab_tab_type_current").apply(driver);
    }

    @Step("Переход в 'соусы' успешно сработал")
    public boolean isSaucesSelected() {
        return ExpectedConditions.attributeContains(sauceButton, "class", "tab_tab_type_current").apply(driver);
    }

    @Step("Переход в 'начинки' успешно сработал")
    public boolean isFillingsSelected() {
        return ExpectedConditions.attributeContains(fillingsButton, "class", "tab_tab_type_current").apply(driver);
    }
}
