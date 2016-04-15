package com.util.reportForm.datadeal.model;

/**
 * Alltablename entity. @author MyEclipse Persistence Tools
 */

public class Alltablename implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tableName;
	private String tableNameDescription;

	// Constructors

	/** default constructor */
	public Alltablename() {
	}

	/** minimal constructor */
	public Alltablename(String tableName) {
		this.tableName = tableName;
	}

	/** full constructor */
	public Alltablename(String tableName, String tableNameDescription) {
		this.tableName = tableName;
		this.tableNameDescription = tableNameDescription;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableNameDescription() {
		return this.tableNameDescription;
	}

	public void setTableNameDescription(String tableNameDescription) {
		this.tableNameDescription = tableNameDescription;
	}

}