package com.soc.service.screen;

import java.util.List;
import java.util.Map;

/**
 * 
 * <大屏展示：首页：Service类>
 * <对设备事件、安全事件、查询>
 * 
 * @author  zhaokui
 * @version  [V100R001C001, 2012-03-06]
 * @see  [相关类/方法]
 * @since  
 */
public interface BigScreenService {

    /**
     * <查询事件统计top 5>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public Object queryEventNum(Map map);
	
	 /**
     * <查询资产分布>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public Object queryAssetsNum(long id);
	/**
     * <全局风险，查询5分钟内的事件数量>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public double countEvents(long percent);
	
	/**
     * <全局风险定时器，查询5分钟内的事件数量>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public void queryCountEvents();
	/**
     * <全局风险，>
     * <功能详细描述>
     * @param null
     * @return list
     * @see [类、类#方法、类#成员]
     */
	public List queryCountEventsList();
}
