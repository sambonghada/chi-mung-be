package com.sambong.chi_mung.proverb.service;

import com.sambong.chi_mung.proverb.dto.ProverbDto;
import java.util.List;

public interface ProverbService {

    ProverbDto getProverb(Long id);
    List<ProverbDto> getAllProverb();
    ProverbDto getProverbRandom();

}
