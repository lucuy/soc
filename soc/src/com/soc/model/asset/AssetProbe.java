package com.soc.model.asset;

import java.io.Serializable;
/**
 * 资产信息实体类
 * @author 张浩磊
 *
 */
public class AssetProbe implements Serializable {
	private String collectorMac;//采集器MAC
	private String deviceType;//资产设备类型
	private String deviceMemo;//资产设备描述
	private String deviceIp;//资产Ip地址
	private String deviceMac;//资产MAC地址
    private int taskId ;  //探测任务的ID
	public String getCollectorMac() {
		return collectorMac;
	}
	public void setCollectorMac(String collectorMac) {
		this.collectorMac = collectorMac;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceMemo() {
		return deviceMemo;
	}
	public void setDeviceMemo(String deviceMemo) {
		this.deviceMemo = deviceMemo;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}
