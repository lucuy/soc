package com.soc.model.warn;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * <预警发布实体类>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Warn implements Serializable
{
    //预警id
    private int warnId;
    
    //预警名称
    private String warnName;
    
    //预警描述
    private String warnDescription;
    
    //预警危害
    private String warnHarm;
    
    //预警类型
    private int warnType;
    
    //漏洞名称
    private String leakName;
    
    //漏洞类型
    private String leakType;
    
    //漏洞等级
    private int leakLevel;
    
    //状态
    private int status;
    
    //严重程度
    private int seriousLevel;
    
    //解决方案
    private String solution;
    
    //源发布日期
    private Date publicDate;
    
    //来源
    private String source;
    
    //发布人
    private String publisher;
    
    //受影响操作系统
    private String influenceSys;
    
    //漏洞编号
    private String leakNum;
    
    //受影响的服务器端口
    private String influenceInterface;
    
    public int getWarnId()
    {
        return warnId;
    }
    
    public void setWarnId(int warnId)
    {
        this.warnId = warnId;
    }
    
    public String getWarnName()
    {
        return warnName;
    }
    
    public void setWarnName(String warnName)
    {
        this.warnName = warnName;
    }
    
    public String getWarnDescription()
    {
        return warnDescription;
    }
    
    public void setWarnDescription(String warnDescription)
    {
        this.warnDescription = warnDescription;
    }
    
    public String getWarnHarm()
    {
        return warnHarm;
    }
    
    public void setWarnHarm(String warnHarm)
    {
        this.warnHarm = warnHarm;
    }
    
    public int getWarnType()
    {
        return warnType;
    }
    
    public void setWarnType(int warnType)
    {
        this.warnType = warnType;
    }
    
    public String getLeakName()
    {
        return leakName;
    }
    
    public void setLeakName(String leakName)
    {
        this.leakName = leakName;
    }
    
    public String getLeakType()
    {
        return leakType;
    }
    
    public void setLeakType(String leakType)
    {
        this.leakType = leakType;
    }
    
    public int getLeakLevel()
    {
        return leakLevel;
    }
    
    public void setLeakLevel(int leakLevel)
    {
        this.leakLevel = leakLevel;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public int getSeriousLevel()
    {
        return seriousLevel;
    }
    
    public void setSeriousLevel(int seriousLevel)
    {
        this.seriousLevel = seriousLevel;
    }
    
    public String getSolution()
    {
        return solution;
    }
    
    public void setSolution(String solution)
    {
        this.solution = solution;
    }
    
    public Date getPublicDate()
    {
        return publicDate;
    }
    
    public void setPublicDate(Date publicDate)
    {
        this.publicDate = publicDate;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public void setSource(String source)
    {
        this.source = source;
    }
    
    public String getPublisher()
    {
        return publisher;
    }
    
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    
    public String getInfluenceSys()
    {
        return influenceSys;
    }
    
    public void setInfluenceSys(String influenceSys)
    {
        this.influenceSys = influenceSys;
    }
    
    public String getLeakNum()
    {
        return leakNum;
    }
    
    public void setLeakNum(String leakNum)
    {
        this.leakNum = leakNum;
    }
    
    public String getInfluenceInterface()
    {
        return influenceInterface;
    }
    
    public void setInfluenceInterface(String influenceInterface)
    {
        this.influenceInterface = influenceInterface;
    }
    
}
