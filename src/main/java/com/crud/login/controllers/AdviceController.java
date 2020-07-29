package com.crud.login.controllers;

import com.crud.login.exceptions.EmailExistException;
import com.crud.login.models.responses.AttributesResponseError;
import com.crud.login.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AttributesResponseError handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        return AttributesResponseError.fromMethodArgumentNotValidException(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailExistException.class)
    public ErrorResponse handleCodeAlreadyRegistered(EmailExistException ex) {

        return ErrorResponse.fromRunTimeException(ex);
    }
}
