package com.ganesh.aichatbackend.repository;

import com.ganesh.aichatbackend.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository
        extends JpaRepository<Chat,Long> {

    List<Chat> findByUser_IdOrderByCreatedAtDesc(
            Long userId);

};