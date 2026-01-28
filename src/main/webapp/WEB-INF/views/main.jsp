<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 - OCR 검증 시스템</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }
        
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 1rem 2rem;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1200px;
            margin: 0 auto;
        }
        
        .logo {
            font-size: 1.5rem;
            font-weight: bold;
        }
        
        .user-info {
            display: flex;
            align-items: center;
            gap: 1rem;
        }
        
        .user-name {
            font-weight: 500;
        }
        
        .logout-btn {
            background: rgba(255,255,255,0.2);
            color: white;
            border: 1px solid rgba(255,255,255,0.3);
            padding: 0.5rem 1rem;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .logout-btn:hover {
            background: rgba(255,255,255,0.3);
        }
        
        .container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 2rem;
        }
        
        .welcome-card {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            margin-bottom: 2rem;
        }
        
        .welcome-title {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 1rem;
        }
        
        .user-details {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1rem;
            margin-top: 1rem;
        }
        
        .detail-item {
            background: #f8f9fa;
            padding: 1rem;
            border-radius: 5px;
        }
        
        .detail-label {
            font-weight: 500;
            color: #666;
            font-size: 0.9rem;
        }
        
        .detail-value {
            color: #333;
            font-size: 1rem;
            margin-top: 0.25rem;
        }
        
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
        }
        
        .menu-card {
            background: white;
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            cursor: pointer;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        
        .menu-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }
        
        .menu-icon {
            width: 50px;
            height: 50px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1.5rem;
            margin-bottom: 1rem;
        }
        
        .menu-title {
            font-size: 1.2rem;
            font-weight: 600;
            color: #333;
            margin-bottom: 0.5rem;
        }
        
        .menu-description {
            color: #666;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="header-content">
            <div class="logo">OCR 검증 시스템</div>
            <div class="user-info">
                <span class="user-name">${usrNm}님</span>
                <button class="logout-btn" onclick="logout()">로그아웃</button>
            </div>
        </div>
    </header>
    
    <div class="container">
        <div class="welcome-card">
            <h2 class="welcome-title">환영합니다, ${usrNm}님!</h2>
            <p>OCR 검증 시스템에 로그인하셨습니다.</p>
            
            <div class="user-details">
                <div class="detail-item">
                    <div class="detail-label">사용자 ID</div>
                    <div class="detail-value">${usrId}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">부서 코드</div>
                    <div class="detail-value">${dptCd}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">직무 코드</div>
                    <div class="detail-value">${dtyCd}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">내선번호</div>
                    <div class="detail-value">${inPhNo}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">DB 모드</div>
                    <div class="detail-value">${dbMode}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">포트</div>
                    <div class="detail-value">${localPort}</div>
                </div>
            </div>
        </div>
        
        <div class="menu-grid">
            <div class="menu-card" onclick="goToMenu('ocr')">
                <div class="menu-icon">📄</div>
                <div class="menu-title">OCR 처리</div>
                <div class="menu-description">문서 이미지에서 텍스트를 추출합니다</div>
            </div>
            
            <div class="menu-card" onclick="goToMenu('verify')">
                <div class="menu-icon">✓</div>
                <div class="menu-title">검증 관리</div>
                <div class="menu-description">추출된 텍스트를 검증하고 관리합니다</div>
            </div>
            
            <div class="menu-card" onclick="goToMenu('history')">
                <div class="menu-icon">📊</div>
                <div class="menu-title">처리 이력</div>
                <div class="menu-description">OCR 처리 및 검증 이력을 조회합니다</div>
            </div>
            
            <div class="menu-card" onclick="goToMenu('settings')">
                <div class="menu-icon">⚙️</div>
                <div class="menu-title">시스템 설정</div>
                <div class="menu-description">시스템 환경을 설정합니다</div>
            </div>
        </div>
    </div>
    
    <script>
        function logout() {
            if (confirm('로그아웃 하시겠습니까?')) {
                window.location.href = '${pageContext.request.contextPath}/logout';
            }
        }
        
        function goToMenu(menu) {
            alert(menu + ' 메뉴로 이동합니다. (구현 예정)');
            // 실제 구현시에는 해당 메뉴로 이동
            // window.location.href = '${pageContext.request.contextPath}/' + menu;
        }
    </script>
</body>
</html>