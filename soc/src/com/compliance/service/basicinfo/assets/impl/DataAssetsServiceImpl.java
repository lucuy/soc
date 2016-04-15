package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.DataAssetsDao;
import com.compliance.model.basicinfo.assets.DataAssets;
import com.compliance.service.basicinfo.assets.DataAssetsService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DataAssetsServiceImpl extends BaseServiceImpl implements DataAssetsService {
	private DataAssetsDao dataAssetsDao;

	public DataAssetsDao getDataAssetsDao() {
		return dataAssetsDao;
	}

	public void setDataAssetsDao(DataAssetsDao dataAssetsDao) {
		this.dataAssetsDao = dataAssetsDao;
	}

	// 模糊搜索数据行数
	public int dataAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return dataAssetsDao.dataAssetsCount(mapper);
	}

	// 模糊搜索关键数据类别
	public List<DataAssets> query(Map map, int startRow, int pageSize) { //
		// TODO Auto-generated method stub
		return dataAssetsDao.query(map, startRow, pageSize);
	}

	// 添加关键数据类别
	public void dataAssetsInsert(DataAssets dataAssets) {
		// TODO Auto-generated method stub
		dataAssetsDao.dataAssetsInsert(dataAssets);
	}

	// 修改关键数据类别
	public void dataAssetsUpdate(DataAssets dataAssets) {
		// TODO Auto-generated method stub
		dataAssetsDao.dataAssetsUpdate(dataAssets);

	}

	// 删除关键数据类别
	public void dataAssetsDelete(int id) {
		// TODO Auto-generated method stub
		dataAssetsDao.dataAssetsDelete(id);
	}

	// 根据id查询关键数据类别
	public DataAssets dataAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return dataAssetsDao.dataAssetsQueryById(id);
	}

	// 模糊搜索关键数据类别
	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = dataAssetsDao.dataAssetsCount(map);
		page.setTotalCount(rowCount);
		List<DataAssets> list = dataAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	// 高级搜索关键数据类别数据行数
	public int dataAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return dataAssetsDao.dataAssetsPreciseCount(mapper);
	}

	// 高级搜索关键数据类别
	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = dataAssetsDao.dataAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<DataAssets> list = dataAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
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
	 * @return List<DataAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DataAssets> queryAllDataAssets(){
		return dataAssetsDao.queryAllDataAssets();
	}

}
