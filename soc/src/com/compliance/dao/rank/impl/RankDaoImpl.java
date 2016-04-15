package com.compliance.dao.rank.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.RankDao;
import com.compliance.model.rank.Rank;
import com.soc.model.conf.GlobalConfig;
/**
 * 定级dao接口实现类
 * @author quyongkun
 *
 */
public class RankDaoImpl extends BaseDaoiBatis implements RankDao {
	 /**
	  * 得到数据条数
	  * @param map
	  * @return 查询结果行数
	  */
	public int count(Map map) {
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"rank.cont", map);
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
	  * 根据定级级别查询定级个数
	  * @param rankGrade 定级级别
	  * @return 定级个数
	  */
	public int queryByGrade(String rankGrade){
		 Object ob = null;
			try {
				ob = super.queryForObject(GlobalConfig.sqlId+"rank.queryByGrade", rankGrade);
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
	  * 得到已定级数据条数
	  * @param map
	  * @return 查询结果行数
	  */
	public int contRankFinish(Map map) {
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"rank.contRankFinish", map);
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
	  * 添加定级信息
	  * @param rank 定级信息
	  * @return 添加是否成功
	  */
	public boolean insert(Rank rank) {
		return super.create(GlobalConfig.sqlId+"rank.insert",rank)==null;

	}
	
	/**
	 * 查询定级信息
	 * @param map
	 * @param startRow
	 * @param pagesize
	 * @return 定级信息集合
	 */
	public List<Rank> query(Map map, int startRow, int pagesize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.query", map, startRow, pagesize);
	}
	
	/**
	 * 查询定级信息
	 * @param map
	 * @param startRow
	 * @param pagesize
	 * @return 定级信息集合
	 */
	public List<Rank> queRankFinish(Map map, int startRow, int pagesize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.queRankFinish", map, startRow, pagesize);
	}
	
	/**
	 * 查询所有定级信息
	 * @return 定级集合
	 */
	public List<Rank> queryAll(){
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.queryAll");
		
	}
	
	 /**
	 * 查询已定级的定级信息
	 * @return 定级集合
	 */
	public List<Rank> queRankFinish(){
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.queRankFinish");
		
	}
	

   /**
    * 根据ID查询定级信息
    * @param id 定级编号
    * @return 定级
    */
	public Rank queryById(int rankId) {
		return (Rank)super.queryForObject(GlobalConfig.sqlId+"rank.queryById",rankId);
	}
	
	  /**
	   * 根据系统编号编号查询定级信息
	   * @param rankId 定级编号
	   * @return 定级
	    */
	public Rank queryBySysId(String sysInFoId){
		List<Rank> ranks=this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.queryBySysId",sysInFoId);
		if(ranks.size()!=0){
			return ranks.get(0);
		}else{
			return null;
		}
		
	}

	/**
	 * 更新定级信息
	 * @param rank 定级
    * @return 更新是否成功
	 */
	public boolean update(Rank rank) {

		return super.update(GlobalConfig.sqlId+"rank.update", rank)>0;
	}
	/**
	 * 高级搜索定级信息数据条数
	 */
	public int precisecount(Map mapper) {
		// TODO Auto-generated method stub
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"rank.precisecount", mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}

		return totalRows;
	}

	public List<Rank> precisequery(Map map, int startRow, int pagesize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rank.precisequery", map, startRow, pagesize);
	}

	public void delete(int id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"rank.delete", id);
	}


}
