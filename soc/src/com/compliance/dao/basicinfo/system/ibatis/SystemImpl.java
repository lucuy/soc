package com.compliance.dao.basicinfo.system.ibatis;

import java.util.List;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.basicinfo.system.SystemDao;

import com.compliance.model.basicinfo.system.SystemManager;
import com.soc.model.conf.GlobalConfig;

public class SystemImpl extends BaseDaoiBatis implements SystemDao {

	/**
	 * 计算有多少条记录
	 * 
	 * @param map
	 * @return int
	 */
	public int count(Map map) {
		Object ob = null;
		try {
			ob = this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"system.querycountByName", map);
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
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Admin
	 */
	public SystemManager queryById(int id) {
		return (SystemManager) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"system.queryById", id);
	}

	/**
	 * 根据系统编号查询数据
	 * 
	 * @param String
	 * @return SystemManager
	 */
	public SystemManager queryBySysId(String sysId) {
		return (SystemManager) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"system.queryBySysId", sysId);
	}

	/**
	 * 查询所有数据
	 * 
	 * @param Map
	 * @return List<Admin>
	 */
	@SuppressWarnings("unchecked")
	public List<SystemManager> query(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"system.query", map);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param Map
	 *            , int, int
	 * @return List<Admin>
	 */
	public List<SystemManager> queryForPage(Map map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"system.query", map,
				startRow, pageSize);
	}

	/*
	 * public List<Pro_Line> queryForPageScore(Map map, int startRow, int
	 * pageSize) { return
	 * this.getSqlMapClientTemplate().queryForList("system.query.score",map,
	 * startRow, pageSize); }
	 */
	/**
	 * 信息查询列表
	 */
	public List<SystemManager> query(Map map, int startRow, int pagesize) {

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"system.query", map,
				startRow, pagesize);
	}

	/**
	 * 添加信息系统并返回PK
	 * 
	 * @param SystemManager
	 * @return id值
	 */
	public int insert(SystemManager system) {
		Integer pk = 0;
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"system.insert",
				system);
		if (obj != null) {
			pk = Integer.parseInt(obj.toString());
		}
		return pk;
	}

	public int insertProLine(SystemManager system) {
		Integer pk = 0;
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"proline.insert",
				system);
		if (obj != null) {
			pk = Integer.parseInt(obj.toString());
		}
		return pk;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"system.deleteref", id);
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"system.delete", id);

	}

	public void deleteProLine(String id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"proline.delete", id);

	}

	/**
	 * 更新数据
	 * 
	 * @param SystemManager
	 */
	public void update(SystemManager system) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"system.update", system);
	}
	
	/**
	 * 更新数据
	 * 
	 * @param SystemManager
	 */
	@SuppressWarnings("unchecked")
	public void updateOther(SystemManager system){
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"system.updateOther1", system);
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"system.updateOther2", system);
	}
	

	public void insertRecord(String sysId) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"record.insert", sysId);
	}

	public void deleteRecord(String id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"record.delete", id);

	}

	public int countPrecise(Map map) {
		// TODO Auto-generated method stub
		Object ob = null;
		try {
			ob = this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"sysPreciseCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}

	public List<SystemManager> queryPrecise(Map map, int startRow, int pagesize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"system.precisequery", map, startRow, pagesize);
	}

}
