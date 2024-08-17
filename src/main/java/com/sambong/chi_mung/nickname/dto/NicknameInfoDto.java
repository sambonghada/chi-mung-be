package com.sambong.chi_mung.nickname.dto;

import com.sambong.chi_mung.nickname.entity.Nickname;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NicknameInfoDto {

    private String nickname;
    private String meaning;

    public NicknameInfoDto(Nickname nickname) {
        this.nickname = nickname.getNickname();
        this.meaning = nickname.getMeaning();
    }
}
