package com.compliance.service.cpManage.gpaShow.impl;

import java.util.List;

import com.compliance.dao.cpManage.gpaShow.GpaShowDao;
import com.compliance.model.cpManage.gpaShow.GpaShow;
import com.compliance.service.cpManage.gpaShow.GpaShowService;
/**
 * 通用物理测评整改需求汇总业务实现类
 * @author quyongkun
 *
 */
public class GpaShowServiceImpl implements GpaShowService {
	
	/**
	 * 通用物理测评整改需求汇总dao接口
	 */
	private  GpaShowDao  gpaShowDao;
	
	
	
	public GpaShowDao getGpaShowDao() {
		return gpaShowDao;
	}



	public void setGpaShowDao(GpaShowDao gpaShowDao) {
		this.gpaShowDao = gpaShowDao;
	}



	/**
	 * 查询所有项数据
	 * @return 
	 */
	public List<GpaShow> queryAllData() {
		return gpaShowDao.queryAllData();
	}

}
