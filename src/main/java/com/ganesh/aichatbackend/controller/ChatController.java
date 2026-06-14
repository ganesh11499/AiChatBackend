package com.ganesh.aichatbackend.controller;

import com.ganesh.aichatbackend.dto.ChatListResponse;
import com.ganesh.aichatbackend.dto.ChatRequest;
import com.ganesh.aichatbackend.dto.ChatResponse;
import com.ganesh.aichatbackend.dto.MessageResponse;
import com.ganesh.aichatbackend.entity.Chat;
import com.ganesh.aichatbackend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<ChatResponse> sendMessage(
            @RequestBody ChatRequest request) {

        return ResponseEntity.ok(
                chatService.sendMessage(
                        request.getUserId(),
                        request
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatListResponse>>
    getUserChats(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                chatService.getUserChats(userId)
        );
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageResponse>>
    getMessages(
            @PathVariable Long chatId) {

        return ResponseEntity.ok(
                chatService.getMessages(chatId)
        );
    }
}