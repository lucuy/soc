package com.soc.service.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.monitor.MonitorWindowsTask;
import com.soc.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface MonitorWindowsTaskService extends BaseService {
	/**
     * 按条件分页查询主机监控列表
     * 
     * @param Map,page
     * @return 返回符合查询条件的结果列表List
     */
	@SuppressWarnings("rawtypes")
	public  SearchResult<MonitorWindowsTask>  queryMonitorWindowsTaskS(Map map, Page page);
	/**
     * 计算符合条件的主机监控记录数
     * 
     * @param Map
     * @return 返回符合查询条件的记录数
     */
    @SuppressWarnings("rawtypes")
	public int countMonitorWindowsTask(Map map);
    /**
     * 新增主机监控，返回新增id
     * @param MonitorWindowsTask mwt
     * @return long id
     */
    public long insertMonitorWindowsTask(MonitorWindowsTask mwt);
    /**
     * 修改主机监控
     * @param MonitorWindowsTask mwt
     */
    public void updateMonitorWindowsTask(MonitorWindowsTask mwt);
    /**
     * 根据id删除指定的主机监控
     * @param id
     */
    public void deleteMonitorWindowsTask(long id);
    /**
     * 根据id启停主机的监控
     * @param  map
     */
    @SuppressWarnings("rawtypes")
	public void stopMonitorWindowsTask(Map map);
    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public MonitorWindowsTask detailMonitorWindowsTask(long id);
    /**
     * 查询任务名称是否存在
     * @param taskName
     * @return
     */
    public String checkWinTaskName(String winTaskName);
    /**
     * 修改数据库在线状态
     * @param map
     */
    public void updateWinOnlie(Map map);
    /**
     * 
	 * 查询所有启动状态的主机监控
	 * @return
	 */
	public List<MonitorWindowsTask> queryAll();
}
