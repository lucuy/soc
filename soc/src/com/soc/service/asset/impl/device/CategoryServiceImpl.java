package com.soc.service.asset.impl.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.device.CategoryDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.device.Category;
import com.soc.service.asset.device.CategoryService;
import com.util.IpConvert;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao cDao;
	@Override
	public long insertCategory(Map map) {
		Long categoryid = null;
		try {
			Object obj = cDao.insertCategory(map);
			categoryid = Long.parseLong(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryid;
	}
	public CategoryDao getcDao() {
		return cDao;
	}
	public void setcDao(CategoryDao cDao) {
		this.cDao = cDao;
	}
	
	@Override
	public SearchResult queryCategories(Map map, Page page) {
		// 按Map中存储的条件查找用户列表
				int rowsCount;
				List<Category> list;
				rowsCount = cDao.count(map);
				list = cDao.queryCategories(map, page.getStartIndex(),
					page.getPageSize());
				page.setTotalCount(rowsCount);
				// 对查找的用户列表做分页处理
				SearchResult sr = new SearchResult();
				sr.setList(list);
				sr.setPage(page);
				return sr;
	}
	@Override
	public void delCategoryById(long id) {
		cDao.delCategoryById(id);
		cDao.delCategoryByUpsId(id);
		
	}
	@Override
	public void delCategoryByUpsId(long upsid) {
		cDao.delCategoryByUpsId(upsid);
	}
	@Override
	public Category queryCategoryById(long id) {
		return cDao.queryCategoryById(id);
	}
	@Override
	public Category queryCategoryByCategoryName(String categoryName) {
		
		return cDao.queryCategoryByCategoryName(categoryName);
	}

	
}
