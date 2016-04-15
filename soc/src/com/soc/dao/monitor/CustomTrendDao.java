package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.events.Events;
import com.soc.model.monitor.CustomTrend;


public interface CustomTrendDao extends BaseDao {
	 /**
     * <查询所有的自定义趋势> <功能详细描述>
     * @param map Map
     * @return CustomTrend List
     * @see [类、类#方法、类#成员]
     */
	public List<CustomTrend> queryAllCustomTrend(Map map);
	/**
     * <条件查询分页> <功能详细描述>
     * @param map Map
     * @return CustomTrend List
     * @see [类、类#方法、类#成员]
     */
	public List<CustomTrend> queryCustomTrend(Map map,int startRow, int pageSize);
	/**
     * <查询趋势图数据> <功能详细描述>
     * @param sql String
     * @return map List
     * @see [类、类#方法、类#成员]
     */
	public List<Map> queryEvents(String sql);
	/**
     * <查询所有的自定义趋势数量> <功能详细描述>
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public int countAllCustomTrend(Map map);
	/**
     * <按照id查询> <功能详细描述>
     * @param id long
     * @return CustomTrend
     * @see [类、类#方法、类#成员]
     */
	public CustomTrend queryById(long id);
	/**
     * <修改> <功能详细描述>
     * @param CustomTrend
     * @return 
     * @see [类、类#方法、类#成员]
     */
	public void update(CustomTrend customTrend);
	/**
     * <删除> <功能详细描述>
     * @param id long
     * @return 
     * @see [类、类#方法、类#成员]
     */
	public void delete(long id);
	/**
     * <新增> <功能详细描述>
     * @param id long
     * @return 
     * @see [类、类#方法、类#成员]
     */
	public void insert(CustomTrend customTrend);
	
	/**
     * <按照名称查询> <功能详细描述>
     * @param name String
     * @return CustomTrend
     * @see [类、类#方法、类#成员]
     */
	public CustomTrend queryByName(String name);
	
	/**
     * <查询事件数量> <功能详细描述>
     * @param sql String
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public int countEvents(String sql);
	
	/**
     * <查询事件数量> <功能详细描述>
     * @param sql String
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public List<Events> queryAllEvents(String sql,int startRow, int pageSize);
}
