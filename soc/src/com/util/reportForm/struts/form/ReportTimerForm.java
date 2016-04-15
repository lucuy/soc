package com.util.reportForm.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.util.reportForm.datadeal.model.ReportTimer;

/**
 * 定时报表form
 * 
 * @author zsa
 * 
 */
public class ReportTimerForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private ReportTimer reportTimer = new ReportTimer();
	private List<Integer> weekList;
	private List<Integer> dayList;
	private Integer useweek;
	private Integer useday;
	private String weekSet;
	private String daySet;
	private String reportStr;


	public String getReportStr() {
		return reportStr;
	}

	public void setReportStr(String reportStr) {
		this.reportStr = reportStr;
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

	public Integer getUseweek() {
		return useweek;
	}

	public void setUseweek(Integer useweek) {
		this.useweek = useweek;
	}

	public Integer getUseday() {
		return useday;
	}

	public void setUseday(Integer useday) {
		this.useday = useday;
	}

	public ReportTimer getReportTimer() {
		return reportTimer;
	}

	public void setReportTimer(ReportTimer reportTimer) {
		this.reportTimer = reportTimer;
	}

	public List<Integer> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<Integer> weekList) {
		this.weekList = weekList;
	}

	public List<Integer> getDayList() {
		return dayList;
	}

	public void setDayList(List<Integer> dayList) {
		this.dayList = dayList;
	}

}
