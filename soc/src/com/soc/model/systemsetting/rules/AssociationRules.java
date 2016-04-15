package com.soc.model.systemsetting.rules;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * <关联规则>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AssociationRules implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    // 过滤规则ID
    private String filterId;
    
    // 过滤规则名称
    private String filterName;
    
    // 过滤条件
    private String filterCondition;
    
    // 描述
    private String filterDescription;
    
    // 创建时间
    private Date filterCreateTime;
    
    // 创建者
    private String filterCreator;
    
    // 更新时间
    private Date filterUpdateTime;
    
    private Integer filterType;
    
    public String getFilterId()
    {
        return filterId;
    }
    
    public void setFilterId(String filterId)
    {
        this.filterId = filterId;
    }
    
    public String getFilterName()
    {
        return filterName;
    }
    
    public void setFilterName(String filterName)
    {
        this.filterName = filterName;
    }
    
    public String getFilterCondition()
    {
        return filterCondition;
    }
    
    public void setFilterCondition(String filterCondition)
    {
        this.filterCondition = filterCondition;
    }
    
    public String getFilterDescription()
    {
        return filterDescription;
    }

    public void setFilterDescription(String filterDescription)
    {
        this.filterDescription = filterDescription;
    }

    public Date getFilterCreateTime()
    {
        return filterCreateTime;
    }
    
    public void setFilterCreateTime(Date filterCreateTime)
    {
        this.filterCreateTime = filterCreateTime;
    }
    
    public String getFilterCreator()
    {
        return filterCreator;
    }
    
    public void setFilterCreator(String filterCreator)
    {
        this.filterCreator = filterCreator;
    }
    
    public Date getFilterUpdateTime()
    {
        return filterUpdateTime;
    }
    
    public void setFilterUpdateTime(Date filterUpdateTime)
    {
        this.filterUpdateTime = filterUpdateTime;
    }

    public Integer getFilterType()
    {
        return filterType;
    }

    public void setFilterType(Integer filterType)
    {
        this.filterType = filterType;
    }
}
