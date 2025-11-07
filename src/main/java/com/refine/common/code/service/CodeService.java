package com.refine.common.code.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CodeService {
    public static final Logger logger = LoggerFactory.getLogger(CodeService.class);

    public List<Map<String, String>> getRfCodeByComClsCd(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getRltyCd(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getRfCode(String comClsCd) throws SQLException;

    public abstract List<Map<String, String>> getRfCodeFilter(String comClsCd,String chkCd1) throws SQLException;

    public abstract List<Map<String, String>> getRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getRfCode2(String comClsCd) throws SQLException;

    public abstract List<Map<String, String>> getRfCode3(String comClsCd) throws SQLException;

    public abstract List<Map<String, String>> getFndPrpsTpCdForLttr() throws SQLException;

    public abstract List<Map<String, String>> getRfOldCodeForLttr(String comClsCd) throws SQLException;

    public abstract List<Map<String, Object>> getLeftRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getInstCode() throws SQLException;

    public abstract List<Map<String, String>> getListInstCode(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getListNhInstCode(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getOffInstCode() throws SQLException;

    public abstract String getLttrInstCd(String instCd) throws SQLException;

    public abstract List<Map<String, String>> getBrnhCodeByInst(String instCd) throws SQLException;

    public abstract List<Map<String, String>> getBrnhCodeByInst(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getPrdtCodeByInst(String instCd) throws SQLException;

    public abstract List<Map<String, String>> getPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getSrvyOpinByInst(String instCd, String srvyOpinTpCd) throws SQLException;

    public abstract String getSrvyOpinCnts(String instCd, String srvyOpinTpCd, String srvyOpinCd) throws SQLException;

    public abstract List<Map<String, String>> getNtfcCode(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getAooTpCdCode(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getSrvyFrmList(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getIlSrvyFrmList() throws SQLException;

    public abstract List<Map<String, String>> getSrvyFrmListAll(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getDptCode() throws SQLException;

    public List<Map<String, String>> getSrvyOpinList(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getRightDocList(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getPrcsStats(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getXpstPrcsStats(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getXpstPrcsStats2(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getXpstPrcsStats2Prev(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getXpstPrcsStats3(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, String> getTtlCodeByInst(Map<String, Object> modelMap) throws SQLException;

    public abstract void insertErrLog(Map<String, Object> modelMap) throws SQLException;

    public abstract String isSaveBtnYn(Map<String, Object> modelMap) throws SQLException;

    public abstract String getPublicYn() throws SQLException;

    public abstract List<Map<String, String>> getXpstNewCnfmCd(Map<String, Object> modelMap) throws SQLException;

    public abstract String getInstNm(Map<String, Object> modelMap) throws SQLException;

    public abstract String getInstNm2(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getTelUserList() throws SQLException;

    public abstract List<Map<String, String>> getCalTrgtClsCd(String comClsCd) throws SQLException;

    public abstract List<Map<String, String>> getCalTrgtClsCdAgnt(String comClsCd) throws SQLException;

    public abstract String getDocImgSaveQry(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getXpstRgstrOpinCd(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> lsrAgrClsCdList(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> lsrAgrClsCdGrpList(Map<String, Object> modelMap) throws SQLException;

    public String getTtlInsrNm(Map<String, Object> modelMap) throws SQLException;

    public String getCrpNm(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getMemoClsCd() throws SQLException;

    public List<Map<String, String>> getIns32tChrgrList() throws SQLException;

    public List<Map<String, String>> getIns32tChrgrFaxList() throws SQLException;

    public List<Map<String, String>> getJobLgrClsCd() throws SQLException;

    public List<Map<String, String>> getJobMidClsCd(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getJobSmlClsCd(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getJobPstnClsCd(Map<String, Object> modelMap) throws SQLException;

    public abstract String isSaveBtnYn2(Map<String, Object> modelMap) throws SQLException;

    public int getIsDate(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getJlCalClsCd() throws SQLException;

    public String getSysCd(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getTellUsrList(Map<String, Object> modelMap) throws SQLException;

    public List<Map<String, String>> getDptList(Map<String, Object> modelMap) throws SQLException;

    public String getQstVer(Map<String, Object> modelMap) throws SQLException;

    public String getSysClsCd(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getListHugInstCode(Map<String, Object> modelMap) throws SQLException;

    public abstract List<Map<String, String>> getHugPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException;

    public abstract String getCodeNm(Map<String, Object> modelMap) throws SQLException;

    public String getSrvyImpYn(Map<String, Object> modelMap) throws SQLException;

    public abstract Map<String, Object> getMenuInfo(String menuCd) throws SQLException;
}
