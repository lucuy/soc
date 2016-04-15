package com.compliance.service.msad.Impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.msad.MsadDao;
import com.compliance.model.msad.Msad;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.msad.MsadService;

public class MsadServiceImpl extends BaseServiceImpl implements MsadService{

	private MsadDao msadDao;
	public List<Msad> queryMsadByFatherSort(String msadFatherSort) {
		return msadDao.queryMsadByFatherSort(msadFatherSort);
	}
	/**
	 * 根据排序查询
	 */
	public List<Msad> queryByMsadSort(Map map) {
		return msadDao.queryByMsadSort(map);
	}
	/**
	 * 查询是否有下一级
	 */
	public int queryByNextSort(Map map) {
		return msadDao.queryByNextSort(map);
	}
	
	public String queryMsadName(String msadid) {
		// TODO Auto-generated method stub
		return msadDao.queryMsadByMsadid(msadid);
	}
	public MsadDao getMsadDao() {
		return msadDao;
	}
	public void setMsadDao(MsadDao msadDao) {
		this.msadDao = msadDao;
	}



}
