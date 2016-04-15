package com.compliance.model.basicinfo.assets;

import java.util.List;

//安全相关人员
public class EmpAssets {

	// id值
	private int id;
	// 姓名
	private String empName;
	// 联系方式
	private String conInfo;
	// 岗位描述
	private String jobDes;
	// 备注
	private String empRemarks;
	// 资产种类
	private String resType;
	//拼凑字符串
	private String sysName;
	// 信息系统名称
	private List<String> relsysName;

	public String getSysName() {
		if (relsysName != null && relsysName.size() > 0) {
			StringBuffer str = new StringBuffer();
			for (String tmp : relsysName) {
				str.append(tmp);
				str.append(";");
			}
			sysName = str.substring(0, str.lastIndexOf(";"));
			return sysName;
		} else {
			return "";
		}
	}
	public List<String> getRelsysName() {
		return relsysName;
	}

	public void setRelsysName(List<String> relsysName) {
		this.relsysName = relsysName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getConInfo() {
		return conInfo;
	}

	public void setConInfo(String conInfo) {
		this.conInfo = conInfo;
	}

	public String getJobDes() {
		return jobDes;
	}

	public void setJobDes(String jobDes) {
		this.jobDes = jobDes;
	}

	public String getEmpRemarks() {
		return empRemarks;
	}

	public void setEmpRemarks(String empRemarks) {
		this.empRemarks = empRemarks;
	}

}
