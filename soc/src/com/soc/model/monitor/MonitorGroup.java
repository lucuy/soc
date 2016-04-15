package com.soc.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <监控组的实体类> 
 * <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-10-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorGroup implements Serializable
{
    
    //监控组id
    private long monitorGroupId;
    
    //监控组的名称
    private String monitorGroupName;
    
    //监控租的父节点Id
    private long monitorGroupParentId;
    
    //监控组的创建时间
    private Date monitorGroupCreateDateTime;
    
    //监控组的更新时间
    private Date monitorGroupUpdateDateTime;
    
    //监控租的描述
    private String monitorGroupMemo;
    
    //监控组的标记删除
    private int monitorGroupIsDelete;
    
    //创建者登录名
    private String monitorGroupCreateUserLoginName;
    
    //关联的图表的ids
    private String relMonitorIds;
    
    public long getMonitorGroupId()
    {
        return monitorGroupId;
    }
    
    public void setMonitorGroupId(long monitorGroupId)
    {
        this.monitorGroupId = monitorGroupId;
    }
    
    public String getMonitorGroupName()
    {
        return monitorGroupName;
    }
    
    public void setMonitorGroupName(String monitorGroupName)
    {
        this.monitorGroupName = monitorGroupName;
    }
    
    public long getMonitorGroupParentId()
    {
        return monitorGroupParentId;
    }
    
    public void setMonitorGroupParentId(long monitorGroupParentId)
    {
        this.monitorGroupParentId = monitorGroupParentId;
    }
    
    public Date getMonitorGroupCreateDateTime()
    {
        return monitorGroupCreateDateTime;
    }
    
    public void setMonitorGroupCreateDateTime(Date monitorGroupCreateDateTime)
    {
        this.monitorGroupCreateDateTime = monitorGroupCreateDateTime;
    }
    
    public Date getMonitorGroupUpdateDateTime()
    {
        return monitorGroupUpdateDateTime;
    }
    
    public void setMonitorGroupUpdateDateTime(Date monitorGroupUpdateDateTime)
    {
        this.monitorGroupUpdateDateTime = monitorGroupUpdateDateTime;
    }
    
    public String getMonitorGroupMemo()
    {
        return monitorGroupMemo;
    }
    
    public void setMonitorGroupMemo(String monitorGroupMemo)
    {
        this.monitorGroupMemo = monitorGroupMemo;
    }
    
    public int getMonitorGroupIsDelete()
    {
        return monitorGroupIsDelete;
    }
    
    public void setMonitorGroupIsDelete(int monitorGroupIsDelete)
    {
        this.monitorGroupIsDelete = monitorGroupIsDelete;
    }
    
    public String getMonitorGroupCreateUserLoginName()
    {
        return monitorGroupCreateUserLoginName;
    }
    
    public void setMonitorGroupCreateUserLoginName(String monitorGroupCreateUserLoginName)
    {
        this.monitorGroupCreateUserLoginName = monitorGroupCreateUserLoginName;
    }
    
    public String getRelMonitorIds()
    {
        return relMonitorIds;
    }
    
    public void setRelMonitorIds(String relMonitorIds)
    {
        this.relMonitorIds = relMonitorIds;
    }
    
}
