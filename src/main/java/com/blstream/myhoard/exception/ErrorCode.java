package com.blstream.myhoard.exception;

import org.codehaus.jackson.annotate.JsonProperty;

// TODO errors array
public class ErrorCode {

    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("error_message")
    private String errorMessage;

    public ErrorCode() {
    }

    public ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
