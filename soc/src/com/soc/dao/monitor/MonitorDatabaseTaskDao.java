package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.monitor.MonitorDatabaseTask;

public interface MonitorDatabaseTaskDao extends BaseDao{
	/**
     * 按条件分页查询数据库监控列表
     * 
     * @param Map,int,int
     * @return 返回符合查询条件的结果列表List<User>
     */
	@SuppressWarnings("rawtypes")
	public List<MonitorDatabaseTask> queryMonitorDatabaseTaskS(Map map ,int startRow, int pageSize);
	/**
	 * 查询所有
	 * @return
	 */
	public List<MonitorDatabaseTask> queryAll();
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
    /**暂时废弃此方法，看以后能不能用到
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
}
