package com.compliance.model.cpManage.gapReportDocument;

public class ReportDocument {
	private int jichu;
	private int bianjie;
	private int zhongduan;
	private int fuwu;
	private int yingyong;
	private int shuju;
	private int anquan;
	private int sum;
	private String sysname;
	private String[] utilname = { "基础网络安全", "边界安全", "终端系统安全", "服务端系统安全",
			"应用安全", "数据安全域备份恢复", "安全管理中心", "通用物理安全", "通用管理安全", "合计" };
	
	
	
	private int xitongjichusum;
	private int xitongbianjisum;
	private	int xitongzhongduansum;
	private	int xitongfuwusum;
	private	int xitongyingyongsum;
	private	int xitongshujusum;
	private	int xitonganquansum;
	
	
	private	int tywuli;
	private int tyguanli;
	

	public int getTywuli() {
		return tywuli;
	}

	public void setTywuli(int tywuli) {
		this.tywuli = tywuli;
	}


	public int getTyguanli() {
		return tyguanli;
	}

	public void setTyguanli(int tyguanli) {
		this.tyguanli = tyguanli;
	}

	public int getXitongjichusum() {
		return xitongjichusum;
	}

	public void setXitongjichusum(int xitongjichusum) {
		this.xitongjichusum = xitongjichusum;
	}

	public int getXitongbianjisum() {
		return xitongbianjisum;
	}

	public void setXitongbianjisum(int xitongbianjisum) {
		this.xitongbianjisum = xitongbianjisum;
	}

	public int getXitongzhongduansum() {
		return xitongzhongduansum;
	}

	public void setXitongzhongduansum(int xitongzhongduansum) {
		this.xitongzhongduansum = xitongzhongduansum;
	}

	public int getXitongfuwusum() {
		return xitongfuwusum;
	}

	public void setXitongfuwusum(int xitongfuwusum) {
		this.xitongfuwusum = xitongfuwusum;
	}

	public int getXitongyingyongsum() {
		return xitongyingyongsum;
	}

	public void setXitongyingyongsum(int xitongyingyongsum) {
		this.xitongyingyongsum = xitongyingyongsum;
	}

	public int getXitongshujusum() {
		return xitongshujusum;
	}

	public void setXitongshujusum(int xitongshujusum) {
		this.xitongshujusum = xitongshujusum;
	}

	public int getXitonganquansum() {
		return xitonganquansum;
	}

	public void setXitonganquansum(int xitonganquansum) {
		this.xitonganquansum = xitonganquansum;
	}

	public String[] getUtilname() {
		return utilname;
	}

	public void setUtilname(String[] utilname) {
		this.utilname = utilname;
	}

	public int getJichu() {
		return jichu;
	}

	public void setJichu(int jichu) {
		this.jichu = jichu;
	}

	public int getBianjie() {
		return bianjie;
	}

	public void setBianjie(int bianjie) {
		this.bianjie = bianjie;
	}

	public int getZhongduan() {
		return zhongduan;
	}

	public void setZhongduan(int zhongduan) {
		this.zhongduan = zhongduan;
	}

	public int getFuwu() {
		return fuwu;
	}

	public void setFuwu(int fuwu) {
		this.fuwu = fuwu;
	}

	public int getYingyong() {
		return yingyong;
	}

	public void setYingyong(int yingyong) {
		this.yingyong = yingyong;
	}

	public int getShuju() {
		return shuju;
	}

	public void setShuju(int shuju) {
		this.shuju = shuju;
	}

	public int getAnquan() {
		return anquan;
	}

	public void setAnquan(int anquan) {
		this.anquan = anquan;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
