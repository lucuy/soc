package com.compliance.service.cpManage.demand;

import com.compliance.model.cpManage.demand.Arithmetic;

 
public interface ArithmeticService{
	/**
	 * 根据排序查询js算法
	 * @param String
	 * @return Arithmetic
	 */
	@SuppressWarnings("unchecked")
	public Arithmetic queryJsAlgBySort(String sort);
}
