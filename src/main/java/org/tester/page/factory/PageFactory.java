package org.tester.page.factory;

import org.openqa.selenium.WebDriver;
import org.tester.page.MainPage;

public class PageFactory {

    private WebDriver driver;

    PageFactory(WebDriver aDriver) {
        driver = aDriver;
        driver.get("http://autotesttask.herokuapp.com/");
    }

    public static PageFactory getInstance(WebDriver driver) {
        return new PageFactory(driver);
    }

    public MainPage getMainPage() {
        return new MainPage(driver);
    }
}
