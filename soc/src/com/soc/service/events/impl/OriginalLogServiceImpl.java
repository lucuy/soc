package com.soc.service.events.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.OriginalLogDao;
import com.soc.model.events.OriginalEvents;
import com.soc.model.policy.AddressPolicy;
import com.soc.service.events.OriginalLogService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class OriginalLogServiceImpl implements OriginalLogService
{
    private OriginalLogDao originalLogDao;
    
    /**
     * {@inheritDoc}
     */
    public void insertOrginalLog(Map<String, Object> map)
    {
        originalLogDao.insertOrginalLog(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public String queryOriginalLog(Map<String, Object> map)
    {
        return originalLogDao.queryOriginalLog(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return originalLogDao.existsTable(tableName);
    }
    
    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return originalLogDao.existsSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createOriginalLogSeq(String seqName)
    {
        originalLogDao.createOriginalLogSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createOriginalLogTable(Map<String, String> map)
    {
        originalLogDao.createOriginalLogTable(map);
    }

    public OriginalLogDao getOriginalLogDao()
    {
        return originalLogDao;
    }

    public void setOriginalLogDao(OriginalLogDao originalLogDao)
    {
        this.originalLogDao = originalLogDao;
    }

    /** {@inheritDoc} */
     
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return originalLogDao.count(map);
    }

    /** {@inheritDoc} */
     
    @Override
    public SearchResult queryOriginalEvents(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = originalLogDao.count(map);
        page.setTotalCount(rowsCount);
        List<OriginalEvents> list = originalLogDao.queryForList(map, page.getStartIndex(), page.getPageSize());
        //对查找结果进行处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
}
