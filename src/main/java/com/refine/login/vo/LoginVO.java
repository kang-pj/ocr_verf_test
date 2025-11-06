package com.refine.login.vo;

public class LoginVO implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 10345760586501L;

	private String usrId;				//사용자아이디
	private String menuAuthCd;			//메뉴권한코드
	private String scrnAuthCd;			//화면권한코드
	private String usrNm;				//사용자명
	private String pwd;				//비밀번호
	private String useYn;				//사용여부
	private String inPhNo;				//내선번호
	private String dptCd;				//부서 코드
	private String dtyCd;				//직급코드
	private String sysAuthCnts;		//시스템 구분
	private String altnClsCd;//배정분류코드

	private String telNo;//전화번호
	private String faxNo;//팩스번호
	private String empNo;
	private String pwChgTm;	//비밀번호 변경일
	private String sessionkey;
	private String refreshkey;
	private String redirecturl;
	private String rfLtabNum;
	private String newSiteYn;
	private String authCode;

    private String roles;

	private String mltLoginYn;

	private String bfPwd;
	private String curPw;

    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

	public String getRedirecturl() {
		return redirecturl;
	}
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public String getRefreshkey() {
		return refreshkey;
	}
	public void setRefreshkey(String refreshkey) {
		this.refreshkey = refreshkey;
	}
	public String getDtyCd() {
		return dtyCd;
	}
	public void setDtyCd(String dtyCd) {
		this.dtyCd = dtyCd;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getAltnClsCd() {
		return altnClsCd;
	}
	public void setAltnClsCd(String altnClsCd) {
		this.altnClsCd = altnClsCd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSysAuthCnts() {
		return sysAuthCnts;
	}
	public void setSysAuthCnts(String sysAuthCnts) {
		this.sysAuthCnts = sysAuthCnts;
	}
	public String getInPhNo() {
		return inPhNo;
	}
	public void setInPhNo(String inPhNo) {
		this.inPhNo = inPhNo;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getMenuAuthCd() {
		return menuAuthCd;
	}
	public void setMenuAuthCd(String menuAuthCd) {
		this.menuAuthCd = menuAuthCd;
	}
	public String getScrnAuthCd() {
		return scrnAuthCd;
	}
	public void setScrnAuthCd(String scrnAuthCd) {
		this.scrnAuthCd = scrnAuthCd;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDptCd() {
		return dptCd;
	}
	public void setDptCd(String dptCd) {
		this.dptCd = dptCd;
	}
	public String getPwChgTm() {
		return pwChgTm;
	}
	public void setPwChgTm(String pwChgTm) {
		this.pwChgTm = pwChgTm;
	}

	public String getRfLtabNum() {
		return rfLtabNum;
	}

	public void setRfLtabNum(String rfLtabNum) {
		this.rfLtabNum = rfLtabNum;
	}

	public String getNewSiteYn() {
		return newSiteYn;
	}

	public void setNewSiteYn(String newSiteYn) {
		this.newSiteYn = newSiteYn;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getMltLoginYn() {
		return mltLoginYn;
	}

	public void setMltLoginYn(String mltLoginYn) {
		this.mltLoginYn = mltLoginYn;
	}

	public String getBfPwd() {
		return bfPwd;
	}

	public void setBfPwd(String bfPwd) {
		this.bfPwd = bfPwd;
	}

	public String getCurPw() {
		return curPw;
	}

	public void setCurPw(String curPw) {
		this.curPw = curPw;
	}

	@Override
	public String toString() {
		return "LoginVO{" +
				"usrId='" + usrId + '\'' +
				", menuAuthCd='" + menuAuthCd + '\'' +
				", scrnAuthCd='" + scrnAuthCd + '\'' +
				", usrNm='" + usrNm + '\'' +
				", pwd='" + pwd + '\'' +
				", useYn='" + useYn + '\'' +
				", inPhNo='" + inPhNo + '\'' +
				", dptCd='" + dptCd + '\'' +
				", dtyCd='" + dtyCd + '\'' +
				", sysAuthCnts='" + sysAuthCnts + '\'' +
				", altnClsCd='" + altnClsCd + '\'' +
				", telNo='" + telNo + '\'' +
				", faxNo='" + faxNo + '\'' +
				", empNo='" + empNo + '\'' +
				", pwChgTm='" + pwChgTm + '\'' +
				", sessionkey='" + sessionkey + '\'' +
				", refreshkey='" + refreshkey + '\'' +
				", redirecturl='" + redirecturl + '\'' +
				", rfLtabNum='" + rfLtabNum + '\'' +
				", newSiteYn='" + newSiteYn + '\'' +
				", authCode='" + authCode + '\'' +
				", roles='" + roles + '\'' +
				", mltLoginYn='" + mltLoginYn + '\'' +
				'}';
	}
}
