package com.sambong.chi_mung.proverb.dto;

import com.sambong.chi_mung.proverb.entity.Proverb;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProverbDto {
    private Long id;
    private String content;
    private String answer;
    private String helpText;
    private String soundUrl;

    @Builder
    public ProverbDto(Proverb proverb){
        this.id = proverb.getId();
        this.content = proverb.getContent();
        this.answer = proverb.getAnswer();
        this.helpText = proverb.getHelpText();
        this.soundUrl = proverb.getSoundUrl();
    }
}
