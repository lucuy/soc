package com.compliance.dao.cpManage.gpaShow.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.gpaShow.GpaDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.gpaShow.Gpa;
import com.soc.model.conf.GlobalConfig;

public class GpaDaoImpl extends  BaseDaoiBatis implements GpaDao  {

	
	/**
	 * 根据map集合查询通用物理测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public List<Gpa> needConnect(Map map) {
		return super.queryForList(GlobalConfig.sqlId+"gpa.needConnect", map);
	}
	
	/**
	 * 根据map集合查询通用物理测评评估项个数
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public int rectCount(Map map) {
		Object ob=super.queryForObject(GlobalConfig.sqlId+"gpa.rectCount", map);
		if(ob!=null){
			return  ((Integer)ob).intValue();
		}else{
			return 0;
		}
		
	}
	

	/**
     * 查询唯一日期
	 * @return 通用物理测评集合
	 */
	public List<Gpa> querySoleDate() {
		return super.queryForList(GlobalConfig.sqlId+"gpa.querySoleDate");
	}

}
