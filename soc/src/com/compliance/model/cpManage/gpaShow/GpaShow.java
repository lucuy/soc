package com.compliance.model.cpManage.gpaShow;

/**
 * 通用物理安全测评表整改需求汇总
 * @author quyongkun
 *
 */
public class GpaShow {
	
	/**
	 * 祖父排序
	 */
	private String gFatherSort;
	
	/**
	 * 祖父名称
	 */
	private String gFatherName;
	
	/**
	 * 父亲排序
	 */
	private String fatherSort;
	
	/**
	 * 祖父名称
	 */
	private String fatherName;
	
	/**
	 * 儿子排序
	 */
	private String sonSort;
	
	/**
	 * 儿子内容
	 */
	private String sonContent;

 
 

	public String getgFatherSort() {
		return gFatherSort;
	}




	public void setgFatherSort(String gFatherSort) {
		this.gFatherSort = gFatherSort;
	}




	public String getgFatherName() {
		return gFatherName;
	}




	public void setgFatherName(String gFatherName) {
		this.gFatherName = gFatherName;
	}




	public String getFatherSort() {
		return fatherSort;
	}




	public void setFatherSort(String fatherSort) {
		this.fatherSort = fatherSort;
	}




	public String getFatherName() {
		return fatherName;
	}




	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}




	public String getSonSort() {
		return sonSort;
	}




	public void setSonSort(String sonSort) {
		this.sonSort = sonSort;
	}




	public String getSonContent() {
		return sonContent;
	}




	public void setSonContent(String sonContent) {
		this.sonContent = sonContent;
	}




	@Override
	public String toString() {
		return "PsaShow [gFatherSort=" + gFatherSort + ", gFatherName="
				+ gFatherName + ", fatherSort=" + fatherSort + ", fatherName="
				+ fatherName + ", sonSort=" + sonSort + ", sonContent="
				+ sonContent + "]";
	}
	
	

}
