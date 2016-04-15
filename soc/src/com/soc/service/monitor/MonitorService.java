package com.soc.service.monitor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.monitor.Monitor;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <监控业务处理类>
 * <监控的查询>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorService extends Serializable
{
    
    /**
     * <查询所有的监控>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Monitor> queryAll();
    
    /**
     * <根据id查询某个监控>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Monitor queryById(long id);
    
    /**
     * <查询图表的数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <分页查询图表>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryMonitor(Map map,Page page);
    /**
     * <插入自定义监控图表>
     * <功能详细描述>
     * @param monitor
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void insertChar(Monitor monitor);
	 /**
     * <查询自定义监控图表>
     * <功能详细描述>
     * @param monitor
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
	public SearchResult queryMonitorCustom(Map<String, Object> map, Page page);
	 /**
     * <删除自定义监控图表>
     * <功能详细描述>
     * @param ids
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void deleteCustom(String ids);
	 /**
     * <根据id查询监控图表的详情>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
	public Monitor selectMonitorById(Map map);
	 /**
     * <更新自定义监控图表内容>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void updateChar(Monitor monitor);
    
}
