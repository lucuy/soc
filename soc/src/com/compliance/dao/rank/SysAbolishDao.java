package com.compliance.dao.rank;

import com.compliance.model.rank.SysAbolish;


/**
 * 系统废止dao接口
 * @author quyongkun
 *
 */
public interface SysAbolishDao {
	
	/**
	 * 
	 * 根据信息系统主键查询系统废止信息
	 * @param pkSysInfo 信息系统主键
	 * @return 系统废止信息
	 */
	 SysAbolish query (int pkSysInfo);
	 
	/**
	 * 
	 * 插入系统废止信息
	 * @param sysAbolish 系统废止信息
	 */
	void insert(SysAbolish sysAbolish);
	
	/**
	 * 
	 * 更新系统废止信息
	 * @param sysAbolish
	 */
	void update(SysAbolish sysAbolish);
}
