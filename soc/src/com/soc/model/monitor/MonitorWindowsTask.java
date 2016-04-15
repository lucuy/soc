package com.soc.model.monitor;

import java.util.Date;

import com.soc.model.monitor.computer.windows.WindowsDetail;

public class MonitorWindowsTask {
	
	private long winId;//任务编号
	private String winName;//任务名称
	private String winIp;//ip地址
	private String snmpType;//监控类型：snmp
	private int winPort;//端口
	private String communityName;//团体名称
	private String winContacts;//联系方式
	private String winAddress;//物理地址
	private String winTaskDesc;//任务描述
	private int winIsOnline;//健康状态 0在线 1 不在线
	private int winTaskStatus;//启停状态  0 停止监控 1启动监控
	private Date regTime;//修改时间
	private int winIsDelete;//删除状态
	public long getWinId() {
		return winId;
	}
	public void setWinId(long winId) {
		this.winId = winId;
	}
	public String getWinName() {
		return winName;
	}
	public void setWinName(String winName) {
		this.winName = winName;
	}
	public String getWinIp() {
		return winIp;
	}
	public void setWinIp(String winIp) {
		this.winIp = winIp;
	}
	public String getSnmpType() {
		return snmpType;
	}
	public void setSnmpType(String snmpType) {
		this.snmpType = snmpType;
	}
	public int getWinPort() {
		return winPort;
	}
	public void setWinPort(int winPort) {
		this.winPort = winPort;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getWinContacts() {
		return winContacts;
	}
	public void setWinContacts(String winContacts) {
		this.winContacts = winContacts;
	}
	public String getWinAddress() {
		return winAddress;
	}
	public void setWinAddress(String winAddress) {
		this.winAddress = winAddress;
	}
	public String getWinTaskDesc() {
		return winTaskDesc;
	}
	public void setWinTaskDesc(String winTaskDesc) {
		this.winTaskDesc = winTaskDesc;
	}
	public int getWinIsOnline() {
		return winIsOnline;
	}
	public void setWinIsOnline(int winIsOnline) {
		this.winIsOnline = winIsOnline;
	}
	public int getWinTaskStatus() {
		return winTaskStatus;
	}
	public void setWinTaskStatus(int winTaskStatus) {
		this.winTaskStatus = winTaskStatus;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public int getWinIsDelete() {
		return winIsDelete;
	}
	public void setWinIsDelete(int winIsDelete) {
		this.winIsDelete = winIsDelete;
	}
}
