package com.sambong.chi_mung.word.repository;

import com.sambong.chi_mung.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
