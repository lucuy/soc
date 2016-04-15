package com.util.reportForm.datadeal.model;

/**
 * 自定义报表实体类
 * 
 * @author zsa
 * 
 */
public class ReportTimer implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer useWeek;
	private String weekSet;
	private Integer useDay;
	private String daySet;
	private Integer reportType;
	private String reportId;
	
	
	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public ReportTimer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeekSet() {
		return weekSet;
	}

	public void setWeekSet(String weekSet) {
		this.weekSet = weekSet;
	}

	public String getDaySet() {
		return daySet;
	}

	public void setDaySet(String daySet) {
		this.daySet = daySet;
	}

	public Integer getUseWeek() {
		return useWeek;
	}

	public void setUseWeek(Integer useWeek) {
		this.useWeek = useWeek;
	}

	public Integer getUseDay() {
		return useDay;
	}

	public void setUseDay(Integer useDay) {
		this.useDay = useDay;
	}

}