package com.compliance.model.cpManage.gapStatisticsUnit;

public class GapStatisticsUnit {
	private int bianzhun;
	private int shiyong;
	private int fuhe;
	private int bufenfuhe;
	private int bufuhe;
	private int chaju;
	private String fuhedu;

	private int bianzhunbianji;
	private int shiyongbianji;
	private int fuhebianji;
	private int bufenfuhebianji;
	private int bufuhebianji;
	private int chajubianji;
	private String fuhedubianji;

	private int bianzhunzhongduan;
	private int shiyongzhongduan;
	private int fuhezhongduan;
	private int bufenfuhezhongduan;
	private int bufuhezhongduan;
	private int chajuzhongduan;
	private String fuheduzhongduan;

	private int bianzhunfuwu;
	private int shiyongfuwu;
	private int fuhefuwu;
	private int bufenfuhefuwu;
	private int bufuhefuwu;
	private int chajufuwu;
	private String fuhedufuwu;

	private int bianzhunyingyong;
	private int shiyongyingyong;
	private int fuheyingyong;
	private int bufenfuheyingyong;
	private int bufuheyingyong;
	private int chajuyingyong;
	private String fuheduyingyong;

	private int bianzhunshuju;
	private int shiyongshuju;
	private int fuheshuju;
	private int bufenfuheshuju;
	private int bufuheshuju;
	private int chajushuju;
	private String fuhedushuju;

	private int bianzhunanquan;
	private int shiyonganquan;
	private int fuheanquan;
	private int bufenfuheanquan;
	private int bufuheanquan;
	private int chajuanquan;
	private String fuheduanquan;
	private String[] danweiname;

	private int bianzhuntywuli;
	private int shiyongtywuli;
	private int fuhetywuli;
	private int bufenfuhetywuli;
	private int bufuhetywuli;
	private int chajutywuli;
	private String fuhedutywuli;

	private int bianzhuntyguanli;
	private int shiyongtyguanli;
	private int fuhetyguanli;
	private int bufenfuhetyguanli;
	private int bufuhetyguanli;
	private int chajutyguanli;
	private String fuhedutyguanli;

	private String jichu;

	private String bianji;
	private String zhongduan;
	private String fuwu;
	private String yingyong;
	private String shuju;
	private String anquan;
	private String tywuli;
	private String tyguanli;

	private int fuhesum;
	private int bufenfuhesum;
	private int bufuhesum;
	String strname[] = { "基础网络安全", "边界安全", "终端系统安全", "服务端系统安全", "应用安全",
			"数据安全域备份恢复", "安全管理中心" };

	public int getBianzhuntywuli() {
		return bianzhuntywuli;
	}

	public void setBianzhuntywuli(int bianzhuntywuli) {
		this.bianzhuntywuli = bianzhuntywuli;
	}

	public int getShiyongtywuli() {
		return shiyongtywuli;
	}

	public void setShiyongtywuli(int shiyongtywuli) {
		this.shiyongtywuli = shiyongtywuli;
	}

	public int getFuhetywuli() {
		return fuhetywuli;
	}

	public void setFuhetywuli(int fuhetywuli) {
		this.fuhetywuli = fuhetywuli;
	}

	public int getBufenfuhetywuli() {
		return bufenfuhetywuli;
	}

	public void setBufenfuhetywuli(int bufenfuhetywuli) {
		this.bufenfuhetywuli = bufenfuhetywuli;
	}

	public int getBufuhetywuli() {
		return bufuhetywuli;
	}

	public void setBufuhetywuli(int bufuhetywuli) {
		this.bufuhetywuli = bufuhetywuli;
	}

	public int getChajutywuli() {
		return chajutywuli;
	}

	public void setChajutywuli(int chajutywuli) {
		this.chajutywuli = chajutywuli;
	}

	public String getFuhedutywuli() {
		return fuhedutywuli;
	}

	public void setFuhedutywuli(String fuhedutywuli) {
		this.fuhedutywuli = fuhedutywuli;
	}

	public int getBianzhuntyguanli() {
		return bianzhuntyguanli;
	}

	public void setBianzhuntyguanli(int bianzhuntyguanli) {
		this.bianzhuntyguanli = bianzhuntyguanli;
	}

	public int getShiyongtyguanli() {
		return shiyongtyguanli;
	}

