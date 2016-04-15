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

import com.compliance.model.basicinfo.assets.DataAssets;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.DataAssetsService;

import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DataAssetsAction extends BaseAction {
	//审计模块
	
	

	private DataAssetsService dataAssetsService;
	private int dataAssetsCount = 0;
	private List<DataAssets> dataAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private DataAssets dataAssets = new DataAssets();
	private String ids;
	// 高级搜索关键数据类别字段
	private String dataType;
	// 高级搜索业务应用软件名称
	private String softName;
	// 高级搜索信息系统名称字段
	private String sysName;
	// 高级搜索关键数据类别描述
	private String dataDescription;
	// 高级搜索重要程度字段
	private String impDegree;

	/**
	 * 分页显示安全设备列表
	 * 
	 * @return
	 * 
	 * */
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
		dataAssetsService.dataAssetsCount(mapper);
		page.setTotalCount(dataAssetsCount);
		List<DataAssets> list = dataAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		dataAssetsList = sr.getList();
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
			dataAssets = dataAssetsService.dataAssetsQueryById(Integer
					.parseInt(id));

		}
		return SUCCESS;
	}

	/**
	 * 
	 * 添加关键数据类别
	 * 
	 * @return
	 */
	public String addDataAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String dateType = request.getParameter("dateType");
		String resName = request.getParameter("resName");
		List<String> relresName = Arrays.asList(resName.split(";"));
		String devDescription = request.getParameter("devDescription");
		String dataRemarks = request.getParameter("dataRemarks");
		String impDegree = request.getParameter("impDegree");
		dataAssets.setId(Integer.parseInt(id));
		dataAssets.setDateType(dateType);
		dataAssets.setDevDescription(devDescription);
		dataAssets.setRelsysName(relSysName);
		dataAssets.setDataRemarks(dataRemarks);
		dataAssets.setRelresName(relresName);
		dataAssets.setImpDegree(impDegree);
		if (Integer.parseInt(id) == 0) {
			dataAssetsService.dataAssetsInsert(dataAssets);
		} else {
			dataAssetsService.dataAssetsUpdate(dataAssets);
		}
		
		
	/*	String userName= (String)super.getSession().getAttribute("SSI_LOGIN_USER");
		Audit audit=new Audit();
		audit.setName(userName);
		audit.setObject("关键数据类别");
		audit.setDetailed(dateType);
		audit.setType("添加");
		String time=DateUtil.putDateToTimeStr19(new Date());
		audit.setTime(time);
		auditService.instert(audit);
		*/
		
		return SUCCESS;
	}

	/**
	 * 
	 * 模糊搜索关键数据类别
	 * 
	 * @return
	 */
	public String queryDataAssets() {
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
		dataAssetsCount = dataAssetsService.dataAssetsCount(map);
		page.setTotalCount(dataAssetsCount);
		List<DataAssets> list = dataAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());

		sr.setList(list);
		sr.setPage(page);
		dataAssetsList = sr.getList();
		if (sr != null) {
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * 删除关键数据类别
	 * 
	 * @return
	 */
	public String delete() {
		log.info("update employee status...");
		DataAssets dataAssets1 = new DataAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
					dataAssetsService.dataAssetsDelete(Integer
							.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
			 
				dataAssetsService.dataAssetsDelete(Integer.parseInt(ids));
			}
		}
		return SUCCESS;
	}

	/**
	 * 模糊搜索关键数据类别
	 * 
	 * @return
	 */
	public void queryAjaxDataAssets() {

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
		SearchResult sr = dataAssetsService.query(map, page);
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
	 * 高级搜索关键数据类别
	 * 
	 * @return
	 */
	public void queryAjaxPreciseDataAssets() {

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
		if (request.getParameter("dataType") != null) {
			try {
				dataType = java.net.URLDecoder.decode(dataType, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			map.put("keyword1", dataType);

		}
		if (request.getParameter("softName") != null) {
			try {
				softName = java.net.URLDecoder.decode(softName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			map.put("keyword2", sysName);

		}
		if (request.getParameter("sysName") != null) {
			try {
				sysName = java.net.URLDecoder.decode(sysName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			map.put("keyword3", softName);

		}
		if (request.getParameter("dataDescription") != null) {
			try {
				dataDescription = java.net.URLDecoder.decode(dataDescription,
						"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			map.put("keyword4", dataDescription);

		}
		if (request.getParameter("impDegree") != null) {
			try {
				impDegree = java.net.URLDecoder.decode(impDegree, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			map.put("keyword5", impDegree);

		}
		SearchResult sr = dataAssetsService.queryPrecise(map, page);
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

	public DataAssetsService getDataAssetsService() {
		return dataAssetsService;
	}

	public void setDataAssetsService(DataAssetsService dataAssetsService) {
		this.dataAssetsService = dataAssetsService;
	}

	public int getDataAssetsCount() {
		return dataAssetsCount;
	}

	public void setDataAssetsCount(int dataAssetsCount) {
		this.dataAssetsCount = dataAssetsCount;
	}

	public List<DataAssets> getDataAssetsList() {
		return dataAssetsList;
	}

	public void setDataAssetsList(List<DataAssets> dataAssetsList) {
		this.dataAssetsList = dataAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public DataAssets getDataAssets() {
		return dataAssets;
	}

	public void setDataAssets(DataAssets dataAssets) {
		this.dataAssets = dataAssets;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
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

	public String getDataDescription() {
		return dataDescription;
	}

	public void setDataDescription(String dataDescription) {
		this.dataDescription = dataDescription;
	}

	public String getImpDegree() {
		return impDegree;
	}

	public void setImpDegree(String impDegree) {
		this.impDegree = impDegree;
	}
 
}
