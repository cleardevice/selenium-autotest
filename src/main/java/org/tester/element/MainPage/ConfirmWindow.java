package org.tester.element.MainPage;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class ConfirmWindow extends HtmlElement {

    @FindBy(xpath = ".//span[text()='Yes']/parent::button")
    private Button yesButton;
    @FindBy(xpath = ".//span[text()='No']/parent::button")
    private Button noButton;

    public void clickYesButton() {
        yesButton.click();
    }

    public void clickNoButton() {
        noButton.click();
    }
}
