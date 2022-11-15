package org.tester.element.MainPage;

import org.openqa.selenium.support.FindBy;
import org.tester.domain.Bug;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class BugManipulateForm extends HtmlElement {

    @FindBy(xpath = ".//label[contains(text(),'Done')]/following-sibling::div/input")
    private CheckBox doneCheckbox;

    @FindBy(xpath = ".//label[contains(text(),'Name')]/following-sibling::div/input")
    private TextInput nameField;

    @FindBy(xpath = ".//label[contains(text(),'Notes')]/following-sibling::div/textarea")
    private TextInput notesField;

    @FindBy(xpath = ".//label[contains(text(),'Priority')]/following-sibling::div/input")
    private TextInput priorityField;

    @FindBy(xpath = ".//label[contains(text(),'Due')]/following-sibling::div/input")
    private TextInput dueDateField;

    private TextInput createdAtDateField;
    private TextInput createdAtTimeField;

    private TextInput updatedAtDateField;
    private TextInput updatedAtTimeField;

    @FindBy(xpath = ".//span[contains(text(),'OK')]")
    private Button okButton;

    @FindBy(xpath = ".//span[contains(text(),'Cancel')]")
    private Button cancelButton;

    public void fillForm(Bug bug) {
        boolean isChecked = doneCheckbox.getAttribute("aria-checked").contains("true");
        if (isChecked) {
            doneCheckbox.select();
        }
        doneCheckbox.set(bug.isDone());

        nameField.clear();
        nameField.sendKeys(bug.getName());

        notesField.clear();
        notesField.sendKeys(bug.getNotes());

        priorityField.clear();
        priorityField.sendKeys(bug.getPriorityString());

        dueDateField.clear();
        dueDateField.sendKeys(bug.getDueString());
    }

    public void clickOk() {
        okButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }
}