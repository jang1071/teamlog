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

    /**
     * 회원 가입 요청 처리
     * @param user: JSON 형태의 사용자 정보 (email, password, role)
     * @return 등록된 사용자 정보
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println(">>> [UserController] 사용자 등록 요청: " + user.getEmail());

        User savedUser = userService.register(user);
        System.out.println(">>> [UserController] 저장 완료: id=" + savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }


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
