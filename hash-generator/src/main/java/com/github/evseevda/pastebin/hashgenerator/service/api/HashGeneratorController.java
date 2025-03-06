package com.github.evseevda.pastebin.hashgenerator.service.api;

import org.springframework.http.ResponseEntity;

public interface HashGeneratorController {

    ResponseEntity<String> getNewHash();

}
