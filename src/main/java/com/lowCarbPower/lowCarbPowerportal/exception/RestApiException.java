package com.lowCarbPower.lowCarbPowerportal.exception;

public class RestApiException extends Exception {

    private String statusCode;

    public RestApiException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

}
