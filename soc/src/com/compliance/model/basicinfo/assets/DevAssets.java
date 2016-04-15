package com.compliance.model.basicinfo.assets;

import java.util.List;

//安全设备
public class DevAssets {
	// id值
	private int id;
	// 设备名称
	private String devName;
	// 重要程度
	private String impDegree;
	// 设备描述
	private String devDescription;
	// 备注
	private String devRemarks;
	// 资产种类
	private String devtype;
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
	public String getDevtype() {
		return devtype;
	}

	public void setDevtype(String devtype) {
		this.devtype = devtype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}

	public String getDevDescription() {
		return devDescription;
	}

	public void setDevDescription(String devDescription) {
		this.devDescription = devDescription;
	}

	public String getDevRemarks() {
		return devRemarks;
	}

	public void setDevRemarks(String devRemarks) {
		this.devRemarks = devRemarks;
	}

}
