package com.sambong.chi_mung.tale.service;

import com.sambong.chi_mung.tale.entity.Tale;
import com.sambong.chi_mung.tale.respository.TaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaleService {

    @Autowired
    private TaleRepository taleRepository;

    public List<Tale> getAllTales() {
        return taleRepository.findAll();
    }

    public Optional<Tale> getTaleById(int id) {
        return taleRepository.findById(id);
    }
}
