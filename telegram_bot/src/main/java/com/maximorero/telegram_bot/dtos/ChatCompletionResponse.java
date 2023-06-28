package com.maximorero.telegram_bot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatCompletionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private long created;

    @JsonProperty("choices")
    private Choice[] choices;

    @JsonProperty("usage")
    private Usage usage;

    // Getters y setters


    @Data
    public static class Choice {
        @JsonProperty("index")
        private int index;

        @JsonProperty("message")
        private Message message;

        @JsonProperty("finish_reason")
        private String finishReason;

        // Getters y setters
    }

    @Data
    public static class Message {
        @JsonProperty("role")
        private String role;

        @JsonProperty("content")
        private String content;

        // Getters y setters
    }

    @Data
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;

        // Getters y setters
    }
}