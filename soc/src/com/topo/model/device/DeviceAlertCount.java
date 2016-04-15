package com.topo.model.device;

public class DeviceAlertCount {

	//告警数量ID
	private long deviceAlertCountId;
	//告警数量IP
	private String deviceAlertCountIp;
	//告警数量
	private int deviceAlertCount;
	//设备名称
	private String deviceName;
	
	public DeviceAlertCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeviceAlertCount(long deviceAlertCountId, String deviceAlertCountIp,
			int deviceAlertCount) {
		super();
		this.deviceAlertCountId = deviceAlertCountId;
		this.deviceAlertCountIp = deviceAlertCountIp;
		this.deviceAlertCount = deviceAlertCount;
	}
	public long getDeviceAlertCountId() {
		return deviceAlertCountId;
	}
	public void setDeviceAlertCountId(long deviceAlertCountId) {
		this.deviceAlertCountId = deviceAlertCountId;
	}
	public String getDeviceAlertCountIp() {
		return deviceAlertCountIp;
	}
	public void setDeviceAlertCountIp(String deviceAlertCountIp) {
		this.deviceAlertCountIp = deviceAlertCountIp;
	}
	public int getDeviceAlertCount() {
		return deviceAlertCount;
	}
	public void setDeviceAlertCount(int deviceAlertCount) {
		this.deviceAlertCount = deviceAlertCount;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}
