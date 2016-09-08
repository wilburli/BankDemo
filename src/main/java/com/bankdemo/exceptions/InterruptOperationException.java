package com.bankdemo.exceptions;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public class InterruptOperationException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Operation interrupted";

    public InterruptOperationException() {
        super(DEFAULT_MESSAGE);
    }

    public InterruptOperationException(String message) {
        super(message);
    }

    public InterruptOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptOperationException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public InterruptOperationException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
