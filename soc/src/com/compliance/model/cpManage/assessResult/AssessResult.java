package com.compliance.model.cpManage.assessResult;

public class AssessResult {

	private int PK_CIA;//最新测评信息表id
	private int FK_CA;//新建评估项id
	private String CIA_Sort;//评估项序号，如6.1.1.1
	//评估项，
	private String CIA_A;
	private String CIA_B;
	private String CIA_C;
	private String CIA_D;
	private String CIA_E;
	private String CIA_F;
	private String CIA_G;
	private String CIA_H;
	private String CIA_I;
	private String CIA_J;
	private String CIA_AssessResult;//单元评估结果
	private String CIA_MainProbDes;//主要问题描述
	
	public AssessResult() {
		// TODO Auto-generated constructor stub
	}

	public int getPK_CIA() {
		return PK_CIA;
	}

	public void setPK_CIA(int pK_CIA) {
		PK_CIA = pK_CIA;
	}

	public int getFK_CA() {
		return FK_CA;
	}

	public void setFK_CA(int fK_CA) {
		FK_CA = fK_CA;
	}

	public String getCIA_Sort() {
		return CIA_Sort;
	}

	public void setCIA_Sort(String cIA_Sort) {
		CIA_Sort = cIA_Sort;
	}

	public String getCIA_A() {
		return CIA_A;
	}

	public void setCIA_A(String cIA_A) {
		CIA_A = cIA_A;
	}

	public String getCIA_B() {
		return CIA_B;
	}

	public void setCIA_B(String cIA_B) {
		CIA_B = cIA_B;
	}

	public String getCIA_C() {
		return CIA_C;
	}

	public void setCIA_C(String cIA_C) {
		CIA_C = cIA_C;
	}

	public String getCIA_D() {
		return CIA_D;
	}

	public void setCIA_D(String cIA_D) {
		CIA_D = cIA_D;
	}

	public String getCIA_E() {
		return CIA_E;
	}

	public void setCIA_E(String cIA_E) {
		CIA_E = cIA_E;
	}

	public String getCIA_F() {
		return CIA_F;
	}

	public void setCIA_F(String cIA_F) {
		CIA_F = cIA_F;
	}

	public String getCIA_G() {
		return CIA_G;
	}

	public void setCIA_G(String cIA_G) {
		CIA_G = cIA_G;
	}

	public String getCIA_H() {
		return CIA_H;
	}

	public void setCIA_H(String cIA_H) {
		CIA_H = cIA_H;
	}

	public String getCIA_I() {
		return CIA_I;
	}

	public void setCIA_I(String cIA_I) {
		CIA_I = cIA_I;
	}

	public String getCIA_J() {
		return CIA_J;
	}

	public void setCIA_J(String cIA_J) {
		CIA_J = cIA_J;
	}

	public String getCIA_AssessResult() {
		return CIA_AssessResult;
	}

	public void setCIA_AssessResult(String cIA_AssessResult) {
		CIA_AssessResult = cIA_AssessResult;
	}

	public String getCIA_MainProbDes() {
		return CIA_MainProbDes;
	}

	public void setCIA_MainProbDes(String cIA_MainProbDes) {
		CIA_MainProbDes = cIA_MainProbDes;
	}
	
	
}
