package com.compliance.model.rank;

/**
 * 系统废止
 * @author quyongkun
 *
 */
public class SysAbolish {

	/**
	 * 主键
	 */
	private int sysAbolishId;
	
	/**
	 * 引用信息系统主键
	 */
	private int pkSysInfo;
	
	/**
	 * 信息删除描述
	 */
	private String sysDescription;
	
	/**
	 * 信息删除相关附件
	 */
	private String sysAccess;

	/**
	 * 重命名信息删除相关附件
	 */
	private String reSysAccess;
	/**
	 * 设备清除描述
	 */
	private String devDescription;
	
	/**
	 * 设备清除相关附件
	 */
	private String devAccess;
	
	/**
	 * 重命名设备清除相关附件
	 */
	private String reDevAccess;
	
	/**
	 * 存储清除描述
	 */
	private String storDescription;
	
	/**
	 * 存储清除相关附件
	 */
	private String storAccess;
	
	/**
	 * 重命名存储清除相关附件
	 */
	private String reStorAccess;
    private String abolishsysnames;
	
	public int getSysAbolishId() {
		return sysAbolishId;
	}

	public void setSysAbolishId(int sysAbolishId) {
		this.sysAbolishId = sysAbolishId;
	}

	public int getPkSysInfo() {
		return pkSysInfo;
	}


	public void setPkSysInfo(int pkSysInfo) {
		this.pkSysInfo = pkSysInfo;
	}


	public String getSysDescription() {
		return sysDescription;
	}

	public void setSysDescription(String sysDescription) {
		this.sysDescription = sysDescription;
	}

	public String getSysAccess() {
		return sysAccess;
	}

	public void setSysAccess(String sysAccess) {
		this.sysAccess = sysAccess;
	}

	public String getDevDescription() {
		return devDescription;
	}

	public void setDevDescription(String devDescription) {
		this.devDescription = devDescription;
	}

	public String getDevAccess() {
		return devAccess;
	}

	public void setDevAccess(String devAccess) {
		this.devAccess = devAccess;
	}

	public String getStorDescription() {
		return storDescription;
	}


	public void setStorDescription(String storDescription) {
		this.storDescription = storDescription;
	}


	public String getStorAccess() {
		return storAccess;
	}


	public void setStorAccess(String storAccess) {
		this.storAccess = storAccess;
	}


	public SysAbolish() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAbolishsysnames() {
		return abolishsysnames;
	}

	public void setAbolishsysnames(String abolishsysnames) {
		this.abolishsysnames = abolishsysnames;
	}

	@Override
	public String toString() {
		return "SysAbolish [sysAbolishId=" + sysAbolishId + ", pkSysInfo="
				+ pkSysInfo + ", sysDescription=" + sysDescription
				+ ", sysAccess=" + sysAccess + ", devDescription="
				+ devDescription + ", devAccess=" + devAccess
				+ ", storDescription=" + storDescription + ", storAccess="
				+ storAccess + "]";
	}

	public String getReSysAccess() {
		return reSysAccess;
	}

	public void setReSysAccess(String reSysAccess) {
		this.reSysAccess = reSysAccess;
	}

	public String getReDevAccess() {
		return reDevAccess;
	}

	public void setReDevAccess(String reDevAccess) {
		this.reDevAccess = reDevAccess;
	}

	public String getReStorAccess() {
		return reStorAccess;
	}

	public void setReStorAccess(String reStorAccess) {
		this.reStorAccess = reStorAccess;
	}

}
