package com.compliance.service.cpManage.demand.impl;

/**
 * 整改需求汇总service实现层
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.assessResult.AssessResultDao;
import com.compliance.dao.cpManage.demand.DemandColletDao;
import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.service.cpManage.demand.DemandColletService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DemandColletServiceImpl extends BaseServiceImpl implements DemandColletService {
	private DemandColletDao demandColletDao;
	private AssessResultDao assessResultDao;
	
	
	
	public String queryUnitDomainNameByNum(String unitDomainName) {
		return demandColletDao.queryUnitDomainNameByNum(unitDomainName);
	}

	/**
	 * 根据技术差距评估木块提供的接口得到基础差距评估结果数据，gatTechnologyByFK_CAAndCIA_AssessResult(String ByFK_CA,String CIA_AssessResult);
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> query(Map map) {
//		String ByFK_CA = (String) map.get("FK_CA");
//		String CIA_AssessResult = (String) map.get("CIA_AssessResult");
		// 调用高阳接口查询
		//int rowCount = assessResultDao.count(map);
		//page.setTotalCount(rowCount);
		//List<AssessResult> assessResults = assessResultDao.assessResults(map, page.getStartIndex(), page.getPageSize());
		List<AssessResult> assessResults = assessResultDao.assessResults(map, 0, 100);
		List<DemandCollet> demandCollets = new ArrayList<DemandCollet>();
		DemandCollet demandCollet;

		for (AssessResult assessResult : assessResults) {

			demandCollet = new DemandCollet();
			// 根据排序名在测评表中查出评估域名称与评估单元名称
			String unitDomainName = assessResult.getCIA_Sort().substring(0, 3) + demandColletDao.queryUnitDomainNameByNum(assessResult.getCIA_Sort().substring(0, 3));
			demandCollet.setUnitDomainName(unitDomainName);
			String unitName = assessResult.getCIA_Sort().substring(0, 5) + demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5));
			demandCollet.setUnitName(unitName);
			// 将该评估单元的信息放到list集合中
			@SuppressWarnings("rawtypes")
			List cons = new ArrayList();
			// 对con集合中的数据进行编号
			String CIA_ACon = "";
			String CIA_BCon = "";
			String CIA_CCon = "";
			String CIA_DCon = "";
			String CIA_ECon = "";
			String CIA_FCon = "";
			String CIA_GCon = "";
			String CIA_HCon = "";
			String CIA_ICon = "";
			String CIA_JCon = "";
			// 根据评估项的评估结果查询该评估项内容
			if (null != assessResult.getCIA_A()) {
				if (assessResult.getCIA_A().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ACon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.a");
					cons.add("a)" + CIA_ACon.trim());
				}
			}
			if (null != assessResult.getCIA_B()) {
				if (assessResult.getCIA_B().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_BCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.b");
					cons.add("b)" + CIA_BCon.trim());
				}
			}
			if (null != assessResult.getCIA_C()) {
				if (assessResult.getCIA_C().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_CCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.c");
					cons.add("c)" + CIA_CCon.trim());
				}
			}
			if (null != assessResult.getCIA_D()) {
				if (assessResult.getCIA_D().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_DCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.d");
					cons.add("d)" + CIA_DCon.trim());
				}
			}
			if (null != assessResult.getCIA_E()) {
				if (assessResult.getCIA_E().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ECon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.e");
					cons.add("e)" + CIA_ECon.trim());
				}
			}
			if (null != assessResult.getCIA_F()) {
				if (assessResult.getCIA_F().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_FCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.f");
					cons.add("f)" + CIA_FCon.trim());
				}
			}
			if (null != assessResult.getCIA_G()) {
				if (assessResult.getCIA_G().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_GCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.g");
					cons.add("g)" + CIA_GCon.trim());
				}
			}
			if (null != assessResult.getCIA_H()) {
				if (assessResult.getCIA_H().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_HCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.h");
					cons.add("h)" + CIA_HCon.trim());
				}
			}
			if (null != assessResult.getCIA_I()) {
				if (assessResult.getCIA_I().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ICon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.i");
					cons.add("i)" + CIA_ICon.trim());
				}
			}
			if (null != assessResult.getCIA_J()) {
				if (assessResult.getCIA_J().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_JCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.j");
					cons.add("j)" + CIA_JCon.trim());
				}
			}
			demandCollet.setList(cons);
			demandCollets.add(demandCollet);
		}
		// 处理分页
	/*	SearchResult sr = new SearchResult();
		sr.setList(demandCollets);
		sr.setPage(page);*/
		return demandCollets;

	}
	/**
	 * 整改需求汇总搜索功能
	 */
	@SuppressWarnings("unchecked")
	public SearchResult queryDemand(Map map, Page page) {
		// 调用高阳接口查询
		List<AssessResult> assessResults = assessResultDao.assessResults(map, 0, 1000);
		List<DemandCollet> demandCollets = new ArrayList<DemandCollet>();
		List<DemandCollet> demandCollets2 = new ArrayList<DemandCollet>();
		List<DemandCollet> demandColletsPage = new ArrayList<DemandCollet>();
		
		DemandCollet demandCollet;
		DemandCollet demandCollet2;
		for (AssessResult assessResult : assessResults) {
			demandCollet = new DemandCollet();
			// 根据排序名在测评表中查出评估域名称与评估单元名称
			String unitDomainName = assessResult.getCIA_Sort().substring(0, 3) + demandColletDao.queryUnitDomainNameByNum(assessResult.getCIA_Sort().substring(0, 3));
			demandCollet.setUnitDomainName(unitDomainName);
			String unitName = assessResult.getCIA_Sort().substring(0, 5) + demandColletDao.queryUnitNameByNum(assessResult.getCIA_Sort().substring(0, 5));
			demandCollet.setUnitName(unitName);
			// 将该评估单元的信息放到list集合中
			@SuppressWarnings("rawtypes")
			List cons = new ArrayList();
			// 对con集合中的数据进行编号
			String CIA_ACon = "";
			String CIA_BCon = "";
			String CIA_CCon = "";
			String CIA_DCon = "";
			String CIA_ECon = "";
			String CIA_FCon = "";
			String CIA_GCon = "";
			String CIA_HCon = "";
			String CIA_ICon = "";
			String CIA_JCon = "";
			// 根据评估项的评估结果查询该评估项内容
			if (null != assessResult.getCIA_A()) {
				if (assessResult.getCIA_A().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ACon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.a");
					cons.add("a)" + CIA_ACon.trim());
				}
			}
			if (null != assessResult.getCIA_B()) {
				if (assessResult.getCIA_B().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_BCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.b");
					cons.add("b)" + CIA_BCon.trim());
				}
			}
			if (null != assessResult.getCIA_C()) {
				if (assessResult.getCIA_C().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_CCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.c");
					cons.add("c)" + CIA_CCon.trim());
				}
			}
			if (null != assessResult.getCIA_D()) {
				if (assessResult.getCIA_D().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_DCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.d");
					cons.add("d)" + CIA_DCon.trim());
				}
			}
			if (null != assessResult.getCIA_E()) {
				if (assessResult.getCIA_E().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ECon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.e");
					cons.add("e)" + CIA_ECon.trim());
				}
			}
			if (null != assessResult.getCIA_F()) {
				if (assessResult.getCIA_F().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_FCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.f");
					cons.add("f)" + CIA_FCon.trim());
				}
			}
			if (null != assessResult.getCIA_G()) {
				if (assessResult.getCIA_G().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_GCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.g");
					cons.add("g)" + CIA_GCon.trim());
				}
			}
			if (null != assessResult.getCIA_H()) {
				if (assessResult.getCIA_H().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_HCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.h");
					cons.add("h)" + CIA_HCon.trim());
				}
			}
			if (null != assessResult.getCIA_I()) {
				if (assessResult.getCIA_I().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_ICon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.i");
					cons.add("i)" + CIA_ICon.trim());
				}
			}
			if (null != assessResult.getCIA_J()) {
				if (assessResult.getCIA_J().equals("0")) {
					// 根据评估单元编号与评估项名称查询出该评估项内容
					CIA_JCon = demandColletDao.queryConByUnitName(assessResult.getCIA_Sort() + ".2.j");
					cons.add("j)" + CIA_JCon.trim());
				}
			}
			demandCollet.setList(cons);
			demandCollets.add(demandCollet);
		}
		
		for (DemandCollet demandColletone : demandCollets) {
			demandCollet2 = new DemandCollet();
			String a=demandColletone.getUnitDomainName();
			String b=demandColletone.getUnitName();
			List<String> listone=demandColletone.getList();
			String key=(String)map.get("keyword");
			if(a.contains(key))
			{
				demandCollet2.setUnitDomainName(a);
				demandCollet2.setUnitName(b);
				demandCollet2.setList(listone);
			}
			if(b.contains(key)){
				demandCollet2.setUnitDomainName(a);
				demandCollet2.setUnitName(b);
				demandCollet2.setList(listone);
			}
			
			for (String str : listone) {
				if(str.contains(key)){
					demandCollet2.setUnitDomainName(a);
					demandCollet2.setUnitName(b);
					demandCollet2.setList(listone);
				}
			}
			
			
			if(StringUtil.isNotBlank(demandCollet2.getUnitName())){
				demandCollets2.add(demandCollet2);
			}
				
		}
		// 处理分页
		SearchResult sr = new SearchResult();
		for (int i = 0; i < demandCollets2.size(); i++) {
			
			if((page.getStartIndex()<=i)&&(demandCollets2.get(i)!=null)&&(demandColletsPage.size()<page.getPageSize())){
				demandColletsPage.add(demandCollets2.get(i));
			}
		}
		sr.setList(demandColletsPage);
		page.setTotalCount(demandCollets2.size());
		sr.setPage(page);
		return sr;

	}
	/**
	 * 根据排序查找该对象
	 * @param String
	 * @return DemandCollet
	 */
	@SuppressWarnings("unchecked")
	public DemandCollet querydemandColletBySort(String sort){
		return demandColletDao.querydemandColletBySort(sort);
	}
	
	/**
	 * 差距分布图查询
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryAssessInfoImage(Map map){
		return demandColletDao.queryAssessInfoImage(map);
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

	/**
	 * 根据评估项排序得到评估项是树形结构
	 * 
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryBySortForTree(Map map) {
		return demandColletDao.queryBySortForTree(map);
	}
	
	/**
	 * 查询还有哪些项未评估
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryAssessInfo(Map map){
		return demandColletDao.queryAssessInfo(map);
	}
	
	
	
	
	public int queryNextSortInfo(Map map) {
		return demandColletDao.queryNextSortInfo(map);
	}
	
	public List<DemandCollet> queryNextSort(Map map) {
		return demandColletDao.queryNextSort(map);
	}

	public List<DemandCollet> querySortInfo(Map map) {
		return demandColletDao.querySortInfo(map);
	}
	
	
}
