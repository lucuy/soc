package com.soc.model.systemsetting;

import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  YinHaiping
 * @version  [版本号, 2012-8-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CpuData
{
    protected Integer systemCpuId; //cpu_id
    protected String systemCpuEmploy; //cpu_占用值
    protected String systemCpuPeak; //cpu_峰值
    protected Date systemCpuDate; //cpu_时间
    public Integer getSystemCpuId()
    {
        return systemCpuId;
    }
    public void setSystemCpuId(Integer systemCpuId)
    {
        this.systemCpuId = systemCpuId;
    }
    public String getSystemCpuEmploy()
    {
        return systemCpuEmploy;
    }
    public void setSystemCpuEmploy(String systemCpuEmploy)
    {
        this.systemCpuEmploy = systemCpuEmploy;
    }
    public String getSystemCpuPeak()
    {
        return systemCpuPeak;
    }
    public void setSystemCpuPeak(String systemCpuPeak)
    {
        this.systemCpuPeak = systemCpuPeak;
    }
    public Date getSystemCpuDate()
    {
        return systemCpuDate;
    }
    public void setSystemCpuDate(Date systemCpuDate)
    {
        this.systemCpuDate = systemCpuDate;
    }
    
    
}