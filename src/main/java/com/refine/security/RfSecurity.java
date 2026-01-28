package com.refine.security;

import java.util.Base64;

public class RfSecurity {
    // 임시 보안 클래스
    
    public static String RfEncode(String str) {
        if (str == null) return null;
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    
    public static String RfDecode(String str) {
        if (str == null) return null;
        return new String(Base64.getDecoder().decode(str));
    }
}
