package com.soc.dao.risk.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.risk.AssetRiskEvaluationDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.risk.AssetRiskEvaluation;

/**
 * 事件库Dao实现类 对事件树的表（tbl_event_Library）进行操作
 * 
 * @author 何贝贝
 * 
 */
public class AssetRiskEvaluationDaoIbatis extends BaseDaoIbatis implements AssetRiskEvaluationDao {

	/**
     * {@inheritDoc}
     */
	public int count(Map map) {
		
		Object ob = null;
		
		// 根据map中存储的条件查询符合条件的事件列表的记录数
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"assetRiskEvaluation.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 总条数
		int totalRows = 0;
		
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}

	@Override
	public int insertAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.assetRiskEvaluation", assetRiskEvaluation);
		return 0;
	}

	/**
     * {@inheritDoc}
     */
	@Override
    public List<AssetRiskEvaluation> query(Map map, int startRow, int pageSize)
    {
         return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.assetRiskEvaluation", map, startRow,	pageSize);
    }

	@Override
	public AssetRiskEvaluation queryById(long id){
		return (AssetRiskEvaluation)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.assetRiskEvaluationByID",id);
	}
	@Override
	public int updateAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation){
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"update.assetRiskEvaluation", assetRiskEvaluation);
		return 0;
	}

	@Override
	public int deleteAssetRiskEvaluationById(int... ids) {
		
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<ids.length;i++) {
			sb.append( "'"+ids[i] + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("assetRiskEvaluationId", sb.toString());
		return this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.assetRiskEvaluation", map);
	}

}
