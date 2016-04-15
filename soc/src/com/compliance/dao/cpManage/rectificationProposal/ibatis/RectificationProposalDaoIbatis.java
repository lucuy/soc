package com.compliance.dao.cpManage.rectificationProposal.ibatis;

import java.util.Map;

import com.compliance.dao.cpManage.rectificationProposal.RectificationProposalDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.soc.model.conf.GlobalConfig;

public class RectificationProposalDaoIbatis extends BaseDaoiBatis implements RectificationProposalDao{
	/**
	 * 根据id与差距项编号查询信息
	 */
	public String queryByIdAndItemNumber(Map map) {
		return  (String) super.queryForObject(GlobalConfig.sqlId+"rectificationProposal.queryBySysIdAndIteamNum", map);
	}
/**
 * 得到整改建议创建时间
 */
	public String queryCreatDateByIdAndItemNumber(Map map) {
		 
	 return  (String) super.queryForObject(GlobalConfig.sqlId+"rectificationProposal.queryCreatDateBySysIdAndIteamNum", map);
	}
	
	/**
	 * 查询评估项个数
	 */
	public int queryRectNum(Map map){
		int num =((Integer) super.queryForObject(GlobalConfig.sqlId+"rectificationProposal.queryRectNum", map)).intValue();
		return num;
	}
	
	
	
	/**
	 * 插入整改建议
	 */
   public void insterProposa(RectificationProposal proposal) {
	this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"rectificationProposal.inster", proposal);
	
}
   /**
    * 查询id
    */
	@SuppressWarnings("rawtypes")
	public int  queryIDBySysIdAndIteamNum(Map map) {
		int id =((Integer) super.queryForObject(GlobalConfig.sqlId+"rectificationProposal.queryIDBySysIdAndIteamNum", map)).intValue();
		return id;
	}
	/**
	 * 修改整改建议
	 */
public void updataProposal(RectificationProposal proposal) {
	this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"rectificationProposal.updata", proposal);
	
}
/**
 * 删除整改建议
 */
	public void deleteProposal(Map map) {
		
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"rectificationProposal.delete", map);
	}

}
