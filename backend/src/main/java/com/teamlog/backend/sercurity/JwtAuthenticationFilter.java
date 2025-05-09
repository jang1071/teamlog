package com.teamlog.backend.sercurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// ✅ 사용자 인증 필터 (모든 요청에 대해 1번만 실행됨)
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // 📌 실제 요청이 들어올 때 실행되는 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1️⃣ 요청 헤더에서 Authorization 값 추출
        String authHeader = request.getHeader("Authorization");

        // 2️⃣ Authorization 헤더가 존재하고, "Bearer "로 시작할 경우
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            // 3️⃣ 토큰 부분만 추출 ("Bearer " 제거)
            String token = authHeader.substring(7);

            // 4️⃣ 토큰이 유효한지 검증
            if(jwtTokenProvider.validateToken(token)) {
                // 5️⃣ 토큰에서 사용자 이메일(subject) 추출
                String userEmail = jwtTokenProvider.getUsernameFromToken(token);

                // 6️⃣ 인증 객체 생성 (우리는 로그인 시 Role 없이 간단하게 처리)
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEmail, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));

                // 7️⃣ 인증 정보에 요청 세부정보 추가
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 8️⃣ Spring Security의 SecurityContext에 인증 정보 등록
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // ⛔ 토큰이 유효하지 않은 경우: 401 Unauthorized 응답 반환
                // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
                // return;
            }
        }

        // 9️⃣ 다음 필터로 요청 전달
        filterChain.doFilter(request, response);

    }
}
