package com.soc.model.asset.device;

import java.util.List;
import java.util.Map;

public class Category {
	
	private long device_category_id;
	private String device_category_name;
	private long higher_ups_id;
	private String events_devname;
	private String events_programtype;
	private long events_customd1;
	private long events_rawid;
	private String device_category_code;
	private List<Category> devicelist;
	
	
	
	public long getDevice_category_id() {
		return device_category_id;
	}
	public void setDevice_category_id(long device_category_id) {
		this.device_category_id = device_category_id;
	}
	public String getDevice_category_name() {
		return device_category_name;
	}
	public void setDevice_category_name(String device_category_name) {
		this.device_category_name = device_category_name;
	}
	public long getHigher_ups_id() {
		return higher_ups_id;
	}
	public void setHigher_ups_id(long higher_ups_id) {
		this.higher_ups_id = higher_ups_id;
	}
	public String getEvents_devname() {
		return events_devname;
	}
	public void setEvents_devname(String events_devname) {
		this.events_devname = events_devname;
	}
	public String getEvents_programtype() {
		return events_programtype;
	}
	public void setEvents_programtype(String events_programtype) {
		this.events_programtype = events_programtype;
	}
	public long getEvents_customd1() {
		return events_customd1;
	}
	public void setEvents_customd1(long events_customd1) {
		this.events_customd1 = events_customd1;
	}
	public long getEvents_rawid() {
		return events_rawid;
	}
	public void setEvents_rawid(long events_rawid) {
		this.events_rawid = events_rawid;
	}
	public String getDevice_category_code() {
		return device_category_code;
	}
	public void setDevice_category_code(String device_category_code) {
		this.device_category_code = device_category_code;
	}
	public List<Category> getDevicelist() {
		return devicelist;
	}
	public void setDevicelist(List<Category> devicelist) {
		this.devicelist = devicelist;
	}
	

	
	
}
