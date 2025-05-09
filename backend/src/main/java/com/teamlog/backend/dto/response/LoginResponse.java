package com.teamlog.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 성공 시 클라이언트에 전달되는 응답 DTO
 * JWT 토큰 + 사용자 정보 포함
 */
@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;    // JWT 액세스 토큰
    private String refreshToken;   // JWT 리프레시 토큰
    private String email;          // 사용자 이메일
    private String role;           // 사용자 권한
}
