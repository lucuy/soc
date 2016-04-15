package com.soc.model.home;

import java.io.Serializable;

/**
 * 
 * <homepage中div的各个属性>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2014-1-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HomePageDiv  implements Serializable{
//div的id
private String id;
//div的显示状态 1显示 0不显示
private int status;
//div的内容 html串
private String divContext;
//位置
private int x;
private int y;
private long userId;
public HomePageDiv() {
	super();
}

public HomePageDiv(String id, int status, int x ,long userId) {
	super();
	this.id = id;
	this.x = x;
	this.status = status;
	this.userId = userId;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getDivContext() {
	return divContext;
}
public void setDivContext(String divContext) {
	this.divContext = divContext;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}

public long getUserId() {
	return userId;
}

public void setUserId(long userId) {
	this.userId = userId;
}

}
