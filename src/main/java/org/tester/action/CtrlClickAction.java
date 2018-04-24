package org.tester.action;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CtrlClickAction {

    private WebDriver driver;

    public CtrlClickAction(WebDriver aDriver) {
        driver = aDriver;
    }

    public void perform(List<WebElement> elementsToClick) {
        Actions actions = new Actions(driver)
                .keyDown(Keys.LEFT_SHIFT);

        for (WebElement w: elementsToClick) {
            actions.click(w);
        }

        actions
                .keyUp(Keys.LEFT_SHIFT)
                .build()
                .perform();
    }
}
