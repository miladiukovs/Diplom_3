package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    // Локаторы элементов на странице регистрации
    private By nameInput = By.xpath(".//*[contains(label,'Имя')]/parent::div/div/input");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");
    private By signUpButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private static final String TEXT_ERROR_PASSWORD_XPATH = ".//p[text()='Некорректный пароль']";
    private final By incorrectPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Метод для выполнения полного процесса входа
    public void signUpNewUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickSignUpButton();
    }

    @Step("Метод для ввода name")
    public void enterName(String email) {
        WebElement emailField = driver.findElement(nameInput);
        emailField.clear(); // Очистка поля перед вводом
        emailField.sendKeys(email);
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

    @Step("Нажатие на кнопку открытия формы регистрации")
    public void clickSignUpButton() {
        WebElement loginBtn = driver.findElement(signUpButton);
        loginBtn.click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessageText() {
        return driver.findElement(By.xpath(TEXT_ERROR_PASSWORD_XPATH)).getText();
    }

}
