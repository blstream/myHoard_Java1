package com.blstream.myhoard.exception;

public class ResourceAlreadyExistException extends RuntimeException {

        public ResourceAlreadyExistException() {
        }

        public ResourceAlreadyExistException(String message) {
                super(message);
        }

}
