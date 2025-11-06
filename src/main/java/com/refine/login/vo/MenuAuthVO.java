package com.refine.login.vo;

import java.io.Serializable;

public class MenuAuthVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String menuId;				//메뉴아이디

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "MenuAuthVO [menuId=" + menuId + "]";
	}	
	
}
