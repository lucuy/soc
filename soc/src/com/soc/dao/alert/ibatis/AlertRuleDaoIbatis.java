package com.soc.dao.alert.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.alert.AlertRuleDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.alert.AlertInteractionTable;
import com.soc.model.alert.AlertRule;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;

/**
 * 告警规则Dao实现类
 * 实现告警规则增加，删除，查询，快速查询，高级查询
 * 
 * @author  jiadongxu
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AlertRuleDaoIbatis extends BaseDaoIbatis implements AlertRuleDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<AlertRule> queryRule(Map map, int startRow, int pageSize)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rule.query", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        
        Object ob = null;
        
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"rule.count", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    
    /**
     * {@inheritDoc}
     */
    public AlertRule queryByRuleId(long ruleId)
    {
        
        return (AlertRule)super.queryForObject(GlobalConfig.sqlId+"rule.queryById", ruleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> queryUserByRuleId(long ruleId)
    {
        
//        return this.getSqlMapClientTemplate().queryForList("rule.queryUserByRuleId", ruleId);
        return null;
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateRule(AlertRule rule)
    {
        super.update(GlobalConfig.sqlId+"rule.update", rule);
    }
    
    /**
     * {@inheritDoc}
     */
    public long insertRule(AlertRule rule)
    {
        long ruleId = 0;
        Object ruleObject = super.create(GlobalConfig.sqlId+"rule.insert", rule);
        if (ruleObject != null)
        {
            ruleId = Long.parseLong(ruleObject.toString());
        }
        return ruleId;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRule(long ruleId)
    {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("ruleId", ruleId);
        map.put("ruleIdIsDelete", Long.valueOf(GlobalConfig.IS_DELETE));
        super.update(GlobalConfig.sqlId+"rule.updateRuleIsDelete", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelUser(long ruleId)
    {
        super.delete(GlobalConfig.sqlId+"rule.delete-relUser", ruleId);
    }
    
    /**
     * {@inheritDoc}
     */
    /*public void deleteRelAsset(long ruleId)
    {
        
        super.delete("rule.delete-relAsset", ruleId);
    }*/
    
    /**
     * {@inheritDoc}
     */
    public void deleteRank(long ruleId)
    {
        
        super.delete(GlobalConfig.sqlId+"rule.delete-relRank", ruleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelUser(Map map)
    {
        super.create(GlobalConfig.sqlId+"rule.insert-relUser", map);
    }
    
    /**
     * {@inheritDoc}
     */
    /*public void insertRelAsset(Map map)
    {
        super.create("rule.insert-relAsset", map);
    }*/
    
    /**
     * {@inheritDoc}
     */
    /*public List<Map> queryRelAsset(long ruleId)
    {
        return super.queryForList("rule.query-relAsset", ruleId);
    }*/
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUser(long ruleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"rule.query-relUser", ruleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUserByRuleId(long ruleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"rule.queryRelUserByRuleId", ruleId);
    }
    
    /**
     * {@inheritDoc}
     */
    /*public List<Map> queryRelAssetByRuleId(long ruleId)
    {
        return super.queryForList("rule.queryRelAssetByRuleId", ruleId);
    }*/
    
    /**
     * {@inheritDoc}
     */
    public void updateRank(Map map)
    {
        
        super.create(GlobalConfig.sqlId+"rule.insertRan", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRank(Map map)
    {
        
        return super.queryForList(GlobalConfig.sqlId+"rule.queryRank", map);
    }

    @Override
    public List<AlertRule> queryRankByLevel(Map<String, String> levelStrs)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.alarmRuleRank", levelStrs);
    }

    @Override
    public List<Map<String, Object>> queryUserIdByRankId(long rankId)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.userIdByRankId", rankId);
    }
    /**
     * 设备类型
     */
	@Override
	public List<Map<String, Object>> queryDeviceType() {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.deviceType");
	}
	/**
	 * 设备名称
	 */
	@Override
	public List<Map<String, Object>> queryDeviceByName() {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.deviceByName");
	}

	@Override
	public List<Map<String, Object>> queryAssetType() {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.AssetType");
	}

	@Override
	public AlertInteractionTable queryDeviceTypeByIdAll(long ruleId) {
		return (AlertInteractionTable)super.queryForObject(GlobalConfig.sqlId+"query.deviceTypeById", ruleId);
		/*return (AlertInteractionTable) this.getSqlMapClientTemplate().queryForList("query.deviceTypeById",ruleId);*/
	}

	@Override
	public List<Map<String, Object>> queryAssetTypeById(String deviceCategoryId) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.AssetType1",Long.valueOf(deviceCategoryId));
	}

	@Override
	public List<Map<String, Object>> queryDeviceByNameById(String assetId) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.deviceByName1",Long.valueOf(assetId));
	}

	@Override
	public List<Map<String, Object>> queryDeviceTypeById(String definitionId) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.deviceType1",Long.valueOf(definitionId));
	}
	@Override
	public List<Map<String ,Object>> queryUserById(String string) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"ruleQueryUserIdAndName.query",Long.valueOf(string));
	}
	
	@Override
    public List<Map<String, Object>> queryEventType(String string) {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"ruleQueryEventTypeIdAndName.query",string);
    }

    @Override
    public List<Map<String, Object>> queryAllEventType()
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"ruleQueryAllEventTypeIdAndName.query");
    }

	@Override
	public List<AlertRule> queryAlertRuleByAssetIdAndLevel(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.alarmRuleRankByAssetIdAndLevel",map);
	}

	@Override
	public void updateRuleScpriptByScpriptName(String scpriptName) {
		super.update(GlobalConfig.sqlId+"ruleScript.update", scpriptName);
		
	}
}
