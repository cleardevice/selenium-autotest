package org.tester.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.tester.page.factory.PageFactory;

import java.net.MalformedURLException;
import java.net.URI;

public class BaseTest {

    private static WebDriver driver;
    protected static PageFactory pageFactory;

    static WebDriver getDriver() throws MalformedURLException {
        if (driver != null)
            return driver;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true );
        capabilities.setCapability("enableVideo", true );

        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeAll
    static void setUpBaseTest() throws MalformedURLException {
        pageFactory = PageFactory.getInstance(getDriver());
    }

    @AfterAll
    static void killWebDriver() {
        driver.quit();
    }
}
