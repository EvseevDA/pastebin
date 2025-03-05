package com.github.evseevda.pastebin.hashgenerator.exception;

public class HashGeneratorServiceException extends RuntimeException {

    public HashGeneratorServiceException() {
        super();
    }

    public HashGeneratorServiceException(String message) {
        super(message);
    }

    public HashGeneratorServiceException(Throwable cause) {
        super(cause);
    }

    public HashGeneratorServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
