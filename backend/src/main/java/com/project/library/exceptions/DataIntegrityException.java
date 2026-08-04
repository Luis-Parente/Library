package com.project.library.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String msg) {
        super(msg);
    }
}
