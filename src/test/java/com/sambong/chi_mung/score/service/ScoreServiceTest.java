package com.sambong.chi_mung.score.service;

import com.sambong.chi_mung.score.dto.ScoreDTO;
import com.sambong.chi_mung.score.repository.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import score.entity.Score;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ScoreRepository scoreRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTop10ScoresByCategory_word() {
        // Given
        List<Score> scores = Arrays.asList(
                Score.builder().username("user1").score(100).category("word").build(),
                Score.builder().username("user2").score(95).category("word").build()
        );

        when(scoreRepository.findTop10ByCategoryOrderByScoreDesc("word")).thenReturn(scores);

        // When
        List<ScoreDTO> result = scoreService.getTop10ScoresByCategory("word");

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals(100, result.get(0).getScore());
    }

    @Test
    public void testGetTop10ScoresByCategory_sentence() {
        // Given
        List<Score> scores = Arrays.asList(
                Score.builder().username("user1").score(120).category("sentence").build(),
                Score.builder().username("user3").score(110).category("sentence").build()
        );

        when(scoreRepository.findTop10ByCategoryOrderByScoreDesc("sentence")).thenReturn(scores);

        // When
        List<ScoreDTO> result = scoreService.getTop10ScoresByCategory("sentence");

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals(120, result.get(0).getScore());
    }
}
