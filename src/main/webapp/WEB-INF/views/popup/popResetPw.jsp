<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
</head>
<body>
    <div class="popup-container">
        <h2>비밀번호 재설정</h2>
        <form id="resetPwForm">
            <div class="form-group">
                <label>사용자 ID:</label>
                <input type="text" name="usrId" required>
            </div>
            <div class="form-group">
                <label>이메일:</label>
                <input type="email" name="email" required>
            </div>
            <div class="button-group">
                <button type="submit">재설정 요청</button>
                <button type="button" onclick="window.close()">취소</button>
            </div>
        </form>
    </div>
</body>
</html>