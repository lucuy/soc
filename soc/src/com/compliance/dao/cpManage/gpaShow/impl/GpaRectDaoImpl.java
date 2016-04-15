package com.compliance.dao.cpManage.gpaShow.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.gpaShow.GpaRectDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.gpaShow.GpaRect;
import com.soc.model.conf.GlobalConfig;
/**
 * 通用物理整改建议dao实现类
 * @author quyongkun
 *
 */
public class GpaRectDaoImpl extends  BaseDaoiBatis implements GpaRectDao {
	
	/**
	 * 查询所有的通用物理源数据
	 * @return 所有的通用物理源数据
	 */
	public List<GpaRect> queryAllData() {
		return super.queryForList(GlobalConfig.sqlId+"gpaRect.queryAllData");
	}
	
	/**
	 * 根据日期 查询通用物理整改建议
	 * @return 通用物理整改建议
	 */	
	@SuppressWarnings("unchecked")
	public List<GpaRect> query(String gpaDate) {
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("gpaDate", gpaDate+" ");
		 List<GpaRect> rects = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"gpaRect.query",map);
		return rects;
	}
	
	/**
	 * 根据map查询通用物理整改建议历史
	 * @param map
	 * @return
	 */
	public List<GpaRect> queryByMap(Map map){
		return super.queryForList(GlobalConfig.sqlId+"gpaRect.queryByMap",map);
	}

	
	/**
	 * 查询整改建议树
	 * @return 通用物理整改建议集合
	 */
	public List<GpaRect>  queryTree(){
		return super.queryForList(GlobalConfig.sqlId+"gpaRect.queryTree");
	}
	
	/**
	 * 插入通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 插入是否成功
	 */
	public boolean insert(GpaRect gpaRect) {
		return super.create(GlobalConfig.sqlId+"gpaRect.insert", gpaRect)==null;
	}
	
	/**
	 * 根据主键删除通用物理整改建议
	 * @param gpaRectId 通用物理整改建议主键
	 * @return 删除是否成功
	 */	
	public boolean delete(int gpaRectId) {
		
		return super.delete(GlobalConfig.sqlId+"gpaRect.delete", gpaRectId)>0;
	}
	
	/**
	 * 修改通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 修改是否成功
	 */
	public boolean update(GpaRect gpaRect) {
		return super.update(GlobalConfig.sqlId+"gpaRect.update", gpaRect)>0;
	}

	/**
	 * 根据主键查询通用物理整改建议
	 * @param gpaRectId 主键
	 * @return
	 */
	public GpaRect queryById(int gpaRectId){
		return (GpaRect)super.queryForObject(GlobalConfig.sqlId+"gpaRect.queryById", gpaRectId);
	}
	


}
