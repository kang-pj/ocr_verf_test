package com.refine.common.code.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refine.common.code.dao.CodeDAO;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDAO codeDAO;

    /**
     * 코드 리스트(저당용)
     */
    public List<Map<String, String>> getRfCodeByComClsCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getRfCodeByComClsCd(modelMap);
    }

    /**
     * 물건종류(저당, 전세)
     */
    public List<Map<String, String>> getRltyCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getRltyCd(modelMap);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode(String comClsCd) throws SQLException {
        return codeDAO.getRfCode(comClsCd);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCodeFilter(String comClsCd,String chkCd1) throws SQLException {
        return codeDAO.getRfCodeFilter(comClsCd,chkCd1);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getRfCode(comClsCd, modelMap);
    }

    /**
     * 코드에 해당하는 공통코드비고 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode2(String comClsCd) throws SQLException {
        return codeDAO.getRfCode2(comClsCd);
    }

    /**
     * 코드에 해당하는 공통코드비고 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getRfCode3(String comClsCd) throws SQLException {
        return codeDAO.getRfCode3(comClsCd);
    }

    /**
     * 전문용 자금용도코드 리스트
     */
    @Override
    public List<Map<String, String>> getFndPrpsTpCdForLttr() throws SQLException {
        return codeDAO.getFndPrpsTpCdForLttr();
    }

    /**
     * 전문용 자금용도코드 리스트
     */
    @Override
    public List<Map<String, String>> getRfOldCodeForLttr(String comClsCd) throws SQLException {
        return codeDAO.getRfOldCodeForLttr(comClsCd);
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, Object>> getLeftRfCode(String comClsCd, Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getLeftRfCode(comClsCd, modelMap);
    }

    /**
     * 기관(은행) 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getInstCode() throws SQLException {
        return codeDAO.getInstCode();
    }

    /**
     * 기관(은행) 리스트 가져오기 리스트용(부서구분)
     */
    @Override
    public List<Map<String, String>> getListInstCode(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getListInstCode(modelMap);
    }

    /**
     * 사후 중개업소 기관(은행) 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getListNhInstCode(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getListNhInstCode(modelMap);
    }

    /**
     * offline 기관(은행) 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getOffInstCode() throws SQLException {
        return codeDAO.getOffInstCode();
    }

    /**
     * 전문 전송시 사용되는 기관코트 가져오기
     */
    @Override
    public String getLttrInstCd(String instCd) throws SQLException {
        return codeDAO.getLttrInstCd(instCd);
    }

    /**
     * 기관(은행)에 따른 지점 리스트 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getBrnhCodeByInst(String instCd) throws SQLException {
        return codeDAO.getBrnhCodeByInst(instCd);
    }

    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getBrnhCodeByInst(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getBrnhCodeByInst(modelMap);
    }

    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getPrdtCodeByInst(String instCd) throws SQLException {
        return codeDAO.getPrdtCodeByInst(instCd);
    }

    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getPrdtCodeByInst(modelMap);
    }

    /**
     * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사)에 따른 조사의견 리스트 가져오기
     * @param instCd 지점(은행) 코드
     * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getSrvyOpinByInst(String instCd, String srvyOpinTpCd) throws SQLException {
        return codeDAO.getSrvyOpinByInst(instCd, srvyOpinTpCd);
    }

    /**
     * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사), 조사의견에 따른 조사의견내용 가져오기
     * @param instCd 지점(은행) 코드
     * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
     * @param srvyOpinCd 조사의견코드
     * @return
     * @throws SQLException
     */
    @Override
    public String getSrvyOpinCnts(String instCd, String srvyOpinTpCd, String srvyOpinCd) throws SQLException {
        return codeDAO.getSrvyOpinCnts(instCd, srvyOpinTpCd, srvyOpinCd);
    }

    /**
     * 임대인고지방법 은행, 상품에 따라서 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getNtfcCode(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getNtfcCode(modelMap);
    }

    /**
     * 임대인고지방법 은행, 상품에 따라서 리스트 가져오기  (대주보)
     */
    @Override
    public List<Map<String, String>> getAooTpCdCode(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getAooTpCdCode(modelMap);
    }

    /**
     * 조사업체 리스트
     */
    @Override
    public List<Map<String, String>> getSrvyFrmList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSrvyFrmList(modelMap);
    }

    /**
     * 인터넷전세 조사업체 리스트
     */
    @Override
    public List<Map<String, String>> getIlSrvyFrmList() throws SQLException {
        return codeDAO.getIlSrvyFrmList();
    }

    /**
     * 조사업체 all 리스트
     */
    @Override
    public List<Map<String, String>> getSrvyFrmListAll(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSrvyFrmListAll(modelMap);
    }

    /**
     * 부서(리파인) 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getDptCode() throws SQLException {
        return codeDAO.getDptCode();
    }

    public List<Map<String, String>> getSrvyOpinList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSrvyOpinList(modelMap);
    }

    /**
     * RIGHT메뉴 서류추가 화면 서류리스트
     */
    @Override
    public List<Map<String, String>> getRightDocList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getRightDocList(modelMap);
    }

    /**
     * 사전 처리현황
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getPrcsStats(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getPrcsStats(modelMap);
    }

    /**
     * 사후 등기변동 처리현황
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstPrcsStats(modelMap);
    }

    /**
     * 사후 전입 처리현황
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats2(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstPrcsStats2(modelMap);
    }

    /**
     * 이전 사후 전입 처리현황
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats2Prev(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstPrcsStats2Prev(modelMap);
    }

    /**
     * 사후 해당 회차 등기변동 처리현황
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Object> getXpstPrcsStats3(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstPrcsStats3(modelMap);
    }

    /**
     * 기관(은행)/상품에 따른 권원보험사코드 가져오기
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getTtlCodeByInst(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getTtlCodeByInst(modelMap);
    }

    /**
     * 에러로그 남기기
     * @param modelMap
     * @throws SQLException
     */
    @Override
    public void insertErrLog(Map<String, Object> modelMap) throws SQLException {
        codeDAO.insertErrLog(modelMap);
    }

    /**
     * 확인서 발송후 저장버튼 활성화 가능여부
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String isSaveBtnYn(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.isSaveBtnYn(modelMap);
    }

    /**
     * 부동산 고유 번호 등록 버튼 control
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getPublicYn() throws SQLException {
        return codeDAO.getPublicYn();
    }

    /**
     * 전입조사유선실행계획 확인회차코드 리스트가져오기
     */
    @Override
    public List<Map<String, String>> getXpstNewCnfmCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstNewCnfmCd(modelMap);
    }

    /**
     * 기관명
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getInstNm(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getInstNm(modelMap);
    }

    /**
     * 기관명 풀네임
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getInstNm2(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getInstNm2(modelMap);
    }

    /**
     * 유선 USER 리스트
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getTelUserList() throws SQLException {
        return codeDAO.getTelUserList();
    }

    /**
     * 코드에 해당하는 공통코드 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getCalTrgtClsCd(String comClsCd) throws SQLException {
        return codeDAO.getCalTrgtClsCd(comClsCd);
    }

    /**
     * 대리인 리스트 가져오기
     */
    @Override
    public List<Map<String, String>> getCalTrgtClsCdAgnt(String comClsCd) throws SQLException {
        return codeDAO.getCalTrgtClsCdAgnt(comClsCd);
    }

    /**
     * 이미지 편집툴을 이용하여 편집된 이미지를 저장하기 위한 쿼리를 생성하여 리턴한다.
     */
    @Override
    public String getDocImgSaveQry(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getDocImgSaveQry(modelMap);
    }

    /**
     * 사후등기 조사의견 리스트
     */
    public List<Map<String, String>> getXpstRgstrOpinCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getXpstRgstrOpinCd(modelMap);
    }

    /**
     * 임대인 동의분류 코드
     */
    @Override
    public List<Map<String, String>> lsrAgrClsCdList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.lsrAgrClsCdList(modelMap);
    }

    /**
     * 임대인동의그룹리스트 및 임대인동의디폴트
     */
    @Override
    public List<Map<String, String>> lsrAgrClsCdGrpList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.lsrAgrClsCdGrpList(modelMap);
    }

    /**
     * 보험사 귀중명
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public String getTtlInsrNm(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getTtlInsrNm(modelMap);
    }

    /**
     * 법인명
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public String getCrpNm(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getCrpNm(modelMap);
    }

    /**
     * 전세용 메모분류코드
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getMemoClsCd() throws SQLException {
        return codeDAO.getMemoClsCd();
    }

    /**
     * 대주보 지사 리스트
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getIns32tChrgrList() throws SQLException {
        return codeDAO.getIns32tChrgrList();
    }

    /**
     * 주택도시보증 지사 리스트
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getIns32tChrgrFaxList() throws SQLException {
        return codeDAO.getIns32tChrgrFaxList();
    }

    /**
     * 직업 대분류코드
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobLgrClsCd() throws SQLException {
        return  codeDAO.getJobLgrClsCd();
    }

    /**
     * 직업 중분류코드
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobMidClsCd(Map<String, Object> modelMap) throws SQLException {
        return  codeDAO.getJobMidClsCd(modelMap);
    }

    /**
     * 직업 소분류코드
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobSmlClsCd(Map<String, Object> modelMap) throws SQLException {
        return  codeDAO.getJobSmlClsCd(modelMap);
    }

    /**
     * 직위 소분류코드
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getJobPstnClsCd(Map<String, Object> modelMap) throws SQLException {
        return  codeDAO.getJobPstnClsCd(modelMap);
    }
    @Override
    public String isSaveBtnYn2(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.isSaveBtnYn2(modelMap);
    }

    /**
     * 날짜 포멧이 맞는지 확인
     * @param modelMap
     * @return
     * @throws SQLException
     */
    public int getIsDate(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getIsDate(modelMap);
    }

    /**
     * 전세 녹취분류코드
     */
    public List<Map<String, String>> getJlCalClsCd() throws SQLException {
        return codeDAO.getJlCalClsCd();
    }

    @Override
    public String getSysCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSysCd(modelMap);
    }

    /**
     *유선담당자 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getTellUsrList(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getTellUsrList(modelMap);
    }

    public List<Map<String, String>> getDptList(Map<String, Object> modelMap) throws SQLException{
        return codeDAO.getDptList(modelMap);
    }

    /**
     * 질문지버전
     */
    @Override
    public String getQstVer(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getQstVer(modelMap);
    }

    /**
     * 상품 분류코드
     */
    @Override
    public String getSysClsCd(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSysClsCd(modelMap);
    }

    /**
     * 기관(은행) 리스트 가져오기 리스트용(부서구분)
     */
    @Override
    public List<Map<String, String>> getListHugInstCode(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getListHugInstCode(modelMap);
    }


    /**
     * 기관(은행)에 따른 상품 리스트 가져오기
     * @param instCd
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getHugPrdtCodeByInst(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getHugPrdtCodeByInst(modelMap);
    }

    /**
     * 코드명 가져오기
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getCodeNm(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getCodeNm(modelMap);
    }

    /**
     * 조사지 불가지역 구분값 가져오기
     * @param modelMap
     * @return
     * @throws SQLException
     */
    @Override
    public String getSrvyImpYn(Map<String, Object> modelMap) throws SQLException {
        return codeDAO.getSrvyImpYn(modelMap);
    }

    @Override
    public Map<String, Object> getMenuInfo(String menuCd) throws SQLException {
        return codeDAO.getMenuInfo(menuCd);
    }
}
