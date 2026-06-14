package com.ganesh.aichatbackend.service;

import com.ganesh.aichatbackend.dto.ChatListResponse;
import com.ganesh.aichatbackend.dto.ChatRequest;
import com.ganesh.aichatbackend.dto.ChatResponse;
import com.ganesh.aichatbackend.dto.MessageResponse;
import com.ganesh.aichatbackend.entity.Chat;

import java.util.List;

public interface ChatService {

    ChatResponse sendMessage(
            Long userId,
            ChatRequest request);

    List<ChatListResponse> getUserChats(
            Long userId);
    
    List<MessageResponse> getMessages(
            Long chatId);

}