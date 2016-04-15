package com.compliance.webapp.action.cpManage.demand;

/**
 * 整改需求action类
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.Arithmetic;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.model.cpManage.technology.Technology;
import com.compliance.service.cpManage.assessResult.AssessResultService;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.cpManage.demand.DemandColletService;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class DemandColletAction extends BaseAction implements ModelDriven<DemandCollet>{
	private DemandColletService DemandColletService;
	private TechnologyService technologyService;
	private ArithmeticService arithmeticService;
	private AssessResultService assessResultService;
	private String keyword;
	private List<DemandCollet> demandCollets;
	private List<Technology> technologies;
	private DemandCollet demandCollet;
	private List<DemandCollet> demandList;
	private PageModel pageModell;

	public DemandCollet getModel() {
		return demandCollet;
	}

	/**
	 * 整改需求汇总左侧导航列表
	 * 
	 * @return
	 */

	@SuppressWarnings("unused")
	public void queryTree() {
		log.info("queryTree demandCollet info...");
		HttpServletRequest request = super.getRequest();
		try {
			technologies = technologyService.queryMaxEndTime();
			if (null != technologies) {
				JSONArray jsonArray = JSONArray.fromObject(technologies);
				getResponse().getWriter().write(jsonArray.toString());
			}
		} catch (Exception e) {
			log.error(DemandColletAction.class, e);
		}
		return;
	}

	/**
	 * 查询整改需求汇总查询列表
	 * 
	 * @return
	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public String queryDemand() {
//		log.info("queryList technology info...");
//		  String FK_CA = super.getRequest().getParameter("FK_CA").trim();
//		    super.getRequest().setAttribute("FK_CA", FK_CA);
//		    String CIA_AssessResult = super.getRequest().getParameter("CIA_AssessResult").trim();
//		    super.getRequest().setAttribute("CIA_AssessResult", CIA_AssessResult);
//		HttpServletRequest request = super.getRequest();
//		// 接受查询条件
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (FK_CA != null) {
//			map.put("FK_CA", FK_CA);
//		}
//		if (CIA_AssessResult != null) {
//			map.put("CIA_AssessResult", CIA_AssessResult);
//		}
//		demandCollets = DemandColletService.query(map);
//		PageModel pm = new PageModel(demandCollets, 15);
//		// 处理数据分页的起始条数
//				String startIndex = request.getParameter("page");
//				if (StringUtil.isNotBlank(startIndex)) {
//					demandCollets =pm.getObjectLists(Integer.valueOf(startIndex)); 
//				} else {
//					demandCollets =pm.getObjectLists(1); 
//				}
//		request.setAttribute("Page", pm);
//		return SUCCESS;
//
//	}
// 
	
	
	
	@SuppressWarnings({ "unchecked" })
	public String queryDemand() {
		log.info("queryList technology info...");
		  String FK_CA = super.getRequest().getParameter("FK_CA").trim();
		    super.getRequest().setAttribute("FK_CA", FK_CA);
		    String CIA_AssessResult = super.getRequest().getParameter("CIA_AssessResult").trim();
		    super.getRequest().setAttribute("CIA_AssessResult", CIA_AssessResult);
		HttpServletRequest request = super.getRequest();
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (FK_CA != null) {
			map.put("FK_CA", Integer.parseInt(FK_CA));
		}
		if (CIA_AssessResult != null) {
			map.put("CIA_AssessResult", CIA_AssessResult);
		}
		demandCollets = DemandColletService.query(map);
		PageModel pm = new PageModel(demandCollets, 15);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("page");
				if (StringUtil.isNotBlank(startIndex)) {
					demandCollets =pm.getObjectLists(Integer.valueOf(startIndex)); 
				} else {
					demandCollets =pm.getObjectLists(1); 
				}
		request.setAttribute("Page", pm);
		return SUCCESS;

	}
 
	
	/**
	 * ajax查询整改需求列表
	 * @throws UnsupportedEncodingException 
	 */
	public void queryAjaxDemand() throws UnsupportedEncodingException {
		String FK_CA = super.getRequest().getParameter("FK_CA").trim();
		super.getRequest().setAttribute("FK_CA", FK_CA);
		String CIA_AssessResult = super.getRequest().getParameter("CIA_AssessResult").trim();
		super.getRequest().setAttribute("CIA_AssessResult", CIA_AssessResult);
		String key=super.getRequest().getParameter("keyword").trim();
		keyword = java.net.URLDecoder.decode(key, "UTF-8");
		HttpServletRequest request = super.getRequest();
		super.getResponse().setCharacterEncoding("UTF-8");
		Page page = null;
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (FK_CA != null) {
			map.put("FK_CA", Integer.parseInt(FK_CA));
		}
		if (CIA_AssessResult != null) {
			map.put("CIA_AssessResult", CIA_AssessResult);
		} 
		if(keyword!=null){
			map.put("keyword", keyword);
		}
		SearchResult sr = DemandColletService.queryDemand(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			//System.out.println("{\"demandCollets\":" + jsonArray.toString()+ ",\"page\":" + jsonObject.toString() + "}");
			getResponse().getWriter().write("{\"demandCollets\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}
	
	
	/**
	 * ajax查询整改需求列表
	 * @throws UnsupportedEncodingException 
	 */
	public void queryNoKeyAjaxDemand() throws UnsupportedEncodingException {
		String FK_CA = super.getRequest().getParameter("FK_CA").trim();
		super.getRequest().setAttribute("FK_CA", FK_CA);
		String CIA_AssessResult = super.getRequest().getParameter("CIA_AssessResult").trim();
		super.getRequest().setAttribute("CIA_AssessResult", CIA_AssessResult);
		String key=super.getRequest().getParameter("keyword").trim();
		keyword = java.net.URLDecoder.decode(key, "UTF-8");
		HttpServletRequest request = super.getRequest();
		super.getResponse().setCharacterEncoding("UTF-8");
		Page page = null;
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(FK_CA)) {
			map.put("FK_CA", Integer.parseInt(FK_CA));
		}
		if (CIA_AssessResult != null) {
			map.put("CIA_AssessResult", CIA_AssessResult);
		} 
		if(keyword!=null){
			map.put("keyword", keyword);
		}
		SearchResult sr = DemandColletService.queryDemand(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			//System.out.println("{\"demandCollets\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
			getResponse().getWriter().write("{\"demandCollets\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * @author 杜高杨
	 * @Version 1.0
	 * @Created at 2013-05-18
	 * 
	 *  查询评估项树形结构
	 */
	public void queryGradeForTree() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		String acId = request.getParameter("acId");
		if (acId != null && acId != "") {
			Technology t = technologyService.queryById(Integer.parseInt(acId));
			if (t != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				String sysGrade = "";
				if("第二级".equals(t.getSysGrade())){
					map.put("sort", "6");
					sysGrade = "第二级";
				}else if ("第三级".equals(t.getSysGrade())) {
					map.put("sort", "7");
					sysGrade = "第三级";
				}else if ("第四级".equals(t.getSysGrade())) {
					map.put("sort", "8");
					sysGrade = "第四级";
				}else if ("第五级".equals(t.getSysGrade())) {
					map.put("sort", "9");
					sysGrade = "第五级";
				}else{
					map.clear();
				}
				List<DemandCollet> demandList = DemandColletService.queryBySortForTree(map);
				Map<String, Object> demandMap = new HashMap<String, Object>();
				demandMap.put("tree", demandList);
				demandMap.put("sysGrade", sysGrade);
				try {
					JSONObject jsonObject = JSONObject.fromObject(demandMap);
					response.getWriter().write(jsonObject.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}
	
	/**
	 * @author 杜高杨
	 * @Version 1.0
	 * @Created at 2013-05-18
	 * 
	 *  根据排序查询评估项
	 */
	public String querySortInfo() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		
		String acId = request.getParameter("acId");
		String sort = request.getParameter("sort");
		String cadname=DemandColletService.queryUnitDomainNameByNum(sort);
		int assessOverCount = assessResultService.queryAssessOverCount(acId);
		//System.out.println("当前进度============="+assessOverCount);
		//页面读取评估进度
		request.setAttribute("processnum", assessOverCount);
		if (sort != null && sort !="" && acId != null && acId != "") {
			request.setAttribute("acId", acId);
			request.setAttribute("sort", sort);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sort", sort);
			List<DemandCollet> demandColletList = DemandColletService.querySortInfo(map);
			List<DemandCollet> demandColletLi = new ArrayList<DemandCollet>();
			String str = "";
			for (DemandCollet demandCollet : demandColletList) {
				if(demandCollet.getUnitDomainName().contains(sort+".2")){
					char s = demandCollet.getUnitDomainName().trim().charAt(demandCollet.getUnitDomainName().trim().length()-1);
					if(str != ""){
						str += ","+String.valueOf(s);
					}else{
						str += String.valueOf(s);
					}
					Map<String,Object> assMap = new HashMap<String, Object>();
					assMap.put("acId", Integer.parseInt(acId));
					assMap.put("sort", sort);
				
					List<AssessResult>  asList = assessResultService.queryAssessCount(assMap);
					if(asList.size()==1){
						AssessResult ar = asList.get(0);
						if(s=='a')
							demandCollet.setCAD_ListGrade(ar.getCIA_A());
						if(s=='b')
							demandCollet.setCAD_ListGrade(ar.getCIA_B());
						if(s=='c')
							demandCollet.setCAD_ListGrade(ar.getCIA_C());
						if(s=='d')
							demandCollet.setCAD_ListGrade(ar.getCIA_D());
						if(s=='e')
							demandCollet.setCAD_ListGrade(ar.getCIA_E());
						if(s=='f')
							demandCollet.setCAD_ListGrade(ar.getCIA_F());
						if(s=='g')
							demandCollet.setCAD_ListGrade(ar.getCIA_G());
						if(s=='h')
							demandCollet.setCAD_ListGrade(ar.getCIA_H());
						if(s=='i')
							demandCollet.setCAD_ListGrade(ar.getCIA_I());
						if(s=='j')
							demandCollet.setCAD_ListGrade(ar.getCIA_J());
					}
				}
				demandColletLi.add(demandCollet);
			}
			demandList = demandColletLi;
			
			request.setAttribute("abcSort", str);
			Arithmetic arithmetic = arithmeticService.queryJsAlgBySort(sort);
			if(arithmetic!=null){
				request.setAttribute("JsAlg", arithmetic.getJsAlg());
			}
			String subSort = String.valueOf(sort.trim().substring(0, 1));
			map.clear();
			map.put("sort", subSort);
			int countSort = DemandColletService.queryNextSortInfo(map);
			//int nextSort = Integer.parseInt(String.valueOf(sort.trim().charAt(sort.trim().length()-1)));
			List<DemandCollet> demandList =	DemandColletService.queryNextSort(map);
			int index = 0;
			for (DemandCollet demandCollet : demandList) {
				if(demandCollet.getUnitDomainName().equals(sort.trim())){
					 index =  demandList.indexOf(demandCollet);
				}
			}
			if(countSort == index+1){
				request.setAttribute("nextSort", "over");
			}else{
				DemandCollet dc = demandList.get(index+1);
				request.setAttribute("nextSort", dc.getUnitDomainName());
			}
			Map<String,Object> asMap = new HashMap<String, Object>();
			asMap.put("acId", Integer.parseInt(acId));
			asMap.put("sort", sort);
		
			List<AssessResult>  asList = assessResultService.queryAssessCount(asMap);
			if(asList.size()==1){
				request.setAttribute("assessResult", asList.get(0));
			}
		}
		request.setAttribute("cadname", cadname);
		return SUCCESS;
	}

	public DemandColletService getDemandColletService() {
		return DemandColletService;
	}

	public void setDemandColletService(DemandColletService demandColletService) {
		DemandColletService = demandColletService;
	}

	public List<DemandCollet> getDemandCollets() {
		return demandCollets;
	}

	public void setDemandCollets(List<DemandCollet> demandCollets) {
		this.demandCollets = demandCollets;
	}

	public DemandCollet getDemandCollet() {
		return demandCollet;
	}

	public void setDemandCollet(DemandCollet demandCollet) {
		this.demandCollet = demandCollet;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public TechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public List<DemandCollet> getDemandList() {
		return demandList;
	}

	public void setDemandList(List<DemandCollet> demandList) {
		this.demandList = demandList;
	}

	public ArithmeticService getArithmeticService() {
		return arithmeticService;
	}

	public void setArithmeticService(ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}

	public AssessResultService getAssessResultService() {
		return assessResultService;
	}

	public void setAssessResultService(AssessResultService assessResultService) {
		this.assessResultService = assessResultService;
	}

	public PageModel getPageModell() {
		return pageModell;
	}

	public void setPageModell(PageModel pageModell) {
		this.pageModell = pageModell;
	}

}
