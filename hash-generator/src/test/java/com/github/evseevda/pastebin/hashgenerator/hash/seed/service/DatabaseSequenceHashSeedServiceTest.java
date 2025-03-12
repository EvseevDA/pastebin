package com.github.evseevda.pastebin.hashgenerator.hash.seed.service;

import com.github.evseevda.pastebin.hashgenerator.util.db.DatabaseOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseSequenceHashSeedServiceTest {

    @Mock
    private DatabaseOperations databaseOperations;

    @InjectMocks
    private DatabaseSequenceHashSeedService hashSeedService;

    @Test
    void whenGetNextSeedsIsCalled_ThenDbOperationsGetNIntegersFromSequenceIsCalled() {
        // arrange
        when(databaseOperations.getNIntegersFromSequence(anyInt(), any()))
                .thenReturn(List.of());

        // action
        hashSeedService.getNextSeeds(3);

        // assertion
        verify(databaseOperations, times(1))
                .getNIntegersFromSequence(anyInt(), any());
    }

    @Test
    void whenDbOperationsGetNIntegersFromSequenceResultReturned_ThenHashSeedServiceGetNextSeedsResultIsSame() {
        // arrange
        List<Integer> expectedSeeds = List.of(1, 2, 3, 4);
        when(databaseOperations.getNIntegersFromSequence(anyInt(), any()))
                .thenReturn(expectedSeeds);

        // action
        List<Integer> actualSeeds = hashSeedService.getNextSeeds(4);

        // assertion
        assertEquals(expectedSeeds, actualSeeds);
    }

}