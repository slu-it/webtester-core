package integration.utils;

import static info.novatec.testit.webtester.utils.Conditions.invisible;
import static info.novatec.testit.webtester.utils.Conditions.present;
import static info.novatec.testit.webtester.utils.Conditions.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.waiting.TimeoutException;
import info.novatec.testit.webtester.waiting.Wait;


public class WaitsIntegrationTest extends AbstractWebTesterIntegrationTest {

    WaitsTestPage page;

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobject/base/index.html";
    }

    @Before
    public void page() {
        page = getBrowser().create(WaitsTestPage.class);
    }

    /* testWaitUntilPresent */

    @Test
    public final void testWaitUntilPresent_ElementIsPresent_ElementFound() {
        Wait.until(page.spanIsPresent).is(present());
    }

    @Test(expected = TimeoutException.class)
    public final void testWaitUntilPresent_ElementIsNotPresent_Exception() {
        Wait.until(page.spanIsNotPresent).is(present());
    }

    /* testWaitUntilVisible */

    @Test
    public final void testWaitUntilVisible_ElementIsVisible_ElementFound() {
        Wait.until(page.spanIsVisible).is(visible());
    }

    @Test(expected = TimeoutException.class)
    public final void testWaitUntilVisible_ElementIsNotVisible_Exception() {
        Wait.until(page.spanIsNotVisible).is(visible());
    }

    /* testWaitUntilInvisible */

    @Test
    public final void testWaitUntilInvisible_ElementIsInvisible_ElementFound() {
        Wait.until(page.spanIsNotVisible).is(invisible());
    }

    @Test(expected = TimeoutException.class)
    public final void testWaitUntilInvisible_ElementIsNotInvisible_Exception() {
        Wait.until(page.spanIsVisible).is(invisible());
    }

    /* UTILITIES */

    public static class WaitsTestPage extends PageObject {

        @IdentifyUsing("spanIsPresent")
        PageObject spanIsPresent;

        @IdentifyUsing("spanIsNotPresent")
        PageObject spanIsNotPresent;

        @IdentifyUsing("spanIsVisible")
        PageObject spanIsVisible;

        @IdentifyUsing("spanIsNotVisible")
        PageObject spanIsNotVisible;

        @PostConstruct
        void checkThatButtonsHaveCorrectStateForTest() {
            assertThat(spanIsVisible.isVisible(), is(true));
            assertThat(spanIsNotVisible.isVisible(), is(false));
        }

    }

}
