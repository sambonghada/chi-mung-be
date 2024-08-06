package com.sambong.chi_mung.tale.respository;

import com.sambong.chi_mung.tale.entity.Tale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaleRepository extends JpaRepository<Tale, Integer> {
}