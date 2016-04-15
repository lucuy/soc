package com.compliance.service.cpManage.contrastRectification.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.contrastRectification.ContrastDao;
import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.compliance.model.cpManage.technology.Technology;
import com.compliance.service.cpManage.contrastRectification.ContrastService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class ContrastServiceImpl extends BaseServiceImpl implements ContrastService {

	public ContrastDao contrastDao;
	/**
	 * 查询系统列表
	 */
	public List<RectificationProposal> auerySysList() {

		return contrastDao.querySysList();
	}
	public ContrastDao getContrastDao() {
		return contrastDao;
	}
	public void setContrastDao(ContrastDao contrastDao) {
		this.contrastDao = contrastDao;
	}
	 /**
	  * 查询列表页面
	  */
	@SuppressWarnings("rawtypes")
	public SearchResult query(Map<String, Object> map, Page page) {
		// 查询数据
				/*int rowCount = contrastDao.count(map);
				page.setTotalCount(rowCount);*/
				//遍历查询出数据库总的评估单元项
				List<RectificationProposal> UnitList = contrastDao.queryUnitList(map, 0, 10000);
				
				//将控制项与控制单元进行合并操作后保存到DataList集合中
				List <RectificationProposal> DataList = new ArrayList<RectificationProposal>();
				
				RectificationProposal proposal;
				for (RectificationProposal unitData : UnitList) {
					proposal = new RectificationProposal();
					proposal.setCORRRECOM_ControlDomainName(unitData.getCORRRECOM_ControlDomainSort()+":"+unitData.getCORRRECOM_ControlDomainName());
					proposal.setCORRRECOM_ControlUnitName(unitData.getCORRRECOM_ControlUnitSort()+":"+unitData.getCORRRECOM_ControlUnitName());
					//根据系统名称与评估单元编号查询评估项整改建议对比
					Map<String, String >iteamMap = new HashMap<String, String>();
					iteamMap.put("CORRRECOM_SysName", unitData.getCORRRECOM_SysName());
					iteamMap.put("CORRRECOM_ControlUnitSort", unitData.getCORRRECOM_ControlUnitSort());
					List <RectificationProposal>iteamList=contrastDao.queryItemList(iteamMap);
					proposal.setList(iteamList);
					DataList.add(proposal);
					 
				}
				
				
				// 处理分页
				SearchResult sr = new SearchResult();
				sr.setList(DataList);
				//sr.setPage(page);
				return sr;
	}
	
	/**
	 * 导出excel文档
	 */
	@SuppressWarnings("rawtypes")
	public List<RectificationProposal> query(Map<String, Object> map) {
		 
				//遍历查询出数据库总的评估单元项
				List<RectificationProposal> UnitList = contrastDao.queryUnitList(map);
				
				//将控制项与控制单元进行合并操作后保存到DataList集合中
				List <RectificationProposal> DataList = new ArrayList<RectificationProposal>();
				
				RectificationProposal proposal;
				for (RectificationProposal unitData : UnitList) {
					proposal = new RectificationProposal();
					proposal.setCORRRECOM_ControlDomainName(unitData.getCORRRECOM_ControlDomainSort()+":"+unitData.getCORRRECOM_ControlDomainName());
					proposal.setCORRRECOM_ControlUnitName(unitData.getCORRRECOM_ControlUnitSort()+":"+unitData.getCORRRECOM_ControlUnitName());
					//根据系统名称与评估单元编号查询评估项整改建议对比
					Map<String, String >iteamMap = new HashMap<String, String>();
					iteamMap.put("CORRRECOM_SysName", unitData.getCORRRECOM_SysName());
					iteamMap.put("CORRRECOM_ControlUnitSort", unitData.getCORRRECOM_ControlUnitSort());
					List <RectificationProposal>iteamList=contrastDao.queryItemList(iteamMap);
					proposal.setList(iteamList);
					DataList.add(proposal);
					 
				}
				return DataList;
	}
	
}
