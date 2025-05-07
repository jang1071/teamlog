import { useEffect, useState } from 'react';
import axios from 'axios';

type Props = {
  onLogout: () => void; // 🔥 로그아웃 props
};

const UserInfoPage = ({ onLogout }: Props) => {
  const [user, setUser] = useState<{ id: number; email: string; role: string } | null>(null);
  const [error, setError] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      setError('로그인이 필요합니다.');
      return;
    }

    axios
      .get('http://localhost:8080/api/users/my', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {
        setError('사용자 정보를 불러오지 못했습니다.');
        console.error(err);
      });
  }, []);

  if (error) return <div>{error}</div>;
  if (!user) return <div>사용자 정보를 불러오는 중...</div>;

  return (
    <div style={{ padding: 40 }}>
      <h2>내 정보</h2>
      <p>ID: {user.id}</p>
      <p>Email: {user.email}</p>
      <p>Role: {user.role}</p>
      {/* 🔥 로그아웃 버튼 */}
      <button onClick={onLogout} style={{ marginTop: 20 }}>
        로그아웃
      </button>
    </div>
  );
};

export default UserInfoPage;
