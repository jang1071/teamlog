package com.teamlog.backend.controller;

import com.teamlog.backend.domain.User;
import com.teamlog.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println(">>> [UserController] 사용자 등록 요청: " + user.getEmail());

        User savedUser = userService.save(user);

        System.out.println(">>> [UserController] 저장 완료: id=" + savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
