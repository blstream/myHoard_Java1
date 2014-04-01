package com.blstream.myhoard.exception;

import java.util.Map;

public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> errors;

    public ValidatorException(Map<String, String> errors) {
        this.errors = errors;
    }

    public ValidatorException() {
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
