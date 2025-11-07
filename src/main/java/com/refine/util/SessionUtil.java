package com.refine.util;

import com.refine.login.vo.LoginVO;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

    private static Map<String, Object> sessions;

    public static void setSessions(LoginVO loginVO, String sessionKey) throws Exception {
        if (sessions == null ) {
            sessions = new HashMap<String, Object>();
        }
        sessions.put(loginVO.getUsrId(), sessionKey);
    }

    public static boolean checkSession(LoginVO loginVO, HttpSession session) throws Exception {
        if (sessions != null ) {
            String sessionKey = (String)sessions.get(loginVO.getUsrId());
            if (sessionKey != null && "".equals(sessionKey) == false ) {
                if (sessionKey.equals(session.getAttribute("SESSION_KEY")) == false ) {
                    session.removeAttribute("SCRN_AUTH"); //화면권한 세선 삭제
                    session.removeAttribute("MENU_AUTH"); //메뉴권한 세션 삭제
                    session.removeAttribute("USER");      //사용자 세션 삭제
                    return false;
                }
            }
        }
        return true;
    }
}