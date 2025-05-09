# 📝 TeamLog - 팀 협업 로그 웹 애플리케이션

**TeamLog**는 개발자들을 위한 팀 기반 협업 기록 시스템입니다.  
React와 Spring Boot를 활용하여 JWT 기반 인증 시스템을 구현하였으며,  
팀 생성, 게시글 작성, 마이페이지 등 실무에서 사용할 수 있는 기능을 제공합니다.

---

## 🎯 프로젝트 목적

TeamLog는 “실제 실무에서 바로 사용할 수 있는 개발자용 협업 로그 시스템”을 목표로 기획되었으며,  
인증/보안/사용자 역할 관리/REST API 설계 등 풀스택 개발 역량을 집중적으로 담고 있습니다.

---

## 📁 프로젝트 구조

```text
teamlog/
├── backend/     # Spring Boot API 서버
├── frontend/    # React 프론트엔드 
```

---

## 🚀 구현 기능 (진행 상태 포함)

- ✅ 회원가입 / 로그인 (JWT 기반)
- ✅ accessToken / refreshToken 분리 발급
- ✅ JWT 인증 필터 적용 (Spring Security)
- ✅ refreshToken 기반 accessToken 자동 재발급
- ✅ 로그인 후 마이페이지 (`/mypage`) 사용자 정보 표시
- ✅ Axios Interceptor를 통한 401 자동 처리
- 🟡 팀 생성 / 참여 기능 (`/teams`) [구현 예정]
- 🟡 팀 게시판 및 게시글/댓글 등록 (`/posts`) [예정]
- ✅ Swagger API 문서 자동화
- 🟡 사용자 권한(Role) 기반 접근 제한 (`@PreAuthorize`) [예정]
- 🟡 예외 처리 및 만료 토큰 대응 메시지 출력 [예정]

---

## 🖥️ 프로젝트 화면 구성

| 경로 | 설명 |
|------|------|
| `/` | 홈 화면 (서비스 소개, CTA 버튼) |
| `/register` | 회원가입 |
| `/login` | 로그인 |
| `/mypage` | 로그인된 사용자 정보 확인 |
| `/teams` | 팀 목록 / 팀 생성 (예정) |
| `/teams/:id` | 팀 상세 페이지 (예정) |
| `/posts/:id` | 게시글 상세 페이지 (예정) |


---

## 🧩 기술 스택

### 📌 Backend
- Java 17
- Spring Boot 3.x
- Spring Security + JWT (JJWT)
- Spring Data JPA + Hibernate
- MySQL
- Maven

### 📌 Frontend
- React + Vite
- TypeScript
- Tailwind CSS
- Axios
- React Router

---

## 💻 개발 환경 및 사용 도구

- IDE:  
  - **Frontend**: VSCode  
  - **Backend**: IntelliJ IDEA

- 기타 툴:  
  - Postman (API 테스트)  
  - Git + GitHub (버전 관리)  
  - DALL·E & ChatGPT (디자인 시안 도출)

---

## 🧪 실행 방법

### Backend 실행
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend 실행 (예정)
```bash
cd frontend
npm install
npm run dev
```

---

## 📌 향후 확장 예정 기능

- 팀별 채널 기능
- Markdown 기반 게시글 작성
- 실시간 알림 기능 (WebSocket)
- OAuth2 기반 소셜 로그인 (GitHub / Google)
- CI/CD 파이프라인 연동 (GitHub Actions)

---

## 📚 API 문서 (Swagger UI)

- 백엔드에서 자동 생성되는 Swagger UI를 통해 전체 API 문서를 확인할 수 있습니다.

- JWT 토큰 기반 인증이 필요한 요청도 Swagger UI의 Authorize 버튼으로 테스트 가능합니다.

---

## Swagger UI 경로
```bash
http://localhost:8080/swagger-ui.html
```
---

## Postman 자동화 테스트

- teamlog-jwt-test.postman_collection.json 컬렉션 제공
- 로그인, 인증, 토큰 재발급, 마이페이지 흐름까지 자동화된 테스트 지원

---

## 🔗 참고 링크

- [GitHub Projects 보드](https://github.com/jang1071/teamlog/projects)
- [Notion 기능 명세서](https://www.notion.so/1ea308a3731d808cb848dfadb85d0b36?v=1ea308a3731d80a0a3e6000cb54f02ea&p=1ea308a3731d80aeb639f6b4935e6862&pm=s)


