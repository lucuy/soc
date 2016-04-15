package com.soc.dao.alert;

import java.util.List;
import java.util.Map;

import com.soc.model.alert.AlertInteractionTable;
import com.soc.model.alert.AlertRule;
import com.soc.model.user.User;

/**
 * 
 * 告警规则管理Dao
 * 实现告警规则的增删改查
 * 
 * @author  jiadongxu
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AlertRuleDao
{
    
    /**
     * <返回规则记录数>
     * <根据条件返回规则数>
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <分页查询规则信息>
     * <根据条件查询规则信息，分页展示>
     * @param map Map
     * @param startRow int 
     * @param pageSize int
     * @return List<AlertRule>
     * @see [类、类#方法、类#成员]
     */
    public List<AlertRule> queryRule(Map map, int startRow, int pageSize);
    
    /**
     * 根据规则ID查询规则
     * 
     * @param ruleId long
     * @return 返回对应ID的规则
     */
    public AlertRule queryByRuleId(long ruleId);
    
    /**
     * 根据规则ID查询用户
     * @param ruleId long
     * @return 返回对应ID的用户
     */
    public List<User> queryUserByRuleId(long ruleId);
    
    /**
     * 根据条件更新规则
     * @param rule AlertRule
     * @return 
     */
    public void updateRule(AlertRule rule);
    
    /**
     * 根据条件更新规则
     * @param String
     * @return 
     */
    public void updateRuleScpriptByScpriptName(String scpriptName);
    
    /**
     * 根据条件插入规则
     * @param rule AlertRule
     * @return long
     */
    public long insertRule(AlertRule rule);
    
    /**
     * 根据ruleid删除规则
     * @param ruleId long
     * @return 
     *
     */
    public void deleteRule(long ruleId);
    
    /**
     * 根据ruleId删除相关用户
     * @param ruleId long
     * @return 
     * 
     */
    public void deleteRelUser(long ruleId);
    
    /**
     * 根据ruleId删除相关资产
     * @param ruleId long
     * @return 
     * 
     */
   // public void deleteRelAsset(long ruleId);
    
    /**
     * 根据ruleId删除相关等级
     * @param ruleId long
     * @return 
     * @see 
     */
    public void deleteRank(long ruleId);
    
    /**
     * 根据条件插入相关用户
     * @param map Map
     * @return 
     * @see 
     */
    public void insertRelUser(Map map);
    
    /**
     * 根据条件插入相关资产
     * @param map Map
     * @return 
     * @see 
     */
   // public void insertRelAsset(Map map);
    
    /**
     * 根据ruleId查询相关资产
     * @param ruleId long
     * @return List<map>
     * @see 
     */
   // public List<Map> queryRelAsset(long ruleId);
    
    /**
     * 根据ruleId查询相关用户
     * @param ruleId long
     * @return List<map>
     * @see 
     */
    public List<Map> queryRelUser(long ruleId);
    
    /**
     * 根据ruleId查询相关用户
     * @param ruleId long
     * @return List<map>
     * @see 
     */
    //public List<Map> queryRelAssetByRuleId(long ruleId);
    
    /**
     * 根据ruleId查询相关用户
     * @param ruleId long
     * @return List<map>
     * @see 
     */
    public List<Map> queryRelUserByRuleId(long ruleId);
    
    /**
     * 根据条件更新等级
     * @param map Map
     * @return 
     * @see 
     */
    public void updateRank(Map map);
    
    /**
     * 根据条件查询等级
     * @param map Map
     * @return List<map>
     * @see 
     */
    public List<Map> queryRank(Map map);
    
    /**
     * 根据级别查询规则
     * @param levelStr
     * @return 
     */
    public List<AlertRule> queryRankByLevel(Map<String, String> levelStrs);
    
    /**
     * 根据告警规则ID串查询用户ID
     * @param rankIds
     * @return 
     */
    public List<Map<String ,Object>> queryUserIdByRankId(long rankIds);
    /**
     * 设备类型
     * @return
     */
    public List<Map<String ,Object>> queryDeviceType();
    /**
     * 设备名称
     * @return
     */
    public List<Map<String ,Object>> queryDeviceByName();
    /**
     * 事件类型
     * @return
     */
    public List<Map<String ,Object>> queryAssetType();
    //查询的
	public AlertInteractionTable queryDeviceTypeByIdAll(long ruleId);
	//查各个表
	public List<Map<String, Object>> queryAssetTypeById(String deviceCategoryId);

	public List<Map<String, Object>> queryDeviceByNameById(String assetId);

	public List<Map<String, Object>> queryDeviceTypeById(String definitionId);

	public List<Map<String, Object>> queryUserById(String string);
	
	public List<Map<String, Object>> queryEventType(String string);
	
	public List<Map<String, Object>> queryAllEventType();
	
	public List<AlertRule> queryAlertRuleByAssetIdAndLevel(Map<String, Object> map) ; 

}
