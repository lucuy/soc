package com.compliance.dao.cpManage.demand.ibatis;

import com.compliance.dao.cpManage.demand.ArithmeticDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.demand.Arithmetic;
import com.soc.model.conf.GlobalConfig;

public class ArithmeticDaoIbatis extends BaseDaoiBatis implements ArithmeticDao{
	
	/**
	 * 根据排序查询js算法
	 * @param String
	 * @return Arithmetic
	 */
	@SuppressWarnings("unchecked")
	public Arithmetic queryJsAlgBySort(String sort) {
		return (Arithmetic)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"demandCollet.queryJsAlgBySort",sort);
	}

}
