package com.ganesh.aichatbackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthResponse {

    private String token;

    private String email;

    private String fullName;

    private Long user_id;
}
