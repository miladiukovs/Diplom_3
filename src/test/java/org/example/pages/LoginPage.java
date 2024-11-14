package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    // Локаторы элементов на странице входа
    private final By loginButtonFromRegistrationForm = By.xpath(".//a[text() = 'Войти']");
    private final By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private final By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");

    // Конструктор класса
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Метод для перехода в форму логина со страницы регистрации")
    public void clickLoginFromRegistrationForm() {
        WebElement element = driver.findElement(loginButtonFromRegistrationForm);
        element.click();
    }

    @Step("Метод для нажатия кнопки входа")
    public void clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
    }

    @Step("Метод для выполнения полного процесса входа")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Метод для ввода email")
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.clear(); // Очистка поля перед вводом
        emailField.sendKeys(email);
    }

    @Step("Метод для ввода пароля")
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInput);
        passwordField.clear(); // Очистка поля перед вводом
        passwordField.sendKeys(password);
    }
}
