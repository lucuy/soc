package com.compliance.webapp.action.cpManage.technology;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.compliance.model.cpManage.technology.Technology;
import com.compliance.model.rank.Rank;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.service.rank.RankService;
import com.compliance.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * Description: 技术差距分析 Action
 * 
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 */

public class TechnologyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private TechnologyService technologyService;
	private RankService rankService;
	private List<Technology> technologyList;
	private String sysInfoId;

	/**
	 * 查询某定级系统评估列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryList() {
		log.info("queryList technology info...");
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("utf-8");
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
		String sysInfoId = request.getParameter("sysInfoId");
		if (sysInfoId != null) {
			try {
				sysInfoId = java.net.URLDecoder.decode(sysInfoId, "UTF-8");
				map.put("sysInfoId", sysInfoId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SearchResult sr = technologyService.query(map, page);

		technologyList = sr.getList();
		request.setAttribute("Page", sr.getPage());
		request.setAttribute("sysInfoId", sysInfoId);
		return SUCCESS;

	}

	/**
	 * 添加评估项时查询某系统定级信息
	 */
	public String queryBySysInfoId() {
		log.info("queryBySysInfoId technology info...");
		HttpServletRequest request = super.getRequest();

		String sysInfoId = request.getParameter("sysInfoId");
		Rank rank = null;
		if (sysInfoId != null) {
			try {
				sysInfoId = java.net.URLDecoder.decode(sysInfoId, "UTF-8");
				rank = rankService.queryBySysId(sysInfoId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("rank", rank);
		return SUCCESS;
	}

	/**
	 * 删除评估项
	 */
	public String deleteById() {

		String ids = super.getRequest().getParameter("ids");
		String sysInfoIds = super.getRequest().getParameter("sysInfoId");
	
		
		if (ids != null) {
			String[] checked = ids.split(",");
			for (String checkId : checked) {
				/**
				 * 添加审计
				 * @author hanyang
				 */
			/*	Audit a = new Audit();
				a.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
				a.setObject("技术差距分析");
				String  sysName = technologyService.queryById(Integer.parseInt(checkId)).getName();
				a.setDetailed(sysName);
				a.setTime(DateUtil.curDateTimeStr19());
				a.setType("删除");
				auditService.instert(a);*/
				technologyService.delete(Integer.parseInt(checkId));
			}
		}
		if (sysInfoIds != null) {
			super.getRequest().setAttribute("sysInfoId", sysInfoIds);
		}

		return SUCCESS;
	}

	/**
	 * 查询已定级系统列表
	 */
	public void queryTree() {
		log.info("queryTree technology info...");
		//HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);


		List<Rank> rankList = null;

		try {
			rankList = rankService.queRankFinish();
			if (null != rankList) {
				JSONArray jsonArray = JSONArray.fromObject(rankList);
				getResponse().getWriter().write(jsonArray.toString());
			}
		} catch (Exception e) {
			log.error(TechnologyAction.class, e);
		}
		return;
	}

	/*
	 * 添加评估项
	 */
	public String insert() {
		log.info("insert technology info...");
		HttpServletRequest request = super.getRequest();

		sysInfoId = request.getParameter("rankId");
		request.setAttribute("sysInfoId", sysInfoId);
		String name = request.getParameter("name");
		String sysGrade = request.getParameter("sysGrade");
		String description = request.getParameter("description");

		Technology t = new Technology();
		t.setName(name);
		t.setRankId(sysInfoId);
		t.setSysGrade(sysGrade);
		t.setDescribe(description);
		t.setStartTime(new Date());
		t.setCurrentState("0");
		technologyService.insert(t);
		
		/**
		 * 添加审计
		 * @author hanyang
		 */
	/*	Audit a = new Audit();
		a.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		a.setObject("技术差距分析");
		a.setDetailed(name);
		a.setTime(DateUtil.curDateTimeStr19());
		a.setType("添加");
		auditService.instert(a);*/
		return SUCCESS;
	}

	/*
	 * 保存后开始评估
	 */
	public void saveAssess() {
		log.info("saveAssess technology info...");
		HttpServletRequest request = super.getRequest();

		String sysInfoId = request.getParameter("rankId");
		String name = request.getParameter("name");
		String sysGrade = request.getParameter("sysGrade");
		String description = request.getParameter("description");

		Technology t = new Technology();
		t.setName(name);
		t.setRankId(sysInfoId);
		t.setSysGrade(sysGrade);
		t.setDescribe(description);
		t.setStartTime(new Date());
		t.setCurrentState("0");
		int id = 0;
		id = technologyService.insert(t);
		/**
		 * 添加审计
		 * @author hanyang
		 */
	/*	Audit a = new Audit();
		a.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		a.setObject("技术差距分析");
		a.setDetailed(name);
		a.setTime(DateUtil.curDateTimeStr19());
		a.setType("添加");
		auditService.instert(a);*/
		try {
			if (id != 0)
				getResponse().getWriter().write(String.valueOf(id));
			else
				getResponse().getWriter().write("error");
		} catch (Exception e) {
			log.error(TechnologyAction.class, e);
		}

		return;
	}

	public TechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	public List<Technology> getTechnologyList() {
		return technologyList;
	}

	public void setTechnologyList(List<Technology> technologyList) {
		this.technologyList = technologyList;
	}

	public String getSysInfoId() {
		return sysInfoId;
	}

	public void setSysInfoId(String sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

/*	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/
	
	
}
