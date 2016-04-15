package com.compliance.service.basicinfo.assets.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.DocAssetsDao;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.service.basicinfo.assets.DocAssetsService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DocAssetsServiceImpl extends BaseServiceImpl implements DocAssetsService {
	private DocAssetsDao docAssetsDao;

	public int docAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		return docAssetsDao.docAssetsCount(mapper);
	}

	public List<DocAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return docAssetsDao.query(map, startRow, pageSize);
	}

	public void docAssetsInsert(DocAssets docAssets) {
		// TODO Auto-generated method stub
		docAssetsDao.docAssetsInsert(docAssets);
	}

	public void docAssetsUpdate(DocAssets docAssets) {
		// TODO Auto-generated method stub
		docAssetsDao.docAssetsUpdate(docAssets);

	}

	public void docAssetsDelete(int id) {
		// TODO Auto-generated method stub
		docAssetsDao.docAssetsDelete(id);
	}

	public DocAssets docAssetsQueryById(int id) {
		// TODO Auto-generated method stub
		return docAssetsDao.docAssetsQueryById(id);
	}

	public DocAssetsDao getDocAssetsDao() {
		return docAssetsDao;
	}

	public void setDocAssetsDao(DocAssetsDao docAssetsDao) {
		this.docAssetsDao = docAssetsDao;
	}

	public SearchResult query(Map map, Page page) {
		// 查询数据
		int rowCount = docAssetsDao.docAssetsCount(map);
		page.setTotalCount(rowCount);
		List<DocAssets> list = docAssetsDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public int docAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		return docAssetsDao.docAssetsPreciseCount(mapper);
	}

	public SearchResult queryPrecise(Map map, Page page) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount = docAssetsDao.docAssetsPreciseCount(map);
		page.setTotalCount(rowCount);
		List<DocAssets> list = docAssetsDao.queryPrecise(map, page.getStartIndex(), page.getPageSize());
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
	 * @return List<DocAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DocAssets> queryAllDocAssets(){
		return docAssetsDao.queryAllDocAssets();
	}

}
