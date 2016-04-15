package com.soc.service.events.impl;

import java.util.HashMap;
import java.util.List;

import com.soc.dao.events.FilterDao;
import com.soc.model.events.Filter;
import com.soc.service.events.FilterService;

public class FilterServiceImpl implements FilterService
{
    private FilterDao filterDao;
    
    /**
     * {@inheritDoc}
     */
    public List<Filter> queryFilter()
    {
        return filterDao.queryFilter();
    }
    
    /**
     * {@inheritDoc}
     */
    public Filter queryFilterById(String filterId)
    {
        return filterDao.queryFilterById(filterId);
    }
    
    /**
     * {@inheritDoc}
     */
    public String queryTableNameByFilterId(String filterId)
    {
        return filterDao.queryTableNameByFilterId(filterId);
    }

    public FilterDao getFilterDao()
    {
        return filterDao;
    }

    public void setFilterDao(FilterDao filterDao)
    {
        this.filterDao = filterDao;
    }

}
