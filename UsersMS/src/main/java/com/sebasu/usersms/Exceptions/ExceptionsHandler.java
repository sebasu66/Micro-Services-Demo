package com.sebasu.usersms.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //will catch exceptions on every controller
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    //this annotation tells Spring to handle the exception described on value, in this case general Exception
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        ExWrapper exWrapper = ExWrapper.builder().exception(e).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        RestResponse restResponse= RestResponse.builder().message(exWrapper.betterMsg()).path(request.getContextPath()).build();
        return new ResponseEntity<>(restResponse ,new HttpHeaders(), exWrapper.getHTTPStatus());
        }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleCustomException(Exception e, WebRequest request) {
        ExWrapper exWrapper = ExWrapper.builder().exception(e).build();
        RestResponse restResponse= RestResponse.builder().message(exWrapper.betterMsg()).path(request.getContextPath()).build();
        return new ResponseEntity<>(restResponse ,new HttpHeaders(), exWrapper.getHTTPStatus());
    }

/*
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/
}
