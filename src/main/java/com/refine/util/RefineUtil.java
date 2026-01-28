package com.refine.util;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RefineUtil {
    // 임시 유틸리티 클래스
    private static final Gson gson = new Gson();
    
    public static String getClientIP() {
        return "127.0.0.1";
    }
    
    public static String getCurrYYYYMMDDHH24MISS() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
    
    public static String getObjectToGson(Object obj) {
        return gson.toJson(obj);
    }
}
