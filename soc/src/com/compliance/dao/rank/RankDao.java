package com.compliance.dao.rank;

import java.util.List;
import java.util.Map;

import com.compliance.model.rank.Rank;
/**
 * 定级dao接口
 * @author quyongkun
 *
 */
public interface RankDao {
	
	/**
	 * 定级条数
	 * @param mapper
	 * @return
	 */
	 int count(Map mapper);
	 
	 /**
	  * 根据定级级别查询定级个数
	  * @param rankGrade 定级级别
	  * @return 定级个数
	  */
	 int queryByGrade(String rankGrade);
	 
	 /**
	  * 得到已定级数据条数
	  * @param map
	  * @return 查询结果行数
	  */
	 int contRankFinish(Map map);
	
	
	/**
	 * 查询定级信息
	 * @param map
	 * @param startRow
	 * @param pagesize
	 * @return 定级信息集合
	 */
	
	  List<Rank>query(Map map,int startRow,int pagesize);

		
		/**
		 * 查询已定级信息
		 * @param map
		 * @param startRow
		 * @param pagesize
		 * @return 定级信息集合
		 */
		
	 List<Rank>queRankFinish(Map map,int startRow,int pagesize);
	 
	 /**
	 * 查询所有定级信息
	 * @return 定级集合
	 */
	 List<Rank> queryAll();
	 
	 /**
	 * 查询已定级的定级信息
	 * @return 定级集合
	 */
	 List<Rank> queRankFinish();

	
    /**
     * 根据定级编号查询定级信息
     * @param rankId 定级编号
     * @return 定级
     */
	 Rank queryById(int rankId);
	 
	  /**
	   * 根据系统编号编号查询定级信息
	   * @param rankId 定级编号
	   * @return 定级
	    */
	Rank queryBySysId(String sysInFoId);

	
	
	/**
	 * 更新定级信息
	 * @param rank 定级信息
     * @return 更新是否成功
	 */
	 boolean update(Rank rank);
	
	 /**
	  * 添加定级信息
	  * @param rank 定级信息
	  * @return 添加是否成功
	  */
	 boolean insert(Rank rank);
	 /**
	  * 高级搜索数据条数
	  */
	 int precisecount(Map mapper);
	 /**
	  * 高级搜索定级信息
	  */
	 List<Rank> precisequery(Map map,int startRow,int pagesize);
/**
 * 根据id进行删除
 * @param id
 */
	 public void delete(int id);
}
