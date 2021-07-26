package com.somlabs.springbootsample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<Object> handleUserException(UserException userException, WebRequest webRequest){
        CustomExceptionResponse  customExceptionResponse = new CustomExceptionResponse(userException.getMessage());
        return new ResponseEntity(customExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
