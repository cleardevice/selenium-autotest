package org.tester.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDriverWait extends WebDriverWait {

    public BaseDriverWait(WebDriver driver, long timeOutInSeconds) {
        super(driver, timeOutInSeconds);
    }

    public void waitUntilNotVisibleByCssSelector(String selector) {
        this.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    public void waitUntilVisibleByCssSelector(String selector) {
        this.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
    }
}
