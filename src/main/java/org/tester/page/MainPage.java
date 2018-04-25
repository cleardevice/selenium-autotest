package org.tester.page;

import org.jruby.RubyProcess;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tester.domain.Bug;
import org.tester.domain.factory.BugFactory;
import org.tester.element.MainPage.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public MainPage bugAddInForm(Bug bug) {
        addBug(bug);
        waitUntilMaskVisible();

        return this;
    }

    public MainPage bugAddInline(Bug bug) {
        toolbar.clickAddInlineButton();

        try {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            actionFactory.getActions()
                    .sendKeys(bug.getName())
                    .sendKeys(Keys.TAB)
                    .build()
                    .perform();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            actionFactory.getActions()
                    .sendKeys(bug.getNotes())
                    .sendKeys(Keys.TAB)
                    .build()
                    .perform();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            actionFactory.getActions()
                    .sendKeys(bug.getPriorityString())
                    .sendKeys(Keys.TAB)
                    .build()
                    .perform();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            actionFactory.getActions()
                    .sendKeys(bug.getDueString())
                    .perform();

            List<WebElement> line = bugsTable.getLastLine();
            actionFactory.getActions()
                    .click(line.get(1)).perform();

            toolbar.clickApplyButton();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace().toString());
        }

        return this;
    }

    public MainPage bugAddInFormWithError(Bug bug) {
        addBug(bug);

        return this;
    }

    public MainPage bugAddFormClose() {
        bugAddForm.clickCancel();

        return this;
    }

    private MainPage editBug(int bugNumber, Bug bug) {
        bugsTable.getLineNumber(bugNumber).get(2).click();

        toolbar.clickEditInForm();
        waitUntilMaskNotVisible();

        bugEditForm.fillForm(bug);
        bugEditForm.clickOk();

        return this;
    }

    public MainPage editAddInForm(int bugNumber, Bug bug) {
        editBug(bugNumber, bug);
        waitUntilMaskVisible();

        return this;
    }

    public int getBugsCount() {
        return bugsTable.getBugsCount();
    }

    public void clearAllBugs() {
        if (bugsTable.getBugsCount() == 0)
            return;

        actionFactory.getActions()
                .keyDown(Keys.LEFT_SHIFT)
                // Get elements for select by click with shift
                // 2nd element click select line
                .click(bugsTable.getFirstLine().get(2))
                .click(bugsTable.getLastLine().get(2))
                .keyUp(Keys.LEFT_SHIFT)
                .build()
                .perform();

        toolbar.clickDeleteButton();
        waitForConfirmWindow();
        confirmWindow.clickYesButton();
        waitUntilMaskVisible();
    }

    public Bug getBugByLine(int lineNumber) throws ParseException {
        List<WebElement> bugLine = bugsTable.getLineNumber(lineNumber);

        String bugDateString = bugLine.get(5).findElement(By.cssSelector(".x-grid-cell-inner")).getText();
        Date bugDate = new SimpleDateFormat("MM/dd/yyyy").parse(bugDateString);

        boolean isDone = bugLine.get(1).findElement(By.cssSelector(".x-grid-checkheader"))
                    .getAttribute("class").contains("x-grid-checkheader-checked");

        return BugFactory.getInstance().get(
                isDone,
                bugLine.get(2).findElement(By.cssSelector(".x-grid-cell-inner")).getText(),
                bugLine.get(3).findElement(By.cssSelector(".x-grid-cell-inner")).getText(),
                Integer.parseInt(bugLine.get(4).findElement(By.cssSelector(".x-grid-cell-inner")).getText()),
                bugDate);
    }

    public Bug getBugFromLastLine() throws ParseException {
        return getBugByLine(bugsTable.getRowsCount()-1);
    }
}