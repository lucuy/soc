package com.util.reportForm.model;

import java.util.List;

import com.util.reportForm.datadeal.model.Reportforms;
/**
 * @author lenjey
 * 根据模板查询功能所对应用到的bean
 */
public class TemplateQueryModel {
	
	//模板类型ID
	private Long typeId;
	
	//模板名称
	private String templateName;
	
	//模板ID对应的所有组态报表模板
	private List<Reportforms> reportFormList;
	
	//组态报表模板对应的所有列，
	//private List<Reportformsinfo> formsColList;
	
	//每一个模板类型对应的组态报表个数
	private int reportformSize = 0;
	
	public TemplateQueryModel() {
	}
	

	public TemplateQueryModel(Long typeId,
			List<Reportforms> reportFormList, String templateName) {
		this.typeId = typeId;
		this.reportFormList = reportFormList;
		this.templateName = templateName;
		if(null != reportFormList){
			reportformSize = reportFormList.size();
		}
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<Reportforms> getReportFormList() {
		return reportFormList;
	}

	public void setReportFormList(List<Reportforms> reportFormList) {
		this.reportFormList = reportFormList;
		if(null != reportFormList){
			reportformSize = reportFormList.size();
		}
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public int getReportformSize() {
		return reportformSize;
	}

	public void setReportformSize(int reportformSize) {
		this.reportformSize = reportformSize;
	}
	
}
