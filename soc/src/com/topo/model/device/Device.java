package com.topo.model.device;

import com.topo.model.deviceCategory.DeviceCategory;
import com.util.StringUtil;

/**
 * 设备实体类
 * @author peixiumei
 *
 */
public class Device {
	
	public static final int STATUS_ONLINE = 1;//设备在线
	public static final int STATUS_OFFLINE = 0;//设备不在线
	public static final int STATUS_COMMON_ALARM=2;//一般告警
	public static final int STATUS_SLIGHT_ALARM=3;//轻微告警
	public static final int STATUS_IMPORTANT_ALARM=4;//重要告警
	public static final int STATUS_SERIOUS_ALARM = 5;//严重告警

	//设备ID
	private long device_id;
	//设备名称
	private String device_name;
	//设备类型名称
	private DeviceCategory deviceCategory;
	//private long device_deviceCategory_id;
	//private String device_deviceCategory_name;
	//设备IP
	private String device_ip;
	//设备mac
	private String device_mac;
	//设备状态
	private int device_status;
	//设备描述
	private String device_describe;
	//设备位置X
	private String device_locationX;
	//设备位置Y
	private String device_locationY;
	//设备关联ID
	private String device_association_id;
	//标识字段
	private long device_mark;
	//资产ID
	private long device_asset_id;
	//用来保存外网的登陆名称
	private String device_loginName;
	
	//告警数量（外网拓扑用到的）
	private Integer alertAcount;
	//snmp社区名称
	private String device_community_name;
	
	
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getDevice_id() {
		return device_id;
	}
	public void setDevice_id(long device_id) {
		this.device_id = device_id;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	
	
	
	
	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}
	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
	public String getDevice_ip() {
		return device_ip;
	}
	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	public String getDevice_mac() {
		return device_mac;
	}
	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}
	
	 public int getDevice_status() {
		return device_status;
	}
	public void setDevice_status(int device_status) {
		this.device_status = device_status;
	} 
	public String getDevice_describe() {
		return device_describe;
	}
	public void setDevice_describe(String device_describe) {
		this.device_describe = device_describe;
	}
	public String getDevice_locationX() {
		return device_locationX;
	}
	public void setDevice_locationX(String device_locationX) {
		this.device_locationX = device_locationX;
	}
	public String getDevice_locationY() {
		return device_locationY;
	}
	public void setDevice_locationY(String device_locationY) {
		this.device_locationY = device_locationY;
	}
	
	
	public String getDevice_association_id() {
		return device_association_id;
	}
	public void setDevice_association_id(String device_association_id) {
		this.device_association_id = device_association_id;
	}
	
	public long getDevice_mark() {
		return device_mark;
	}
	public void setDevice_mark(long device_mark) {
		this.device_mark = device_mark;
	}
	
	
	public long getDevice_asset_id() {
		return device_asset_id;
	}
	public void setDevice_asset_id(long device_asset_id) {
		this.device_asset_id = device_asset_id;
	}
	
	
	public String getDevice_loginName() {
		return device_loginName;
	}
	public void setDevice_loginName(String device_loginName) {
		this.device_loginName = device_loginName;
	}
	
	public Integer getAlertAcount() {
		return alertAcount;
	}
	public void setAlertAcount(Integer alertAcount) {
		this.alertAcount = alertAcount;
	}
	public String getDevice_community_name() {
		return device_community_name;
	}
	public void setDevice_community_name(String device_community_name) {
		this.device_community_name = device_community_name;
	}
	
	
	
	
}
