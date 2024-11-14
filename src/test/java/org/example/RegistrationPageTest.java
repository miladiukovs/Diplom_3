package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.RegisterPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.example.UserGenerator.getRandom;
import static org.example.pages.BasePage.PAGE_LOGIN;
import static org.example.pages.BasePage.PAGE_REGISTER;

public class RegistrationPageTest extends BasePageTest {
    private RegisterPage registerPage;
    private UserMethods methods;
    private User user;

    @Before
    public void setUp() {
        super.setUp();
        registerPage = new RegisterPage(driver);
        methods = new UserMethods();
        user = getRandom();
    }

    @Override
    public void clearUp() {
        String accessToken = methods.getAccessToken(user.getUserCredentials());
        super.clearUp();
        if (accessToken != null) {
            methods.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Тест на Успешную регистрацию")
    public void registerNewUserTest() {
        driver.get(PAGE_REGISTER);
        registerPage.signUpNewUser(user.getName(), user.getEmail(), user.getPassword());
        registerPage.sleep(1000);
        checkCurrentLink(PAGE_LOGIN);
    }

    @Test
    @DisplayName("Выдача Ошибки для некорректного пароля")
    public void registerErrorForIncorrectPassword() {
        driver.get(PAGE_REGISTER);
        registerPage.signUpNewUser(user.getName(), user.getEmail(), "54321");
        registerPage.sleep(1000);
        String errorMessage = registerPage.getErrorMessageText();
        Assert.assertEquals("Некорректный пароль", errorMessage);
    }
}
