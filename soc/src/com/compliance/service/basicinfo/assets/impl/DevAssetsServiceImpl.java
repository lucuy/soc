package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.DevAssetsDao;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.service.basicinfo.assets.DevAssetsService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DevAssetsServiceImpl extends BaseServiceImpl implements DevAssetsService {
	private DevAssetsDao devAssetsDao;

	public DevAssetsDao getDevAssetsDao() {
		return devAssetsDao;
	}

	public void setDevAssetsDao(DevAssetsDao devAssetsDao) {
		this.devAssetsDao = devAssetsDao;
	}

	public int devAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return devAssetsDao.devAssetsCount(mapper);
	}

	public List<DevAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return devAssetsDao.query(map, startRow, pageSize);
	}

	public void devAssetsInsert(DevAssets devAssets) {
		// TODO Auto-generated method stub
		devAssetsDao.devAssetsInsert(devAssets);
	}

	public void devAssetsUpdate(DevAssets devAssets) {
		// TODO Auto-generated method stub
		devAssetsDao.devAssetsUpdate(devAssets);

	}

	public void devAssetsDelete(int id) {
		// TODO Auto-generated method stub
		devAssetsDao.devAssetsDelete(id);
	}

	public DevAssets devAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return devAssetsDao.devAssetsQueryById(id);
	}

	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = devAssetsDao.devAssetsCount(map);
		page.setTotalCount(rowCount);
		List<DevAssets> list = devAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int devAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return devAssetsDao.devAssetsPreciseCount(mapper);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = devAssetsDao.devAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<DevAssets> list = devAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
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
	 * @return List<DevAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DevAssets> queryAllDevAssets(){
		return devAssetsDao.queryAllDevAssets();
	}

}
