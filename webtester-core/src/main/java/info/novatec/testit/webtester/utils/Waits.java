package info.novatec.testit.webtester.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import lombok.extern.slf4j.Slf4j;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.waiting.TimeoutException;
import info.novatec.testit.webtester.waiting.Wait;
import info.novatec.testit.webtester.waiting.WaitConfig;


/**
 * Provides methods for waiting. Among these are standard 'Wait a given amount
 * of time' waits as well as more complicated 'wait for a specific state of an
 * page object waits'.
 *
 * @since 0.9.6
 * @deprecated this class will be removed in v1.3 - use {@link Wait} instead.
 */
@Slf4j
@Deprecated
public final class Waits {

    // TODO: remove in v1.3

    /**
     * Waits the given duration in seconds.
     *
     * @param duration the duration in seconds
     * @since 0.9.6
     */
    public static void waitSeconds(long duration) {
        Wait.exactly(duration, TimeUnit.SECONDS);
    }

    /**
     * Waits the given duration in milliseconds.
     *
     * @param duration the duration in milliseconds
     * @since 0.9.6
     */
    public static void waitMilliseconds(long duration) {
        Wait.exactly(duration, TimeUnit.MILLISECONDS);
    }

    /**
     * Waits the given duration using the given {@link TimeUnit time unit}.
     *
     * @param duration the duration
     * @param timeUnit the time unit to use when converting the duration
     * @since 0.9.8 changed method parameter order
     */
    public static void wait(long duration, TimeUnit timeUnit) {
        Wait.exactly(duration, timeUnit);
    }

    /**
     * Waits up to the provided amount of milliseconds until the given
     * {@link Supplier condition} is met. Per default a wait interval of 100ms
     * is used between checks.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of milliseconds the operation is
     * retried
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static void waitMillisecondsUntil(long timeout, Supplier<Boolean> condition) {
        Wait.withTimeoutOf(timeout, TimeUnit.MILLISECONDS).until(condition);
    }

    /**
     * Waits up to the provided amount of seconds until the given
     * {@link Supplier condition} is met. Per default a wait interval of 100ms
     * is used between checks.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of seconds the operation is retried
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static void waitSecondsUntil(long timeout, Supplier<Boolean> condition) {
        Wait.withTimeoutOf(timeout, TimeUnit.SECONDS).until(condition);
    }

    /**
     * Waits until the given {@link Supplier condition} is met within the
     * allowed time frame (timeout). ALlows for the configuration of the used
     * {@link TimeUnit time unit}. Per default a wait interval of 100ms is used
     * between checks.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of time the operation is retried - the
     * unit decides if its milliseconds, seconds or weeks
     * @param unit the time unit to use when interpreting the timeout
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static void waitUntil(long timeout, TimeUnit unit, Supplier<Boolean> condition) {
        Wait.withTimeoutOf(timeout, unit).until(condition);
    }

    /**
     * Waits until the given {@link Supplier condition} is met within the
     * allowed time frame (timeout). ALlows for the configuration of the used
     * {@link TimeUnit time unit} and check interval.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of time the operation is retried - the
     * unit decides if its milliseconds, seconds or weeks
     * @param unit the time unit to use when interpreting the timeout
     * @param interval the interval (in ms) in which to check if the condition
     * is met
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public static void waitUntil(long timeout, TimeUnit unit, long interval, Supplier<Boolean> condition) {
        Wait.withTimeoutOf(new WaitConfig().setTimeout(timeout).setTimeUnit(unit).setInterval(interval)).until(condition);
    }

    /**
     * Returns a pre-configured {@linkplain WebDriverWait web driver wait} for
     * the given {@link PageObject page object's} {@linkplain Browser browser}
     * to be used when implementing custom wait operations. A timeout must be
     * provided.
     *
     * @param pageObject the page object
     * @param timeout a timeout amount in seconds.
     * @return the initialized {@linkplain WebDriverWait}
     * @since 0.9.6
     */
    public static WebDriverWait getWait(PageObject pageObject, int timeout) {
        return getWait(pageObject.getBrowser(), timeout);
    }

