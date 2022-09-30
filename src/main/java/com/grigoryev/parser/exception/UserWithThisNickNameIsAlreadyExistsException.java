package com.grigoryev.parser.exception;

public class UserWithThisNickNameIsAlreadyExistsException extends RuntimeException {

    public UserWithThisNickNameIsAlreadyExistsException(String message) {
        super(message);
    }
}
