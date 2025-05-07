import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import UserInfoPage from './pages/UserInfoPage';
import './index.css';

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
  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsLoggedIn(false);
  };

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/login"
          element={<LoginPage onLoginSuccess={() => setIsLoggedIn(true)} />}
        />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/mypage" element={isLoggedIn ? (<UserInfoPage onLogout={handleLogout} />) : (<Navigate to="/login" replace />)}/>
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;