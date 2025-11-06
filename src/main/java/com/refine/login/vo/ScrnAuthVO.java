package com.refine.login.vo;

import java.io.Serializable;

public class ScrnAuthVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String scrnId;				//화면아이디
	private String prcsAuth;			//처리권한
	
	public String getScrnId() {
		return scrnId;
	}
	public void setScrnId(String scrnId) {
		this.scrnId = scrnId;
	}
	public String getPrcsAuth() {
		return prcsAuth;
	}
	public void setPrcsAuth(String prcsAuth) {
		this.prcsAuth = prcsAuth;
	}
	@Override
	public String toString() {
		return "ScrnAuthVO [scrnId=" + scrnId + ", prcsAuth=" + prcsAuth + "]";
	}


	
	
	
}
