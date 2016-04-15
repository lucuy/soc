package com.soc.dao.asset;

import com.soc.dao.BaseDao;
import com.soc.model.asset.ProbeState;
/**
 * 采集器状态DAO接口
 * @author Administrator
 *
 */
public interface ProbeStateDao extends BaseDao {
	/**
	 * 根据MAC查询条数
     * @param ProbeState
     * @return 
	 */
	public ProbeState queryMac(String Mac);
	/**
	 * 更新采集器状态信息
     * @param ProbeState
     * @return 
	 */
	public void updateState(ProbeState probeState);
	/**
	 * 根据TaskId查询对应的采集器状态
	 * @param int 
	 * @return ProbeState 
	 */
	public ProbeState queryByTaskId(int taskId) ; 
	
	/**
	 * 新增采集器状态
	 * @param ProbeState
     * @return 
	 */
	public void insertState(ProbeState probeState);
	
	public void deleteState(int taskId) ; 
	
}
