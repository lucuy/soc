package com.soc.dao.events.ibatis;

import java.util.HashMap;
import java.util.List;

import com.soc.dao.events.FilterDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Filter;

public class FilterDaoIbatis extends BaseDaoIbatis implements FilterDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<Filter> queryFilter()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.filter");
    }
    
    /**
     * {@inheritDoc}
     */
    public Filter queryFilterById(String filterId)
    {
        return (Filter)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.filterById", filterId);
    }

    /**
     * {@inheritDoc}
     */
    public String queryTableNameByFilterId(String filterId)
    {
        return (String)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.tableNameByFilterId", filterId);
    }
    
}
