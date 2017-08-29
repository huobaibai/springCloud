package com.huobaibai.poi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huobaibai.domain.constant.GroupBookConstant;
import com.huobaibai.domain.constant.RoleBookConstant;

public class ImportGroupTemplet extends Base {		

	Logger logger = LoggerFactory.getLogger(ImportGroupTemplet.class);
	private final static String FJ_GROUP_TABNAME_COM = "10_分公司用户组下人员表";
	private final static String FJ_COMMONUSER_TABNAME = "11_分公司用户表";
	// private final static String FJ_COMBINE_GROUP_TABNAME = "12_合并组";
	// private final static String FJ_REGIONINFO_TABNAME = "13_片区";
	private final static int START_LINE = 9;
	private final static String WORK_PATH="C:\\projects\\AC-1205\\作业环境\\";//数据源环境
	private final static String WORK_OUTTER_PATH="C:\\projects\\AC-1205\\";//输出环境
	private final static String 工作组及人员_TEMP = WORK_PATH +"工作组及人员_template.xlsx";//工作组模板路径
	private final static String GROUPBOOK = WORK_PATH + GroupBookConstant.SHANDONG.getName();//工作组数据源
	private final static String USERBOOK = WORK_PATH +  RoleBookConstant.SHANDONG_ROLEUSER.getName();//角色数据源
	private final static String 工作组及人员 = WORK_OUTTER_PATH+"工作组及人员.xlsx";//输出文件
	
	private final static String GROUPBOOK_ORIGIN_DATA_NAME = GroupBookConstant.SHANDONG_SHEET1.getName();//工作组数据源sheet1
	private final static String ROLE_TEMP_SHEET1_NAME = RoleBookConstant.SHANDONG_ROLEUSER_SHEET1.getName();//角色数据源sheet1

