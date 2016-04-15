package com.compliance.webapp.action.basicinfo.assets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.service.basicinfo.assets.impl.SoftUseServiceImpl;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.webapp.action.BaseAction;

import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SoftUseAction extends BaseAction {

	private SoftUseServiceImpl softUseServiceImpl;
	private List<BusinessAssets> resTypeList = null;
	private int softUseCount = 0;
	private String resType; // 资产种类
	private String keyword; // 快速搜索关键字
	// 高级搜索软件名称
	private String softName;
	// 高级搜索系统名称
	private String sysName;
	// 高级搜索软件功能描述
	private String softDescription;
	// 高级搜索重要程度
	private String impDegree;
	private BusinessAssets resType_t = new BusinessAssets(); // 信息资产类
	private String ids;
	private SystemService sysService;
	private List<SystemManager> sysList;
	private SystemManager sysMan = new SystemManager();

	
	public String queryName() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		try {
			softName = java.net.URLDecoder.decode(softName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		paraMap.put("keyword1", softName);
		int result = softUseServiceImpl.softPreciseCount(paraMap);
		// Ajax返回
		try {
			getResponse().getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	/**
	 * 添加或删除后跳转的action分页显示业务应用软件列表
	 * 
	 * @returnf
	 * */
	 	public String query() { 
	 		HttpServletRequest request = super.getRequest(); Page page = null; SearchResult sr = new
	         SearchResult(); String startIndex = request.getParameter("startIndex"); 
	         if (StringUtil.isNotBlank(startIndex)) { 
	        	 page = new  Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex)); 
	        	 }
	         else { 
	        	 page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
	        	 } 
	         Map mapper = new HashMap(); 
	         int softUseCount= softUseServiceImpl.softCount(mapper);
	         page.setTotalCount(softUseCount); 
	         List<BusinessAssets> list = softUseServiceImpl.query(mapper, page.getStartIndex(), page.getPageSize()); sr.setList(list); sr.setPage(page);
	          resTypeList = sr.getList(); 
	          if (sr != null) {
	           request.setAttribute("Page", sr.getPage()); 
	           } else {
	         request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0)); 
	         } return SUCCESS;
}

	/**
	 * 
	 * 添加业务应用软件信息
	 * 
	 * @return
	 */
	public String addSoftUse() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String impDegree = request.getParameter("impDegree");
		String mainFun = request.getParameter("mianFun");
		String resName = request.getParameter("resName");
		String remarks = request.getParameter("remarks");
		resType_t.setId(Integer.parseInt(id));
		resType_t.setImpDegree(impDegree);
		resType_t.setMianFun(mainFun);
		resType_t.setRemarks(remarks);
		resType_t.setRelsysName(relSysName);
		resType_t.setResName(resName);
		resType_t.setResType("业务应用软件");
		if (Integer.parseInt(id) == 0) {
			softUseServiceImpl.softInsert(resType_t);
		} else {
			softUseServiceImpl.softUpdate(resType_t);
		}
		
		return SUCCESS;
	}

	/**
	 * 修改业务应用软件信息
	 * 
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = super.getRequest();
		String id = null;
		id = request.getParameter("id");
		resType = request.getParameter("resType");

		if (id != null && Integer.parseInt(id) != 0) {
			resType_t = softUseServiceImpl.softQueryById(Integer.parseInt(id));

		}
		return SUCCESS;
	}

	
	
	public String queryAjaxSoft() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		this.getResponse().setCharacterEncoding("UTF-8");
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		String pageSize = request.getParameter("pageSize");
		if (StringUtil.isNotBlank(startIndex)) {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize), Integer.valueOf(startIndex));
			}else{
				page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			}
		} else {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize), 0);
			}else{
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			}
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
		map.put("resType", "业务应用软件");
		softUseCount = softUseServiceImpl.softCount(map);
		page.setTotalCount(softUseCount);
		List<BusinessAssets> list = softUseServiceImpl.query(map, page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		//List<ResType> resType_list = null;
		resTypeList = sr.getList();
		if(sr!=null)
		{
				request.setAttribute("Page", sr.getPage());
			
		}
		else
		{
				request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE,0));
		}
		return SUCCESS;
	}
	/**
	 * 删除业务应用软件
	 * 
	 * @return
	 */
	public String delete() {
		log.info("update employee status...");
		BusinessAssets businessAssets = new BusinessAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
				/*	String userName= (String)super.getSession().getAttribute("SSI_LOGIN_USER");
					Audit audit=new Audit();
					audit.setName(userName);
					audit.setObject("业务应用软件");
					businessAssets = softUseServiceImpl.softQueryById(Integer.parseInt(checkid));
					String resName=businessAssets.getResName();
					audit.setDetailed(resName);
					audit.setType("删除");
					String time=DateUtil.putDateToTimeStr19(new Date());
					audit.setTime(time);
					auditService.instert(audit);
					*/
					
					softUseServiceImpl.softDelete(Integer.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
				/*String userName= (String)super.getSession().getAttribute("SSI_LOGIN_USER");
				Audit audit=new Audit();
				audit.setName(userName);
				audit.setObject("业务应用软件");
				businessAssets = softUseServiceImpl.softQueryById(Integer.parseInt(ids));
				String resName=businessAssets.getResName();
				audit.setDetailed(resName);
				audit.setType("删除");
				String time=DateUtil.putDateToTimeStr19(new Date());
				audit.setTime(time);
				auditService.instert(audit);*/
				
				softUseServiceImpl.softDelete(Integer.parseInt(ids));
				// mailWebTreatyService.deleteById(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * 弹出框添加所属信息系统，弹出框的信息系统列表显示
	 * 
	 * @return
	 */
	public void queryAjaxSystem() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		this.getResponse().setCharacterEncoding("UTF-8");

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		String pageSize = request.getParameter("pageSize");

		if (StringUtil.isNotBlank(startIndex)) {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize),
						Integer.valueOf(startIndex));
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE,
						Integer.valueOf(startIndex));
			}
		} else {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize), 0);
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			}
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
		SearchResult sr = sysService.query(map, page);
		sysList = sr.getList();

		List<Map> mapSysList = new ArrayList<Map>();
		for (SystemManager m : sysList) {
			Map<String, Object> sysManMap = new HashMap<String, Object>();
			sysManMap.put("id", m.getId());
			sysManMap.put("sysName", m.getSysName());
			sysManMap.put("sysId", m.getSysId());
			sysManMap.put("busDescription", m.getBusDescription());
			mapSysList.add(sysManMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(mapSysList);
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

	/**
	 * 查询业务应用软件
	 * 
	 * @return
	 */
	public void  jsonAjaxSoft()
	{
		HttpServletRequest request = super.getRequest();
		Page page = null;
		this.getResponse().setCharacterEncoding("UTF-8");

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		String pageSize = request.getParameter("pageSize");
		
		if (StringUtil.isNotBlank(startIndex)) {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize), Integer.valueOf(startIndex));
			}else{
				page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			}
		} else {
			if (StringUtil.isNotBlank(pageSize)) {
				page = new Page(Integer.parseInt(pageSize), 0);
			}else{
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			}
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
		SearchResult sr = new SearchResult();
		softUseCount = softUseServiceImpl.softCount(map);
		page.setTotalCount(softUseCount);
		List<BusinessAssets> list = softUseServiceImpl.query(map, page.getStartIndex(), page.getPageSize());
		
		sr.setList(list);
		sr.setPage(page);
		//SearchResult sr = softUseServiceImpl.query(map, page);
		resTypeList = sr.getList();

		List<Map> mapSoftList = new ArrayList<Map>();
		for (BusinessAssets r : resTypeList) {
			Map<String, Object> resTypeMap = new HashMap<String, Object>();
			resTypeMap.put("id", r.getId());
			resTypeMap.put("resName", r.getResName());
			resTypeMap.put("sysName", r.getRelsysName());
			resTypeMap.put("mianFun", r.getMianFun());
			resTypeMap.put("impDegree", r.getImpDegree());
			mapSoftList.add(resTypeMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(mapSoftList);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * ajax查询分页
	 * 
	 * @return
	 */
	public void queryAjaxSoftAssets() {

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
		SearchResult sr = softUseServiceImpl.query(map, page);
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

	// 高级搜索业务应用软件
	public void extQuery() {

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
		if (request.getParameter("softName") != null) {
			try {
				softName = java.net.URLDecoder.decode(softName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", softName);
		}
		if (request.getParameter("sysName") != null) {
			try {
				sysName = java.net.URLDecoder.decode(sysName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysName);
		}
		if (request.getParameter("softDescription") != null) {
			try {
				softDescription = java.net.URLDecoder.decode(softDescription,
						"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", softDescription);
		}
		if (request.getParameter("impDegree") != null) {
			try {
				impDegree = java.net.URLDecoder.decode(impDegree, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword4", impDegree);
		}
		SearchResult sr = softUseServiceImpl.queryPreciseSoft(map, page);
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

	public SoftUseServiceImpl getSoftUseServiceImpl() {
		return softUseServiceImpl;
	}

	public void setSoftUseServiceImpl(SoftUseServiceImpl softUseServiceImpl) {
		this.softUseServiceImpl = softUseServiceImpl;
	}

	public List<BusinessAssets> getResTypeList() {
		return resTypeList;
	}

	public void setResTypeList(List<BusinessAssets> resTypeList) {
		this.resTypeList = resTypeList;
	}

	public int getSoftUseCount() {
		return softUseCount;
	}

	public void setSoftUseCount(int softUseCount) {
		this.softUseCount = softUseCount;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BusinessAssets getResType_t() {
		return resType_t;
	}

	public void setResType_t(BusinessAssets resType_t) {
		this.resType_t = resType_t;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SystemService getSysService() {
		return sysService;
	}

	public void setSysService(SystemService sysService) {
		this.sysService = sysService;
	}

	public List<SystemManager> getSysList() {
		return sysList;
	}

	public void setSysList(List<SystemManager> sysList) {
		this.sysList = sysList;
	}

	public SystemManager getSysMan() {
		return sysMan;
	}

	public void setSysMan(SystemManager sysMan) {
		this.sysMan = sysMan;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSoftDescription() {
		return softDescription;
	}

	public void setSoftDescription(String softDescription) {
		this.softDescription = softDescription;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}
 
}
