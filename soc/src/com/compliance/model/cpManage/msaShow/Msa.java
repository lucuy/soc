package com.compliance.model.cpManage.msaShow;

import java.util.Date;

/**
 * 通用管理安全测评实体
 * @author quyongkun
 *
 */
public class Msa {
	
	/**
	 * 用管理安全测评主键
	 */
	private int msaId;
	
	/**
	 * 测评时间
	 */
	private Date msaDate;
	
	/**
	 * 排序
	 */
	private String msaSort;
	
	/**
	 * a结果
	 */
	private String msaA;
	
	/**
	 * b结果
	 */
	private String msaB;
	
	/**
	 * c结果
	 */
	private String msaC;
	
	/**
	 * d结果
	 */
	private String msaD;
	
	/**
	 * e结果
	 */
	private String msaE;
	
	/**
	 * f结果
	 */
	private String msaF;
	
	/**
	 * g结果
	 */
	private String msaG;
	
	/**
	 * h结果
	 */
	private String msaH;
	
	/**
	 * i结果
	 */
	private String msaI;
	
	/**
	 * j结果
	 */
	private String msaJ;
	
	/**
	 * 评估结果
	 */
	private String msaAssessResult;
	
	/**
	 * 主要问题描述
	 */
	private String msaMainProbDes;

	public int getMsaId() {
		return msaId;
	}

	public void setMsaId(int msaId) {
		this.msaId = msaId;
	}

	public Date getMsaDate() {
		return msaDate;
	}

	public void setMsaDate(Date msaDate) {
		this.msaDate = msaDate;
	}

	public String getMsaSort() {
		return msaSort;
	}

	public void setMsaSort(String msaSort) {
		this.msaSort = msaSort;
	}

	public String getMsaA() {
		return msaA;
	}

	public void setMsaA(String msaA) {
		this.msaA = msaA;
	}

	public String getMsaB() {
		return msaB;
	}

	public void setMsaB(String msaB) {
		this.msaB = msaB;
	}

	public String getMsaC() {
		return msaC;
	}

	public void setMsaC(String msaC) {
		this.msaC = msaC;
	}

	public String getMsaD() {
		return msaD;
	}

	public void setMsaD(String msaD) {
		this.msaD = msaD;
	}

	public String getMsaE() {
		return msaE;
	}

	public void setMsaE(String msaE) {
		this.msaE = msaE;
	}

	public String getMsaF() {
		return msaF;
	}

	public void setMsaF(String msaF) {
		this.msaF = msaF;
	}

	public String getMsaG() {
		return msaG;
	}

	public void setMsaG(String msaG) {
		this.msaG = msaG;
	}

	public String getMsaH() {
		return msaH;
	}

	public void setMsaH(String msaH) {
		this.msaH = msaH;
	}

	public String getMsaI() {
		return msaI;
	}

	public void setMsaI(String msaI) {
		this.msaI = msaI;
	}

	public String getMsaJ() {
		return msaJ;
	}

	public void setMsaJ(String msaJ) {
		this.msaJ = msaJ;
	}

	public String getMsaAssessResult() {
		return msaAssessResult;
	}

	public void setMsaAssessResult(String msaAssessResult) {
		this.msaAssessResult = msaAssessResult;
	}

	public String getMsaMainProbDes() {
		return msaMainProbDes;
	}

	public void setMsaMainProbDes(String msaMainProbDes) {
		this.msaMainProbDes = msaMainProbDes;
	}

	public Msa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Msa [msaId=" + msaId + ", msaDate=" + msaDate + ", msaSort="
				+ msaSort + ", msaA=" + msaA + ", msaB=" + msaB + ", msaC="
				+ msaC + ", msaD=" + msaD + ", msaE=" + msaE + ", msaF=" + msaF
				+ ", msaG=" + msaG + ", msaH=" + msaH + ", msaI=" + msaI
				+ ", msaJ=" + msaJ + ", msaAssessResult=" + msaAssessResult
				+ ", msaMainProbDes=" + msaMainProbDes + "]";
	}
	
	

}
