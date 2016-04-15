package com.compliance.dao.cpManage.demand;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.demand.DemandCollet;

/**
 * 
 * Description：整改需求汇总数据层接口 
 * @author leiya
 *2013-5-15下午6:51:57
 */
public interface DemandColletDao {
    /**
     * 查询数据
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     */
	public List<DemandCollet>query(Map<String , Object> map, int startRow, int pageSize);
	/**
	 * 根据控制单元域排序得到控制单元域名称
	 * @param unitDomainName
	 * @return
	 */
	public String queryUnitDomainNameByNum(String unitDomainName);
	/**
	 * 根据控制单元排序得到控制单元名称
	 * @param unitName
	 * @return
	 */
	public String queryUnitNameByNum(String  unitName);
	/**
	 * 根据评估项排序名称得到评估项内容
	 * @param unitNameNum
	 * @return
	 */
	public String queryConByUnitName(String unitNameNum);
	
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
