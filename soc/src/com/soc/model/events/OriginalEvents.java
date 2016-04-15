/*
 * 文 件 名:  OriginalEvents.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2013-3-1
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.model.events;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2013-3-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OriginalEvents implements Serializable
{
    private long originalId;
    
    private String originalContent;
    
    private String relAssetName;
    
    private int relAssetId;
    
    public int getRelAssetId()
    {
        return relAssetId;
    }

    public void setRelAssetId(int relAssetId)
    {
        this.relAssetId = relAssetId;
    }

    public long getOriginalId()
    {
        return originalId;
    }

    public void setOriginalId(long originalId)
    {
        this.originalId = originalId;
    }

    public String getOriginalContent()
    {
        return originalContent;
    }

    public void setOriginalContent(String originalContent)
    {
        this.originalContent = originalContent;
    }

    public String getRelAssetName()
    {
        return relAssetName;
    }
    public void setRelAssetName(String relAssetName)
    {
        this.relAssetName = relAssetName;
    }
    
    
}
