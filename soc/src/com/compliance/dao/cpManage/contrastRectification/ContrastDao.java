package com.compliance.dao.cpManage.contrastRectification;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;

public interface ContrastDao {

	/**
	 * 获得历史整改对比系统列表
	 */
	public  List<RectificationProposal> querySysList();
	 
	/**
	 * 计算有多少条记录
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int count(Map map);
	/**
	 * 查询控制单元列表
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<RectificationProposal> queryUnitList(Map<String , Object> map, int startRow, int pageSize);
	/**
	 * 查询整改建议控制项列表
	 */
	@SuppressWarnings("rawtypes")
	public List <RectificationProposal> queryItemList(Map map);
	
	public List<RectificationProposal> queryUnitList(Map<String , Object> map);
}
