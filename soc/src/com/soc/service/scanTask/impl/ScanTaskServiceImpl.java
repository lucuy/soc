package com.soc.service.scanTask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.scanTask.ScanTaskDao;
import com.soc.model.ScanTask.ScanTask;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.scanTask.ScanTaskService;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class ScanTaskServiceImpl extends BaseServiceImpl implements
		ScanTaskService {
	private ScanTaskDao scanTaskDao;
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public SearchResult queryAllScanTasks(Map map, Page page) {
		int rowsCount =scanTaskDao.count(map);
		
		List<ScanTask> list = scanTaskDao.queryAllScanTasks(map, page.getStartIndex(), page.getPageSize());
		page.setTotalCount(rowsCount);
		SearchResult<ScanTask> sr = new SearchResult<ScanTask>();
		//对list进行修改
		for (ScanTask scanTask : list) {
			List<String> ipList= new ArrayList<String>();
			String [] ips = scanTask.getIpBunch().split(",");
			for (String ip : ips) {
				ipList.add(ip);
			}
			scanTask.setIpList(ipList);
		}
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public ScanTask queryScanTaskById(long id) {
		
		return scanTaskDao.queryScanTaskById(id);
	}

	@Override
	public ScanTask queryScanTaskByName(String name) {
		return scanTaskDao.queryScanTaskByName(name);
	}

	@Override
	public String queryScanTaskByFileName(String fileName) {
		return scanTaskDao.queryScanTaskByFileName(fileName);
	}

	@Override
	public void deleteById(long id) {
		scanTaskDao.deleteById(id);
	}

	@Override
	public void updateScanTask(ScanTask scanTask) {
		scanTaskDao.updateScanTask(scanTask);
	}

	@Override
	public void updateStateById(long id) {
		scanTaskDao.updateStateById(id);
	}

	@Override
	public void updateStateByFileName(String fileName) {
		scanTaskDao.updateStateByFileName(fileName);

	}

	public ScanTaskDao getScanTaskDao() {
		return scanTaskDao;
	}

	public void setScanTaskDao(ScanTaskDao scanTaskDao) {
		this.scanTaskDao = scanTaskDao;
	}

	@Override
	public void insertScanTask(ScanTask scanTask) {
		scanTaskDao.insertScanTask(scanTask);
	}

	

	
	

}
