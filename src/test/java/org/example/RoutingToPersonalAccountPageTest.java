package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.LogoutPage;
import org.example.pages.RoutingToPersonalAccountPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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
    @DisplayName("Проверка перехода в личный кабинет")
    public void routingToPersonalAccountPageTest() {
        driver.get(BASE_URL);
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        routingToPersonalAccountPage.waitForElementToBeVisible(driver, By.xpath(".//p[text()='Личный Кабинет']/parent::a"), 10);
        routingToPersonalAccountPage.clickPersonalAccountButton();
        routingToPersonalAccountPage.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(), 'Профиль')]"), 10);
        checkCurrentLink(PAGE_PROFILE);
    }
}