package com.soc.dao.events.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.OriginalLogDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.OriginalEvents;

public class OriginalLogDaoIbatis extends BaseDaoIbatis implements OriginalLogDao
{

    /**
     * {@inheritDoc}
     */
    public void insertOrginalLog(Map<String, Object> map)
    {
        super.create(GlobalConfig.sqlId+"insert.originalLog", map);
    }

    /**
     * {@inheritDoc}
     */
    public String queryOriginalLog(Map<String, Object> map)
    {
        return (String)super.queryForObject(GlobalConfig.sqlId+"query.originalLog", map);
    }

    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return (Integer)super.queryForObject(GlobalConfig.sqlId+"exists.table", tableName);
    }

    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return (String)super.queryForObject(GlobalConfig.sqlId+"exists.originalLogSeq", seqName);
    }

    /**
     * {@inheritDoc}
     */
    public void createOriginalLogSeq(String seqName)
    {
        super.queryForObject(GlobalConfig.sqlId+"create.seq", seqName);
    }

    /**
     * {@inheritDoc}
     */
    public void createOriginalLogTable(Map<String, String> map)
    {
        super.create(GlobalConfig.sqlId+"create.originalLog", map);
    }

    /** {@inheritDoc} */
     
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        
        Object ob = null;
        //根据map中存储的条件查询符合条件的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"original.count", map);
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

    /** {@inheritDoc} */
     
    @Override
    public List<OriginalEvents> queryForList(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"original.queryList", map, startRow, pageSize);
       
    }

	@Override
	public List<OriginalEvents> exportOriginalEvents(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"original.queryList", map);
	}
    
}
