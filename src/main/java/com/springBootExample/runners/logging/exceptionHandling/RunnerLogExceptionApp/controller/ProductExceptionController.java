package com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp.controller;

import com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp.Exception.ProductNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
        return new ResponseEntity<>("Product not found - ProductNotfoundException", HttpStatus.NOT_FOUND);
    }
}
