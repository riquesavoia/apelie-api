package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(Exception ex) throws IOException {
        return new ResponseEntity(new ExceptionResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(Exception ex) throws IOException {
        return new ResponseEntity(new ExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
