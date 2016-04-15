package com.soc.dao.systemsetting.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SystemMemoryDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.MemoryData;

public class SystemMemoryDaoIbatis extends BaseDaoIbatis implements SystemMemoryDao
{

    @Override
    public void insert(MemoryData memoryData)
    {
        getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"settingMemory.insert", memoryData);
    }

    @Override
    public int count(Map map)
    {
Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
try
{
    ob = super.queryForObject(GlobalConfig.sqlId+"memorycount.query", map);
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
    public List<MemoryData> memoryQuery(Map map, int startRow, int pageSize)
    {
        
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"settingMemory.query",map,startRow,pageSize);
    }
    
    

    @Override
    public List<MemoryData> memoryQuery()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.memory");
    }

    @Override
    public List<MemoryData> memoryQueryOne()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryone.memory");
    }
}