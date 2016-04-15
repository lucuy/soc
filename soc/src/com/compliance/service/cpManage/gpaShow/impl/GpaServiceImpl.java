package com.compliance.service.cpManage.gpaShow.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.gpaShow.GpaDao;
import com.compliance.model.cpManage.gpaShow.Gpa;
import com.compliance.service.cpManage.gpaShow.GpaService;
/**
 * 通用物理测评业务实现类
 * @author quyongkun
 *
 */
public class GpaServiceImpl implements GpaService {
	
	
	/**
	 * 通用物理测评dao接口
	 */
	private GpaDao gpaDao;
	
	

	public GpaDao getGpaDao() {
		return gpaDao;
	}

	public void setGpaDao(GpaDao gpaDao) {
		this.gpaDao = gpaDao;
	}

	/**
	 * 根据map集合查询通用物理测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public List<Gpa> needConnect(Map map) {
		return gpaDao.needConnect(map);
	}
	
	/**
	 * 根据map集合查询通用物理测评评估项个数
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	public int rectCount(Map map) {
		return gpaDao.rectCount(map);
	}

	/**
     * 查询唯一日期
	 * @return 通用物理测评集合
	 */
	public List<Gpa> querySoleDate() {
		return gpaDao.querySoleDate();
	}

}
