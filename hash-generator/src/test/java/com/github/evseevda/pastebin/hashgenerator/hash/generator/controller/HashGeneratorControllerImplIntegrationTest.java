package com.github.evseevda.pastebin.hashgenerator.hash.generator.controller;

import com.github.evseevda.pastebin.hashgenerator.config.IntegrationTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(IntegrationTestConfig.class)
@Sql("/sql/hash-seed-sequence-test.sql")
@AutoConfigureMockMvc
@Transactional
class HashGeneratorControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenRequestToGetNewHash_WhenGettingHash_ThenStatusOkAndHashReturned() throws Exception {
        // arrange
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/hash");

        // action
        mockMvc.perform(requestBuilder)

                // assertion
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.hash").exists(),
                        jsonPath("$.hash").value(matchesPattern(".*"))
                );
    }

}