package com.jpmc.data.customervalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleDataNotFound(BadRequestException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error Code", ex.getErrorCode());
        body.put("Error Message", ex.getErrorMessage());
        body.put("Raw Message", ex.getRawMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
