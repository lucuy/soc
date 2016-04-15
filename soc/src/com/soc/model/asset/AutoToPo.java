package com.soc.model.asset;

public class AutoToPo {
	private String ip;
	private String superIp;
	private int type;//1，是交换机，2服务器
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSuperIp() {
		return superIp;
	}
	public void setSuperIp(String superIp) {
		this.superIp = superIp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
