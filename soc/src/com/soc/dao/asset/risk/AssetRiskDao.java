package com.soc.dao.asset.risk;

import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetRiskGroup;
import com.soc.model.asset.AssetRiskValue;
import com.soc.webapp.action.asset.AssetRisk;

public interface AssetRiskDao
{
    public AssetRiskGroup queryAssetRiskGroup(String groupName);

    public void addAssetRiskValue(AssetRiskValue assetRiskValue);

    public List<AssetRiskValue> queryAssetRiskValuebyname(String groupName);

    public List<AssetRiskValue> queryAssetRiskValue();
    
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int associationPage(Map<String, Object> map);

    public List<Map<String, Object>> queryPage(Map<String, Object> map, int startIndex, int pageSize);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseInt
     * @see [类、类#方法、类#成员]
     */
    public void deleteRisk(int parseInt);
    /**
     * <排序>
     * @param parseInt
     * @see [类、类#方法、类#成员]
     */
    public List<AssetRiskValue> sort(String str,int startRow,int pageSize);
    
    
    /**
     * 根据id获得威胁信息的对象
     * @param id
     * @return
     */
    public AssetRiskValue queryAssetRiskValue(long id);
    
    /**
     * 修改威胁信息
     * @param map
     */
    public void updateAssetRiskValue(Map map);
 
    
    /**
     * 导出威胁信息
     * @param map
     * @return
     */
    public List<Map> ReportExcel(Map map);
    /**
     * 导出威胁信息
     * @param map
     * @return
     */
    public List<Map> ReportExcelMould(Map map);
    /**
     * 根据导入的资产名称查询资产
     * @param map
     * @return
     */
    
    public Asset RiskQueryAssetByName(String str);
}