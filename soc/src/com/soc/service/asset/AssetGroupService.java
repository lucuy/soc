package com.soc.service.asset;

import java.io.Serializable;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.systemsetting.Collector;
import com.util.page.Page;
import com.util.page.SearchResult;
import java.util.List;

/**
 * 
 * 资产组业务类
 * 资产组信息的查看，编辑，删除，添加同级资产组，下级资产组
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  com.soc.dao.asset.AssetGroupDao
 * @since  [任务管理/资产组管理/V100R001C001]
 */
public interface AssetGroupService extends Serializable
{
    /**
     * 查询符合条件的资产组数
     * 
     * @param map Map
     * @return int
     * @see com.soc.dao.asset.AssetGroupDao#count(Map)
     */
    public int count(Map map);
    
    /**
     * 查询符合条件的记录列表
     * 对列表进行分页查询
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.asset.AssetGroupDao#query(Map, int, int)
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * 根据父节点查询列表
     * 查询父节点为传入值的所有组的列表
     * @param map Map
     * @return List<AssetGroup>
     * @see com.soc.dao.asset.AssetGroupDao#queryByParentId(Map)
     */
    public List<AssetGroup> queryByParentId(Map map);
    
    /**
     * 根据id查询资产组
     * 根据传入的id查询资产组
     * @param id long
     * @return AssetGroup
     * @see com.soc.dao.asset.AssetGroupDao#queryById(long)
     */
    public AssetGroup queryById(long id);
    
    /**
     * 按照资产组的id查询子资产组
     * 按照资产组的id查询子资产组
     * @param id long
     * @return AssetGroup
     * @see [类、类#方法、类#成员]
     */
    public List<Map> querySon(String parentsFeature);
    
    /**
     * 更新资产组
     * 资产组的更新，新建
     * @param assetGroup AssetGroup
     * @see com.soc.dao.asset.AssetGroupDao#updateAssetGroup(AssetGroup)
     */
    public int updateAssetGroup(AssetGroup assetGroup);
    
    /**
     * 选择新建并判断为何种类型
     * 
     * @param type String
     * @param groupId int
     * @return AssetGroup
     * @see com.soc.dao.asset.AssetGroupDao#queryById(long)
     */
    public AssetGroup insertAssetGroup(String type,int groupId);
    
    /**
     * 标记删除资产组
     * 删除资产组以及所关联的下级资产组
     * @param id long
     * @see com.soc.dao.asset.AssetGroupDao#deleteAssetGroup(long)
     */
    public void deleteAssetGroup(long id);

    
    /**
     * 根据条件画出树形结构
     * <功能详细描述>
     * @param path
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getTreeStyle(String path);
    /**
     * 根据条件画出树形结构
     * <功能详细描述>
     * @param path
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String   getTreeStyle(String path, long userid);

    /**
     * 查询所有资产组 

     * @return List<AssetGroup> [类、类#方法、类#成员]
     */
    public List<AssetGroup> query();
    /**
     * 根据用户id查询所有资产组 
     * @param map 
     * @return List<AssetGroup> [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryByuserid(Map map);
    /**
     * 向表中插入资产和资产组的关联信息
     * @param map 
     * @return List<AssetGroup> [类、类#方法、类#成员]
     */
    public void linkedAssetAssetGroup(Map map);
    /**
     * 删除关联表中的信息
     * @param map 
     * @return void [类、类#方法、类#成员]
     */
    public void delLikedAssetGroup(long id);
    /**
     * 根据Id查询租下的所有组的ID，返回String字符串
     * @param long 
     * @return void [类、类#方法、类#成员]
     */
    public String getAllGroupIdByID(long groupId) ; 
    /**
     * 根据资产Id查询对应的资产组
     * @param map
     * @return List
     */
    public List<AssetGroup> queryGroupByAssetId(Map map) ;
    /**
     * 删除资产时，删除中间表的信息
     * @param long
     * @return void
     */
    public void delAssetLinked(long id) ;
    
    /**
     * 根据用户Id查找对应的用户资产list
     * @param long
     * @return List<Asset>
     */
    public List<Asset> getAllAsset(long groupId,String groupIdStr,Page page) ; 
    
    /**
     * 根据groupId查询到资产组的上一级的所有组
     * @param long
     * @return List<AssetGroup>
     */
    public List<AssetGroup> queryAllFatherGroupId(long groupId) ; 
    
    /**
     * 重置assetGroupFather List
     * @param null
     * @return null
     */
    public void setAssetGroupFather() ; 
    
    /**
     * 删除特定的资产与资产组的对应
     * @param map
     * @return void
     */
    public void delAssetLinkedPro(Map map) ; 
    
    /**
     * 根据名称查询资产组
     * 根据传入的名称查询资产组
     * @param String assetGroupName
     * @return AssetGroup
     * @see com.soc.dao.asset.AssetGroupDao#queryByAssetGroupName(assetGroupName)
     */
    public AssetGroup queryByAssetGroupName(String assetGroupName);
    
}
