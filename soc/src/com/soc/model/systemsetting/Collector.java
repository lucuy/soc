package com.soc.model.systemsetting;

/**
 * <采集器配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Collector
{
    private int collectorId; //采集器的编号
    
    private String collectorIp; //IP地址
    
    private String collectorWalkPort; //snmp walk端口
    
    private String collectorGroup; //团体名
    
    private String collectorTime; //轮循间隔
    
    private String collectorEnter; //snmp信息库导入
    
    private String collectorTrip; //snmp trap端口
    
    private String collectorLog; //syslog端口
    
    private String collectorAgentPort; //agent端口
    
    private String collectorUpTime; //agent上报间隔
    
    private String collectorIntervalTime;//agent轮循间隔
    
    private String collectorPattern; //范式化轮循间隔
    
    private String collectorBasic; //采集器名称
    
    private String collectorMac; //MAC地址
    
    private String collectorOid;
    
    private int collectorStatus;
    
    private int collectNetwork;
    
    //采集器是否在线
    private int collectorIsOnline;
    
    //采集器健康状态
    private int collectorIsSafe;
    
    //采集器接收事件数目
    private long collectorReceiveEvents;
    
    private int collectorDisplay;
    
    public int getCollectorIsOnline()
    {
        return collectorIsOnline;
    }
    
    public void setCollectorIsOnline(int collectorIsOnline)
    {
        this.collectorIsOnline = collectorIsOnline;
    }
    
    public int getCollectorIsSafe()
    {
        return collectorIsSafe;
    }
    
    public void setCollectorIsSafe(int collectorIsSafe)
    {
        this.collectorIsSafe = collectorIsSafe;
    }
    
    public long getCollectorReceiveEvents()
    {
        return collectorReceiveEvents;
    }
    
    public void setCollectorReceiveEvents(long collectorReceiveEvents)
    {
        this.collectorReceiveEvents = collectorReceiveEvents;
    }
    
    public int getCollectorId()
    {
        return collectorId;
    }
    
    public void setCollectorId(int collectorId)
    {
        this.collectorId = collectorId;
    }
    
    public String getCollectorIp()
    {
        return collectorIp;
    }
    
    public void setCollectorIp(String collectorIp)
    {
        this.collectorIp = collectorIp;
    }
    
    public String getCollectorWalkPort()
    {
        return collectorWalkPort;
    }
    
    public void setCollectorWalkPort(String collectorWalkPort)
    {
        this.collectorWalkPort = collectorWalkPort;
    }
    
    public String getCollectorGroup()
    {
        return collectorGroup;
    }
    
    public void setCollectorGroup(String collectorGroup)
    {
        this.collectorGroup = collectorGroup;
    }
    
    public String getCollectorTime()
    {
        return collectorTime;
    }
    
    public void setCollectorTime(String collectorTime)
    {
        this.collectorTime = collectorTime;
    }
    
    public String getCollectorEnter()
    {
        return collectorEnter;
    }
    
    public void setCollectorEnter(String collectorEnter)
    {
        this.collectorEnter = collectorEnter;
    }
    
    public String getCollectorTrip()
    {
        return collectorTrip;
    }
    
    public void setCollectorTrip(String collectorTrip)
    {
        this.collectorTrip = collectorTrip;
    }
    
    public String getCollectorLog()
    {
        return collectorLog;
    }
    
    public void setCollectorLog(String collectorLog)
    {
        this.collectorLog = collectorLog;
    }
    
    public String getCollectorAgentPort()
    {
        return collectorAgentPort;
    }
    
    public void setCollectorAgentPort(String collectorAgentPort)
    {
        this.collectorAgentPort = collectorAgentPort;
    }
    
    public String getCollectorUpTime()
    {
        return collectorUpTime;
    }
    
    public void setCollectorUpTime(String collectorUpTime)
    {
        this.collectorUpTime = collectorUpTime;
    }
    
    public String getCollectorIntervalTime()
    {
        return collectorIntervalTime;
    }
    
    public void setCollectorIntervalTime(String collectorIntervalTime)
    {
        this.collectorIntervalTime = collectorIntervalTime;
    }
    
    public String getCollectorPattern()
    {
        return collectorPattern;
    }
    
    public void setCollectorPattern(String collectorPattern)
    {
        this.collectorPattern = collectorPattern;
    }
    
    public String getCollectorBasic()
    {
        return collectorBasic;
    }
    
    public void setCollectorBasic(String collectorBasic)
    {
        this.collectorBasic = collectorBasic;
    }
    
    public String getCollectorMac()
    {
        return collectorMac;
    }
    
    public void setCollectorMac(String collectorMac)
    {
        this.collectorMac = collectorMac;
    }
    
    public String getCollectorOid()
    {
        return collectorOid;
    }
    
    public void setCollectorOid(String collectorOid)
    {
        this.collectorOid = collectorOid;
    }

    public int getCollectorStatus()
    {
        return collectorStatus;
    }

    public void setCollectorStatus(int collectorStatus)
    {
        this.collectorStatus = collectorStatus;
    }

    public int getCollectNetwork()
    {
        return collectNetwork;
    }

    public void setCollectNetwork(int collectNetwork)
    {
        this.collectNetwork = collectNetwork;
    }

	public int getCollectorDisplay() {
		return collectorDisplay;
	}

	public void setCollectorDisplay(int collectorDisplay) {
		this.collectorDisplay = collectorDisplay;
	}
}