package com.github.evseevda.pastebin.hashgenerator.hashgenerator.controller;

import org.springframework.http.ResponseEntity;

public interface HashGeneratorController {

    ResponseEntity<String> getNewHash();

}
