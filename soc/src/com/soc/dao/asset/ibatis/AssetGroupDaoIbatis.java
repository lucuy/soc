package com.soc.dao.asset.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetGroupDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.AssetGroup;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 *资产组Dao实现类
 *资产组信息的查看，编辑，删除，添加同级资产组，下级资产组
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  
 * @since  [任务管理/资产组管理/V100R001C001]
 */
public class AssetGroupDaoIbatis extends BaseDaoIbatis implements AssetGroupDao
{

    /**
     *   {@inheritDoc}
     */
    public int count(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"assetGroup.count", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }

    /**
     *   {@inheritDoc}
     */
    public List<AssetGroup> query(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.query", map, startRow, pageSize);
    }

    /**
     *   {@inheritDoc}
     */
    public List<AssetGroup> queryByParentId(Map map)
    {
       
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryByParentId",map);
    }

    /**
     *   {@inheritDoc}
     */
    public AssetGroup queryById(long id)
    {
        
        return (AssetGroup)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetGroup.queryById", id);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> querySon(String parentsFeature)
    {
        return super.queryForList(GlobalConfig.sqlId+"assetGroup.querySon", parentsFeature);
    }

    /**
     *   {@inheritDoc}
     */
    public void updateAssetGroup(AssetGroup assetGroup)
    {
       
        super.update(GlobalConfig.sqlId+"assetGroup.update",assetGroup);
    }

    /**
     *   {@inheritDoc}
     */
    public int insertAssetGroup(AssetGroup assetGroup)
    {
      return  Integer.parseInt(this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"assetGroup.insert",assetGroup).toString());
    }

    /**
     *   {@inheritDoc}
     */
    public void deleteAssetGroup(long id)
    {
       
        super.update(GlobalConfig.sqlId+"assetGroup.delete", id);
        
    }

	@Override
	public List<AssetGroup> query() {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.query");
	}

	@Override
	public List<AssetGroup> queryByuserid(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryByUserId", map);
	}

	
	public List<AssetGroup> queryParentByUserId(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryParentsByUserId", map);
	}

	@Override
	public List<AssetGroup> queryChildren(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryChildrenByParentId", map);
	}

	@Override
	public Object queryParentIdBySonId(Long id) {
		return this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetGroup.queryParentIdBySonId", id);
	}

	@Override
	public void linkedAssetAssetGroup(Map map) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"assetGroup.asset.linked",map) ;
	}

	@Override
	public void delLinedAssetGroup(long id) {
		
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"assetGroup.delAssetGroupLinked", id);
	}

	@Override
	public List<AssetGroup> queryAllFatherGroup() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryFatherGroup");
	}

	@Override
	public List<AssetGroup> queryAllGroupById(Map map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryFatherGroupAndSon",map);
	}

	@Override
	public List<AssetGroup> queryGroupByAssetId(Map map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.queryGroupByAssetId",map);
	}

	@Override
	public void delAssetLinked(long id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"assetGroup.delAssetLinked",id);
	}

	@Override
	public void delAssetLinkedPro(Map map) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"assetGroup.delAssetLinkedPro",map);
	}

	@Override
	public AssetGroup queryByAssetGroupName(String assetGroupName) {
		return (AssetGroup)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetGroup.queryByAssetGroupName", assetGroupName);
	}
	
	

}
