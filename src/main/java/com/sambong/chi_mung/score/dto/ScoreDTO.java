package com.sambong.chi_mung.score.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDTO {

    private String username;
    private int score;
    private String category;

    public String getCategory() {
        return this.category;
    }
}