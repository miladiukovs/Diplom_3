package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.LogoutPage;
import org.junit.Before;
import org.junit.Test;

import static org.example.RestClient.BASE_URL;
import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.PAGE_LOGIN;

@DisplayName("Выход из аккаунта")
public class LogoutPageTest extends BasePageTest {
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private UserMethods methods;
    private User user;

    String accessToken;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        methods = new UserMethods();
        user = getRandom();
        ValidatableResponse createUserResponse = methods.create(user);
        accessToken = createUserResponse.extract().path("accessToken");
        driver.get(BASE_URL);
    }

    @Override
    public void clearUp() {
        methods.delete(accessToken);
        super.clearUp();
    }

    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    public void logoutTest() {
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.clickPersonalAccountButton();
        logoutPage.sleep(1000);
        logoutPage.clickLogoutButton();
        logoutPage.sleep(2000);
        checkCurrentLink(PAGE_LOGIN);
    }
}