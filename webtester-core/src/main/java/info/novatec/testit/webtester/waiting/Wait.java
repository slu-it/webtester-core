package info.novatec.testit.webtester.waiting;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;

import lombok.Setter;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class provides a fluent API for wait operations.
 * <p>
 * <b>Example:</b>
 * <pre>
 * Wait.exactly(5, TimeUnit.SECONDS);
 * Wait.until(pageObject).is(visible());
 * Wait.withTimeoutOf(5).until(pageObject).is(visible());
 * Wait.withTimeoutOf(5, TimeUnit.SECONDS).until(pageObject).is(visible());
 * Wait.until(pageObject).is(visible()).but().not(editable());
 * </pre>
 *
 * @see Waiter
 * @since 1.2
 */
public final class Wait {

    /** The default {@link Waiter} supplier. Generates a new {@link DefaultWaiter} for each call. */
    public static final Supplier<Waiter> DEFAULT_WAITER = new Supplier<Waiter>() {
        @Override
        public Waiter get() {
            return new DefaultWaiter();
        }
    };

    /**
     * A supplier used to get a {@link Waiter} instance to use when executing any wait operations.
     * The supplier can be changed externally to customize the waiting behavior.
     * Since this is a static field you should keep in mind that this will have an JVM global effect!
     * <p>
     * The default supplier is {@link #DEFAULT_WAITER}.
     */
    @Setter
    private static Supplier<Waiter> waiter = DEFAULT_WAITER;

    private Wait() {
        // utility class constructor
    }

    /**
     * Creates a {@link ConfiguredWait configured fluent wait} with a custom timeout.
     * <p>
     * <b>Note:</b> see {@link WaitConfig} for defaults besides the timeout!
     *
     * @param timeout the timeout in seconds
     * @return the fluent wait instance
     * @see Wait
     * @see ConfiguredWait
     * @see WaitConfig
     * @since 1.2
     */
    public static ConfiguredWait withTimeoutOf(long timeout) {
        return new ConfiguredWait(waiter.get(), new WaitConfig().setTimeout(timeout));
    }

    /**
     * Creates a {@link ConfiguredWait configured fluent wait} with a custom timeout and time unit.
     * <p>
     * <b>Note:</b> see {@link WaitConfig} for defaults besides the timeout!
     *
     * @param timeout the timeout value
     * @param timeUnit the time unit the value is specified as
     * @return the fluent wait instance
     * @see Wait
     * @see ConfiguredWait
     * @see WaitConfig
     * @since 1.2
     */
    public static ConfiguredWait withTimeoutOf(long timeout, TimeUnit timeUnit) {
        return new ConfiguredWait(waiter.get(), new WaitConfig().setTimeout(timeout).setTimeUnit(timeUnit));
    }

    /**
     * Creates a {@link ConfiguredWait configured fluent wait} with a custom timeout and time unit.
     * <p>
     * <b>Note:</b> see {@link WaitConfig} for defaults besides the timeout!
     *
     * @param config the wait configuration to use
     * @return the fluent wait instance
     * @see Wait
     * @see ConfiguredWait
     * @see WaitConfig
     * @since 1.2
     */
    public static ConfiguredWait withTimeoutOf(WaitConfig config) {
        return new ConfiguredWait(waiter.get(), config);
    }

    /**
     * Creates a {@link WaitUntil} with the default timeout configuration of {@link WaitConfig} for the given object.
     *
     * @param object the object for the wait until operation
     * @param <T> the type of the object
     * @return the fluent wait instance
     * @see Wait
     * @see WaitUntil
     * @see WaitConfig
     * @since 1.2
     */
    public static <T> WaitUntil<T> until(T object) {
        return new WaitUntil<>(waiter.get(), new WaitConfig(), object);
    }

    /**
     * Creates a {@link WaitUntil} with the default timeout from the given {@link PageObject}'s configuration.
     *
     * @param pageObject the page object for the wait until operation
     * @param <T> the type of the page page object subclass
     * @return the fluent wait instance
     * @see Wait
     * @see WaitUntil
     * @see WaitConfig
     * @since 1.2
     */
    public static <T extends PageObject> WaitUntil<T> until(T pageObject) {
        return new WaitUntil<>(waiter.get(), WaitConfig.from(pageObject), pageObject);
    }

    /**
     * Waits the given amount of time with the given unit.
     * <p>
     * <b>Example:</b>
     * <pre>
     * Wait.exactly(10, TimeUnit.SECONDS);
     * Wait.exactly(1, TimeUnit.MINUTE);
     * </pre>
     *
     * @param duration the amount of time to wait
     * @param timeUnit the time unit to us
     * @since 1.2
     */
    public static void exactly(long duration, TimeUnit timeUnit) {
        waiter.get().waitExactly(duration, timeUnit);
    }

}
