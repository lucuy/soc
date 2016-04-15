package com.compliance.dao.psad;

import java.util.Date;
import java.util.Map;
import com.compliance.model.psad.Psa;


public interface PsaDao {

	/**
	 * 在psa中插入一条数据
	 */
	public int insert(Psa p);
	
	/**
	 * 修改一条数据
	 */
	public void upData(Psa P);
	
	/**
	 * 查询psa中最新数据条数
	 */
	public int lastNum();
	/**
	 * 查询最新评估时间
	 */
	public Date lastTime();
	
	/**
	 * 根据时间与评估序号查询该评估单元的评估结果
	 */
	public Psa queryResultByTimeAndSort(Map<String, Object> map);
}
