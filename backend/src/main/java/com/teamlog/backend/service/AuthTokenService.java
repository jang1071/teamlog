package com.teamlog.backend.service;

import com.teamlog.backend.domain.User;
import com.teamlog.backend.sercurity.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    /**
     * JWT 토큰을 생성하는 컴포넌트
     * 이메일 기반으로 access token을 생성하는 데 사용됨
     */
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 액세스 토큰 발급
     * @param user 대상 사용자
     * @return accessToken
     */
    public String issueAccessToken(User user) {
        return jwtTokenProvider.generateAccessToken(user);
    }

    /**
     * 리프레시 토큰 발급
     * @param user 대상 사용자
     * @return refreshToken
     */
    public String issueRefreshToken(User user) {
        return jwtTokenProvider.generateRefreshToken(user);
    }
}
