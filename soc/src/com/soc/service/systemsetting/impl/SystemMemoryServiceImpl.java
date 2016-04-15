package com.soc.service.systemsetting.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.SystemMemoryDao;
import com.soc.model.systemsetting.CpuData;
import com.soc.model.systemsetting.MemoryData;
import com.soc.service.systemsetting.SystemMemoryService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SystemMemoryServiceImpl implements SystemMemoryService
{
    private SystemMemoryDao systemMemoryDao;
    
    @Override
    public List<MemoryData> memoryQuery()
    {
        return systemMemoryDao.memoryQuery();
    }
    
    @Override
    public List<MemoryData> memoryQueryOne()
    {
        return systemMemoryDao.memoryQueryOne();
    }

    @Override
    public void insert(MemoryData memoryData)
    {
        systemMemoryDao.insert(memoryData);
    }

    public SystemMemoryDao getSystemMemoryDao()
    {
        return systemMemoryDao;
    }

    public void setSystemMemoryDao(SystemMemoryDao systemMemoryDao)
    {
        this.systemMemoryDao = systemMemoryDao;
    }

    @Override
    public SearchResult memoryQuery(Map map, Page page)
    {
        int rowsCount = systemMemoryDao.count(map);
        page.setTotalCount(rowsCount);
        List<MemoryData> list = systemMemoryDao.memoryQuery(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }

}