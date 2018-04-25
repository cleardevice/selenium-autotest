package org.tester;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tester.domain.Bug;
import org.tester.domain.factory.BugFactory;
import org.tester.page.MainPage;
import org.tester.test.BaseTest;

import java.text.ParseException;
import java.util.stream.IntStream;

class BugsFormTest extends BaseTest {

    private static int ITEMS_CREATE_COUNT = 5;

    @BeforeAll
    static void setUp() {
        pageFactory.getMainPage()
                .clearAllBugs();
    }

    @ParameterizedTest
    @MethodSource("range1")
    void addBugInForm(int i) throws ParseException {
        MainPage mainPage = pageFactory.getMainPage();

        int bugsCountBefore = mainPage.getBugsCount();
        Bug bugData = BugFactory.getInstance().getNumered(i);
        mainPage.bugAddInForm(bugData);
        int bugsCountAfter = mainPage.getBugsCount();

        Assertions.assertEquals(bugData, mainPage.getLastBug());
        Assertions.assertEquals(bugsCountBefore+1, bugsCountAfter);
    }

    static IntStream range1() {
        return IntStream.range(1, ITEMS_CREATE_COUNT+1);
    }

    @ParameterizedTest
    @MethodSource("range2")
    void addBugInline(int i) throws ParseException {
        MainPage mainPage = pageFactory.getMainPage();

        int bugsCountBefore = mainPage.getBugsCount();
        Bug bugData = BugFactory.getInstance().getNumered(i);
        mainPage.bugAddInline(bugData);
        int bugsCountAfter = mainPage.getBugsCount();

        Assertions.assertEquals(bugData, mainPage.getLastBug());
        Assertions.assertEquals(bugsCountBefore+1, bugsCountAfter);
    }

    static IntStream range2() {
        return IntStream.range(ITEMS_CREATE_COUNT+1, ITEMS_CREATE_COUNT*2+1);
    }


    @Nested
    class Step1 {
        @Test
        void addBugIfExists() {
            String actualErrorMessage = pageFactory.getMainPage()
                    .bugAddInFormWithError(BugFactory.getInstance().getNumered(1))
                    .bugAddFormClose()
                    .getErrorMessage();

            Assertions.assertEquals("Name has already been taken", actualErrorMessage);
        }

        @Test
        void editBugInForm() {

        }

        @Test
        void deleteAllBugs() {
            MainPage mainPage = pageFactory.getMainPage();

            int bugsCountBefore = mainPage.getBugsCount();
            mainPage.clearAllBugs();
            int bugsCountAfter = mainPage.getBugsCount();

            Assertions.assertEquals(ITEMS_CREATE_COUNT*2, bugsCountBefore);
            Assertions.assertEquals(0, bugsCountAfter);
        }
    }
}