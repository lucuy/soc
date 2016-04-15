package com.compliance.service.cpManage.msaShow.impl;



import java.util.List;

import com.compliance.dao.cpManage.msaShow.MsaShowDao;
import com.compliance.model.cpManage.msaShow.MsaShow;
import com.compliance.service.cpManage.msaShow.MsaShowService;



/**
 * 通用管理安全测评整改需求汇总业务实现类
 * @author quyongkun
 *
 */
public class MsaShowServiceImpl implements MsaShowService {
	
	/**
	 * 通用管理安全测评整改需求汇总dao接口
	 */
	private  MsaShowDao msaShowDao;
	
	

	
	public MsaShowDao getMsaShowDao() {
		return msaShowDao;
	}




	public void setMsaShowDao(MsaShowDao msaShowDao) {
		this.msaShowDao = msaShowDao;
	}




	/**
	 * 查询所有项数据
	 * @return 
	 */
	public List<MsaShow> queryAllData() {
		return msaShowDao.queryAllData();
		 
	}
	
	



}
