package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.monitor.Monitor;

/**
 * 
 * <监控Dao类> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-10-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MonitorDao extends BaseDao
{
    
    /**
     * <查询所有的监控表> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Monitor> queryAll();
    
    /**
     * <根据id查询某个监控表> <功能详细描述>
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Monitor queryById(long id);
    
    /**
     * <查询所有图表列表>
     * <按照分页查询>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Monitor> queryMonitorAjax(Map map,int startRow, int pageSize);
    
    
    /**
     * <查询所有图表列表>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int queryMonitorCount(Map map);
    /**
     * <插入自定义监控图表>
     * <功能详细描述>
     * @param monitor
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void insertChar(Monitor monitor);
	/**
	 * 查询自定义监控图表的数量
	 * @param map
	 * @return
	 */
	public int countMonitorCustom(Map<String, Object> map);
	/**
	 * 查询自定义监控图表的list
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Monitor> queryMonitorCustom(Map<String, Object> map,
		int startIndex, int pageSize);
	/**
	 * 删除自定义图表的方法
	 * @return
	 */
	public void deleteCustom(long l);
/**
 * 根据id查询出图表
 * @param map
 * @return
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
