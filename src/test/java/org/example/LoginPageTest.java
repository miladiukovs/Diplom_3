package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;

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
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.sleep(1000);
        loginPage.clickPersonalAccountButton();
        loginPage.sleep(2000);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void loginUserByPersonalAccountButton() {
        driver.get(BASE_URL);
        loginPage.clickPersonalAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.sleep(1000);
        loginPage.clickPersonalAccountButton();
        loginPage.sleep(2000);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void loginUserByRegisterButton() {
        driver.get(PAGE_REGISTER);
        loginPage.clickLoginFromRegistrationForm();
        loginPage.sleep(1000);
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.sleep(1000);
        loginPage.clickPersonalAccountButton();
        loginPage.sleep(2000);
        checkCurrentLink(PAGE_PROFILE);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void loginUserByForgotPasswordButton() {
        driver.get(PAGE_FORGOT_PASSWORD);
        loginPage.clickLoginFromRegistrationForm();
        loginPage.sleep(1000);
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.sleep(1000);
        loginPage.clickPersonalAccountButton();
        loginPage.sleep(2000);
        checkCurrentLink(PAGE_PROFILE);
    }
}