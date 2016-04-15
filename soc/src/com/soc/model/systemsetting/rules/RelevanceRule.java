package com.soc.model.systemsetting.rules;

import java.util.Date;

/**
 * 
 * <自定义关联规则实体类>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-3-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RelevanceRule {
	/**
	 * 
	 */
	// id
	private long relevanceRuleId;

	// 名称
	private String relevanceRuleName;

	// 创建者
	private String relevanceRuleCreater;

	// 创建时间
	private Date relevanceRuleCreateDate;

	// 修改者
	private String relevanceRuleMender;

	// 修改时间
	private Date relevanceRuleMenderDate;

	// 描述
	private String relevanceRuleDes;

	// 条件字符串
	private String relevanceRuleCondition;

	// 状态
	private Integer relevanceRuleType;
	//设备(资产ID)
	private String assetIds;
	//事件类型id 字符串
	private String eventsTypeIds;
	//关联后事件类型id 
	private int eventsTypeIdsRe;
	//事件类别
	private String eventsCategoryIds;
	//关联后事件类别
	private int eventsCategoryIdsRe;
	//源ip地址
	private String sourceAddr;
	//目标ip地址
	private String targetAddr;
	//源端口
	private String sourcePort;
	//目标端口
	private String targetPort;
	//等级字符串 1,1,1,1,1
	private String priorityIds;
	//关联后的事件名称
	private String eventsName;
	//关联后的事件等级
	private int level;
	//多长时间内
	private int time;
	//发成多少次
	private int count;
	//所属组的id
	private int groupId;
	//放关联的下一级的id
	private String resultIds;
	//是否是初始状态
	private int initState; //是否是初始状态1 是  0  不是
	public long getRelevanceRuleId() {
		return relevanceRuleId;
	}
	public void setRelevanceRuleId(long relevanceRuleId) {
		this.relevanceRuleId = relevanceRuleId;
	}
	public String getRelevanceRuleName() {
		return relevanceRuleName;
	}
	public void setRelevanceRuleName(String relevanceRuleName) {
		this.relevanceRuleName = relevanceRuleName;
	}
	public String getRelevanceRuleCreater() {
		return relevanceRuleCreater;
	}
	public void setRelevanceRuleCreater(String relevanceRuleCreater) {
		this.relevanceRuleCreater = relevanceRuleCreater;
	}
	public Date getRelevanceRuleCreateDate() {
		return relevanceRuleCreateDate;
	}
	public void setRelevanceRuleCreateDate(Date relevanceRuleCreateDate) {
		this.relevanceRuleCreateDate = relevanceRuleCreateDate;
	}
	public String getRelevanceRuleMender() {
		return relevanceRuleMender;
	}
	public void setRelevanceRuleMender(String relevanceRuleMender) {
		this.relevanceRuleMender = relevanceRuleMender;
	}
	public Date getRelevanceRuleMenderDate() {
		return relevanceRuleMenderDate;
	}
	public void setRelevanceRuleMenderDate(Date relevanceRuleMenderDate) {
		this.relevanceRuleMenderDate = relevanceRuleMenderDate;
	}
	public String getRelevanceRuleDes() {
		return relevanceRuleDes;
	}
	public void setRelevanceRuleDes(String relevanceRuleDes) {
		this.relevanceRuleDes = relevanceRuleDes;
	}
	public String getRelevanceRuleCondition() {
		return relevanceRuleCondition;
	}
	public void setRelevanceRuleCondition(String relevanceRuleCondition) {
		this.relevanceRuleCondition = relevanceRuleCondition;
	}
	public Integer getRelevanceRuleType() {
		return relevanceRuleType;
	}
	public void setRelevanceRuleType(Integer relevanceRuleType) {
		this.relevanceRuleType = relevanceRuleType;
	}
	public String getAssetIds() {
		return assetIds;
	}
	public void setAssetIds(String assetIds) {
		this.assetIds = assetIds;
	}
	public String getEventsTypeIds() {
		return eventsTypeIds;
	}
	public void setEventsTypeIds(String eventsTypeIds) {
		this.eventsTypeIds = eventsTypeIds;
	}
	public String getEventsCategoryIds() {
		return eventsCategoryIds;
	}
	public void setEventsCategoryIds(String eventsCategoryIds) {
		this.eventsCategoryIds = eventsCategoryIds;
	}
	public String getSourceAddr() {
		return sourceAddr;
	}
	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}
	public String getTargetAddr() {
		return targetAddr;
	}
	public void setTargetAddr(String targetAddr) {
		this.targetAddr = targetAddr;
	}
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getTargetPort() {
		return targetPort;
	}
	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
	}
	public String getPriorityIds() {
		return priorityIds;
	}
	public void setPriorityIds(String priorityIds) {
		this.priorityIds = priorityIds;
	}
	public String getEventsName() {
		return eventsName;
	}
	public void setEventsName(String eventsName) {
		this.eventsName = eventsName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getEventsTypeIdsRe() {
		return eventsTypeIdsRe;
	}
	public void setEventsTypeIdsRe(int eventsTypeIdsRe) {
		this.eventsTypeIdsRe = eventsTypeIdsRe;
	}
	public int getEventsCategoryIdsRe() {
		return eventsCategoryIdsRe;
	}
	public void setEventsCategoryIdsRe(int eventsCategoryIdsRe) {
		this.eventsCategoryIdsRe = eventsCategoryIdsRe;
	}
	public String getResultIds() {
		return resultIds;
	}
	public void setResultIds(String resultIds) {
		this.resultIds = resultIds;
	}
	public int getInitState() {
		return initState;
	}
	public void setInitState(int initState) {
		this.initState = initState;
	}
}
