package com.util.reportForm.model;


import java.util.List;

import org.apache.struts.util.MessageResources;

public class ReportExportModel {
	
	private String title ="";             //公司商标
	
	private String support = "";          //技术支持
	
	private String owner = "";            //产品所属公司
	
	private String reportTitle = "" ;     //报表标题
	
	private String[] picPath ;            //图片路径
	
	private String filePath = "";         //生成html的路径
	 
	private String exportUser = "";         //生成报表用户
	
	private String createUser = "";       //模板创建用户
	
	private String company = "";          //单位
	
	private List tableList;               //表格数据
	
	private List reportFormInfo ;         //模板详细信息
	
	private String table = "";            //列表表格数据
	
	private String zipFileNameAndPath = ""; //模板导出压缩路径
	
	private String[] sql ;
	
	public ReportExportModel() {
		init();
	}
	
	private void init(){
		//MessageResources messages = MessageResources.getMessageResources("MessageResources_zh_CN");
		//title = messages.getMessage("global.title") ;
		//support = messages.getMessage("copyright.inc.support");
		//owner = messages.getMessage("copyright.inc.owner") ;
	}
	
	public void export(String type){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String[] getPicPath() {
		return picPath;
	}

	public void setPicPath(String[] picPath) {
		this.picPath = picPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExportUser() {
		return exportUser;
	}

	public void setExportUser(String exportUser) {
		this.exportUser = exportUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List getTableList() {
		return tableList;
	}

	public void setTableList(List tableList) {
		this.tableList = tableList;
	}

	public List getReportFormInfo() {
		return reportFormInfo;
	}

	public void setReportFormInfo(List reportFormInfo) {
		this.reportFormInfo = reportFormInfo;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getZipFileNameAndPath() {
		return zipFileNameAndPath;
	}

	public void setZipFileNameAndPath(String zipFileNameAndPath) {
		this.zipFileNameAndPath = zipFileNameAndPath;
	}

	public String[] getSql() {
		return sql;
	}

	public void setSql(String[] sql) {
		this.sql = sql;
	}
}
