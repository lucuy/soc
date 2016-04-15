package com.soc.service.asset;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.topo.model.device.Device;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * 资产管理业务类
 * 实现资产增加，删除，查询，快速查询，高级查询
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  com.soc.dao.asset.AssetDao
 * @since  [任务管理/资产管理/V100R001C001]
 */
public interface AssetService extends Serializable
{
    /**
     * 查询符合条件的记录数
     * 
     * @param map Map
     * @return int
     * @see com.soc.dao.asset.AssetDao#count(Map)
     */
    public int count(Map map);
    
    /**
     * 查询符合条件的记录列表
     * 
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.asset.AssetDao#query(Map, int, int)
     */
    public SearchResult query(Map map, Page page);
    
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
     * 删除资产
     * 根据资产id标记删除资产
     * @param assetId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteAsset(long assetId);
    
    /**
     * 查询资产
     * 根据资产id查询资产
     * @param id long
     * @return Asset
     * @see com.soc.dao.asset.AssetDao#queryByid(long)
     */
    public Asset searchById(long id);
    
    /**
     * 更新资产
     * 包括资产的新建，修改
     * @param asset Asset
     * @see com.soc.dao.asset.AssetDao#updateAsset(Asset)
     */
    public long updateAsset(Asset asset, String assetGroupIds,Device device);
    
    /**
     * 根据名称查询
     * 根据传入的资产名字查询资产
     * @param name String
     * @return List<Asset>
     * @see com.soc.dao.asset.AssetDao#queryByName(String)
     */
    public List<Asset> queryByName(String name);
    
    /**
     * 删除资产组所关联的资产
     * 将所关联资产组的资产的资产组置为未分组
     * @param assetGroupid long
     * @see com.soc.dao.asset.AssetDao#deleteGroup(Map)
     */
    public void deleteAssetGroup(long assetGroupid);
    
    /**
     * 查询树
     * @param map Map
     * @see 
     */
    public String queryIpTree(String objectBath);
    
    /**
     * 查询设备种类
     * @param map Map
     * @see 
     */
    public List<Map> queryCategory();
    
    /**
     * 查询设备种类
     * @param map Map
     * @see 
     */
    public List<Map> queryCategoryService();
    
    /**
     * 查询设备种类
     * @param map Map
     * @see 
     */
    public List<Map> queryCategorys();
    
    /**
     * 根据upsId查询设备种类
     * @param upsId 
     * @see 
     */
    public List<Map> queryCategory(long upsId);
    
    /**
     * 根据deiceId查询设备种类
     * @param upsId 
     * @see 
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
     * <验证mac地址的唯一性>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Asset> checkMac(Map map);
    
    /**
     * <更新资产的状态>
     * <功能详细描述>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void updateStatus(Map map);
    
    /**
     * <导出excel格式>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> export(Map map);
    
    /**
     * <查询某个组下的结构内是否有资产存在>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int selectByGroupId(Map map);
    
    /**
     * <资产导入的时候的插入>
     * <功能详细描述>
     * @param asset
     * @see [类、类#方法、类#成员]
     */
    public void insertAsset(Asset asset);
    
    
    public int queryVaNo(Map map);
    /**
     * 根据资产组ID查询组下的资产
     * @param map
     * @retrun List<Asset> [类、类#方法、类#成员]
     */
     public List<Asset> queryAssetByGroupId(Map map) ; 
/**
 * <根据登录角色的id查询出管辖的所有资产>
 * <功能详细描述>
 * @param id
 * @return
 * @see [类、类#方法、类#成员]
 */
      public List<Asset> queryAllAssetByUserId(long id) ; 
     /**
      * 资产下发功能
      * @param Asset 
      * @retrun void [类、类#方法、类#成员]
      */
     public void createCollectorConfigFile(Asset a) ; 
     
     /**
      * 资产下发后修改资产状态
      * @param Asset 
      * @retrun void [类、类#方法、类#成员]
      */
     public void updateIssued(Asset a) ; 
     
     /**
      * 修改资产的关联规则
      * @param map
      * @return void
      */
     public void updateAnalysisRules(Map map) ;
     
     /**
      * 根据支持的设备查找资产
      * @param map
      * @return list
      */
     public List<Asset> queryByAssetSupportDevice(Map map) ; 

     
     /**
      * 根据用户关联的资产组，查询到资产id串
      * @param map
      * @return String
      */
     public String queryIDSByUser(long id) ;
     
     /**
      * 根据用户关联的资产组，查询到资产关联的采集器
      * @param map
      * @return String
      */
     public String queryCollectorByUser(long id);
     public void updateAssetVavulNerabilityValue(long assetId, Map map);
     
     public Asset queryAssetByIp(long ip);
     
     /**
 	 * 根据父级的设别种类，查询子集的字符串，并以"|"拼接返回
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
