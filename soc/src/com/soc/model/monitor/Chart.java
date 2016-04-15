package com.soc.model.monitor;

import java.io.Serializable;

/**
 * 
 * <图表样式实体类>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Chart implements Serializable
{
    
    //样式id
    private long chartId;
    
    //样式名称
    private String chartName;
    
    //样式类型
    private String chartType;
    
    //样式的图例是否显示
    private int chartLegendVisible;
    
    //样式图例的位置
    private String chartLegendLocation;
    
    //样式轴上的样式(支持用户自定义)
    private String chartAxesStyle;
    
    //样式轴上的数据类型
    private String chartAxesType;
    
    //样式的位置
    private String chartAxesLocation;
    
    //样式是否有动画效果
    private int chartAnimationDuration;
    
    //设置轴上数据的最小值
    private int chartMinimum;
    
    //设置轴上数据的最大值
    private int chartMaximum;
    
    //设置每次增长的幅度
    private String chartInterval;
    
    public long getChartId()
    {
        return chartId;
    }
    
    public void setChartId(long chartId)
    {
        this.chartId = chartId;
    }
    
    public String getChartName()
    {
        return chartName;
    }
    
    public void setChartName(String chartName)
    {
        this.chartName = chartName;
    }
    
    public String getChartType()
    {
        return chartType;
    }
    
    public void setChartType(String chartType)
    {
        this.chartType = chartType;
    }
    
    public int getChartLegendVisible()
    {
        return chartLegendVisible;
    }
    
    public void setChartLegendVisible(int chartLegendVisible)
    {
        this.chartLegendVisible = chartLegendVisible;
    }
    
    public String getChartLegendLocation()
    {
        return chartLegendLocation;
    }
    
    public void setChartLegendLocation(String chartLegendLocation)
    {
        this.chartLegendLocation = chartLegendLocation;
    }
    
    public String getChartAxesStyle()
    {
        return chartAxesStyle;
    }
    
    public void setChartAxesStyle(String chartAxesStyle)
    {
        this.chartAxesStyle = chartAxesStyle;
    }
    
    public String getChartAxesType()
    {
        return chartAxesType;
    }
    
    public void setChartAxesType(String chartAxesType)
    {
        this.chartAxesType = chartAxesType;
    }
    
    public String getChartAxesLocation()
    {
        return chartAxesLocation;
    }
    
    public void setChartAxesLocation(String chartAxesLocation)
    {
        this.chartAxesLocation = chartAxesLocation;
    }
    
    public int getChartAnimationDuration()
    {
        return chartAnimationDuration;
    }
    
    public void setChartAnimationDuration(int chartAnimationDuration)
    {
        this.chartAnimationDuration = chartAnimationDuration;
    }
    
    public int getChartMinimum()
    {
        return chartMinimum;
    }
    
    public void setChartMinimum(int chartMinimum)
    {
        this.chartMinimum = chartMinimum;
    }
    
    public int getChartMaximum()
    {
        return chartMaximum;
    }
    
    public void setChartMaximum(int chartMaximum)
    {
        this.chartMaximum = chartMaximum;
    }
    
    public String getChartInterval()
    {
        return chartInterval;
    }
    
    public void setChartInterval(String chartInterval)
    {
        this.chartInterval = chartInterval;
    }
}
