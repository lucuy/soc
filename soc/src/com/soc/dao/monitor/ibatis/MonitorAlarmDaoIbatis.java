package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorAlarmDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorAlarm;

public class MonitorAlarmDaoIbatis extends BaseDaoIbatis implements MonitorAlarmDao
{

    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"monitorAlarm.count", map);
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

    /**
     * {@inheritDoc}
     */
    public List<MonitorAlarm> query(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitorAlarm.query", map, startRow, pageSize);
    }

    /**
     * {@inheritDoc}
     */
    public long insert(MonitorAlarm monitorAlarm)
    {
        long userId = 0;
        userId = (Long)super.create(GlobalConfig.sqlId+"monitorAlarm.insert", monitorAlarm);
        return userId;
    }

	@Override
	public List<Map<String,Object>> queryTenAlarmMessage(Map map) {
		// TODO Auto-generated method stub
		return (List<Map<String,Object>>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitorAlarm.GetTenMonitorAPMMessage" , map);
	}
    
}
