package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.Map;

public class AbstractValidator {

    public final static String REQUEST_METHOD_POST = "post";
    public final static String REQUEST_METHOD_PUT = "put";
    public final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    protected Map<String, String> errorMessages = new HashMap<>();

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
