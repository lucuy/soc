package com.util.reportForm.model;

public class ReportFormModel {

	private Integer id;             //数据库中的唯一表示
	private String reportType;  	//模板类型
	private Long reportFormId;	//模板id
	private String reportFormName; 	//模板名称
	private Long createDate;  		//模板创建时间
	private String reportFormDescription;  //模板描述
	private String displayDate;  //页面显示的时间	private String reportFormSort;
	
	private Integer reportFormType;
	
	public ReportFormModel(){
		
	}
	

	public ReportFormModel(Integer id,String reportType, Long reportFormId,
			String reportFormName, Long createDate,
			String reportFormDescription) {
		this.id = id;
		this.reportType = reportType;
		this.reportFormId = reportFormId;
		this.reportFormName = reportFormName;
		this.createDate = createDate;
		this.reportFormDescription = reportFormDescription;
	}
	public ReportFormModel(Integer id,Integer reportFormType, Long reportFormId,
			String reportFormName, Long createDate,
			String reportFormDescription) {
		this.id = id;
		this.reportFormType = reportFormType;
		this.reportFormId = reportFormId;
		this.reportFormName = reportFormName;
		this.createDate = createDate;
		this.reportFormDescription = reportFormDescription;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public Long getReportFormId() {
		return reportFormId;
	}

	public void setReportFormId(Long reportFormId) {
		this.reportFormId = reportFormId;
	}

	public String getReportFormName() {
		return reportFormName;
	}

	public void setReportFormName(String reportFormName) {
		this.reportFormName = reportFormName;
	}



	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getReportFormDescription() {
		return reportFormDescription;
	}

	public void setReportFormDescription(String reportFormDescription) {
		this.reportFormDescription = reportFormDescription;
	}

	public String getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(String displayDate) {
		this.displayDate = displayDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportFormSort() {
		return reportFormSort;
	}

	public void setReportFormSort(String reportFormSort) {
		this.reportFormSort = reportFormSort;
	}

	public Integer getReportFormType() {
		return reportFormType;
	}

	public void setReportFormType(Integer reportFormType) {
		this.reportFormType = reportFormType;
	}
	
	
	
}
