package com.util.reportForm.model;


import java.util.List;

import com.util.reportForm.datadeal.model.Reportforms;


//存放模板相关的信息
public class ReportFormAndFormInfoModel {

	private Reportforms reportform;
	
	private List formInfoList;

	public ReportFormAndFormInfoModel() {

	}
	
	public ReportFormAndFormInfoModel(Reportforms reportform,
			List formInfoList) {
		this.reportform = reportform;
		this.formInfoList = formInfoList;
	}

	public Reportforms getReportform() {
		return reportform;
	}

	public void setReportform(Reportforms reportform) {
		this.reportform = reportform;
	}

	public List getFormInfoList() {
		return formInfoList;
	}

	public void setFormInfoList(List formInfoList) {
		this.formInfoList = formInfoList;
	}

}
