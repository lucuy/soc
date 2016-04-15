package com.soc.service.asset.impl;

import com.soc.dao.asset.ProbeStateDao;
import com.soc.model.asset.ProbeState;
import com.soc.service.asset.ProbeStateService;
import com.soc.service.impl.BaseServiceImpl;

public class ProbeStateServiceImpl extends BaseServiceImpl implements
		ProbeStateService {
	// 资产状态管理业务类
	private ProbeStateDao probeStateDao;

	public ProbeStateDao getProbeStateDao() {
		return probeStateDao;
	}

	public void setProbeStateDao(ProbeStateDao probeStateDao) {
		this.probeStateDao = probeStateDao;
	}
	@Override
	public ProbeState queryMac(String Mac){
		return probeStateDao.queryMac(Mac);
	}
	@Override
	public void updateState(ProbeState probeState) {
		probeStateDao.updateState(probeState);

	}

	@Override
	public ProbeState queryByTaskId(int id) {
		// TODO Auto-generated method stub
		return probeStateDao.queryByTaskId(id);
	}

	@Override
	public void insertState(ProbeState probeState) {

         probeStateDao.insertState(probeState);
		
	}

	@Override
	public void deleteProbeState(int taskId) {
		// TODO Auto-generated method stub
		probeStateDao.deleteState(taskId) ; 
	}

}
