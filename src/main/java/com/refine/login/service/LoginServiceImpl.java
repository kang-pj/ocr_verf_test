package com.refine.login.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.refine.common.cognito.CognitoAuthService;
import com.refine.login.dao.LoginDAO;
import com.refine.login.vo.*;
import com.refine.util.SessionUtil;
import lombok.Setter;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@Service
@ConfigurationProperties
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    CognitoAuthService cognitoAuthService;

    @Setter public String authUrl;

    @Qualifier(value="restTemplate")
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Map<String, Object> preAuthenticate(LoginVO loginVO) throws SQLException {

        Map<String, Object> resultMap = Maps.newHashMap();
        String orgPwd = loginVO.getPwChgTm();

        // 0. 사용자 비밀번호 틀린 횟수 확인
        Map<String, Object> _map = new HashMap<String, Object>();
        _map.put("usrId", loginVO.getUsrId());
        String pw_miss = loginDAO.getPwMiss(loginVO.getUsrId());
        if (pw_miss == null) pw_miss = "";
        resultMap.put("pwMissCnt", pw_miss);

        if (pw_miss.equals("5")) {  // 오류 횟수 5회일 때 로그인 시도하지 않고 잠금 메세지 띄움
            resultMap.put("msg", NOT_EXIST_USR);
            return resultMap;
        }

        // 1. 사용자 정보 확인
        LoginVO userVO = loginDAO.isMember(loginVO);

        if (userVO == null) {
            if (!"".equals(pw_miss)) {
                _map.put("pw_miss", pw_miss);
                _map.put("loginMiss", 'Y');
                loginDAO.isPwMiss(_map);        //비밀번호 오류 카운트 올리기
                if (pw_miss.equals("4")) loginDAO.insertLockHis(_map);
                int pwMissCnt = Integer.parseInt(pw_miss) + 1;
                resultMap.put("pwMissCnt", Integer.toString(pwMissCnt));
            }

            resultMap.put("msg", NOT_EXIST_USR);
            return resultMap;
        }

        // 2. 코그니토 체크
        try {
            if (loginVO.getSessionkey() != null) {
                resultMap.put("COGNITOYN", "Y");
                resultMap.put("SESSION_KEY", loginVO.getSessionkey());
                resultMap.put("REFRESH_KEY", loginVO.getRefreshkey());
                resultMap.put("REDIRECT_URL", loginVO.getRedirecturl());
                resultMap.put("NEW_SITE_YN", "Y");
                resultMap.put("RF_LTAB_NUM", loginVO.getRfLtabNum());
            } else {
                resultMap.put("COGNITOYN", "N");
                String cognitoYn = cognitoAuthService.idCheck(loginVO.getUsrId());

                if ("Y".equals(cognitoYn)) {
                    BaseResponse response = cognitoAuthService.cognitoLoginForm(loginVO.getUsrId(), orgPwd);

                    if (response != null && "ok".equals(response.getStatus())) {
                        Map<String, String> resultStat = (Map<String, String>) response.getValue();
                        resultMap.put("SESSION_KEY", resultStat.get("sessionkey"));
                        resultMap.put("REFRESH_KEY", resultStat.get("refreshkey"));
                        resultMap.put("COGNITOYN", "Y");
                    }
                } else if ("E".equals(cognitoYn)) {
                    resultMap.put("msg", "COGNITO_EMAIL_NOT_CONFIRM");
                    return resultMap;
                } else if ("N".equals(cognitoYn)) {
                    resultMap.put("msg", "EAPI_USER_NOT_FOUND");
                    return resultMap;
                }
            }

            // 멀티로그인사용자가 아닌경우
            if ("N".equals(userVO.getMltLoginYn())) {
                SessionUtil.setSessions(userVO, (String)resultMap.get("SESSION_KEY"));
            }
        } catch ( Exception ex) {
            logger.error("cognito error ::::"  +  ex.getMessage());
        }

        // 3. 패스워드 변경일 체크하기
        String pwChgTm = loginDAO.getPwChgTm(userVO.getUsrId());
        String pwModify = "N";
        int count = 0;

        if (pwChgTm != null && !"".equals(pwChgTm)) {
            Date today = new Date();
            Calendar cal = Calendar.getInstance();

            cal.setTime(today);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Integer.parseInt(pwChgTm.substring(0,4)), Integer.parseInt(pwChgTm.substring(4,6))-1, Integer.parseInt(pwChgTm.substring(6,8)));

            while (!cal2.after(cal)) {
                count++;
                cal2.add (Calendar.DATE, 1);
                if (count > 100) break;
            }

            if (count > 90) {
                pwModify = "Y";
            }
        } else {
            pwModify = "Y";
        }

        if ("Y".equals(pwModify)) {
            resultMap.put("msg", LOGIN_OK_PWMODIFY);
            return resultMap;
        }

        // 4. 메뉴권한 정보 확인
        List<MenuAuthVO> menuAuthList = loginDAO.getMenuAuth(userVO.getUsrId());
        if (menuAuthList == null || menuAuthList.isEmpty()) {
            resultMap.put("msg", NOT_EXIST_MENUAUTH);
            return resultMap;
        }

        // 5. 화면권한 정보 확인
        List<ScrnAuthVO> scrnAuthList = loginDAO.getScrnAuth(userVO.getDptCd());
        if (scrnAuthList == null || scrnAuthList.isEmpty()) {
            resultMap.put("msg", NOT_EXIST_SCRNAUTH);
            return resultMap;
        }

        // 6. 사용자, 메뉴권한, 화면권한 세션 정보 세팅
        resultMap.put("USER", userVO);
        resultMap.put("MENU_AUTH", menuAuthList);
        resultMap.put("SCRN_AUTH", scrnAuthList);
        resultMap.put("SYS_AUTH", userVO.getSysAuthCnts());
        resultMap.put("msg", LOGIN_OK);

        // 7. 비밀번호 오류 횟수 초기화
        _map.put("loginMiss", 'N');
        loginDAO.isPwMiss(_map);

