package com.soc.service.systemsetting;

import java.util.Date;

public interface SettingSyncDate {

	/**
	 * 设置系统的时间
	 * @param str
	 */
	public void settingNtpAddress(String str) ; 
	
	/**
	 * 获得当前的系统时间
	 * @return
	 */
	public Date getSystemDate() ; 
	
	/**
	 * 设置时间
	 * @return void
	 */
	public void setTime(String installTime) ; 
	
	/**
	 * 获取系统ntp服务器地址
	 * @return String
	 */
	public String getSyncIp() ; 
	
	/**
	 * ntp服务器地址入库
	 * @return void
	 */
	public void updateNtpIP(String ntpStr) ; 
	
}
