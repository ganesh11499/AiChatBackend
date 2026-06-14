package com.ganesh.aichatbackend.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private Long userId;

    private Long chatId;

    private String prompt;
}