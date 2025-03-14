package com.github.evseevda.pastebin.hashgenerator.hash.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HashResponse {

    private String hash;

    public static HashResponse of(String hash) {
        return new HashResponse(hash);
    }

}