	public void setShiyongtyguanli(int shiyongtyguanli) {
		this.shiyongtyguanli = shiyongtyguanli;
	}

	public int getFuhetyguanli() {
		return fuhetyguanli;
	}

	public void setFuhetyguanli(int fuhetyguanli) {
		this.fuhetyguanli = fuhetyguanli;
	}

	public int getBufenfuhetyguanli() {
		return bufenfuhetyguanli;
	}

	public void setBufenfuhetyguanli(int bufenfuhetyguanli) {
		this.bufenfuhetyguanli = bufenfuhetyguanli;
	}

	public int getBufuhetyguanli() {
		return bufuhetyguanli;
	}

	public void setBufuhetyguanli(int bufuhetyguanli) {
		this.bufuhetyguanli = bufuhetyguanli;
	}

	public int getChajutyguanli() {
		return chajutyguanli;
	}

	public void setChajutyguanli(int chajutyguanli) {
		this.chajutyguanli = chajutyguanli;
	}

	public String getFuhedutyguanli() {
		return fuhedutyguanli;
	}

	public void setFuhedutyguanli(String fuhedutyguanli) {
		this.fuhedutyguanli = fuhedutyguanli;
	}

	public String getTywuli() {
		return tywuli;
	}

	public void setTywuli(String tywuli) {
		this.tywuli = tywuli;
	}

	public String getTyguanli() {
		return tyguanli;
	}

	public void setTyguanli(String tyguanli) {
		this.tyguanli = tyguanli;
	}

	public String[] getDanweiname() {
		return danweiname;
	}

	public void setDanweiname(String[] danweiname) {
		this.danweiname = danweiname;
	}

	public int getBianzhunanquan() {
		return bianzhunanquan;
	}

	public void setBianzhunanquan(int bianzhunanquan) {
		this.bianzhunanquan = bianzhunanquan;
	}

	public int getShiyonganquan() {
		return shiyonganquan;
	}

	public void setShiyonganquan(int shiyonganquan) {
		this.shiyonganquan = shiyonganquan;
	}

	public int getFuheanquan() {
		return fuheanquan;
	}

	public void setFuheanquan(int fuheanquan) {
		this.fuheanquan = fuheanquan;
	}

	public int getBufenfuheanquan() {
		return bufenfuheanquan;
	}

	public void setBufenfuheanquan(int bufenfuheanquan) {
		this.bufenfuheanquan = bufenfuheanquan;
	}

	public int getBufuheanquan() {
		return bufuheanquan;
	}

	public void setBufuheanquan(int bufuheanquan) {
		this.bufuheanquan = bufuheanquan;
	}

	public int getChajuanquan() {
		return chajuanquan;
	}

	public void setChajuanquan(int chajuanquan) {
		this.chajuanquan = chajuanquan;
	}

	public String getFuheduanquan() {
		return fuheduanquan;
	}

	public void setFuheduanquan(String fuheduanquan) {
		this.fuheduanquan = fuheduanquan;
	}

	public String getAnquan() {
		return anquan;
	}

	public void setAnquan(String anquan) {
		this.anquan = anquan;
	}

	public int getFuhesum() {
		return fuhesum;
	}

	public void setFuhesum(int fuhesum) {
		this.fuhesum = fuhesum;
	}

	public int getBufenfuhesum() {
		return bufenfuhesum;
	}

	public void setBufenfuhesum(int bufenfuhesum) {
		this.bufenfuhesum = bufenfuhesum;
	}

	public int getBufuhesum() {
		return bufuhesum;
	}

	public void setBufuhesum(int bufuhesum) {
		this.bufuhesum = bufuhesum;
	}

	public String[] getStrname() {
		return strname;
	}

	public void setStrname(String[] strname) {
		this.strname = strname;
	}

	public String getJichu() {
		return jichu;
	}

	public void setJichu(String jichu) {
		this.jichu = jichu;
	}

	public String getBianji() {
		return bianji;
	}

	public void setBianji(String bianji) {
		this.bianji = bianji;
	}

	public String getZhongduan() {
		return zhongduan;
	}

	public void setZhongduan(String zhongduan) {
		this.zhongduan = zhongduan;
	}

	public String getFuwu() {
		return fuwu;
	}

	public void setFuwu(String fuwu) {
		this.fuwu = fuwu;
	}

	public String getYingyong() {
		return yingyong;
	}

	public void setYingyong(String yingyong) {
		this.yingyong = yingyong;
	}

