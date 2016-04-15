package com.compliance.model.cpManage.msaShow;
/**
 * 通用管理整改建议实体
 * @author quyongkun
 *
 */
public class MsaRect {
	
	/**
	 * 通用管理整改编号
	 */
	private int msaRectId;
	
	/**
	 * 通用管理测评时间
	 */	
	private String msaDate;
	
	/**
	 * 通用管理整改建议
	 */	
	private String msaRectAdvise;
	
	/**
	 * 通用管理整改时间
	 */
	private String msaRectDate;
	
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
	
	/**
	 * 评估项测评结果 0：符合，1：部分符合，2：不符合，3：不适用
	 */
	private String msaAssessResult;

	public int getMsaRectId() {
		return msaRectId;
	}

	public void setMsaRectId(int msaRectId) {
		this.msaRectId = msaRectId;
	}

	public String getMsaDate() {
		return msaDate;
	}

	public void setMsaDate(String msaDate) {
		this.msaDate = msaDate;
	}

	public String getMsaRectAdvise() {
		return msaRectAdvise;
	}

	public void setMsaRectAdvise(String msaRectAdvise) {
		this.msaRectAdvise = msaRectAdvise;
	}

	public String getMsaRectDate() {
		return msaRectDate;
	}

	public void setMsaRectDate(String msaRectDate) {
		this.msaRectDate = msaRectDate;
	}

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

	public String getMsaAssessResult() {
		return msaAssessResult;
	}

	public void setMsaAssessResult(String msaAssessResult) {
		this.msaAssessResult = msaAssessResult;
	}

	public MsaRect() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MsaRect [msaRectId=" + msaRectId + ", msaDate=" + msaDate
				+ ", msaRectAdvise=" + msaRectAdvise + ", msaRectDate="
				+ msaRectDate + ", gFatherSort=" + gFatherSort
				+ ", gFatherName=" + gFatherName + ", fatherSort=" + fatherSort
				+ ", fatherName=" + fatherName + ", sonSort=" + sonSort
				+ ", sonContent=" + sonContent + ", msaAssessResult="
				+ msaAssessResult + "]";
	}


	
}
