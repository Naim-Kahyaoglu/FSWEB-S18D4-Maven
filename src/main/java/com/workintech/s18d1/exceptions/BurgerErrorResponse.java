package com.workintech.s18d1.exceptions;

public class BurgerErrorResponse {
    private String message;
    private int status;

    // Default constructor
    public BurgerErrorResponse() {
    }

    // Constructor with parameters
    public BurgerErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // New constructor
    public BurgerErrorResponse(String message) {
        this.message = message;
        this.status = 0; // Default value
    }

    // Getters and setters
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
