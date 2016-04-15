package com.soc.model.servicemsg;

import java.io.Serializable;
import java.util.Date;

/***
 * 用来封装服务日志信息的类
 * @author gaosong
 *
 */
// mac|8|192.168.1.18|35/72/2013-04-21-14-55-28|35|Plug and Play|1|1|0|1
public class WinServiceMsg implements Serializable{
	// mac地址
private String mac;
// ip
private String ip;
//时间戳
private String timestamp;
//服务名
private String serviceName;
//安装状态
private String installStatus;
//操作状态 正在运行....未运行
private String operatestatus;
//是否可卸载
private String canBeUninstall;
//是否可停止
private String canBeStop;
//日志服务序号
private int serviceNO;
private Date fromDate;


public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
public String getCanBeStop() {
	return canBeStop;
}
public void setCanBeStop(String canBeStop) {
	this.canBeStop = canBeStop;
}
public String getMac() {
	return mac;
}
public void setMac(String mac) {
	this.mac = mac;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public String getServiceName() {
	return serviceName;
}
public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
}
public String getInstallStatus() {
	return installStatus;
}
public void setInstallStatus(String installStatus) {
	this.installStatus = installStatus;
}
public String getOperatestatus() {
	return operatestatus;
}
public void setOperatestatus(String operatestatus) {
	this.operatestatus = operatestatus;
}
public String getCanBeUninstall() {
	return canBeUninstall;
}
public void setCanBeUninstall(String canBeUninstall) {
	this.canBeUninstall = canBeUninstall;
}
public int getServiceNO() {
	return serviceNO;
}
public void setServiceNO(int serviceNO) {
	this.serviceNO = serviceNO;
}


}
