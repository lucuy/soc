package com.compliance.webapp.action.basicinfo.system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.about.Serial;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.rank.RankService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class SystemAction extends BaseAction {
	private SystemService systemService;
	private RankService rankService;
	private List<SystemManager> list;
	/* private AuditService auditService; */
	// private List<Pro_Line> proList;
	private SystemManager system;
	private int pageindex;
	private String ids;
	private int count;
	private String keyword;
	Map map = new HashMap();
	// 高级搜索信息系统名称字段
	private String sysNames;
	// 高级搜索信息系统编号字段
	private String sysID;

	/**
	 * 查询系统信息列表
	 * 
	 * @return
	 */
	public String query() {
		HttpServletRequest request = super.getRequest();
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
		String keyword = this.getRequest().getParameter("keyword");
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		SearchResult sr = systemService.query(map, page);
		list = (List<SystemManager>) sr.getList();
		request.setAttribute("Page", sr.getPage());
		return SUCCESS;
	}

	public String queryScore() {
		Page page = this.getPage();
		String sysName = this.getRequest().getParameter("searchSysName");
		if (!"".equals(sysName) || sysName != null) {
			map.put("keyword", sysName);
		}
		count = this.getAllCount(map);
		page.setTotalCount(count);
		// proList=systemService.queryForPageScore(map, page.getStartIndex(),
		// page.DEFAULT_PAGE_SIZE);
		this.getRequest().setAttribute("current", pageindex);
		this.getRequest().setAttribute("pagecount", page.getPageCount());
		if ("updaterank".equals(this.getRequest().getParameter("method"))) {
			return "updaterank";
		}
		return SUCCESS;
	}

	public Page getPage() {
		Page page = null;
		if (pageindex == 0 || pageindex == 1) // 第一次进入此界面或点击首页进入
		{
			pageindex = 1;
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		} else {

			page = new Page(Page.DEFAULT_PAGE_SIZE, pageindex
					* Page.DEFAULT_PAGE_SIZE - Page.DEFAULT_PAGE_SIZE);
		}
		return page;
	}

	public int getAllCount(Map map) {
		return systemService.count(map);
	}

	public String queryForGoHead() {
		String id = this.getRequest().getParameter("id");
		SystemManager system = systemService.queryById(Integer.parseInt(id));
		this.getRequest().setAttribute("system", system);
		return SUCCESS;
	}

	// 添加信息系统
	public String insert() {
		systemService.insert(system);

		/*
		 * Audit audit = new Audit(); audit.setName((String)
		 * super.getSession().getAttribute("SSI_LOGIN_USER"));
		 * audit.setObject("信息系统管理"); audit.setDetailed(system.getSysName());
		 * audit.setType("添加信息系统"); audit.setTime(DateUtil.curDateTimeStr19());
		 * auditService.instert(audit);
		 */
		// systemService.insertProLine(system);
		// String sysmanager=system.getSysId();
		// systemService.insertRecord(sysId);
		rankService.insert(system);
		return SUCCESS;
	}

	public String queryById() {
		String system_id = this.getRequest().getParameter("id");

		int id = Integer.parseInt(system_id);
		SystemManager system = systemService.queryById(id);
		this.getRequest().setAttribute("system", system);
		return SUCCESS;
	}

	public void queryByName() {
		String system_id = this.getRequest().getParameter("sysName");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword1", system_id);
		Integer count = systemService.countPrecise(map);
		// Ajax返回
		try {
			getResponse().getWriter().write(String.valueOf(count));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String delete() // 删除选中的
	{
		String[] id = ids.split(",");
		for (String temp : id) {
			if (StringUtil.isNotBlank(temp)) {
				int te = Integer.parseInt(temp);

				systemService.delete(te);
				rankService.delete(te);
			}
		}
		this.query();
		return SUCCESS;
	}

	public String update() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		systemService.update(system);
		/*
		 * Audit audit = new Audit(); audit.setName((String)
		 * super.getSession().getAttribute("SSI_LOGIN_USER"));
		 * audit.setObject("信息系统管理"); audit.setDetailed(system.getSysName());
		 * audit.setType("修改信息系统"); audit.setTime(DateUtil.curDateTimeStr19());
		 * auditService.instert(audit);
		 */
		this.query();
		return SUCCESS;
	}

	/**
	 * ajax数据分页
	 */
	public void queryAjaxSystem() {

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
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		int totCount = systemService.countPrecise(null);
		int resourceNum = Serial.SERIAL_RESOURCE_NUM;
		SearchResult sr = systemService.query(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"
							+ jsonObject.toString() + ",\"totCount\":"
							+ String.valueOf(totCount) + ",\"resourceNum\":"
							+ String.valueOf(resourceNum) + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void queryAjaxSysId() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");

		String sysId = request.getParameter("sysId");
		SystemManager sm = null;
		try {
			if (StringUtil.isNotBlank(sysId)) {
				sm = systemService.queryBySysId(sysId);
			}
			if (null == sm) {
				response.getWriter().write("SUCCESS");
			} else {
				response.getWriter().write("ERROR");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	// 高级搜索信息系统
	public void queryExtSystem() {
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
		if (request.getParameter("sysNames") != null) {
			try {
				sysNames = java.net.URLDecoder.decode(sysNames, "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", sysNames);
		}
		if (request.getParameter("sysID") != null) {
			try {
				sysID = java.net.URLDecoder.decode(sysID, "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysID);
		}
		SearchResult sr = systemService.queryPrecise(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"
							+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/*
	 * public List<Pro_Line> getProList() { return proList; } public void
	 * setProList(List<Pro_Line> proList) { this.proList = proList; }
	 */
	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {

		this.count = count;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SystemManager getSystem() {
		return system;
	}

	public void setSystem(SystemManager system) {
		this.system = system;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public List<SystemManager> getList() {
		return list;
	}

	public void setList(List<SystemManager> list) {
		this.list = list;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getSysNames() {
		return sysNames;
	}

	public void setSysNames(String sysNames) {
		this.sysNames = sysNames;
	}

	public String getSysID() {
		return sysID;
	}

	public void setSysID(String sysID) {
		this.sysID = sysID;
	}

	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	/*
	 * public AuditService getAuditService() { return auditService; }
	 * 
	 * public void setAuditService(AuditService auditService) {
	 * this.auditService = auditService; }
	 */

}
