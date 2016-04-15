package com.compliance.dao.cpManage.gpaShow.impl;

import java.util.List;

import com.compliance.dao.cpManage.gpaShow.GpaShowDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.gpaShow.GpaShow;
import com.soc.model.conf.GlobalConfig;
/**
 * 通用物理测评整改需求汇总dao实现类
 * @author quyongkun
 *
 */
public class GpaShowDaoImpl extends  BaseDaoiBatis implements GpaShowDao {

	/**
	 * 查询所有项数据
	 * @return 
	 */
	public List<GpaShow> queryAllData() {
		return super.queryForList(GlobalConfig.sqlId+"gpaShow.queryAllData");
	}

}
