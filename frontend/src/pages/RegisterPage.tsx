import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const RegisterPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (!email || !password || !confirmPassword) {
      setError('모든 필드를 입력해주세요.');
      return;
    }

    if (password !== confirmPassword) {
      setError('비밀번호가 일치하지 않습니다.');
      return;
    }

    try {
      await axios.post('/api/auth/register', {
        email,
        password,
      });

      alert('회원가입이 완료되었습니다!');
      navigate('/login');
    } catch (err: any) {
      setError(err.response?.data?.message || '회원가입 실패');
    }
  };

  return (
    <div className="flex justify-center items-start pt-[100px] bg-white min-h-screen w-full">
        <div className="bg-white border border-gray-200 p-10 w-[600px] rounded-lg">
        <h2 className="text-2xl font-bold text-left mb-6 text-[#03c75a]">회원가입</h2>

        <form onSubmit={handleRegister}>
          <div className="mb-4">
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded text-sm text-gray-800"
              placeholder="이메일"
              required
            />
          </div>

          <div className="mb-4">
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded text-sm text-gray-800"
              placeholder="비밀번호"
              required
            />
          </div>

          <div className="mb-4">
            <input
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded text-sm text-gray-800"
              placeholder="비밀번호 확인"
              required
            />
          </div>

          {error && (
            <div className="text-red-600 text-sm mb-2">{error}</div>
          )}

          <button
            type="submit"
            className="w-full bg-[#03c75a] text-white py-3 rounded font-bold text-base"
          >
            회원가입
          </button>

          <div className="text-sm text-center mt-6">
            이미 계정이 있으신가요?{' '}
            <button
              type="button"
              onClick={() => navigate('/login')}
              className="text-blue-600 hover:underline font-medium"
            >
              로그인
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
