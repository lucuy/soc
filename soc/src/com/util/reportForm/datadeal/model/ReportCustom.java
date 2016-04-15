package com.util.reportForm.datadeal.model;

import java.util.Date;

/**
 * 自定义报表实体类
 * 
 * @author zsa
 * 
 */
public class ReportCustom implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String customSql;
	private String memo;
	private Date createDate;
	private Date updateDate;

	public ReportCustom() {
	}

	public ReportCustom(Integer id,Date createDate,Date updateDate) {
		this.id=id;
		this.createDate=createDate;
		this.updateDate=updateDate;
	}

	public ReportCustom(Integer id,String customSql,String memo,Date createDate,Date updateDate) {
		this.id=id;
		this.customSql=customSql;
		this.memo=memo;
		this.createDate=createDate;
		this.updateDate=updateDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomSql() {
		return customSql;
	}

	public void setCustomSql(String customSql) {
		this.customSql = customSql;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}