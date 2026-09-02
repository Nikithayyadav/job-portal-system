package com.jobportal.exception;

public class ApplicationNotAvailableException
        extends RuntimeException {

    public ApplicationNotAvailableException(
            String message) {

        super(message);
    }
}