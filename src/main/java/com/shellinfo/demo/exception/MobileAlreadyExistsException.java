package com.shellinfo.demo.exception;

public class MobileAlreadyExistsException extends RuntimeException {

    public MobileAlreadyExistsException(String message) {
        super(message);
    }
}

