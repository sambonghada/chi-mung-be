package com.sambong.chi_mung.translate.controller;

import com.sambong.chi_mung.translate.service.JejuTranslationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class TranslateController {
    private static final Logger logger = LoggerFactory.getLogger(TranslateController.class);

    private final JejuTranslationService jejuTranslationService;

    @GetMapping("/translate")
    public ResponseEntity<String> translate(@RequestParam("text") String text) {
        try {
            String jejuTranslation = jejuTranslationService.translateToJeju(text);
            return ResponseEntity.ok(jejuTranslation);
        } catch (IOException e) {
            logger.error("I/O 에러 발생", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
