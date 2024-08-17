package com.sambong.chi_mung.score.controller;

import com.sambong.chi_mung.score.dto.ScoreDTO;
import com.sambong.chi_mung.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/top10/{category}")
    public List<ScoreDTO> getTop10Scores(@PathVariable String category) {
        return scoreService.getTop10ScoresByCategory(category);
    }

    @GetMapping("/top50/{category}")
    public List<ScoreDTO> getTop50Scores(@PathVariable String category) {
        return scoreService.getTop50ScoresByCategory(category);
    }

    @PostMapping
    public ScoreDTO saveScore(@RequestBody ScoreDTO scoreDTO) {
        return scoreService.saveScore(scoreDTO);
    }
}
