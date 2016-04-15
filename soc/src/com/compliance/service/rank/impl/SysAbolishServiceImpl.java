package com.compliance.service.rank.impl;

import com.compliance.dao.rank.SysAbolishDao;
import com.compliance.model.rank.SysAbolish;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.rank.SysAbolishService;
/**
 * 系统废止业务实现类
 * @author quyongkun
 *
 */
public class SysAbolishServiceImpl extends BaseServiceImpl implements SysAbolishService {

	/**
	 * 系统废止dao接口
	 */
	private SysAbolishDao sysAbolishDao;

	

	public SysAbolishDao getSysAbolishDao() {
		return sysAbolishDao;
	}

	public void setSysAbolishDao(SysAbolishDao sysAbolishDao) {
		this.sysAbolishDao = sysAbolishDao;
	}

	/**
	 * 
	 * 插入系统废止信息
	 * @param sysAbolish 系统废止信息
	 */
	public void insert(SysAbolish sysAbolish) {
		sysAbolishDao.insert(sysAbolish);
	}

	/**
	 * 
	 * 更新系统废止信息
	 * @param sysAbolish
	 */
	public void update(SysAbolish sysAbolish) {
		sysAbolishDao.update(sysAbolish);
	}

	/**
	 * 
	 * 根据信息系统主键查询系统废止信息
	 * @param pkSysInfo 信息系统主键
	 * @return 系统废止信息
	 */
	public SysAbolish query(int pkSysInfo) {
		return sysAbolishDao.query(pkSysInfo);
		 
	}
	
	
	
	

}
