package com.soc.model.monitor;

import java.util.Date;

public class MonitorAlarmAsset {

	 //资产id
    private long assetId;
    
    //资产名称
    private String assetName;
    
    //资产mac地址
    private String assetMac;
	
    /**
     * MonitorAlarm对象信息
     */
    // 告警ID
    private long monitorAlarmId;
    
    // 告警内容
    private String monitorAlarmContent;
    
    // 告警指标
    private String monitorAlarmIndex;
    
    // 告警级别
    private int monitorAlarmRank;
    
    // 发生告警的资产ID
    private long monitorAlarmAssetId;
    
    // 发生告警的当前值
    private double monitorAlarmCurrentValue;
    
    // 触发告警的阀值
    private int monitorAlarmThreshold;
    
    // 发生告警的时间
    private Date monitorAlarmTime;

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetMac() {
		return assetMac;
	}

	public void setAssetMac(String assetMac) {
		this.assetMac = assetMac;
	}

	public long getMonitorAlarmId() {
		return monitorAlarmId;
	}

	public void setMonitorAlarmId(long monitorAlarmId) {
		this.monitorAlarmId = monitorAlarmId;
	}

	public String getMonitorAlarmContent() {
		return monitorAlarmContent;
	}

	public void setMonitorAlarmContent(String monitorAlarmContent) {
		this.monitorAlarmContent = monitorAlarmContent;
	}

	public String getMonitorAlarmIndex() {
		return monitorAlarmIndex;
	}

	public void setMonitorAlarmIndex(String monitorAlarmIndex) {
		this.monitorAlarmIndex = monitorAlarmIndex;
	}

	public int getMonitorAlarmRank() {
		return monitorAlarmRank;
	}

	public void setMonitorAlarmRank(int monitorAlarmRank) {
		this.monitorAlarmRank = monitorAlarmRank;
	}

	public long getMonitorAlarmAssetId() {
		return monitorAlarmAssetId;
	}

	public void setMonitorAlarmAssetId(long monitorAlarmAssetId) {
		this.monitorAlarmAssetId = monitorAlarmAssetId;
	}

	public double getMonitorAlarmCurrentValue() {
		return monitorAlarmCurrentValue;
	}

	public void setMonitorAlarmCurrentValue(double monitorAlarmCurrentValue) {
		this.monitorAlarmCurrentValue = monitorAlarmCurrentValue;
	}

	public int getMonitorAlarmThreshold() {
		return monitorAlarmThreshold;
	}

	public void setMonitorAlarmThreshold(int monitorAlarmThreshold) {
		this.monitorAlarmThreshold = monitorAlarmThreshold;
	}

	public Date getMonitorAlarmTime() {
		return monitorAlarmTime;
	}

	public void setMonitorAlarmTime(Date monitorAlarmTime) {
		this.monitorAlarmTime = monitorAlarmTime;
	}
    
    
    
    
}
