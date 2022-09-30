package com.grigoryev.parser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    private final IncorrectProductData data = new IncorrectProductData();

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<IncorrectProductData> noSuchProductException(NoSuchProductException exception) {
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchDepartmentException.class)
    public ResponseEntity<IncorrectProductData> noSuchDepartmentException(NoSuchDepartmentException exception) {
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithThisNickNameIsAlreadyExistsException.class)
    public ResponseEntity<IncorrectProductData> userWithThisNickNameIsAlreadyExistsException(UserWithThisNickNameIsAlreadyExistsException exception) {
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.CONFLICT);
    }
}
