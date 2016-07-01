package info.novatec.testit.webtester.waiting;

/**
 * This {@link Clock} implementation uses {@link System#currentTimeMillis()} to keep time.
 *
 * @since 1.2
 */
public class SystemClock implements Clock {

    @Override
    public long millis() {
        return System.currentTimeMillis();
    }

}
