package com.soc.service.events.impl;

import java.util.List;

import com.soc.dao.events.FilterByGroupDao;
import com.soc.model.events.FilterByGroup;
import com.soc.service.events.FilterByGroupService;

public class FilterByGroupServiceImpl implements FilterByGroupService
{

    private FilterByGroupDao filterByGroupDao;
    
    /**
     * {@inheritDoc}
     */
    public List<FilterByGroup> queryFilterByGroup()
    {
        return (List<FilterByGroup>)filterByGroupDao.queryFilterByGroup();
    }

    /**
     * {@inheritDoc}
     */
    public FilterByGroup queryFilterByGroupById(String filterByGroupId)
    {
        return filterByGroupDao.queryFilterByGroupById(filterByGroupId);
    }

    public FilterByGroupDao getFilterByGroupDao()
    {
        return filterByGroupDao;
    }

    public void setFilterByGroupDao(FilterByGroupDao filterByGroupDao)
    {
        this.filterByGroupDao = filterByGroupDao;
    }
    
}
