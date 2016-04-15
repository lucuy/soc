package com.soc.dao.risk.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.risk.AssetRiskGroupDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.risk.VulnerabilityAssessment;

public class AssetRiskGroupDaoIbatis extends BaseDaoIbatis implements AssetRiskGroupDao{

	@Override
	public int assetRiskGroupPagePage(Map map) {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPage.assetRiskGroup", map);
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

	@Override
	public List<AssetGroup> queryPage(Map map, int startIndex,int pageSize) {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetRiskGroup.query",map,startIndex,pageSize);
        }

	@Override
	public String queryByName(Map map) {
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetNameByValue.query",map);
	}

	@Override
	public String queryByName1(Map map) {
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetNameByValue1.query",map);
	}

	@Override
	public List<AssetGroup> queryGroupName() {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryByGroupName2.query");
	}

	@Override
	public void updateGrouprisk(AssetGroup assetgroup) {
		super.update(GlobalConfig.sqlId+"riskValue.update", assetgroup);
	}

	@Override
	public String queryByName2(Map map) {
		
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetNameByValue2.query",map);
	}
	/**
	 * 查子
	 */
	@Override
	public int assetRiskGroupPagePage1(Map map) {
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

	@Override
	public List<Asset> queryPage1(Map map, int startIndex, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.query",map,startIndex,pageSize);
	}

	@Override
	public List<Map> export(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetGroup.export", map);
	}

	@Override
	public List<Map> queryAssetsNum(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"risk.queryAssets",map);
	}

	@Override
	public int count(Map map) {
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

	@Override
	public double avgRiskValue(long id) {
		
		 Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的用户的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"asset.queryRiskvalue", id);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        //总条数
	        double riskValue = 0.0;
	        
	        if (ob != null)
	        {
	        	riskValue = (Double)ob;
	        }
	        
	        return riskValue;
		
		
		
		
		}

	@Override
	public double avgVAValue(String str) {
		 Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的用户的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"asset.queryVAvalue", str);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        //总条数
	        double vaValue = 0.0;
	        
	        if (ob != null)
	        {
	        	vaValue = (Double)ob;
	        }
		return vaValue;
	}
	@Override
	public void updateAssetValue(Asset asset){
		super.update(GlobalConfig.sqlId+"asset.updateValue", asset);
	}

	@Override
	public double AssetValue(long id) {
		Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"asset.queryAssetvalue", id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //总条数
        double AssetValue = 0.0;
        
        if (ob != null)
        {
        	AssetValue = (Double)ob;
        }
		return AssetValue;
	}

	@Override
	public List<Map> queryRiskAssetGroup(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"risk.queryRiskAssetGroup",map);
	}

	@Override
	public List<Map> queryRiskAssets(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"risk.queryRiskAssets", map);
	}
}
