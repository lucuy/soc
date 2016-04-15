package com.compliance.model.cpManage.demand;

import java.util.List;
import java.util.Map;

/**
 * 
 * Description：整改需求汇总model层 
 * @author leiya
 *2013-5-15下午6:46:41
 */
public class DemandCollet {
 
	private int PK_CAD;
	private String unitDomainName;//排序名 （CAD_Sort）
	private String unitName;//单元名(CAD_Name)
	private String unitCon;//单元内容(CAD_Content)
	private String CAD_FatherSort;
	private String CAD_ListGrade;
	private String CAD_Grade;
	private List list;

	public DemandCollet() {
		// TODO Auto-generated constructor stub
		
	}


	public int getPK_CAD() {
		return PK_CAD;
	}


	public void setPK_CAD(int pK_CAD) {
		PK_CAD = pK_CAD;
	}


	public String getUnitDomainName() {
		return unitDomainName;
	}


	public void setUnitDomainName(String unitDomainName) {
		this.unitDomainName = unitDomainName;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getUnitCon() {
		return unitCon;
	}


	public void setUnitCon(String unitCon) {
		this.unitCon = unitCon;
	}


	public String getCAD_FatherSort() {
		return CAD_FatherSort;
	}


	public void setCAD_FatherSort(String cAD_FatherSort) {
		CAD_FatherSort = cAD_FatherSort;
	}


	public String getCAD_ListGrade() {
		return CAD_ListGrade;
	}


	public void setCAD_ListGrade(String cAD_ListGrade) {
		CAD_ListGrade = cAD_ListGrade;
	}


	public String getCAD_Grade() {
		return CAD_Grade;
	}


	public void setCAD_Grade(String cAD_Grade) {
		CAD_Grade = cAD_Grade;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}
}
