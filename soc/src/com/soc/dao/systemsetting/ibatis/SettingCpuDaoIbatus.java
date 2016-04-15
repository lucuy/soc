package com.soc.dao.systemsetting.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SettingCpuDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.CpuData;

public class SettingCpuDaoIbatus extends BaseDaoIbatis implements SettingCpuDao
{

    @Override
    public void setCpuData(CpuData cpuData)
    {
        getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"settingcpu.insert", cpuData);
    }

    @Override
    public int count(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"count.query", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }

    @Override
    public List<CpuData> query(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"cpu.query",map,startRow,pageSize);
    }

    @Override
    public List<CpuData> cpuQuery()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.cpu");
    }

    @Override
    public List<CpuData> cpuQueryOne()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryone.cpu");
    }
    
}