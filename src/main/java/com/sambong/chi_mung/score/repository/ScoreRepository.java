package com.sambong.chi_mung.score.repository;

import com.sambong.chi_mung.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    // 카테고리별로 상위 10명의 점수를 반환하는 메서드
    List<Score> findTop10ByCategoryIgnoreCaseOrderByScoreDesc(String category);
    List<Score> findTop50ByCategoryIgnoreCaseOrderByScoreDesc(String category);

}