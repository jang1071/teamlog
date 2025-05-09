import './App.css';
import './index.css';

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import UserInfoPage from './pages/UserInfoPage';
import HomePage from './pages/HomePage'; 
import TeamsPage from './pages/TeamsPage'; 

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 🔥 로그인 유지 상태 확인 (accessToken 존재 여부)
  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');
    if(accessToken) {
      setIsLoggedIn(true);
    }
  }, []);

  // 🔥 로그아웃 함수
  const handleLogout = () => {
    localStorage.removeItem('accessToken'); 
    localStorage.removeItem('refreshToken');
    setIsLoggedIn(false);
  };

  return (
    <BrowserRouter>
    <Routes>
      {/* 🏠 홈 화면 */}
      <Route path="/" element={<HomePage />} /> 

      {/* 로그인 */}
      <Route
        path="/login"
        element={<LoginPage onLoginSuccess={() => setIsLoggedIn(true)} />}
      />

      {/* 회원가입 */}
      <Route path="/register" element={<RegisterPage />} />

      {/* 마이페이지 */}
      <Route
        path="/mypage"
        element={
          localStorage.getItem("accessToken")  ? (
            <UserInfoPage onLogout={handleLogout} />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      {/* 팀 기능 페이지 (예정) */}
      <Route
        path="/teams"
        element={
          localStorage.getItem("accessToken")  ? (
            <TeamsPage onLogout={handleLogout} />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  </BrowserRouter>
  );
}

export default App;