package com.sambong.chi_mung.translate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class JejuTranslationService {

    private static final Logger logger = LoggerFactory.getLogger(JejuTranslationService.class);

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Value("${openai.api.model}")
    private String openaiModel;

    public String translateToJeju(String text) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 메시지를 생성하는 ArrayNode
        ArrayNode messagesNode = objectMapper.createArrayNode();
        ObjectNode messageObject = objectMapper.createObjectNode()
                .put("role", "user")
                .put("content", String.format("다음 문장을 제주도 사투리로 번역해 주세요: \"%s\".", text));
        messagesNode.add(messageObject);

        // 요청 본문을 구성하는 ObjectNode
        ObjectNode requestBody = objectMapper.createObjectNode()
                .put("model", openaiModel)
                .put("max_tokens", 100)
                .put("temperature", 0.2)
                .put("top_p", 0.5)
                .set("messages", messagesNode);  // ArrayNode를 ObjectNode에 추가

        // HTTP 클라이언트 생성 및 UTF-8 인코딩으로 StringEntity 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(openaiApiUrl);
        request.addHeader("Content-Type", "application/json; charset=UTF-8");
        request.addHeader("Authorization", "Bearer " + openaiApiKey);

        // StringEntity에 UTF-8 인코딩을 명시적으로 설정
        StringEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8));
        request.setEntity(entity);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            JsonNode responseBody = objectMapper.readTree(response.getEntity().getContent());

            // API 응답 구조를 로그로 출력
            logger.info("API Response: {}", responseBody.toPrettyString());

            // "choices" 키가 존재하고, 배열인지 확인
            if (responseBody.has("choices") && responseBody.get("choices").isArray()) {
                JsonNode choices = responseBody.get("choices");
                if (choices.size() > 0) {
                    JsonNode firstChoice = choices.get(0);
                    if (firstChoice.has("message")) {
                        return firstChoice.get("message").get("content").asText().trim();
                    }
                }
            }

            throw new IOException("Unexpected API response format: " + responseBody.toPrettyString());
        }
    }
}
