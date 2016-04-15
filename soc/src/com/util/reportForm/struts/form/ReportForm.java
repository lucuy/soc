package com.util.reportForm.struts.form;


import org.apache.struts.action.ActionForm;

import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;

public class ReportForm extends ActionForm{
//	private String modelname;
//	private String modeldescription;
//	private String modeltype;
//	private String modelcatogry;
	//Reportforms 表
//	private Integer id;
   //private Long reportFormId;
//    private String reportFormName;
//    private String reportFormDescription;
//    private String createDate;
//    private String reportFormSort;
//    private String reportFormType;
//    private String tables;
//    private String selTerm;
//    private String reportFormSql;
    
    //Reportformsinfo 表
//    private String colName;
//    private String colWidth;
//    private Integer alignType;
//    private Integer correspondingTable;
//    private Integer correspondingField;
//    private String exportType;
    
    private Reportforms ref=new Reportforms();
    private Reportformsinfo rf=new Reportformsinfo();
    
    
	public Integer getId() {
		return ref.getId();
	}
	public void setId(Integer id) {
		this.ref.setId(id);
	}
	public Long getReportFormId() {
		return ref.getReportFormId();
	}
	public void setReportFormId(Long reportFormId) {
		this.ref.setReportFormId(reportFormId);
	}
	public String getReportFormName() {
		return ref.getReportFormName();
	}
	public void setReportFormName(String reportFormName) {
		this.ref.setReportFormName(reportFormName);
	}
	public String getReportFormDescription() {
		return ref.getReportFormDescription();
	}
	public void setReportFormDescription(String reportFormDescription) {
		this.ref.setReportFormDescription(reportFormDescription);
	}
	public Long getCreateDate() {
		return ref.getCreateDate();
	}
	public void setCreateDate(Long createDate) {
		this.ref.setCreateDate(createDate);
	}
	public Integer getReportFormSort() {
		return ref.getReportFormSort();
	}
	public void setReportFormSort(Integer reportFormSort) {
		this.ref.setReportFormSort(reportFormSort);
	}
	public Integer getReportFormType() {
		return ref.getReportFormType();
	}
	public void setReportFormType(Integer reportFormType) {
		this.ref.setReportFormType(reportFormType);
	}
	public String getTables() {
		return ref.getTables();
	}
	public void setTables(String tables) {
		this.ref.setTables(tables);
	}
	public String getSelTerm() {
		return ref.getSelTerm();
	}
	public void setSelTerm(String selTerm) {
		this.ref.setSelTerm(selTerm);
	}
	public String getReportFormSql() {
		return ref.getReportFormSql();
	}
	public void setReportFormSql(String reportFormSql) {
		this.ref.setReportFormSql(reportFormSql);
	}
	public String getColName() {
		return rf.getColName();
	}
	public void setColName(String colName) {
		this.rf.setColName(colName);
	}
	public String getColWidth() {
		return rf.getColWidth();
	}
	public void setColWidth(String colWidth) {
		this.rf.setColWidth(colWidth);
	}
	public Integer getAlignType() {
		return rf.getAlignType();
	}
	public void setAlignType(Integer alignType) {
		this.rf.setAlignType(alignType);
	}
	public Integer getCorrespondingTable() {
		return rf.getCorrespondingTable();
	}
	public void setCorrespondingTable(Integer correspondingTable) {
		this.rf.setCorrespondingTable(correspondingTable);
	}
	public Integer getCorrespondingField() {
		return rf.getCorrespondingField();
	}
	public void setCorrespondingField(Integer correspondingField) {
		this.rf.setCorrespondingField(correspondingField);
	}
	public String getExportType() {
		return rf.getExportType();
	}
	public void setExportType(String exportType) {
		this.rf.setExportType(exportType);
	}
	public Reportforms getRef() {
		return ref;
	}
	public void setRef(Reportforms ref) {
		this.ref = ref;
	}
	public Reportformsinfo getRf() {
		return rf;
	}
	public void setRf(Reportformsinfo rf) {
		this.rf = rf;
	}
}
