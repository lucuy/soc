package com.soc.dao.asset.ibatis.risk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.risk.AssetRiskDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetRiskGroup;
import com.soc.model.asset.AssetRiskValue;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.AnalysisRules;

public class AssetRiskDaoIbatis extends BaseDaoIbatis implements AssetRiskDao
{

    @Override
    public AssetRiskGroup queryAssetRiskGroup(String groupName)
    {
        return (AssetRiskGroup)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.riskGroupByName", groupName);
    }

    @Override
    public void addAssetRiskValue(AssetRiskValue assetRiskValue)
    {
        getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.riskValue", assetRiskValue);
    }

    @Override
    public List<AssetRiskValue> queryAssetRiskValuebyname(String groupName)
    {
        return (List<AssetRiskValue>)getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"query.RiskValuebyname", groupName);
    }

    @Override
    public List<AssetRiskValue> queryAssetRiskValue()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.RiskValue");
    }
    /**
     * {@分页}
     */
    @Override
    public int associationPage(Map<String, Object> map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPage.RiskValue", map);
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
    public List<Map<String, Object>> queryPage(Map<String, Object> map, int startIndex, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.RiskValue",map,startIndex,pageSize);
    }

    @Override
    public void deleteRisk(int parseInt)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("riskId", parseInt);
        super.update(GlobalConfig.sqlId+"delete.RiskValue", map);
    }

	@Override
	public List<AssetRiskValue> sort(String str, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"riskValue.sort",str,startRow,pageSize);
	}

	@Override
	public AssetRiskValue queryAssetRiskValue(long id) {
		return (AssetRiskValue) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.riskValueId",id);
	}

	@Override
	public void updateAssetRiskValue(Map map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"update.riskValue",map);
	}

	@Override
	public List<Map> ReportExcel(Map map) {
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"riskSQL.export",map);
		
	}

	@Override
	public List<Map> ReportExcelMould(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"riskSQL.exportMould",map);
	}

	@Override
	public Asset RiskQueryAssetByName(String str) {
		
		return  (Asset) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryByName",str);
	}
    
}