package com.compliance.model.basicinfo.assets;

import java.util.List;

//关键数据类别
public class DataAssets {

	// id值
	private int id;
	// 属所系统名称
	private String sysName;
	// 数据类别
	private String dateType;
	// 所属业务应用软件名称
	private String resName;
	// 设备描述
	private String devDescription;
	// 备注
	private String dataRemarks;
	// 重要程度
	private String impDegree;
	// 资产种类
	private String assetsType;
	
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

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getDevDescription() {
		return devDescription;
	}

	public void setDevDescription(String devDescription) {
		this.devDescription = devDescription;
	}

	public String getDataRemarks() {
		return dataRemarks;
	}

	public void setDataRemarks(String dataRemarks) {
		this.dataRemarks = dataRemarks;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}

}
