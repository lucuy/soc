package com.soc.dao.asset.ibatis;

import com.soc.dao.asset.ProbeStateDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.ProbeState;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * @author 张浩磊
 * 
 */
public class ProbeStateDaoIbatis extends BaseDaoIbatis implements ProbeStateDao {
	@Override
	public ProbeState queryMac(String mac){
		
	return 	(ProbeState)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"probeState.queryMac",mac);

	}

	@Override
	public void updateState(ProbeState probeState) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"probeState.update", probeState);
	}

	@Override
	public ProbeState queryByTaskId(int taskId) {
		ProbeState probeState = (ProbeState)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"probeState.queryByTaskId",taskId);
		return probeState;
	}

	@Override
	public void insertState(ProbeState probeState) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"probeState.insert",probeState);
		
	}

	@Override
	public void deleteState(int taskId) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"probeState.deleteProbeState",taskId) ; 
	}
	


}
