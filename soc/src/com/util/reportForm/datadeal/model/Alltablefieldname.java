package com.util.reportForm.datadeal.model;

/**
 * Alltablefieldname entity. @author MyEclipse Persistence Tools
 */

public class Alltablefieldname implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer tableNameId;
	private String tableFieldName;
	private String tableFieldDescription;
	private Integer tableFieldType;

	// Constructors

	/** default constructor */
	public Alltablefieldname() {
	}

	/** full constructor */
	public Alltablefieldname(Integer tableNameId, String tableFieldName,
			String tableFieldDescription, Integer tableFieldType) {
		this.tableNameId = tableNameId;
		this.tableFieldName = tableFieldName;
		this.tableFieldDescription = tableFieldDescription;
		this.tableFieldType = tableFieldType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTableNameId() {
		return this.tableNameId;
	}

	public void setTableNameId(Integer tableNameId) {
		this.tableNameId = tableNameId;
	}

	public String getTableFieldName() {
		return this.tableFieldName;
	}

	public void setTableFieldName(String tableFieldName) {
		this.tableFieldName = tableFieldName;
	}

	public String getTableFieldDescription() {
		return this.tableFieldDescription;
	}

	public void setTableFieldDescription(String tableFieldDescription) {
		this.tableFieldDescription = tableFieldDescription;
	}

	public Integer getTableFieldType() {
		return this.tableFieldType;
	}

	public void setTableFieldType(Integer tableFieldType) {
		this.tableFieldType = tableFieldType;
	}

}