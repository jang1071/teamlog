// src/pages/HomePage.tsx
import { Link } from 'react-router-dom';

function HomePage() {
  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        {/* 타이틀 */}
        <h2 style={styles.title}>TeamLog</h2>
        <p style={styles.description}>
          개발자들을 위한 팀 협업 기록 시스템<br />
          팀을 만들고, 기록하고, 함께 성장하세요.
        </p>

        {/* 버튼 */}
        <div style={styles.buttonGroup}>
          <Link to="/register" style={{ width: '100%' }}>
            <button style={styles.greenButton}>무료로 시작하기</button>
          </Link>
          <Link to="/login" style={{ width: '100%' }}>
            <button style={styles.grayButton}>로그인</button>
          </Link>
        </div>

        {/* 기능 요약 텍스트 */}
        <div style={styles.subtext}>
          <p>✅ 팀 생성 및 참여</p>
          <p>✅ 피드 작성 및 공유</p>
          <p>✅ 실무 친화적인 협업 기록</p>
        </div>

        {/* 하이라이트 */}
        <div style={styles.preview}>
          <p style={styles.highlight}>
            👀 지금 바로 시작하세요! 설치 없이, 3분 만에 협업 시작
          </p>
        </div>
      </div>
    </div>
  );
}

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
    borderRadius: 8,
    width: '600px',
    boxSizing: 'border-box',
    textAlign: 'left',
  },
  title: {
    fontSize: 24,
    color: '#03c75a',
    fontWeight: 'bold',
    marginBottom: 16,
  },
  description: {
    fontSize: 14,
    color: '#555',
    marginBottom: 24,
    lineHeight: '1.6',
  },
  buttonGroup: {
    display: 'flex',
    gap: '10px',
    marginBottom: 20,
  },
  greenButton: {
    padding: '14px',
    backgroundColor: '#03c75a',
    color: '#fff',
    border: 'none',
    borderRadius: 4,
    fontWeight: 'bold',
    fontSize: 16,
    cursor: 'pointer',
    width: '100%',
  },
  grayButton: {
    padding: '14px',
    backgroundColor: '#eee',
    color: '#333',
    border: 'none',
    borderRadius: 4,
    fontWeight: 'bold',
    fontSize: 16,
    cursor: 'pointer',
    width: '100%',
  },
  subtext: {
    fontSize: 14,
    color: '#666',
    lineHeight: '1.5',
    marginBottom: 16,
  },
  highlight: {
    fontSize: 13,
    color: '#333',
    fontWeight: 'bold',
    marginBottom: 8,
  },
  preview: {
    marginTop: 12,
  },
  thumbnail: {
    width: '100%',
    border: '1px solid #ddd',
    padding: 8,
    borderRadius: 4,
    backgroundColor: '#f9f9f9',
  },
};

export default HomePage;
