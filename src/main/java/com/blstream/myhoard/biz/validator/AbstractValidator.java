package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractValidator {

    protected final String MESSAGE_NOT_EMPTY = "may not be empty";
    protected final String MESSAGE_LENGTH_MIN_MAX = "length must be between %s and %s";

    protected Map<String, String> errorMessages = new HashMap<>();

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
