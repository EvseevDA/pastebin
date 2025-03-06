package com.github.evseevda.pastebin.hashgenerator.service.impl;

import com.github.evseevda.pastebin.hashgenerator.service.api.HashGeneratorController;
import com.github.evseevda.pastebin.hashgenerator.service.api.HashGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hash")
@RequiredArgsConstructor
public class HashGeneratorControllerImpl implements HashGeneratorController {

    private final HashGeneratorService hashGeneratorService;

    @Override
    @GetMapping
    public ResponseEntity<String> getNewHash() {
        return ResponseEntity.ok(
                hashGeneratorService.generateHash()
        );
    }

}
