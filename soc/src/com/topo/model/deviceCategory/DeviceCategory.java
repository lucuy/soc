package com.topo.model.deviceCategory;

public class DeviceCategory {
	//设备类型ID
	private long deviceCategory_id;
	//设备类型名称
	private String deviceCategory_name;
	//设备类型图片
	private String deviceCategory_photo;
	//设备类型描述
	private String deviceCategory_describe;
	public DeviceCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeviceCategory(long deviceCategory_id, String deviceCategory_name,
			String deviceCategory_photo, String deviceCategory_describe) {
		super();
		this.deviceCategory_id = deviceCategory_id;
		this.deviceCategory_name = deviceCategory_name;
		this.deviceCategory_photo = deviceCategory_photo;
		this.deviceCategory_describe = deviceCategory_describe;
	}
	public long getDeviceCategory_id() {
		return deviceCategory_id;
	}
	public void setDeviceCategory_id(long deviceCategory_id) {
		this.deviceCategory_id = deviceCategory_id;
	}
	public String getDeviceCategory_name() {
		return deviceCategory_name;
	}
	public void setDeviceCategory_name(String deviceCategory_name) {
		this.deviceCategory_name = deviceCategory_name;
	}
	public String getDeviceCategory_photo() {
		return deviceCategory_photo;
	}
	public void setDeviceCategory_photo(String deviceCategory_photo) {
		this.deviceCategory_photo = deviceCategory_photo;
	}
	public String getDeviceCategory_describe() {
		return deviceCategory_describe;
	}
	public void setDeviceCategory_describe(String deviceCategory_describe) {
		this.deviceCategory_describe = deviceCategory_describe;
	}
	
}
