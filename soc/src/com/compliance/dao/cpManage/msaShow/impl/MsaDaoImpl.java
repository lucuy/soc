package com.compliance.dao.cpManage.msaShow.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.msaShow.MsaDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.msaShow.Msa;
import com.soc.model.conf.GlobalConfig;

/**
 * 通用管理安全测评dao实现类
 * @author quyongkun
 *
 */
public class MsaDaoImpl extends  BaseDaoiBatis implements MsaDao {
	
	/**
	 * 根据map集合查询通用管理安全测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用管理安全测评集合
	 */
	public List<Msa> needConnect(Map map){
		return super.queryForList(GlobalConfig.sqlId+"msa.needConnect", map);
	}
	
	/**
	 * 根据map集合查询通用管理测评评估项个数
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public int rectCount(Map map) {
		Object ob=super.queryForObject(GlobalConfig.sqlId+"msa.rectCount", map);
		if(ob!=null){
			return  ((Integer)ob).intValue();
		}else{
			return 0;
		}
		
	}
	
	/**
     * 查询唯一日期
	 * @return 通用管理安全测评集合
	 */
	public List<Msa> querySoleDate(){
		return super.queryForList(GlobalConfig.sqlId+"msa.querySoleDate");
	}

}
