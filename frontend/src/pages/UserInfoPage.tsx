import { useEffect, useState } from "react";
import api from "./api/axiosConfig";

type Props = {
  onLogout: () => void;
};

type UserInfo = {
  email: string;
  role: string;
};

function UserInfoPage({ onLogout }: Props) {
  const [user, setUser] = useState<UserInfo | null>(null);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) {
      alert("로그인이 필요합니다.");
      onLogout();
      return;
    }

    api.get("/api/users/my") // ✅ baseURL 생략, 토큰 자동 포함
        .then((res) => setUser(res.data))
        .catch((err) => {
          console.error("❌ 사용자 정보 조회 실패:", err);
          alert("로그인이 만료되었거나 잘못된 토큰입니다.");
          onLogout();
      });
  }, []);

  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        <h2 style={styles.title}>마이페이지</h2>
        {user ? (
          <>
            <p>이메일: {user.email}</p>
            <p>권한: {user.role}</p>
            <button onClick={onLogout} style={styles.logoutButton}>
              로그아웃
            </button>
          </>
        ) : (
          <p>불러오는 중...</p>
        )}
      </div>
    </div>
  );
}

const styles: { [key: string]: React.CSSProperties } = {
  wrapper: {
    display: "flex",
    justifyContent: "center",
    alignItems: "flex-start",
    paddingTop: "100px",
    backgroundColor: "#ffffff",
    height: "100vh",
    width: "100%",
  },
  card: {
    backgroundColor: "#ffffff",
    padding: "40px 30px",
    border: "1px solid #ddd",
    borderRadius: 8,
    width: "600px",
    boxSizing: "border-box",
    textAlign: "left",
  },
  title: {
    fontSize: 24,
    color: "#03c75a",
    fontWeight: "bold",
    marginBottom: 24,
  },
  logoutButton: {
    marginTop: 20,
    padding: "12px",
    backgroundColor: "#e53935",
    color: "#fff",
    border: "none",
    borderRadius: 4,
    fontWeight: "bold",
    cursor: "pointer",
    fontSize: 14,
  },
};

export default UserInfoPage;
