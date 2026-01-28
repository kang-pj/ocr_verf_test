package com.refine.common.cognito;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.refine.login.vo.BaseError;
import com.refine.login.vo.BaseResponse;
import com.refine.login.vo.LoginVO;
import com.refine.util.HttpUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@ConfigurationProperties
public class CognitoAuthServiceImpl implements CognitoAuthService {

    @Setter
    public String authUrl;

    @Qualifier(value="restTemplate")
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String idCheck(String usrId) throws Exception {
        String cognitoYn = "";
        BaseResponse authResult = restTemplate.getForObject(authUrl+ "/RF_AUTH/api/user/idCheck?userId=" + usrId, BaseResponse.class);

        if (authResult != null) {
            if (authResult.getClass().equals(BaseError.class)) {
                Gson gson = new Gson();
                String result = gson.toJson(authResult);

                if (result != null) {
                    if (result.contains("EAPI_USER_NOT_FOUND")) {
                        cognitoYn = "N";
                    } else if (result.contains("UserNotConfirmedException")) {
                        cognitoYn = "E"; // Email  인증 안됨
                    }
                }
            } else {
                cognitoYn = "Y";
            }
        }

        return cognitoYn;
    }

    @Override
    public BaseResponse passwordReset(String usrId) throws Exception {
        BaseResponse authResult;

        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("usrId", usrId);

        Gson gson = new Gson();
        String userData = gson.toJson(modelMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        authResult = restTemplate.postForObject(authUrl+ "/RF_AUTH/api/user/passwordReset", new HttpEntity<>(userData, headers), BaseResponse.class);
        return authResult;
    }

    @Override
    public BaseResponse setNewPassword(LoginVO loginVO) throws Exception {
        BaseResponse authResult;

        Gson gson = new Gson();
        String userData = gson.toJson(loginVO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        authResult = restTemplate.postForObject(authUrl + "/RF_AUTH/api/user/setNewPassword", new HttpEntity<>(userData, headers), BaseResponse.class);

        return authResult;
    }

    @Override
    public BaseResponse cognitoLoginForm(String usrId, String pwd) throws Exception {
        BaseResponse authResult = new BaseResponse();

        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("usrId", usrId);
        modelMap.put("pwd", pwd);

        Gson gson = new Gson();
        String userData = gson.toJson(modelMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        authResult = restTemplate.postForObject(authUrl + "/RF_AUTH/api/login", new HttpEntity<>(userData, headers), BaseResponse.class);
        
        return authResult;
    }

    @Override
    public BaseResponse verifyAccessCode(String usrId, String accCode) throws Exception {
        BaseResponse authResult = new BaseResponse();
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("usrId", usrId);
        modelMap.put("accCode", accCode);
        Type resultType = new TypeToken<BaseResponse>() {}.getType();

        Gson gson = new Gson();
        String userData = gson.toJson(modelMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        authResult = restTemplate.postForObject(authUrl + "/RF_AUTH/api/user/verifyAccessCode", new HttpEntity<>(userData, headers), BaseResponse.class);

        return authResult;
    }
}
