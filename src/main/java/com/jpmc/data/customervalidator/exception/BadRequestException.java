package com.jpmc.data.customervalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No Data Found")
public class BadRequestException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -6885009761100356912L;
    private String errorCode;
    private String errorMessage;
    private String rawMessage;

    public BadRequestException(String errorCode, String errorMessage, String rawMessage) {
        super(errorCode + " " + errorMessage + " " + rawMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.rawMessage = rawMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    @Override
    public String toString() {
        return "DataNotFound{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", rawMessage='" + rawMessage + '\'' +
                '}';
    }
}
