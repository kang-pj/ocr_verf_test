package com.refine.common.cognito;

import com.refine.login.vo.BaseResponse;
import com.refine.login.vo.LoginVO;

import java.lang.reflect.Type;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CognitoAuthService {

    public static final Logger logger = LoggerFactory.getLogger(CognitoAuthService.class);

    public String idCheck(String usrId) throws Exception;

    public BaseResponse passwordReset(String usrId) throws Exception;

    public BaseResponse setNewPassword(LoginVO loginVO) throws Exception;

    public BaseResponse cognitoLoginForm(String usrId, String pwd) throws Exception;

    public BaseResponse verifyAccessCode(String usrId, String accCode) throws Exception;
}
