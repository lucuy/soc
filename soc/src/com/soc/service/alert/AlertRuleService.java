package com.soc.service.alert;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.alert.AlertInteractionTable;
import com.soc.model.alert.AlertRule;
import com.soc.model.user.User;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * 告警规则业务类
 * 告警规则信息的查看，编辑，删除
 * 
 * @author  jiadongxu
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AlertRuleService extends Serializable
{
    
    /**
     * 返回 规则记录数
     * 根据条件返回规则记录数
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    
    public int count(Map map);
    
    /**
     * <按分页查询规则> 
     * <根据条件查询规则，分页展示>
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see [类、类#方法、类#成员]
     */
    public SearchResult ruleQuery(Map map, Page page);
    
    /**
     * 根据规则ID查询规则
     * 
     * @param ruleId long
     * @return 规则AlertRule
     */
    public AlertRule queryByRuleId(long ruleId);
    
    /**
     * 根据规则ID查询用户信息
     * 
     * @param ruleId long
     * @return 用户User
     */
    public List<User> queryUserByRuleId(long ruleId);
    
    /**
     * 根据更新添加规则
     * @param rule AlertRule
     * @return long
     */
    public long updateRule(AlertRule rule);
    
    /**
     * 根据规则ID删除规则
     * @param ruleId long
     * @return 
     */
    public void deleteRule(long ruleId);
    
    /**
     * 根据规则ID删除规则关联的用户
     * @param ruleId long
     * @return 
     */
    public void deleteRelUser(long ruleId);
    
    /**
     * 根据规则ID删除规则关联的资产
     * @param ruleId long
     * @return 
     */
    //public void deleteRelAsset(long ruleId);
    
    /**
     * 根据规则ID删除规则关联的等级
     * @param ruleId long
     * @return 
     */
    public void deleteRank(long ruleId);
    
    /**
     * 根据条件插入规则关联的用户
     * @param map Map
     * @return 
     */
    public void insertRelUser(Map map);
    
    /**
     * 根据条件插入规则关联的资产
     * @param map Map
     * @return 
     */
  //  public void insertRelAsset(Map map);
    
    /**
     * 根据规则ID条件查询规则关联的资产
     * @param ruleId long
     * @return 资产
     */
  //  public List<Map> queryRelAsset(long ruleId);
    
    /**
     * 根据规则ID条件查询规则关联的用户
     * @param ruleId long
     * @return 用户User
     */
    public List<Map> queryRelUser(long ruleId);
    
    /**
     * 根据规则ID条件查询规则关联的资产
     * @param ruleId long
     * @return 用户
     */
    public List<Map> queryRelUserByRuleId(long ruleId);
    
    /**
     * 根据规则ID条件查询规则关联的资产
     * @param ruleId long
     * @return 资产
     */
   // public List<Map> queryRelAssetByRuleId(long ruleId);
    
    /**
     * 更新
     * @param map Map
     * @return 
     */
    public void updateRank(Map map);
    
    /**
     * 根据等级设置所需等级大小
     * @param rank String
     * @return String
     */
    public String setRank(String rank);
    
    /**
     * 根据条件查询等级
     * @param map Map
     * @return List<Map>
     */
    public List<Map> queryRank(Map map);
    
    /**
     * <事件日志告警>
     * <事件日志根据告警规则告警>
     * @param dataMap
     */
    public void logAlarmHandling(Map<String, Object> dataMap,List<AlertRule> alertRuleList);

	public List<Map<String, Object>> queryDeviceType();

	public List<Map<String, Object>> queryAssetType();

	public List<Map<String, Object>> queryDeviceByName();
	//查询
	public AlertInteractionTable queryDeviceTypeByIdAll(long ruleId);
	//根据id查询

	public List<Map<String, Object>> queryAssetTypeById(String deviceCategoryId);

	public List<Map<String, Object>> queryDeviceByNameById(String assetId);

	public List<Map<String, Object>> queryDeviceTypeById(String definitionId);

	public List<Map<String, Object>> queryUserById(String string);
	
	public List<Map<String, Object>> queryEventType(String string);
	
	public List<Map<String, Object>> queryAllEventType();
	
	/**
	 * 根据Events对象，查询告警规则中是否有对应的（1、资产ID；2、告警等级；）
	 * @param <long> assetId
	 * @param <int> oriPriority 
	 * @return List<AlertRule>
	 */
	public List<AlertRule> queryAlertRule(long assetId , int oriPriority) ; 
	
}
