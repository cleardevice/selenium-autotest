package org.tester.element.MainPage;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Toolbar extends HtmlElement {

    @FindBy(xpath = ".//span[text()='Add']/parent::button")
    private Button addButton;
    @FindBy(xpath = ".//span[text()='Edit']/parent::button")
    private Button editButton;

    @FindBy(xpath = ".//span[contains(text(),'Apply')]/parent::button")
    private Button applyButton;
    @FindBy(xpath = ".//span[contains(text(),'Delete')]/parent::button")
    private Button deleteButton;

    @FindBy(xpath = ".//span[contains(text(),'Add in form')]/parent::button")
    private Button addInFormButton;
    @FindBy(xpath = ".//span[contains(text(),'Edit in form')]/parent::button")
    private Button editInFormButton;

    @FindBy(xpath = ".//span[contains(text(),'Search')]/parent::button")
    private Button searchButton;

    public void clickAddInFormButton() {
        addInFormButton.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }
}