	//@Test
	public void execute() {
		Workbook 工作组及人员_Book = this.getWorkBook(工作组及人员_TEMP);
		Workbook dataWorkbook = this.getWorkBook(GROUPBOOK);
		Workbook userBook = this.getWorkBook(USERBOOK);
		this.fillGroupData(工作组及人员_Book, dataWorkbook);
		this.fillUserData(工作组及人员_Book, userBook);
		this.saveAsFile(工作组及人员_Book, 工作组及人员);
	}

	
	
	
	private void fillGroupData(Workbook tmpBook, Workbook databook) {
		Sheet groupSheet = tmpBook.getSheet(FJ_GROUP_TABNAME_COM);
		Sheet dataSheet = databook.getSheet(GROUPBOOK_ORIGIN_DATA_NAME);
		int dataRow = 3;
		int lastRow = dataSheet.getLastRowNum();
		int index = START_LINE;
		
//		Set<String> set = new HashSet<String>();
		Map<String, ImportGroup> groupMap = new HashMap<String, ImportGroup>();
		// 把工作组归集
		for (int i = dataRow; i <= lastRow; i++) {
			// 用户组名称
			String userGroupName = this.getCellValue(dataSheet.getRow(i).getCell(8));
			if (StringUtils.isBlank(userGroupName)) {
				continue;
			}
			if (!groupMap.containsKey(userGroupName)) {
				ImportGroup group = new ImportGroup();
				group.setGroupName(userGroupName);
				// 分公司code
				String branchCode = this.getCellValue(dataSheet.getRow(i).getCell(3));
				group.setBranchCode(branchCode);
				// 部门组code
				String departCode = branchCode + this.getCellValue(dataSheet.getRow(i).getCell(5));
				group.setDepartCode(departCode);
				// 大案审核级别
				String daAnLevel = this.getCellValue(dataSheet.getRow(i).getCell(15));
				group.setDaAnLevel(daAnLevel);
				groupMap.put(userGroupName, group);
			}
		}

		fillTaskTypeToGroup(dataSheet, dataRow, lastRow, groupMap);
		
		fillUserToGroup(dataSheet, dataRow, lastRow, groupMap);

		// 遍历group
		int count = 0;
		for(Entry<String, ImportGroup> set:groupMap.entrySet()) {
			//设置GROUP
			count = 0;//初始化新插入组人员设置的行标志
			ImportGroup group =set.getValue();
			Row row = groupSheet.createRow(index);
			String userGroupName = group.getGroupName();
			String compCode = group.getBranchCode();
			String departCode = group.getDepartCode();
			String dashLevel = group.getDaAnLevel();
			String ycGroup = group.getYcz();
			row.createCell(1).setCellValue(userGroupName);
			
			//设置工作组对应任务类型
			List<ImportTask> taskList = group.getTaskList();
			String taskStr = "";
			for (ImportTask each : taskList) {
				String taskCode = each.getCode();
				if (StringUtils.isBlank(taskCode)) {
					logger.error("Tasktype isnull:[{}]", each.getName());
					continue;
				}
				taskStr += each.getCode() + "\r\n";
			}
			 row.createCell(2).setCellValue(taskStr);
			 row.createCell(3).setCellValue(compCode);
			 row.createCell(5).setCellValue(departCode);
			 row.createCell(12).setCellValue(dashLevel);
			 row.createCell(17).setCellValue(ycGroup);
			
			 //设置工作组人员
			 List<ImportUser> users = group.getUserList();
			 for (ImportUser user : users) {
			 String userCode = user.getCode();
			 String userName = user.getName();
			 String notTZ = user.getFzyLeader();
			 String TZ = user.getZyLeader();
			 if (count > 0) {//第一次新插工作组已有所以跳过
				 row = groupSheet.createRow(index);
			 }
			 row.createCell(13).setCellValue(userCode);
			 row.createCell(14).setCellValue(userName);
			 row.createCell(15).setCellValue(notTZ);
			 row.createCell(16).setCellValue(TZ);
			 index++;
			 count++;
			 }
			}
//			 for (String earch : set) {
//			 System.out.println(earch);
//		     }
	}
/**
 * 
 * @param dataSheet
 * @param dataRow
 * @param lastRow
 * @param groupMap
 */
	public void fillTaskTypeToGroup(Sheet dataSheet, int dataRow, int lastRow, Map<String, ImportGroup> groupMap) {
		/*
		 * 任务类型与工作组匹配 工作组 ->任务类型List 工作组 与任务类型关系 1：N
		 */
		String taskType_back = "";
		for (int i = dataRow; i <= lastRow; i++) {
			// 用户组名称
			String userGroupName = this.getCellValue(dataSheet.getRow(i).getCell(8));
			if (StringUtils.isBlank(userGroupName)) {
				continue;
			}
			// 任务类型
			String taskType = this.getCellValue(dataSheet.getRow(i).getCell(2));

			if (!StringUtils.isBlank(taskType)) {
				taskType_back = taskType;
			}

			if (StringUtils.isNotBlank(taskType_back)) {
				//拆分下
//				logger.info("工作组【{}】：主任务类型[{}]",userGroupName,taskType_back);
				String[] taskType_backs=taskType_back.split("\n");
				
				for(String ts:taskType_backs) {
					ImportTask task = new ImportTask();
//					task.setName(taskType_back);
					//logger.info("工作组【{}】：主任务类型[{}]",userGroupName,ts);
					task.setName(ts);
					if (!this.existTask(groupMap.get(userGroupName).getTaskList(), task)) {
						groupMap.get(userGroupName).getTaskList().add(task);
					}
				}
			}
		}
	}

