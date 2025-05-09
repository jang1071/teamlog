package com.teamlog.backend.service;

import com.teamlog.backend.domain.User;
import com.teamlog.backend.dto.request.LoginRequest;
import com.teamlog.backend.dto.request.RefreshRequest;
import com.teamlog.backend.dto.request.RegisterRequest;
import com.teamlog.backend.dto.response.RefreshResponse;
import com.teamlog.backend.repository.UserRepository;
import com.teamlog.backend.dto.response.LoginResponse;
import com.teamlog.backend.dto.response.RegisterResponse;
import com.teamlog.backend.sercurity.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenService authTokenService;      // ✅ access/refresh 토큰 발급용
    private final JwtTokenProvider jwtTokenProvider;      // ✅ 토큰 검증/파싱용


    public User save(User user) {
        return userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException("사용자를 찾을 수 없습니다:" + email));
    }

    public User authenticate(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElseThrow( () -> new RuntimeException("Invalid email or password"));
    }

    /**
     * 회원가입 로직 (요청 DTO → 엔티티 저장 → 응답 DTO 반환)
     * @param request RegisterRequest: 이메일 + 비밀번호
     * @return RegisterResponse: 가입 완료된 사용자 ID, 이메일 반환
     */
    public RegisterResponse register(RegisterRequest request) {
        // 1. 이메일 중복 검사
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException("이미 등록된 이메일입니다: " + request.getEmail());
        });

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. User 엔티티 생성 및 저장
        User newUser = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role("USER")
                .build();

        userRepository.save(newUser);

        // JWT 토큰 생성
        String accessToken = authTokenService.issueAccessToken(newUser);
        String refreshToken = authTokenService.issueRefreshToken(newUser);

        // 4. 응답 반환
        // return new RegisterResponse(newUser.getId(), newUser.getEmail(), token);
        return RegisterResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 로그인 요청을 처리하고 JWT 토큰을 생성하여 반환합니다.
     * 이메일과 비밀번호를 검증한 후, 인증에 성공하면 JWT를 생성합니다.
     *
     * @param request 로그인 요청 정보 (이메일, 비밀번호)
     * @return 로그인 응답 DTO (JWT 토큰 포함)
     * @throws RuntimeException 인증 실패 시 예외 발생
     */
    public LoginResponse login(LoginRequest request) {
        // 1. 이메일 및 비밀번호로 사용자 인증
        User user = authenticate(request.getEmail(), request.getPassword());

        // 2. 인증된 사용자 정보를 기반으로 JWT 토큰 생성
        String accessToken  = authTokenService.issueAccessToken(user);
        String refreshToken = authTokenService.issueRefreshToken(user);

        // 3. 토큰을 포함한 응답 객체 반환
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role("USER")
                .build();
    }

    /**
     * 리프레시 토큰을 검증하고, 유효할 경우 새로운 액세스 토큰을 발급하는 서비스 메서드입니다.
     *
     * 📌 동작 순서:
     * 1. 전달받은 refreshToken이 유효한지 검증
     * 2. 토큰에서 사용자 이메일 추출
     * 3. 이메일 기반으로 실제 사용자 조회 (위조 토큰 방지)
     * 4. 새로운 accessToken 발급
     * 5. accessToken을 포함한 응답 객체 반환
     *
     * @param refreshToken 클라이언트가 전달한 refreshToken
     * @return accessToken이 포함된 RefreshResponse 객체
     * @throws RuntimeException refreshToken이 유효하지 않거나, 사용자 조회에 실패한 경우
     */
    public RefreshResponse refreshAccessToken(String refreshToken) {
        // [1] refreshToken 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다");
        }

        // [2] refreshToken에서 이메일(subject) 추출
        String email = jwtTokenProvider.getUsernameFromToken(refreshToken);

        // [3] 액티브 사용자인지 DB에서 재확인 필요
        User user = findByEmail(email);  // 내부 메서드, 예외 발생 가능

        // [4] 새 accessToken 발급
        String newAccessToken = authTokenService.issueAccessToken(user);

        // [5] 응답 객체 구성 후 반환
        return RefreshResponse.builder()
                .accessToken(newAccessToken)
                .build();
    }
}
