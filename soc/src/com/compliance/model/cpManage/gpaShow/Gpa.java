package com.compliance.model.cpManage.gpaShow;





import java.util.Date;

/**
 * 通用物理安全测评表实体
 * @author quyongkun
 *
 */
public class Gpa {
	
	/**
	 * 主键
	 */
	private int gpaId;
	
	/**
	 * 测评时间
	 */
	private Date gpaDate;
	
	/**
	 * 排序
	 */
	private String gpaSort;
	
	/**
	 * a结果
	 */
	private String gpaA;
	
	/**
	 * b结果
	 */
	private String gpaB;
	
	/**
	 * c结果
	 */
	private String gpaC;
	
	/**
	 * d结果
	 */
	private String gpaD;
	
	/**
	 * e结果
	 */
	private String gpaE;
	
	/**
	 * f结果
	 */
	private String gpaF;
	
	/**
	 * g结果
	 */
	private String gpaG;
	
	/**
	 * h结果
	 */
	private String gpaH;
	
	/**
	 * i结果
	 */
	private String gpaI;
	
	/**
	 * j结果
	 */
	private String gpaJ;
	
	/**
	 * 评估结果
	 */
	private String gpaAssessResult;
	
	/**
	 * 主要问题描述
	 */
	private String gpaMainProbDes;

	public int getGpaId() {
		return gpaId;
	}

	public void setGpaId(int gpaId) {
		this.gpaId = gpaId;
	}

	public Date getGpaDate() {
		return gpaDate;
	}

	public void setGpaDate(Date gpaDate) {
		this.gpaDate = gpaDate;
	}

	public String getGpaSort() {
		return gpaSort;
	}

	public void setGpaSort(String gpaSort) {
		this.gpaSort = gpaSort;
	}

	public String getGpaA() {
		return gpaA;
	}

	public void setGpaA(String gpaA) {
		this.gpaA = gpaA;
	}

	public String getGpaB() {
		return gpaB;
	}

	public void setGpaB(String gpaB) {
		this.gpaB = gpaB;
	}

	public String getGpaC() {
		return gpaC;
	}

	public void setGpaC(String gpaC) {
		this.gpaC = gpaC;
	}

	public String getGpaD() {
		return gpaD;
	}

	public void setGpaD(String gpaD) {
		this.gpaD = gpaD;
	}

	public String getGpaE() {
		return gpaE;
	}

	public void setGpaE(String gpaE) {
		this.gpaE = gpaE;
	}

	public String getGpaF() {
		return gpaF;
	}

	public void setGpaF(String gpaF) {
		this.gpaF = gpaF;
	}

	public String getGpaG() {
		return gpaG;
	}

	public void setGpaG(String gpaG) {
		this.gpaG = gpaG;
	}

	public String getGpaH() {
		return gpaH;
	}

	public void setGpaH(String gpaH) {
		this.gpaH = gpaH;
	}

	public String getGpaI() {
		return gpaI;
	}

	public void setGpaI(String gpaI) {
		this.gpaI = gpaI;
	}

	public String getGpaJ() {
		return gpaJ;
	}

	public void setGpaJ(String gpaJ) {
		this.gpaJ = gpaJ;
	}

	public String getGpaAssessResult() {
		return gpaAssessResult;
	}

	public void setGpaAssessResult(String gpaAssessResult) {
		this.gpaAssessResult = gpaAssessResult;
	}

	public String getGpaMainProbDes() {
		return gpaMainProbDes;
	}

	public void setGpaMainProbDes(String gpaMainProbDes) {
		this.gpaMainProbDes = gpaMainProbDes;
	}

	public Gpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Gpa [gpaId=" + gpaId + ", gpaDate=" + gpaDate + ", gpaSort="
				+ gpaSort + ", gpaA=" + gpaA + ", gpaB=" + gpaB + ", gpaC="
				+ gpaC + ", gpaD=" + gpaD + ", gpaE=" + gpaE + ", gpaF=" + gpaF
				+ ", gpaG=" + gpaG + ", gpaH=" + gpaH + ", gpaI=" + gpaI
				+ ", gpaJ=" + gpaJ + ", gpaAssessResult=" + gpaAssessResult
				+ ", gpaMainProbDes=" + gpaMainProbDes + "]";
	}




	

}
