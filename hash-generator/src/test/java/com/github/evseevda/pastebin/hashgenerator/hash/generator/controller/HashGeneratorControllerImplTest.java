package com.github.evseevda.pastebin.hashgenerator.hash.generator.controller;

import com.github.evseevda.pastebin.hashgenerator.hash.dto.HashResponse;
import com.github.evseevda.pastebin.hashgenerator.hash.generator.service.HashGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HashGeneratorControllerImplTest {

    @Mock
    private HashGeneratorService hashGeneratorService;

    @InjectMocks
    private HashGeneratorControllerImpl hashGeneratorController;


    @Test
    void givenHashGeneratorController_WhenGetNewHashIsCalled_ThenHashGeneratorServiceGenerateHashIsCalled() {
        // arrange
        when(hashGeneratorService.generateHash()).thenReturn("");

        // action
        hashGeneratorController.getNewHash();

        // assertion
        verify(hashGeneratorService).generateHash();
    }

    @Test
    void givenHashGeneratorController_WhenGetNewHashIsCalled_ThenStatus200AndHashSameAsReturnedByService() {
        // arrange
        String hash = "dih2dwdiw";
        when(hashGeneratorService.generateHash()).thenReturn(hash);

        // action
        ResponseEntity<HashResponse> newHash = hashGeneratorController.getNewHash();

        // assertion
        assertEquals(HttpStatus.OK, newHash.getStatusCode());
        assertEquals(hash, newHash.getBody().getHash());
    }

}