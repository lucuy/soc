package com.soc.service.asset;

import java.io.Serializable;

import com.soc.model.asset.ProbeState;

/**
 * 资产状态service接口
 * 
 * @author 张浩磊
 * 
 */
public interface ProbeStateService extends Serializable {
	/**
	 * 根据MAC查询条数
     * @param ProbeState
     * @return 
	 */
	public ProbeState queryMac(String Mac);

	/**
	 * 更新采集器状态信息
	 * 
	 * @param ProbeState
	 * @return
	 */
	public void updateState(ProbeState probeState);
	
    public ProbeState queryByTaskId(int id) ; 
    
    public void insertState(ProbeState probeState);
    
    /**
     * 删除采集器状态
     */
    public void deleteProbeState(int taskId) ; 
}
