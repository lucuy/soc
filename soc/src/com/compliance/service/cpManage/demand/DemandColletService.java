package com.compliance.service.cpManage.demand;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.demand.DemandCollet;
import com.util.page.Page;
import com.util.page.SearchResult;
 
public interface DemandColletService{
	 
	
	public String queryUnitDomainNameByNum(String unitDomainName);
	/**
	 * 查询整改需求汇总
	 * @param map
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<DemandCollet> query(Map map);
	
	@SuppressWarnings("rawtypes")
	public SearchResult queryDemand(Map map, Page page);
	/**
	 * 根据评估项排序得到评估项是树形结构
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryBySortForTree(Map map);
	
	/**
	 * 根据排序查询评估项是否还有下一级
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryNextSortInfo(Map map);
	
	/**
	 * 根据排序查找该对象
	 * @param String
	 * @return DemandCollet
	 */
	@SuppressWarnings("unchecked")
	public DemandCollet querydemandColletBySort(String sort);
	
	/**
	 * 根据排序查询评估项是否还有下一级
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> queryNextSort(Map map);
	
	/**
	 * 根据排序查询评估项
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> querySortInfo(Map map);
	
	/**
	 * 查询还有哪些项未评估
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryAssessInfo(Map map);
	
	/**
	 * 差距分布图查询
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryAssessInfoImage(Map map);
	
}
