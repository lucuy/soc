package com.compliance.service.rank;

import java.util.List;

import com.compliance.model.rank.SysAbolish;
/**
 * 系统废止业务接口
 * @author quyongkun
 *
 */
public interface SysAbolishService {
	
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
	 void update(SysAbolish sysAbolish) ;
	 
	/**
	 * 
	 * 根据信息系统主键查询系统废止信息
	 * @param pkSysInfo 信息系统主键
	 * @return 系统废止信息
	 */
	 SysAbolish query(int pkSysInfo) ;
}
