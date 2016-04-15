package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.SoftUseDao;

import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.SoftUseService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SoftUseServiceImpl extends BaseServiceImpl implements
		SoftUseService {
	private SoftUseDao softUseDao;

	public SoftUseDao getSoftUseDao() {
		return softUseDao;
	}

	public void setSoftUseDao(SoftUseDao softUseDao) {
		this.softUseDao = softUseDao;
	}

	/*
	 * 模糊搜索业务应用软件信息的条数
	 */
	public int softCount(Map mapper) {
		// TODO Auto-generated method stub
		return softUseDao.softCount(mapper);
	}

	
	 public List<BusinessAssets> query(Map map, int startRow, int pageSize) {
	 // TODO Auto-generated method stub return softUseDao.query(map, startRow,
		 return softUseDao.query(map, startRow, pageSize);
	 }
	 

	/*
	 * 添加业务应用软件
	 */
	public void softInsert(BusinessAssets resType) {
		// TODO Auto-generated method stub
		softUseDao.softInsert(resType);
	}

	/*
	 * 修改业务应用软件
	 */
	public void softUpdate(BusinessAssets resType) {
		// TODO Auto-generated method stub
		softUseDao.softUpdate(resType);

	}

	/*
	 * 删除业务应用软件
	 */
	public void softDelete(int id) {
		// TODO Auto-generated method stub
		softUseDao.softDelete(id);

	}

	/*
	 * 根据id查找业务应用软件
	 */

	public BusinessAssets softQueryById(int id) {
		// TODO Auto-generated method stub
		return softUseDao.softQueryById(id);
	}

	/*
	 * 模糊搜索业务应用软件
	 */
	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = softUseDao.softCount(map);
		page.setTotalCount(rowCount);
		List<BusinessAssets> list = softUseDao.query(map, page.getStartIndex(),
				page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/*
	 * 高级搜索业务应用软件信息列表
	 */

	public SearchResult queryPreciseSoft(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowCount = softUseDao.softPreciseCount(map);
		page.setTotalCount(rowCount);
		List<BusinessAssets> list = softUseDao.preciseQuerySoft(map,
				page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/*
	 * 高级搜索业务应用软件信息数据条数
	 */
	public int softPreciseCount(Map mapper) {
		return softUseDao.softPreciseCount(mapper);
	}
	
	
	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param 
	 * @return  List<BusinessAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<BusinessAssets>  queryAllBusinessAssets(){
		return softUseDao.queryAllBusinessAssets();
	}

}
