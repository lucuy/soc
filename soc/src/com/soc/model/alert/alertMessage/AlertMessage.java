package com.soc.model.alert.alertMessage;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soc.model.conf.GlobalConfig;

/**
 * @author zhou
 *
 */
public class AlertMessage implements Serializable
{
    
    private long alertId;
    
    private String alertTitle;
    
    private int alertRank;
    
    private int alertReceiver;
    
    private int alertSendWay;
    
    private int alertState;
    
    //获得当前时间的毫秒数
    private long alertCreateDatetime;
    
    //转换后的时间
    private String date;
    
    //关联的事件id
    private long relEventsIdentification;
    
    private int alertNumber;
    
    //用户真姓名
    private String userRealName;
     
    //告警方式
    private String sendMode;
    
    //告警时间
    private Date alertDate;
    
    private long alertAssetId;
    
    private String alertAssetName;
    
    private String alertLogTableName;
    
    //关联事件的名称
    private String  alertEventName;
    
    //关联事件的类型
    private String alertEventType;
    
    //关联设备的ip
    private String alertDeviceIp = "";
    
    //设备类型
    private String alertDeviceType;
    
    //设备名称
    private String alertDeviceName;
    
    //告警规则ID
    private Long alarmRuleId;
    
    private long eventsSourceAdd; //源IP地址转换
    
    private long eventsSourcePort; //源端口
    
    private long eventsTargetAdd; //目标IP地址转换
    
    private long eventsTargetPort; //目标端口
    
    private String eventsSourceAddT; //源IP
    
    private String eventsTargetAddT; //目标IP
    
    private String workorder;		//工单状态
    
    private Date messageOperateDate; //最后修改日期
    
    private int alertMark;//0：内网设备产生的告警  1：外网设备产生的告警
    
    private long eventSendTime;//事件产生时间.
    private int isClose;//告警是否关闭，0：代表关闭，1：代表未关闭。
    
    
    public int getIsClose() {
		return isClose;
	}

	public void setIsClose(int isClose) {
		this.isClose = isClose;
	}

	public Date getMessageOperateDate() {
		return messageOperateDate;
	}

	public void setMessageOperateDate(Date messageOperateDate) {
		this.messageOperateDate = messageOperateDate;
	}

	public String getAlertEventName()
    {
		

			return alertEventName;
		
		
    }

    public void setAlertEventName(String alertEventName)
    {
        this.alertEventName = alertEventName;
    }

    public String getAlertEventType()
    {
			return alertEventType;
		
    }

    public void setAlertEventType(String alertEventType)
    {
        this.alertEventType = alertEventType;
    }

    public String getAlertDeviceIp()
    {
        return alertDeviceIp;
    }

    public void setAlertDeviceIp(String alertDeviceIp)
    {
        this.alertDeviceIp = alertDeviceIp;
    }

    public String getAlertDeviceType()
    {
        return alertDeviceType;
    }

    public void setAlertDeviceType(String alertDeviceType)
    {
        this.alertDeviceType = alertDeviceType;
    }
    
    public long getAlertId()
    {
        return alertId;
    }
    
    public void setAlertId(long alertId)
    {
        this.alertId = alertId;
    }
    
    public String getAlertTitle()
    {
        return alertTitle;
    }
    
    public void setAlertTitle(String alertTitle)
    {
        this.alertTitle = alertTitle;
    }
    
    public int getAlertRank()
    {
        return alertRank;
    }
    
    public void setAlertRank(int alertRank)
    {
        this.alertRank = alertRank;
    }
    
    public int getAlertState()
    {
        return alertState;
    }
    
    public void setAlertState(int alertState)
    {
        this.alertState = alertState;
    }
    
    public long getAlertCreateDatetime()
    {
        return alertCreateDatetime;
    }
    
    public void setAlertCreateDatetime(long alertCreateDatetime)
    {
        this.alertCreateDatetime = alertCreateDatetime;
    }
    
    public int getAlertNumber()
    {
        return alertNumber;
    }
    
    public void setAlertNumber(int alertNumber)
    {
        this.alertNumber = alertNumber;
    }
    
    public int getAlertReceiver()
    {
        return alertReceiver;
    }
    
    public void setAlertReceiver(int alertReceiver)
    {
        this.alertReceiver = alertReceiver;
    }
    
    public int getAlertSendWay()
    {
        return alertSendWay;
    }
    
    public void setAlertSendWay(int alertSendWay)
    {
        this.alertSendWay = alertSendWay;
    }
    
    public long getRelEventsIdentification()
    {
        return relEventsIdentification;
    }

    public void setRelEventsIdentification(long relEventsIdentification)
    {
        this.relEventsIdentification = relEventsIdentification;
    }