    /**
     * Returns a pre-configured {@linkplain WebDriverWait web driver wait} for
     * the given given {@linkplain Browser browser} to be used when implementing
     * custom wait operations. A timeout must be provided..
     *
     * @param browser the browser
     * @param timeout a timeout amount in seconds.
     * @return the initialized {@linkplain WebDriverWait}
     * @since 0.9.6
     */
    public static WebDriverWait getWait(Browser browser, int timeout) {
        Configuration configuration = browser.getConfiguration();
        long interval = configuration.getWaitInterval();
        return new WebDriverWait(browser.getWebDriver(), timeout, interval);
    }

    /**
     * Waits until the given {@link Predicate condition} is met by the provided
     * {@link PageObject page object}. The page object's browser's configuration
     * is used to retrieve the timeout (in seconds) and check interval to use.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param pageObject the page object on which the condition is invoked
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @param <T> type of the page object
     * @return the page object instance from the parameters for use in fluent API calls
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static <T extends PageObject> T waitUntil(T pageObject, Predicate<? super T> condition) {
        Wait.until(pageObject).has(condition);
        return pageObject;
    }

    /**
     * Waits until the given {@link Predicate condition} is met by the provided
     * {@link PageObject page object}. The timeout must be provided in
     * milliseconds. The check interval of the page object's browser's
     * configuration is used.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of milliseconds the operation is
     * retried
     * @param pageObject the page object on which the condition is invoked
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @param <T> type of the page object
     * @return the page object instance from the parameters for use in fluent API calls
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static <T extends PageObject> T waitMillisecondsUntil(long timeout, T pageObject,
        Predicate<? super T> condition) {
        Wait.withTimeoutOf(timeout, TimeUnit.MILLISECONDS).until(pageObject).has(condition);
        return pageObject;
    }

    /**
     * Waits until the given {@link Predicate condition} is met by the provided
     * {@link PageObject page object}. The timeout must be provided in
     * seconds.The check interval of the page object's browser's configuration
     * is used.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of seconds the operation is retried
     * @param pageObject the page object on which the condition is invoked
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @param <T> type of the page object
     * @return the page object instance from the parameters for use in fluent API calls
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static <T extends PageObject> T waitSecondsUntil(long timeout, T pageObject, Predicate<? super T> condition) {
        Wait.withTimeoutOf(timeout, TimeUnit.SECONDS).until(pageObject).has(condition);
        return pageObject;
    }

    /**
     * Waits until the given {@link Predicate condition} is met by the provided
     * {@link PageObject page object}. Allows for the configuration of the
     * timeout's {@link TimeUnit time unit}. The check interval of the page
     * object's browser's configuration is used.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of time the operation is retried - the
     * unit decides if its milliseconds, seconds or weeks
     * @param unit the time unit to use when interpreting the timeout
     * @param pageObject the page object on which the condition is invoked
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @param <T> type of the page object
     * @return the page object instance from the parameters for use in fluent API calls
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    public static <T extends PageObject> T waitUntil(long timeout, TimeUnit unit, T pageObject,
        Predicate<? super T> condition) {
        Wait.withTimeoutOf(timeout, unit).until(pageObject).has(condition);
        return pageObject;
    }

    /**
     * Waits until the given {@link Predicate condition} is met by the provided
     * {@link PageObject page object}. Allows for the configuration of the
     * timeout's {@link TimeUnit time unit} and check interval.
     * <p>
     * All runtime exceptions occurring within the callback's method will be
     * ignored until the timeout is reached. At which point the latest exception
     * (if any) will be used as the cause of the thrown {@link TimeoutException}
     * .
     *
     * @param timeout the maximum amount of time the operation is retried - the
     * unit decides if its milliseconds, seconds or weeks
     * @param unit the time unit to use when interpreting the timeout
     * @param interval the interval (in ms) in which to check if the condition
     * is met
     * @param pageObject the page object on which the condition is invoked
     * @param condition the callback logic to invoke in order to check
     * of the condition is met
     * @param <T> type of the page object
     * @return the page object instance from the parameters for use in fluent API calls
     * @throws TimeoutException in case the callback did not return
     * <code>true</code> within the allowed time frame
     * @since 0.9.8
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public static <T extends PageObject> T waitUntil(long timeout, TimeUnit unit, long interval, final T pageObject,
        final Predicate<? super T> condition) {
        Wait.withTimeoutOf(new WaitConfig().setTimeout(timeout).setTimeUnit(unit).setInterval(interval))
            .until(pageObject)
            .has(condition);
        return pageObject;
    }

    private Waits() {
    }

}
