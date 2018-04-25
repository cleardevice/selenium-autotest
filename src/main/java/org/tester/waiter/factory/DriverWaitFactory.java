package org.tester.waiter.factory;

import org.openqa.selenium.WebDriver;
import org.tester.waiter.BaseDriverWait;

public class DriverWaitFactory {
    private WebDriver driver;

    DriverWaitFactory(WebDriver aDriver) {
        driver = aDriver;
    }

    public static DriverWaitFactory getInstance(WebDriver driver) {
        return new DriverWaitFactory(driver);
    }

    public BaseDriverWait getBaseDriverWait() {
        return new BaseDriverWait(driver, 2);
    }
}
