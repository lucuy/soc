package com.soc.model.progressmsg;

import java.io.Serializable;
import java.util.Date;

public class MonitorAlarmHistory implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    

    
    // 告警指标
    private String monitorAlarmIndex;
    
 
    // 发生告警的资产ip
    private String ip;
    
    // 发生告警的当前值
    private String monitorAlarmCurrentValue;
    
    // 发生告警的时间
    private Date monitorAlarmTime;

	public String getMonitorAlarmIndex() {
		return monitorAlarmIndex;
	}

	public void setMonitorAlarmIndex(String monitorAlarmIndex) {
		this.monitorAlarmIndex = monitorAlarmIndex;
	}

	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getMonitorAlarmCurrentValue() {
		return monitorAlarmCurrentValue;
	}

	public void setMonitorAlarmCurrentValue(String monitorAlarmCurrentValue) {
		this.monitorAlarmCurrentValue = monitorAlarmCurrentValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getMonitorAlarmTime() {
		return monitorAlarmTime;
	}

	public void setMonitorAlarmTime(Date monitorAlarmTime) {
		this.monitorAlarmTime = monitorAlarmTime;
	}
    

    
   
    
}
