package info.novatec.testit.webtester.waiting;

import info.novatec.testit.webtester.api.exceptions.WebTesterException;


/**
 * This {@link WebTesterException} is thrown in case a wait operation timed out.
 *
 * @see Wait
 * @see WaitUntil
 * @since 1.2
 */
public class TimeoutException extends WebTesterException {

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

}
