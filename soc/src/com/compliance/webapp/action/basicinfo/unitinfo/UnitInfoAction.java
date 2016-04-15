package com.compliance.webapp.action.basicinfo.unitinfo;

import javax.servlet.http.HttpServletRequest;

import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.basicinfo.unitinfo.UnitInfoService;
import com.compliance.service.rank.RankService;

import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class UnitInfoAction extends BaseAction implements ModelDriven<UnitInfo>{

	private UnitInfoService unitInfoService;
	private RankService rankService;
	private SystemService systemService;
	private UnitInfo unitInfo = new UnitInfo();
//	private AuditService auditService;
	private int sysNum = 0; // 信息系统个数

	private int sysSecNum = 0; // 第二级信息系统个数
	private int systhrNum = 0; // 第三级信息系统个数
	private int systhiNum = 0; // 第四级信息系统个数

	
	
	@Override
	public UnitInfo getModel() {
		 
		return unitInfo;
	}
	/**
	 * 查询单位信息
	 * 
	 * @return UnitInfo
	 * 
	 */

	public String query() {
		unitInfo = unitInfoService.query();
		sysNum = systemService.count(null);

		sysSecNum = rankService.queryByGrade("第二级");
		systhrNum = rankService.queryByGrade("第三级");
		systhiNum = rankService.queryByGrade("第四级");

		return SUCCESS;

	}
	/**
	 * 单位信息编辑
	 * 
	 * @return String
	 */
	public String edit() {

		unitInfo = unitInfoService.query();
		sysNum = systemService.count(null);

		sysSecNum = rankService.queryByGrade("第二级");
		systhrNum = rankService.queryByGrade("第三级");
		systhiNum = rankService.queryByGrade("第四级");

		return SUCCESS;
	}

	/**
	 * 修改单位信息
	 * 
	 * @return
	 */
	public String update() {
		HttpServletRequest request = super.getRequest();
		String unitName = request.getParameter("unitName");
		int unitId = 0;
		String tt = null;
		tt = request.getParameter("id");
		if (tt != null && !tt.equals("")) {
			unitId = Integer.parseInt(tt.toString());
		}
		unitInfo.setId(unitId);
		unitInfo.setUnitName(unitName);
		unitInfo.setProvince(request.getParameter("province").toString());
		unitInfo.setCity(request.getParameter("city").toLowerCase());
		unitInfo.setCounty(request.getParameter("county").toString());
		String postcode = null;
		postcode = request.getParameter("postcode");
		if (postcode != null && !postcode.equals("")) {
			unitInfo.setPostcode(postcode);
		}
		unitInfo.setDivisionCode(request.getParameter("divisionCode")
				.toString());
		unitInfo.setUnitLeader(request.getParameter("unitLeader").toString());
		unitInfo.setDuty(request.getParameter("duty").toString());
		unitInfo.setUnitTel(request.getParameter("unitTel").toString());
		unitInfo.setUnitEmail(request.getParameter("unitEmail".toString()));
		unitInfo.setUnitDep(request.getParameter("unitDep").toString());
		unitInfo.setDepContact(request.getParameter("depContact").toString());
		unitInfo.setDepDuty(request.getParameter("depDuty").toString());
		String depTel = null;
		depTel = request.getParameter("depTel");
		if (depTel != null && !depTel.equals("")) {
			unitInfo.setDepTel(depTel);
		}
		String depMobile = null;
		depMobile = request.getParameter("depMobile");
		if (depMobile != null && !depMobile.equals("")) {
			unitInfo.setDepMobile(depMobile);
		}
		unitInfo.setDepEmail(request.getParameter("depEmail").toString());
		if (request.getParameter("subordinate") != null
				&& !request.getParameter("subordinate").equals("")) {
			unitInfo.setSubordinate(request.getParameter("subordinate")
					.toString());
		}
		String otherSub = request.getParameter("otherSub");
		if (request.getParameter("otherSub") != null
				&& !request.getParameter("otherSub").equals("")) {
			unitInfo.setOtherSub(request.getParameter("otherSub").trim()
					.toString());
		}
		if (request.getParameter("unitType") != null
				&& !request.getParameter("unitType").equals("")) {
			unitInfo.setUnitType(request.getParameter("unitType").toString());
		}
		if (request.getParameter("otherUnitType") != null
				&& !request.getParameter("otherUnitType").equals("")) {
			unitInfo.setOtherUnitType(request.getParameter("otherUnitType")
					.toString());
		}
		if (request.getParameter("employment") != null
				&& !request.getParameter("employment").equals("")) {
			unitInfo.setEmployment(request.getParameter("employment")
					.toString());
		}
		if (request.getParameter("otherEmp") != null
				&& !request.getParameter("otherEmp").equals("")) {
			unitInfo.setOtherEmp(request.getParameter("otherEmp").toString());
		}
		String sysTotal = null;
		sysTotal = request.getParameter("sysTotal");
		if (sysTotal != null && !sysTotal.equals("")) {
			unitInfo.setSysTotal(sysTotal);
		}
		
		if (unitId == 0) {
			unitInfoService.insert(unitInfo);
//			Audit audit = new Audit();
//			audit.setName((String) super.getSession().getAttribute("SSI_LOGIN_USER"));
//			audit.setObject("单位信息");
//			audit.setDetailed("单位信息");
//			audit.setType("添加");
//			audit.setTime(DateUtil.curDateTimeStr19());
//			auditService.instert(audit);
		} else {
			unitInfoService.update(unitInfo);
			/*Audit audit = new Audit();
			audit.setName((String) super.getSession().getAttribute("SSI_LOGIN_USER"));
			audit.setObject("单位信息");
			audit.setDetailed("单位信息");
			audit.setType("修改");
			audit.setTime(DateUtil.curDateTimeStr19());
			auditService.instert(audit);*/
		}
		sysNum = systemService.count(null);

		sysSecNum = rankService.queryByGrade("第二级");
		systhrNum = rankService.queryByGrade("第三级");
		systhiNum = rankService.queryByGrade("第四级");

		return SUCCESS;
	}

	public UnitInfoService getUnitInfoService() {
		return unitInfoService;
	}

	public void setUnitInfoService(UnitInfoService unitInfoService) {
		this.unitInfoService = unitInfoService;
	}

	public UnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	public int getSysNum() {
		return sysNum;
	}

	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}

	public int getSysSecNum() {
		return sysSecNum;
	}

	public void setSysSecNum(int sysSecNum) {
		this.sysSecNum = sysSecNum;
	}

	public int getSysthrNum() {
		return systhrNum;
	}

	public void setSysthrNum(int systhrNum) {
		this.systhrNum = systhrNum;
	}

	public int getSysthiNum() {
		return systhiNum;
	}

	public void setSysthiNum(int systhiNum) {
		this.systhiNum = systhiNum;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

 

}
