package com.bankdemo.exceptions;

/**
 * Created by Ilyas.Kuanyshbekov on 21.09.2016.
 */
public class CbrException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Cbr exception";

    public CbrException() {
        super(DEFAULT_MESSAGE);
    }

    public CbrException(String message) {
        super(message);
    }

    public CbrException(String message, Throwable cause) {
        super(message, cause);
    }

    public CbrException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public CbrException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
