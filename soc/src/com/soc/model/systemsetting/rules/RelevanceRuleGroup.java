package com.soc.model.systemsetting.rules;

import java.util.Date;
import java.util.List;


/**
 * 
 * <自定义关联规则实体类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RelevanceRuleGroup {
	//id
	private int id;
	//规则名字
	private String name;
	//描述
	private String desc;
	//启用状态
	private int status;
	//parentid
	private int parentId;
	// 创建者
	private String creater;
	//创建时间
	private Date time;
	//这个组下的规则
		private List<RelevanceRule> relevanceRules;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "RelevanceRuleGroup [id=" + id + ", name=" + name + ", desc="
				+ desc + ", status=" + status + ", parentId=" + parentId
				+ ", creater=" + creater + ", time=" + time + "]";
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<RelevanceRule> getRelevanceRules() {
		return relevanceRules;
	}
	public void setRelevanceRules(List<RelevanceRule> relevanceRules) {
		this.relevanceRules = relevanceRules;
	}
	

}
