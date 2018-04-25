package org.tester.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tester.domain.Bug;
import org.tester.element.MainPage.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = ".x-panel-body")
    private BugsTable bugsTable;

    @FindBy(xpath = "//div[@role='toolbar']")
    private Toolbar toolbar;

    @FindBy(id = "bugs__add_form")
    private BugManipulateForm bugAddForm;

    @FindBy(id = "bugs__edit_form")
    private BugManipulateForm bugEditForm;

    @FindBy(id = "bugs__search_form")
    private BugsSearchForm bugsSearchForm;

    @FindBy(css = confirmWindowCssSelector)
    private ConfirmWindow confirmWindow;

    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    private void addBug(Bug bug) {
        toolbar.clickAddInFormButton();
        waitUntilMaskNotVisible();

        bugAddForm.fillForm(bug);
        bugAddForm.clickOk();
    }

    public MainPage bugAdd(Bug bug) {
        addBug(bug);
        waitUntilMaskVisible();

        return this;
    }

    public MainPage bugAddWithError(Bug bug) {
        addBug(bug);

        return this;
    }

    public MainPage bugAddFormClose() {
        bugAddForm.clickCancel();

        return this;
    }

    public MainPage editBug(int bugNumber, Bug bug) {
        return this;
    }

    public int getBugsCount() {
        return bugsTable.getBugsCount();
    }

    public void clearBugs() {
        if (bugsTable.getBugsCount() == 0)
            return;

        List<WebElement> list = new ArrayList<>();
        list.add(bugsTable.getFirstLineElement());
        list.add(bugsTable.getLastListElement());
        ctrlClickAction.perform(list);

        toolbar.clickDeleteButton();
        waitForConfirmWindow();
        confirmWindow.clickYesButton();
        waitUntilMaskVisible();
    }
}