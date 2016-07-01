package info.novatec.testit.webtester.waiting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Supplier;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(Enclosed.class)
public class WaitTest {

    @RunWith(MockitoJUnitRunner.class)
    public static abstract class AbstractWaitTest {

        @Mock
        Waiter waiter;

        @Before
        public void rememberAndReplaceOriginalWaiter() {
            Wait.setWaiter(new Supplier<Waiter>() {
                @Override
                public Waiter get() {
                    return waiter;
                }
            });
        }

        @After
        public void restoreOriginalWaiter() {
            Wait.setWaiter(Wait.DEFAULT_WAITER);
        }

    }

    public static class WithTimeoutOf_Timeout extends AbstractWaitTest {

        @Test
        public void configuredWaitIsCreatedWithSameWaiter() {
            ConfiguredWait wait = Wait.withTimeoutOf(1);
            assertThat(wait.getWaiter()).isSameAs(waiter);
        }

        @Test
        public void configuredWaitIsCreatedWithCorrectConfiguration() {
            ConfiguredWait wait = Wait.withTimeoutOf(1);
            WaitConfig config = wait.getConfig();
            assertThat(config.getTimeout()).isEqualTo(1);
            assertThat(config.getTimeUnit()).isEqualTo(WaitConfig.DEFAULT_TIME_UNIT);
            assertThat(config.getInterval()).isEqualTo(WaitConfig.DEFAULT_INTERVAL);
        }

    }

    public static class WithTimeoutOf_TimeoutAndUnit extends AbstractWaitTest {

        @Test
        public void configuredWaitIsCreatedWithSameWaiter() {
            ConfiguredWait wait = Wait.withTimeoutOf(1, TimeUnit.MINUTES);
            assertThat(wait.getWaiter()).isSameAs(waiter);
        }

        @Test
        public void configuredWaitIsCreatedWithCorrectConfiguration() {
            ConfiguredWait wait = Wait.withTimeoutOf(1, TimeUnit.MINUTES);
            WaitConfig config = wait.getConfig();
            assertThat(config.getTimeout()).isEqualTo(1);
            assertThat(config.getTimeUnit()).isEqualTo(TimeUnit.MINUTES);
            assertThat(config.getInterval()).isEqualTo(WaitConfig.DEFAULT_INTERVAL);
        }

    }

    public static class WithTimeoutOf_WaitConfig extends AbstractWaitTest {

        WaitConfig config = new WaitConfig().setTimeout(42).setTimeUnit(TimeUnit.SECONDS).setInterval(150);

        @Test
        public void configuredWaitIsCreatedWithSameWaiter() {
            ConfiguredWait wait = Wait.withTimeoutOf(config);
            assertThat(wait.getWaiter()).isSameAs(waiter);
        }

        @Test
        public void configuredWaitIsCreatedWithCorrectConfiguration() {
            ConfiguredWait wait = Wait.withTimeoutOf(config);
            assertThat(wait.getConfig()).isSameAs(config);
        }

    }

    public static class Until_Object extends AbstractWaitTest {

        @Mock
        Object object;

        @Test
        public void waitUntilIsCreatedWithSameWaiter() {
            WaitUntil<Object> until = Wait.until(object);
            assertThat(until.getWaiter()).isSameAs(waiter);
        }

        @Test
        public void waitUntilIsCreatedWithDefaultConfiguration() {
            WaitUntil<Object> until = Wait.until(object);
            WaitConfig config = until.getConfig();
            assertThat(config.getTimeout()).isEqualTo(WaitConfig.DEFAULT_TIMEOUT);
            assertThat(config.getTimeUnit()).isEqualTo(WaitConfig.DEFAULT_TIME_UNIT);
            assertThat(config.getInterval()).isEqualTo(WaitConfig.DEFAULT_INTERVAL);
        }

        @Test
        public void waitUntilIsCreatedForObject() {
            WaitUntil<Object> until = Wait.until(object);
            assertThat(until.getObject()).isSameAs(object);
        }

    }

    public static class Until_PageFragment extends AbstractWaitTest {

        @Mock
        Configuration configuration;
        @Mock
        Browser browser;
        @Mock
        PageObject pageObject;

        @Before
        public void setUpFragment() {
            doReturn(configuration).when(browser).getConfiguration();
            doReturn(browser).when(pageObject).getBrowser();
            doReturn(1).when(configuration).getWaitTimeout();
            doReturn(50L).when(configuration).getWaitInterval();
        }

        @Test
        public void waitUntilIsCreatedWithSameWaiter() {
            WaitUntil<PageObject> until = Wait.until(pageObject);
            assertThat(until.getWaiter()).isSameAs(waiter);
        }

        @Test
        public void waitUntilIsCreatedWithFragmentsConfiguration() {
            WaitUntil<PageObject> until = Wait.until(pageObject);
            WaitConfig config = until.getConfig();
            assertThat(config.getTimeout()).isEqualTo(1);
            assertThat(config.getTimeUnit()).isEqualTo(TimeUnit.SECONDS);
            assertThat(config.getInterval()).isEqualTo(50L);
        }

        @Test
        public void waitUntilIsCreatedForPageFragment() {
            WaitUntil<PageObject> until = Wait.until(pageObject);
            assertThat(until.getObject()).isSameAs(pageObject);
        }

    }

    public static class Exactly extends AbstractWaitTest {

        @Test
        public void isDirectlyDelegatedToWaiter() {
            Wait.exactly(10, TimeUnit.SECONDS);
            verify(waiter).waitExactly(10, TimeUnit.SECONDS);
        }

    }

}
