package info.novatec.testit.webtester.waiting;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;


/**
 * Implementations of this interface provide different kinds of wait operations.
 * The two basic types are 'wait exactly X amount of time' and 'wait until X passes a certain condition check'.
 *
 * @since 1.2
 */
public interface Waiter {

    /**
     * Waits the given amount of time using the {@link TimeUnit time unit}.
     * <p>
     * <b>Note:</b> This is only accurate to a millisecond level. Using any unit smaller than milliseconds will result in
     * the method doing nothing!
     *
     * @param duration the duration
     * @param timeUnit the time unit to use
     * @since 1.2
     */
    void waitExactly(long duration, TimeUnit timeUnit);

    /**
     * Waits until the given boolean {@link Supplier} returns <code>true</code> or the {@link WaitConfig configured} timeout
     * is reached.
     *
     * @param config the configuration to use
     * @param condition the condition to evaluate
     * @see WaitConfig
     * @since 1.2
     */
    void waitUntil(WaitConfig config, Supplier<Boolean> condition);

}
