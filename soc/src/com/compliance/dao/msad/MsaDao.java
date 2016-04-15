package com.compliance.dao.msad;

import java.util.Date;
import java.util.Map;

import com.compliance.model.msad.MsaApp;
import com.compliance.model.msad.Msad;


public interface MsaDao {

	/**
	 * 插入一条数据
	 * 
	 *
	 */
	public int insert(MsaApp m);
	
	/**
	 * 修改一条数据
	 */
	public void upData(MsaApp m);
	
	
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
	public MsaApp queryResultByTimeAndSort(Map<String, String> map);
}
