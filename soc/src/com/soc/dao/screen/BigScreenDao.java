package com.soc.dao.screen;

import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;

/**
 * 
 * <大屏展示：事件统计：Dao>
 * 
 * @author  zhaokui
 * @version  [V100R001C001, 2012-03-06]
 * @see  [相关类/方法]
 * @since  
 */
public interface BigScreenDao {

    /**
     * <查询事件统计top 5>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	
	public List<Map> queryEventNum(Map map);
	
	 /**
     * <查询资产分布>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public List<Map> queryAssetsNum();
	/**
     * 根据资产组id查询关联的资产总数
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public int countByAssetGroupId(Map map);
	
	/**
     * 根据资产组id查询资产组名称
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public String AssetGroupNameById(int id);
	
	 /**
     * <全局风险，查询5分钟内的事件数量>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public List<Map> countEvents(Map map);
	
	/**
     * <全局风险，查询所有资产>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public List<Asset> queryAllAsset();
}
