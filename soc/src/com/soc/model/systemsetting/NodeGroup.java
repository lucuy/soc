package com.soc.model.systemsetting;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 级联节点组实体类
 * 节点组信息的查看，编辑，删除，添加同级节点组，下级节点组
 * 
 * @author  lichanghong
 * @version  [版本号, 2013-12-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NodeGroup implements Serializable
{
	private long nodeGroupId;//节点id
	private String nodeGroupName;//节点名称
	private long nodeGroupParentId;//上级id
	//创建时间
    private Date nodeGroupCreateDateTime;
    //更新时间
    private Date nodeGroupUpdateDateTime;
    //删除标识
    private int nodeGroupIsDelete;
    //描述
    private String nodeGroupMemo;  
    //节点组的父资产组
    private NodeGroup parentGroup;
    private String nodeIp;//节点ip
    private String nodePort;//节点端口
    //预留字段
    //登录名字
    private String loginName;
    //用作图片的路径
    private String url;
    private int left;
    private int top;
    private String customs5;
	public long getNodeGroupId() {
		return nodeGroupId;
	}
	public void setNodeGroupId(long nodeGroupId) {
		this.nodeGroupId = nodeGroupId;
	}
	public String getNodeGroupName() {
		return nodeGroupName;
	}
	public void setNodeGroupName(String nodeGroupName) {
		this.nodeGroupName = nodeGroupName;
	}
	public long getNodeGroupParentId() {
		return nodeGroupParentId;
	}
	public void setNodeGroupParentId(long nodeGroupParentId) {
		this.nodeGroupParentId = nodeGroupParentId;
	}
	public Date getNodeGroupCreateDateTime() {
		return nodeGroupCreateDateTime;
	}
	public void setNodeGroupCreateDateTime(Date nodeGroupCreateDateTime) {
		this.nodeGroupCreateDateTime = nodeGroupCreateDateTime;
	}
	public Date getNodeGroupUpdateDateTime() {
		return nodeGroupUpdateDateTime;
	}
	public void setNodeGroupUpdateDateTime(Date nodeGroupUpdateDateTime) {
		this.nodeGroupUpdateDateTime = nodeGroupUpdateDateTime;
	}
	public int getNodeGroupIsDelete() {
		return nodeGroupIsDelete;
	}
	public void setNodeGroupIsDelete(int nodeGroupIsDelete) {
		this.nodeGroupIsDelete = nodeGroupIsDelete;
	}
	public String getNodeGroupMemo() {
		return nodeGroupMemo;
	}
	public void setNodeGroupMemo(String nodeGroupMemo) {
		this.nodeGroupMemo = nodeGroupMemo;
	}
	public NodeGroup getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(NodeGroup parentGroup) {
		this.parentGroup = parentGroup;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	
	public String getNodePort() {
		return nodePort;
	}
	public void setNodePort(String nodePort) {
		this.nodePort = nodePort;
	}
	public String getCustoms5() {
		return customs5;
	}
	public void setCustoms5(String customs5) {
		this.customs5 = customs5;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	
   
}
