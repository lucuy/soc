package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.Monitor;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorDaoIbatis extends BaseDaoIbatis implements MonitorDao
{
    
    @Override
    public List<Monitor> queryAll()
    {
        // TODO Auto-generated method stub
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitor.query");
    }
    
    @Override
    public Monitor queryById(long id)
    {
        // TODO Auto-generated method stub
        return (Monitor)super.queryForObject(GlobalConfig.sqlId+"monitor.queryById", id);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<Monitor> queryMonitorAjax(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitor.query", map, startRow, pageSize);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public int queryMonitorCount(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"monitor.count", map);
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
	public void insertChar(Monitor monitor) {
	
		getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"monitor.insertChar", monitor);
	}

	@Override
	public int countMonitorCustom(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"monitor.queryCustomCount", map);
	}

	@Override
	public List<Monitor> queryMonitorCustom(Map<String, Object> map,
			int startIndex, int pageSize) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"monitor.queryCustom",map,startIndex,pageSize);
	}

	@Override
	public void deleteCustom(long l) {
		 getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"monitor.deleteCustom", l);
		
	}

	@Override
	public void updateChar(Monitor monitor) {
		 getSqlMapClientTemplate().update(GlobalConfig.sqlId+"monitor.updateCustom", monitor);
		
	}

	@Override
	public Monitor selectMonitorById(Map map) {
		return (Monitor) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"monitor.queryCustom", map);
	}
    
    
}
