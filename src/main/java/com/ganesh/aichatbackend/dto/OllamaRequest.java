package com.ganesh.aichatbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OllamaRequest {

    private String model;

    private String prompt;

    private boolean stream;
}