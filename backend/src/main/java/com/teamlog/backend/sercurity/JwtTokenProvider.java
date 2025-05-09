package com.teamlog.backend.sercurity;

import com.teamlog.backend.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 토큰 생성 및 검증을 담당하는 유틸 클래스
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey;

    // ⏳ 액세스 토큰 유효 시간 (15분)
//    private final long ACCESS_TOKEN_VALIDITY = 1000L * 60 * 15;
    private static final long ACCESS_TOKEN_VALIDITY = 60 * 1000;      // 1분

    // ⏳ 리프레시 토큰 유효 시간 (7일)
//    private final long REFRESH_TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 7;
    private static final long REFRESH_TOKEN_VALIDITY = 10 * 60 * 1000; // 10분


    @PostConstruct
    public void init() {
        // ✅ UTF-8 인코딩 기반 서명 키 초기화
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    }
    /**
     * 액세스 토큰 생성 (15분 유효)
     * @param user 사용자 객체
     * @return JWT 액세스 토큰 문자열
     */
    public String generateAccessToken(User user){
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 리프레시 토큰 생성 (7일 유효)
     * @param user 사용자 객체
     * @return JWT 리프레시 토큰 문자열
     */
    public String generateRefreshToken(User user){
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(now + 1000)) // 🔥 1초라도 지연된 시간 주기
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 주어진 JWT 토큰이 유효한지 검사하는 메서드
     *
     * @param token 클라이언트로 부터 전달받은 JWT문자열
     * @return 유효한 토큰이면 true, 아니면 false
     */
    public boolean validateToken(String token){
        try {
            // 📌 JWT 파서 생성 후 토큰 검증 (서명, 구조, 만료 여부 포함)
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // ⛔ 여기서 서명 키 일치 여부 체크
                    .build()
                    .parseClaimsJws(token);   // ⛔ 형식이나 만료된 토큰이면 예외 발생
            return true;
        } catch (Exception e) {
          return false;
        }
    }

    /**
     * JWT 토큰에서 사용자 식별자(subject) 추출
     *
     * @param token JWT 문자열
     * @return 이메일 등 subject 필드 값
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)    // 🔒 서명 키 설정
                .build()
                .parseClaimsJws(token)       // 📦 전체 토큰 파싱
                .getBody()                   // 🧠 Claims(본문) 꺼내기
                .getSubject();               // 📌 subject는 로그인 시 setSubject(email)로 넣은 값
    }
}
