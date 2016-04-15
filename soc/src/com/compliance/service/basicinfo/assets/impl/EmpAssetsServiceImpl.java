package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.EmpAssetsDao;
import com.compliance.model.basicinfo.assets.EmpAssets;
import com.compliance.service.basicinfo.assets.EmpAssetsService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class EmpAssetsServiceImpl extends BaseServiceImpl implements EmpAssetsService {
	private EmpAssetsDao empAssetsDao;

	public int empAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return empAssetsDao.empAssetsCount(mapper);
	}

	public List<EmpAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return empAssetsDao.query(map, startRow, pageSize);
	}

	public void empAssetsInsert(EmpAssets empAssets) {
		// TODO Auto-generated method stub
		empAssetsDao.empAssetsInsert(empAssets);
	}

	public void empAssetsUpdate(EmpAssets empAssets) {
		// TODO Auto-generated method stub
		empAssetsDao.empAssetsUpdate(empAssets);

	}

	public void empAssetsDelete(int id) {
		// TODO Auto-generated method stub
		empAssetsDao.empAssetsDelete(id);
	}

	public EmpAssets empAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return empAssetsDao.empAssetsQueryById(id);
	}

	public EmpAssetsDao getEmpAssetsDao() {
		return empAssetsDao;
	}

	public void setEmpAssetsDao(EmpAssetsDao empAssetsDao) {
		this.empAssetsDao = empAssetsDao;
	}

	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = empAssetsDao.empAssetsCount(map);
		page.setTotalCount(rowCount);
		List<EmpAssets> list = empAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int empAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return empAssetsDao.empAssetsPreciseCount(mapper);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = empAssetsDao.empAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<EmpAssets> list = empAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
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
	 * @return List<EmpAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<EmpAssets> queryAllEmpAssets(){
		return empAssetsDao.queryAllEmpAssets();
	}

}