//        logger.debug("@@@ 세션 세팅 정보");
//        logger.debug(userVO.toString());
//        logger.debug(menuAuthList.toString());
//        logger.debug(scrnAuthList.toString());
//        logger.debug("@@@ 세션 세팅 정보 끝");

        return resultMap;
    }

    /**
     * MAIN 메뉴 업무별 MAX값 구하기
     */
    @Override
    public JSONArray getMainData(Map<String, Object> modelMap) throws SQLException {
        return loginDAO.getMainData(modelMap);

    }

    /**
     * 패스워드 변경
     */
    @Override
    public void updateChgPw(Map<String, Object> modelMap) throws SQLException {
        loginDAO.updateChgPw(modelMap);
        String passChg = (String)modelMap.get("passChg");
        if("Y".equals(passChg)){
            loginDAO.updateEmp(modelMap);
            loginDAO.insertPwChgHis(modelMap);
        }
    }

    @Override
    public LoginVO isMember(LoginVO loginVO) throws SQLException {
        return loginDAO.isMember(loginVO);
    }

    @Override
    public LoginVO tokenCheck(LoginVO loginVO) throws SQLException {
        logger.debug("tokenCheck start");
        LoginVO resultUser = new LoginVO();
        BaseResponse<LoginVO> authResult;
        try {
            Gson gson = new Gson();
            String loginStr = gson.toJson(loginVO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            authResult = restTemplate.postForObject(authUrl + "/RF_AUTH/api/auth/ssotoken", new HttpEntity<>(loginStr, headers) ,BaseResponse.class);
            if(authResult != null) {
                if(authResult.getClass().equals(BaseError.class)) {
                    resultUser = null;
                }else {
                    ObjectMapper mapper = new ObjectMapper();
                    LoginVO loginInfo = mapper.readValue(gson.toJson(authResult.getValue()), LoginVO.class);

                    if(loginInfo != null && loginInfo.getUsrId() != null) {
                        resultUser = loginInfo;
                    } else {
                        resultUser = null;
                    }
                }
            }

        } catch(Exception ex) {
            logger.debug(ex.getMessage());
            resultUser = null;
        }
        logger.debug("tokenCheck end");
        return resultUser;
    }

    @Override
    public LoginVO isCognitoSsoMember(LoginVO loginVO) throws SQLException {

        // 1. 사용자 정보 확인
        LoginVO userVO = loginDAO.isCognitoMember(loginVO);
        return userVO;
    }

    @Override
    public LoginVO isCognitoMember(LoginVO loginVO, HttpSession session) throws SQLException {

        // 1. 사용자 정보 확인
        LoginVO userVO = loginDAO.isCognitoMember(loginVO);
        if (userVO == null){
            return null;
        }

        //사용자 권한 임시 주석 처리 나중에 다시 원복해야 함.
        // 3. 메뉴권한 정보 확인
        List<MenuAuthVO> menuAuthList = loginDAO.getMenuAuth(userVO.getDptCd());
        if (menuAuthList == null || menuAuthList.isEmpty()){
            return null;
        }

        // 4. 화면권한 정보 확인
        List<ScrnAuthVO> scrnAuthList = loginDAO.getScrnAuth(userVO.getDptCd());
        if (scrnAuthList == null || scrnAuthList.isEmpty()){
            return null;
        }

        // 5. 사용자, 메뉴권한, 화면권한 세션 정보 세팅
        session.setAttribute("USER", userVO);
        session.setAttribute("MENU_AUTH", menuAuthList);
        session.setAttribute("SCRN_AUTH", scrnAuthList);
        session.setAttribute("SYS_AUTH", userVO.getSysAuthCnts());


        return userVO;
    }

    @Override
    public BaseResponse setPasswordReset(LoginVO loginVO) throws SQLException {
        BaseResponse result = new BaseResponse();
        try {

            LoginVO userVO = loginDAO.isMember(loginVO);
            if (userVO == null) {
                result = new BaseError("PASS_NOT_MACH", "패스워드가 틀렸습니다.");
            } else {
                result = cognitoAuthService.passwordReset(loginVO.getUsrId());
            }
        } catch (Exception ex) {
            result = new BaseError("ETC_ERROR", ex.getMessage());
        }
        return result;
    }

    @Override
    public BaseResponse setNewPassword(LoginVO loginVO) throws SQLException {
        BaseResponse result = new BaseResponse();
        try {
            result = cognitoAuthService.setNewPassword(loginVO);
        } catch (Exception ex) {
            result = new BaseError("ETC_ERROR", ex.getMessage());
        }
        return result;
    }

    public void chgTel(Map<String, Object> modelMap) throws SQLException {
        loginDAO.chgTel(modelMap);
    }
}