	public String getShuju() {
		return shuju;
	}

	public void setShuju(String shuju) {
		this.shuju = shuju;
	}

	public int getBianzhunbianji() {
		return bianzhunbianji;
	}

	public void setBianzhunbianji(int bianzhunbianji) {
		this.bianzhunbianji = bianzhunbianji;
	}

	public int getShiyongbianji() {
		return shiyongbianji;
	}

	public void setShiyongbianji(int shiyongbianji) {
		this.shiyongbianji = shiyongbianji;
	}

	public int getFuhebianji() {
		return fuhebianji;
	}

	public void setFuhebianji(int fuhebianji) {
		this.fuhebianji = fuhebianji;
	}

	public int getBufenfuhebianji() {
		return bufenfuhebianji;
	}

	public void setBufenfuhebianji(int bufenfuhebianji) {
		this.bufenfuhebianji = bufenfuhebianji;
	}

	public int getBufuhebianji() {
		return bufuhebianji;
	}

	public void setBufuhebianji(int bufuhebianji) {
		this.bufuhebianji = bufuhebianji;
	}

	public int getChajubianji() {
		return chajubianji;
	}

	public void setChajubianji(int chajubianji) {
		this.chajubianji = chajubianji;
	}

	public String getFuhedubianji() {
		return fuhedubianji;
	}

	public void setFuhedubianji(String fuhedubianji) {
		this.fuhedubianji = fuhedubianji;
	}

	public int getBianzhunzhongduan() {
		return bianzhunzhongduan;
	}

	public void setBianzhunzhongduan(int bianzhunzhongduan) {
		this.bianzhunzhongduan = bianzhunzhongduan;
	}

	public int getShiyongzhongduan() {
		return shiyongzhongduan;
	}

	public void setShiyongzhongduan(int shiyongzhongduan) {
		this.shiyongzhongduan = shiyongzhongduan;
	}

	public int getFuhezhongduan() {
		return fuhezhongduan;
	}

	public void setFuhezhongduan(int fuhezhongduan) {
		this.fuhezhongduan = fuhezhongduan;
	}

	public int getBufenfuhezhongduan() {
		return bufenfuhezhongduan;
	}

	public void setBufenfuhezhongduan(int bufenfuhezhongduan) {
		this.bufenfuhezhongduan = bufenfuhezhongduan;
	}

	public int getBufuhezhongduan() {
		return bufuhezhongduan;
	}

	public void setBufuhezhongduan(int bufuhezhongduan) {
		this.bufuhezhongduan = bufuhezhongduan;
	}

	public int getChajuzhongduan() {
		return chajuzhongduan;
	}

	public void setChajuzhongduan(int chajuzhongduan) {
		this.chajuzhongduan = chajuzhongduan;
	}

	public String getFuheduzhongduan() {
		return fuheduzhongduan;
	}

	public void setFuheduzhongduan(String fuheduzhongduan) {
		this.fuheduzhongduan = fuheduzhongduan;
	}

	public int getBianzhunfuwu() {
		return bianzhunfuwu;
	}

	public void setBianzhunfuwu(int bianzhunfuwu) {
		this.bianzhunfuwu = bianzhunfuwu;
	}

	public int getShiyongfuwu() {
		return shiyongfuwu;
	}

	public void setShiyongfuwu(int shiyongfuwu) {
		this.shiyongfuwu = shiyongfuwu;
	}

	public int getFuhefuwu() {
		return fuhefuwu;
	}

	public void setFuhefuwu(int fuhefuwu) {
		this.fuhefuwu = fuhefuwu;
	}

	public int getBufenfuhefuwu() {
		return bufenfuhefuwu;
	}

	public void setBufenfuhefuwu(int bufenfuhefuwu) {
		this.bufenfuhefuwu = bufenfuhefuwu;
	}

	public int getBufuhefuwu() {
		return bufuhefuwu;
	}

	public void setBufuhefuwu(int bufuhefuwu) {
		this.bufuhefuwu = bufuhefuwu;
	}

	public int getChajufuwu() {
		return chajufuwu;
	}

	public void setChajufuwu(int chajufuwu) {
		this.chajufuwu = chajufuwu;
	}

	public String getFuhedufuwu() {
		return fuhedufuwu;
	}

	public void setFuhedufuwu(String fuhedufuwu) {
		this.fuhedufuwu = fuhedufuwu;
	}

