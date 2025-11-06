package com.refine.login.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.refine.login.vo.LoginVO;
import com.refine.login.vo.MenuAuthVO;
import com.refine.login.vo.ScrnAuthVO;

public interface LoginDAO {
	public static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);

	public abstract LoginVO isMember(LoginVO loginVO) throws SQLException;

	public abstract LoginVO isCognitoMember(LoginVO loginVO) throws SQLException;

	public abstract LoginVO getMember(String usrId) throws SQLException;

	public String getPwMiss(String UsrId) throws SQLException;

	public void isPwMiss(Map<String, Object> _map) throws SQLException;

	public void insertLockHis(Map<String, Object> _map) throws SQLException;

	public abstract List<ScrnAuthVO> getScrnAuth(String scrnAuthCd) throws SQLException;

	public abstract List<MenuAuthVO> getMenuAuth(String usrId) throws SQLException;

	public abstract JSONArray getMainData(Map<String, Object> modelMap) throws SQLException;

	public abstract void updateChgPw(Map<String, Object> modelMap) throws SQLException;

	public abstract void updateEmp(Map<String, Object> modelMap) throws SQLException;

	public void insertPwChgHis(Map<String, Object> _map) throws SQLException;

	public String getPwChgTm(String usrId) throws SQLException;

	public void chgTel(Map<String, Object> _map) throws SQLException;
}
