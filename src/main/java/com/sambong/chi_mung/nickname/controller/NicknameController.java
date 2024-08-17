package com.sambong.chi_mung.nickname.controller;

import com.sambong.chi_mung.nickname.dto.NicknameInfoDto;
import com.sambong.chi_mung.nickname.service.NicknameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nickname")
@RequiredArgsConstructor
public class NicknameController {

    private final NicknameService nicknameService;

    @GetMapping
    public ResponseEntity<NicknameInfoDto> getNickname(){
        return ResponseEntity.ok().body(nicknameService.getNicknameRandom());
    }

}