	public int getBianzhunyingyong() {
		return bianzhunyingyong;
	}

	public void setBianzhunyingyong(int bianzhunyingyong) {
		this.bianzhunyingyong = bianzhunyingyong;
	}

	public int getShiyongyingyong() {
		return shiyongyingyong;
	}

	public void setShiyongyingyong(int shiyongyingyong) {
		this.shiyongyingyong = shiyongyingyong;
	}

	public int getFuheyingyong() {
		return fuheyingyong;
	}

	public void setFuheyingyong(int fuheyingyong) {
		this.fuheyingyong = fuheyingyong;
	}

	public int getBufenfuheyingyong() {
		return bufenfuheyingyong;
	}

	public void setBufenfuheyingyong(int bufenfuheyingyong) {
		this.bufenfuheyingyong = bufenfuheyingyong;
	}

	public int getBufuheyingyong() {
		return bufuheyingyong;
	}

	public void setBufuheyingyong(int bufuheyingyong) {
		this.bufuheyingyong = bufuheyingyong;
	}

	public int getChajuyingyong() {
		return chajuyingyong;
	}

	public void setChajuyingyong(int chajuyingyong) {
		this.chajuyingyong = chajuyingyong;
	}

	public String getFuheduyingyong() {
		return fuheduyingyong;
	}

	public void setFuheduyingyong(String fuheduyingyong) {
		this.fuheduyingyong = fuheduyingyong;
	}

	public int getBianzhunshuju() {
		return bianzhunshuju;
	}

	public void setBianzhunshuju(int bianzhunshuju) {
		this.bianzhunshuju = bianzhunshuju;
	}

	public int getShiyongshuju() {
		return shiyongshuju;
	}

	public void setShiyongshuju(int shiyongshuju) {
		this.shiyongshuju = shiyongshuju;
	}

	public int getFuheshuju() {
		return fuheshuju;
	}

	public void setFuheshuju(int fuheshuju) {
		this.fuheshuju = fuheshuju;
	}

	public int getBufenfuheshuju() {
		return bufenfuheshuju;
	}

	public void setBufenfuheshuju(int bufenfuheshuju) {
		this.bufenfuheshuju = bufenfuheshuju;
	}

	public int getBufuheshuju() {
		return bufuheshuju;
	}

	public void setBufuheshuju(int bufuheshuju) {
		this.bufuheshuju = bufuheshuju;
	}

	public int getChajushuju() {
		return chajushuju;
	}

	public void setChajushuju(int chajushuju) {
		this.chajushuju = chajushuju;
	}

	public String getFuhedushuju() {
		return fuhedushuju;
	}

	public void setFuhedushuju(String fuhedushuju) {
		this.fuhedushuju = fuhedushuju;
	}

	private String sysname;

	private String casysGrade;

	private String[] utilname = { "基础网络安全", "边界安全", "终端系统安全", "服务端系统安全",
			"应用安全", "数据安全域备份恢复", "安全管理中心", "通用物理安全", "通用管理安全" };

	public String[] getUtilname() {
		return utilname;
	}

	public void setUtilname(String[] utilname) {
		this.utilname = utilname;
	}

	public String getCasysGrade() {
		return casysGrade;
	}

	public void setCasysGrade(String casysGrade) {
		this.casysGrade = casysGrade;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public int getBianzhun() {
		return bianzhun;
	}

	public void setBianzhun(int bianzhun) {
		this.bianzhun = bianzhun;
	}

	public int getShiyong() {
		return shiyong;
	}

	public void setShiyong(int shiyong) {
		this.shiyong = shiyong;
	}

	public int getFuhe() {
		return fuhe;
	}

	public void setFuhe(int fuhe) {
		this.fuhe = fuhe;
	}

	public int getBufenfuhe() {
		return bufenfuhe;
	}

	public void setBufenfuhe(int bufenfuhe) {
		this.bufenfuhe = bufenfuhe;
	}

	public int getBufuhe() {
		return bufuhe;
	}

	public void setBufuhe(int bufuhe) {
		this.bufuhe = bufuhe;
	}

	public int getChaju() {
		return chaju;
	}

	public void setChaju(int chaju) {
		this.chaju = chaju;
	}

	public String getFuhedu() {
		return fuhedu;
	}

	public void setFuhedu(String fuhedu) {
		this.fuhedu = fuhedu;
	}

}
