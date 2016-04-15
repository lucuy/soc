package com.compliance.model.cpManage.rectificationProposal;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Description：整改建议实体类，包含整改结果汇总。 
 * @author leiya
 *2013-5-19上午10:51:33
 */
public class RectificationProposal {

	private int PK_CORRRECOM;
	private String CORRRECOM_ControlDomainSort;//控制域排序
	private String CORRRECOM_ControlDomainName;//控制域名称 控制域=排序+名称
	private String CORRRECOM_ControlUnitSort;//控制单元排序
	private String CORRRECOM_ControlUnitName;//控制单元名称 控制单元=排序+名称
	private String CORRRECOM_ItemNumber;//整改项编号
	private String CORRRECOM_Content;//整改项内容
	private String CORRRECOM_Advise;//整改项建议
	private String CORRRECOM_SysName;//系统名称
	private String CORRRECOM_SysId;//系统编号
	private String CORRRECOM_Date;//整改时间
	private String CORRRECOM_AssessResult;//此项的评估结果  0：符合，1：部分符合，2：不符合，3：不适用
	private String CORRRECOM_AssessType;//评估类型 0：通用物理安全，1：通用管理安全，2：普通评估
	
	@SuppressWarnings("rawtypes")
	private List list = new ArrayList();
/*	private int PK_CAD;
	private String CAD_Sort;//排序名 （CAD_Sort）
	private String CAD_Name;//单元名(CAD_Name)
	private String CIA_A;
	private String CIA_B;
	private String CIA_C;
	private String CIA_D;
	private String CIA_E;
	private String CIA_F;
	private String CIA_G;
	private String CIA_H;
	private String CIA_I;
	private String CIA_J;*/
	
	public RectificationProposal() {
		// TODO Auto-generated constructor stub
	}

	public int getPK_CORRRECOM() {
		return PK_CORRRECOM;
	}

	public void setPK_CORRRECOM(int pK_CORRRECOM) {
		PK_CORRRECOM = pK_CORRRECOM;
	}

	public String getCORRRECOM_ControlDomainSort() {
		return CORRRECOM_ControlDomainSort;
	}

	public void setCORRRECOM_ControlDomainSort(String cORRRECOM_ControlDomainSort) {
		CORRRECOM_ControlDomainSort = cORRRECOM_ControlDomainSort;
	}

	public String getCORRRECOM_ControlDomainName() {
		return CORRRECOM_ControlDomainName;
	}

	public void setCORRRECOM_ControlDomainName(String cORRRECOM_ControlDomainName) {
		CORRRECOM_ControlDomainName = cORRRECOM_ControlDomainName;
	}

	public String getCORRRECOM_ControlUnitSort() {
		return CORRRECOM_ControlUnitSort;
	}

	public void setCORRRECOM_ControlUnitSort(String cORRRECOM_ControlUnitSort) {
		CORRRECOM_ControlUnitSort = cORRRECOM_ControlUnitSort;
	}

	public String getCORRRECOM_ControlUnitName() {
		return CORRRECOM_ControlUnitName;
	}

	public void setCORRRECOM_ControlUnitName(String cORRRECOM_ControlUnitName) {
		CORRRECOM_ControlUnitName = cORRRECOM_ControlUnitName;
	}

	
	public String getCORRRECOM_ItemNumber() {
		return CORRRECOM_ItemNumber;
	}

	public void setCORRRECOM_ItemNumber(String cORRRECOM_ItemNumber) {
		CORRRECOM_ItemNumber = cORRRECOM_ItemNumber;
	}

	public String getCORRRECOM_Content() {
		return CORRRECOM_Content;
	}

	public void setCORRRECOM_Content(String cORRRECOM_Content) {
		CORRRECOM_Content = cORRRECOM_Content;
	}

	public String getCORRRECOM_Advise() {
		return CORRRECOM_Advise;
	}

	public void setCORRRECOM_Advise(String cORRRECOM_Advise) {
		CORRRECOM_Advise = cORRRECOM_Advise;
	}

	public String getCORRRECOM_SysName() {
		return CORRRECOM_SysName;
	}

	public void setCORRRECOM_SysName(String cORRRECOM_SysName) {
		CORRRECOM_SysName = cORRRECOM_SysName;
	}

	public String getCORRRECOM_SysId() {
		return CORRRECOM_SysId;
	}

	public void setCORRRECOM_SysId(String cORRRECOM_SysId) {
		CORRRECOM_SysId = cORRRECOM_SysId;
	}

	public String getCORRRECOM_Date() {
		return CORRRECOM_Date;
	}

	public void setCORRRECOM_Date(String cORRRECOM_Date) {
		CORRRECOM_Date = cORRRECOM_Date;
	}

	public String getCORRRECOM_AssessResult() {
		return CORRRECOM_AssessResult;
	}

	public void setCORRRECOM_AssessResult(String cORRRECOM_AssessResult) {
		CORRRECOM_AssessResult = cORRRECOM_AssessResult;
	}

	public String getCORRRECOM_AssessType() {
		return CORRRECOM_AssessType;
	}

	public void setCORRRECOM_AssessType(String cORRRECOM_AssessType) {
		CORRRECOM_AssessType = cORRRECOM_AssessType;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

 
}
