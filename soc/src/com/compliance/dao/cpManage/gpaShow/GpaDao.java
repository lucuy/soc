package com.compliance.dao.cpManage.gpaShow;



import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.gpaShow.Gpa;

/**
 * 通用物理测评dao接口
 * @author quyongkun
 *
 */
public interface GpaDao {
	
	/**
	 * 根据map集合查询通用物理测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	List<Gpa> needConnect(Map map);
	
	/**
	 * 根据map集合查询通用物理测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	 int rectCount(Map map) ;
	
	/**
     * 查询唯一日期
	 * @return 通用物理测评集合
	 */
	List<Gpa> querySoleDate();
	
	

}
