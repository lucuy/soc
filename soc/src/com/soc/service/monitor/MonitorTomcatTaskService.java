package com.soc.service.monitor;

import java.util.Map;

import com.soc.model.monitor.MonitorTomcatTask;
import com.soc.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface MonitorTomcatTaskService extends BaseService {

	/**
     * 按条件分页查询tomcat监控列表
     * 
     * @param Map,page
     * @return 返回符合查询条件的结果列表
     */
	@SuppressWarnings("rawtypes")
	public  SearchResult<MonitorTomcatTask>  queryMonitorTomcatTaskS(Map map, Page page);
	/**
     *计算符合条件的监控对象记录数
     * 
     * @param Map
     * @return 返回符合查询条件的记录数
     */
    @SuppressWarnings("rawtypes")
	public int countMonitorTomcatTask(Map map);
    /**
     * 新增Tomcat监控对象，返回新增id
     * @param MonitorTomcatTask mtt
     * @return long id
     */
    public long insertMonitorTomcatTask(MonitorTomcatTask mtt);
    /**
     * 修改tomcat监控对象
     * @param MonitorTomcatTask mtt
     */
    public void updateMonitorTomcatTask(MonitorTomcatTask mtt);
    /**
     * 根据id删除指定的tomcat监控
     * @param id
     */
    public void deleteMonitorTomcatTask(long id);
    /**
     * * 根据id启停tomcat的监控
     * @param  map
     */
    @SuppressWarnings("rawtypes")
	public void stopMonitorTomcatTask(Map map);
    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public MonitorTomcatTask detailMonitorTomcatTask(long id);
    /**
     * 查询任务名称是否存在
     * @param taskName
     * @return
     */
    public String checkTomName(String tomName);
}
