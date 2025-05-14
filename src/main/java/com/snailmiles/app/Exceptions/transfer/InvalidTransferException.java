package com.snailmiles.app.Exceptions.transfer;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String message) {
        super(message);
    }
}
