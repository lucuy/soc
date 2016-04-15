package com.compliance.service.cpManage.contrastRectification;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface ContrastService {

	/**
	 * 得到系统列表
	 */
	public List<RectificationProposal> auerySysList();
	
	/**
	 * 	查询整改建议历史对比
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult query(Map<String , Object> map, Page page);
	/**
	 * 导出文件
	 */
	public List<RectificationProposal> query(Map<String , Object> map);
}
