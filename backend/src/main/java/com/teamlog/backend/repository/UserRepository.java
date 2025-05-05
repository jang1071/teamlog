package com.teamlog.backend.repository;

import com.teamlog.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// User 엔티티를 위한 JPA Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 조회 (로그인 시 사용 예정)
    Optional<User> findByEmail(String email);
}
