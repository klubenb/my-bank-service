package ru.skillfactory.mybankservice.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}