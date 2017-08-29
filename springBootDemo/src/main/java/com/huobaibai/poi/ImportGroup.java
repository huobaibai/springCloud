package com.huobaibai.poi;

import java.util.ArrayList;
import java.util.List;

public class ImportGroup {
	private String groupName;
	private String branchCode;
	private String departCode;
	private String daAnLevel;
	private String ycz;
	private List<ImportUser> userList = new ArrayList<ImportUser>();
	private List<ImportTask> taskList = new ArrayList<ImportTask>();
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDaAnLevel() {
		return daAnLevel;
	}
	public void setDaAnLevel(String daAnLevel) {
		this.daAnLevel = daAnLevel;
	}
	public List<ImportUser> getUserList() {
		return userList;
	}
	public void setUserList(List<ImportUser> userList) {
		this.userList = userList;
	}
	public List<ImportTask> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<ImportTask> taskList) {
		this.taskList = taskList;
	}
	public String getYcz() {
		return ycz;
	}
	public void setYcz(String ycz) {
		this.ycz = ycz;
	}
	
	
	
	
}
