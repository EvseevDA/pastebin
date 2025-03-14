package com.github.evseevda.pastebin.hashgenerator.advice;

import com.github.evseevda.pastebin.hashgenerator.common.dto.ErrorResponseBody;
import com.github.evseevda.pastebin.hashgenerator.exception.HashGeneratorServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CentralizedExceptionHandler {

    @ExceptionHandler(HashGeneratorServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseBody handleHashGeneratorServiceException(HashGeneratorServiceException e) {
        return unknownErrorResponseBody(e);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseBody handleAnyException(Throwable e) {
        return unknownErrorResponseBody(e);
    }

    private ErrorResponseBody unknownErrorResponseBody(Throwable e) {
        log.error(e.getMessage(), e);
        return ErrorResponseBody.withMessage("Произошла неизвестная ошибка. Повторите попытку позже.");
    }

}
