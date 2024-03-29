package com.js.springbootcrmbackend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception ex, Locale locale) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());

    }
}
