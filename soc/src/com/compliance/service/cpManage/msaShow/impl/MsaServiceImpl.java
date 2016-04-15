package com.compliance.service.cpManage.msaShow.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.msaShow.MsaDao;
import com.compliance.model.cpManage.msaShow.Msa;
import com.compliance.service.cpManage.msaShow.MsaService;

/**
 * 用管理安全测评业务实现类
 * @author quyongkun
 *
 */
public class MsaServiceImpl implements MsaService {
	
	/**
	 * 通用管理安全测评dao接口
	 */
	private  MsaDao msaDao;
	
	
	/**
	 * 根据map集合查询通用管理安全测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用管理安全测评集合
	 */
	public List<Msa>  needConnect(Map map){
		return this.msaDao.needConnect(map);
	}
	

	/**
	 * 根据map集合查询通用管理测评评估项个数
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public int rectCount(Map map) {
		return this.msaDao.rectCount(map);
	}
	
	/**
     * 查询唯一日期
	 * @return 通用管理安全测评集合
	 */
	public List<Msa> querySoleDate(){
		return this.msaDao.querySoleDate();
	}


	public MsaDao getMsaDao() {
		return msaDao;
	}


	public void setMsaDao(MsaDao msaDao) {
		this.msaDao = msaDao;
	}
	
	

}
