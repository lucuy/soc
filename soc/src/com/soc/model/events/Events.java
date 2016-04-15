package com.soc.model.events;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soc.model.conf.GlobalConfig;


/*public class Events implements Serializable
{
    private long eventsId;
    
    private String name; //事件名称
    
    private long aggregatedCount; //聚合数
    
    private long systype; //
    
    private long collectType; //收集类型
    
    private long sensorAddr; //采集器地址
    
    private String sensorName; //采集器名称
    
    private long collectorAddr; //收集器地址
    
    private String collectorName; //收集器名称
    
    private String cateGory; //种类
    
    private String type; //类型
    
    private long priority; //优先级
    
    private String oriPriority; //源始等级
    
    private long rawId; //事件本身编号
    
    private long duration; //持续时刻
    
    private long send; //发送字节
    
    private long receive; //收到字节
    
    private int protocol; //协议
    
    private int appProtocol; //应用协议
    
    private String object; //对象
    
    private String policy; //策略
    
    private String resource; //资源
    
    private String action; //动作
    
    private String intent; //目的
    
    private String result; //结果
    
    private long sAddr; //源IP地址
    
    private String sName; //源名称
    
    private long sPort; //源端口
    
    private String sprocess; //进程名称
    
    private String suserId; //源用户ID
    
    private String suserName; //源用户名称
    
    private long sTAddr; //源地址转换
    
    private int sTPort; //源地址转换端口
    
    private long dAddr; //目标地址
    
    private String dName; //目标名称
    
    private long dPort; //目标端口
    
    private String dProcess; //目标进程名称
    
    private String dUserId; //目标用户ID
    
    private String dUserName; //目标用户名称
    
    private long dTAddr; //目标地址转换
    
    private int dTPort; //目标地址转换端口
    
    private long devAdd; //设备地址
    
    private String devName; //设备名称
    
    private long devCategory; //设备分类
    
    private String devType; //设备型号
    
    private String devVendor; //设备厂商
    
    private String programType; //程序类型
    
    private String program; //程序名称
    
    private String customs1; //SIDType(SID帐号的类型)
    
    private String customs2; //CateGoryString ,根据事件来源对事件进行的分类。例如，安全性类别包括了：登录和注销、策略更改、权限使用、系统事件、对象访问、详情跟踪和帐户管理-->
    
    private String customs3; //OSVer(操作系统的版本号)
    
    private String customs4; //OSVer(日志内容的具体描述信息)
    
    private long customd1; //定制整型
    
    private long customd2; //定制整型
    
    private String devproduct;
    
    private String sessionId;
    
    private String customs5; //资产的名称
    
    private String customs6;
    
    private String customs7;
    
    private double customd3;
    
    private double customd4;
    
    private String requesturi;
    
    private String msg;
    
    private long sendTime;
    
    private long receptTime;
    
    private long identification; //解析日志标识
    
    private String originLog; //源始日志
    
    private String sAdd; //字符串源IP地址
    
    private String tAdd; //字串目标IP地址
    
    private String devAdds; //字符串设备ID地址
    
    private String sendTimes; //字符串结束日期
    
    private String receptTimes; //字符串开始日期
    
    private long assetId; //资源产Id
    
    private String devAddT; //设备IP地址转换
    
    private String tableName;
    
    *//**
     * 将long型日期转换为String
     * @param lTime
     * @return
     *//*
    public String lTimeTransitionStr(long lTime)
    {
        String timeStr = "";
        if (lTime > 0)
        {
            Date date = new Date(lTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
            timeStr = sdf.format(date);
        }
        return timeStr;
    }
    
    *//**
     * 将long型IP转换为String
     * @param lipAdd
     * @return
     *//*
    public String lIpTransitionStrIp(long lipAdd)
    {
        String ipStr = "";
        StringBuffer sBuffer = new StringBuffer();
        if (lipAdd > 0)
        {
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
    
    public String getSendTimes()
    {
        sendTimes = lTimeTransitionStr(sendTime);
        return sendTimes;
    }
    
    public void setSendTimes(String sendTimes)
    {
        this.sendTimes = sendTimes;
    }
    
    public String getReceptTimes()
    {
        receptTimes = lTimeTransitionStr(receptTime);
        return receptTimes;
    }
    
    public void setReceptTimes(String receptTimes)
    {
        this.receptTimes = receptTimes;
    }
    
    public String getDevAdds()
    {
        devAdds = lIpTransitionStrIp(devAdd);
        return devAdds;
    }
    
    public void setDevAdds(String devAdds)
    {
        this.devAdds = devAdds;
    }
    
    public String getsAdd()
    {
        sAdd = lIpTransitionStrIp(sAddr);
        return sAdd;
    }
    
    public void setsAdd(String sAdd)
    {
        this.sAdd = sAdd;
    }
    
    public String gettAdd()
    {
        tAdd = lIpTransitionStrIp(dAddr);
        return tAdd;
    }
    
    public void settAdd(String tAdd)
    {
        this.tAdd = tAdd;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public long getAggregatedCount()
    {
        return aggregatedCount;
    }
    
    public void setAggregatedCount(long aggregatedCount)
    {
        this.aggregatedCount = aggregatedCount;
    }
    
    public long getSystype()
    {
        return systype;
    }
    
    public void setSystype(long systype)
    {
        this.systype = systype;
    }
    
    public long getCollectType()
    {
        return collectType;
    }
    
    public void setCollectType(long collectType)
    {
        this.collectType = collectType;
    }
    
    public long getSensorAddr()
    {
        return sensorAddr;
    }
    
    public void setSensorAddr(long sensorAddr)
    {
        this.sensorAddr = sensorAddr;
    }
    
    public String getSensorName()
    {
        return sensorName;
    }
    
    public void setSensorName(String sensorName)
    {
        this.sensorName = sensorName;
    }
    
    public long getCollectorAddr()
    {
        return collectorAddr;
    }
    
    public void setCollectorAddr(long collectorAddr)
    {
        this.collectorAddr = collectorAddr;
    }
    
    public String getCollectorName()
    {
        return collectorName;
    }
    
    public void setCollectorName(String collectorName)
    {
        this.collectorName = collectorName;
    }
    
    public String getCateGory()
    {
        return cateGory;
    }
    
    public void setCateGory(String cateGory)
    {
        this.cateGory = cateGory;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public long getPriority()
    {
        return priority;
    }
    
    public void setPriority(long priority)
    {
        this.priority = priority;
    }
    
    public String getOriPriority()
    {
        return oriPriority;
    }
    
    public void setOriPriority(String oriPriority)
    {
        this.oriPriority = oriPriority;
    }
    
    public long getRawId()
    {
        return rawId;
    }
    
    public void setRawId(long rawId)
    {
        this.rawId = rawId;
    }
    
    public long getDuration()
    {
        return duration;
    }
    
    public void setDuration(long duration)
    {
        this.duration = duration;
    }
    
    public long getSend()
    {
        return send;
    }
    
    public void setSend(long send)
    {
        this.send = send;
    }
    
    public long getReceive()
    {
        return receive;
    }
    
    public void setReceive(long receive)
    {
        this.receive = receive;
    }
    
    public int getProtocol()
    {
        return protocol;
    }
    
    public void setProtocol(int protocol)
    {
        this.protocol = protocol;
    }
    
    public String getObject()
    {
        return object;
    }
    
    public void setObject(String object)
    {
        this.object = object;
    }
    
    public String getPolicy()
    {
        return policy;
    }
    
    public void setPolicy(String policy)
    {
        this.policy = policy;
    }
    
    public String getResource()
    {
        return resource;
    }
    
    public void setResource(String resource)
    {
        this.resource = resource;
    }
    
    public String getAction()
    {
        return action;
    }
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public String getIntent()
    {
        return intent;
    }
    
    public void setIntent(String intent)
    {
        this.intent = intent;
    }
    
    public String getResult()
    {
        return result;
    }
    
    public void setResult(String result)
    {
        this.result = result;
    }
    
    public long getsAddr()
    {
        return sAddr;
    }
    
    public void setsAddr(long sAddr)
    {
        this.sAddr = sAddr;
    }
    
    public String getsName()
    {
        return sName;
    }
    
    public void setsName(String sName)
    {
        this.sName = sName;
    }
    
    public long getsPort()
    {
        return sPort;
    }
    
    public void setsPort(long sPort)
    {
        this.sPort = sPort;
    }
    
    public String getSprocess()
    {
        return sprocess;
    }
    
    public void setSprocess(String sprocess)
    {
        this.sprocess = sprocess;
    }
    
    public String getSuserId()
    {
        return suserId;
    }
    
    public void setSuserId(String suserId)
    {
        this.suserId = suserId;
    }
    
    public String getSuserName()
    {
        return suserName;
    }
    
    public void setSuserName(String suserName)
    {
        this.suserName = suserName;
    }
    
    public long getsTAddr()
    {
        return sTAddr;
    }
    
    public void setsTAddr(long sTAddr)
    {
        this.sTAddr = sTAddr;
    }
    
    public int getsTPort()
    {
        return sTPort;
    }
    
    public void setsTPort(int sTPort)
    {
        this.sTPort = sTPort;
    }
    
    public long getdAddr()
    {
        return dAddr;
    }
    
    public void setdAddr(long dAddr)
    {
        this.dAddr = dAddr;
    }
    
    public String getdName()
    {
        return dName;
    }
    
    public void setdName(String dName)
    {
        this.dName = dName;
    }
    
    public long getdPort()
    {
        return dPort;
    }
    
    public void setdPort(long dPort)
    {
        this.dPort = dPort;
    }
    
    public String getdProcess()
    {
        return dProcess;
    }
    
    public void setdProcess(String dProcess)
    {
        this.dProcess = dProcess;
    }
    
    public String getdUserId()
    {
        return dUserId;
    }
    
    public void setdUserId(String dUserId)
    {
        this.dUserId = dUserId;
    }
    
    public String getdUserName()
    {
        return dUserName;
    }
    
    public void setdUserName(String dUserName)
    {
        this.dUserName = dUserName;
    }
    
    public long getdTAddr()
    {
        return dTAddr;
    }
    
    public void setdTAddr(long dTAddr)
    {
        this.dTAddr = dTAddr;
    }
    
    public int getdTPort()
    {
        return dTPort;
    }
    
    public void setdTPort(int dTPort)
    {
        this.dTPort = dTPort;
    }
    
    public long getDevAdd()
    {
        return devAdd;
    }
    
    public void setDevAdd(long devAdd)
    {
        this.devAdd = devAdd;
    }
    
    public String getDevName()
    {
        return devName;
    }
    
    public void setDevName(String devName)
    {
        this.devName = devName;
    }
    
    public long getDevCategory()
    {
        return devCategory;
    }
    
    public void setDevCategory(long devCategory)
    {
        this.devCategory = devCategory;
    }
    
    public String getDevType()
    {
        return devType;
    }
    
    public void setDevType(String devType)
    {
        this.devType = devType;
    }
    
    public String getDevVendor()
    {
        return devVendor;
    }
    
    public void setDevVendor(String devVendor)
    {
        this.devVendor = devVendor;
    }
    
    public String getProgramType()
    {
        return programType;
    }
    
    public void setProgramType(String programType)
    {
        this.programType = programType;
    }
    
    public String getProgram()
    {
        return program;
    }
    
    public void setProgram(String program)
    {
        this.program = program;
    }
    
    public String getCustoms1()
    {
        return customs1;
    }
    
    public void setCustoms1(String customs1)
    {
        this.customs1 = customs1;
    }
    
    public String getCustoms2()
    {
        return customs2;
    }
    
    public void setCustoms2(String customs2)
    {
        this.customs2 = customs2;
    }
    
    public String getCustoms3()
    {
        return customs3;
    }
    
    public void setCustoms3(String customs3)
    {
        this.customs3 = customs3;
    }
    
    public String getCustoms4()
    {
        return customs4;
    }
    
    public void setCustoms4(String customs4)
    {
        this.customs4 = customs4;
    }
    
    public long getCustomd1()
    {
        return customd1;
    }
    
    public void setCustomd1(long customd1)
    {
        this.customd1 = customd1;
    }
    
    public long getCustomd2()
    {
        return customd2;
    }
    
    public void setCustomd2(long customd2)
    {
        this.customd2 = customd2;
    }
    
    public String getDevproduct()
    {
        return devproduct;
    }
    
    public void setDevproduct(String devproduct)
    {
        this.devproduct = devproduct;
    }
    
    public String getSessionId()
    {
        return sessionId;
    }
    
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
    
    public String getCustoms5()
    {
        return customs5;
    }
    
    public void setCustoms5(String customs5)
    {
        this.customs5 = customs5;
    }
    
    public String getCustoms6()
    {
        return customs6;
    }
    
    public void setCustoms6(String customs6)
    {
        this.customs6 = customs6;
    }
    
    public String getCustoms7()
    {
        return customs7;
    }
    
    public void setCustoms7(String customs7)
    {
        this.customs7 = customs7;
    }
    
    public double getCustomd3()
    {
        return customd3;
    }
    
    public void setCustomd3(double customd3)
    {
        this.customd3 = customd3;
    }
    
    public double getCustomd4()
    {
        return customd4;
    }
    
    public void setCustomd4(double customd4)
    {
        this.customd4 = customd4;
    }
    
    public String getRequesturi()
    {
        return requesturi;
    }
    
    public void setRequesturi(String requesturi)
    {
        this.requesturi = requesturi;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public long getSendTime()
    {
        return sendTime;
    }
    
    public void setSendTime(long sendTime)
    {
        this.sendTime = sendTime;
    }
    
    public long getReceptTime()
    {
        return receptTime;
    }
    
    public void setReceptTime(long receptTime)
    {
        this.receptTime = receptTime;
    }
    
    public long getIdentification()
    {
        return identification;
    }
    
    public void setIdentification(long identification)
    {
        this.identification = identification;
    }
    
    public long getEventsId()
    {
        return eventsId;
    }
    
    public void setEventsId(long eventsId)
    {
        this.eventsId = eventsId;
    }
    
    public String getOriginLog()
    {
        return originLog;
    }
    
    public void setOriginLog(String originLog)
    {
        this.originLog = originLog;
    }
    
    public int getAppProtocol()
    {
        return appProtocol;
    }
    
    public void setAppProtocol(int appProtocol)
    {
        this.appProtocol = appProtocol;
    }
    
    public long getAssetId()
    {
        return assetId;
    }
    
    public void setAssetId(long assetId)
    {
        this.assetId = assetId;
    }
    
    public String getDevAddT()
    {
        devAddT = lIpTransitionStrIp(devAdd);
        return devAddT;
    }
    
    public void setDevAddT(String devAddT)
    {
        this.devAddT = devAddT;
    }
    
    public String getTableName()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	
        return sdf.format(new Date());
    }
    
    public void setTableName(String tableName)
    {
    	
        this.tableName = tableName;
    }
    
*/
public class Events implements Serializable {
	private long eventsId;

