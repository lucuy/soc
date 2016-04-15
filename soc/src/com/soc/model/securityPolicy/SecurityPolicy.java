package com.soc.model.securityPolicy;
/**
 * 安全策略的实体类
 * @author gaosong
 *
 */
public class SecurityPolicy {
private int id;//id
private String policyName;//策略名字
private String createUsername;//创建者的名字
private String modifyUsername;//修改人的名字方法
private String createTime;//创建者的名字、
private String modifyTime;//修改时间
private String relPolicyName;//关联策略的名字 这是一个文件名 java下发哪个文件到资产 为了避免重名问题 加上策略id
private String desc;//描述

public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPolicyName() {
	return policyName;
}
public void setPolicyName(String policyName) {
	this.policyName = policyName;
}
public String getCreateUsername() {
	return createUsername;
}
public void setCreateUsername(String createUsername) {
	this.createUsername = createUsername;
}
public String getModifyUsername() {
	return modifyUsername;
}
public void setModifyUsername(String modifyUsername) {
	this.modifyUsername = modifyUsername;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getModifyTime() {
	return modifyTime;
}
public void setModifyTime(String modifyTime) {
	this.modifyTime = modifyTime;
}
public String getRelPolicyName() {
	return relPolicyName;
}
public void setRelPolicyName(String relPolicyName) {
	this.relPolicyName = relPolicyName;
}


}
