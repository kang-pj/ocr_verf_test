<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header">
    <div class="header-content">
        <div class="logo">
            <img src="/resources/images/common/logo.png" alt="Refine Logo">
        </div>
        <div class="user-info">
            <span>환영합니다, ${sessionScope.userInfo.usrNm}님</span>
            <a href="/logout">로그아웃</a>
        </div>
    </div>
</header>