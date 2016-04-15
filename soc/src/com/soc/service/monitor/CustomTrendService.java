package com.soc.service.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.monitor.CustomTrend;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface CustomTrendService {
	 /**
     * <查询所有的自定义趋势> <功能详细描述>
     * @param map Map
     * @return CustomTrend List
     * @see [类、类#方法、类#成员]
     */
	public List<CustomTrend> queryAllCustomTrend(Map map);
	
	/**
     * <查询趋势图数据> <功能详细描述>
     * @param sql String
     * @return map List
     * @see [类、类#方法、类#成员]
     */
	public Map queryEvents(String trendId,String assetId);
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
     * 按条件分页查询自定义趋势
     * 
     * @param map Map
     * @param page Page
     * @return SearchResult
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * <创建趋势分析的树>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String createCustomTree(String objectpath);
    /**
     * <按照名称查询> <功能详细描述>
     * @param name String
     * @return CustomTrend
     * @see [类、类#方法、类#成员]
     */
	public CustomTrend queryByName(String name);
	
	/**
     * <查询上周事件趋势> <功能详细描述>
     * @param assetId String
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public String queryLastWeekcountEvents(String assetId);
	
	/**
     * <查询事件数量> <功能详细描述>
     * @param sql String
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public SearchResult queryAllEvents(long trendId,Page page,String assetId);
	
	public Map<String, Object> getFreemakerMapExport(long auditReportId,
			String path, String reprotType);
}
