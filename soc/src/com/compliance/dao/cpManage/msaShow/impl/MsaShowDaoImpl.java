package com.compliance.dao.cpManage.msaShow.impl;

import java.util.List;

import com.compliance.dao.cpManage.msaShow.MsaShowDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.msaShow.MsaShow;
import com.soc.model.conf.GlobalConfig;


/**
 * 通用管理安全测评整改需求汇总dao实现类
 * @author quyongkun
 *
 */
public class MsaShowDaoImpl extends  BaseDaoiBatis implements MsaShowDao {
	
	/**
	 * 查询所有项数据
	 * @return 
	 */
	public List<MsaShow> queryAllData(){
		return super.queryForList(GlobalConfig.sqlId+"msaShow.queryAllData");
	}

}
