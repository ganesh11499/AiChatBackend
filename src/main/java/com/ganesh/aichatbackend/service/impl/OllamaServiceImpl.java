package com.ganesh.aichatbackend.service.impl;
import com.ganesh.aichatbackend.dto.OllamaRequest;
import com.ganesh.aichatbackend.dto.OllamaResponse;
import org.springframework.beans.factory.annotation.Value;



import com.ganesh.aichatbackend.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OllamaServiceImpl
        implements OllamaService {

    private final RestTemplate restTemplate;

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String model;

    @Override
    public String generateResponse(String prompt) {

        OllamaRequest request =
                OllamaRequest.builder()
                        .model(model)
                        .prompt(prompt)
                        .stream(false)
                        .build();

        ResponseEntity<OllamaResponse> response =
                restTemplate.postForEntity(
                        ollamaUrl,
                        request,
                        OllamaResponse.class
                );

        return response.getBody().getResponse();
    }
}