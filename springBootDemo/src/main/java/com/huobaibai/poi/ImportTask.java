package com.huobaibai.poi;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


/**
 * select * from code_entry where category ='FromTaskType' and property2<>'非任务类型';
 * @author Jian.Yang
 *
 */
public class ImportTask {
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		String code = mapper.get(this.name);
		code = StringUtils.replace(code, "task_", "");
		return code;
	}

	public static HashMap<String, String> mapper = new HashMap<String, String>();;
	
	static {
		mapper.put("预估调整审核","task_assessmentFeeInAdvanceAudit");
		mapper.put("重开审核","task_compensationReopenAudit");
		mapper.put("通赔交互","task_remoteClaimExplain");
		mapper.put("退票审核","task_payBackAudit");
		mapper.put("退票","task_payBack");
		mapper.put("追加索赔申请","task_compensationApply");
		mapper.put("补赔核实","task_compensationAudit");
		mapper.put("追偿跟踪","task_recoveryTrack");
		mapper.put("追偿申请确认","task_recoveryApplyConfirm");
		mapper.put("追偿申请","task_recoveryApply");
		mapper.put("追偿审核","task_recoveryAudit");
		mapper.put("车预核损","task_vehiclePreLossAudit");
		mapper.put("车核损","task_vehicleLossAudit");
		mapper.put("核价","task_vehiclePriceAudit");
		mapper.put("车定损","task_vehicleAssessment");
		mapper.put("车复勘配件报告","task_repairedCheckAssessment");
		mapper.put("车复勘现场核损","task_repairedCheckLossAudit");
		mapper.put("费用调整","task_expenses");
		mapper.put("费用申请","task_expensesApply");
		mapper.put("费用理赔申请","task_expensesAdditional");
		mapper.put("费用审核","task_expensesAudit");
		mapper.put("调查申请审核","task_investApplyAudit");
		mapper.put("调查申请（自动）","task_investigationApply");
		mapper.put("调查结果审核","task_investigationAudit");
		mapper.put("调查","task_investigation");
		mapper.put("诉讼跟踪","task_lawsuitTrack");
		mapper.put("复勘配件","task_lawsuitApplyAudit");
		mapper.put("诉讼申请","task_lawsuitApply");
		mapper.put("诉讼方案申请","task_lawsuitSolutionApply");
		mapper.put("诉讼方案审核","task_lawsuitSolutionAudit");
		mapper.put("诉讼任务","task_lawsuit");
		mapper.put("人工结案","task_claimSettle");
		mapper.put("疑异申请","task_objectionApply");
		mapper.put("理算","task_adjust");
		mapper.put("物预核损","task_itemPreLossAudit");
		mapper.put("物核损","task_itemLossAudit");
		mapper.put("物定损","task_itemAssessment");
		mapper.put("物复勘现场核损","task_itemRepairedCheckLossAudit");
		mapper.put("物复勘现场报告","task_itemRepairedCheckAssessment");
		mapper.put("注销申请","task_logOffApply");
		mapper.put("零结案审核","task_logOffAudit");
		mapper.put("案卷接收归档","task_casefolderRevTran");
		mapper.put("核赔（普通）","task_claim");
		mapper.put("查勘审核","task_surveyAudit");
		mapper.put("查勘定损退回团队长审核","task_audit");
		mapper.put("车物查勘","task_survey");
		mapper.put("机构报价","task_quotationBranch");
		mapper.put("拒赔申请审核","task_rejectionAudit");
		mapper.put("拒赔申请","task_rejectionApply");
		mapper.put("拒赔审核","task_rejectionClaimsAudit");
		mapper.put("报价","task_quotation");
		mapper.put("意见征询","task_consultation");
		mapper.put("总公司疑异审核","task_objectionHeadAudit");
		mapper.put("强制支付垫付申请","task_compulsoryPaymentApply");
		mapper.put("交强险支付/垫付/担保审核","task_compulsoryPaymentAudit");
		mapper.put("大案预报确认","task_criticalCaseConfirm");
		mapper.put("大案预报","task_criticalCasePredict");
		mapper.put("多次退回","task_disputeReply");
		mapper.put("外部调查申请审核","task_investOutApplyAudit");
		mapper.put("外部调查","task_investOutside");
		mapper.put("复勘现场","task_resurvey");
		mapper.put("合议申请审核","task_discussionAudit");
		mapper.put("合议申请","task_discussionApply");
		mapper.put("合议","task_discussion");
		mapper.put("单证","task_doc");
		mapper.put("区域报价","task_quotationOtherArea");
		mapper.put("分公司疑异审核","task_objectionBranchAudit");
		mapper.put("人伤跟踪计划","task_injuryTrackPlan");
		mapper.put("人伤跟踪审核","task_injuryTrackDirection");
		mapper.put("人伤跟踪","task_injuryTrack");
		mapper.put("人伤调解审核","task_injuryPreLossAudit");
		mapper.put("人伤调解","task_injuryMediation");
		mapper.put("人伤管家","task_injuryOneOnOneTrack");
		mapper.put("人伤核损","task_injuryLossAudit");
		mapper.put("人伤查勘","task_injuryOneOnOneFirst");
		mapper.put("人伤定损","task_injuryAssessment");
		mapper.put("人伤风险因素核查","task_injuryResurvey");
		mapper.put("人伤单证收集","task_injuryDoc");
		mapper.put("专家指导","task_expertGuidance");
		/*车物核损—>车核损、物核损*/
		mapper.put("车物核损","task_vehicleLossAudit\r\n" + 
		"task_itemLossAudit\r\n" + 
		"task_repairedCheckLossAudit\r\n" + 
		"task_itemRepairedCheckLossAudit");
		mapper.put("人伤跟踪审核（含风险核查信息审核）","task_injuryTrackDirection");
		mapper.put("注销审核","task_logOffAudit");
		/*20170808 ADD*/	
		mapper.put("核价","task_vehiclePriceAudit");
		/*车物核价—>车核价*/
		mapper.put("车物核价","task_vehiclePriceAudit");
		/*大案预核损（车）—>车预核损*/
		mapper.put("大案预核损（车）","task_vehiclePreLossAudit");
		/*核赔（普通/先行赔付）—>核赔*/
		mapper.put("核赔（普通/先行赔付）","task_claim");
		/*大案查勘->查勘	task_survey*/
		mapper.put("大案查勘","task_survey");
	/*	整案/部分单证齐全确认（在单证收集环节中, 不以任务的形式出现）->单证	task_doc*/
		mapper.put("整案/部分单证齐全确认（在单证收集环节中, 不以任务的形式出现）","task_doc");
		/*大案定损->车定损，人定损，（物定损?)*/
		mapper.put("大案定损","task_vehicleAssessment\r\n" + 
				"task_injuryAssessment\r\n");
	/*	核赔（先行赔付）->核赔 task_claim*/
		mapper.put("核赔（先行赔付）","task_claim");
	/*	调查报告审核*/
		mapper.put("调查报告审核","task_investigationAudit");
		
	}
	
	public boolean equals(ImportTask obj) {
		return this.name.equals(obj.getName());
	}
	
}
