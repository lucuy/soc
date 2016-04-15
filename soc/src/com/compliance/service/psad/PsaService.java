package com.compliance.service.psad;

import java.util.Date;
import java.util.Map;

import com.compliance.model.psad.Psa;

public interface PsaService {

	/**
	 * 插入数据
	 * @param p
	 * @return
	 */
	public int insert(Psa p);
	
	/**
	 * 修改整改单元数据
	 */
	public void upData(Psa p);
	
	/**
	 * 查找最新时间
	 */
	public Date queryLastTime();
	/**
	 *查询最新时间内的统计个数
	 */
	
	public int queryTheTimeNum();
	
	
	/**
	 *根据时间与评估单元编号查询该评估单元的 评估结果 
	 */
    public Psa queryResultByTimeAndSort(Map<String, Object>map);
    
}
