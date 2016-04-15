package com.soc.model.events;

import java.io.Serializable;

/**
 * 事件实体类
 * @author admin
 *
 */
public class QueryEvents implements Serializable
{
    private long queryEventsId; //事件ID
    
    private String queryEventsName; //事件名称
    
    private long queryEventsGroupId; //事件组ID
    
    private String queryEventsConditions; //查询条件
    
    private int queryEventsType; //事件类型（0：内置事件  1：自定义事件）
    
    public long getQueryEventsId()
    {
        return queryEventsId;
    }
    
    public void setQueryEventsId(long queryEventsId)
    {
        this.queryEventsId = queryEventsId;
    }
    
    public String getQueryEventsName()
    {
        return queryEventsName;
    }
    
    public void setQueryEventsName(String queryEventsName)
    {
        this.queryEventsName = queryEventsName;
    }
    
    public long getQueryEventsGroupId()
    {
        return queryEventsGroupId;
    }
    
    public void setQueryEventsGroupId(long queryEventsGroupId)
    {
        this.queryEventsGroupId = queryEventsGroupId;
    }
    
    public String getQueryEventsConditions()
    {
        return queryEventsConditions;
    }
    
    public void setQueryEventsConditions(String queryEventsConditions)
    {
        this.queryEventsConditions = queryEventsConditions;
    }
    
    public int getQueryEventsType()
    {
        return queryEventsType;
    }
    
    public void setQueryEventsType(int queryEventsType)
    {
        this.queryEventsType = queryEventsType;
    }
    
}