	/**
	 * 人员与工作组匹配 工作组 ->人员List
	 * 
	 */
	public void fillUserToGroup(Sheet dataSheet, int dataRow, int lastRow,
			Map<String, ImportGroup> groupMap) {

		for (int i = dataRow; i <= lastRow; i++) {
			// 用户组名称
			String userGroupName = this.getCellValue(dataSheet.getRow(i).getCell(8));
			if (StringUtils.isBlank(userGroupName)) {
				continue;
			}

			// 用户Code
			String userCode = this.getCellValue(dataSheet.getRow(i).getCell(14));
			// 分公司code
			String branchCode = this.getCellValue(dataSheet.getRow(i).getCell(3));
			// 用户名称
			String userName = this.getCellValue(dataSheet.getRow(i).getCell(13));

			//// List<?> userIds = userDao.findLoginUserInfo(branchCode, userCode);
			// List<?> userIds = userDao.findLoginUserInfo(userCode);
			// if (userIds.isEmpty()) {
			// 	logger.info("找不到用户{},分公司code:{},用户code:{}", userName , branchCode, userCode);
			// }
			ImportUser user = new ImportUser();
			// 非作业团队长
			String notTZ = this.getCellValue(dataSheet.getRow(i).getCell(11));
			// 作业团队长
			String TZ = this.getCellValue(dataSheet.getRow(i).getCell(12));
			user.setCode(userCode);
			user.setName(userName);
			user.setZyLeader(TZ);
			user.setFzyLeader(notTZ);
			groupMap.get(userGroupName).getUserList().add(user);

		}
	}

