import { useState } from 'react';
import api from "./api/axiosConfig";
import { useNavigate } from 'react-router-dom';

// 🔹 props 타입 정의
type LoginPageProps = {
  onLoginSuccess: () => void;
};

const LoginPage = ({ onLoginSuccess }: LoginPageProps) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const navigate = useNavigate(); // ✅ navigate 함수 선언

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      // const response = await axios.post('http://localhost:8080/api/auth/login', {
      //   email,
      //   password,
      // });

      // ✅ axios → api 변경 (baseURL 자동 적용됨)
      const response = await api.post('/api/auth/login', {
        email,
        password,
      });


      const accessToken = response.data.accessToken;
      console.log('✅ 받은 JWT accessToken 토큰:', accessToken);

      const refreshToken = response.data.refreshToken;
      console.log('✅ 받은 JWT refreshToken 토큰:', refreshToken);

      // 🔥 토큰 저장
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);


      // 🔥 상위(App.tsx)에 로그인 성공 알림
      onLoginSuccess();
      navigate("/mypage");
    } catch (error) {
      console.error('❌ 로그인 실패:', error);
      alert('이메일 또는 비밀번호가 올바르지 않습니다.');
    }
  };

  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        <h2 style={styles.title}>Login</h2>
        <form onSubmit={handleSubmit}>
          <div style={styles.inputGroup}>
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              style={styles.input}
            />
          </div>
          <div style={styles.inputGroup}>
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              style={styles.input}
            />
          </div>
          <div style={styles.checkboxWrapper}>
            <input
              type="checkbox"
              id="rememberMe"
              checked={rememberMe}
              onChange={() => setRememberMe(!rememberMe)}
              style={styles.checkbox}
            />
            <label htmlFor="rememberMe" style={styles.checkboxLabel}>
              아이디 저장하기
            </label>
          </div>
          <button type="submit" style={styles.button}>Login</button>
        </form>
      </div>
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  wrapper: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'flex-start',
    paddingTop: '100px',
    backgroundColor: '#ffffff',
    height: '100vh',
    width: '100%',
  },
  card: {
    backgroundColor: '#ffffff',
    padding: '40px 30px',
    border: '1px solid #ddd',
    borderRadius: '8px',
    width: '600px',
    boxSizing: 'border-box',
  },
  title: {
    textAlign: 'left',
    marginBottom: 24,
    fontSize: 24,
    color: '#03c75a',
    fontWeight: 'bold',
  },
  inputGroup: {
    marginBottom: 16,
  },
  input: {
    width: '100%',
    padding: '14px',
    borderRadius: 4,
    border: '1px solid #ccc',
    backgroundColor: '#fff',
    fontSize: 14,
    color: '#333',
    boxSizing: 'border-box',
  },
  checkboxWrapper: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: 20,
  },
  checkbox: {
    marginRight: 8,
  },
  checkboxLabel: {
    fontSize: 14,
    color: '#555',
  },
  button: {
    width: '100%',
    padding: '14px',
    backgroundColor: '#03c75a',
    color: '#fff',
    border: 'none',
    borderRadius: 4,
    fontWeight: 'bold',
    cursor: 'pointer',
    fontSize: 16,
  },
};

export default LoginPage;
