package com.compliance.dao.cpManage.demand;

import com.compliance.model.cpManage.demand.Arithmetic;

/**
 * Description: 根据sort排序查询js算法 Dao
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-21
 * @Modified by
 * 
 */
public interface ArithmeticDao {
	
	/**
	 * 根据排序查询js算法
	 * @param String
	 * @return Arithmetic
	 */
	@SuppressWarnings("unchecked")
	public Arithmetic queryJsAlgBySort(String sort);
	
}
