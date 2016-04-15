package com.soc.model.servicemsg;

import java.io.Serializable;
import java.util.Date;

/***
 * 用来封装服务日志信息的类
 * @author gaosong
 *
 */
// mac|15|192.168.1.67|2/45/2013-05-25-13-33-05|2|[ + ]  atd
//日志种类 | 主机ip | 日志序号/日志总数/时间标签 |日志序号 | 一行内容
public class LinuxServiceMsg implements Serializable{
	// mac地址
private String mac;
// ip
private String ip;
//时间戳
private String timestamp;
//日志服务序号
private int serviceNO;
//一行内容
private String content;
private Date fromDate;
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
public int getServiceNO() {
	return serviceNO;
}
public void setServiceNO(int serviceNO) {
	this.serviceNO = serviceNO;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "LinuxServiceMsg [mac=" + mac + ", ip=" + ip + ", timestamp="
			+ timestamp + ", serviceNO=" + serviceNO + ", content=" + content
			+ "]";
}
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}




}
