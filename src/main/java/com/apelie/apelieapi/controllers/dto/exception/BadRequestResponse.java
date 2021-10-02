package com.apelie.apelieapi.controllers.dto.exception;

import java.util.Arrays;
import java.util.List;

public class BadRequestResponse extends GeneralExceptionResponse {
    private List<String> errors;

    public BadRequestResponse(int status, String message, List<String> errors) {
        super(status, message);
        this.errors = errors;
    }

    public BadRequestResponse(int status, String message, String error) {
        super(status, message);
        this.errors = Arrays.asList(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
