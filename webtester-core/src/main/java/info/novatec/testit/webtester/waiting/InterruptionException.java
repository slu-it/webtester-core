package info.novatec.testit.webtester.waiting;

import info.novatec.testit.webtester.api.exceptions.WebTesterException;


/**
 * This {@link WebTesterException} is thrown in case a wait operation is interrupted.
 *
 * @see Wait
 * @see WaitUntil
 * @since 1.2
 */
public class InterruptionException extends WebTesterException {

    public InterruptionException(Throwable cause) {
        super(cause);
    }

}
