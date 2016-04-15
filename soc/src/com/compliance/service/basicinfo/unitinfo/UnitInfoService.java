package com.compliance.service.basicinfo.unitinfo;

import com.compliance.model.basicinfo.unitinfo.UnitInfo;

public interface UnitInfoService {

	/**
	 * 查询单位信息
	 * 
	 * @param null
	 * @return UnitInfo
	 * @author lidayong createDate 2013-2-17
	 * 
	 */
	public UnitInfo query();

	/**
	 * 添加单位信息
	 * 
	 * @param UnitInfo
	 * @return void
	 * @author lidayong createDate 2013-2-17
	 * 
	 */
	public void insert(UnitInfo unitInfo);

	/**
	 * 修改单位信息
	 * 
	 * @param UnitInfo
	 * @author lidayong
	 * @return void createDate 2013-2-17
	 * 
	 */
	public void update(UnitInfo unitInfo);

}
