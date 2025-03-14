package com.github.evseevda.pastebin.hashgenerator.hash.generator.controller;

import com.github.evseevda.pastebin.hashgenerator.hash.dto.HashResponse;
import org.springframework.http.ResponseEntity;

public interface HashGeneratorController {

    ResponseEntity<HashResponse> getNewHash();

}
