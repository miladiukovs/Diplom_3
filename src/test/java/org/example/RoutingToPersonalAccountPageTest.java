package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.LogoutPage;
import org.example.pages.RoutingToPersonalAccountPage;
import org.junit.Before;
import org.junit.Test;

import static org.example.RestClient.BASE_URL;
import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.PAGE_LOGIN;
import static org.example.pages.BasePage.PAGE_PROFILE;

@DisplayName("Выход из аккаунта")
public class RoutingToPersonalAccountPageTest extends BasePageTest {
    private LoginPage loginPage;
    private RoutingToPersonalAccountPage routingToPersonalAccountPage;
    private UserMethods methods;
    private User user;

    String accessToken;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        routingToPersonalAccountPage = new RoutingToPersonalAccountPage(driver);
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
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    public void routingToPersonalAccountPageTest() {
        driver.get(BASE_URL);
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        routingToPersonalAccountPage.sleep(1000);
        routingToPersonalAccountPage.clickPersonalAccountButton();
        routingToPersonalAccountPage.sleep(1000);
        checkCurrentLink(PAGE_PROFILE);
    }
}