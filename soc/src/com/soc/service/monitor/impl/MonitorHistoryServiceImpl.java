package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorHistoryDao;
import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorHistoryService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MonitorHistoryServiceImpl extends BaseServiceImpl implements
		MonitorHistoryService {
	private MonitorHistoryDao monitorHistoryDao;

	@Override
	public void insterMonitorAlarmHistory(MonitorAlarmHistory mah) {
		monitorHistoryDao.insterMonitorAlarmHistory(mah);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryMonitorAlarmHistories(Map map, Page page) {
		int rowsCount = monitorHistoryDao.countMonitorAlarmHistory(map);
		page.setTotalCount(rowsCount);
		List<MonitorAlarmHistory> list = monitorHistoryDao
				.queryMonitorAlarmHistories(map, page.getStartIndex(),
						page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void insterWinProgressMsg(WinProgressMsg wpm) {
		monitorHistoryDao.insterWinProgressMsg(wpm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryWinProgressMsgs(Map map, Page page) {
		int rowsCount = monitorHistoryDao.countWinProgressMsg(map);
		page.setTotalCount(rowsCount);
		List<WinProgressMsg> list = monitorHistoryDao.queryWinProgressMsgs(map,
				page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void insterWinServiceMsg(WinServiceMsg wsm) {
		monitorHistoryDao.insterWinServiceMsg(wsm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryWinServiceMsgs(Map map, Page page) {
		int totalCount = monitorHistoryDao.countWinServiceMsgg(map);
		page.setTotalCount(totalCount);
		List<WinServiceMsg> list = monitorHistoryDao.queryWinServiceMsgs(map,
				page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void insterWinSoftMsg(WinSoftMsg wfm) {
		monitorHistoryDao.insterWinSoftMsg(wfm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryWinSoftMsgs(Map map, Page page) {
		int totalCount = monitorHistoryDao.countWinSoftMsg(map);
		page.setTotalCount(totalCount);
		List<WinSoftMsg> list = monitorHistoryDao.queryWinSoftMsgs(map,
				page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void insterLinuxProgressMsg(LinuxProgressMsg lpm) {
		monitorHistoryDao.insterLinuxProgressMsg(lpm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryLinuxProgressMsgs(Map map, Page page) {
		int totalCount = monitorHistoryDao.countLinuxProgressMsg(map);
		page.setTotalCount(totalCount);
		List<LinuxProgressMsg> list = monitorHistoryDao.queryLinuxProgressMsgs(
				map, page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void insterLinuxServiceMsg(LinuxServiceMsg lsm) {
		monitorHistoryDao.insterLinuxServiceMsg(lsm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SearchResult queryLinuxServiceMsgs(Map map, Page page) {
		int totalCount = monitorHistoryDao.countLinuxServiceMsg(map);
		page.setTotalCount(totalCount);
		List<LinuxServiceMsg> list = monitorHistoryDao.queryLinuxServiceMsgs(
				map, page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public MonitorHistoryDao getMonitorHistoryDao() {
		return monitorHistoryDao;
	}

	public void setMonitorHistoryDao(MonitorHistoryDao monitorHistoryDao) {
		this.monitorHistoryDao = monitorHistoryDao;
	}

}
