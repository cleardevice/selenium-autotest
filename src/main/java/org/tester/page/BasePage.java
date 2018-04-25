package org.tester.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.tester.action.factory.ActionFactory;
import org.tester.waiter.factory.DriverWaitFactory;
import ru.yandex.qatools.htmlelements.element.TextBlock;

class BasePage implements PageObjectInterface {

    protected final String defaultWindowCssSelector = ".x-window.x-window-default";
    protected final String confirmWindowCssSelector = ".x-window.x-message-box";
    protected final String maskCssSelector = "div.x-mask";

    protected DriverWaitFactory driverWaitFactory;
    protected ActionFactory actionFactory;

    @FindBy(id = "msg-div")
    private TextBlock textMessage;

    BasePage(WebDriver driver) {
        driverWaitFactory = DriverWaitFactory.getInstance(driver);
        actionFactory = ActionFactory.getInstance(driver);
    }

    private void waitUntilNotVisible(String selector) {
        driverWaitFactory.getBaseDriverWait()
                .waitUntilNotVisibleByCssSelector(selector);
    }

    private void waitUntilVisible(String selector) {
        driverWaitFactory.getBaseDriverWait()
                .waitUntilVisibleByCssSelector(selector);
    }

    void waitUntilMaskNotVisible() {
        waitUntilNotVisible(maskCssSelector);
    }

    void waitUntilMaskVisible() {
        waitUntilVisible(maskCssSelector);
    }

    void waitForDefaultWindow() {
        waitUntilNotVisible(defaultWindowCssSelector);
    }

    void waitForDefaultWindowClosed() {
        waitUntilVisible(defaultWindowCssSelector);
    }

    void waitForConfirmWindow() {
        waitUntilNotVisible(confirmWindowCssSelector);
    }

    public String getErrorMessage() {
        waitUntilNotVisible("#msg-div");

        String msgTitle = textMessage.findElement(By.cssSelector("#msg-div div.msg h3")).getText();
        Assertions.assertEquals("Error", msgTitle);

        return textMessage.findElement(By.cssSelector("#msg-div div.msg p")).getText();
    }

    public void wait(int mseconds) {
        try {
            Thread.sleep(mseconds);
        } catch (InterruptedException e) {
        }
    }
}
