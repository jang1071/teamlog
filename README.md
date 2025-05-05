# TeamLog

Spring Boot + React 기반 웹 서비스 (포트폴리오 프로젝트)

---

## 📁 프로젝트 구조

```text
teamlog/
├── backend/     # Spring Boot API 서버
├── frontend/    # React 프론트엔드 (예정)
```

---

## 🔑 주요 기능

- 사용자 회원가입 / 로그인 (JWT 인증)
- 비밀번호 암호화 (BCrypt)
- 사용자 권한(Role) 기반 인증
- (예정) 글 작성 및 댓글 기능

---

## ⚙️ 사용 기술 스택

### ✅ Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- JPA (Hibernate)
- H2 / MySQL
- JWT (JJWT 라이브러리)

### 🎨 Frontend (예정)
- React
- Vite
- Axios
- Tailwind CSS

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

## 🗂️ 프로젝트 목적

이 프로젝트는 취업을 위한 포트폴리오용으로 개발되었으며,  
로그인 인증 흐름(JWT 기반)과 사용자 권한 설계를 중심으로 학습 목적을 포함합니다.

---

## 🔗 참고 링크

- [GitHub Projects 보드](https://github.com/jang1071/teamlog/projects)
- [Notion 기능 명세서](https://www.notion.so/1ea308a3731d808cb848dfadb85d0b36?v=1ea308a3731d80a0a3e6000cb54f02ea&p=1ea308a3731d80aeb639f6b4935e6862&pm=s)
