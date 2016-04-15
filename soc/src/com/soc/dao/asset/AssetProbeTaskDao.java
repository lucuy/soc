package com.soc.dao.asset;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.asset.AssetProbeTask;

/**
 * 资产任务dao接口
 * 
 * @author 张浩磊
 * 
 */
public interface AssetProbeTaskDao extends BaseDao {
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
	public List<AssetProbeTask> queryProbe(int startRow,int pageSize);
	/**
	 * 快速查询探测任务
	 */
	public List<Map> queryName(int startRow,int pageSize , String keyword);
	/**
	 * 修改探测任务
	 * 
	 * @param AssetProbeTask
	 */
	public void updateTask(AssetProbeTask task);

	/**
	 * 根据id删除探测任务
	 */
	public void deleteTask(int taskId);

	/**
	 * 根据ID查询探测任务
	 */
	public AssetProbeTask queryByIdTask(int taskId);
	
	/**
	 * 查询总条数
	 */
	public int count(Map map) ;
	
	/**
	 * 查询所有探测任务（左外）
	 */
	public List<Map> queryAllTask(int startRow,int pageSize) ; 
	
	/**
	 * 查询条数（左外）
	 * 
	 */
	public int queryNumLeftJoin(String keyword)  ; 
	
	/**
	 * 高级查询
	 */
	public List<Map> queryPro(Map map, int startRow ,int pageSize);
	
	/**
	 * 左外连接高级查询条数
	 */
	public int getProCount(Map map) ; 
	
	
}
