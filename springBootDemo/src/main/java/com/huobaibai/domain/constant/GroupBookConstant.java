package com.huobaibai.domain.constant;

public enum GroupBookConstant {
	/**
	 * 山东用户组
	 */
	SHANDONG("SD","山东分公司用户分组信息表.xlsx"),
	SHANDONG_SHEET1("01","（2）机构填写 -山东"),
	/**
	 * 作业中心与总公司用户组
	 */
	ZZZX("ZZ","用户分组信息-总公司&作业中心.xls"),
	ZZZX_SHEET1("01","机构填写 - 总公司+ 作业中心");
	
	
	private String code;
	private String name;
	
	private GroupBookConstant(String code,String name) {
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
