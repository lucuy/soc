package com.topo.model.device;
/**
 * 临时设备实体类，用来存放临时字段
 * @author peixiumei
 *
 */
public class TempDevice {

	//临时设备表ID
	private long temp_id;
	//临时设备ID
	private long temp_device_id;
	//临时设备位置X
	private String temp_locationx;
	//临时设备位置Y
	private String temp_locaitony;
	
	public TempDevice() {
		
	}

	public long getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(long temp_id) {
		this.temp_id = temp_id;
	}

	public long getTemp_device_id() {
		return temp_device_id;
	}

	public void setTemp_device_id(long temp_device_id) {
		this.temp_device_id = temp_device_id;
	}

	public String getTemp_locationx() {
		return temp_locationx;
	}

	public void setTemp_locationx(String temp_locationx) {
		this.temp_locationx = temp_locationx;
	}

	public String getTemp_locaitony() {
		return temp_locaitony;
	}

	public void setTemp_locaitony(String temp_locaitony) {
		this.temp_locaitony = temp_locaitony;
	}
	
	
}
