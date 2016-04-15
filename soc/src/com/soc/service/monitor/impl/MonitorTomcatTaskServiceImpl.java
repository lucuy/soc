package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorTomcatTaskDao;
import com.soc.model.monitor.MonitorTomcatTask;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorTomcatTaskService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MonitorTomcatTaskServiceImpl extends BaseServiceImpl implements
		MonitorTomcatTaskService {

	private MonitorTomcatTaskDao mttDao;
	public MonitorTomcatTaskDao getMttDao() {
		return mttDao;
	}
	public void setMttDao(MonitorTomcatTaskDao mttDao) {
		this.mttDao = mttDao;
	}
	@SuppressWarnings("rawtypes")
	public SearchResult<MonitorTomcatTask> queryMonitorTomcatTaskS(Map map,
			Page page) {
		// TODO Auto-generated method stub
		page.setTotalCount(mttDao.countMonitorTomcatTask(map));
		List<MonitorTomcatTask> list = mttDao.queryMonitorTomcatTaskS(map, page.getStartIndex(), page.getPageSize());
		SearchResult<MonitorTomcatTask> sr = new SearchResult<MonitorTomcatTask>();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
	@SuppressWarnings("rawtypes")
	public int countMonitorTomcatTask(Map map) {
		// TODO Auto-generated method stub
		return mttDao.countMonitorTomcatTask(map);
	}

	public long insertMonitorTomcatTask(MonitorTomcatTask mtt) {
		// TODO Auto-generated method stub
		return mttDao.insertMonitorTomcatTask(mtt);
	}

	public void updateMonitorTomcatTask(MonitorTomcatTask mtt) {
		// TODO Auto-generated method stub
		mttDao.updateMonitorTomcatTask(mtt);
	}

	public void deleteMonitorTomcatTask(long id) {
		// TODO Auto-generated method stub
		mttDao.deleteMonitorTomcatTask(id);

	}
	@SuppressWarnings("rawtypes")
	public void stopMonitorTomcatTask(Map map) {
		// TODO Auto-generated method stub
		mttDao.stopMonitorTomcatTask(map);

	}

	public MonitorTomcatTask detailMonitorTomcatTask(long id) {
		// TODO Auto-generated method stub
		return mttDao.detailMonitorTomcatTask(id);
	}

	public String checkTomName(String tomName) {
		// TODO Auto-generated method stub
		return mttDao.checkTomName(tomName);
	}

}
