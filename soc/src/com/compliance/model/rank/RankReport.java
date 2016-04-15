package com.compliance.model.rank;

import java.io.Serializable;


public class RankReport implements Serializable{
	
    private String sysId;	//系统编号
	
	private String sysName; //系统名称
	
	private String grade;	//等级
	
	private String docment ;	//备案情况

	
	
	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDocment() {
		return docment;
	}

	public void setDocment(String docment) {
		this.docment = docment;
	}

	
	
}
