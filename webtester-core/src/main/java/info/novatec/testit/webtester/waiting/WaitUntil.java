package info.novatec.testit.webtester.waiting;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import lombok.AccessLevel;
import lombok.Getter;

import info.novatec.testit.webtester.utils.Conditions;


/**
 * This class offers a number of methods which allow for the waiting until a specific condition is met for any object type.
 *
 * @see Wait
 * @since 1.2
 */
@Getter(AccessLevel.PACKAGE)
public class WaitUntil<T> {

    private final Waiter waiter;
    private final WaitConfig config;
    private final T object;

    /**
     * Creates a new {@link WaitUntil} instance for the given object and {@link WaitConfig}.
     *
     * @param config the configuration to use
     * @param object the object to use
     * @see Wait
     * @since 1.2
     */
    WaitUntil(Waiter waiter, WaitConfig config, T object) {
        this.waiter = waiter;
        this.config = config;
        this.object = object;
    }

    /**
     * Waits until the given condition is met. A set of default conditions can be initialized from {@link Predicate}.
     *
     * @param condition the condition to wait for
     * @return the same instance for fluent API use
     * @throws TimeoutException in case the condition is not met within the configured timeout
     * @see Wait
     * @see Conditions
     * @since 1.2
     */
    public WaitUntil<T> is(Predicate<? super T> condition) throws TimeoutException {
        return doWait(condition);
    }

    /**
     * Waits until the given condition is met. A set of default conditions can be initialized from {@link Predicate}.
     *
     * @param condition the condition to wait for
     * @return the same instance for fluent API use
     * @throws TimeoutException in case the condition is not met within the configured timeout
     * @see Wait
     * @see Conditions
     * @since 1.2
     */
    public WaitUntil<T> has(Predicate<? super T> condition) throws TimeoutException {
        return doWait(condition);
    }

    /**
     * Waits until the given condition is NOT met. A set of default conditions can be initialized from {@link Predicate}.
     *
     * @param condition the condition to wait for
     * @return the same instance for fluent API use
     * @throws TimeoutException in case the condition is not met within the configured timeout
     * @see Wait
     * @see Conditions
     * @since 1.2
     */
    public WaitUntil<T> isNot(Predicate<? super T> condition) throws TimeoutException {
        return doWait(Conditions.not(condition));
    }

    /**
     * Waits until the given condition is NOT met. A set of default conditions can be initialized from {@link Predicate}.
     *
     * @param condition the condition to wait for
     * @return the same instance for fluent API use
     * @throws TimeoutException in case the condition is not met within the configured timeout
     * @see Wait
     * @see Conditions
     * @since 1.2
     */
    public WaitUntil<T> hasNot(Predicate<? super T> condition) throws TimeoutException {
        return doWait(Conditions.not(condition));
    }

    /**
     * Waits until the given condition is NOT met. A set of default conditions can be initialized from {@link Predicate}.
     *
     * @param condition the condition to wait for
     * @return the same instance for fluent API use
     * @throws TimeoutException in case the condition is not met within the configured timeout
     * @see Wait
     * @see Conditions
     * @since 1.2
     */
    public WaitUntil<T> not(Predicate<? super T> condition) throws TimeoutException {
        return doWait(Conditions.not(condition));
    }

    private WaitUntil<T> doWait(final Predicate<? super T> condition) {
        waiter.waitUntil(config, new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return condition.apply(object);
            }
        });
        return this;
    }

    /**
     * This method does nothing by it's own. It is intended to be used in order to write more expressive linked wait
     * statements.
     * <p>
     * <b>Example:</b> {@code Wait.until(button).is(visible()).and().not(editable());}
     *
     * @return the same instance for fluent API use
     * @see Wait
     * @since 1.2
     */
    public WaitUntil<T> and() {
        return this;
    }

    /**
     * This method does nothing by it's own. It is intended to be used in order to write more expressive linked wait
     * statements.
     * <p>
     * <b>Example:</b> {@code Wait.until(button).is(visible()).but().not(editable());}
     *
     * @return the same instance for fluent API use
     * @see Wait
     * @since 1.2
     */
    public WaitUntil<T> but() {
        return this;
    }

}
