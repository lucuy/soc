package com.soc.service.alert;

import java.util.List;
import java.util.Map;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <告警信息Service类>
 * <实现告警信息的展示，快速搜索，高级搜索>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AlertMessageSerive {
    /**
     * <符合条件的记录数>
     * <根据条件查询符合条件的记录数>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	public int count(Map map);
	
	/**
	 * <查询符合条件的记录>
	 * <根据条件查询符合条件的记录>
	 * @param map
	 * @param page
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SearchResult alertQuery(Map map, Page page);
	/**
	 * <导出doc>
	 * <根据条件导出，默认导出所有>
	 * @param map
	 * @param page
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public  List<AlertMessage> alertQueryDoc(Map map);
	
	/**
	 * <插入一条信息>
	 * <根据信息插入一条记录>
	 * @param alertMessage
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
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<AlertMessage> queryCurrentEvents(long timestamp);
	
	
	/**
	 * <查看最近事件内的最新的8条告警信息>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<AlertMessage> queryCurrentMessage(Map map);
	
	
	/**
	 * <查看最近事件内的按照等级排序>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Object queryCurrentByGroup(Map map);
	
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
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryAepetitionMessage(Map map);
/**
 * <最近7天 一天 一小时的告警类型分布>
 * <功能详细描述>
 * @param map
 * @return
 * @see [类、类#方法、类#成员]
 */
	public String lastTimeDaysAlarmTypeDistribution(Map map);
	/**
	 * <最近7天 一小时 24小时 告警最多的10个资产>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String lastTimeDaysUpTo10AlarmsAssets(Map map);
	
	/**
	 * 查询所有没有派发工单的告警信息（裴秀梅）
	 */
	public List<AlertMessage> queryAllAlertInfo(Map map);
	
	/**
	 * 如果下级设备把设备换了，则需插入一条告警信息（裴秀梅）
	 */
	public String insertAlertMessageUseTopo(String deviceIP,String assetId,String assetName); 
	
	public  String getMac(String ip,String macObj,String assetId,String displayName,String snmpCommunityName);
	/**
	 * 查询下级设备产生的没有派发工单的告警数量 (裴秀梅)
	 */
	public int queryCountAlert();
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
