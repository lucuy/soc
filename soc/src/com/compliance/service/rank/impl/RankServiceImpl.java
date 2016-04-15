package com.compliance.service.rank.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.rank.RankDao;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.rank.Rank;
import com.compliance.service.rank.RankService;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 定级业务接口实现类
 * @author quyongkun
 *
 */
public class RankServiceImpl implements RankService {
	
	/**
	 * 定级dao接口
	 */
	private RankDao rankDao;
	
	public void setRankDao(RankDao rankDao) {
		this.rankDao = rankDao;
	}
	
	public RankDao getRankDao() {
		return rankDao;
	}
	

	/**
	 * 根据定级编号查询数据
	 */
	public Rank queryById(int rankId) {
		return this.rankDao.queryById(rankId);
	}
	
	  /**
	   * 根据系统编号编号查询定级信息
	   * @param rankId 定级编号
	   * @return 定级
	    */
	public Rank queryBySysId(String sysInFoId){
		return this.rankDao.queryBySysId(sysInFoId);
	}
	
	/**
	 * 查询所有定级信息
	 * @return 定级集合
	 */
	public List<Rank> queryAll(){
		return this.rankDao.queryAll();
		
	}
	
	 /**
	 * 查询已定级的定级信息
	 * @return 定级集合
	 */
	public List<Rank> queRankFinish(){
		return this.rankDao.queRankFinish();
		
	}

	
    /**
     * 更新定级信息
     */
	public boolean update(Rank rank) {
		return this.rankDao.update(rank);
	}


	/**
	 * 添加定级
	 */
	public boolean insert(SystemManager systemManager) {
		Rank rank=new Rank();
		rank.setSysInfoId(systemManager.getSysId());
		rank.setSysInfoName(systemManager.getSysName());
		rank.setSysInfoBusDescription(systemManager.getBusDescription());
		return this.rankDao.insert(rank);
	}
	

	public SearchResult query(Map map, Page page) {
		 //查询数据
		int rowCount =this.rankDao.count(map);
		page.setTotalCount(rowCount);
		List<Rank>list = this.rankDao.query(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public SearchResult queRankFinish(Map map, Page page) {
		 //查询数据
		int rowCount =this.rankDao.contRankFinish(map);
		page.setTotalCount(rowCount);
		List<Rank>list = this.rankDao.queRankFinish(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/**
	 * 总的定级条数
	 */
	public int count(Map map) {
		return rankDao.count(map);
	}
	
	   /**
	    * 根据定级级别查询定级个数
	    * @param rankGrade
	    * @return
	    */
	public	 int queryByGrade(String rankGrade){
		return rankDao.queryByGrade(rankGrade);
		 }
	

	/**
	 * 总的已定级条数
	 */
	public int contRankFinish(Map map) {
		return rankDao.contRankFinish(map);
	}
	/**
	 * 高级搜索定级信息条数
	 */
	public int preciseCount(Map map) {
		// TODO Auto-generated method stub
		return rankDao.precisecount(map);
	}
	/**
	 * 高级搜索定级信息详细
	 */
	public SearchResult preciseQueryRank(Map map, Page page) {
		// TODO Auto-generated method stub
		//查询数据
		int rowCount =this.rankDao.precisecount(map);
		page.setTotalCount(rowCount);
		List<Rank>list = this.rankDao.precisequery(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/**
	 * 根据id进行删除
	 */
	public void delete(int id) {
		rankDao.delete(id);
	}
	
}
