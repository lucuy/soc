package com.soc.dao.risk;

import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.risk.VulnerabilityAssessment;

public interface AssetRiskGroupDao {

	public int assetRiskGroupPagePage(Map map);

	public List<AssetGroup> queryPage(Map map, int startIndex,int pageSize);

	public String queryByName(Map map);
	
	public String queryByName1(Map map);

	public List<AssetGroup> queryGroupName();

	public void updateGrouprisk(AssetGroup assetgroup);

	public String queryByName2(Map map);

	public int assetRiskGroupPagePage1(Map map);

	public List<Asset> queryPage1(Map map, int startIndex, int pageSize);
	 /**
     * <资产导出成excel>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> export(Map map);
    /**
     * <查询资产分布>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public List<Map> queryAssetsNum(Map map);
	/**
     * 计算符合条件的资产记录数
     * 
     * @param map Map
     * @return 返回符合查询条件的记录数
     */
    public int count(Map map);
    /**
     * 根据资产id查询资产所关联的威胁性平均值
     * 
     * @param str String
     * @return double
     */
    public double avgRiskValue(long id);
    /**
     * 根据资产id查询资产所关联的资产值
     * 
     * @param str String
     * @return double
     */
    public double AssetValue(long id);
    
    /**
     * 根据资产ip查询资产所关联的脆弱性平均值
     * 
     * @param str String
     * @return double
     */
    public double avgVAValue(String str);
    /**
     * 更改资产的威胁值、和脆弱性值
     * 
     * @param Asset
     * 
     */
    public void updateAssetValue(Asset asset);
    /**
     * 查询资产组的值，图表展示
     * 
     * @param map
     * @return List<Map>
     * 
     */
    public List<Map> queryRiskAssetGroup(Map map);
   
    /**
     * 查询资产的值，图表展示
     * 
     * @param map
     * @return List<Map>
     * 
     */
    public List<Map> queryRiskAssets(Map map);
    
    
    
}