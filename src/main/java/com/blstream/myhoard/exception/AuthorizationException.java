package com.blstream.myhoard.exception;

public class AuthorizationException extends RuntimeException {

    private int errorCode;

    public AuthorizationException() {
    }

    public AuthorizationException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