    public String getUserRealName()
    {
        return userRealName;
    }
    
    public void setUserRealName(String userRealName)
    {
        this.userRealName = userRealName;
    }
    
    public String getSendMode()
    {
        return sendMode;
    }
    
    public void setSendMode(String sendMode)
    {
        this.sendMode = sendMode;
    }


    public Date getAlertDate()
    {
        return alertDate;
    }

    public void setAlertDate(Date alertDate)
    {
        this.alertDate = alertDate;
    }
    


    public long getAlertAssetId()
    {
        return alertAssetId;
    }

    public void setAlertAssetId(long alertAssetId)
    {
        this.alertAssetId = alertAssetId;
    }

    public String getAlertAssetName()
    {
        return alertAssetName;
    }

    public void setAlertAssetName(String alertAssetName)
    {
        this.alertAssetName = alertAssetName;
    }

    public String getAlertLogTableName()
    {
        return alertLogTableName;
    }

    public void setAlertLogTableName(String alertLogTableName)
    {
        this.alertLogTableName = alertLogTableName;
    }

	public Long getAlarmRuleId() {
		return alarmRuleId;
	}

	public void setAlarmRuleId(Long alarmRuleId) {
		this.alarmRuleId = alarmRuleId;
	}

	public long getEventsSourceAdd() {
		return eventsSourceAdd;
	}

	public void setEventsSourceAdd(long eventsSourceAdd) {
		this.eventsSourceAdd = eventsSourceAdd;
	}

	
	public long getEventsTargetAdd() {
		return eventsTargetAdd;
	}

	public void setEventsTargetAdd(long eventsTargetAdd) {
		this.eventsTargetAdd = eventsTargetAdd;
	}

	
	public String getEventsSourceAddT()
    {
        return lIpTransitionStrIp(this.getEventsSourceAdd());
    }
    
    public void setEventsSourceAddT(String eventsSourceAddT)
    {
        this.eventsSourceAddT = eventsSourceAddT;
    }
    
    public String getEventsTargetAddT()
    {
        return lIpTransitionStrIp(this.getEventsTargetAdd());
    }
    
    public void setEventsTargetAddT(String eventsTargetAddT)
    {
        this.eventsTargetAddT = eventsTargetAddT;
    }
    
	 public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.getAlertCreateDatetime());
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getWorkorder() {
		return workorder;
	}

	public void setWorkorder(String workorder) {
		this.workorder = workorder;
	}
	
	public String getAlertDeviceName() {
		return alertDeviceName;
	}

	public void setAlertDeviceName(String alertDeviceName) {
		this.alertDeviceName = alertDeviceName;
	}
	

	public long getEventsSourcePort() {
		return eventsSourcePort;
	}

	public void setEventsSourcePort(long eventsSourcePort) {
		this.eventsSourcePort = eventsSourcePort;
	}

	public long getEventsTargetPort() {
		return eventsTargetPort;
	}

	public void setEventsTargetPort(long eventsTargetPort) {
		this.eventsTargetPort = eventsTargetPort;
	}

	public int getAlertMark() {
		return alertMark;
	}

	public void setAlertMark(int alertMark) {
		this.alertMark = alertMark;
	}

	public String lIpTransitionStrIp(long lipAdd) {
		String ipStr = "";
		StringBuffer sBuffer = new StringBuffer();
		if (lipAdd > 0) {
			sBuffer.append(String.valueOf((lipAdd >>> 24)));
			sBuffer.append(".");
			// 将高8位置0，然后右移16位
			sBuffer.append(String.valueOf((lipAdd & 0x00FFFFFF) >>> 16));
			sBuffer.append(".");
			// 将高16位置0，然后右移8位
			sBuffer.append(String.valueOf((lipAdd & 0x0000FFFF) >>> 8));
			sBuffer.append(".");
			// 将高24位置0
			sBuffer.append(String.valueOf((lipAdd & 0x000000FF)));
			ipStr = sBuffer.toString();
		}
		return ipStr;
	}
	
	public long lIpTransitionStrIpRevert(String lipAdd) {
		Long a = (long) 0,b = (long) 0,c = (long) 0,d = (long) 0;
		long tmp = 0;
		if (lipAdd != null && !"".equals(lipAdd)) {
			String[] str = lipAdd.split("\\.");
			a = (Long.parseLong(str[0]) << 24);
			b = (Long.parseLong(str[1]) << 16);
			c = (Long.parseLong(str[2]) << 8);
			d = Long.parseLong(str[3]);
		}
		tmp = a + b + c + d;
		return tmp;
	}

	public long getEventSendTime() {
		return eventSendTime;
	}

	public void setEventSendTime(long eventSendTime) {
		this.eventSendTime = eventSendTime;
	}
    
}
