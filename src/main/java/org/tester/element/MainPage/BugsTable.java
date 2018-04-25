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

    public List<WebElement> getFirstLine() {
        List<List<WebElement>> rows = dataTable.getRows();
        return rows.get(0);
    }

    public List<WebElement> getLastLine() {
        List<List<WebElement>> rows = dataTable.getRows();
        return rows.get(rows.size()-1);
    }
}
