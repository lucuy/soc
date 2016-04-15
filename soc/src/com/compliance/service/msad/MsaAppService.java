package com.compliance.service.msad;

import java.util.Date;
import java.util.Map;

import com.compliance.model.msad.MsaApp;

public interface MsaAppService {

	public int insert(MsaApp m);
	
	/**
	 * 修改整改单元数据
	 */
	public void upData(MsaApp m);
	
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
    public MsaApp queryResultByTimeAndSort(Map<String, String>map);
    
    
    
}
