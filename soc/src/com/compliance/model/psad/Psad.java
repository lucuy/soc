package com.compliance.model.psad;

import java.io.Serializable;

public class Psad implements Serializable{

	private int psadId;  //编号
	private String psadSort; //排序
	private String psadName;  //名称
	private String psadContent;  //内容
	private String psadFatherSort;//父排序
	private int ishave; //是否需要评估0-无评估1-评估
	private int haveIf; //是否有如果条件0-无1-有
	private String result;//该项评估结果
	
	public int getIshave() {
		return ishave;
	}
	public void setIshave(int ishave) {
		this.ishave = ishave;
	}
	public int getHaveIf() {
		return haveIf;
	}
	public void setHaveIf(int haveIf) {
		this.haveIf = haveIf;
	}
	public int getPsadId() {
		return psadId;
	}
	public void setPsadId(int psadId) {
		this.psadId = psadId;
	}
	public String getPsadSort() {
		return psadSort;
	}
	public void setPsadSort(String psadSort) {
		this.psadSort = psadSort;
	}
	public String getPsadName() {
		return psadName;
	}
	public void setPsadName(String psadName) {
		this.psadName = psadName;
	}
	public String getPsadContent() {
		return psadContent;
	}
	public void setPsadContent(String psadContent) {
		this.psadContent = psadContent;
	}
	public String getPsadFatherSort() {
		return psadFatherSort;
	}
	public void setPsadFatherSort(String psadFatherSort) {
		this.psadFatherSort = psadFatherSort;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	
	
}
