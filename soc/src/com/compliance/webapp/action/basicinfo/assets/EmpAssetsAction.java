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
import com.compliance.model.basicinfo.assets.EmpAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.EmpAssetsService;

import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class EmpAssetsAction extends BaseAction {
	//审计模块

	private EmpAssetsService empAssetsService;
	private int empAssetsCount = 0;
	private List<EmpAssets> empAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private EmpAssets empAssets = new EmpAssets();
	private String ids;
	// 高级搜索安全人员姓名字段
	private String empName;
	// 高级搜索安全人员所管理系统名称字段
	private String sysName;
	// 高级搜索安全人员联系电话字段
	private String empTel;

	/**
	 * 分页显示安全人员列表
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
		empAssetsCount = empAssetsService.empAssetsCount(mapper);
		page.setTotalCount(empAssetsCount);
		List<EmpAssets> list = empAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		empAssetsList = sr.getList();
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
			empAssets = empAssetsService.empAssetsQueryById(Integer
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
	public String addEmpAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String empName = request.getParameter("empName");
		String conInfo = request.getParameter("conInfo");
		String jobDes = request.getParameter("jobDes");
		String empRemarks = request.getParameter("empRemarks");
		empAssets.setId(Integer.parseInt(id));
		empAssets.setEmpName(empName);
		empAssets.setJobDes(jobDes);
		empAssets.setConInfo(conInfo);
		empAssets.setEmpRemarks(empRemarks);
		empAssets.setRelsysName(relSysName);
		if (Integer.parseInt(id) == 0) {
			empAssetsService.empAssetsInsert(empAssets);
		} else {
			empAssetsService.empAssetsUpdate(empAssets);
		}
		
	 
		return SUCCESS;
	}

	/**
	 * 
	 * 快速搜索
	 * 
	 * @return
	 */
	public String queryEmpAssets() {
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
		empAssetsCount = empAssetsService.empAssetsCount(map);
		page.setTotalCount(empAssetsCount);
		List<EmpAssets> list = empAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());

		sr.setList(list);
		sr.setPage(page);
		empAssetsList = sr.getList();
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
		EmpAssets empAssets1 = new EmpAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {
				 

					empAssetsService.empAssetsDelete(Integer.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
			 
				empAssetsService.empAssetsDelete(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * ajax查询分页
	 * 
	 * @return
	 */
	public void queryAjaxEmpAssets() {

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
		SearchResult sr = empAssetsService.query(map, page);
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

	public void queryExtEmpAsssets() {
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
		if (request.getParameter("empName") != null) {
			try {
				empName = java.net.URLDecoder.decode(empName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", empName);
		}

		if (request.getParameter("sysName") != null) {
			try {
				sysName = java.net.URLDecoder.decode(sysName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysName);
		}
		if (request.getParameter("empTel") != null) {
			try {
				empTel = java.net.URLDecoder.decode(empTel, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", empTel);
		}
		SearchResult sr = empAssetsService.queryPrecise(map, page);
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

	public EmpAssetsService getEmpAssetsService() {
		return empAssetsService;
	}

	public void setEmpAssetsService(EmpAssetsService empAssetsService) {
		this.empAssetsService = empAssetsService;
	}

	public int getEmpAssetsCount() {
		return empAssetsCount;
	}

	public void setEmpAssetsCount(int empAssetsCount) {
		this.empAssetsCount = empAssetsCount;
	}

	public List<EmpAssets> getEmpAssetsList() {
		return empAssetsList;
	}

	public void setEmpAssetsList(List<EmpAssets> empAssetsList) {
		this.empAssetsList = empAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public EmpAssets getEmpAssets() {
		return empAssets;
	}

	public void setEmpAssets(EmpAssets empAssets) {
		this.empAssets = empAssets;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
 

}
