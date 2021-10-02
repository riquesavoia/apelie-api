package com.apelie.apelieapi.controllers.dto.exception;

public class GeneralExceptionResponse {
    private int status;
    private String message;

    public GeneralExceptionResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
