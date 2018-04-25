package org.tester.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.tester.page.factory.PageFactory;

import java.net.MalformedURLException;
import java.net.URI;

@Configuration
@ComponentScan("org.tester")
public class AppConfig {

    @Bean
    public WebDriver getWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true );
        capabilities.setCapability("enableVideo", true );

        WebDriver driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().window().maximize();

        return driver;
    }

    @Bean
    public PageFactory getPageFactory() throws MalformedURLException {
        return PageFactory.getInstance(getWebDriver());
    }
}
