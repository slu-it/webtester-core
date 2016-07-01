package info.novatec.testit.webtester.waiting;

/**
 * Implementations of this interface are used to keep time.
 *
 * @since 1.2
 */
public interface Clock {

    /**
     * Returns the clocks current time in milliseconds since 1.1.1970.
     *
     * @return the current time in millis
     * @since 1.2
     */
    long millis();

}
