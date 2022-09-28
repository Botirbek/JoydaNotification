package com.example.joydanotification.v1.exceptions;

public class SqlException extends RuntimeException{

    public SqlException(String message) {
        super(message);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
