package com.compliance.model.psad;

import java.util.Date;

/**
 * 通用物理测评
 * @author Administrator
 *
 */
public class Psa {

	private int psaId;
	private String psaDate; //评估时间，每天只能评估一次，整个评估的时间一样
	private String psaSort; //排序
	private String psaA;   //a0:否1：是2：不适用
	private String psaB;   //b0:否1：是2：不适用
	private String psaC;  //c0:否1：是2：不适用
	private String psaD;  //d0:否1：是2：不适用
	private String psaE;  //e0:否1：是2：不适用
	private String psaF;  //f0:否1：是2：不适用
	private String psaG; //g0:否1：是2：不适用
	private String psaH; //h0:否1：是2：不适用
	private String psaI; //i0:否1：是2：不适用
	private String psaJ; //j0:否1：是2：不适用
	private String psaAssessResult; //评估结果0:符合1：部分符合2：不符合3:不适用
	private String psaMainProbDes;  //主要问题描述
	public int getPsaId() {
		return psaId;
	}
	public void setPsaId(int psaId) {
		this.psaId = psaId;
	}
 
	public String getPsaDate() {
		return psaDate;
	}
	public void setPsaDate(String psaDate) {
		this.psaDate = psaDate;
	}
	public String getPsaSort() {
		return psaSort;
	}
	public void setPsaSort(String psaSort) {
		this.psaSort = psaSort;
	}
	public String getPsaA() {
		return psaA;
	}
	public void setPsaA(String psaA) {
		this.psaA = psaA;
	}
	public String getPsaB() {
		return psaB;
	}
	public void setPsaB(String psaB) {
		this.psaB = psaB;
	}
	public String getPsaC() {
		return psaC;
	}
	public void setPsaC(String psaC) {
		this.psaC = psaC;
	}
	public String getPsaD() {
		return psaD;
	}
	public void setPsaD(String psaD) {
		this.psaD = psaD;
	}
	public String getPsaE() {
		return psaE;
	}
	public void setPsaE(String psaE) {
		this.psaE = psaE;
	}
	public String getPsaF() {
		return psaF;
	}
	public void setPsaF(String psaF) {
		this.psaF = psaF;
	}
	public String getPsaG() {
		return psaG;
	}
	public void setPsaG(String psaG) {
		this.psaG = psaG;
	}
	public String getPsaH() {
		return psaH;
	}
	public void setPsaH(String psaH) {
		this.psaH = psaH;
	}
	public String getPsaI() {
		return psaI;
	}
	public void setPsaI(String psaI) {
		this.psaI = psaI;
	}
	public String getPsaJ() {
		return psaJ;
	}
	public void setPsaJ(String psaJ) {
		this.psaJ = psaJ;
	}
	public String getPsaAssessResult() {
		return psaAssessResult;
	}
	public void setPsaAssessResult(String psaAssessResult) {
		this.psaAssessResult = psaAssessResult;
	}
	public String getPsaMainProbDes() {
		return psaMainProbDes;
	}
	public void setPsaMainProbDes(String psaMainProbDes) {
		this.psaMainProbDes = psaMainProbDes;
	}
	@Override
	public String toString() {
		return "Psa [psaId=" + psaId + ", psaDate=" + psaDate + ", psaSort="
				+ psaSort + ", psaA=" + psaA + ", psaB=" + psaB + ", psaC="
				+ psaC + ", psaD=" + psaD + ", psaE=" + psaE + ", psaF=" + psaF
				+ ", psaG=" + psaG + ", psaH=" + psaH + ", psaI=" + psaI
				+ ", psaJ=" + psaJ + ", psaAssessResult=" + psaAssessResult
				+ ", psaMainProbDes=" + psaMainProbDes + "]";
	}

}
