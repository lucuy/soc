package com.soc.dao.monitor.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorDatabaseTaskDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorDatabaseTask;

public class MonitorDatabaseTaskDaoIbatis extends BaseDaoIbatis implements 
	MonitorDatabaseTaskDao
{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<MonitorDatabaseTask> queryMonitorDatabaseTaskS(Map map,
			int startRow, int pageSize) {
		List<MonitorDatabaseTask> list;
		try{
			list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"mdt.queryAll", map, startRow, pageSize);
		}catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<MonitorDatabaseTask>();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorDatabaseTask(Map map) {
		try{
			return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mdt.count", map);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long insertMonitorDatabaseTask(MonitorDatabaseTask mdt) {
		try{
			return (Long) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"mdt.insert", mdt);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateMonitorDatabaseTask(MonitorDatabaseTask mdt) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mdt.update", mdt);
	}

	@Override
	public void deleteMonitorDatabaseTask(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"mdt.delete", id);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void stopMonitorDatabaseTask(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mdt.updateStatus", map);
		
	}

	@Override
	public MonitorDatabaseTask detailMonitorDatabaseTask(long id) {
		return (MonitorDatabaseTask) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mdt.queryDetailById", id);
	}

	@Override
	public String checkTaskName(String taskName) {
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mdt.queryDetailByName", taskName);
	}

	@Override
	public void updateDBOnlie(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mdt.updateOnline", map);
		
	}

	@Override
	public List<MonitorDatabaseTask> queryAll() {
		List<MonitorDatabaseTask> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"mdt.queryAllDB");
		} catch (Exception e) {
			list = new ArrayList<MonitorDatabaseTask>();
			e.printStackTrace();
		}
		return list;
	}

}
