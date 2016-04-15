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

import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.DevAssetsService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DevAssetsAction extends BaseAction {
	

	private DevAssetsService devAssetsService;
	private int devAssetsCount = 0;
	private List<DevAssets> devAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private DevAssets devAssets = new DevAssets();
	private String ids;
	// 高级搜索安全设备名称字段
	private String devName;
	// 高级搜索所属系统名称字段
	private String sysName;
	// 高级搜索重要程度字段
	private String impDegree;

	/**
	 * 分页显示安全设备列表
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
		// mapper.put("resType", resType);
		devAssetsCount = devAssetsService.devAssetsCount(mapper);
		page.setTotalCount(devAssetsCount);
		List<DevAssets> list = devAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		devAssetsList = sr.getList();
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
			devAssets = devAssetsService.devAssetsQueryById(Integer
					.parseInt(id));

		}
		return SUCCESS;
	}

	/**
	 * 
	 * 添加安全设备
	 * 
	 * @return
	 */
	public String addDevAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String impDegree = request.getParameter("impDegree");
		String devName = request.getParameter("devName");
		String devDescription = request.getParameter("devDescription");
		String devRemarks = request.getParameter("devRemarks");
		devAssets.setId(Integer.parseInt(id));
		devAssets.setImpDegree(impDegree);
		devAssets.setDevDescription(devDescription);
		devAssets.setDevName(devName);
		devAssets.setDevRemarks(devRemarks);
		devAssets.setRelsysName(relSysName);
		if (Integer.parseInt(id) == 0) {
			devAssetsService.devAssetsInsert(devAssets);
		} else {
			devAssetsService.devAssetsUpdate(devAssets);
		}
		
	 
		
		return SUCCESS;
	}

	/**
	 * 
	 * 快速搜索
	 * 
	 * @return
	 */
	public String queryDevAssets() {
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
		devAssetsCount = devAssetsService.devAssetsCount(map);
		page.setTotalCount(devAssetsCount);
		List<DevAssets> list = devAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());

		sr.setList(list);
		sr.setPage(page);
		devAssetsList = sr.getList();
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
		DevAssets devAssets1 = new DevAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
					
					 
					
					devAssetsService.devAssetsDelete(Integer.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
				 
				devAssetsService.devAssetsDelete(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * ajax查询分页
	 * 
	 * @return
	 */
	public void queryAjaxDevAssets() {

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
		SearchResult sr = devAssetsService.query(map, page);
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

	// 高级搜索安全设备信息
	public void queryExtDevAssets() {
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
		if (request.getParameter("impDegree") != null) {
			try {
				impDegree = java.net.URLDecoder.decode(impDegree, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", impDegree);
		}
		SearchResult sr = devAssetsService.queryPrecise(map, page);
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

	public DevAssetsService getDevAssetsService() {
		return devAssetsService;
	}

	public void setDevAssetsService(DevAssetsService devAssetsService) {
		this.devAssetsService = devAssetsService;
	}

	public int getDevAssetsCount() {
		return devAssetsCount;
	}

	public void setDevAssetsCount(int devAssetsCount) {
		this.devAssetsCount = devAssetsCount;
	}

	public List<DevAssets> getDevAssetsList() {
		return devAssetsList;
	}

	public void setDevAssetsList(List<DevAssets> devAssetsList) {
		this.devAssetsList = devAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public DevAssets getDevAssets() {
		return devAssets;
	}

	public void setDevAssets(DevAssets devAssets) {
		this.devAssets = devAssets;
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

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}
 
}
