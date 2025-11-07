package com.refine.common.code.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.refine.common.code.service.CodeService;
import com.refine.login.vo.LoginVO;
import com.refine.security.RfSecurity;
import com.refine.util.RefineUtil;

import biz.refine.annotation.RequestAttribute;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class CodeController {
	public static final Logger logger = LoggerFactory.getLogger(CodeController.class);
		
	@Autowired
	protected CodeService codeService;	
		
	/**
	 * 코드리스트 (저당용)
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRfCodeByComClsCd")
	@ResponseBody
	public HashMap<String, Object> getRfCodeByComClsCd(@RequestParam(value = "comClsCd", required = true) String comClsCd,
			@RequestParam(value = "instCd", required = true) String instCd,
			@RequestParam(value = "prdtCd", required = true) String prdtCd,
			Map<String, Object> modelMap) throws Exception {
		
		modelMap.put("instCd", instCd);				
		modelMap.put("prdtCd", prdtCd);	
		modelMap.put("comClsCd", comClsCd);		
		List<Map<String, String>> comClsCdList = codeService.getRfCodeByComClsCd( modelMap);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("comClsCdList", comClsCdList);
		return resultMap;
	}	
	
	/**
	 * 코드리스트 (저당용)
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRltyCd")
	@ResponseBody
	public HashMap<String, Object> getRltyCd(@RequestParam(value = "instCd", required = true) String instCd,
			@RequestParam(value = "prdtCd", required = true) String prdtCd,
			Map<String, Object> modelMap) throws Exception {
		
		modelMap.put("instCd", instCd);				
		modelMap.put("prdtCd", prdtCd);	
		List<Map<String, String>> rltyCdList = codeService.getRltyCd( modelMap);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rltyCdList", rltyCdList);
		return resultMap;
	}		
	
	/**
	 * 코드에 해당하는 공통코드 리스트 가져오기
	 * @param comClsCd 공통분류코드
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRfCodeList")
	@ResponseBody
	public HashMap<String, Object> getRfCodeListJSON(@RequestParam(value="comClsCd", required=true) String comClsCd,
			Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> rfCdList= (List<Map<String, String>>)codeService.getRfCode(comClsCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rfCdList", rfCdList);

		return resultMap;
	}
	
	/**
	 * 코드에 해당하는 공통코드 리스트 가져오기 CHK_CD1 에 구분에 따라서 리스트 데이터를 필터하는 함수
	 * @param comClsCd 공통분류코드
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRfCodeFilterList")
	@ResponseBody
	public HashMap<String, Object> getRfCodeListFilterJSON(@RequestParam(value="comClsCd", required=true) String comClsCd,
			                                    @RequestParam(value="chkCd", required=true) String chkCd,
			                                    Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> rfCdList= (List<Map<String, String>>)codeService.getRfCodeFilter(comClsCd,chkCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rfCdList", rfCdList);

		return resultMap;
	}
	
	/**
	 * 코드에 해당하는 공통코드비고 리스트 가져오기
	 * @param comClsCd 공통분류코드
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRfCodeNoteList")
	@ResponseBody
	public HashMap<String, Object> getRfCode3ListJSON(@RequestParam(value="comClsCd", required=true) String comClsCd,
			Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> rfCdList= (List<Map<String, String>>)codeService.getRfCode3(comClsCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rfCdList", rfCdList);

		return resultMap;
	}	
	
	/**
	 * 기관(은행)에 따른 지점 리스트 가져오기
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getRrnhCdList")
	@ResponseBody
	public HashMap<String, Object> getRrnhCdListJSON(@RequestParam(value="instCd", required=true) String instCd,
			Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> brnhCdList= (List<Map<String, String>>)codeService.getBrnhCodeByInst(instCd);

		String lttrInstCd= codeService.getLttrInstCd(instCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("brnhCdList", brnhCdList);
		resultMap.put("lttrInstCd", lttrInstCd);

		return resultMap;
	}
	
	/**
	 * 기관(은행)에 따른 지점 리스트 가져오기 (체크박스)
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value="/common/code/getBrnhCdListChk")
	@ResponseBody
	public HashMap<String, Object> getBrnhCdListChk(Map<String, Object> modelMap, @RequestAttribute(value="params") String datas) throws Exception {
		Map<String, Object> _map = (Map<String, Object>)(JSONObject.toBean((JSONObject)(JSONSerializer.toJSON(datas)), Map.class));
		
		Iterator<String> iterator = _map.keySet().iterator();
		while (iterator.hasNext()) {
	        String key = (String) iterator.next();        
	        
	        if(key.equals("bankChk")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun","array");
	        	}
	        }
        	if (!"".equals(_map.get(key)))
        		modelMap.put(key, _map.get(key));
	        
	    }
		List<Map<String, String>> brnhCdList= (List<Map<String, String>>)codeService.getBrnhCodeByInst(modelMap);
		//logger.debug("prdtCdList::::::"+prdtCdList);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("brnhCdList", brnhCdList);
		
		return resultMap;
	}
	
	/**
	 * 기관(은행)에 따른 상품 리스트 가져오기
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getPrdtCdList")
	@ResponseBody
	public HashMap<String, Object> getPrdtCdListJSON(@RequestParam(value="instCd", required=true) String instCd,
			Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> prdtCdList= (List<Map<String, String>>)codeService.getPrdtCodeByInst(instCd);
		logger.debug("prdtCdList::::::"+prdtCdList);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prdtCdList", prdtCdList);

		return resultMap;
	}	
	
	/**
	 * 기관(은행)에 따른 상품 리스트 가져오기 (체크박스)
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value="/common/code/{prdtClsCd}/getPrdtCdListChk")
	@ResponseBody
	public HashMap<String, Object> getPrdtCdListChk(@PathVariable String prdtClsCd, Map<String, Object> modelMap, @RequestAttribute(value="params") String datas) throws Exception {
		Map<String, Object> _map = (Map<String, Object>)(JSONObject.toBean((JSONObject)(JSONSerializer.toJSON(datas)), Map.class));
		
		Iterator<String> iterator = _map.keySet().iterator();
		while (iterator.hasNext()) {
	        String key = (String) iterator.next();        
	        
	        if(key.equals("bankChk")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun","array");
	        	}
	        }
	        if(key.equals("dptCd")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun3","array");
	        	}
	        }
        	if (!"".equals(_map.get(key)))
        		modelMap.put(key, _map.get(key));
	        
	    }
		modelMap.put("prdtClsCd",prdtClsCd);
		modelMap.put("bankChk32", _map.get("bankChk32"));
		modelMap.put("wherecall", _map.get("wherecall"));
		List<Map<String, String>> prdtCdList= (List<Map<String, String>>)codeService.getPrdtCodeByInst(modelMap);
		//logger.debug("prdtCdList::::::"+prdtCdList);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prdtCdList", prdtCdList);
		
		return resultMap;
	}	
	
	/**
	 * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사)에 따른 조사의견 리스트 가져오기
	 * @param instCd 지점(은행) 코드
	 * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getSrvyOpinList")
	@ResponseBody
	public HashMap<String, Object> getSrvyOpinListJSON(@RequestParam(value="instCd", required=true) String instCd,
			@RequestParam(value="srvyOpinTpCd", required=true) String srvyOpinTpCd,
			Map<String, Object> modelMap) throws Exception {
		
		List<Map<String, String>> srvyOpinList= (List<Map<String, String>>)codeService.getSrvyOpinByInst(instCd, srvyOpinTpCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("srvyOpinList", srvyOpinList);

		return resultMap;
	}		
	
	/**
	 * 기관, 조사의견유형(권원조사보고서,권원확인서,전화조사), 조사의견에 따른 조사의견내용 가져오기
	 * @param instCd 지점(은행) 코드
	 * @param srvyOpinTpCd 조사의견유형코드(01:권원조사보고서, 02:권원확인서, 03:전화조사)
	 * @param srvyOpinCd 조사의견코드
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getSrvyOpinCnts")
	@ResponseBody
	public HashMap<String, Object> getSrvyOpinCntsJSON(@RequestParam(value="instCd", required=true) String instCd,
			@RequestParam(value="srvyOpinTpCd", required=true) String srvyOpinTpCd,
			@RequestParam(value="srvyOpinCd", required=true) String srvyOpinCd,
			Map<String, Object> modelMap) throws Exception {
		
		String svryOpinCnts= codeService.getSrvyOpinCnts(instCd, srvyOpinTpCd, srvyOpinCd);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("svryOpinCnts", svryOpinCnts);

		return resultMap;
	}	
	
	/**
	 * 조사업체 리스트
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getSrvyFrmList")
	@ResponseBody
	public HashMap<String, Object> getSrvyFrmListJSON(@RequestParam(value="sysClsCd", required=true) String sysClsCd,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("sysClsCd",sysClsCd);
		
		List<Map<String, String>> srvyFrmList= (List<Map<String, String>>)codeService.getSrvyFrmList(modelMap);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("srvyFrmList", srvyFrmList);
		return resultMap;
	}
	
	/**
	 * 인터넷전세 - 조사업체 리스트
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getIlSrvyFrmList")
	@ResponseBody
	public HashMap<String, Object> getIlSrvyFrmList(Map<String, Object> modelMap) throws Exception {		
		List<Map<String, String>> srvyFrmList= (List<Map<String, String>>)codeService.getIlSrvyFrmList();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("srvyFrmList", srvyFrmList);
		return resultMap;
	}
	
	/**
	 * 조사업체 All 리스트
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getSrvyFrmListAll")
	@ResponseBody
	public HashMap<String, Object> getSrvyFrmListAllJSON(@RequestParam(value="sysClsCd", required=true) String sysClsCd,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("sysClsCd",sysClsCd);
		
		List<Map<String, String>> srvyFrmList= (List<Map<String, String>>)codeService.getSrvyFrmListAll(modelMap);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("srvyFrmList", srvyFrmList);
		return resultMap;
	}	
	
	/**
	 * qry RfSecurity.RfEncode(qry)
	 * key RfSecurity.RfEncode(RefineUtil.getCurrYYYYMMDDHH24MISS() + "|" + loginId + "|" + flCtrlNo)
	 * 
	 * @param flCtrlNo
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getDocImgSaveQry")
	@ResponseBody
	public HashMap<String, Object> getDocImgSaveQry(@RequestParam(value="flCtrlNo", required=true) String flCtrlNo,
			Map<String, Object> modelMap, HttpSession session) throws Exception {
		
		//파라미터
		modelMap.put("flCtrlNo", flCtrlNo);
		String loginId = ((LoginVO)session.getAttribute("USER")).getUsrId();
		modelMap.put("userId", loginId); //등록자아이디
		
		String qry= codeService.getDocImgSaveQry(modelMap);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		qry = RfSecurity.RfEncode(qry);
		String key = RfSecurity.RfEncode(RefineUtil.getCurrYYYYMMDDHH24MISS() + "|" + loginId + "|" + flCtrlNo);

		resultMap.put("qry", URLEncoder.encode(qry, "utf-8"));
		resultMap.put("key", URLEncoder.encode(key, "utf-8"));

		return resultMap;
	}	
	
	/**
	 * 직업대분류
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getJobLgrClsCd")
	@ResponseBody
	public HashMap<String, Object> getJobLgrClsCd(Map<String, Object> modelMap) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> jobLgrClsCdList= (List<Map<String, String>>)codeService.getJobLgrClsCd();		
		resultMap.put("jobLgrClsCdList", jobLgrClsCdList);
		return resultMap;
	}
	
	/**
	 * 직업중분류
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getJobMidClsCd")
	@ResponseBody
	public HashMap<String, Object> getJobMidClsCd(@RequestParam(value="jobLgrClsCd", required=true) String jobLgrClsCd,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("jobLgrClsCd", jobLgrClsCd);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> jobMidClsCdList= (List<Map<String, String>>)codeService.getJobMidClsCd(modelMap);
		resultMap.put("jobMidClsCdList", jobMidClsCdList);
		return resultMap;
	}	
	
	/**
	 * 직업소분류
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getJobSmlClsCd")
	@ResponseBody
	public HashMap<String, Object> getJobSmlClsCd(@RequestParam(value="jobLgrClsCd", required=true) String jobLgrClsCd,
			@RequestParam(value="jobMidClsCd", required=true) String jobMidClsCd,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("jobLgrClsCd", jobLgrClsCd);
		modelMap.put("jobMidClsCd", jobMidClsCd);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> jobSmlClsCdList= (List<Map<String, String>>)codeService.getJobSmlClsCd(modelMap);
		resultMap.put("jobSmlClsCdList", jobSmlClsCdList);
		return resultMap;
	}		
	
	/**
	 * 직위 분류
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getJobPstnClsCd")
	@ResponseBody
	public HashMap<String, Object> getJobPsntClsCd(@RequestParam(value="jobLgrClsCd", required=true) String jobLgrClsCd,
			@RequestParam(value="jobMidClsCd", required=true) String jobMidClsCd,
			@RequestParam(value="jobSmlClsCd", required=true) String jobSmlClsCd,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("jobLgrClsCd", jobLgrClsCd);
		modelMap.put("jobMidClsCd", jobMidClsCd);
		modelMap.put("jobSmlClsCd", jobSmlClsCd);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> jobPstnClsCdList= (List<Map<String, String>>)codeService.getJobPstnClsCd(modelMap);
		resultMap.put("jobPstnClsCdList", jobPstnClsCdList);
		return resultMap;
	}			
	
	/**
	 * 날짜 포멧이 맞는지 확인
	 * @param dt 날짜
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getIsDate")
	@ResponseBody
	public HashMap<String, Object> getIsDateJSON(@RequestParam(value="dt", required=true) String dt,
			@RequestParam(value="dt2", required=true) String dt2,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("dt", dt);
		int i = 1;
		int i2 = 1;
		if(!"".equals(dt)){
			i = codeService.getIsDate(modelMap);
		}
		if(!"".equals(dt2)){
			modelMap.put("dt", dt2);	
			i2 = codeService.getIsDate(modelMap);
		}				

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(1 == i && 1 == i2){
			resultMap.put("result", "Y"); //날짜포멧 맞음
		}else{
			resultMap.put("result", "N");
		}
		return resultMap;
	}
	
	/**
	 *  유선담당자 가져오기
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value="/common/code/getTellUsrList")
	@ResponseBody
	public HashMap<String, Object> getTellUsrList(Map<String, Object> modelMap, @RequestAttribute(value="params") String datas) throws Exception {
		Map<String, Object> _map = (Map<String, Object>)(JSONObject.toBean((JSONObject)(JSONSerializer.toJSON(datas)), Map.class));
		
		Iterator<String> iterator = _map.keySet().iterator();
		while (iterator.hasNext()) {
	        String key = (String) iterator.next();        
	        
	        if(key.equals("tellPart")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun","array");
	        	}
	        }
        	if (!"".equals(_map.get(key)))
        		modelMap.put(key, _map.get(key));	        		        
	    }
		
		
		List<Map<String, String>> tellUsrList = (List<Map<String, String>>)codeService.getTellUsrList(modelMap);
		logger.debug("tellUsrList::::::"+tellUsrList);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("tellUsrList", tellUsrList);
		
		return resultMap;
	}
	/**
	 * 날짜 포멧이 맞는지 확인
	 * @param dt 날짜
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/common/code/getIsDate2")
	@ResponseBody
	public HashMap<String, Object> getIsDate2JSON(@RequestParam(value="dt", required=true) String dt,
			Map<String, Object> modelMap) throws Exception {
		modelMap.put("dt", dt);
		int i = 1;
		if(!"".equals(dt)){
			i = codeService.getIsDate(modelMap);
		}

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(1 == i){
			resultMap.put("result", "Y"); //날짜포멧 맞음
		}else{
			resultMap.put("result", "N");
		}
		return resultMap;
	}		
	
	
	/**
	 * 기관(은행)에 따른 상품 리스트 가져오기 (체크박스)
	 * @param instCd
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value="/common/code/{prdtClsCd}/getHugPrdtCdListChk")
	@ResponseBody
	public HashMap<String, Object> getHugPrdtCdListChk(@PathVariable String prdtClsCd, Map<String, Object> modelMap, @RequestAttribute(value="params") String datas) throws Exception {
		Map<String, Object> _map = (Map<String, Object>)(JSONObject.toBean((JSONObject)(JSONSerializer.toJSON(datas)), Map.class));
		
		Iterator<String> iterator = _map.keySet().iterator();
		while (iterator.hasNext()) {
	        String key = (String) iterator.next();        
	        
	        if(key.equals("bankChk")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun","array");
	        	}
	        }
	        if(key.equals("dptCd")){
	        	Object obj =  _map.get(key);
	    		if( obj instanceof ArrayList ){ //라디오, 체크박스일 경우
	    			modelMap.put("gubun3","array");
	        	}
	        }
        	if (!"".equals(_map.get(key)))
        		modelMap.put(key, _map.get(key));
	        
	    }
		modelMap.put("prdtClsCd",prdtClsCd);
		modelMap.put("bankChk32", _map.get("bankChk32"));
		modelMap.put("wherecall", _map.get("wherecall"));
		List<Map<String, String>> prdtCdList= (List<Map<String, String>>)codeService.getHugPrdtCodeByInst(modelMap);
		//logger.debug("prdtCdList::::::"+prdtCdList);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prdtCdList", prdtCdList);
		
		return resultMap;
	}	
	
	
}
