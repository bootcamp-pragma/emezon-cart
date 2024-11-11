package com.emezon.cart.domain.utils;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private String message;
    private String requestedURI;
    private Integer code;
    private String details;
    private LocalDateTime timestamp;

    public ExceptionResponse(String message, String requestedURI, Integer code, String details) {
        this.message = message;
        this.requestedURI = requestedURI;
        this.code = code;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(String message, String requestedURI, Integer code) {
        this.message = message;
        this.requestedURI = requestedURI;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void setRequestedURI(String requestedURI) {
        this.requestedURI = requestedURI;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
