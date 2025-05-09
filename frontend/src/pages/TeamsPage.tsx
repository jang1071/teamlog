import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

type Props = {
  onLogout: () => void;
};

function TeamsPage({ onLogout }: Props) {
  const navigate = useNavigate();

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) {
      alert("로그인이 필요합니다.");
      onLogout();
      navigate("/login"); // ✅ 비로그인 시 강제 이동
    }
  }, []);

  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        <h2 style={styles.title}>팀 목록</h2>
        <p>현재 등록된 팀이 없습니다.</p>
        <button onClick={onLogout} style={styles.logoutButton}>
          로그아웃
        </button>
      </div>
    </div>
  );
}

export default TeamsPage;

// ✅ UserInfoPage와 동일한 style 사용
const styles = {
  wrapper: {
    display: "flex",
    justifyContent: "center",
    marginTop: "50px",
  },
  card: {
    width: "600px",
    padding: "40px",
    border: "1px solid #ddd",
    borderRadius: "8px",
    backgroundColor: "#f9fff9",
  },
  title: {
    fontSize: "24px",
    fontWeight: "bold",
    marginBottom: "20px",
    color: "#2e7d32",
  },
  logoutButton: {
    marginTop: "20px",
    padding: "10px 20px",
    backgroundColor: "#2e7d32",
    color: "white",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
};

