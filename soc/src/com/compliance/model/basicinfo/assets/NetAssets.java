package com.compliance.model.basicinfo.assets;

import java.util.List;

//网络互连设备
public class NetAssets {

	// id值
	private int id;
	// 设备名称
	private String devName;
	// 重要程度
	private String impDegree;
	// 设备描述
	private String devDescriotion;
	// 备注
	private String netRemarks;
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

	public String getDevDescriotion() {
		return devDescriotion;
	}

	public void setDevDescriotion(String devDescriotion) {
		this.devDescriotion = devDescriotion;
	}

	public String getNetRemarks() {
		return netRemarks;
	}

	public void setNetRemarks(String netRemarks) {
		this.netRemarks = netRemarks;
	}

}
