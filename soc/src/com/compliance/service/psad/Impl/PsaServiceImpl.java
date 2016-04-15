package com.compliance.service.psad.Impl;

import java.util.Date;
import java.util.Map;

import com.compliance.dao.psad.PsaDao;
import com.compliance.model.psad.Psa;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.psad.PsaService;

public class PsaServiceImpl extends BaseServiceImpl implements PsaService{

	private PsaDao psaDao;
	public int insert(Psa p) {
		return psaDao.insert(p);
	}
	public PsaDao getPsaDao() {
		return psaDao;
	}
	public void setPsaDao(PsaDao psaDao) {
		this.psaDao = psaDao;
	}
	
	
	public void upData(Psa p) {
		psaDao.upData(p);
		
	}
	/**
	 * 查询最新时间
	 */
	public Date queryLastTime() {
		 
		return psaDao.lastTime();
	}
	/**
	 * 查询最新时间内已经评估的单元数
	 */
	public int queryTheTimeNum() {
		return psaDao.lastNum();
	}
	public Psa queryResultByTimeAndSort(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return psaDao.queryResultByTimeAndSort(map);
	}

	
}
