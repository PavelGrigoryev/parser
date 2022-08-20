package com.grigoryev.parser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ProductIncorrectData> noSuchProductException(NoSuchProductException exception) {
        ProductIncorrectData data = new ProductIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
