package com.sambong.chi_mung.tale.controller;

import com.sambong.chi_mung.tale.entity.Tale;
import com.sambong.chi_mung.tale.service.TaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tales")
public class TaleController {

    @Autowired
    private TaleService taleService;

    // 모든 설화 데이터를 반환하는 엔드포인트
    @GetMapping("/all")
    public List<Tale> getAllTales() {
        return taleService.getAllTales();
    }

    @GetMapping("/{id}")
    public Optional<Tale> getTaleById(@PathVariable int id) {
        return taleService.getTaleById(id);
    }
}
