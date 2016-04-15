package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorDatabaseTaskDao;
import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorDatabaseTaskService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MonitorDatabaseTaskServiceImpl extends BaseServiceImpl implements MonitorDatabaseTaskService {
	private MonitorDatabaseTaskDao mdtDao;
	@SuppressWarnings("rawtypes")
	@Override
	public SearchResult<MonitorDatabaseTask> queryMonitorDatabaseTaskS(Map map, Page page) {
		page.setTotalCount(mdtDao.countMonitorDatabaseTask(map));
		List<MonitorDatabaseTask> list = mdtDao.queryMonitorDatabaseTaskS(map, page.getStartIndex(), page.getPageSize());
		SearchResult<MonitorDatabaseTask> sr = new SearchResult<MonitorDatabaseTask>();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorDatabaseTask(Map map) {
		return mdtDao.countMonitorDatabaseTask(map);
	}

	@Override
	public long insertMonitorDatabaseTask(MonitorDatabaseTask mdt) {
		return mdtDao.insertMonitorDatabaseTask(mdt);
	}

	@Override
	public void updateMonitorDatabaseTask(MonitorDatabaseTask mdt) {
		mdtDao.updateMonitorDatabaseTask(mdt);
	}

	@Override
	public void deleteMonitorDatabaseTask(long id) {
		mdtDao.deleteMonitorDatabaseTask(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void stopMonitorDatabaseTask(Map map) {
		mdtDao.stopMonitorDatabaseTask(map);
	}

	@Override
	public MonitorDatabaseTask detailMonitorDatabaseTask(long id) {
		return mdtDao.detailMonitorDatabaseTask(id);
	}

	@Override
	public String checkTaskName(String taskName) {
		return mdtDao.checkTaskName(taskName);
	}

	public MonitorDatabaseTaskDao getMdtDao() {
		return mdtDao;
	}

	public void setMdtDao(MonitorDatabaseTaskDao mdtDao) {
		this.mdtDao = mdtDao;
	}

	@Override
	public void updateDBOnlie(Map map) {
		mdtDao.updateDBOnlie(map);
	}

	@Override
	public List<MonitorDatabaseTask> queryAll() {
		return mdtDao.queryAll();
	}
	

}
