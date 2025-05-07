import './App.css';
import { useEffect, useState } from 'react';
import LoginPage from './pages/LoginPage';
import UserInfoPage from './pages/UserInfoPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 🔥 로그인 유지 상태 확인 (토큰 있는지)
  useEffect(() => {
    const token = localStorage.getItem('token');
    if(token) {
      setIsLoggedIn(true);
    }
  }, []);

  // 🔥 로그아웃 함수
  const handleLoout = () => {
    localStorage.removeItem('token');
    setIsLoggedIn(false);
  };

  return (
    <div className="App">
      {!isLoggedIn ? (
        <LoginPage onLoginSuccess={() => setIsLoggedIn(true)} />
      ) : (
        <UserInfoPage onLogout={handleLoout} />
      )}
    </div>
  );
}

export default App;