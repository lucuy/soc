package com.compliance.service.psad.Impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.psad.PsadDao;
import com.compliance.model.psad.Psa;
import com.compliance.model.psad.Psad;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.psad.PsadService;

public class PsadServiceImpl extends BaseServiceImpl implements PsadService{

	private PsadDao psadDao;

	public List<Psad> queryPsadByFatherSort(String psadFatherSort) {
		return psadDao.queryPsadByFatherSort(psadFatherSort);
	}

	public PsadDao getPsadDao() {
		return psadDao;
	}

	public void setPsadDao(PsadDao psadDao) {
		this.psadDao = psadDao;
	}
    /**
     * 根据排序查询
    */
	public List<Psad> queryByPsadSortInfo(Map map) {
		return psadDao.queryByPsadSortInfo(map);
	}
/**
 * 根据排序查询是否有下一级
 */
	public int queryNextPsadSortInfo(Map map) {
		return psadDao.queryNextPsadSortInfo(map);
	}

public String queryPsadnameById(String psadid) {
	// TODO Auto-generated method stub
	return psadDao.queryPsadNameByid(psadid);
}


	

}
