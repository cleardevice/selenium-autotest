package org.tester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tester.domain.BugFactory;
import org.tester.page.MainPage;
import org.tester.test.BaseTest;

class BugsFormTest extends BaseTest {

    @BeforeAll
    static void setUp() {
        pageFactory.getMainPage()
                .clearBugs();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
    void addItem(int i) {
        MainPage mainPage = pageFactory.getMainPage();

        int bugsCountBefore = mainPage.getBugsCount();
        mainPage
                .addBug(BugFactory.getInstance().getNumered(i));
        int bugsCountAfter = mainPage.getBugsCount();

        Assertions.assertEquals(bugsCountBefore, bugsCountAfter);
    }

    @Test
    void addBugIfExists() {
        String actualErrorMessage = pageFactory.getMainPage()
                .addBug(BugFactory.getInstance().getNumered(1))
                .getErrorMessage();

        Assertions.assertEquals("Name has already been taken", actualErrorMessage);
    }
}