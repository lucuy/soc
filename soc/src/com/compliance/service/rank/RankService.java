package com.compliance.service.rank;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.rank.Rank;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 定级业务接口
 * @author quyongkun
 *
 */
public interface RankService {
	
	/**
	 * 定级查询
	 * 
	 */
   SearchResult query(Map map, Page page) ;
   
	/**
	 * 已定级查询
	 * 
	 */
   SearchResult queRankFinish(Map map, Page page);
	
	
	/**
	 * 查询所有定级信息
	 * @return 定级集合
	 */
    List<Rank> queryAll();
	
	/**
	 * 定级添加
	 */
	boolean insert(SystemManager systemManager);
	
	
	/**
	 * 定级修改
	 */
	boolean update(Rank rank);
	
	
	/**
	 *根据定级编号查询定级
	 */
	Rank  queryById(int rankId);
	
	  /**
	   * 根据系统编号编号查询定级信息
	   * @param rankId 定级编号
	   * @return 定级
	    */
	public Rank queryBySysId(String sysInFoId);
	
	 
	
	 /**
	 * 查询已定级的定级信息
	 * @return 定级集合
	 */
	List<Rank> queRankFinish();


	/**
	 * 查询定级总数
	 * 
	 */
   int count(Map map);

   /**
    * 根据定级级别查询定级个数
    * @param rankGrade
    * @return
    */
	 int queryByGrade(String rankGrade);
   

	/**
	 * 查询已定级总数
	 * 
	 */
	 int contRankFinish(Map map);
  /**
   * 高级搜索定级信息数据条数
   */
  	int preciseCount(Map map);
  /**
	 * 高级搜索定级信息
	 * 
	 */
  SearchResult preciseQueryRank(Map map, Page page);
  
  /**
   * 根据系统主键id进行删除系统
   * @param id
   */
	public void delete(int id);

}
