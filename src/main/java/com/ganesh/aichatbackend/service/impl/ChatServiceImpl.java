package com.ganesh.aichatbackend.service.impl;

import com.ganesh.aichatbackend.dto.ChatListResponse;
import com.ganesh.aichatbackend.dto.ChatRequest;
import com.ganesh.aichatbackend.dto.ChatResponse;
import com.ganesh.aichatbackend.dto.MessageResponse;
import com.ganesh.aichatbackend.entity.Chat;
import com.ganesh.aichatbackend.entity.Message;
import com.ganesh.aichatbackend.entity.User;
import com.ganesh.aichatbackend.repository.ChatRepository;
import com.ganesh.aichatbackend.repository.MessageRepository;
import com.ganesh.aichatbackend.repository.UserRepository;
import com.ganesh.aichatbackend.service.ChatService;
import com.ganesh.aichatbackend.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl
        implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final OllamaService ollamaService;

    @Override
    public List<MessageResponse> getMessages(
            Long chatId) {

        return messageRepository
                .findByChat_IdOrderByCreatedAtAsc(
                        chatId)
                .stream()
                .map(message ->
                        MessageResponse.builder()
                                .id(message.getId())
                                .sender(message.getSender())
                                .content(message.getContent())
                                .createdAt(
                                        message.getCreatedAt()
                                )
                                .build()
                )
                .toList();
    }
    @Override
    public List<ChatListResponse> getUserChats(
            Long userId) {

        return chatRepository
                .findByUser_IdOrderByCreatedAtDesc(
                        userId)
                .stream()
                .map(chat ->
                        ChatListResponse.builder()
                                .id(chat.getId())
                                .title(chat.getTitle())
                                .createdAt(
                                        chat.getCreatedAt()
                                )
                                .build()
                )
                .toList();
    }

    @Override
    public ChatResponse sendMessage(
            Long userId,
            ChatRequest request) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Chat chat = Chat.builder()
                .title(request.getPrompt())
                .user(user)
                .build();

        chat = chatRepository.save(chat);

        Message userMessage = Message.builder()
                .chat(chat)
                .sender("USER")
                .content(request.getPrompt())
                .build();

        messageRepository.save(userMessage);

        String aiResponse =
                ollamaService.generateResponse(
                        request.getPrompt());

        Message aiMessage = Message.builder()
                .chat(chat)
                .sender("AI")
                .content(aiResponse)
                .build();

        messageRepository.save(aiMessage);

        return ChatResponse.builder()
                .chatId(chat.getId())
                .userMessage(request.getPrompt())
                .aiMessage(aiResponse)
                .build();
    }
}