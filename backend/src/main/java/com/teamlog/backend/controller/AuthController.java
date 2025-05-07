package com.teamlog.backend.controller;

import com.teamlog.backend.domain.User;
import com.teamlog.backend.dto.LoginRequest;
import com.teamlog.backend.response.LoginResponse;
import com.teamlog.backend.sercurity.JwtTokenProvider;
import com.teamlog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // TODO: 사용자 인증 및 비밀번호 확인은 추후 추가
        User user = userService.authenticate(request.getEmail(), request.getPassword());
        String token = jwtTokenProvider.generateToken(request.getEmail());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
