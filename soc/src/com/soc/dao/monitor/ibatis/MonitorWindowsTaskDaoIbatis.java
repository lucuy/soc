package com.soc.dao.monitor.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorWindowsTaskDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.model.monitor.MonitorWindowsTask;

public class MonitorWindowsTaskDaoIbatis extends BaseDaoIbatis implements
		MonitorWindowsTaskDao {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<MonitorWindowsTask> queryMonitorWindowsTaskS( Map map,
			int startRow, int pageSize) {
		List<MonitorWindowsTask> list;
		try{
			list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"mwt.queryAll", map, startRow, pageSize);
		}catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<MonitorWindowsTask>();
		}
		return list;
	}

	@Override
	public List<MonitorWindowsTask> queryAll() {
		List<MonitorWindowsTask> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"mwt.queryAllWin");
		} catch (Exception e) {
			list = new ArrayList<MonitorWindowsTask>();
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorWindowsTask(Map map) {
		try{
			return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mwt.count", map);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long insertMonitorWindowsTask(MonitorWindowsTask mwt) {
		try{
			return (Long) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"mwt.insert", mwt);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateMonitorWindowsTask(MonitorWindowsTask mwt) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mwt.update", mwt);

	}

	@Override
	public void deleteMonitorWindowsTask(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"mwt.delete", id);

	}
	@SuppressWarnings("rawtypes")
	@Override
	public void stopMonitorWindowsTask(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mwt.updateStatus", map);

	}

	@Override
	public MonitorWindowsTask detailMonitorWindowsTask(long id) {
		return (MonitorWindowsTask) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mwt.queryDetailById", id);
	}

	@Override
	public String checkWinTaskName(String winTaskName) {
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mwt.queryDetailByName", winTaskName);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void updateWinOnlie(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mwt.updateOnline", map);
	}

}
