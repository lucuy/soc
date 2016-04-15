package com.compliance.service.basicinfo.system.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.system.SystemDao;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.basicinfo.system.SystemService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SystemServiceImpl extends BaseServiceImpl implements SystemService {

	private SystemDao systemDao;

	public SystemDao getSystemDao() {
		return systemDao;
	}

	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}

	public int count(Map map) {
		return systemDao.count(map);
	}

	public SystemManager queryById(int id) {
		return systemDao.queryById(id);
	}

	public SystemManager queryBySysId(String sysId) {
		return systemDao.queryBySysId(sysId);
	}

	public List<SystemManager> query(Map map) {
		return systemDao.query(map);
	}

	public List<SystemManager> queryForPage(Map map, int startRow, int pageSize) {
		return systemDao.queryForPage(map, startRow, pageSize);
	}

	public int insert(SystemManager system) {
		return systemDao.insert(system);
	}

	public int insertProLine(SystemManager system) {
		return systemDao.insertProLine(system);
	}

	public void deleteProLine(String id) {
		systemDao.deleteProLine(id);
	}

	public void delete(int id) {
		systemDao.delete(id);

	}

	public void update(SystemManager system) {
		systemDao.update(system);
		systemDao.updateOther(system);//修改定级和备案中的系统信息
	}

	/*
	 * public List<Pro_Line> queryForPageScore(Map map, int startRow, int
	 * pageSize) { return (List<Pro_Line>)systemDao.queryForPageScore(map,
	 * startRow, pageSize); }
	 */

	public void insertRecord(String sysId) {
		this.systemDao.insertRecord(sysId);

	}

	public void deleteRecord(String id) {
		this.systemDao.deleteRecord(id);

	}

	/**
	 * 模糊查询数据列表
	 */
	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = systemDao.count(map);
		page.setTotalCount(rowCount);
		List<SystemManager> list = systemDao.query(map, page.getStartIndex(),
				page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int countPrecise(Map map) {
		// TODO Auto-generated method stub
		return systemDao.countPrecise(map);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = systemDao.countPrecise(map);
		page.setTotalCount(rowCount);
		List<SystemManager> list = systemDao.queryPrecise(map,
				page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

}
