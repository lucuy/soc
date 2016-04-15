package com.soc.dao.asset.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * 资产Dao实现类
 * 实现资产增加，删除，查询，快速查询，高级查询
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  
 * @since  [任务管理/资产管理/V100R001C001]
 */
public class AssetDaoIbatis extends BaseDaoIbatis implements AssetDao
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
            ob = super.queryForObject(GlobalConfig.sqlId+"asset.count", map);
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
    public List<Asset> query(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.query", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Asset> queryAssetByAssetGroupId(long assetGroupId)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryByAssetGroupId", assetGroupId);
    }
    
    /**
     *   {@inheritDoc}
     */
    public void deleteAsset(Map map)
    {
        // TODO Auto-generated method stub
        super.update(GlobalConfig.sqlId+"asset.delete", map);
        
    }
    
    /**
     *   {@inheritDoc}
     */
    public long insertAsset(Asset asset)
    {
        long assetId = 0;
        Object assetObject = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"asset.insert", asset);
        if (assetObject != null)
        {
            assetId = Long.parseLong(assetObject.toString());
        }
        return assetId;
    }
    
    /**
     *   {@inheritDoc}
     */
    public void updateAsset(Asset asset)
    {
        super.update(GlobalConfig.sqlId+"asset.update", asset);
    }
    
    /**
     *   {@inheritDoc}
     */
    public Asset queryByid(long id)
    {
        // TODO Auto-generated method stub
        return (Asset)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryById", id);
    }
    
    /**
     *   {@inheritDoc}
     */
    public List<Asset> queryByName(String name)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryByName", name);
    }
    
    /**
     *   {@inheritDoc}
     */
    public void deleteGroup(Map map)
    {
        super.update(GlobalConfig.sqlId+"asset.deleteGroup", map);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateGroupName(Map map)
    {
        // TODO Auto-generated method stub
        super.update(GlobalConfig.sqlId+"asset.updateGroupName", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Asset> query(Map map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryEx", map);
    }
    
    @Override
    public List<Map> queryIpTree()
    {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryIpTree.query");
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryCategory()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCategory");
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryCategoryService()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCategoryService");
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryCategorys()
    {
        
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCategorys");
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryCategory(long upsId)
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCategoryByUpsId", upsId);
    }
    
    public String queryDeviceById(long deviceId)
    {
        return (String)super.queryForObject(GlobalConfig.sqlId+"queryDeviceById", deviceId);
    }
    
    public Map<String, Object> queryDevice(long deviceId)
    {
        return (Map<String, Object>)super.queryForObject(GlobalConfig.sqlId+"query.deviceById", deviceId);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<Asset> checkMac(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"check.mac", map);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void updateStatus(Map map)
    {
        // TODO Auto-generated method stub
        super.update(GlobalConfig.sqlId+"asset.updateStatus", map);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<Map> export(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"asset.export", map);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public int selectByGroupId(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"asset.selectByGroupId", map);
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

    /** {@inheritDoc} */
     
    @Override
    public String queryDeviceRules(long id)
    {
        // TODO Auto-generated method stub
        return (String)super.queryForObject(GlobalConfig.sqlId+"query.deviceRulesById", id);
    }

	@Override
	public int queryVaNo(Map map) {
		Object obj = super.queryForObject(GlobalConfig.sqlId+"asset.assetIp",map);
		if (obj==null) {
			return 0;
		}
		return (Integer)obj;
	}

	@Override
	public List<Asset> queryAssetByGroupId(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryAssetByGroupId",map);
	}

	@Override
	public List<Asset> queryAllAsset(Map map,int startRow , int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryAllAssetByGroupId",map,startRow,pageSize);
	}

	@Override
	public void updateIssued(Asset asset) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"asset.updataIssued",asset);
	}

	@Override
	public void updateAnalysisRules(Map map) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"asset.updateAnalysisRules",map) ; 
	}

	@Override
	public void deleteRiskValues(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"asset.deleteRiskValues", id);
	}

	@Override
	public void deleteAssetGroupAssets(long id) {
		
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"asset.deleteAssetGroupAssets", id);
	}

	@Override
	public void deleteAssetValues(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"asset.deleteAssetValues", id);
		
	}

	@Override
	public void deleteRiskVulnerabilityassessment(String ip) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"asset.deleteRiskVulnerabilityassessment", ip);
		
	}
	@Override
	public void updateRiskVulnerabilityassessmentStatus(Map map) {
		super.update(GlobalConfig.sqlId+"update.VAStates", map);
		
	}

	@Override
	public void updateAssetValuesStatus(Map map) {
		super.update(GlobalConfig.sqlId+"update.assetRiskEvaluationStates", map);
		
	}

	@Override
	public void updateRiskValuesStatus(Map map) {
		super.update(GlobalConfig.sqlId+"update.riskValueStates", map);
		
	}

	@Override
	public void updateAssetVavulNerabilityValue(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"asset.updateAssetVavulNerabilityValue",map);
		
	}

	@Override
	public List<Integer> queryAssetIdWorkOrder(Map map2) {
	       return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryAssetIdWorkOrder", map2);
	}

	@Override
	public Asset queryAssetByIp(long ip) {
		// TODO Auto-generated method stub
		return (Asset)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryAssetByIp",ip);
	}

	@Override
	public Asset queryByid1(long id) {
		// TODO Auto-generated method stub
		 return (Asset)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryById1", id);
	}

	@Override
	public String queryCategoryByFatherId(long deviceId) {
		return (String)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"QueryCategoryByFatherId.toString",deviceId);
	}

	@Override
	public String queryAssetIdByCategoryName(Map map) {
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"QueryAssetIdByCategoryName.toString",map);
		if(obj!=null)
		return (String) obj;
		else
			return null;
			
	}
	
}
