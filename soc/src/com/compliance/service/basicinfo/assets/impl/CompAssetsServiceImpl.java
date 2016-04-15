package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.CompAssetsDao;
import com.compliance.model.basicinfo.assets.CompAssets;
import com.compliance.service.basicinfo.assets.CompAssetsService;

import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CompAssetsServiceImpl extends BaseServiceImpl implements CompAssetsService {
	private CompAssetsDao compAssetsDao;

	public CompAssetsDao getCompAssetsDao() {
		return compAssetsDao;
	}

	public void setCompAssetsDao(CompAssetsDao compAssetsDao) {
		this.compAssetsDao = compAssetsDao;
	}

	public int compAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return compAssetsDao.compAssetsCount(mapper);
	}

	public List<CompAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return compAssetsDao.query(map, startRow, pageSize);
	}

	public void compAssetsInsert(CompAssets compAssets) {
		// TODO Auto-generated method stub
		compAssetsDao.compAssetsInsert(compAssets);
	}

	public void compAssetsUpdate(CompAssets compAssets) {
		// TODO Auto-generated method stub
		compAssetsDao.compAssetsUpdate(compAssets);

	}

	public void compAssetsDelete(int id) {
		// TODO Auto-generated method stub
		compAssetsDao.compAssetsDelete(id);
	}

	public CompAssets compAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return compAssetsDao.compAssetsQueryById(id);
	}

	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = compAssetsDao.compAssetsCount(map);
		page.setTotalCount(rowCount);
		List<CompAssets> list = compAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int compAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return compAssetsDao.compAssetsPreciseCount(mapper);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = compAssetsDao.compAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<CompAssets> list = compAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<CompAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<CompAssets> queryAllCompAssets() {
		return compAssetsDao.queryAllCompAssets();
	}

}
