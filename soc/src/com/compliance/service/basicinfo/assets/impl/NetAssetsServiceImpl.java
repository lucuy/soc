package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.NetAssetsDao;
import com.compliance.model.basicinfo.assets.NetAssets;
import com.compliance.service.basicinfo.assets.NetAssetsService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class NetAssetsServiceImpl extends BaseServiceImpl implements NetAssetsService {
	private NetAssetsDao netAssetsDao;

	public int netAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return netAssetsDao.netAssetsCount(mapper);
	}

	public List<NetAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return netAssetsDao.query(map, startRow, pageSize);
	}

	public void netAssetsInsert(NetAssets netAssets) {
		// TODO Auto-generated method stub
		netAssetsDao.netAssetsInsert(netAssets);
	}

	public void netAssetsUpdate(NetAssets netAssets) {
		// TODO Auto-generated method stub
		netAssetsDao.netAssetsUpdate(netAssets);

	}

	public void netAssetsDelete(int id) {
		// TODO Auto-generated method stub
		netAssetsDao.netAssetsDelete(id);
	}

	public NetAssets netAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return netAssetsDao.netAssetsQueryById(id);
	}

	public NetAssetsDao getNetAssetsDao() {
		return netAssetsDao;
	}

	public void setNetAssetsDao(NetAssetsDao netAssetsDao) {
		this.netAssetsDao = netAssetsDao;
	}

	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = netAssetsDao.netAssetsCount(map);
		page.setTotalCount(rowCount);
		List<NetAssets> list = netAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int netAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return netAssetsDao.netAssetsPreciseCount(mapper);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = netAssetsDao.netAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<NetAssets> list = netAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
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
	 * @return List<NetAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<NetAssets> queryAllNetAssets(){
		return netAssetsDao.queryAllNetAssets();
	}

}
