package com.refine.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.refine.login.service.LoginService;
import com.refine.login.vo.LoginVO;

@Component("ap")
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	protected LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginVO loginVO = new LoginVO();
		String strSession = (String)authentication.getCredentials();

		if (strSession != null
					&& strSession.contains("sessionkey")
					&& strSession.contains("refreshkey")) {
			//loginVO = (LoginVO)authentication.getCredentials();
			Gson gson = new Gson();
			loginVO = gson.fromJson(strSession, LoginVO.class);
			try {
			LoginVO currentUsr = loginService.tokenCheck(loginVO);
			if(currentUsr == null) {
				throw new CustomBadCredentialsException("Login Fail !!");
			}
			else {
				LoginVO pwdLogin2 = loginService.isCognitoSsoMember(currentUsr);
				loginVO.setUsrId(pwdLogin2.getUsrId());
				loginVO.setPwd(pwdLogin2.getPwd());
			}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		} else {
			loginVO.setUsrId(authentication.getName());
			loginVO.setPwd((String)authentication.getCredentials());
		    try {
		    	loginVO.setPwChgTm(loginVO.getPwd());
				loginVO.setPwd(HashUtil.digestStringSHA256(loginVO.getPwd()));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}

	    //기존 로직으로 사전체크
	    Map<String, Object> validationResult;
		try {
			validationResult = loginService.preAuthenticate(loginVO);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	    if(!"LOGIN_OK".equals(validationResult.get("msg"))) {
	    	throw new CustomBadCredentialsException("Login Error !!", validationResult);
	    }

	    //여기서 부터는 spring security 통상적인 절차.
	    List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
	    String roles = Optional.ofNullable(((LoginVO)validationResult.get("USER")).getRoles()).orElse("").toString();
	    Splitter.on(",").omitEmptyStrings().trimResults().split(roles).forEach(
	    	c1 -> authorities.add(new SimpleGrantedAuthority(c1))
	    );
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

	    UsernamePasswordAuthenticationToken upAuthentication = new UsernamePasswordAuthenticationToken(validationResult.get("USER"), null, authorities);
	    upAuthentication.setDetails(validationResult);
	    return upAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@SuppressWarnings("unchecked")
	public String getDtyCd() {
		Map<String, Object> authDetails = (Map<String, Object>)SecurityContextHolder.getContext().getAuthentication().getDetails();
		return  ((LoginVO)authDetails.get("USER")).getDtyCd(); //authentication.details['USER'].dtyCd
	}

}
