package org.tester;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tester.domain.BugFactory;
import org.tester.page.MainPage;
import org.tester.test.BaseTest;

import java.util.stream.IntStream;

class BugsFormTest extends BaseTest {

    private static int MAX_ITEMS_CREATED = 10;

    @BeforeAll
    static void setUp() {
        pageFactory.getMainPage()
                .clearBugs();
    }

    @ParameterizedTest
    @MethodSource("range")
    void addBug(int i) {
        MainPage mainPage = pageFactory.getMainPage();

        int bugsCountBefore = mainPage.getBugsCount();
        mainPage
                .bugAdd(BugFactory.getInstance().getNumered(i));
        int bugsCountAfter = mainPage.getBugsCount();

        Assertions.assertEquals(bugsCountBefore+1, bugsCountAfter);
    }

    static IntStream range() {
        return IntStream.range(1, MAX_ITEMS_CREATED+1);
    }

    @Nested
    class Step1 {
        @Test
        void addBugIfExists() {
            String actualErrorMessage = pageFactory.getMainPage()
                    .bugAddWithError(BugFactory.getInstance().getNumered(1))
                    .getErrorMessage();

            pageFactory.getMainPage()
                    .bugAddFormClose();

            Assertions.assertEquals("Name has already been taken", actualErrorMessage);
        }
    }
}