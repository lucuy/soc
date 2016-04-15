package com.compliance.service.cpManage.demand.impl;

/**
 * 整改需求汇总service实现层
 */
import com.compliance.dao.cpManage.demand.ArithmeticDao;
import com.compliance.model.cpManage.demand.Arithmetic;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.impl.BaseServiceImpl;

public class ArithmeticServiceImpl extends BaseServiceImpl implements ArithmeticService {
	
	private ArithmeticDao arithmeticDao;
	
	/**
	 * 根据排序查询js算法
	 * @param String
	 * @return Arithmetic
	 */
	@SuppressWarnings("unchecked")
	public Arithmetic queryJsAlgBySort(String sort) {
		return arithmeticDao.queryJsAlgBySort(sort);
	}

	public ArithmeticDao getArithmeticDao() {
		return arithmeticDao;
	}

	public void setArithmeticDao(ArithmeticDao arithmeticDao) {
		this.arithmeticDao = arithmeticDao;
	}
	
	
	
}
