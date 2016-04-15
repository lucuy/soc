package com.soc.model.progressmsg;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <封装c过来的监控进程的信息>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-5-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LinuxProgressMsg implements Serializable{
//信息格式
	//日志种类 | 主机ip | 日志序号/日志总数/时间标签 | 日志序号 | PID |USER | PR | NI | VIRT | RES | SHR | S | %CPU | %MEM | TIME+ | COMMAND
	//13|192.168.1.67|3/12/2013-05-25-13-33-05|1|5659|root|20|0|17256|6616|1704|R|99.2|0.6|0:00.52|standard0
	// mac地址
	private String mac;
	//ip
	private String ip;
	//时间戳
	private String timestamp;
	//进程号
	private	int progressNO;

	//进程pid
	private String progressNamePID;
	//USER
	private String USER;
	//PR
	private String PR;
	//NI
	private String NI;
	//VIRT
	private String VIRT;
	//RES
	private String RES;
	//SHR
	private String SHR;
	//s
	private String S;
	//%CPU
	private String CPU;
	//%MEM
	private String MEM;
	//TIME+
	private String TIME;
	//COMMAND
	private String COMMAND;
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
	public int getProgressNO() {
		return progressNO;
	}
	public void setProgressNO(int progressNO) {
		this.progressNO = progressNO;
	}

	public String getProgressNamePID() {
		return progressNamePID;
	}
	public void setProgressNamePID(String progressNamePID) {
		this.progressNamePID = progressNamePID;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPR() {
		return PR;
	}
	public void setPR(String pR) {
		PR = pR;
	}
	public String getNI() {
		return NI;
	}
	public void setNI(String nI) {
		NI = nI;
	}
	public String getVIRT() {
		return VIRT;
	}
	public void setVIRT(String vIRT) {
		VIRT = vIRT;
	}
	public String getRES() {
		return RES;
	}
	public void setRES(String rES) {
		RES = rES;
	}
	public String getSHR() {
		return SHR;
	}
	public void setSHR(String sHR) {
		SHR = sHR;
	}
	public String getS() {
		return S;
	}
	public void setS(String s) {
		S = s;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
	public String getMEM() {
		return MEM;
	}
	public void setMEM(String mEM) {
		MEM = mEM;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getCOMMAND() {
		return COMMAND;
	}
	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}
	@Override
	public String toString() {
		return "LinuxProgressMsg [mac=" + mac + ", ip=" + ip + ", timestamp="
				+ timestamp + ", progressNO=" + progressNO
				+ ", progressNamePID=" + progressNamePID + ", USER=" + USER
				+ ", PR=" + PR + ", NI=" + NI + ", VIRT=" + VIRT + ", RES="
				+ RES + ", SHR=" + SHR + ", S=" + S + ", CPU=" + CPU + ", MEM="
				+ MEM + ", TIME=" + TIME + ", COMMAND=" + COMMAND + "]";
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	
}
