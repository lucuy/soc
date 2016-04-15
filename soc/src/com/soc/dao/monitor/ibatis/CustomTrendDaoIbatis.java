package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.CustomTrendDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.monitor.CustomTrend;

public class CustomTrendDaoIbatis extends BaseDaoIbatis implements
		CustomTrendDao {

	@Override
	public List<CustomTrend> queryAllCustomTrend(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"customTrend.query", map);
	}

	@Override
	public List<Map> queryEvents(String sql) {
		return  this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"customTrend.queryEvents", sql);
	}

	@Override
	public int countAllCustomTrend(Map map) {
Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"customTrend.count", map);
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
	public CustomTrend queryById(long id) {
		
		return (CustomTrend) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"customTrend.queryById", id);
	}

	@Override
	public void update(CustomTrend customTrend) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"customTrend.update", customTrend);
	}

	@Override
	public void delete(long id) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"customTrend.delete",id);

	}

	@Override
	public void insert(CustomTrend customTrend) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"customTrend.insert", customTrend);
		
	}

	@Override
	public List<CustomTrend> queryCustomTrend(Map map, int startRow,
			int pageSize) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"customTrend.query", map,startRow,pageSize);
	}

	@Override
	public CustomTrend queryByName(String name) {
		return (CustomTrend) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"customTrend.queryByName", name);
	}

	@Override
	public int countEvents(String sql) {
		
		Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"customTrend.queryEventsCount", sql);
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
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
	public List<Events> queryAllEvents(String sql,int startRow, int pageSize) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"customTrend.queryAllEvents", sql,startRow,pageSize);
	}

}
