package com.soc.dao.alert;

import java.util.List;
import java.util.Map;

import com.soc.model.alert.alertMessage.AlertMessage;


/**
 * 
 * <告警信息Dao>
 * <实现告警信息的查看，搜索，快速搜索功能>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AlertMessageDao {
  
	/**
     * <查询符合条件的记录数>
     * <查询符合条件的告警信息条数>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	public int count(Map map);
	
	/**
	 * <查询告警信息列表>
	 * <根据条件查询告警信息>
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public List<AlertMessage> queryAlertMessage(Map map, int startRow, int pageSize);
    /**
	 * <导出doc>
	 * <根据条件导出，默认导出所有>
	 * @param map
	 * @
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public List<AlertMessage> queryAlertMessageDoc(Map map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertAlertMessage(AlertMessage alertMessage);
    
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param timestamp
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int queryCurrent(Map map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param timestamp
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AlertMessage> queryCurrentEvents(long timestamp);
    
    /**
     * <查询近期内的告警信息top8>
     * <根据传入的条件查询符合条件的告警列表>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AlertMessage> queryCurrentMessage(Map map);
    
    
    /**
     * <按照等级分组查询近期的告警>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCurrentMessageByGroup(Map map);
    
    /**
     * <查询近期事件的按照资产纬度统计>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryCurrentMessageByAsset(Map map);

	public List<Map> quertAlertRuleByID(Map mapRule);
    
    public List<Map> exportExcel(Map map);
    
    /**
     * <修改日期和工单的状态>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateAlertMassage(Map map);

    /**
     * <根据id查询一条告警，派发工单发邮件时候用>
     * <功能详细描述>
     * @param map
     * @return AlertMessage
     * @see [类、类#方法、类#成员]
     */
    public AlertMessage queryById(Map map);
    
    /**
     * <根据资产id的字符串查询告警，派发工单发邮件时候工单权限控制用>
     * <功能详细描述>
     * @param map
     * @return List<AlertMessage>
     * @see [类、类#方法、类#成员]
     */
    public List<AlertMessage> queryByAssetId(Map map);
    /**
     * <根据根据传入的事件查询事件范围内重复最多的10个告警>
     * <功能详细描述>
     * @param time long
     * @return List<map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryAepetitionMessage(Map map);
    
    public long last7DaysAlarmTypeDistributionCount(Map map);
    
/**
 * <最近7天的告警类型分布>
 * <功能详细描述>
 * @param map
 * @return
 * @see [类、类#方法、类#成员]
 */
	public List<Map<String, Object>> last7DaysAlarmTypeDistribution(Map map);
	/**
	 * 查询所有没有派发工单的告警信息（裴秀梅）
	 */
	public List<AlertMessage> queryAllAlertInfo(Map map);
	
	/**
	 * 查询下级设备产生的没有派发工单的告警数量 (裴秀梅)
	 */
	public int queryCountAlert();
	
	/**
	 * 根据事件类型查询告警信息
	 */
	public List<AlertMessage> queryAlertMessageByEventName(Map map);
	/**
	 * 关闭告警信息
	 */
	public void closeAlertMessageById(Map map);

	/**
	 * <查询未关闭告警信息列表>
	 * <根据条件查询告警信息>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public List<AlertMessage> queryNotCloseAlertMessage(Map map);
}
