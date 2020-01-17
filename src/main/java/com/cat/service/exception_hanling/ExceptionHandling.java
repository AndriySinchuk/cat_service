package com.cat.service.exception_hanling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ThereIsNoSuchEntityException.class)
    public void handleThereIsNoSuchEntityException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(),"There is no such entity");
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public void handleDuplicateEntityException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value(),"Entity already exist");
    }

    @ExceptionHandler(InfoMissingInDB.class)
    public void handleInfoMissingInDBException(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value(),"Requested info not found in data base");
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    public void handleDuplicatePhoneNumnerException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(),"User with given phone number already exist");
    }
}
