package com.topo.model.deviceCategory;

public class DeviceCategoryPhoto {
	//设备类型图片ID
	private long deviceCategoryPhoto_id;
	//设备类型图片路径
	private String deviceCategoryphoto_Path;
	private String deviceCategoryphoto_name;
	public DeviceCategoryPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeviceCategoryPhoto(long deviceCategoryPhoto_id, String deviceCategoryphoto_Path) {
		super();
		this.deviceCategoryPhoto_id = deviceCategoryPhoto_id;
		this.deviceCategoryphoto_Path = deviceCategoryphoto_Path;
	}
	public long getDeviceCategoryPhoto_id() {
		return deviceCategoryPhoto_id;
	}
	public void setDeviceCategoryPhoto_id(long deviceCategoryPhoto_id) {
		this.deviceCategoryPhoto_id = deviceCategoryPhoto_id;
	}
	public String getDeviceCategoryphoto_Path() {
		return deviceCategoryphoto_Path;
	}
	public void setDeviceCategoryphoto_Path(String deviceCategoryphoto_Path) {
		this.deviceCategoryphoto_Path = deviceCategoryphoto_Path;
	}
	public String getDeviceCategoryphoto_name() {
		return deviceCategoryphoto_name;
	}
	public void setDeviceCategoryphoto_name(String deviceCategoryphoto_name) {
		this.deviceCategoryphoto_name = deviceCategoryphoto_name;
	}
	
}
