package com.sambong.chi_mung.nickname.service;

import com.sambong.chi_mung.nickname.dto.NicknameInfoDto;
import com.sambong.chi_mung.nickname.entity.Nickname;
import com.sambong.chi_mung.nickname.repository.NicknameRepository;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NicknameServiceImpl implements NicknameService{

    private static final Random random = new Random();
    private final NicknameRepository nicknameRepository;

    @Override
    public NicknameInfoDto getNicknameRandom() {
        long id = random.nextLong(251-79) + 79;
        Nickname nickname = nicknameRepository.findById(id).orElseThrow();
        return NicknameInfoDto.builder().nickname(nickname.getNickname()).meaning(nickname.getMeaning()).build();
    }
}
