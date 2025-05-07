package com.teamlog.backend.sercurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey;

    // ⏳ 토큰 유효 시간 (1시간)
    private final long EXPIRATION_MS = 1000 * 60 * 60;

    @PostConstruct
    public void init() {
        // ✅ Base64 디코딩 또는 UTF-8로 안전하게 키 생성
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    /**
     * 주어진 JWT 토큰이 유효한지 검사하는 메서드
     *
     * @param token 클라이언트로 부터 전달받은 JWT문자열
     * @return 유효한 토큰이면 true
     */
    public boolean validateToken(String token){
        try {
            // 📌 JWT 파서 생성 후 토큰 검증 (서명, 구조, 만료 여부 포함)
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // ⛔ 여기서 서명 키 일치 여부 체크
                    .build()
                    .parseClaimsJws(token);   // ⛔ 형식이나 만료된 토큰이면 예외 발생

            return true; // ✅ 검증 성공
        } catch (Exception e) {
          // ❌ 유효하지 않은 토큰: 만료, 위조, 파싱 오류 등
          return false;
        }
    }

    /**
     * 유효한 JWT 토큰에서 사용자 식별자(subject)를 추출하는 메서드
     *
     * @param token JWT 토큰 문자열
     * @return 토큰에 저장된 사용자 이메일 등 고유값(subject 필드)
     */
    public String getUsernameFromToke(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)    // 🔒 서명 키 설정
                .build()
                .parseClaimsJws(token)       // 📦 전체 토큰 파싱
                .getBody()                   // 🧠 Claims(본문) 꺼내기
                .getSubject();               // 📌 subject는 로그인 시 setSubject(email)로 넣은 값
    }
}
