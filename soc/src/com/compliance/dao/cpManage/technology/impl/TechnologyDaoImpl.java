package com.compliance.dao.cpManage.technology.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.technology.TechnologyDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.technology.Technology;
import com.soc.model.conf.GlobalConfig;



/**
 * Description: 技术差距分析 DaoImpl
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 * 
 */
public class TechnologyDaoImpl extends BaseDaoiBatis implements TechnologyDao {

	/**
	 * 计算有多少条记录
	 * @param map
	 * @return int
	 */
	public int count(Map map) {
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"technology.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}
	
	/**
	 * 查询数据
	 * @param Map, int, int
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> query(Map<String , Object> map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"technology.query", map,startRow,pageSize);
	}
	
	/**
	 * 查询信息系统最新评估结果列表
	 * @param 
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryMaxEndTime() {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"technology.queryMaxEndTime");
	}
	
	
	/**
	 * 根据系统id查询状态为评估结束的列表
	 * @param String
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryByAcIdForCs2(String currentState){
		return (List<Technology>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"technology.queryByAcIdForCs2", currentState);
	}
	
	/**
	 * 根据id查询某条系统详细信息
	 * @param id
	 * @return Technology
	 */
	@SuppressWarnings("unchecked")
	public Technology queryById(int id) {
		return (Technology)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"technology.queryById",id);
	}

	/**
	 * 删除数据
	 * @param id
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void delete(int id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"technology.deleteById",id);
	}
	
	
	/**
	 * 插入数据
	 * @param Technology
	 * @return void
	 */
	public int insert(Technology t) {
		Integer pk = 0;
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"technology.insert", t);
		if (obj != null) {
			pk = Integer.parseInt(obj.toString());
		}
		return pk;
	}
	
	/**
	 * 修改数据
	 * @param Technology
	 * @return void
	 */
	public void update(Technology t) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"technology.update", t);
	}
	
	/**
	 * 修改状态
	 * @param Map
	 * @return void
	 */
	public void updateCurrentState(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"technology.updateCurrentState", map);
	}
	
	/**
	 * 修改结束时间
	 * @param Map 
	 * @return void
	 */
	public void updateEndTime(Map map){
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"technology.updateEndTime",map);
	}
	
}
