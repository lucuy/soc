package com.soc.model.knowledge;

import java.io.Serializable;

/**
 * 
 * <漏洞库模块的实体类>
 * 
 * 
 * @author gaosong
 * @version [版本号, 2013-1-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Leak implements Serializable {

	// 漏洞id
	private long leakId;

	// 漏洞名称
	private String leakName;

	// 漏洞类型
	private String leakType;

	// 漏洞等级
	private String leakLevel;

	// CNCVE编号
	private String CNCVENO;

	// CVE编号
	private String CVENO;

	// BUGTRAQ编号
	private String BUGTRAQNO;

	// 短描述
	private String sDES;

	// 长描述
	private String lDes;

	// 建议
	private String advice;

	public Leak() {

	}

	

	public long getLeakId() {
		return leakId;
	}



	public void setLeakId(long leakId) {
		this.leakId = leakId;
	}



	public String getLeakName() {
		return leakName;
	}

	public void setLeakName(String leakName) {
		this.leakName = leakName;
	}

	public String getLeakType() {
		return leakType;
	}

	public void setLeakType(String leakType) {
		this.leakType = leakType;
	}

	public String getLeakLevel() {
		return leakLevel;
	}

	public void setLeakLevel(String leakLevel) {
		this.leakLevel = leakLevel;
	}

	public String getCNCVENO() {
		return CNCVENO;
	}

	public void setCNCVENO(String cNCVENO) {
		CNCVENO = cNCVENO;
	}

	public String getCVENO() {
		return CVENO;
	}

	public void setCVENO(String cVENO) {
		CVENO = cVENO;
	}

	public String getBUGTRAQNO() {
		return BUGTRAQNO;
	}

	public void setBUGTRAQNO(String bUGTRAQNO) {
		BUGTRAQNO = bUGTRAQNO;
	}

	public String getsDES() {
		return sDES;
	}

	public void setsDES(String sDES) {
		this.sDES = sDES;
	}

	public String getlDes() {
		return lDes;
	}

	public void setlDes(String lDes) {
		this.lDes = lDes;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	@Override
	public String toString() {
		return "Leak [leakId=" + leakId + ", leakName=" + leakName
				+ ", leakType=" + leakType + ", leakLevel=" + leakLevel
				+ ", CNCVENO=" + CNCVENO + ", CVENO=" + CVENO + ", BUGTRAQNO="
				+ BUGTRAQNO + ", sDES=" + sDES + ", lDes=" + lDes + ", advice="
				+ advice + "]";
	}

}
