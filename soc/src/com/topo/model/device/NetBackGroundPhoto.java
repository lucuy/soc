package com.topo.model.device;

public class NetBackGroundPhoto {

	private long photoId;
	//图片名称
	private String photoName;
	//图片选中状态（1：选中，0，木选中）
	private int photoChecked;
	//图片分类（0：内网，1：外网）
	private int photoStatus;
	
	public NetBackGroundPhoto() {
		
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public int getPhotoChecked() {
		return photoChecked;
	}

	public void setPhotoChecked(int photoChecked) {
		this.photoChecked = photoChecked;
	}

	public int getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(int photoStatus) {
		this.photoStatus = photoStatus;
	}
	
	
	
}
