package com.ganesh.aichatbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatListResponse {

    private Long id;

    private String title;

    private LocalDateTime createdAt;
}