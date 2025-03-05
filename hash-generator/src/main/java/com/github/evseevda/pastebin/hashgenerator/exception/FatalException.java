package com.github.evseevda.pastebin.hashgenerator.exception;

public class FatalException extends HashGeneratorServiceException {

    public FatalException() {
        super();
    }

    public FatalException(String message) {
        super(message);
    }

    public FatalException(Throwable cause) {
        super(cause);
    }

    public FatalException(String message, Throwable cause) {
        super(message, cause);
    }

}
