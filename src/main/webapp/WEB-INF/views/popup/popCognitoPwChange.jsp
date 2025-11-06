<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cognito 비밀번호 변경</title>
</head>
<body>
    <div class="popup-container">
        <h2>비밀번호 변경</h2>
        <form id="pwChangeForm">
            <div class="form-group">
                <label>현재 비밀번호:</label>
                <input type="password" name="currentPassword" required>
            </div>
            <div class="form-group">
                <label>새 비밀번호:</label>
                <input type="password" name="newPassword" required>
            </div>
            <div class="form-group">
                <label>비밀번호 확인:</label>
                <input type="password" name="confirmPassword" required>
            </div>
            <div class="button-group">
                <button type="submit">변경</button>
                <button type="button" onclick="window.close()">취소</button>
            </div>
        </form>
    </div>
</body>
</html>