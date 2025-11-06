package com.refine.login.service;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import com.refine.login.vo.BaseResponse;
import com.refine.login.vo.LoginVO;

public interface LoginService {
    public static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public static final String NOT_EXIST_USR = "NOT_EXIST_USR"; //회원이 존재하지 않음

    public static final String NOT_EXIST_MENUAUTH = "NOT_EXIST_MENUAUTH"; //메뉴권한이 존재하지 않음

    public static final String NOT_EXIST_SCRNAUTH = "NOT_EXIST_SCRNAUTH"; //화면권한이 존재하지 않음

    public static final String LOGIN_OK = "LOGIN_OK"; //로그인 정상

    public static final String LOGIN_OK_PWMODIFY = "LOGIN_OK_PWMODIFY"; //로그인 정상이나 패스워드변경 필수

    public Map<String, Object> preAuthenticate(LoginVO loginVO) throws SQLException;

    public abstract JSONArray getMainData(Map<String, Object> modelMap) throws SQLException;

    public abstract void updateChgPw(Map<String, Object> modelMap) throws SQLException;

    public abstract LoginVO isMember(LoginVO loginVO) throws SQLException;

    public abstract LoginVO tokenCheck(LoginVO loginVO) throws SQLException;

    public abstract BaseResponse setPasswordReset(LoginVO loginVO) throws SQLException;

    public abstract BaseResponse setNewPassword(LoginVO loginVO) throws SQLException;

    public abstract LoginVO isCognitoMember(LoginVO loginVO, HttpSession session) throws SQLException;

    public abstract LoginVO isCognitoSsoMember(LoginVO loginVO) throws SQLException;

    public abstract void chgTel(Map<String, Object> modelMap) throws SQLException;
}
