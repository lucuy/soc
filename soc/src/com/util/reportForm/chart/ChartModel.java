package com.util.reportForm.chart;


import java.util.ArrayList;
import java.util.List;

public class ChartModel {
	//曲线的名称
	private String lineName;
	
	//曲线轴标记
	private String axisName;
	
	//曲线轴标记对应的数据
	private Double data;
	
	//LineChartModel链表
	private List<ChartModel> chartModelList = new ArrayList<ChartModel>();
	
	public ChartModel() {
	}
	
	/**
	 * 把数据添加到LineChartModel链表
	 * @param lineName
	 * @param axisName
	 * @param data
	 */
	public void addDataToModelList(String lineName, String axisName, Double data){
		if(null == lineName || null == axisName || null == data){
			return;
		}
		ChartModel model= new ChartModel(lineName,axisName,data);
		chartModelList.add(model);
	}
	
	/**
	 * 获得LineChartModel链表
	 * @return LineChartModel链表
	 */
	public List<ChartModel> getChartModelList(){
		return this.chartModelList;
	}

	public void setChartModelList(List<ChartModel> chartModelList) {
		this.chartModelList = chartModelList;
	}

	public ChartModel(String lineName, String axisName, Double data) {
		this.lineName = lineName;
		this.axisName = axisName;
		this.data = data;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}
	
}
