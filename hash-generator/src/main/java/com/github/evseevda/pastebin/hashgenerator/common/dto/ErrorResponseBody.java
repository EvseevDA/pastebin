package com.github.evseevda.pastebin.hashgenerator.common.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponseBody {

    private String message;
    private LocalDateTime timestamp;

    public static ErrorResponseBody withMessage(String message) {
        return new ErrorResponseBody(message, LocalDateTime.now(ZoneOffset.UTC));
    }

}
