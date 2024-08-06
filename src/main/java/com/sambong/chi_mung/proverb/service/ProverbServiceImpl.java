package com.sambong.chi_mung.proverb.service;

import com.sambong.chi_mung.proverb.dto.ProverbDto;
import com.sambong.chi_mung.proverb.entity.Proverb;
import com.sambong.chi_mung.proverb.repository.ProverbRepository;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProverbServiceImpl implements ProverbService{

    private static final Random random = new Random();
    private final ProverbRepository proverbRepository;


    @Override
    public ProverbDto getProverb(Long id) {
        Proverb proverb = proverbRepository.findById(id).orElseThrow();
        return ProverbDto.builder().proverb(proverb).build();
    }

    @Override
    public List<ProverbDto> getAllProverb() {
        List<Proverb> proverbs = proverbRepository.findAll();
        return proverbs.stream().map(x -> ProverbDto.builder().proverb(x).build()).toList();
    }

    @Override
    public ProverbDto getProverbRandom() {
        long randomId = random.nextInt(1050) + 1;
        Proverb proverb = proverbRepository.findById(randomId).orElseThrow();
        return ProverbDto.builder().proverb(proverb).build();
    }
}
