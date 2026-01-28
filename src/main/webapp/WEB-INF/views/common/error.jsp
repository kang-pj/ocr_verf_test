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
<body class="business_full">
<!-- class="business_wide" -->
<div id="wrap">
<div class="errorWrap">
<p class="txt">
<strong>404</strong>
<span>Page Not Found</span>
</p>
<p class="txt2">
<strong>원하시는 페이지를 찾을 수가 없습니다.</strong>
<span>찾으시려는 페이지의 주소가 잘못 입력되었거나,<br>
페이지 주소의 변경 혹은 삭제로 인해 현재 사용하실 수 없습니다.<br>
입력하신 페이지의 주소가 정확한지 다시 한번 확인 해 주시길 부탁 드립니다. </span>
</p>
<p class="dirMain">
<a href="${pageContext.request.contextPath}/getOcrInfo">메인페이지 바로가기</a>
</p>
</div>
</div>
</body>
</html>
