package com.compliance.model.cpManage.securityTable;

public class SecurityTable {
	private String jichuPertage;
	private String bianjiePertage;
	private String zhongduanPertage;
	private String fuwuduanPertage;
	private String yingyongPertage;
	private String shujuPertage;
	private String anquanPertage;
	private String twuliPertage;
	private String tguanliPertage;
	private String sysname;
	private String[] utilname = { "基础网络安全", "边界安全", "终端系统安全", "服务端系统安全",
			"应用安全", "数据安全域备份恢复", "安全管理中心", "通用物理安全", "通用管理安全" };
	public String[] getUtilname() {
		return utilname;
	}

	public void setUtilname(String[] utilname) {
		this.utilname = utilname;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getBianjiePertage() {
		return bianjiePertage;
	}

	public void setBianjiePertage(String bianjiePertage) {
		this.bianjiePertage = bianjiePertage;
	}

	public String getZhongduanPertage() {
		return zhongduanPertage;
	}

	public void setZhongduanPertage(String zhongduanPertage) {
		this.zhongduanPertage = zhongduanPertage;
	}

	public String getFuwuduanPertage() {
		return fuwuduanPertage;
	}

	public void setFuwuduanPertage(String fuwuduanPertage) {
		this.fuwuduanPertage = fuwuduanPertage;
	}

	public String getYingyongPertage() {
		return yingyongPertage;
	}

	public void setYingyongPertage(String yingyongPertage) {
		this.yingyongPertage = yingyongPertage;
	}

	public String getShujuPertage() {
		return shujuPertage;
	}

	public void setShujuPertage(String shujuPertage) {
		this.shujuPertage = shujuPertage;
	}

	public String getAnquanPertage() {
		return anquanPertage;
	}

	public void setAnquanPertage(String anquanPertage) {
		this.anquanPertage = anquanPertage;
	}

	public String getTwuliPertage() {
		return twuliPertage;
	}

	public void setTwuliPertage(String twuliPertage) {
		this.twuliPertage = twuliPertage;
	}

	public String getTguanliPertage() {
		return tguanliPertage;
	}

	public void setTguanliPertage(String tguanliPertage) {
		this.tguanliPertage = tguanliPertage;
	}

	public String getJichuPertage() {
		return jichuPertage;
	}

	public void setJichuPertage(String jichuPertage) {
		this.jichuPertage = jichuPertage;
	}
	

}
