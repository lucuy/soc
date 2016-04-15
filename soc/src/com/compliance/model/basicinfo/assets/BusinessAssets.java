package com.compliance.model.basicinfo.assets;

import java.util.List;

//业务应用软件
public class BusinessAssets {
	// 业务应用软件主键
	private int id;
	// 资产种类
	private String resType;
	// 软件名称
	private String resName;
	// 重要程度
	private String impDegree;
	// 主要功能
	private String mianFun;
	// 备注
	private String remarks;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}

	public String getMianFun() {
		return mianFun;
	}

	public void setMianFun(String mianFun) {
		this.mianFun = mianFun;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
