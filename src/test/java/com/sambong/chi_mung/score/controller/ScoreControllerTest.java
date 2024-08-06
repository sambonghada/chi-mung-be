package com.sambong.chi_mung.score.controller;

import com.sambong.chi_mung.score.dto.ScoreDTO;
import com.sambong.chi_mung.score.service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScoreControllerTest {

    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private ScoreService scoreService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scoreController).build();
    }

    @Test
    public void testGetTop10Scores_word() throws Exception {
        // Given
        when(scoreService.getTop10ScoresByCategory("word"))
                .thenReturn(Arrays.asList(
                        ScoreDTO.builder().username("user1").score(100).build(),
                        ScoreDTO.builder().username("user2").score(95).build()
                ));

        // When & Then
        mockMvc.perform(get("/api/scores/top10/word")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].score").value(100))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[1].score").value(95));
    }

    @Test
    public void testGetTop10Scores_sentence() throws Exception {
        // Given
        when(scoreService.getTop10ScoresByCategory("sentence"))
                .thenReturn(Arrays.asList(
                        ScoreDTO.builder().username("user1").score(120).build(),
                        ScoreDTO.builder().username("user3").score(110).build()
                ));

        // When & Then
        mockMvc.perform(get("/api/scores/top10/sentence")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].score").value(120))
                .andExpect(jsonPath("$[1].username").value("user3"))
                .andExpect(jsonPath("$[1].score").value(110));
    }
}
