/*
 * 文 件 名:  HistoryAsset.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-9-3
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.model.asset;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史资产实体类
 * 主要根据历史资产查看历史资产详情
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-9-3]
 * @see  
 * @since  [任务管理/历史资产管理/V100R001C001]
 */
public class HistoryAsset implements Serializable
{
    //历史资产id
    private long historyAssetId;
    
    //历史任务id
    private long historyAssetHistoryTaskId;
    
    //资产id;
    private long historyAssetAssetId;
    
    //资产名称
    private String historyAssetName;
    
    //资产Ip
    private String historyAssetIp;
    
    //资产组Id;
    private long historyAssetGroupId;
    
    //资产重要性
    private int histtoryAssetImportance;
    
    //资产主机名
    private String historyAssetHostName;
    
    //资产描述
    private String historyAssetMac;
    
    //创建时间
    private Date historyAssetCreateDateTime;
    
    // 更新时间
    private Date historyAssetUpdateDateTime;
    
    //用户登录名
    private String historyAssetUserLoginName;
    
    //节点Id
    private long historyAssetNodeId;
    
    //标记删除标识
    private int historyAssetIsDelete;
    
    //描述
    private String historyAssetMemo;
    
    public long getHistoryAssetId()
    {
        return historyAssetId;
    }

    public void setHistoryAssetId(long historyAssetId)
    {
        this.historyAssetId = historyAssetId;
    }

    public long getHistoryAssetHistoryTaskId()
    {
        return historyAssetHistoryTaskId;
    }

    public void setHistoryAssetHistoryTaskId(long historyAssetHistoryTaskId)
    {
        this.historyAssetHistoryTaskId = historyAssetHistoryTaskId;
    }

    public long getHistoryAssetAssetId()
    {
        return historyAssetAssetId;
    }

    public void setHistoryAssetAssetId(long historyAssetAssetId)
    {
        this.historyAssetAssetId = historyAssetAssetId;
    }

    public String getHistoryAssetName()
    {
        return historyAssetName;
    }

    public void setHistoryAssetName(String historyAssetName)
    {
        this.historyAssetName = historyAssetName;
    }

    public String getHistoryAssetIp()
    {
        return historyAssetIp;
    }

    public void setHistoryAssetIp(String historyAssetIp)
    {
        this.historyAssetIp = historyAssetIp;
    }

    public long getHistoryAssetGroupId()
    {
        return historyAssetGroupId;
    }

    public void setHistoryAssetGroupId(long historyAssetGroupId)
    {
        this.historyAssetGroupId = historyAssetGroupId;
    }

    public int getHisttoryAssetImportance()
    {
        return histtoryAssetImportance;
    }

    public void setHisttoryAssetImportance(int histtoryAssetImportance)
    {
        this.histtoryAssetImportance = histtoryAssetImportance;
    }

    public String getHistoryAssetHostName()
    {
        return historyAssetHostName;
    }

    public void setHistoryAssetHostName(String historyAssetHostName)
    {
        this.historyAssetHostName = historyAssetHostName;
    }

    public String getHistoryAssetMac()
    {
        return historyAssetMac;
    }

    public void setHistoryAssetMac(String historyAssetMac)
    {
        this.historyAssetMac = historyAssetMac;
    }

   
   
    public Date getHistoryAssetUpdateDateTime()
    {
        return historyAssetUpdateDateTime;
    }

    public void setHistoryAssetUpdateDateTime(Date historyAssetUpdateDateTime)
    {
        this.historyAssetUpdateDateTime = historyAssetUpdateDateTime;
    }

    public String getHistoryAssetUserLoginName()
    {
        return historyAssetUserLoginName;
    }

    public void setHistoryAssetUserLoginName(String historyAssetUserLoginName)
    {
        this.historyAssetUserLoginName = historyAssetUserLoginName;
    }

    public long getHistoryAssetNodeId()
    {
        return historyAssetNodeId;
    }

    public void setHistoryAssetNodeId(long historyAssetNodeId)
    {
        this.historyAssetNodeId = historyAssetNodeId;
    }

    public int getHistoryAssetIsDelete()
    {
        return historyAssetIsDelete;
    }

    public void setHistoryAssetIsDelete(int historyAssetIsDelete)
    {
        this.historyAssetIsDelete = historyAssetIsDelete;
    }

    public String getHistoryAssetMemo()
    {
        return historyAssetMemo;
    }

    public void setHistoryAssetMemo(String historyAssetMemo)
    {
        this.historyAssetMemo = historyAssetMemo;
    }

    public Date getHistoryAssetCreateDateTime()
    {
        return historyAssetCreateDateTime;
    }

    public void setHistoryAssetCreateDateTime(Date historyAssetCreateDateTime)
    {
        this.historyAssetCreateDateTime = historyAssetCreateDateTime;
    }
}
