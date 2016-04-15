package com.soc.service.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface MonitorDatabaseTaskService extends BaseService{
	/**
     * 按条件分页查询数据库监控列表
     * 
     * @param Map,page
     * @return 返回符合查询条件的结果列表List<User>
     */
	@SuppressWarnings("rawtypes")
	public  SearchResult<MonitorDatabaseTask>  queryMonitorDatabaseTaskS(Map map, Page page);
	/**
     * 计算符合条件的数据库监控记录数
     * 
     * @param Map
     * @return 返回符合查询条件的记录数
     */
    @SuppressWarnings("rawtypes")
	public int countMonitorDatabaseTask(Map map);
    /**
     * 新增数据库监控，返回新增id
     * @param MonitorDatabaseTask mdt
     * @return long id
     */
    public long insertMonitorDatabaseTask(MonitorDatabaseTask mdt);
    /**
     * 修改数据库监控
     * @param MonitorDatabaseTask mdt
     */
    public void updateMonitorDatabaseTask(MonitorDatabaseTask mdt);
    /**
     * 根据id删除指定的数据库监控
     * @param id
     */
    public void deleteMonitorDatabaseTask(long id);
    /**
     * 根据id启停数据的监控
     * @param  map
     */
    @SuppressWarnings("rawtypes")
	public void stopMonitorDatabaseTask(Map map);
    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public MonitorDatabaseTask detailMonitorDatabaseTask(long id);
    /**
     * 查询任务名称是否存在
     * @param taskName
     * @return
     */
    public String checkTaskName(String taskName);
    /**暂时废弃此方法，看以后能不能用到
     * 修改数据库在线状态
     * @param map
     */
    public void updateDBOnlie(Map map);
    /**
     * 暂时废弃此方法，看以后能不能用到
	 * 查询所有启动状态的数据库监控
	 * @return
	 */
	public List<MonitorDatabaseTask> queryAll();
}
