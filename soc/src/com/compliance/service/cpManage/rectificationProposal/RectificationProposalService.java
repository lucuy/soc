package com.compliance.service.cpManage.rectificationProposal;

import java.util.Map;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface RectificationProposalService {

	/**
	 * 查询整改需求汇总
	 * @param map
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult query(Map map, Page page);
	
	 /**
	  * 添加整改建议项
	  */
	public void  insterProposal(RectificationProposal proposal);
	
	/**
	 * 修改整改建议
	 */
	public void updataProposal(RectificationProposal proposal);
	/**
	 * 删除整改监狱
	 */
	public void deleteProposal(Map map);
	
	/**
	 * 查询评估项个数
	 */
	public int queryRectNum(Map map);
}
