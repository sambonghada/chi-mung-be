package com.sambong.chi_mung.score.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDTO {

    private String username;
    private int score;
    private String category;

}