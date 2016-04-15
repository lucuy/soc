package com.compliance.model.cpManage.gpaShow;
/**
 * 通用物理整改建议实体
 * @author quyongkun
 *
 */
public class GpaRect {
	
	/**
	 * 通用物理整改编号
	 */
	private int gpaRectId;
	
	/**
	 * 通用物理测评时间
	 */	
	private String gpaDate;
	
	/**
	 * 通用物理整改建议
	 */	
	private String gpaRectAdvise;
	
	/**
	 * 通用物理整改时间
	 */
	private String gpaRectDate;
	
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
	private String gpaAssessResult;

	public int getGpaRectId() {
		return gpaRectId;
	}

	public void setGpaRectId(int gpaRectId) {
		this.gpaRectId = gpaRectId;
	}

	public String getGpaDate() {
		return gpaDate;
	}

	public void setGpaDate(String gpaDate) {
		this.gpaDate = gpaDate;
	}

	public String getGpaRectAdvise() {
		return gpaRectAdvise;
	}

	public void setGpaRectAdvise(String gpaRectAdvise) {
		this.gpaRectAdvise = gpaRectAdvise;
	}

	public String getGpaRectDate() {
		return gpaRectDate;
	}

	public void setGpaRectDate(String gpaRectDate) {
		this.gpaRectDate = gpaRectDate;
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
	
	

	public String getGpaAssessResult() {
		return gpaAssessResult;
	}

	public void setGpaAssessResult(String gpaAssessResult) {
		this.gpaAssessResult = gpaAssessResult;
	}

	public GpaRect() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GpaRect [gpaRectId=" + gpaRectId + ", gpaDate=" + gpaDate
				+ ", gpaRectAdvise=" + gpaRectAdvise + ", gpaRectDate="
				+ gpaRectDate + ", gFatherSort=" + gFatherSort
				+ ", gFatherName=" + gFatherName + ", fatherSort=" + fatherSort
				+ ", fatherName=" + fatherName + ", sonSort=" + sonSort
				+ ", sonContent=" + sonContent + ", gpaAssessResult="
				+ gpaAssessResult + "]";
	}

   


	
	
}
