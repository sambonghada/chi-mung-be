package com.sambong.chi_mung.word.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sambong.chi_mung.word.entity.Word;
import com.sambong.chi_mung.word.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    private static final String API_URL = "https://www.jeju.go.kr/rest/JejuDialectService/getJejuDialectServiceList?pageSize=9999";

    public void updateWords() {
        List<Word> words = fetchWordData();
        if (words != null && !words.isEmpty()) {
            wordRepository.saveAll(words);
        }
    }

    private List<Word> fetchWordData() {
        try {
            // JSON 형식으로 요청을 보내기 위해 Accept 헤더를 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

            String jsonResponse = response.getBody();

            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // items 배열을 가져옵니다
            JsonNode itemsNode = rootNode.path("items");
            List<Word> words = new ArrayList<>();

            for (JsonNode itemNode : itemsNode) {
                String name = itemNode.path("name").asText();
                String contents = itemNode.path("contents").asText();
                String soundUrl = itemNode.path("soundUrl").asText();

                // &#로 시작하는 문자열이 포함된 데이터를 제외합니다.
                if (name.contains("&#") || contents.contains("&#")) {
                    System.out.println("Excluding data: " + name + ", " + contents);
                    continue; // 이 데이터를 제외하고 다음으로 넘어갑니다.
                }

                // Word 엔티티로 변환
                Word word = new Word();
                word.setWord(name);
                word.setMeaning(contents);
                word.setSoundurl(soundUrl);
                words.add(word);
            }

            return words;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Word> getRandomWordsByCount(int count) {
        // 데이터베이스에서 모든 단어를 가져옵니다.
        List<Word> allWords = wordRepository.findAll();

        // 5자 이하의 단어만 필터링합니다.
        List<Word> filteredWords = allWords.stream()
                .filter(word -> word.getWord().length() <= 5)
                .collect(Collectors.toList());

        // 리스트를 랜덤하게 섞습니다.
        Collections.shuffle(filteredWords, new Random());

        // 요청된 개수만큼 단어를 반환합니다.
        if (count > filteredWords.size()) {
            return filteredWords; // 요청한 개수가 실제 단어 수보다 크면 모든 필터링된 단어를 반환
        } else {
            return filteredWords.subList(0, count); // 요청한 개수만큼 잘라서 반환
        }
    }

}