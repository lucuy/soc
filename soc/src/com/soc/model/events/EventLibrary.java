package com.soc.model.events;

import java.io.Serializable;

public class EventLibrary implements Serializable {

	/**
	 * 事件编号
	 */
	private String eventLibraryId;
	/**
	 * 事件类型
	 */
	private int eventLibraryType;
	/**
	 * 事件名称
	 */
	private String eventLibraryName;
	/**
	 * 描述
	 */
	private String discription;
	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 原因
	 */
	private String 	cause;
	public String getEventLibraryId() {
		return eventLibraryId;
	}
	public void setEventLibraryId(String eventLibraryId) {
		this.eventLibraryId = eventLibraryId;
	}
	public int getEventLibraryType() {
		return eventLibraryType;
	}
	public void setEventLibraryType(int eventLibraryType) {
		this.eventLibraryType = eventLibraryType;
	}
	public String getEventLibraryName() {
		return eventLibraryName;
	}
	public void setEventLibraryName(String eventLibraryName) {
		this.eventLibraryName = eventLibraryName;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	@Override
	public String toString() {
		return "EventLibrary [eventLibraryId=" + eventLibraryId
				+ ", eventLibraryType=" + eventLibraryType
				+ ", eventLibraryName=" + eventLibraryName + ", discription="
				+ discription + ", deviceType=" + deviceType + ", cause="
				+ cause + "]";
	}
	
	
}
