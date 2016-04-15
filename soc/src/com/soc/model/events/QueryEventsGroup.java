package com.soc.model.events;

import java.io.Serializable;
import java.util.List;

/**
 * 事件组实体类
 * @author 王纯
 *
 */
public class QueryEventsGroup implements Serializable
{
    private long queryEventsGroupId; //组ID
    
    private String queryEventsGroupName; //组名称
    
    private long queryEventsGroupParentId; //组父ID
    
    private int queryEventsGroupType; //组类型（0：内置事件组   1：自定义事件组）
    
    private String queryEventsConditions; //事件查询条件
    
    private List<QueryEvents> queryEventsList; //认证事件对象集合
    
    private List<QueryEvents> queryEventsAttestationList; //认证事件对象集合
    
    private List<QueryEvents> queryEventsConfigList; //配置事件对象集合
    
    private List<QueryEvents> queryEventsOperationList; //操作事件对象集合
    
    private List<QueryEvents> queryEventsResourcesList; //操作事件对象集合
    
    private List<QueryEventsGroup> groupNodeList; //组二级以下的组节点对象集合
    
    public long getQueryEventsGroupId()
    {
        return queryEventsGroupId;
    }
    
    public void setQueryEventsGroupId(long queryEventsGroupId)
    {
        this.queryEventsGroupId = queryEventsGroupId;
    }
    
    public String getQueryEventsGroupName()
    {
        return queryEventsGroupName;
    }
    
    public void setQueryEventsGroupName(String queryEventsGroupName)
    {
        this.queryEventsGroupName = queryEventsGroupName;
    }
    
    public long getQueryEventsGroupParentId()
    {
        return queryEventsGroupParentId;
    }
    
    public void setQueryEventsGroupParentId(long queryEventsGroupParentId)
    {
        this.queryEventsGroupParentId = queryEventsGroupParentId;
    }
    
    public int getQueryEventsGroupType()
    {
        return queryEventsGroupType;
    }
    
    public void setQueryEventsGroupType(int queryEventsGroupType)
    {
        this.queryEventsGroupType = queryEventsGroupType;
    }
    
    public List<QueryEvents> getQueryEventsAttestationList()
    {
        return queryEventsAttestationList;
    }
    
    public void setQueryEventsAttestationList(List<QueryEvents> queryEventsAttestationList)
    {
        this.queryEventsAttestationList = queryEventsAttestationList;
    }
    
    public List<QueryEvents> getQueryEventsConfigList()
    {
        return queryEventsConfigList;
    }
    
    public void setQueryEventsConfigList(List<QueryEvents> queryEventsConfigList)
    {
        this.queryEventsConfigList = queryEventsConfigList;
    }
    
    public List<QueryEvents> getQueryEventsOperationList()
    {
        return queryEventsOperationList;
    }
    
    public void setQueryEventsOperationList(List<QueryEvents> queryEventsOperationList)
    {
        this.queryEventsOperationList = queryEventsOperationList;
    }
    
    public List<QueryEvents> getQueryEventsResourcesList()
    {
        return queryEventsResourcesList;
    }
    
    public void setQueryEventsResourcesList(List<QueryEvents> queryEventsResourcesList)
    {
        this.queryEventsResourcesList = queryEventsResourcesList;
    }
    
    public List<QueryEventsGroup> getGroupNodeList()
    {
        return groupNodeList;
    }
    
    public void setGroupNodeList(List<QueryEventsGroup> groupNodeList)
    {
        this.groupNodeList = groupNodeList;
    }
    
    public String getQueryEventsConditions()
    {
        return queryEventsConditions;
    }
    
    public void setQueryEventsConditions(String queryEventsConditions)
    {
        this.queryEventsConditions = queryEventsConditions;
    }
    
    public List<QueryEvents> getQueryEventsList()
    {
        return queryEventsList;
    }
    
    public void setQueryEventsList(List<QueryEvents> queryEventsList)
    {
        this.queryEventsList = queryEventsList;
    }
    
}
