package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.example.RestClient.BASE_URL;
import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.*;

public class LoginPageTest extends BasePageTest {
    private LoginPage loginPage;
    private UserMethods methods;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        methods = new UserMethods();
        user = getRandom();
        ValidatableResponse createUserResponse = methods.create(user);
        accessToken = createUserResponse.extract().path("accessToken");
    }

    @Override
    public void clearUp() {
        methods.delete(accessToken);
        super.clearUp();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginUserByLoginToAccountButton() {
        driver.get(BASE_URL);
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//button[text() = 'Войти в аккаунт']"), 2);
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 2);
        loginPage.clickPersonalAccountButton();
        loginPage.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(), 'Профиль')]"), 10);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void loginUserByPersonalAccountButton() {
        driver.get(BASE_URL);
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 10);
        loginPage.clickPersonalAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 10);
        loginPage.clickPersonalAccountButton();
        loginPage.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(), 'Профиль')]"), 10);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void loginUserByRegisterButton() {
        driver.get(PAGE_REGISTER);
        loginPage.clickLoginFromRegistrationForm();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 2);
        loginPage.clickPersonalAccountButton();
        loginPage.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(), 'Профиль')]"), 2);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void loginUserByForgotPasswordButton() {
        driver.get(PAGE_FORGOT_PASSWORD);
        loginPage.clickLoginFromRegistrationForm();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 2);
        loginPage.clickPersonalAccountButton();
        loginPage.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(), 'Профиль')]"), 2);
        checkCurrentLink(PAGE_PROFILE);
    }
}