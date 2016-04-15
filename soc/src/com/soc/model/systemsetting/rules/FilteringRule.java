package com.soc.model.systemsetting.rules;

import java.util.Date;

/**
 * 
 * <自定义过滤规则实体类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-2-26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FilteringRule {
	/**
	 * 
	 */
	// id
	private long filteringRuleId;

	// 名称
	private String filteringRuleName;

	// 创建者
	private String filteringRuleCreater;

	// 创建时间
	private Date filteringRuleCreateDate;

	// 修改者
	private String filteringRuleMender;

	// 修改时间
	private Date filteringRuleMenderDate;

	// 描述
	private String filteringRuleDes;

	// 条件字符串
	private String filteringRuleCondition;

	// 状态
	private Integer filteringRuleType;
	//保存设备(资产)id的字符串1,1,1,1,1
	private String assetIds;
	//事件类型id 字符串
	private String eventsTypeIds;
	//事件类别
	private String eventsCategoryIds;
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
	//目标地址等于...
	private String targetAddrEqual;
	//目标地址不等于
	private String targetAddrNotEqual;
	//目标地址之间
	private String targetAddrBetween;
	//源地址等于...
	private String sourceAddrEqual;
	//源地址不等于
	private String sourceAddrNotEqual;
	//源地地址之间
	private String sourceAddrBetween;
	//目标端口等于
	private String targetPortEqual;
	//目标端口不等于
	private String targetPortNotEqual;
	//目标端口之间
	private String targetPortBetween;
	//源端口等于
	private String sourcePortEqual;
	//源端口不等于
	private String sourcePortNotEqual;
	//源端口之间
	private String sourcePortBetween;
	public long getFilteringRuleId() {
		return filteringRuleId;
	}

	public void setFilteringRuleId(long filteringRuleId) {
		this.filteringRuleId = filteringRuleId;
	}

	public String getFilteringRuleName() {
		return filteringRuleName;
	}

	public void setFilteringRuleName(String filteringRuleName) {
		this.filteringRuleName = filteringRuleName;
	}

	public String getFilteringRuleCreater() {
		return filteringRuleCreater;
	}

	public void setFilteringRuleCreater(String filteringRuleCreater) {
		this.filteringRuleCreater = filteringRuleCreater;
	}

	public String getFilteringRuleMender() {
		return filteringRuleMender;
	}

	public void setFilteringRuleMender(String filteringRuleMender) {
		this.filteringRuleMender = filteringRuleMender;
	}

	public Date getFilteringRuleCreateDate() {
		return filteringRuleCreateDate;
	}

	public void setFilteringRuleCreateDate(Date filteringRuleCreateDate) {
		this.filteringRuleCreateDate = filteringRuleCreateDate;
	}

	public Date getFilteringRuleMenderDate() {
		return filteringRuleMenderDate;
	}

	public void setFilteringRuleMenderDate(Date filteringRuleMenderDate) {
		this.filteringRuleMenderDate = filteringRuleMenderDate;
	}

	public String getFilteringRuleDes() {
		return filteringRuleDes;
	}

	public void setFilteringRuleDes(String filteringRuleDes) {
		this.filteringRuleDes = filteringRuleDes;
	}

	public String getFilteringRuleCondition() {
		return filteringRuleCondition;
	}

	public void setFilteringRuleCondition(String filteringRuleCondition) {
		this.filteringRuleCondition = filteringRuleCondition;
	}

	public Integer getFilteringRuleType() {
		return filteringRuleType;
	}

	public void setFilteringRuleType(Integer filteringRuleType) {
		this.filteringRuleType = filteringRuleType;
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



	public String getTargetAddrEqual() {
		return this.splitAddrOrPort("等于", this.targetAddr);
	}

	public void setTargetAddrEqual(String targetAddrEqual) {
		this.targetAddrEqual = targetAddrEqual;
	}

	public String getTargetAddrNotEqual() {
		return this.splitAddrOrPort("不等于", this.targetAddr);
	}

	public void setTargetAddrNotEqual(String targetAddrNotEqual) {
		this.targetAddrNotEqual = targetAddrNotEqual;
	}

	public String getTargetAddrBetween() {
		return this.splitAddrOrPort("两者之间", this.targetAddr);
	}

	public void setTargetAddrBetween(String targetAddrBetween) {
		this.targetAddrBetween = targetAddrBetween;
	}

	public String getSourceAddrEqual() {
		return  this.splitAddrOrPort("等于", this.sourceAddr);
	}

	public void setSourceAddrEqual(String sourceAddrEqual) {
		this.sourceAddrEqual = sourceAddrEqual;
	}

	public String getSourceAddrNotEqual() {
		 return this.splitAddrOrPort("不等于", this.sourceAddr);
	}

	public void setSourceAddrNotEqual(String sourceAddrNotEqual) {
		this.sourceAddrNotEqual = sourceAddrNotEqual;
	}

	public String getSourceAddrBetween() {
		return this.splitAddrOrPort("两者之间", this.sourceAddr);
	}

	public void setSourceAddrBetween(String sourceAddrBetween) {
		this.sourceAddrBetween = sourceAddrBetween;
	}

	public String getTargetPortEqual() {
		return this.splitAddrOrPort("等于", this.targetPort);
	}

	public void setTargetPortEqual(String targetPortEqual) {
		this.targetPortEqual = targetPortEqual;
	}

	public String getTargetPortNotEqual() {
		return this.splitAddrOrPort("不等于", this.targetPort);
	}

	public void setTargetPortNotEqual(String targetPortNotEqual) {
		this.targetPortNotEqual = targetPortNotEqual;
	}

	public String getTargetPortBetween() {
		return this.splitAddrOrPort("两者之间", this.targetPort);
	}

	public void setTargetPortBetween(String targetPortBetween) {
		this.targetPortBetween = targetPortBetween;
	}

	public String getSourcePortEqual() {
		return this.splitAddrOrPort("等于", this.sourcePort);
	}

	public void setSourcePortEqual(String sourcePortEqual) {
		this.sourcePortEqual = sourcePortEqual;
	}

	public String getSourcePortNotEqual() {
		return this.splitAddrOrPort("不等于", this.sourcePort);
	}

	public void setSourcePortNotEqual(String sourcePortNotEqual) {
		this.sourcePortNotEqual = sourcePortNotEqual;
	}

	public String getSourcePortBetween() {
		return this.splitAddrOrPort("两者之间", this.sourcePort);
	}

	public void setSourcePortBetween(String sourcePortBetween) {
		this.sourcePortBetween = sourcePortBetween;
	}
/**
 * <把端口和地址拆分等于的放一个属性 不等于的放一个属性>
 * <功能详细描述>
 * @param i 操作类型 等于0 不等于1 两者之间 2
 * @param string 待处理的串
 * @return
 * @see [类、类#方法、类#成员]
 */
private String splitAddrOrPort(String condition,String string){
	//等于1,两者之间2-3,
	String result = "";//要返回的字符串
	if (!"".equals(string)) {
		String [] sTemp = string.split(",");
	

			for (String string2 : sTemp) {
				String[] string2Arr = string2.split(":");
				if (string2Arr[0].equals(condition)) {
					result = result+string2Arr[1]+",";
				}
				}

	}
	
	return result;

}

@Override
public String toString() {
	return "FilteringRule [ assetIds="
			+ assetIds + ", eventsTypeIds=" + eventsTypeIds
			+ ", eventsCategoryIds=" + eventsCategoryIds + ", sourceAddr="
			+ sourceAddr + ", targetAddr=" + targetAddr + ", sourcePort="
			+ sourcePort + ", targetPort=" + targetPort + ", priorityIds="
			+ priorityIds + ", targetAddrEqual=" + targetAddrEqual
			+ ", targetAddrNotEqual=" + targetAddrNotEqual
			+ ", targetAddrBetween=" + targetAddrBetween + ", sourceAddrEqual="
			+ sourceAddrEqual + ", sourceAddrNotEqual=" + sourceAddrNotEqual
			+ ", sourceAddrBetween=" + sourceAddrBetween + ", targetPortEqual="
			+ targetPortEqual + ", targetPortNotEqual=" + targetPortNotEqual
			+ ", targetPortBetween=" + targetPortBetween + ", sourcePortEqual="
			+ sourcePortEqual + ", sourcePortNotEqual=" + sourcePortNotEqual
			+ ", sourcePortBetween=" + sourcePortBetween + "]";
}






}