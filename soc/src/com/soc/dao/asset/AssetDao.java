package com.soc.dao.asset;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.asset.Asset;

/**
 * 
 * 资产管理Dao类
 * 实现资产增加，删除，查询，快速查询，高级查询
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  
 * @since  [任务管理/资产管理/V100R001C001]
 */
public interface AssetDao extends BaseDao
{
    
    /**
     * 计算符合条件的资产记录数
     * 
     * @param map Map
     * @return 返回符合查询条件的记录数
     */
    public int count(Map map);
    
    /**
     * 按条件分页查询资产
     * 
     * @param map Map
     * @param startRow int
     * @param pageSize int
     * @return 返回符合查询条件的资产结果列表List<Asset>
     */
    public List<Asset> query(Map map, int startRow, int pageSize);
    
    /**
     * 按条件查询资产
     * 
     * @param map Map
     * @return 返回符合查询条件的资产结果列表List<Asset>
     */
    public List<Asset> query(Map map);
    
    /**
     * 查询符合条件的记录列表
     * 
     * @param map Map
     * @return List<Asset>
     * @see com.soc.dao.asset.AssetDao#query(Map, int, int)
     */
    public List<Asset> queryAssetByAssetGroupId(long assetGroupId);
    
    /**
     * 标记删除资产
     * 根据传入的值标记删除资产
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void deleteAsset(Map map);
   
    /**
     * 插入资产
     * 根据传入的数据插入一条资产
     * @param asset Asset
     * @see 
     */
    public long insertAsset(Asset asset);
    
    /**
     * 更新资产
     * 根据传入的数据更新一条资产
     * @param asset Asset
     * @see 
     */
    public void updateAsset(Asset asset);
    
    /**
     * 查询一条资产
     * 根据id查询资产
     * @param id long
     * @return Asset
     * @see 
     */
    public Asset queryByid(long id);
    /**
     * 查询一条资产
     * 根据id查询资产--查询是的未被标记删除的资产
     * @param id long
     * @return Asset
     * @see 
     */
    public Asset queryByid1(long id);
    /**
     * 根据资产名称查询
     * 根据资产名称查询
     * @param name String
     * @return List<Asset>
     * @see 
     */
    public List<Asset> queryByName(String name);
    
    /**
     * 删除资产所关联的资产组
     * 删除资产组时候，将所关联的资产的资产组赋值为未分组
     * @param map Map
     * @see 
     */
    public void deleteGroup(Map map);
    
    /**
     * 更改资产所关联的资产组的名称
     * 根据修改的资产组信息修改资产内关联的资产组的信息
     * @param map Map
     * @see 
     */
    public void updateGroupName(Map map);
    
    /**
     * 根据标记产寻
     * @param Map
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public List<Map>  queryIpTree();
    
    /**
     * 查询设备
     * @param 
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCategory();
    
    /**
     * 查询设备
     * @param 
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCategoryService();
    
    /**
     * 查询设备
     * @param 
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCategorys();
    
    /**
     * 查询设备
     * @param upsId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCategory(long upsId);
    
    /**
     * 查询设备
     * @param deviceId long
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryDeviceById(long deviceId);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> queryDevice(long deviceId);
    
    /**
     * <验证Mac地址的唯一性>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Asset> checkMac(Map map);
    
    /**
     * <更新资产的状态>
     *  <>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void updateStatus(Map map);
    
    /**
     * <资产导出成excel>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> export(Map map);
    
    /**
     * <查询某个资产组结构下是否有资产存在>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int selectByGroupId(Map map);
    
    /**
     * <根据设备id查询所对应的规则>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryDeviceRules(long id);
    
    
    /**
     * 根据ip查询资产
     * @param map
     * @return
     */
    public int queryVaNo(Map map);
    
    /**
     * 根据组Id查询对应的组内资产
     * @param map
     * @return list
     * 
     */
    public List<Asset> queryAssetByGroupId(Map map);
    
    /**
     * 查询对应组中所有的资产
     * @param map
     * @return list
     */
    public List<Asset> queryAllAsset(Map map,int startRow , int pageSize) ; 
    
    /**
     * 更新资产的下发状态
     * @param asset
     * @return void
     */
    public void updateIssued(Asset asset) ;
    
    /**
     * 更新资产的关联解析规则
     * @param map
     * @return void
     */
    public void updateAnalysisRules(Map map) ; 
    /**
     * 删除威胁值
     * @param map
     * @return void
     */
    public void deleteRiskValues(long id  ) ; 
    /**
     * 删除资产和资产组中间表
     * @param long
     * @return void
     */
    public void deleteAssetGroupAssets(long id  ) ; 
    /**
     * 删除关联的资产风险值
     * @param long
     * @return void
     */
    public void deleteAssetValues(long id ) ; 
    /**
     * 删除关联的脆弱性值
     * @param String
     * @return void
     */
    public void deleteRiskVulnerabilityassessment(String ip ) ; 
    /**
     * 修改关联的脆弱性值状态
     * @param map
     * @return void
     */
    public void updateRiskVulnerabilityassessmentStatus(Map map) ;
    /**
     * 修改关联的风险值状态
     * @param map
     * @return void
     */
    public void updateAssetValuesStatus(Map map ) ; 
    
    /**
     * 修改关联的威胁值状态
     * @param map
     * @return void
     */
    public void updateRiskValuesStatus(Map map  ) ;
    public void updateAssetVavulNerabilityValue(Map map);

	public List<Integer> queryAssetIdWorkOrder(Map map2);
	
	public Asset queryAssetByIp(long ip);
	
	/**
	 * 根据父级的设别种类，查询子集的字符串，并以","拼接返回
	 * @param long
	 * @return String
	 */
	public String queryCategoryByFatherId(long deviceId) ; 
	/**
	 * <根据传入的设备种类查询关联的资产id串>
	 * @param Map
	 * @return value String
	 */
	public String queryAssetIdByCategoryName(Map map) ; 
}
