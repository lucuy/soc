package com.compliance.model.basicinfo.system;

import java.io.Serializable;

public class SystemManager implements Serializable {
	// 主键
	private Integer id;
	// 系统名称
	private String sysName;
	// 系统编号
	private String sysId;
	// 业务类型
	private String busType;
	// 其他业务类型
	private String otherBusType;
	// 业务描述
	private String busDescription;
	// 其他业务范围
	private String otherArea;
	// 服务范围选择
	private String serArea;
	// 跨省个数
	private Integer proTotal;
	// 跨市个数
	private Integer cityTotal;
	// 服务对象
	private String serObj;
	// 拓扑图
	private String gplot;
	// 相关附件
	private String relAccess;
	// 其他服务对象
	private String otherObj;
	// 系统状态 0为正常 1为废止
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getOtherBusType() {
		return otherBusType;
	}

	public void setOtherBusType(String otherBusType) {
		this.otherBusType = otherBusType;
	}

	public String getBusDescription() {
		return busDescription;
	}

	public void setBusDescription(String busDescription) {
		this.busDescription = busDescription;
	}

	public String getSerArea() {
		return serArea;
	}

	public void setSerArea(String serArea) {
		this.serArea = serArea;
	}

	public Integer getProTotal() {
		return proTotal;
	}

	public void setProTotal(Integer proTotal) {
		this.proTotal = proTotal;
	}

	public Integer getCityTotal() {
		return cityTotal;
	}

	public void setCityTotal(Integer cityTotal) {
		this.cityTotal = cityTotal;
	}

	public String getSerObj() {
		return serObj;
	}

	public void setSerObj(String serObj) {
		this.serObj = serObj;
	}

	public String getGplot() {
		return gplot;
	}

	public void setGplot(String gplot) {
		this.gplot = gplot;
	}

	public String getRelAccess() {
		return relAccess;
	}

	public void setRelAccess(String relAccess) {
		this.relAccess = relAccess;
	}

	public String getOtherArea() {
		return otherArea;
	}

	public void setOtherArea(String otherArea) {
		this.otherArea = otherArea;
	}

	public String getOtherObj() {
		return otherObj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOtherObj(String otherObj) {
		this.otherObj = otherObj;
	}

}
