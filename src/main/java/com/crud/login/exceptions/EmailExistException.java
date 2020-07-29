package com.crud.login.exceptions;


import static com.crud.login.utils.ExceptionErrorMessage.EMAIL_EXIST;

public class EmailExistException extends RuntimeException {

    public EmailExistException() {
        super(EMAIL_EXIST);
    }
}
