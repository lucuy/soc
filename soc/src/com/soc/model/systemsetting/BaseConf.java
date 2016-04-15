package com.soc.model.systemsetting;

/**
 * Description: 配置实体父类
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-31
 * 
 */
public class BaseConf {

	protected 		Integer 	id; // ID
	protected 		String 		key; // 键名
	protected 		String 		value; // 键值
	protected 		String 		memo; // 描述

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
