package com.sambong.chi_mung.word.controller;

import com.sambong.chi_mung.word.entity.Word;
import com.sambong.chi_mung.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/word")
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/update")
    public String updateWords() {
        wordService.updateWords();
        return "Word data updated successfully";
    }

    @GetMapping("/random/{count}")
    public List<Word> getRandomWordsByCount(@PathVariable int count) {
        return wordService.getRandomWordsByCount(count);
    }
}
