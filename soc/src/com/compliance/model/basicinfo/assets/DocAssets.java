package com.compliance.model.basicinfo.assets;

import java.util.List;

//安全管理文档
public class DocAssets {

	// id值
	private int id;
	// 文档名称
	private String docName;
	// 重要程度
	private String impDegree;
	// 主要内容
	private String impContent;
	// 备注
	private String docRemarks;
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

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}

	public String getImpContent() {
		return impContent;
	}

	public void setImpContent(String impContent) {
		this.impContent = impContent;
	}

	public String getDocRemarks() {
		return docRemarks;
	}

	public void setDocRemarks(String docRemarks) {
		this.docRemarks = docRemarks;
	}

}
