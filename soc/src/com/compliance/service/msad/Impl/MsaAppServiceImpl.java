package com.compliance.service.msad.Impl;


import java.util.Date;
import java.util.Map;

import com.compliance.dao.msad.MsaDao;
import com.compliance.model.cpManage.msaShow.Msa;
import com.compliance.model.msad.MsaApp;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.msad.MsaAppService;

public class MsaAppServiceImpl extends BaseServiceImpl implements MsaAppService{

	private MsaDao msaAppDao;
	
	public int insert(MsaApp m) {
		return msaAppDao.insert(m);
	}
/**
 * 查询最新时间
 */
	public Date queryLastTime() {
		// TODO Auto-generated method stub
		return msaAppDao.lastTime();
	}
/**
 * 查询最新时间内已经评估的单元数
 */
	public int queryTheTimeNum() {
		// TODO Auto-generated method stub
		return msaAppDao.lastNum();
	}
	
	/*
	 *根据评估时间与评估单元编号查询该评估单元的评估结果
	 */
	public MsaApp queryResultByTimeAndSort(Map<String, String> map){
		return msaAppDao.queryResultByTimeAndSort(map);
	};

	public MsaDao getMsaAppDao() {
		return msaAppDao;
	}

	public void setMsaAppDao(MsaDao msaAppDao) {
		this.msaAppDao = msaAppDao;
	}
	public void upData(MsaApp m) {
		msaAppDao.upData(m);
	}

}
