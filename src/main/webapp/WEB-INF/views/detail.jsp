<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/inc/css/login.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/inc/css/jquery-ui-1.9.2.custom.min.css"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.png"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery.jqtransform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/refine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/jquery.dateFormat-1.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/inc/js/json2.js"></script>
    <script type="text/javascript">
        console.log("TEST");
        const portalUrl = 'https://portal.refinedev.io';

        $(document).ready(function () {
            $('#inputId').focus();

            $('#loginApply').on('click', function () {
                onLogin();
            });

            $('.form input').on('keyup', function (e) {
                if (e.key === 'Enter') {
                    onLogin();
                }
            });
        });

        // 로그인
        function onLogin() {
            if (!$.trim($('#inputId').val())) {
                alert('아이디를 입력해주세요.');
                $('#inputId').focus();
                return;
            }
            if (!$.trim($('#inputPw').val())) {
                alert('비밀번호를 입력해주세요.');
                $('#inputPw').focus();
                return;
            }

            $.ajax({
                data: $('.form input').serialize(),
                contentType: 'application/x-www-form-urlencoded',
                headers: {'X-Requested-With': 'XMLHttpRequest'},
                url: '${pageContext.request.contextPath}/login',
                type: 'POST',
                async: false,
                success: function (result) {
                    if (result != null && !result.empty) {
                        if (result.msg === 'LOGIN_OK') {
                            if (result.urlPriorLogin) {
                                location.href = '${pageContext.request.contextPath}/getOcrInfo';
                            } else {
                                location.href = '${pageContext.request.contextPath}/getOcrInfo';
                            }
                        } else if (result.msg === 'NOT_EXIST_SCRNAUTH') {
                            alert('사용자에 대한 메뉴 권한이 설정되어 있지않습니다.\r관리자에게 문의 바랍니다.');
                        } else if (result.msg === 'NOT_EXIST_MENUAUTH') {
                            alert('사용자에 대한 화면 권한이 설정되어 있지않습니다.\r관리자에게 문의 바랍니다.');
                        } else if (result.msg === 'NOT_EXIST_USR') {
                            if (result.pwMissCnt > 0 && result.pwMissCnt < 5) {
                                alert('로그인을 ' + result.pwMissCnt + '회 실패하였습니다. 5회 실패 시 계정이 잠기게 됩니다.\n비밀번호를 확인하시기 바랍니다.');
                            } else if (result.pwMissCnt === 5) {
                                alert('비밀번호 오류 횟수가 5회를 초과하여 계정이 잠겼습니다.\n비밀번호 초기화 메뉴를 이용하시기 바랍니다.');
                            } else {
                                alert('로그인을 실패하였습니다.\r로그인 정보를 확인하시기 바랍니다.');
                            }
                        } else if (result.msg === 'COGNITO_EMAIL_NOT_CONFIRM') {
                            if (confirm('최초 로그인은 포탈에서 진행하시기를 바랍니다.\n이동하시겠습니까?')) {
                                window.open(portalUrl, '_blank');
                            }
                        } else if (result.msg === 'EAPI_USER_NOT_FOUND') {
                            alert('Cognito 계정이 없습니다. IT개발운영팀으로 문의하시기 바랍니다.');
                        } else if (result.msg === 'LOGIN_OK_PWMODIFY') {
                            if (confirm('비밀번호 변경이 필요합니다. 포탈에서 다시 로그인하시길 바랍니다.\n이동하시겠습니까?')) {
                                window.open(portalUrl, '_blank');
                            }
                        } else {
                            alert('로그인을 실패하였습니다.\r비밀번호를 확인하시기 바랍니다.');
                        }
                    }
                }
            });
        }

        //비밀번호 초기화 팝업
        function popResetPw() {
            if (confirm('포탈로 이동 후 진행하시기를 바랍니다.\n이동하시겠습니까?')) {
                window.open(portalUrl, '_blank');
            }
        }
    </script>
</head>

<body>
<div id="login" class="wrap">
    <div class="centerTable">
        <div class="centerTableRow">
            <div class="centerTableCell">
                <div class="container">
                    <div class="login-header">
                        <h1 class="ci">
                            리파인
                        </h1>
                        <div class="title">
                            로그인
                        </div>
                        <div class="caption">
                            리파인 업무시스템에 오신 것을 환영합니다.
                        </div>
                    </div>
                    <div class="login-body">
                        <div class="form">
                            <div class="input id">
                                <input type="text" id="inputId" name="inputId" required placeholder="아이디 입력" autocomplete="off" >
                            </div>
                            <div class="input password">
                                <input type="password" id="inputPw" name="inputPw" required placeholder="비밀번호 입력" autocomplete="off" >
                            </div>
                        </div>
                        <div class="button">
                            <button id="loginApply">로그인</button>
                        </div>
                        <div class="button">
                            <p style="text-align: right; cursor: pointer; color: blue" onclick="popResetPw()">비밀번호 초기화</p>
                        </div>
                    </div>
                    <div class="login-footer">
                        <address>
                            <strong>REFINE</strong> admin system.
                        </address>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>