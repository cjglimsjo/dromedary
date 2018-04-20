package com.cjglimsjo.dromedary;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Exchange<T> {

    private T message;
    private boolean valid;
    private String errorMessage;
    private Exception exception;

    public Exchange(T message) {
        notNull(message, "message must not be null");

        this.message = message;
        this.valid = true;
    }

    public void invalidate(String errorMessage) {
        invalidate(errorMessage, null);
    }

    public void invalidate(Exception exception) {
        invalidate(exception.getMessage(), exception);
    }

    public void invalidate(String errorMessage, Exception exception) {
        isTrue(this.valid, "exchange must be valid");

        this.valid = false;
        this.errorMessage = errorMessage;
        this.exception = exception;
    }

    public T getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Exception getException() {
        return exception;
    }
}
