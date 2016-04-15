package com.soc.model.systemsetting;

import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  YinHaiping
 * @version  [版本号, 2012-9-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MemoryData
{
    protected Integer systemMemoryId;       //内存id
    protected String systemMemoryEmploy;    //已使用内存
    protected String systemMemoryTotal;     //总内存
    protected String systemMemoryPercent;   //内存百分比
    protected String systemMemoryRemain;    //内存剩余
    protected Date systemMemoryDate;      //记录时间
    
    public Integer getSystemMemoryId()
    {
        return systemMemoryId;
    }
    public void setSystemMemoryId(Integer systemMemoryId)
    {
        this.systemMemoryId = systemMemoryId;
    }
    public String getSystemMemoryEmploy()
    {
        return systemMemoryEmploy;
    }
    public void setSystemMemoryEmploy(String systemMemoryEmploy)
    {
        this.systemMemoryEmploy = systemMemoryEmploy;
    }
    public String getSystemMemoryTotal()
    {
        return systemMemoryTotal;
    }
    public void setSystemMemoryTotal(String systemMemoryTotal)
    {
        this.systemMemoryTotal = systemMemoryTotal;
    }
    public String getSystemMemoryPercent()
    {
        return systemMemoryPercent;
    }
    public void setSystemMemoryPercent(String systemMemoryPercent)
    {
        this.systemMemoryPercent = systemMemoryPercent;
    }
    public String getSystemMemoryRemain()
    {
        return systemMemoryRemain;
    }
    public void setSystemMemoryRemain(String systemMemoryRemain)
    {
        this.systemMemoryRemain = systemMemoryRemain;
    }
    public Date getSystemMemoryDate()
    {
        return systemMemoryDate;
    }
    public void setSystemMemoryDate(Date systemMemoryDate)
    {
        this.systemMemoryDate = systemMemoryDate;
    }
    
}