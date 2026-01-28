<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>리파인 업무시스템</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/inc/css/fax_lease.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/inc/css/jquery-ui-1.9.2.custom.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery.jqtransform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/lease.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/common.js"></script>
</head>
<body class="business_full"><!-- class="business_wide" -->
<div id="wrap">
    <div class="errorWrap">
        <p class="txt"><strong>403</strong><span>Forbidden</span></p>
        <p class="txt2">
            <strong>요청에 문제가 발생하였습니다.</strong>
            <span>입력하신 페이지의 주소가 정확한지 다시 한번 확인 해 주시길 부탁 드립니다. </span>
        </p>
        <p class="dirMain"><a href="${pageContext.request.contextPath}/getOcrInfo">메인페이지 바로가기</a></p>
    </div>
</div>
</body>
</html>
