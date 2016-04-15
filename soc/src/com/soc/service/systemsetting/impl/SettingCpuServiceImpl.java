package com.soc.service.systemsetting.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.SettingCpuDao;
import com.soc.model.systemsetting.CpuData;
import com.soc.model.user.User;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SettingCpuService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SettingCpuServiceImpl extends BaseServiceImpl implements SettingCpuService
{
    private SettingCpuDao settingCpuDao;
    @Override
    public void setCpuData(CpuData cpuData)
    {
        settingCpuDao.setCpuData(cpuData);
    }
    public SettingCpuDao getSettingCpuDao()
    {
        return settingCpuDao;
    }
    public void setSettingCpuDao(SettingCpuDao settingCpuDao)
    {
        this.settingCpuDao = settingCpuDao;
    }
    
    public int count(Map map)
    {
        return settingCpuDao.count(map);
    }
    
    @Override
    public SearchResult query(Map map, Page page)
    {
        int rowsCount = settingCpuDao.count(map);
        page.setTotalCount(rowsCount);
        List<CpuData> list = settingCpuDao.query(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    @Override
    public List<CpuData> cpuQuery()
    {
        return settingCpuDao.cpuQuery();
    }
    @Override
    public List<CpuData> cpuQueryOne()
    {
        return settingCpuDao.cpuQueryOne();
    }
}