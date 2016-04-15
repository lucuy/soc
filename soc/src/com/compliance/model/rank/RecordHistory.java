package com.compliance.model.rank;

import java.util.Date;
/**
 * 定级备案历史
 * @author quyongkun
 *
 */
public class RecordHistory {
	
	/**
	 * 定级备案历史编号
	 */
	private int recordHistoryId;
	
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
	 * 定级备案历史文档
	 */
	private String recordHistoryDocument;
	
	/**
	 * 重命名定级备案历史文档
	 */
	private String reRecordHistoryDocument;
	
	/**
	 * 定级备案历史时间
	 */
	private Date recordHistoryDate;

	public int getRecordHistoryId() {
		return recordHistoryId;
	}

	public void setRecordHistoryId(int recordHistoryId) {
		this.recordHistoryId = recordHistoryId;
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

	public String getSysInFoBusDescription() {
		return sysInFoBusDescription;
	}

	public void setSysInFoBusDescription(String sysInFoBusDescription) {
		this.sysInFoBusDescription = sysInFoBusDescription;
	}

	public String getRankGrade() {
		return rankGrade;
	}

	public void setRankGrade(String rankGrade) {
		this.rankGrade = rankGrade;
	}

	public String getRecordHistoryDocument() {
		return recordHistoryDocument;
	}

	public void setRecordHistoryDocument(String recordHistoryDocument) {
		this.recordHistoryDocument = recordHistoryDocument;
	}

	public Date getRecordHistoryDate() {
		return recordHistoryDate;
	}

	public void setRecordHistoryDate(Date recordHistoryDate) {
		this.recordHistoryDate = recordHistoryDate;
	}
	
	public String getReRecordHistoryDocument() {
		return reRecordHistoryDocument;
	}

	public void setReRecordHistoryDocument(String reRecordHistoryDocument) {
		this.reRecordHistoryDocument = reRecordHistoryDocument;
	}

	@Override
	public String toString() {
		return "RecordHistory [recordHistoryId=" + recordHistoryId
				+ ", sysInFoId=" + sysInFoId + ", sysInFoName=" + sysInFoName
				+ ", sysInFoBusDescription=" + sysInFoBusDescription
				+ ", rankGrade=" + rankGrade + ", recordHistoryDocument="
				+ recordHistoryDocument + ", recordHistoryDate="
				+ recordHistoryDate + "]";
	}
	
	

}
