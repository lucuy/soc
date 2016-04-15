package com.soc.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <图表样式实体类>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-11-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Monitor implements Serializable
{
    
    // 监控id
    private int monitorId;
    
    // 监控的名称
    private String monitorName;
    
    // 监控的创建时间
    private Date monitorCreateDatetime;
    
    // 监控的更新时间
    private Date monitorUpdateDatetime;
    
    // 监控创建者登录名
    private String monitorCreateUsername;
    
    // 监控描述
    private String monitorMemo;
    
    // 监控的数据的数据源
    private String monitorDataUrl;
    
    // 关联的图表
    private Chart chart;
    
    // 图表的图例的标题
    private String relChartLegendTitle;
    
    //关联的数据对象
    private Object object;
    
    //图表位置
    private long station;
    
    public int getMonitorId()
    {
        return monitorId;
    }
    
    public void setMonitorId(int monitorId)
    {
        this.monitorId = monitorId;
    }
    
    public String getMonitorName()
    {
        return monitorName;
    }
    
    public void setMonitorName(String monitorName)
    {
        this.monitorName = monitorName;
    }
    
    public Date getMonitorCreateDatetime()
    {
        return monitorCreateDatetime;
    }
    
    public void setMonitorCreateDatetime(Date monitorCreateDatetime)
    {
        this.monitorCreateDatetime = monitorCreateDatetime;
    }
    
    public Date getMonitorUpdateDatetime()
    {
        return monitorUpdateDatetime;
    }
    
    public void setMonitorUpdateDatetime(Date monitorUpdateDatetime)
    {
        this.monitorUpdateDatetime = monitorUpdateDatetime;
    }
    
    public String getMonitorCreateUsername()
    {
        return monitorCreateUsername;
    }
    
    public void setMonitorCreateUsername(String monitorCreateUsername)
    {
        this.monitorCreateUsername = monitorCreateUsername;
    }
    
    public String getMonitorMemo()
    {
        return monitorMemo;
    }
    
    public void setMonitorMemo(String monitorMemo)
    {
        this.monitorMemo = monitorMemo;
    }
    
    public String getMonitorDataUrl()
    {
        return monitorDataUrl;
    }
    
    public void setMonitorDataUrl(String monitorDataUrl)
    {
        this.monitorDataUrl = monitorDataUrl;
    }
    
    public Chart getChart()
    {
        return chart;
    }
    
    public void setChart(Chart chart)
    {
        this.chart = chart;
    }
    
    public String getRelChartLegendTitle()
    {
        return relChartLegendTitle;
    }
    
    public void setRelChartLegendTitle(String relChartLegendTitle)
    {
        this.relChartLegendTitle = relChartLegendTitle;
    }
    
    public Object getObject()
    {
        return object;
    }
    
    public void setObject(Object object)
    {
        this.object = object;
    }
    
    public long getStation()
    {
        return station;
    }
    
    public void setStation(long station)
    {
        this.station = station;
    }
    
}