	private String name; // 事件名称

	private long aggregatedCount; // 聚合数

	private long systype; //

	private long collectType; // 收集类型

	private long sensorAddr; // 采集器地址

	private String sensorName; // 采集器名称

	private long collectorAddr; // 收集器地址

	private String collectorName; // 收集器名称

	private String cateGory; // 种类

	private String type; // 类型

	private long priority; // 优先级

	private String oriPriority; // 源始等级

	private long rawId; // 事件本身编号

	private long duration; // 持续时刻

	private long send; // 发送字节

	private long receive; // 收到字节

	private int protocol; // 协议

	private int appProtocol; // 应用协议

	private String object; // 对象

	private String policy; // 策略

	private String resource; // 资源

	private String action; // 动作

	private String intent; // 目的

	private String result; // 结果

	private long sAddr; // 源IP地址

	private String sName; // 源名称

	private long sPort; // 源端口

	private String sprocess; // 进程名称

	private String suserId; // 源用户ID

	private String suserName; // 源用户名称

	private long sTAddr; // 源地址转换

	private int sTPort; // 源地址转换端口

	private long dAddr; // 目标地址

	private String dName; // 目标名称

	private long dPort; // 目标端口

	private String dProcess; // 目标进程名称

	private String dUserId; // 目标用户ID

