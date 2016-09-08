package com.basis.exceptions;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public class EmptyResultException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Empty result";

    public EmptyResultException() {
        super(DEFAULT_MESSAGE);
    }

    public EmptyResultException(String message) {
        super(message);
    }

    public EmptyResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public EmptyResultException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
