package com.soc.service.asset.device;

import java.util.List;
import java.util.Map;

import com.soc.model.asset.device.Category;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 添加设备类型业务类
 * @author lc
 *
 */
public interface CategoryService {

	
	/**
	 * 添加设备类型
	 * @param map
	 * @return
	 */
	public long insertCategory(Map map);
	
	/**
	 * 查询是所有的资产设备
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public SearchResult queryCategories(Map map, Page page);

	/**
	 * 根据id删除资产设备
	 * @param id
	 */
	public void delCategoryById(long id);
	
	/**
	 * 根据关联的资产主id删除资产设备厂商
	 * @param id
	 */
	public void delCategoryByUpsId(long upsid);
	
	/**
	 * 根据id查询单个资产设备
	 * @param id
	 * @return
	 */
	public Category queryCategoryById(long id);
	
	/**
	 * 根据设备类型名称查询单个资产设备
	 * @param id
	 * @return
	 */
	public Category queryCategoryByCategoryName(String categoryName);
}
