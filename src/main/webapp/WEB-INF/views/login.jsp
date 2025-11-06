<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/resources/inc/css/login.css">
</head>
<body>
    <div class="login-container">
        <form id="loginForm" method="post" action="/login">
            <div class="login-form">
                <h1>로그인</h1>
                <div class="input-group">
                    <input type="text" name="usrId" id="usrId" placeholder="사용자 ID" required>
                </div>
                <div class="input-group">
                    <input type="password" name="pwdNo" id="pwdNo" placeholder="비밀번호" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="login-btn">로그인</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>