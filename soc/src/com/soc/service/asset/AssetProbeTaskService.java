package com.soc.service.asset;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.AssetProbeTask;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 资产任务service接口
 * 
 * @author 张浩磊
 * 
 */
public interface AssetProbeTaskService extends Serializable {
	/**
	 * 创建探测任务
	 * 
	 * @param AssetProbeTask
	 */
	public long insertTask(AssetProbeTask task);

	/**
	 * 查询探测任务
	 * 
	 * @param AssetProbeTask
	 */
	public SearchResult queryProbe(Page page);

	/**
	 * 快速查询探测任务
	 */
	public SearchResult queryName(Page page ,String keyword);

	/**
	 * 修改探测任务
	 * 
	 * @param AssetProbeTask
	 */
	public void updateTask(AssetProbeTask task);

	/**
	 * 根据id删除探测任务
	 */
	public void deleteTask(int taskid);

	/**
	 * 根据ID查询探测任务
	 */
	public AssetProbeTask queryByIdTask(int taskId);
	
	/**
	 * 查询总条数
	 */
	public int count(); 
	
	/**
	 * 左外连接 -- 查询资产探测任务
	 */
	public SearchResult queryAllTask(Page page) ; 
	
	/**
	 * 左外连接--查询资产返回数目
	 */
	public int getCount(String keyword) ; 
	
	/**
	 * 左外连接高级查询条数
	 */
	public int getProCount(Map map) ; 
	
	/**
	 * 左外连接高级查询
	 */
	public SearchResult queryPro(Page page,Map map) ; 
	
}
