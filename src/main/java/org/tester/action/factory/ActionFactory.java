package org.tester.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionFactory {
    private WebDriver driver;

    ActionFactory(WebDriver aDriver) {
        driver = aDriver;
    }

    public static ActionFactory getInstance(WebDriver driver) {
        return new ActionFactory(driver);
    }

    public Actions getActions() { return new Actions(driver); }
}