	private String dUserName; // 目标用户名称

	private long dTAddr; // 目标地址转换

	private int dTPort; // 目标地址转换端口

	private long devAdd; // 设备地址

	private String devName; // 设备名称

	private long devCategory; // 设备分类

	private String devType; // 设备型号

	private String devVendor; // 设备厂商

	private String programType; // 程序类型

	private String program; // 程序名称

	private String customs1; // SIDType(SID帐号的类型)

	private String customs2; // CateGoryString
								// ,根据事件来源对事件进行的分类。例如，安全性类别包括了：登录和注销、策略更改、权限使用、系统事件、对象访问、详情跟踪和帐户管理-->

	private String customs3; // OSVer(操作系统的版本号)

	private String customs4; // OSVer(日志内容的具体描述信息)

	private long customd1; // 定制整型

	private long customd2; // 定制整型

	private String devproduct;

	private String sessionId;

	private String customs5; // 资产的名称

	private String customs6;

	private String customs7;//采集器mac 入大表的时候加进去 河南项目需要首页中采集器div中加链接,点数字能跳到这个采集器收到的事件

	private double customd3;

	private double customd4;

	private String requesturi;

	private String msg;

	private long sendTime;

	private long receptTime;

	private long identification; // 解析日志标识

	private String originLog; // 源始日志

