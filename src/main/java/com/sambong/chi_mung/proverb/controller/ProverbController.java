package com.sambong.chi_mung.proverb.controller;

import com.sambong.chi_mung.proverb.dto.ProverbDto;
import com.sambong.chi_mung.proverb.entity.Proverb;
import com.sambong.chi_mung.proverb.service.ProverbService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proverb")
@RequiredArgsConstructor
public class ProverbController {

    private final ProverbService proverbService;

    @GetMapping("/{id}")
    public ResponseEntity<ProverbDto> getProverb(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(proverbService.getProverb(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProverbDto>> getAllProverb(){
        return ResponseEntity.status(HttpStatus.OK).body(proverbService.getAllProverb());
    }

    @GetMapping("/random")
    public ResponseEntity<ProverbDto> getProverbRandom(){
        return ResponseEntity.status(HttpStatus.OK).body(proverbService.getProverbRandom());
    }

}
