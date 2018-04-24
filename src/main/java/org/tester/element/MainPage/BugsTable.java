package org.tester.element.MainPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

public class BugsTable extends HtmlElement {

    @FindBy(css = "table")
    private Table dataTable;

    public int getBugsCount() {
        try {
            return dataTable.getRows().size();
        } catch(NoSuchElementException e) {
            return 0;
        }
    }

    public WebElement getFirstLineElement() {
        return dataTable.getCellAt(0, 2);
    }

    public WebElement getLastListElement() {
        List<List<WebElement>> rows = dataTable.getRows();
        return rows.get(rows.size()-1).get(2);
    }
}
