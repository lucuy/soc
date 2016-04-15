package com.compliance.model.msad;

/**
 * 通用管理
 * @author 胡亚丹
 *
 */
public class Msad {

	private int msadId; //编号
	private String msadSort;  //排序
	private String msadName;  //名称
	private String msadContent;  //内容
	private String msadFatherSort; //父排序
	private String msadListGrade;  //目录级别
	private String msadIsHave;
	private String msadHaveIf;
	private String result;//该项评估结果
	public int getMsadId() {
		return msadId;
	}
	public void setMsadId(int msadId) {
		this.msadId = msadId;
	}
	public String getMsadSort() {
		return msadSort;
	}
	public void setMsadSort(String msadSort) {
		this.msadSort = msadSort;
	}
	public String getMsadName() {
		return msadName;
	}
	public void setMsadName(String msadName) {
		this.msadName = msadName;
	}
	public String getMsadContent() {
		return msadContent;
	}
	public void setMsadContent(String msadContent) {
		this.msadContent = msadContent;
	}
	public String getMsadFatherSort() {
		return msadFatherSort;
	}
	public void setMsadFatherSort(String msadFatherSort) {
		this.msadFatherSort = msadFatherSort;
	}
	public String getMsadListGrade() {
		return msadListGrade;
	}
	public void setMsadListGrade(String msadListGrade) {
		this.msadListGrade = msadListGrade;
	}
	public String getMsadIsHave() {
		return msadIsHave;
	}
	public void setMsadIsHave(String msadIsHave) {
		this.msadIsHave = msadIsHave;
	}
	public String getMsadHaveIf() {
		return msadHaveIf;
	}
	public void setMsadHaveIf(String msadHaveIf) {
		this.msadHaveIf = msadHaveIf;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
