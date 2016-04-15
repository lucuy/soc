package com.compliance.service.cpManage.rectificationProposal.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.assessResult.AssessResultDao;
import com.compliance.dao.cpManage.demand.DemandColletDao;
import com.compliance.dao.cpManage.rectificationProposal.RectificationProposalDao;
import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.compliance.service.cpManage.rectificationProposal.RectificationProposalService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RectificationProposalServiceImpl extends BaseServiceImpl implements RectificationProposalService{

	private  DemandColletDao demandColletDao;
	private  AssessResultDao assessResultDao;
	private RectificationProposalDao rectificationProposalDao;
	
	@SuppressWarnings("rawtypes")
	public SearchResult query(Map map, Page page) {
		String ByFK_CA = map.get("FK_CA")+"".toString();
        //调用高阳接口查询不符合项数进行分页操作
        /* int rowCount = assessResultDao.count(map);
         page.setTotalCount(rowCount);*/
         //调用差距评估模块接口查询不符合项内容
         List<AssessResult> assessResults = assessResultDao.assessResults(map, 0, 1000);
		//将查询到的结果以list集合的形式进行返回
		List<RectificationProposal> demandCollets = new ArrayList<RectificationProposal>();
		//通过使用foreach循环遍历assessResults结果，并将该结果信息封装到RectificationProposal模块，并将封装的实体类添加到demandCollets list集合中
		RectificationProposal rectificationProposal;
		for (AssessResult assessResult : assessResults) {
			//添加需要整改的整改项
			String CIA_ACon  = "";
		    String CIA_BCon  = "";
		    String CIA_CCon  = "";
		    String CIA_DCon  = "";
		    String CIA_ECon  = "";
		    String CIA_FCon  = "";
		    String CIA_GCon  = "";
		    String CIA_HCon  = "";
		    String CIA_ICon  = "";
		    String CIA_JCon  = "";
		  //根据评估项的评估结果查询该评估项内容
		    if(null!=assessResult.getCIA_A()){
		    	if(assessResult.getCIA_A().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           //根据评估单元编号与评估项名称查询出该评估项内容
		    		CIA_ACon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.a");
		    		rectificationProposal.setCORRRECOM_Content("a)"+CIA_ACon);
		    		  //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.a");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_B()){
		    	if(assessResult.getCIA_B().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
				    //根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_BCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.b");
				    rectificationProposal.setCORRRECOM_Content("b)"+CIA_BCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.b");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
				}
		    }
		    if(null!=assessResult.getCIA_C()){
		    	if(assessResult.getCIA_C().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_CCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.c");
				    rectificationProposal.setCORRRECOM_Content("c)"+CIA_CCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.c");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_D()){
		    	if(assessResult.getCIA_D().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_DCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.d");
				    rectificationProposal.setCORRRECOM_Content("d)"+CIA_DCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.d");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_E()){
		    	if(assessResult.getCIA_E().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_ECon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.e");
				    rectificationProposal.setCORRRECOM_Content("e)"+CIA_ECon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.e");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_F()){
		    	if(assessResult.getCIA_F().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_FCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.f");
				    rectificationProposal.setCORRRECOM_Content("f)"+CIA_FCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.f");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_G()){
		    	if(assessResult.getCIA_G().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_GCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.g");
				    rectificationProposal.setCORRRECOM_Content("g)"+CIA_GCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.g");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_H()){
		    	if(assessResult.getCIA_H().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_HCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.h");
				    rectificationProposal.setCORRRECOM_Content("h)"+CIA_HCon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.h");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_I()){
		    	if(assessResult.getCIA_I().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		           
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_ICon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.i");
				    rectificationProposal.setCORRRECOM_Content("i)"+CIA_ICon);
				    //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.i");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
		    if(null!=assessResult.getCIA_J()){
		    	if(assessResult.getCIA_J().equals("0")){
		    		rectificationProposal = new RectificationProposal();
					//评估域排序
					rectificationProposal.setCORRRECOM_ControlDomainSort(assessResult.getCIA_Sort().substring(0, 3) );
					//评估域名称
					rectificationProposal.setCORRRECOM_ControlDomainName(demandColletDao.queryUnitDomainNameByNum( assessResult.getCIA_Sort().substring(0, 3)));
					//评估单元排序
					rectificationProposal.setCORRRECOM_ControlUnitSort(assessResult.getCIA_Sort().substring(0, 5));
					//评估单元名称
					rectificationProposal.setCORRRECOM_ControlUnitName(demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5)));
		    		//根据评估单元编号与评估项名称查询出该评估项内容
				    CIA_JCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort()+".2.j");
				    rectificationProposal.setCORRRECOM_Content("j)"+CIA_JCon);
				  //根据评估系统id以及评估项编号，查询该评估项的整改建议
				    Map<String, String> queryCorrrecomMap = new HashMap<String, String>();
				    queryCorrrecomMap.put("CORRRECOM_SysId", ByFK_CA);
				    queryCorrrecomMap.put("CORRRECOM_ItemNumber", assessResult.getCIA_Sort().trim()+".2.j");
				    rectificationProposal.setCORRRECOM_Advise(rectificationProposalDao.queryByIdAndItemNumber(queryCorrrecomMap));
				    rectificationProposal.setCORRRECOM_Date(rectificationProposalDao.queryCreatDateByIdAndItemNumber(queryCorrrecomMap));
				    demandCollets.add(rectificationProposal);
		    	}
		    }
			
		}
		
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(demandCollets);
		//sr.setPage(page);
		return sr;
	}
	/**
	 * 添加整改建议项
	 */
	public void insterProposal(RectificationProposal proposal) {
		rectificationProposalDao.insterProposa(proposal);
	}
	/**
	 *  修改整改建议
	 */
	public void updataProposal(RectificationProposal proposal) {
		rectificationProposalDao.updataProposal(proposal);
		
	}
	
	/**
	 * 查询评估项个数
	 */
	public int queryRectNum(Map map){
		return rectificationProposalDao.queryRectNum(map);
	}
	
	/**
	 * 删除整改建议
	 * @return
	 */
	
	public void deleteProposal(Map map) {
		rectificationProposalDao.deleteProposal(map);
		
	}
	public DemandColletDao getDemandColletDao() {
		return demandColletDao;
	}
	public void setDemandColletDao(DemandColletDao demandColletDao) {
		this.demandColletDao = demandColletDao;
	}
	public AssessResultDao getAssessResultDao() {
		return assessResultDao;
	}
	public void setAssessResultDao(AssessResultDao assessResultDao) {
		this.assessResultDao = assessResultDao;
	}
	public RectificationProposalDao getRectificationProposalDao() {
		return rectificationProposalDao;
	}
	public void setRectificationProposalDao(
			RectificationProposalDao rectificationProposalDao) {
		this.rectificationProposalDao = rectificationProposalDao;
	}



	
}
