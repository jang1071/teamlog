package com.teamlog.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 성공 시 클라이언트에 전달되는 응답 DTO
 */
@Getter
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;               // 사용자 ID
    private String email;          // 사용자 이메일
    private String accessToken;    // JWT 액세스 토큰
    private String refreshToken;   // JWT 리프레시 토큰
}
