package com.soc.model.events;

import java.io.Serializable;
/**
 * 从获得事件列表转换成事件库下的树
 * @author 何贝贝
 * @version 2013-06-27
 */
public class EventTree implements Serializable {

	/**
	 * 节点 的编号
	 */
	private int id;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 父节点ID
	 */
	private int parent;
	/**
	 * Action的提交位置
	 */
	private String action;
	
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
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "EventTree [id=" + id + ", name=" + name + ", parent=" + parent
				+ ", action=" + action + "]";
	}
	
	
}
