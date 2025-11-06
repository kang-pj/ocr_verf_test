package com.refine.login.controller;

import biz.refine.annotation.RequestAttribute;
import com.google.common.net.HttpHeaders;
import com.refine.common.code.service.CodeService;
import com.refine.common.cognito.CognitoAuthService;
import com.refine.login.service.LoginService;
import com.refine.login.vo.BaseResponse;
import com.refine.login.vo.LoginVO;
import com.refine.login.vo.MenuAuthVO;
import com.refine.login.vo.ScrnAuthVO;
import com.refine.rf_core.jdbc.routing.ContextHolder;
import com.refine.security.HashUtil;
import com.refine.util.SessionUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ConfigurationProperties
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected CodeService codeService;

    @Autowired
    private CognitoAuthService cognitoAuthService;

    @GetMapping(value = "/")
    public String home(HttpSession session) {

        LoginVO loginVO = (LoginVO) session.getAttribute("USER");

        if (loginVO == null) {
            return "redirect:/login";
        } else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String getloginForm(HttpServletRequest request, Map<String, Object> modelMap, @RequestParam(value = "redirect", required = false) String redirect) throws SQLException {

        String referer = request.getHeader(HttpHeaders.REFERER);

        if (referer == null || referer.contains("login") || referer.contains("pwChange") || referer.contains("popResetPw") || "false".equals(redirect)) {
            referer = "";
        }

        request.getSession().setAttribute("url_prior_login", referer);

        if (request.getRequestURL().toString().contains("localhost")) {
            return "login";
        } else {
            Map<String, Object> map = codeService.getMenuInfo("PORTAL");
            String portalUrl = (String) map.get("url_addr");
            return "redirect:" + portalUrl;
        }
    }

    @GetMapping(value = "/main")
    public String mainpage(HttpServletRequest request, Map<String, Object> modelMap, HttpSession session) {

        LoginVO loginVO = (LoginVO) session.getAttribute("USER");
        modelMap.put("dptCd", loginVO.getDptCd());
        modelMap.put("usrId", loginVO.getUsrId());
        modelMap.put("usrNm", loginVO.getUsrNm());
        modelMap.put("dtyCd", loginVO.getDtyCd());
        modelMap.put("inPhNo", loginVO.getInPhNo());
        modelMap.put("dbMode", ContextHolder.getDbMode());
        modelMap.put("localPort", request.getLocalPort());

        return "main";
    }

    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> getUserInfo(HttpSession session) throws Exception {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        LoginVO loginVO = (LoginVO) session.getAttribute("USER");
        resultMap.put("userInfo", loginVO);

        return resultMap;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getMenuAuth", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> getMenuAuth(HttpSession session) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<MenuAuthVO> menuAuthList = (List<MenuAuthVO>) session.getAttribute("MENU_AUTH");
        resultMap.put("menuAuth", menuAuthList);

        return resultMap;
    }

    @SuppressWarnings("unchecked")
    @PutMapping(value = "/getScrnAuth")
    @ResponseBody
    public HashMap<String, Object> getScrnAuth(HttpSession session) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<ScrnAuthVO> scrnAuthList = (List<ScrnAuthVO>) session.getAttribute("SCRN_AUTH");
        resultMap.put("scrnAuth", scrnAuthList);

        return resultMap;
    }

    @RequestMapping(value = "/pwChange", method = RequestMethod.GET)
    public String pwChange(Map<String, Object> modelMap, @RequestParam(value = "usrId", required = false) String usrId, HttpSession session) {

        //세션정보 가져오기
        LoginVO login = (LoginVO) session.getAttribute("USER");
        String cognitoYn = (String) session.getAttribute("COGNITOYN");
        modelMap.put("login", login);
        modelMap.put("usrId", usrId);

        if ("Y".equals(cognitoYn)) {
            return "common/popup/popCognitoPwChange";
        } else {
            return "common/errorPop";
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/updatePw", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> updatePw(@RequestAttribute(value = "params") String datas) throws Exception {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> modelMap = (Map<String, Object>) (JSONObject.toBean((JSONObject) (JSONSerializer.toJSON(datas)), Map.class));

        String usrId = (String) modelMap.get("usrId"); // 아이디
        String curPw = (String) modelMap.get("curPw"); // 현재 비밀번호
        String chgPw = (String) modelMap.get("chgPw1"); // 새 비밀번호
        String curPwSHA = HashUtil.digestStringSHA256(curPw);
        String chgPwSHA = HashUtil.digestStringSHA256(chgPw);

        modelMap.put("curPwSHA", curPwSHA); // 입력받은 현재패스워드
        modelMap.put("chgPwSHA", chgPwSHA); // 입력받은 변경할 패스워드

        LoginVO loginVO = new LoginVO();
        loginVO.setUsrId(usrId);
        loginVO.setPwd(curPwSHA);
        LoginVO userVO = loginService.isMember(loginVO);

        if (userVO != null) {
            if (chgPwSHA.equals(userVO.getPwd())) {
                resultMap.put("result", "FAIL_MSG");
                resultMap.put("msg", "현재 비밀번호와 같습니다.");
            } else if (chgPwSHA.equals(userVO.getBfPwd())) {
                resultMap.put("result", "FAIL_MSG");
                resultMap.put("msg", "이전 비밀번호와 같습니다.");
            } else {
                // 패스워드 변경
                loginVO.setPwd(chgPw);
                loginVO.setAuthCode((String) modelMap.get("authCode"));
//                BaseResponse result = loginService.setNewPassword(loginVO);
//
//                if ("fail".equals(result.getStatus())) {
//                    resultMap.put("result", "NOAUTH");
//                } else {
//                    resultMap.put("result", "OK");
//                }
            }
        } else {
            resultMap.put("result", "FAIL");
        }

        return resultMap;
    }

    @PostMapping(value = "/crosslogin")
    public String crossLogin(@ModelAttribute @Valid LoginVO login, HttpSession session) throws Exception {

        Object siteLogin = session.getAttribute("USER");

        if (login != null) {
            logger.debug("Session Key  =" + login.getSessionkey());
            login.setRedirecturl(URLDecoder.decode(login.getRedirecturl(), StandardCharsets.UTF_8));
        } else {
            logger.debug("Session Key  =null");
        }

        if (siteLogin == null) {
            logger.debug("sitelogin =null");
        } else {
            logger.debug("sitelogin =" + siteLogin);
        }

        if (login != null) {
            if (login.getSessionkey() != null && !"".equals(login.getSessionkey()) && login.getRefreshkey() != null && !"".equals(login.getRefreshkey())) {
                LoginVO currentUsr = loginService.tokenCheck(login);
                if (currentUsr != null) {
                    session.setAttribute("SESSION_KEY", login.getSessionkey());
                    session.setAttribute("SESSION_KEY", login.getSessionkey());
                    session.setAttribute("REFRESH_KEY", login.getRefreshkey());
                    session.setAttribute("ENC_SESSION_KEY", new String(Base64.getEncoder().encode(login.getSessionkey().getBytes())));
                    session.setAttribute("ENC_REFRESH_KEY", new String(Base64.getEncoder().encode(login.getRefreshkey().getBytes())));
                    logger.debug("Session Key11  =" + currentUsr.getUsrId());
                    login.setUsrId(currentUsr.getUsrId());
                    loginService.isCognitoMember(currentUsr, session);
                }
            }
        }

        return "crosslogin";
    }

    /**
     * cognito ID 확인 및 확인코드 받기
     *
     * @param datas
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/getResetPwAuthCode")
    @ResponseBody
    public BaseResponse getResetPwAuthCode(@RequestAttribute(value = "params") String datas) throws Exception {

        Map<String, Object> modelMap = (Map<String, Object>) (JSONObject.toBean((JSONObject) (JSONSerializer.toJSON(datas)), Map.class));

        String usrId = (String) modelMap.get("usrId");
        String authResult = cognitoAuthService.idCheck(usrId);

        BaseResponse result = new BaseResponse();
//        if ("Y".equals(authResult)) {
//            result = cognitoAuthService.passwordReset(usrId);
//        } else {
//            result.setStatus("fail");
//            result.setValue("비밀번호를 초기화할 수 없습니다. 관리자에게 문의하십시오.");
//        }

        return result;
    }

    /**
     * 전화번호, 팩스번호 변경
     *
     * @param datas
     * @param session
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/chgTel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> chgTel(@RequestAttribute(value = "params") String datas, HttpSession session) throws Exception {

        Map<String, Object> mapParam = (Map<String, Object>) (JSONObject.toBean((JSONObject) (JSONSerializer.toJSON(datas)), Map.class));
        LoginVO loginVO = (LoginVO) session.getAttribute("USER");
        mapParam.put("updrId", loginVO.getUsrId()); //수정자아이디

        loginService.chgTel(mapParam);

        loginVO.setTelNo((String) mapParam.get("telNo"));
        loginVO.setInPhNo((String) mapParam.get("inphNo"));
        loginVO.setFaxNo((String) mapParam.get("faxNo"));
        SessionUtil.setSessions(loginVO, loginVO.getSessionkey());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "OK");

        return resultMap;
    }
}
