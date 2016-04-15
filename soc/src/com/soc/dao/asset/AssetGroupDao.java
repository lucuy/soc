package com.soc.dao.asset;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.asset.AssetGroup;

/**
 * 
 *资产组管理类
 *资产组信息的查看，编辑，删除，添加同级资产组，下级资产组
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  
 * @since  [任务管理/资产组管理/V100R001C001]
 */
public interface AssetGroupDao extends BaseDao
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
     * @return 返回符合查询条件的用户结果列表List<Asset>
     */
    public List<AssetGroup> query(Map map, int startRow, int pageSize);
    
    /**
     * 按照父资产组Id查询
     * 根据传入的父资产组Id查询该资产组的
     * @param map Map
     * @return List<AssetGroup>
     * @see [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryByParentId(Map map);
    /**
     * 按照用户Id查询所关联的顶级父类
     * 根据传入的父资产组Id查询该资产组的
     * @param map Map
     * @return List<AssetGroup>
     * @see [类、类#方法、类#成员]
     */
  //  public List<AssetGroup> queryParentByUserId(Map map);
    
    /**
     * 按照资产组的id查询资产组
     * 按照传入的资产组Id查询资产组
     * @param id long
     * @return AssetGroup
     * @see [类、类#方法、类#成员]
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
     * 更改资产组的信息
     * @param assetGroup AssetGroup
     * @see [类、类#方法、类#成员]
     */
    public void updateAssetGroup(AssetGroup assetGroup);
    
    /**
     * 插入资产组
     * 按照资产组的信息插入资产组
     * @param assetGroup AssetGroup
     * @see [类、类#方法、类#成员]
     */
    public int insertAssetGroup(AssetGroup assetGroup);
    
    /**
     * 标记删除资产组
     * 按照传入的id,标记删除本资产组以及关联的下级资产组
     * @param id long
     * @see [类、类#方法、类#成员]
     */
    public void deleteAssetGroup(long id);
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
     * 按照父资产组Id,和用户id查询
     * 根据传入的父资产组Id查询该资产组的
     * @param map Map
     * @return List<AssetGroup>
     * @see [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryChildren(Map map);
    
    public Object queryParentIdBySonId(Long id);
    
    /**
     * 想关联表中加入对应信息
     * @param void
     * @return List<AssetGroup>
     * @see [类、类#方法、类#成员]
     */
    public void linkedAssetAssetGroup(Map map);
    
    /**
     * 删除关联表中的信息
     * @param void
     * @see [类、类#方法、类#成员]
     */
    public void delLinedAssetGroup(long id);
    
    /**
     * 查询所有出"未分组"和"全部资产"外的所有父类
     * @param List<AssetGroup>
     * @see [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryAllFatherGroup();
    
    /**
     * 查询父组和子组所有
     * @param Map
     * @see [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryAllGroupById(Map map);
    /**
     * 根据资产查询对应的资产组
     * @param Map
     * @see [类、类#方法、类#成员]
     */
    public List<AssetGroup> queryGroupByAssetId(Map map) ;
    /**
     * 删除资产时，删除关联表中的信息
     * @param long
     * @see [类、类#方法、类#成员]
     */
    public void delAssetLinked(long id) ; 
    
    /**
     * 按照确定条件删除中间表信息
     * @param map
     */
    public void delAssetLinkedPro(Map map) ; 
    

    /**
     * 根据名称查询资产组
     * 根据传入的名称查询资产组
     * @param String assetGroupName
     * @return AssetGroup
     */
    public AssetGroup queryByAssetGroupName(String assetGroupName);
} 
