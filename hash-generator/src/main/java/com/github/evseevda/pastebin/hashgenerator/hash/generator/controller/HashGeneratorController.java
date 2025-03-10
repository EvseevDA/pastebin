package com.github.evseevda.pastebin.hashgenerator.hash.generator.controller;

import org.springframework.http.ResponseEntity;

public interface HashGeneratorController {

    ResponseEntity<String> getNewHash();

}
