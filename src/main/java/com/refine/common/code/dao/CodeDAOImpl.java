package com.refine.common.code.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDAOImpl implements CodeDAO {

    @Resource(name = "sqlSessionRF")
    private SqlSession sqlMapClientTemplateRF;

    /**
     * 코드 리스트(저당용)
     */
    @Override
    public List<Map<String, String>> getRfCodeByComClsCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getRfCodeByComClsCd", modelMap);
    }

    /**
     * 물건종류(저당, 전세)
     */
    @Override
    public List<Map<String, String>> getRltyCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getRltyCd", modelMap);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCode", map);

    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCodeFilter(String comClsCd, String chkCd1) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("comClsCd", comClsCd);
        map.put("chkCd1", chkCd1);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCodeFilter", map);

    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException {
        modelMap.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCode", modelMap);

    }

    /**
     * 코드에 해당하는 공통코드비고 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode2(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCode2", map);

    }

    /**
     * 코드에 해당하는 공통코드비고 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode3(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCode3", map);

    }

    /**
     * 전문용 자금용도코드 리스트
     */
    @Override
    public List<Map<String, String>> getFndPrpsTpCdForLttr() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getFndPrpsTpCdForLttr");
    }

    /**
     * 전문용 코드 매핑  리스트
     */
    @Override
    public List<Map<String, String>> getRfOldCodeForLttr(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfOldCodeForLttr", map);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, Object>> getLeftRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException {
        modelMap.put("comClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getRfCode", modelMap);

    }

    /**
     * 기관(은행) 리스트 가져오기
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getInstCode() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getInstCode");
    }


    /**
     * 기관(은행) 리스트 가져오기 리스트용(부서구분)
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getListInstCode(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getListInstCode", modelMap);
    }

    /**
     * 사후 중개업소 기관(은행) 리스트 가져오기
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getListNhInstCode(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getListNhInstCode", modelMap);
    }

    /**
     * offline 기관(은행) 리스트 가져오기
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getOffInstCode() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getOffInstCode");
    }

    /**
     * 전문전송용 기관코드 가져오기
     */
    @Override
    public String getLttrInstCd(String instCd) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getLttrInstCd", instCd);
    }

    /**
     * 기관(은행)에 따른 지점 리스트 가져오기
     *
     * @param instCd 지점(은행) 코드
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getBrnhCodeByInst(String instCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("instCd", instCd);

        return sqlMapClientTemplateRF.selectList("common.code.getBrnhCodeByInst", map);

    }

    /**
     * 기관(은행)에 따른 지점 리스트 가져오기
     *
     * @param instCd 지점(은행) 코드
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getBrnhCodeByInst(Map<String, Object> modelMap) throws SQLException {

        return sqlMapClientTemplateRF.selectList("common.code.getBrnhCodeByInstChk", modelMap);
        //logger.debug("list.length::::::::::::::::::"+list.size());

    }

    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     *
     * @param instCd 지점(은행) 코드
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getPrdtCodeByInst(String instCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("instCd", instCd);

        return sqlMapClientTemplateRF.selectList("common.code.getPrdtCodeByInst", map);

    }

    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     *
     * @param instCd 지점(은행) 코드
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException {

        return sqlMapClientTemplateRF.selectList("common.code.getPrdtCodeByInstChk", modelMap);
        //logger.debug("list.length::::::::::::::::::"+list.size());

    }

    /**
     * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사)에 따른 조사의견 리스트 가져오기
     *
     * @param instCd       지점(은행) 코드
     * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getSrvyOpinByInst(String instCd, String srvyOpinTpCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("instCd", instCd);
        map.put("srvyOpinTpCd", srvyOpinTpCd);

        return sqlMapClientTemplateRF.selectList("common.code.getSrvyOpinByInst", map);

    }

    /**
     * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사), 조사의견에 따른 조사의견내용 가져오기
     *
     * @param instCd       지점(은행) 코드
     * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
     * @param srvyOpinCd   조사의견코드
     * @return
     * @throws SQLException
     */
    @Override
    public String getSrvyOpinCnts(String instCd, String srvyOpinTpCd, String srvyOpinCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("instCd", instCd);
        map.put("srvyOpinTpCd", srvyOpinTpCd);
        map.put("srvyOpinCd", srvyOpinCd);

        return sqlMapClientTemplateRF.selectOne("common.code.getSrvyOpinCnts", map);
    }

    /**
     * 임대인고지 방법 조회 (각 은행별마다 나오는 메뉴가 다름)
     */
    @Override
    public List<Map<String, String>> getNtfcCode(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getNtfcCode", modelMap);
    }

    /**
     * 임대인고지 방법 조회 (대주보)
     */
    @Override
    public List<Map<String, String>> getAooTpCdCode(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getAooTpCdCode", modelMap);
    }

    /**
     * 조사업체 리스트
     */
    @Override
    public List<Map<String, String>> getSrvyFrmList(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getSrvyFrmList", modelMap);
    }

    /**
     * 인터넷 전세조사업체 리스트
     */
    @Override
    public List<Map<String, String>> getIlSrvyFrmList() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getIlSrvyFrmList");
    }


    /**
     * 조사업체 ALL 리스트
     */
    @Override
    public List<Map<String, String>> getSrvyFrmListAll(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getSrvyFrmListAll", modelMap);
    }

    /**
     * 부서(리파인) 리스트 가져오기
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getDptCode() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getDptCode");
    }


    public List<Map<String, String>> getSrvyOpinList(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getSrvyOpinList", modelMap);
    }

    /**
     * RIGHT메뉴 서류추가 화면 서류리스트
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getRightDocList(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getRightDocList", modelMap);
    }

    /**
     * 사전 처리현황
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getPrcsStats(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getPrcsStats", modelMap);
    }

    /**
     * 사후 등기변동 처리현황
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getXpstPrcsStats", modelMap);
    }

    /**
     * 사후 전입 처리현황
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats2(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getXpstPrcsStats2", modelMap);
    }

    /**
     * 이전 사후 전입 처리현황
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats2Prev(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getXpstPrcsStats2Prev", modelMap);
    }


    /**
     * 사후 해당 회차 등기변동 처리현황
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats3(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getXpstPrcsStats3", modelMap);
    }


    /**
     * 기관(은행)/상품에 따른 권원보험사코드 가져오기
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getTtlCodeByInst(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getTtlCodeByInst", modelMap);
    }

    /**
     * 에러로그 남기기
     *
     * @param modelMap
     * @throws SQLException
     */
    @Override
    public void insertErrLog(Map<String, Object> modelMap) throws SQLException {
        sqlMapClientTemplateRF.update("common.code.insertErrLog", modelMap);
    }

    /**
     * 확인서 발송후 저장버튼 활성화 가능여부
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String isSaveBtnYn(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.isSaveBtnYn", modelMap);
    }

    /**
     * 부동산 고유 번호 등록 버튼 control
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getPublicYn() throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getPublicYn");
    }

    /**
     * 전입조사유선실행계획 확인회차코드 리스트가져오기
     */
    @Override
    public List<Map<String, String>> getXpstNewCnfmCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getXpstNewCnfmCd", modelMap);
    }

    /**
     * 기관명
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getInstNm(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getInstNm", modelMap);
    }

    /**
     * 기관명
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getInstNm2(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getInstNm2", modelMap);
    }

    /**
     * 유선 USER 리스트
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getTelUserList() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getTelUserList");
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getCalTrgtClsCd(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("calTrgtClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getCalTrgtClsCd", map);

    }

    /**
     * 대리인 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getCalTrgtClsCdAgnt(String comClsCd) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("calTrgtClsCd", comClsCd);

        return sqlMapClientTemplateRF.selectList("common.code.getCalTrgtClsCdAgnt", map);

    }

    /**
     * 이미지 편집툴을 이용하여 편집된 이미지를 저장하기 위한 쿼리를 생성하여 리턴한다.
     */
    @Override
    public String getDocImgSaveQry(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getDocImgSaveQry", modelMap);
    }

    /**
     * 사후등기 조사의견 리스트
     */
    public List<Map<String, String>> getXpstRgstrOpinCd(Map<String, Object> modelMap) throws SQLException {
        List<Map<String, String>> list = null;
        list = sqlMapClientTemplateRF.selectList("common.code.getXpstRgstrOpinCd", modelMap);
        return list;
    }

    /**
     * 임대인 동의분류 코드
     */
    public List<Map<String, String>> lsrAgrClsCdList(Map<String, Object> modelMap) throws SQLException {
        List<Map<String, String>> list = null;
        if ("01".equals((String) modelMap.get("instCd"))) { //신한
            list = sqlMapClientTemplateRF.selectList("common.code.lsrAgrClsCdList1", modelMap);
        } else if ("12".equals((String) modelMap.get("instCd")) && ("005".equals((String) modelMap.get("prdtCd")) || "025".equals((String) modelMap.get("prdtCd")))) {
            list = sqlMapClientTemplateRF.selectList("common.code.lsrAgrClsCdList012", modelMap);
        } else {
            list = sqlMapClientTemplateRF.selectList("common.code.lsrAgrClsCdList01", modelMap);
        }
        return list;
    }

    /**
     * 임대인동의그룹리스트 및 임대인동의디폴트
     */
    public List<Map<String, String>> lsrAgrClsCdGrpList(Map<String, Object> modelMap) throws SQLException {
        List<Map<String, String>> list = null;
        list = sqlMapClientTemplateRF.selectList("common.code.lsrAgrClsCdGrpList", modelMap);
        return list;
    }

    /**
     * 보험사 귀중명
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public String getTtlInsrNm(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getTtlInsrNm", modelMap);
    }

    /**
     * 법인명
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public String getCrpNm(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getCrpNm", modelMap);
    }

    /**
     * 전세용 메모분류코드
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getMemoClsCd() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getMemoClsCd");
    }

    /**
     * 대주보 지사 리스트
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getIns32tChrgrList() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getIns32tChrgrList");
    }

    /**
     * 주택도시보증 지사 리스트
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getIns32tChrgrFaxList() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getIns32tChrgrFaxList");
    }

    /**
     * 직업 대분류코드
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobLgrClsCd() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getJobLgrClsCd");
    }

    /**
     * 직업 중분류코드
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobMidClsCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getJobMidClsCd", modelMap);
    }

    /**
     * 직업 소분류코드
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobSmlClsCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getJobSmlClsCd", modelMap);
    }

    /**
     * 직위 소분류코드
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobPstnClsCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getJobPstnClsCd", modelMap);
    }

    @Override
    public String isSaveBtnYn2(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.isSaveBtnYn2", modelMap);
    }

    /**
     * 날짜 포멧이 맞는지 확인
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public int getIsDate(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getIsDate", modelMap);
    }

    /**
     * 전세 녹취분류코드
     */
    @Override
    public List<Map<String, String>> getJlCalClsCd() throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getJlCalClsCd");
    }

    /**
     * 시스템코드
     */
    @Override
    public String getSysCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getSysCd", modelMap);
    }

    /**
     * 유선담당자 가져오기
     *
     * @param
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getTellUsrList(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getTellUsrList", modelMap);
    }

    /**
     * 부서리스트
     */
    @Override
    public List<Map<String, String>> getDptList(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getDptList", modelMap);
    }

    /**
     * 질문지버전
     */
    @Override
    public String getQstVer(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getQstVer", modelMap);
    }

    /**
     * 상품분류코드
     */
    @Override
    public String getSysClsCd(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getSysClsCd", modelMap);
    }

    /**
     * 기관(은행) 리스트 가져오기 리스트용(부서구분)
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getListHugInstCode(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectList("common.code.getListHugInstCode", modelMap);
    }


    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     *
     * @param instCd 지점(은행) 코드
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getHugPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException {

        return sqlMapClientTemplateRF.selectList("common.code.getHugPrdtCodeByInstChk", modelMap);
        //logger.debug("list.length::::::::::::::::::"+list.size());

    }

    /**
     * 코드명 가져오기
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getCodeNm(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getCodeNm", modelMap);
    }

    /**
     * 조사지 불가지역 구분값 가져오기
     *
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getSrvyImpYn(Map<String, Object> modelMap) throws SQLException {
        return sqlMapClientTemplateRF.selectOne("common.code.getSrvyImpYn", modelMap);
    }

    @Override
    public Map<String, Object> getMenuInfo(String menuCd) throws SQLException {
        Map<String, String> modelMap = new HashMap<String, String>();
        modelMap.put("menuCd", menuCd);

        return sqlMapClientTemplateRF.selectOne("common.code.getMenuInfo", modelMap);
    }
}
