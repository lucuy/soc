package com.soc.dao.risk;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.risk.AssetRiskEvaluation;

/**
 * 资源风险赋值Dao
 * @author 何贝贝
 *
 */
public interface AssetRiskEvaluationDao extends BaseDao
{
	/**
	 * 计算资源风险值数量
	 * @param map
	 * @return
	 */
	public int count(Map map);
	
	/**
	 * 插入资源风险值
	 * @param assetRiskEvaluation 资源值对象
	 * @return
	 */
    public int insertAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation);
    /**
     * 查询资源风险值
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     */
    public List<AssetRiskEvaluation> query(Map map, int startRow, int pageSize);
    /**
     * 根据ID查询风险资源值
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     */
    public AssetRiskEvaluation queryById(long id);
    
    /**
     * 更新查询资源值
     * @param assetRiskEvaluation
     * @return
     */
    public int updateAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation);
    
    /**
     * 删除查询资源值
     * @param eventIds
     * @return
     */
    public int deleteAssetRiskEvaluationById(int... eventIds);
}
