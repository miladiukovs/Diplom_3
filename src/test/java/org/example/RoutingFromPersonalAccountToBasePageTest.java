package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.RoutingFromPersonalAccountToBasePage;
import org.example.pages.RoutingToPersonalAccountPage;
import org.junit.Before;
import org.junit.Test;

import static org.example.RestClient.BASE_URL;
import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.PAGE_PROFILE;

@DisplayName("Выход из аккаунта")
public class RoutingFromPersonalAccountToBasePageTest extends BasePageTest {
    private LoginPage loginPage;
    private RoutingFromPersonalAccountToBasePage routingFromPersonalAccountToBasePage;
    private UserMethods methods;
    private User user;

    String accessToken;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        routingFromPersonalAccountToBasePage = new RoutingFromPersonalAccountToBasePage(driver);
        methods = new UserMethods();
        user = getRandom();
        ValidatableResponse createUserResponse = methods.create(user);
        accessToken = createUserResponse.extract().path("accessToken");

        driver.get(BASE_URL);
        loginPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        routingFromPersonalAccountToBasePage.sleep(1000);
        routingFromPersonalAccountToBasePage.clickPersonalAccountButton();
        routingFromPersonalAccountToBasePage.sleep(1000);
    }

    @Override
    public void clearUp() {
        methods.delete(accessToken);
        super.clearUp();
    }

    @Test
    @DisplayName("Тест перехода на главную по кнопке 'Конструктор'")
    public void constructorButtonToMainTest() {
        routingFromPersonalAccountToBasePage.clickConstructorButton();
        checkCurrentLink(BASE_URL);
    }

    @Test
    @DisplayName("Тест перехода на логотип Stellar Burgers")
    public void logoButtonToMainTest() {
        routingFromPersonalAccountToBasePage.clickLogoButton();
        checkCurrentLink(BASE_URL);
    }
}