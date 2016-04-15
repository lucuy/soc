package com.compliance.model.rank;



import java.util.Date;
/**
 * 备案表
 * @author quyongkun
 *
 */
public class Record {
   
	/**
	 * 备案编号
	 */
	private int recordId;
	
	/**
	 * 系统编号
	 */
	private String sysInFoId;
	
	/**
	 * 系统名称
	 */
	private String sysInFoName;
	
	/**
	 * 业务描述
	 */
	private String sysInFoBusDescription;
	
	
	/**
	 * 定级级别
	 */
	private String rankGrade;
	
	/**
	 * 备案文档
	 */
	private String recordDocument;
	
	/**
	 * 备案文档重命名
	 */
	private String reRecordDocument;
	
	/**
	 * 备案时间
	 */
	private Date recordDate;
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getSysInFoId() {
		return sysInFoId;
	}
	public void setSysInFoId(String sysInFoId) {
		this.sysInFoId = sysInFoId;
	}
	public String getSysInFoName() {
		return sysInFoName;
	}
	public void setSysInFoName(String sysInFoName) {
		this.sysInFoName = sysInFoName;
	}
	public String getRankGrade() {
		return rankGrade;
	}
	public void setRankGrade(String rankGrade) {
		this.rankGrade = rankGrade;
	}
	public String getRecordDocument() {
		return recordDocument;
	}
	public void setRecordDocument(String recordDocument) {
		this.recordDocument = recordDocument;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	
	public String getSysInFoBusDescription() {
		return sysInFoBusDescription;
	}
	public void setSysInFoBusDescription(String sysInFoBusDescription) {
		this.sysInFoBusDescription = sysInFoBusDescription;
	}
	
	public String getReRecordDocument() {
		return reRecordDocument;
	}
	public void setReRecordDocument(String reRecordDocument) {
		this.reRecordDocument = reRecordDocument;
	}
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", sysInFoId=" + sysInFoId
				+ ", sysInFoName=" + sysInFoName + ", sysInFoBusDescription="
				+ sysInFoBusDescription + ", rankGrade=" + rankGrade
				+ ", recordDocument=" + recordDocument + ", recordDate="
				+ recordDate + "]";
	}

	
	

	
	
}
