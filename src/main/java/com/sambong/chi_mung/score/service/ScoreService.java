package com.sambong.chi_mung.score.service;

import com.sambong.chi_mung.score.dto.ScoreDTO;
import com.sambong.chi_mung.score.entity.Score;
import com.sambong.chi_mung.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<ScoreDTO> getTop10ScoresByCategory(String category) {
        List<Score> top10Scores = scoreRepository.findTop10ByCategoryIgnoreCaseOrderByScoreDesc(category);
        return top10Scores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ScoreDTO> getTop50ScoresByCategory(String category) {
        List<Score> top50Scores = scoreRepository.findTop50ByCategoryIgnoreCaseOrderByScoreDesc(category);
        return top50Scores.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public ScoreDTO saveScore(ScoreDTO scoreDTO) {
        Score score = convertToEntity(scoreDTO);
        Score savedScore = scoreRepository.save(score);
        return convertToDto(savedScore);
    }

    private ScoreDTO convertToDto(Score score) {
        return ScoreDTO.builder()
                .username(score.getUsername())
                .score(score.getScore())
                .build();
    }

    private Score convertToEntity(ScoreDTO scoreDTO) {
        return Score.builder()
                .username(scoreDTO.getUsername())
                .score(scoreDTO.getScore())
                .category(scoreDTO.getCategory())
                .build();
    }
}
