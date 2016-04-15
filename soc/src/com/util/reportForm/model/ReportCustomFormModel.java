package com.util.reportForm.model;


import java.util.List;
import com.util.reportForm.datadeal.model.ReportCustom;
/**
 * 封装自定义报表信息
 * @author zsa
 *
 */
public class ReportCustomFormModel {

	private ReportCustom reportCustom;
	
	private List formInfoList;

	public ReportCustomFormModel() {

	}
	
	public ReportCustomFormModel(ReportCustom reportCustom,
			List formInfoList) {
		this.reportCustom = reportCustom;
		this.formInfoList = formInfoList;
	}

	public ReportCustom getReportCustom() {
		return reportCustom;
	}

	public void setReportCustom(ReportCustom reportCustom) {
		this.reportCustom = reportCustom;
	}

	public List getFormInfoList() {
		return formInfoList;
	}

	public void setFormInfoList(List formInfoList) {
		this.formInfoList = formInfoList;
	}

}
