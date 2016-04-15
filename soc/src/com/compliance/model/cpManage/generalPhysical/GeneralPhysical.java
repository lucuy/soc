package com.compliance.model.cpManage.generalPhysical;

//差距分析报告导出word文档
//表：信息系统总体符合度

public class GeneralPhysical {
	private String sysname;
	private String sysgrade;
	private String totalPercentage;
	private String physicalPercentage;
	private String technologyPercentage;
	private String managementPercentage;
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getSysgrade() {
		return sysgrade;
	}
	public void setSysgrade(String sysgrade) {
		this.sysgrade = sysgrade;
	}
	public String getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(String totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	public String getPhysicalPercentage() {
		return physicalPercentage;
	}
	public void setPhysicalPercentage(String physicalPercentage) {
		this.physicalPercentage = physicalPercentage;
	}
	public String getTechnologyPercentage() {
		return technologyPercentage;
	}
	public void setTechnologyPercentage(String technologyPercentage) {
		this.technologyPercentage = technologyPercentage;
	}
	public String getManagementPercentage() {
		return managementPercentage;
	}
	public void setManagementPercentage(String managementPercentage) {
		this.managementPercentage = managementPercentage;
	}

}
