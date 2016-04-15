package com.compliance.model.rank;

import java.util.Date;

/**
 * 备案文档集
 * @author quyongkun
 *
 */
public class RecordDocList {
	
	/**
	 * 备案文档集编号
	 */
	private int recordDocListId;
	
	/**
	 * 备案文档编号（备案表主键）
	 */
	private int recordId;
	
	/**
	 * 备案文档集名称
	 */
	private String recordDocListDocmentName;
	
	/**
	 * 重命名备案文档集
	 */
	private String reRecordDocListDocmentName;
	
	/**
	 * 备案文档集创建时间
	 */
	private Date recordDocListProduceDate;
	
	/**
	 * 备案文档集下载时间
	 */
	private Date recordDocListDownDate;
	
	/**
	 * 备案文档集下载次数
	 */
	private Integer recordDocListDownloads;

	public int getRecordDocListId() {
		return recordDocListId;
	}

	public void setRecordDocListId(int recordDocListId) {
		this.recordDocListId = recordDocListId;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getRecordDocListDocmentName() {
		return recordDocListDocmentName;
	}

	public void setRecordDocListDocmentName(String recordDocListDocmentName) {
		this.recordDocListDocmentName = recordDocListDocmentName;
	}

	public Date getRecordDocListProduceDate() {
		return recordDocListProduceDate;
	}

	public void setRecordDocListProduceDate(Date recordDocListProduceDate) {
		this.recordDocListProduceDate = recordDocListProduceDate;
	}

	public Date getRecordDocListDownDate() {
		return recordDocListDownDate;
	}

	public void setRecordDocListDownDate(Date recordDocListDownDate) {
		this.recordDocListDownDate = recordDocListDownDate;
	}

	public Integer getRecordDocListDownloads() {
		return recordDocListDownloads;
	}

	public void setRecordDocListDownloads(Integer recordDocListDownloads) {
		this.recordDocListDownloads = recordDocListDownloads;
	}

	public RecordDocList() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getReRecordDocListDocmentName() {
		return reRecordDocListDocmentName;
	}

	public void setReRecordDocListDocmentName(String reRecordDocListDocmentName) {
		this.reRecordDocListDocmentName = reRecordDocListDocmentName;
	}

	@Override
	public String toString() {
		return "RecordDocList [recordDocListId=" + recordDocListId
				+ ", recordId=" + recordId + ", recordDocListDocmentName="
				+ recordDocListDocmentName + ", recordDocListProduceDate="
				+ recordDocListProduceDate + ", recordDocListDownDate="
				+ recordDocListDownDate + ", recordDocListDownloads="
				+ recordDocListDownloads + "\n]";
	}
	
	
	

}
