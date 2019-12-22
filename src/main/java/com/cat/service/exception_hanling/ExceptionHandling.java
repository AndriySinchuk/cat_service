package com.cat.service.exception_hanling;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ThereIsNoSuchEntityException.class)
    public ResponseEntity handleThereIsNoSuchCatException() {
        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }
}
