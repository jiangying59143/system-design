package com.my.webfluxexplicit;

import com.my.webfluxexplicit.pages.HomePage;
import com.my.webfluxexplicit.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxExplicitApplicationTests {

    private WebDriver driver;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        this.driver = new HtmlUnitDriver();
    }

    @AfterEach
    void tearDown() {
        this.driver.quit();
    }

    @Test
    void login() {
        final LoginPage loginPage = HomePage.to(this.driver, this.port);
        loginPage.assertAt();

        HomePage homePage = loginPage.loginForm().username("user").password("password").submit();
        homePage.assertAt();

        LoginPage logoutSuccess = homePage.logout();
        logoutSuccess.assertAt();
    }

}