	/**
	 * 工作组sheet2 分公司用户表
	 * 
	 * @param tmpBook
	 * @param databook
	 */
	private void fillUserData(Workbook tmpBook, Workbook databook) {
		Sheet groupSheet = tmpBook.getSheet(FJ_COMMONUSER_TABNAME);
		Sheet dataSheet = databook.getSheet(ROLE_TEMP_SHEET1_NAME);

		int dataRow = 2;
		int lastRow = dataSheet.getLastRowNum();
		int index = START_LINE;

//		Set<String> set = new HashSet<String>();
		for (int i = dataRow; i <= lastRow; i++) {
			Row row = groupSheet.createRow(index);
			// 用户名称
			String userName = this.getCellValue(dataSheet.getRow(i).getCell(1));

			// 用户代码
			String userCode = this.getCellValue(dataSheet.getRow(i).getCell(2));

			// P13帐户
			String P13 = this.getCellValue(dataSheet.getRow(i).getCell(3));

			// 作业分公司名称
			String compName = this.getCellValue(dataSheet.getRow(i).getCell(5));

			// 作业分公司代码
			String compCode = this.getCellValue(dataSheet.getRow(i).getCell(4));
			//
			// List<?> list = this.userDao.findLoginUserInfo(compCode, userCode);
			// if (list.isEmpty()) {
			// logger.info("找不到用户{},分公司code:{},用户code:{}", userName , branchCode, userCode);
			//// continue;
			// }

			row.createCell(2).setCellValue(userName);
			row.createCell(3).setCellValue(userCode);
			row.createCell(4).setCellValue(P13);
			row.createCell(6).setCellValue(compName);
			row.createCell(5).setCellValue(compCode);

			// 作业二级代码
			String ejCode = this.getCellValue(dataSheet.getRow(i).getCell(6));
			row.createCell(7).setCellValue(ejCode);

			// 作业二级名称
			String ejName = this.getCellValue(dataSheet.getRow(i).getCell(7));
			row.createCell(8).setCellValue(ejName);

			// 作业三级代码
			// String sjCode =
			// this.getCellValue(dataSheet.getRow(i).getCell(8));
			// row.createCell(9).setCellValue(sjCode);
			//
			// // 作业三级名称
			// String sjName =
			// this.getCellValue(dataSheet.getRow(i).getCell(9));
			// row.createCell(10).setCellValue(sjName);

			// 职务
			String zw = this.getCellValue(dataSheet.getRow(i).getCell(10));
			row.createCell(11).setCellValue(zw);

			// 电话
			String tel = this.getCellValue(dataSheet.getRow(i).getCell(11));
			row.createCell(12).setCellValue(tel);

			// 片区
			String area = this.getCellValue(dataSheet.getRow(i).getCell(12));
			row.createCell(13).setCellValue(area);

			// 现场查勘
			String xckc = this.getCellValue(dataSheet.getRow(i).getCell(14));
			row.createCell(14).setCellValue(xckc);

			// 核损审核权限
			String hsshqx = this.getCellValue(dataSheet.getRow(i).getCell(15));
			hsshqx = StringUtils.replace(hsshqx, "级", "");
			hsshqx = StringUtils.replace(hsshqx, "人伤核损", "21,");
			hsshqx = StringUtils.replace(hsshqx, "人伤跟踪审核", "22,");
			hsshqx = StringUtils.replace(hsshqx, "人伤调解审核", "23,");
			hsshqx = StringUtils.replace(hsshqx, "车物核损", "1,");
			row.createCell(15).setCellValue(hsshqx);
			// 核赔审核权限
			String hpshqx = this.getCellValue(dataSheet.getRow(i).getCell(17));
			hpshqx = StringUtils.replace(hpshqx, "级", "");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限普通", "3,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限拒赔", "4,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限诉讼", "5,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限追偿", "6,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限先行赔付", "7,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限强制支付", "9,");
			hpshqx = StringUtils.replace(hpshqx, "核赔审核权限强制垫付", "10,");
			//20170808 add 
			hpshqx = StringUtils.replace(hpshqx, "普通核赔", "3,");
			hpshqx = StringUtils.replace(hpshqx, "拒赔核赔", "4,");
			hpshqx = StringUtils.replace(hpshqx, "追偿核赔", "6,");
			hpshqx = StringUtils.replace(hpshqx, "先行赔付核赔", "7,");
			hpshqx = StringUtils.replace(hpshqx, "强制支付", "9,");
			hpshqx = StringUtils.replace(hpshqx, "强制垫付", "10,");
			
			
			
			
			row.createCell(16).setCellValue(hpshqx);

			// 大案审核权限
			String dashqx = this.getCellValue(dataSheet.getRow(i).getCell(18));
			if (StringUtils.isNotBlank(dashqx)) {
				row.createCell(17).setCellValue("1");
			}

			// 拒赔自动审核
			String jpzdsh = this.getCellValue(dataSheet.getRow(i).getCell(19));
			row.createCell(18).setCellValue(jpzdsh);

			// 追偿自动审核
			String zczdsh = this.getCellValue(dataSheet.getRow(i).getCell(20));
			row.createCell(19).setCellValue(zczdsh);

			// 合议自动审核
			// TODO

			// 查勘估损自动审核
			String ckgszdsh = this.getCellValue(dataSheet.getRow(i).getCell(21));
			row.createCell(21).setCellValue(ckgszdsh);

			// 案卷视图调查节点权限
			String an1 = this.getCellValue(dataSheet.getRow(i).getCell(22));
			row.createCell(22).setCellValue(an1);
			// 影像调查目录权限
			String y1 = this.getCellValue(dataSheet.getRow(i).getCell(23));
			row.createCell(24).setCellValue(y1);

			// 身份证
			String idCard = this.getCellValue(dataSheet.getRow(i).getCell(13));
			row.createCell(29).setCellValue(idCard);

			// 车物核价权限
			String cwhj = this.getCellValue(dataSheet.getRow(i).getCell(16));
			cwhj = StringUtils.replace(cwhj, "级", "");
			row.createCell(30).setCellValue(cwhj);

			index++;
		}

//		for (String earch : set) {
//			// System.out.println(earch);
//			logger.info(earch);
//		}
	}

	/**
	 * 判断工作组对应任务类型是否添加过
	 * @param list
	 * @param task
	 * @return
	 */
	private boolean existTask(List<ImportTask> list, ImportTask task) {
		if (list != null && list.size() > 0) {
			for (ImportTask each : list) {
				if (each.getName().equals(task.getName())) {
					return true;
				}
			}
		}

		return false;
	}

}
