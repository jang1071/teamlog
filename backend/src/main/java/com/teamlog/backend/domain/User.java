package com.teamlog.backend.domain;

import jakarta.persistence.*;
import lombok.*;


/**
 * 사용자 정보를 저장하는 JPA Entity 클래스
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    // 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로그인 이메일
    private String email;

    // 비밀번호 (암호화 예정)
    private String password;

    // 사용자 권한 (예: USER, ADMIN)
    private String role;
}
