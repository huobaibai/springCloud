package com.huobaibai.poi;

import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huobaibai.poi.Base;


public class ImportRoleTemplet extends Base {
	
Logger logger= LoggerFactory.getLogger(ImportRoleTemplet.class);
//	private final static String FJ_GROUP_TABNAME_COM = "10_分公司用户组下人员表";
//	private final static String FJ_COMMONUSER_TABNAME = "11_分公司用户表";
////	private final static String FJ_COMBINE_GROUP_TABNAME = "12_合并组";
////	private final static String FJ_REGIONINFO_TABNAME = "13_片区";

	private final static int START_LINE = 9;
	
	private final static String 角色权限配置_TEMP = "C:\\projects\\AC-1205\\作业环境\\角色权限配置_template.xlsx";
	private final static String USERBOOK = "C:\\projects\\AC-1205\\作业环境\\角色用户权限配置收集-总部及作业中心.xlsx";
	private final static String 角色权限配置 = "C:\\projects\\AC-1205\\角色权限配置-总部及作业中心.xlsx";
	
//	private final static String ROLE_SHEET2_NAME="山东机构员工角色-机构填写";
	private final static String ROLE_ORIGIN_SHEET2_NAME="总公司作业中心机构员工角色-机构填写";
	
	private final static String ROLE_TEMP_SHEET2_NAME="XX机构员工角色";

	public void execute() {
		// template
		Workbook 角色权限配置_Book = this.getWorkBook(角色权限配置_TEMP);
		Workbook userBook = this.getWorkBook(USERBOOK);

		
		this.fillRoleData(角色权限配置_Book, userBook);
		this.saveAsFile(角色权限配置_Book, 角色权限配置);
	}
	
	private void fillRoleData(Workbook tmpBook, Workbook databook) {
		Sheet tmpSheet = tmpBook.getSheet(ROLE_TEMP_SHEET2_NAME);
//		Sheet dataSheet = databook.getSheet("山东机构员工角色-机构填写");
		Sheet dataSheet = databook.getSheet(ROLE_ORIGIN_SHEET2_NAME);
		int lastRow = dataSheet.getLastRowNum();
		int index = START_LINE;
		Set<String> set = new HashSet<String>();
		for (int i = index; i <= lastRow; i++) {
			Row dataRow = dataSheet.getRow(i);
			// 分公司代码
			String branchCode = this.getCellValue(dataRow.getCell(2));
			// 分公司名称
			String branchName = this.getCellValue(dataRow.getCell(3));
			// 用户代码
			String userCode = this.getCellValue(dataRow.getCell(4));
			// 用户名
			String userName = this.getCellValue(dataRow.getCell(5));
////			List<?> list = userDao.findLoginUserInfo(branchCode, userCode);
//			List<?> list = userDao.findLoginUserInfo(userCode);
//			if(list.isEmpty()) {
//				set.add("找不到用户" + userName + "分公司code:"+ branchCode + "用户code:" + userCode);
//				continue;
//			}
			

			Row tempRow = tmpSheet.createRow(index);

			tempRow.createCell(0).setCellValue("是");
			
			tempRow.createCell(1).setCellValue(branchCode);
			tempRow.createCell(2).setCellValue(branchName);
			tempRow.createCell(3).setCellValue(userCode);
			tempRow.createCell(4).setCellValue(userName);
			// 角色代码
			String roleCode = this.getCellValue(dataRow.getCell(6));
			tempRow.createCell(5).setCellValue(roleCode);
			// 角色名称
			String roleName = this.getCellValue(dataRow.getCell(7));
			tempRow.createCell(6).setCellValue(roleName);
			index++;
			
		}
		for(String earch : set) {
			logger.info(earch);
		}
		
	}

	
	
}
