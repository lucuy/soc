package com.soc.model.monitor;

import java.util.Date;

public class MonitorTomcatTask {
	/**
	 * 
	 */
	private long tomId;//监控对象ID
	private String tomName;//监控对象名称
	private String tomIp;//监控对象IP
	private int tomPort;//监控对象端口
	private String tomUserName;//用户名称
	private String tomUserPass;//用户密码
	private String tomContacts;//联系方式
	private String tomAddress;//物理位置
	private String tomTaskDesc;//任务描述
	private int tomIsOnline;//健康状态 0在线 1 不在线
	private int tomTaskStatus;//启停状态  0 停止监控 1启动监控
	private Date regTime;//更新时间
	private int tomIsDelete;//删除状态
	private String tomUrl;//监控对象Url
	
	public String getTomUrl() {
		return tomUrl;
	}
	public void setTomUrl(String tomUrl) {
		this.tomUrl = tomUrl;
	}
	public long getTomId() {
		return tomId;
	}
	public void setTomId(long tomId) {
		this.tomId = tomId;
	}
	public String getTomName() {
		return tomName;
	}
	public void setTomName(String tomName) {
		this.tomName = tomName;
	}
	public String getTomIp() {
		return tomIp;
	}
	public void setTomIp(String tomIp) {
		this.tomIp = tomIp;
	}
	public int getTomPort() {
		return tomPort;
	}
	public void setTomPort(int tomPort) {
		this.tomPort = tomPort;
	}
	public String getTomUserName() {
		return tomUserName;
	}
	public void setTomUserName(String tomUserName) {
		this.tomUserName = tomUserName;
	}
	public String getTomUserPass() {
		return tomUserPass;
	}
	public void setTomUserPass(String tomUserPass) {
		this.tomUserPass = tomUserPass;
	}
	public String getTomContacts() {
		return tomContacts;
	}
	public void setTomContacts(String tomContacts) {
		this.tomContacts = tomContacts;
	}
	public String getTomAddress() {
		return tomAddress;
	}
	public void setTomAddress(String tomAddress) {
		this.tomAddress = tomAddress;
	}
	public String getTomTaskDesc() {
		return tomTaskDesc;
	}
	public void setTomTaskDesc(String tomTaskDesc) {
		this.tomTaskDesc = tomTaskDesc;
	}
	public int getTomIsOnline() {
		return tomIsOnline;
	}
	public void setTomIsOnline(int tomIsOnline) {
		this.tomIsOnline = tomIsOnline;
	}
	public int getTomTaskStatus() {
		return tomTaskStatus;
	}
	public void setTomTaskStatus(int tomTaskStatus) {
		this.tomTaskStatus = tomTaskStatus;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public int getTomIsDelete() {
		return tomIsDelete;
	}
	public void setTomIsDelete(int tomIsDelete) {
		this.tomIsDelete = tomIsDelete;
	}
}
