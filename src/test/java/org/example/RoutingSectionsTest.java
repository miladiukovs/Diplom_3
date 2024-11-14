package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pages.LoginPage;
import org.example.pages.SectionsOfConstructor;
import org.junit.Before;
import org.junit.Test;

import static org.example.RestClient.BASE_URL;
import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.PAGE_LOGIN;

@DisplayName("Навигация по вкладкам Конструктора бургера")
public class RoutingSectionsTest extends BasePageTest {
    private LoginPage loginPage;
    private UserMethods methods = new UserMethods();
    private SectionsOfConstructor sectionsOfConstructor;
    private User user;

    String accessToken;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        sectionsOfConstructor = new SectionsOfConstructor(driver);
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
    @DisplayName("Переход к разделу \"Булки\"")
    public void routingToBunSection() {
        driver.get(PAGE_LOGIN);
        loginPage.login(user.getEmail(), user.getPassword());
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.clickSauceButton();
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.clickBunsButton();
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.isBunsSelected();
        checkCurrentLink(BASE_URL);
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    public void routingToSauceSection() {
        driver.get(PAGE_LOGIN);
        loginPage.login(user.getEmail(), user.getPassword());
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.clickSauceButton();
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.isSaucesSelected();
        checkCurrentLink(BASE_URL);
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    public void routingToFillingSection() {
        driver.get(PAGE_LOGIN);
        loginPage.login(user.getEmail(), user.getPassword());
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.clickFillingButton();
        sectionsOfConstructor.sleep(1000);
        sectionsOfConstructor.isFillingsSelected();
        checkCurrentLink(BASE_URL);
    }
}