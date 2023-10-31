package com.js.springbootcrmbackend.exception;

public class CrmException extends RuntimeException {
    public CrmException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public CrmException(String exMessage) {
        super(exMessage);
    }
}
