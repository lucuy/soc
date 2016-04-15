package com.compliance.model.basicinfo.assets;

import java.util.List;

//主机存储设备
public class CompAssets {

	// id值
	private int id;
	// 属所信息系统名称
	private String sysName;
	// 设备名称
	private String devName;
	// 所属业务应用软件名称
	private String resName;
	// 资产类别
	private String devType;
	// 设备描述
	private String devDescription;
	// 备注
	private String devRemarks;
	
	private List<String> relsysName;
	private List<String> relresName;

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
	public String getResName() {
		if (relresName != null && relresName.size() > 0) {
			StringBuffer str = new StringBuffer();
			for (String tmp : relresName) {
				str.append(tmp);
				str.append(";");
			}
			resName = str.substring(0, str.lastIndexOf(";"));
			return resName;
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

	public List<String> getRelresName() {
		return relresName;
	}

	public void setRelresName(List<String> relresName) {
		this.relresName = relresName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
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
