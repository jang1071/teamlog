package com.teamlog.backend.dto.request;


import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 요청 정보를 담는 DTO
 */
@Getter
@Setter
public class RegisterRequest {
    private String email;       // 사용자 이메일
    private String password;    // 사용자 비밀번호 (서버에서 암호화 처리 예정)
}
