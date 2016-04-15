package com.soc.dao.events.ibatis;

import java.util.List;

import com.soc.dao.events.FilterByGroupDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Filter;
import com.soc.model.events.FilterByGroup;

public class FilterByGroupDaoIbatis extends BaseDaoIbatis implements FilterByGroupDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<FilterByGroup> queryFilterByGroup()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.filterByGroup");
    }
    
    /**
     * {@inheritDoc}
     */
    public FilterByGroup queryFilterByGroupById(String filterByGroupId)
    {
        return (FilterByGroup)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.filterByGroupById", filterByGroupId);
    }
    
}
