package com.soc.dao.asset.device;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.asset.device.Category;

public interface CategoryDao extends BaseDao{
	
	/**
	 * 添加设备类型
	 * @param map
	 * @return
	 */
	public Object insertCategory(Map map);
	
	/**
	 * 查询是所有的资产设备
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Category> queryCategories(Map map, int startRow, int pageSize);

	/**
	 * 查询资产设备的记录数
	 * @param map
	 * @return
	 */
	public int count(Map map);
	
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
