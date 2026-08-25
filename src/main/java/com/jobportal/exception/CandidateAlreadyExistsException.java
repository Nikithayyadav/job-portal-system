package com.jobportal.exception;

public class CandidateAlreadyExistsException extends RuntimeException {

    public CandidateAlreadyExistsException(String message) {
        super(message);
    }
}