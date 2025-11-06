<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Popup</title>
</head>
<body>
    <div class="error-popup">
        <h3>오류</h3>
        <p>${errorMessage}</p>
        <button onclick="window.close()">닫기</button>
    </div>
</body>
</html>