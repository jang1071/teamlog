package com.teamlog.backend.controller;

import com.teamlog.backend.domain.User;
import com.teamlog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ 로그인한 사용자의 정보 조회 API
    @GetMapping("/my")
    public User getCurrent(Principal principal) {
        // 📌 SecurityContext에 설정된 인증 객체에서 이메일 꺼내기
        String email = principal.getName();

        // 📌 DB에서 사용자 조회
        return userService.findByEmail(email);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
