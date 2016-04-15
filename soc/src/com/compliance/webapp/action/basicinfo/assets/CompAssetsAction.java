package com.compliance.webapp.action.basicinfo.assets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.assets.CompAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.CompAssetsService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CompAssetsAction extends BaseAction {
	
	
	private CompAssetsService compAssetsService;
	private int compAssetsCount = 0;
	private List<CompAssets> compAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private CompAssets compAssets = new CompAssets();
	private String ids;
	// 高级搜索设备名称字段
	private String devName;
	// 高级搜索所属系统名称
	private String sysName;
	// 高级搜索业务应用软件名称字段
	private String softName;

	/**
	 * 取消添加分页显示安全设备列表
	 * 
	 * @return
	 */
	public String query() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		Map mapper = new HashMap();
		compAssetsCount = compAssetsService.compAssetsCount(mapper);
		page.setTotalCount(compAssetsCount);
		List<CompAssets> list = compAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		compAssetsList = sr.getList();
		if (sr != null) {
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * 编辑界面
	 * 
	 */
	public String edit() {
		HttpServletRequest request = super.getRequest();
		String id = null;
		id = request.getParameter("id");
		if (id != null && Integer.parseInt(id) != 0) {
			compAssets = compAssetsService.compAssetsQueryById(Integer
					.parseInt(id));

		}
		return SUCCESS;
	}

	/**
	 * 
	 * 添加主机存储设备
	 * 
	 * @return
	 */
	public String addCompAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String devName = request.getParameter("devName");
		String resName = request.getParameter("resName");
		List<String> relresName = Arrays.asList(resName.split(";"));

		String compDescription = request.getParameter("compDescription");
		String compRemarks = request.getParameter("compRemarks");
		compAssets.setId(Integer.parseInt(id));
		compAssets.setRelsysName(relSysName);
		compAssets.setDevDescription(compDescription);
		compAssets.setDevName(devName);
		compAssets.setDevRemarks(compRemarks);
		compAssets.setRelresName(relresName);
		if (Integer.parseInt(id) == 0) {
			compAssetsService.compAssetsInsert(compAssets);
		} else {
			compAssetsService.compAssetsUpdate(compAssets);
		}
		
		
		return SUCCESS;
	}

	/**
	 * 
	 * 模糊搜索主机存储设备
	 * 
	 * @return
	 */
	public String queryCompAssets() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
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
		compAssetsCount = compAssetsService.compAssetsCount(map);
		page.setTotalCount(compAssetsCount);
		List<CompAssets> list = compAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		compAssetsList = sr.getList();
		if (sr != null) {
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * 删除(列表)
	 * 
	 * @return
	 */
	public String delete() {
		log.info("update employee status...");
		CompAssets compAssets1 = new CompAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
					
				/*	String userName= (String)super.getSession().getAttribute("SSI_LOGIN_USER");
					Audit audit=new Audit();
					audit.setName(userName);
					audit.setObject("主机存储设备");
					
					compAssets1 = compAssetsService.compAssetsQueryById(Integer
							.parseInt(checkid));
					String resName=compAssets1.getDevName();
					audit.setDetailed(resName);
					audit.setType("删除");
					String time=DateUtil.putDateToTimeStr19(new Date());
					audit.setTime(time);
					auditService.instert(audit);*/
					
					compAssetsService.compAssetsDelete(Integer
							.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
				/*String userName= (String)super.getSession().getAttribute("SSI_LOGIN_USER");
				Audit audit=new Audit();
				audit.setName(userName);
				audit.setObject("主机存储设备");
				compAssets1 = compAssetsService.compAssetsQueryById(Integer
						.parseInt(ids));
				String resName=compAssets1.getDevName();
				audit.setDetailed(resName);
				audit.setType("删除");
				String time=DateUtil.putDateToTimeStr19(new Date());
				audit.setTime(time);
				auditService.instert(audit);*/
				
				compAssetsService.compAssetsDelete(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * 模糊搜索
	 * 
	 * @return
	 */
	public void queryAjaxCompAssets() {

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
		SearchResult sr = compAssetsService.query(map, page);
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

	/**
	 * 高级搜索
	 * 
	 * @return
	 */
	public void queryAjaxPreciseCompAssets() {

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
		if (request.getParameter("devName") != null) {
			try {
				devName = java.net.URLDecoder.decode(devName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", devName);
		}
		if (request.getParameter("sysName") != null) {
			try {
				sysName = java.net.URLDecoder.decode(sysName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysName);
		}
		if (request.getParameter("softName") != null) {
			try {
				softName = java.net.URLDecoder.decode(softName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", softName);
		}
		SearchResult sr = compAssetsService.queryPrecise(map, page);
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

	public CompAssetsService getCompAssetsService() {
		return compAssetsService;
	}

	public void setCompAssetsService(CompAssetsService compAssetsService) {
		this.compAssetsService = compAssetsService;
	}

	public int getCompAssetsCount() {
		return compAssetsCount;
	}

	public void setCompAssetsCount(int compAssetsCount) {
		this.compAssetsCount = compAssetsCount;
	}

	public List<CompAssets> getCompAssetsList() {
		return compAssetsList;
	}

	public void setCompAssetsList(List<CompAssets> compAssetsList) {
		this.compAssetsList = compAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public CompAssets getCompAssets() {
		return compAssets;
	}

	public void setCompAssets(CompAssets compAssets) {
		this.compAssets = compAssets;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

/*	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/

}
