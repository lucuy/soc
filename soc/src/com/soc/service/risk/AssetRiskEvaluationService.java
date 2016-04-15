package com.soc.service.risk;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.events.EventLibrary;
import com.soc.model.risk.AssetRiskEvaluation;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 资产风险赋值业务接口类
 * @author 何贝贝
 *
 */
public interface AssetRiskEvaluationService  extends Serializable
{
	/**
     * <计算符合条件的资产风险赋值库记录数>
     * <功能详细描述>
     * @param map Map
     * @return int
     */
    public int count(Map map);
    
    /**
     * 查询资产风险赋值对象
     * @return
     */
    public SearchResult queryAssetRiskEvaluation(Map map, Page page);
    /**
     * 根据Id查询资产风险赋值对象
     * @param map
     * @return
     */
    public AssetRiskEvaluation queryAssetRiskEvaluationById(Map map);
    
    
	/**
     * 插入资产风险赋值
     * @param eventLibrary
     * @return
     */
    public int addAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation);
    
    
    /**
     * 更新资产风险赋值
     * @param eventLibrary
     * @return
     */
    public int modifyAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation);
    /**
     * 根节点ids删除 资产风险赋值
     * @param ids
     * @return
     */
    public int deleteAssetRiskEvaluationById(int...ids);
    /**
     * 根据ID查询资产风险赋值对象
     * @return
     */
    public AssetRiskEvaluation queryInfoById(int id);
    
    
   /* *//**
     * <查询所有的为资产赋值的记录>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     *//**/
    public String charts(int id,String falg);
    
    /**
	 * <获得导出文件的map>
	 * <功能详细描述>
	 * @param auditReportId
	 * @param path
	 * @param reprotType
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
		public Map<String, Object> getFreemakerMapExport(long auditReportId,
				String path, String reprotType);
}
