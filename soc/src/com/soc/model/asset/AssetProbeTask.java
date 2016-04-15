package com.soc.model.asset;

import java.io.Serializable;
/**
 * 资产探测任务实体类
 * @author 张浩磊
 *
 */
public class AssetProbeTask implements Serializable{
	private long taskId;//任务ID
	private String taskName;//任务名称
	private String collectorName;//采集器名称
	private String collectorMac;//采集器mac
	private String beginIp;//开始IP
	private String endIp;//结束IP
	private String medo;//任务描述
	
	

	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	public String getCollectorMac() {
		return collectorMac;
	}
	public void setCollectorMac(String collectorMac) {
		this.collectorMac = collectorMac;
	}
	public String getBeginIp() {
		return beginIp;
	}
	public void setBeginIp(String beginIp) {
		this.beginIp = beginIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getMedo() {
		return medo;
	}
	public void setMedo(String medo) {
		this.medo = medo;
	}

	
}
