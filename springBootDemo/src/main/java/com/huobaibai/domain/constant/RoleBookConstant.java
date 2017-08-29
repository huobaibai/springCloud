package com.huobaibai.domain.constant;

public enum RoleBookConstant {

	SHANDONG_ROLEUSER("00","角色用户权限配置收集-山东.xlsx"),
	SHANDONG_ROLEUSER_SHEET1("01","山东机构用户表-机构填写"),
	ZZ_ROLEUSER("00","角色用户权限配置收集-总部及作业中心.xlsx"),
	ZZ_ROLEUSER_SHEET1("01","总公司中心机构用户表-机构填写");
	
	
	private String code;
	private String name;
	
	private RoleBookConstant(String code,String name) {
		this.code=code;
		this.name=name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
}
