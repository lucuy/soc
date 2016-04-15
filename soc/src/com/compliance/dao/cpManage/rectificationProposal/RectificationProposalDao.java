package com.compliance.dao.cpManage.rectificationProposal;

import java.util.Map;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;

/**
 * 
 * Description：操作整改建议表 
 * @author leiya
 *2013-5-19下午1:53:25
 */
public interface RectificationProposalDao {

	/**
	 * 根据已经评估结束后的信息系统id及不符合评估项编号进行查询该项评估相关内容
	 * @param map
	 * @return
	 */
	public String queryByIdAndItemNumber(Map map);
	
	/**
	 * 查询创建时间
	 */
	public String queryCreatDateByIdAndItemNumber(Map map);
	
	/**
	 * 查id
	 */
	public int queryIDBySysIdAndIteamNum(Map map);
	
	/**
	 * 查询评估项个数
	 */
	public int queryRectNum(Map map);
	
	
	/**
	 * 添加整改建议
	 */
	public void insterProposa(RectificationProposal proposal);
	/**
	 * 修改整改建议项
	 */
	public void updataProposal(RectificationProposal proposal);
	
	/**
	 * 删除整改建议
	 */
	public void deleteProposal(Map map);
}
