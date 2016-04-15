package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorWindowsTaskDao;
import com.soc.model.monitor.MonitorWindowsTask;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorWindowsTaskService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MonitorWindowsTaskServiceImpl extends BaseServiceImpl implements MonitorWindowsTaskService {
	private MonitorWindowsTaskDao mwtDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public SearchResult<MonitorWindowsTask> queryMonitorWindowsTaskS( Map map,Page page) {
		page.setTotalCount(mwtDao.countMonitorWindowsTask(map));
		List<MonitorWindowsTask> list = mwtDao.queryMonitorWindowsTaskS(map, page.getStartIndex(), page.getPageSize());
		SearchResult<MonitorWindowsTask> sr = new SearchResult<MonitorWindowsTask>();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorWindowsTask(Map map) {
		return mwtDao.countMonitorWindowsTask(map);
	}
	
	@Override
	public long insertMonitorWindowsTask(MonitorWindowsTask mwt) {
		return mwtDao.insertMonitorWindowsTask(mwt);
	}
	
	@Override
	public void updateMonitorWindowsTask(MonitorWindowsTask mwt) {
		mwtDao.updateMonitorWindowsTask(mwt);
	}
	
	@Override
	public void deleteMonitorWindowsTask(long id) {
		mwtDao.deleteMonitorWindowsTask(id);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void stopMonitorWindowsTask(Map map) {
		mwtDao.stopMonitorWindowsTask(map);
	}

	@Override
	public MonitorWindowsTask detailMonitorWindowsTask(long id) {
		return mwtDao.detailMonitorWindowsTask(id);
	}

	@Override
	public String checkWinTaskName(String winTaskName) {
		return mwtDao.checkWinTaskName(winTaskName);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void updateWinOnlie(Map map) {
		mwtDao.updateWinOnlie(map);
	}

	@Override
	public List<MonitorWindowsTask> queryAll() {
		return mwtDao.queryAll();
	}
	public MonitorWindowsTaskDao getMwtDao() {
		return mwtDao;
	}
	public void setMwtDao(MonitorWindowsTaskDao mwtDao) {
		this.mwtDao = mwtDao;
	}

}
