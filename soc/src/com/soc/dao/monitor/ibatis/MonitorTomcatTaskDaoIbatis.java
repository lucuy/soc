package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorTomcatTaskDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorTomcatTask;

public class MonitorTomcatTaskDaoIbatis extends BaseDaoIbatis implements
		MonitorTomcatTaskDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<MonitorTomcatTask> queryMonitorTomcatTaskS(Map map,
			int startRow, int pageSize) {
		// TODO Auto-generated method stub
		List<MonitorTomcatTask> list;
		list=this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"mtt.queryAll",map,startRow,pageSize);
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorTomcatTask(Map map) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mtt.count",map);
	}

	@Override
	public long insertMonitorTomcatTask(MonitorTomcatTask mtt) {
		// TODO Auto-generated method stub
		return (Long) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"mtt.insert",mtt);
	}

	@Override
	public void updateMonitorTomcatTask(MonitorTomcatTask mtt) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mtt.update", mtt);

	}

	@Override
	public void deleteMonitorTomcatTask(long id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"mtt.delete", id);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void stopMonitorTomcatTask(Map map) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"mtt.updateStatus", map);

	}

	@Override
	public MonitorTomcatTask detailMonitorTomcatTask(long id) {
		// TODO Auto-generated method stub
		return (MonitorTomcatTask) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mtt.queryDetailById", id);
	}

	@Override
	public String checkTomName(String tomName) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"mtt.queryDetailByName", tomName);
	}

}
