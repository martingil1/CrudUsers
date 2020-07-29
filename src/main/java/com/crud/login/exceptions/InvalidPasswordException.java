package com.crud.login.exceptions;

import static com.crud.login.utils.ExceptionErrorMessage.INVALID_PASSWORD;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super(INVALID_PASSWORD);
    }
}
