package com.soc.model.conf;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Timer;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.attribute.AttributeExtractorException;
import net.sf.ehcache.search.attribute.ValueObjectAttributeExtractor;

public class AgentModel extends ValueObjectAttributeExtractor implements Serializable 
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    // 操作系统
    private String operatingSystem;
    
    // Agent安装路径
    private String agentInstallPath;
    
    // CPU型号
    private String cpuType;
    
    // 主机名
    private String hostName;
    
    // IP地址
    private String ip;
    
    // MAC地址
    private String mac;
    
    // CPU占用率
    private int cpuOccupy;
    
    // 内存占用率
    private double cacheOccupy;
    
    // 内存总数
    private double memoryTotal;
    
    // 网络发送流量
    private double transmitFlow; 
    
    // 网络接收流量
    private double receiveFlow;
    
    // 系统时间
    private long SystemTime;
    
    // 开机时间
    private Date uptime;
    
    // 运行时间
    private String runtime;
    
    private String discSize;
    
    private String versionnumber; 
    
    public String getOperatingSystem()
    {
        return operatingSystem;
    }
    
    public void setOperatingSystem(String operatingSystem)
    {
        this.operatingSystem = operatingSystem;
    }
    
    public String getAgentInstallPath()
    {
        return agentInstallPath;
    }
    
    public void setAgentInstallPath(String agentInstallPath)
    {
        this.agentInstallPath = agentInstallPath;
    }
    
    public String getCpuType()
    {
        return cpuType;
    }

    public void setCpuType(String cpuType)
    {
        this.cpuType = cpuType;
    }

    public String getHostName()
    {
        return hostName;
    }
    
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }
    
    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public String getMac()
    {
        return mac;
    }
    
    public void setMac(String mac)
    {
        this.mac = mac;
    }
    
    public int getCpuOccupy()
    {
        return cpuOccupy;
    }

    public void setCpuOccupy(int cpuOccupy)
    {
        this.cpuOccupy = cpuOccupy;
    }

    public double getCacheOccupy() {
		return cacheOccupy;
	}

	public void setCacheOccupy(double cacheOccupy) {
		this.cacheOccupy = cacheOccupy;
	}

	public double getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(double memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

    public double getTransmitFlow()
    {
        return transmitFlow;
    }

    public void setTransmitFlow(double transmitFlow)
    {
        this.transmitFlow = transmitFlow;
    }

    public double getReceiveFlow()
    {
        return receiveFlow;
    }

    public void setReceiveFlow(double receiveFlow)
    {
        this.receiveFlow = receiveFlow;
    }

    public long getSystemTime()
    {
        return SystemTime;
    }

    public void setSystemTime(long systemTime)
    {
        SystemTime = systemTime;
    }

    public Date getUptime()
    {
        return uptime;
    }

    public void setUptime(Date uptime)
    {
        this.uptime = uptime;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public String getDiscSize()
    {
        return discSize;
    }

    public void setDiscSize(String discSize)
    {
        this.discSize = discSize;
    }

	public String getVersionnumber() {
		return versionnumber;
	}

	public void setVersionnumber(String versionnumber) {
		this.versionnumber = versionnumber;
	}

}
