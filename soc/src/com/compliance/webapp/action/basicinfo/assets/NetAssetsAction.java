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
import com.compliance.model.basicinfo.assets.NetAssets;
import com.compliance.service.basicinfo.assets.NetAssetsService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class NetAssetsAction extends BaseAction {
	private NetAssetsService netAssetsService;
	private int netAssetsCount = 0;
	private List<NetAssets> netAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private NetAssets netAssets = new NetAssets();
	private String ids;
	// 高级搜索网络互连设备名称字段
	private String netDevName;
	// 高级搜索网络互连设备所属信息系统名称字段
	private String sysName;
	// 高级搜索网络互连设备重要程度描述
	private String impDegree;

	/**
	 * 取消添加,分页显示安全设备列表
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
		netAssetsCount = netAssetsService.netAssetsCount(mapper);
		page.setTotalCount(netAssetsCount);
		List<NetAssets> list = netAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		netAssetsList = sr.getList();
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
			netAssets = netAssetsService.netAssetsQueryById(Integer
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
	public String addNetAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String impDegree = request.getParameter("impDegree");
		String devName = request.getParameter("devName");
		String devDescriotion = request.getParameter("devDescriotion");
		String netRemarks = request.getParameter("netRemarks");
		netAssets.setId(Integer.parseInt(id));
		netAssets.setImpDegree(impDegree);
		netAssets.setDevDescriotion(devDescriotion);
		netAssets.setDevName(devName);
		netAssets.setNetRemarks(netRemarks);
		netAssets.setRelsysName(relSysName);
		if (Integer.parseInt(id) == 0) {
			netAssetsService.netAssetsInsert(netAssets);
		} else {
			netAssetsService.netAssetsUpdate(netAssets);
		}
		
	 
		
		return SUCCESS;
	}

	/**
	 * 
	 * 快速搜索
	 * 
	 * @return
	 */
	public String queryNetAssets() {
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
		netAssetsCount = netAssetsService.netAssetsCount(map);
		page.setTotalCount(netAssetsCount);
		List<NetAssets> list = netAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());

		sr.setList(list);
		sr.setPage(page);
		netAssetsList = sr.getList();
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
		NetAssets netAssets1 = new NetAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
					
				 

					netAssetsService.netAssetsDelete(Integer.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
			 
				netAssetsService.netAssetsDelete(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * ajax查询分页
	 * 
	 * @return
	 */
	public void queryAjaxNetworkAssets() {

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
		SearchResult sr = netAssetsService.query(map, page);
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

	// 高级搜索网络互连设备
	public void queryExtNetworkAssets() {
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
		if (request.getParameter("netDevName") != null) {
			try {
				netDevName = java.net.URLDecoder.decode(netDevName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", netDevName);
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
		SearchResult sr = netAssetsService.queryPrecise(map, page);
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

	public NetAssetsService getNetAssetsService() {
		return netAssetsService;
	}

	public void setNetAssetsService(NetAssetsService netAssetsService) {
		this.netAssetsService = netAssetsService;
	}

	public int getNetAssetsCount() {
		return netAssetsCount;
	}

	public void setNetAssetsCount(int netAssetsCount) {
		this.netAssetsCount = netAssetsCount;
	}

	public List<NetAssets> getNetAssetsList() {
		return netAssetsList;
	}

	public void setNetAssetsList(List<NetAssets> netAssetsList) {
		this.netAssetsList = netAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public NetAssets getNetAssets() {
		return netAssets;
	}

	public void setNetAssets(NetAssets netAssets) {
		this.netAssets = netAssets;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNetDevName() {
		return netDevName;
	}

	public void setNetDevName(String netDevName) {
		this.netDevName = netDevName;
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
