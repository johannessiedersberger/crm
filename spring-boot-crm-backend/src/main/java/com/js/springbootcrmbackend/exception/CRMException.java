package com.js.springbootcrmbackend.exception;

public class CRMException extends RuntimeException {
    public CRMException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public CRMException(String exMessage) {
        super(exMessage);
    }
}
