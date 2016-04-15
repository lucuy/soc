package com.soc.model.eventtypetag;

/**
 * 
 * <事件分类定义实体类>
 * <定义事件分类的字段>
 * 
 * @author  admin
 * @version  [版本号, 2013-6-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class eventcategorytag {
	//事件类别id
    private long eventcategoryId;
    
    //事件类别的的key
    private String eventcategorykey;
    
    //事件类别的value
    private String eventcategoryValue;

	public long getEventcategoryId() {
		return eventcategoryId;
	}

	public void setEventcategoryId(long eventcategoryId) {
		this.eventcategoryId = eventcategoryId;
	}

	public String getEventcategorykey() {
		return eventcategorykey;
	}

	public void setEventcategorykey(String eventcategorykey) {
		this.eventcategorykey = eventcategorykey;
	}

	public String getEventcategoryValue() {
		return eventcategoryValue;
	}

	public void setEventcategoryValue(String eventcategoryValue) {
		this.eventcategoryValue = eventcategoryValue;
	}
}
