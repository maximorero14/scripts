package com.maximorero.telegram_bot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maximorero.telegram_bot.dtos.ChatCompletionResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatGPTService {

    private static String openaiApiKey = "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";
    private static String organization = "org-YsDZXjbXPFmLhgvH4O3fL9Hv";

    private static String apiUrl = "https://api.openai.com/v1/chat/completions";
    ObjectMapper objectMapper = new ObjectMapper();

    public String executeQuery(String query) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        // Configure the content header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);
        headers.set("OpenAI-Organization",organization);

        // Configure the request body
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", query);

        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(userMessage);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

// Send the request
        ResponseEntity<ChatCompletionResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatCompletionResponse.class);



        String result = "";
        // Procesar la respuesta
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ChatCompletionResponse responseBody = responseEntity.getBody();
            System.out.println("RESULT: " + objectMapper.writeValueAsString(responseEntity.getBody()).toString());
            result = responseBody.getChoices()[0].getMessage().getContent();
            // Imprimir más campos según sea necesario
        } else {
            result = "Error en la solicitud: " + responseEntity.getStatusCode();
        }
        return result;
    }
}
