package com.soc.model.events;

import java.io.Serializable;
import java.util.Date;

import com.soc.model.conf.GlobalConfig;

/**
 * 事件概要类
 * @author 王纯
 *
 */
public class SummaryEvents implements Serializable
{
    private long eventsId; //概要事件ID
    
    private int eventsLevel; //概要事件级别
    
    private String eventsName; //概要事件名称
    
    private long eventsLogIdentification; //概要事件对应日志详细事件ID
    
    private long eventsSourceAdd; //源IP地址转换
    
    private int eventsSourcePort; //源端口
    
    private long eventsTargetAdd; //目标IP地址转换
    
    private int eventsTargetPort; //目标端口
    
    private int eventsNumber; //数量
    
    private String eventsSource; //事件源
    
    private Date eventsTime; //开始时间
    
    private String eventsType; //事件类型
    
    private int eventsTableType; //表类型0：认证失败表   1：认证成功表
    
    private String eventsSourceAddT; //源IP
    
    private String eventsTargetAddT; //目标IP
    
    private long eventsPutTime; //事件入库日期
    
    private String eventsDevProduct;
    
    private String eventsProgram;
    
    private String eventsDevvendor;
    
    private String eventsDevType;
    
    private String eventsDevName; //设备名称
    
    private String eventsProgramtype;
    
    private int eventsCustomd1;
    
    private int eventsRawid;
    
    private int assetId;
    
    private String assetName;
    
    private String relLogTableName; //关联日志表表名
    private String eventsNameCustomd;//自定义事件名称解决告警无法产生
    private String eventsTypeCustomd;//自定义事件类型解决告警无法产生
    
    public String getEventsName() {
		return eventsName;
	}

	

	public String getEventsType() {
		return eventsType;
	}

	

	//策略
    private String policy; 
    
    public long getEventsId()
    {
        return eventsId;
    }
     
    public void setEventsId(long eventsId)
    {
        this.eventsId = eventsId;
    }
    
    public int getEventsLevel()
    {
        return eventsLevel;
    }
    
    public void setEventsLevel(int eventsLevel)
    {
        this.eventsLevel = eventsLevel;
    }
    
    public String getEventsNameCustomd()
    {
    	try {
			return GlobalConfig.eventTypeTag.get(Long
					.parseLong(eventsName))!=null?GlobalConfig.eventTypeTag.get(Long
							.parseLong(eventsName)):eventsName;
		} catch (Exception e) {
			return eventsName;
		}
    }
    
    public void setEventsName(String eventsName)
    {
    	
        this.eventsName = eventsName;
    }
    
    public long getEventsSourceAdd()
    {
        return eventsSourceAdd;
    }
    
    public void setEventsSourceAdd(long eventsSourceAdd)
    {
        this.eventsSourceAdd = eventsSourceAdd;
    }
    
    public int getEventsSourcePort()
    {
        return eventsSourcePort;
    }
    
    public void setEventsSourcePort(int eventsSourcePort)
    {
        this.eventsSourcePort = eventsSourcePort;
    }
    
    public long getEventsTargetAdd()
    {
        return eventsTargetAdd;
    }
    
    public void setEventsTargetAdd(long eventsTargetAdd)
    {
        this.eventsTargetAdd = eventsTargetAdd;
    }
    
    public int getEventsTargetPort()
    {
        return eventsTargetPort;
    }
    
    public void setEventsTargetPort(int eventsTargetPort)
    {
        this.eventsTargetPort = eventsTargetPort;
    }
    
    public int getEventsNumber()
    {
        return eventsNumber;
    }
    
    public void setEventsNumber(int eventsNumber)
    {
        this.eventsNumber = eventsNumber;
    }
    
    public String getEventsSource()
    {
        return eventsSource;
    }
    
    public void setEventsSource(String eventsSource)
    {
        this.eventsSource = eventsSource;
    }
    
    public String getEventsTypeCustomd()
    {
    	try {
			
			return GlobalConfig.eventTypeTag.get(Long.valueOf(eventsType))!=null?GlobalConfig.eventTypeTag.get(Long.valueOf(eventsType)):eventsType;
		} catch (Exception e) {
			return eventsType;
		}
    }
    
    public void setEventsType(String eventsType)
    {	
    	
        this.eventsType = eventsType;
    }
    
    public int getEventsTableType()
    {
        return eventsTableType;
    }
    
    public void setEventsTableType(int eventsTableType)
    {
        this.eventsTableType = eventsTableType;
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
    
    public Date getEventsTime()
    {
        return eventsTime;
    }
    
    public void setEventsTime(Date eventsTime)
    {
        this.eventsTime = eventsTime;
    }
    
    public long getEventsPutTime()
    {
        return eventsPutTime;
    }
    
    public void setEventsPutTime(long eventsPutTime)
    {
        this.eventsPutTime = eventsPutTime;
    }
    
    public long getEventsLogIdentification()
    {
        return eventsLogIdentification;
    }

    public void setEventsLogIdentification(long eventsLogIdentification)
    {
        this.eventsLogIdentification = eventsLogIdentification;
    }

    public String getEventsDevProduct()
    {
        return eventsDevProduct;
    }
    
    public void setEventsDevProduct(String eventsDevProduct)
    {
        this.eventsDevProduct = eventsDevProduct;
    }
    
    public String getEventsProgram()
    {
        return eventsProgram;
    }
    
    public void setEventsProgram(String eventsProgram)
    {
        this.eventsProgram = eventsProgram;
    }
    
    public String getEventsDevvendor()
    {
        return eventsDevvendor;
    }
    
    public void setEventsDevvendor(String eventsDevvendor)
    {
        this.eventsDevvendor = eventsDevvendor;
    }
    
    public String getEventsDevType()
    {
        return eventsDevType;
    }
    
    public void setEventsDevType(String eventsDevType)
    {
        this.eventsDevType = eventsDevType;
    }
    
    public String getEventsDevName()
    {
        return eventsDevName;
    }
    
    public void setEventsDevName(String eventsDevName)
    {
        this.eventsDevName = eventsDevName;
    }
    
    public String getEventsProgramtype()
    {
        return eventsProgramtype;
    }
    
    public void setEventsProgramtype(String eventsProgramtype)
    {
        this.eventsProgramtype = eventsProgramtype;
    }
    
    public int getEventsCustomd1()
    {
    	try{
    		return Integer.parseInt(this.getEventsType());
    	}catch (Exception e) {
    		return eventsCustomd1;
		}
    }
    
    public void setEventsCustomd1(int eventsCustomd1)
    {
        this.eventsCustomd1 = eventsCustomd1;
    }
    
    public int getEventsRawid()
    {
        return eventsRawid;
    }
    
    public void setEventsRawid(int eventsRawid)
    {
        this.eventsRawid = eventsRawid;
    }
    
    public int getAssetId()
    {
        return assetId;
    }
    
    public void setAssetId(int assetId)
    {
        this.assetId = assetId;
    }
    
    public String getAssetName()
    {
        return assetName;
    }
    
    public void setAssetName(String assetName)
    {
        this.assetName = assetName;
    }
    
    public String getRelLogTableName()
    {
        return relLogTableName;
    }
    
    public void setRelLogTableName(String relLogTableName)
    {
        this.relLogTableName = relLogTableName;
    }

    public String getPolicy()
    {
        return policy;
    }

    public void setPolicy(String policy)
    {
        this.policy = policy;
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
    
}
