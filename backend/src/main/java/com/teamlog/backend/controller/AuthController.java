package com.teamlog.backend.controller;

import com.teamlog.backend.dto.request.LoginRequest;
import com.teamlog.backend.dto.request.RefreshRequest;
import com.teamlog.backend.dto.request.RegisterRequest;
import com.teamlog.backend.dto.response.LoginResponse;
import com.teamlog.backend.dto.response.RefreshResponse;
import com.teamlog.backend.dto.response.RegisterResponse;
import com.teamlog.backend.sercurity.JwtTokenProvider;
import com.teamlog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 관련 API 컨트롤러 (로그인 + 회원가입)
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    /**
     * 로그인 API
     * 이메일/비밀번호 인증 후 JWT 토큰 발급
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입 API
     * 이메일/비밀번호 기반 사용자 등록
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        RegisterResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 🔄 accessToken 재발급 API
     */
    @PostMapping("refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshResponse response = userService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

}
