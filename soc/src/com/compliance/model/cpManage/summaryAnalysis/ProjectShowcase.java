package com.compliance.model.cpManage.summaryAnalysis;

import java.util.Date;

public class ProjectShowcase {
	private String caName;
	private String sysinfoId;
	private int pkca;
	private Date caEndTime;
	private String casysGrade;
	public String getCaName() {
		return caName;
	}
	public void setCaName(String caName) {
		this.caName = caName;
	}
	public String getSysinfoId() {
		return sysinfoId;
	}
	public String getCasysGrade() {
		return casysGrade;
	}
	public void setCasysGrade(String casysGrade) {
		this.casysGrade = casysGrade;
	}
	public void setSysinfoId(String sysinfoId) {
		this.sysinfoId = sysinfoId;
	}
	public int getPkca() {
		return pkca;
	}
	public void setPkca(int pkca) {
		this.pkca = pkca;
	}
	public Date getCaEndTime() {
		return caEndTime;
	}
	public void setCaEndTime(Date caEndTime) {
		this.caEndTime = caEndTime;
	}
	

}
