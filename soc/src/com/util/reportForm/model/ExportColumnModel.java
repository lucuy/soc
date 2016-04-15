package com.util.reportForm.model;

public class ExportColumnModel {
	//模板的输出列的相关信息
	private Long reportFormId; //模板ID
    private String colName; //列名
    private String colWidth; //列宽
    private Integer alignType; //对齐方式
    private String aligndescription; //对齐方式的中文描述
    private Integer correspondingTable;//对应表ID
    private String tablename;//对应表
    private String tabledescription;//对应表的中文描述
    private Integer correspondingField;//对应列ID
    private String fieldname;//对应列
    private String fielddescription;//对应列的中文描述
    private String exportType;//输出方式
    private String exportdescription;//输出方式的中文描述
    private String coordx;
    private String coordy;
	
	public Long getReportFormId() {
		return reportFormId;
	}
	public void setReportFormId(Long reportFormId) {
		this.reportFormId = reportFormId;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColWidth() {
		return colWidth;
	}
	public void setColWidth(String colWidth) {
		this.colWidth = colWidth;
	}
	public Integer getAlignType() {
		return alignType;
	}
	public void setAlignType(Integer alignType) {
		this.alignType = alignType;
	}
	public Integer getCorrespondingTable() {
		return correspondingTable;
	}
	public void setCorrespondingTable(Integer correspondingTable) {
		this.correspondingTable = correspondingTable;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTabledescription() {
		return tabledescription;
	}
	public void setTabledescription(String tabledescription) {
		this.tabledescription = tabledescription;
	}
	public Integer getCorrespondingField() {
		return correspondingField;
	}
	public void setCorrespondingField(Integer correspondingField) {
		this.correspondingField = correspondingField;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFielddescription() {
		return fielddescription;
	}
	public void setFielddescription(String fielddescription) {
		this.fielddescription = fielddescription;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String getAligndescription() {
		return aligndescription;
	}
	public void setAligndescription(String aligndescription) {
		this.aligndescription = aligndescription;
	}
	public String getExportdescription() {
		return exportdescription;
	}
	public void setExportdescription(String exportdescription) {
		this.exportdescription = exportdescription;
	}
	public String getCoordx() {
		return coordx;
	}
	public void setCoordx(String coordx) {
		this.coordx = coordx;
	}
	public String getCoordy() {
		return coordy;
	}
	public void setCoordy(String coordy) {
		this.coordy = coordy;
	}
}
