package com.refine.login.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.refine.login.vo.LoginVO;
import com.refine.login.vo.MenuAuthVO;
import com.refine.login.vo.ScrnAuthVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

    @Resource(name="sqlSessionRF")
    private SqlSession sqlMapClientTemplateRF;

    @Override
    public LoginVO isMember(LoginVO loginVO) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("login.isMember", loginVO);
    }

    @Override
    public LoginVO isCognitoMember(LoginVO loginVO) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("login.isCognitoMember", loginVO);
    }

    @Override
    public LoginVO getMember(String usrId) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("login.getMember", usrId);
    }

    public String getPwMiss(String UsrId) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("login.getPwMiss", UsrId);
    }

    public void isPwMiss(Map<String, Object> _map) throws SQLException {
        sqlMapClientTemplateRF.update("login.isPwMiss", _map);
    }

    public void insertLockHis(Map<String, Object> _map) throws SQLException {
        sqlMapClientTemplateRF.update("login.insertLockHis", _map);
    }

    @Override
    public List<ScrnAuthVO> getScrnAuth(String dptCd) throws SQLException {
        List<ScrnAuthVO> list = sqlMapClientTemplateRF.selectList("login.getScrnAuth", dptCd);
        return list;
    }

    @Override
    public List<MenuAuthVO> getMenuAuth(String usrId) throws SQLException {
        List<MenuAuthVO> list = sqlMapClientTemplateRF.selectList("login.getMenuAuth", usrId);
        return list;
    }

    /**
     *  MAIN 메뉴 업무별 MAX값 구하기
     */
/*	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMainData(Map<String, Object> modelMap) throws SQLException {
		List<Map<String, Object>> list = null;
		list = sqlMapClientTemplateRF.selectList("login.selectMainData", modelMap);
		return list;
	}*/

    /**
     *  MAIN 메뉴 업무별 MAX값 구하기
     */
    @Override
    public JSONArray getMainData(Map<String, Object> modelMap) throws SQLException {
        List<Map<String, Object>> list = null;
        list = sqlMapClientTemplateRF.selectList("login.selectMainData", modelMap);

        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }

    /**
     * 패스워드 변경
     */
    @Override
    public void updateChgPw(Map<String, Object> modelMap) throws SQLException {
        sqlMapClientTemplateRF.update("login.updateChgPw", modelMap);
    }

    /**
     * 패스워드 변경
     */
    @Override
    public void updateEmp(Map<String, Object> modelMap) throws SQLException {
        sqlMapClientTemplateRF.update("login.updateEmp", modelMap);
    }

    /**
     * 패스워드 변경 이력쌓기
     */
    public void insertPwChgHis(Map<String, Object> modelMap) throws SQLException{
        sqlMapClientTemplateRF.update("login.insertPwChgHis", modelMap);
    }

    public String getPwChgTm(String usrId) throws SQLException{
        return sqlMapClientTemplateRF.selectOne("login.getPwChgTm", usrId);
    }

    public void chgTel(Map<String, Object> modelMap) throws SQLException {
        sqlMapClientTemplateRF.update("login.updateChgTel", modelMap);
    }
}