	private String sAdd; // 字符串源IP地址

	private String tAdd; // 字串目标IP地址

	private String devAdds; // 字符串设备ID地址

	private String sendTimes; // 字符串结束日期

	private String receptTimes; // 字符串开始日期
	
	private String receptTimes1;// 年月日类型的日期

	private long assetId; // 资源产Id

	private String devAddT; // 设备IP地址转换

	private String tableName;
	private String nameCustomd;//自定义事件名称解决无法产生告警
	private String cateGoryCustomd;//自定义事件类别解决无法产生告警
	private String typeCustomd;//自定义事件类型解决无法产生告警
	
	public String getName() {
		return name;
	}

	

	public String getCateGory() {
		return cateGory;
	}

	

	public String getType() {
		return type;
	}

	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 将long型日期转换为String
	 * 
	 * @param lTime
	 * @return
	 */
	public String lTimeTransitionStr(long lTime) {
		String timeStr = "";
		if (lTime > 0) {
			Date date = new Date(lTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timeStr = sdf.format(date);
		}
		return timeStr;
	}

	/**
	 * 将long型日期转换为String
	 * 
	 * @param lTime
	 * @return
	 */
	public String lTimeTransitionStr1(long lTime) {
		String timeStr = "";
		if (lTime > 0) {
			Date date = new Date(lTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			timeStr = sdf.format(date);
		}
		return timeStr;
	}

	/**
	 * 将long型IP转换为String
	 * 
	 * @param lipAdd
	 * @return
	 */
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

	public String getSendTimes() {
		sendTimes = lTimeTransitionStr(sendTime);
		return sendTimes;
	}

	public void setSendTimes(String sendTimes) {
		this.sendTimes = sendTimes;
	}

	public String getReceptTimes() {
		receptTimes = lTimeTransitionStr(receptTime);
		return receptTimes;
	}

	public void setReceptTimes(String receptTimes) {
		this.receptTimes = receptTimes;
	}

	public String getReceptTimes1() {
		receptTimes1 =lTimeTransitionStr1(receptTime);
		return receptTimes1;
	}

	public void setReceptTimes1(String receptTimes1) {
		this.receptTimes1 = receptTimes1;
	}

	public String getDevAdds() {
		devAdds = lIpTransitionStrIp(devAdd);
		return devAdds;
	}

	public void setDevAdds(String devAdds) {
		this.devAdds = devAdds;
	}

	public String getsAdd() {
		sAdd = lIpTransitionStrIp(sAddr);
		return sAdd;
	}

	public void setsAdd(String sAdd) {
		this.sAdd = sAdd;
	}

	public String gettAdd() {
		tAdd = lIpTransitionStrIp(dAddr);
		return tAdd;
	}

	public void settAdd(String tAdd) {
		this.tAdd = tAdd;
	}

	public String getNameCustomd() {
		try {
			return GlobalConfig.eventTypeTag.get(Long
					.parseLong(name))!=null?GlobalConfig.eventTypeTag.get(Long
							.parseLong(name)):name;
		} catch (Exception e) {
			return name;
		}
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public long getAggregatedCount() {
		return aggregatedCount;
	}

	public void setAggregatedCount(long aggregatedCount) {
		this.aggregatedCount = aggregatedCount;
	}

	public long getSystype() {
		return systype;
	}

	public void setSystype(long systype) {
		this.systype = systype;
	}

	public long getCollectType() {
		return collectType;
	}

	public void setCollectType(long collectType) {
		this.collectType = collectType;
	}

	public long getSensorAddr() {
		return sensorAddr;
	}

	public void setSensorAddr(long sensorAddr) {
		this.sensorAddr = sensorAddr;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public long getCollectorAddr() {
		return collectorAddr;
	}

	public void setCollectorAddr(long collectorAddr) {
		this.collectorAddr = collectorAddr;
	}

	public String getCollectorName() {
		return collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getCateGoryCustomd() {
		try{
		return GlobalConfig.eventCategoryTag.get(cateGory)!=null?GlobalConfig.eventCategoryTag.get(cateGory):cateGory;
		}catch (Exception e) {
			return cateGory;
		}
	}

	public void setCateGory(String cateGory) {
		this.cateGory = cateGory;
	}

	public String getTypeCustomd() {
		try {
			return GlobalConfig.eventTypeTag.get(Long.valueOf(type))!=null?GlobalConfig.eventTypeTag.get(Long.valueOf(type)):type;
		} catch (Exception e) {
			e.printStackTrace();
			return type;
		}

	}

	public void setType(String type) {
	
		this.type = type;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public String getOriPriority() {
		return oriPriority;
	}

	public void setOriPriority(String oriPriority) {
		this.oriPriority = oriPriority;
	}

	public long getRawId() {
		return rawId;
	}

	public void setRawId(long rawId) {
		this.rawId = rawId;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getSend() {
		return send;
	}

	public void setSend(long send) {
		this.send = send;
	}

	public long getReceive() {
		return receive;
	}

	public void setReceive(long receive) {
		this.receive = receive;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getsAddr() {
		return sAddr;
	}

	public void setsAddr(long sAddr) {
		this.sAddr = sAddr;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public long getsPort() {
		return sPort;
	}

	public void setsPort(long sPort) {
		this.sPort = sPort;
	}

	public String getSprocess() {
		return sprocess;
	}

	public void setSprocess(String sprocess) {
		this.sprocess = sprocess;
	}

	public String getSuserId() {
		return suserId;
	}

	public void setSuserId(String suserId) {
		this.suserId = suserId;
	}

	public String getSuserName() {
		return suserName;
	}

	public void setSuserName(String suserName) {
		this.suserName = suserName;
	}

	public long getsTAddr() {
		return sTAddr;
	}

	public void setsTAddr(long sTAddr) {
		this.sTAddr = sTAddr;
	}

	public int getsTPort() {
		return sTPort;
	}

	public void setsTPort(int sTPort) {
		this.sTPort = sTPort;
	}

	public long getdAddr() {
		return dAddr;
	}

	public void setdAddr(long dAddr) {
		this.dAddr = dAddr;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public long getdPort() {
		return dPort;
	}

	public void setdPort(long dPort) {
		this.dPort = dPort;
	}

	public String getdProcess() {
		return dProcess;
	}

	public void setdProcess(String dProcess) {
		this.dProcess = dProcess;
	}

	public String getdUserId() {
		return dUserId;
	}

	public void setdUserId(String dUserId) {
		this.dUserId = dUserId;
	}

	public String getdUserName() {
		return dUserName;
	}

	public void setdUserName(String dUserName) {
		this.dUserName = dUserName;
	}

	public long getdTAddr() {
		return dTAddr;
	}

	public void setdTAddr(long dTAddr) {
		this.dTAddr = dTAddr;
	}

	public int getdTPort() {
		return dTPort;
	}

	public void setdTPort(int dTPort) {
		this.dTPort = dTPort;
	}

	public long getDevAdd() {
		return devAdd;
	}

	public void setDevAdd(long devAdd) {
		this.devAdd = devAdd;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public long getDevCategory() {
		return devCategory;
	}

	public void setDevCategory(long devCategory) {
		this.devCategory = devCategory;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevVendor() {
		return devVendor;
	}

	public void setDevVendor(String devVendor) {
		this.devVendor = devVendor;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getCustoms1() {
		return customs1;
	}

	public void setCustoms1(String customs1) {
		this.customs1 = customs1;
	}

	public String getCustoms2() {
		return customs2;
	}

	public void setCustoms2(String customs2) {
		this.customs2 = customs2;
	}

	public String getCustoms3() {
		return customs3;
	}

	public void setCustoms3(String customs3) {
		this.customs3 = customs3;
	}

	public String getCustoms4() {
		return customs4;
	}

	public void setCustoms4(String customs4) {
		this.customs4 = customs4;
	}

	public long getCustomd1() {
		try{
			return Integer.parseInt(this.getType());
		}catch (Exception e) {
		
			return customd1;
		}
	}

	public void setCustomd1(long customd1) {
		this.customd1 = customd1;
	}

	public long getCustomd2() {
		return customd2;
	}

	public void setCustomd2(long customd2) {
		this.customd2 = customd2;
	}

	public String getDevproduct() {
		return devproduct;
	}

	public void setDevproduct(String devproduct) {
		this.devproduct = devproduct;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCustoms5() {
		return customs5;
	}

	public void setCustoms5(String customs5) {
		this.customs5 = customs5;
	}

	public String getCustoms6() {
		return customs6;
	}

	public void setCustoms6(String customs6) {
		this.customs6 = customs6;
	}

	public String getCustoms7() {
		return customs7;
	}

	public void setCustoms7(String customs7) {
		this.customs7 = customs7;
	}

	public double getCustomd3() {
		return customd3;
	}

	public void setCustomd3(double customd3) {
		this.customd3 = customd3;
	}

	public double getCustomd4() {
		return customd4;
	}

	public void setCustomd4(double customd4) {
		this.customd4 = customd4;
	}

	public String getRequesturi() {
		return requesturi;
	}

	public void setRequesturi(String requesturi) {
		this.requesturi = requesturi;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public long getReceptTime() {
		return receptTime;
	}

	public void setReceptTime(long receptTime) {
		this.receptTime = receptTime;
	}

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public long getEventsId() {
		return eventsId;
	}

	public void setEventsId(long eventsId) {
		this.eventsId = eventsId;
	}

	public String getOriginLog() {
		return originLog;
	}

	public void setOriginLog(String originLog) {
		this.originLog = originLog;
	}

	public int getAppProtocol() {
		return appProtocol;
	}

	public void setAppProtocol(int appProtocol) {
		this.appProtocol = appProtocol;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getDevAddT() {
		devAddT = lIpTransitionStrIp(devAdd);
		return devAddT;
	}

	public void setDevAddT(String devAddT) {
		this.devAddT = devAddT;
	}

/*	public String getTableName() {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	
         return sdf.format(new Date());
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}*/


}
