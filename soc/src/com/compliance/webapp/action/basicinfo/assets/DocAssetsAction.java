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
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.service.basicinfo.assets.DevAssetsService;
import com.compliance.service.basicinfo.assets.DocAssetsService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DocAssetsAction extends BaseAction {
	private DocAssetsService docAssetsService;
	private int docAssetsCount = 0;
	private List<DocAssets> docAssetsList; // 资产种类
	private String keyword; // 快速搜索关键字
	private DocAssets docAssets = new DocAssets();
	private String ids;
	// 高级搜索安全文档名称字段
	private String docName;
	// 高级搜索安全文档所属系统字段
	private String sysName;
	// 高级搜索安全文档重要程度字段
	private String imDegree;

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
		docAssetsCount = docAssetsService.docAssetsCount(mapper);
		page.setTotalCount(docAssetsCount);
		List<DocAssets> list = docAssetsService.query(mapper,
				page.getStartIndex(), page.getPageSize());
		sr.setList(list);
		sr.setPage(page);
		docAssetsList = sr.getList();
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
			docAssets = docAssetsService.docAssetsQueryById(Integer
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
	public String addDocAssets() {
		HttpServletRequest request = super.getRequest();
		String id = request.getParameter("id");
		String sysName = request.getParameter("sysName");
		List<String> relSysName = Arrays.asList(sysName.split(";"));
		String impDegree = request.getParameter("impDegree");
		String docName = request.getParameter("docName");
		String impContent = request.getParameter("impContent");
		String docRemarks = request.getParameter("docRemarks");
		docAssets.setId(Integer.parseInt(id));
		docAssets.setImpDegree(impDegree);
		docAssets.setImpContent(impContent);
		docAssets.setDocName(docName);
		docAssets.setDocRemarks(docRemarks);
		docAssets.setRelsysName(relSysName);
		if (Integer.parseInt(id) == 0) {
			docAssetsService.docAssetsInsert(docAssets);
		} else {
			docAssetsService.docAssetsUpdate(docAssets);
		}
		
		
	 
		
		return SUCCESS;
	}

	/**
	 * 
	 * 快速搜索
	 * 
	 * @return
	 */
	public String queryDocAssets() {
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
		docAssetsCount = docAssetsService.docAssetsCount(map);
		page.setTotalCount(docAssetsCount);
		List<DocAssets> list = docAssetsService.query(map,
				page.getStartIndex(), page.getPageSize());

		sr.setList(list);
		sr.setPage(page);
		docAssetsList = sr.getList();
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
		DocAssets docAssets1 = new DocAssets();
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String checkid : checked) {

					
				 
					
					
					docAssetsService.docAssetsDelete(Integer.parseInt(checkid));
					// mailWebTreatyService.deleteById(Integer.parseInt(checkid));
				}
			} else {
				
				 
				docAssetsService.docAssetsDelete(Integer.parseInt(ids));

			}
		}
		return SUCCESS;
	}

	/**
	 * ajax查询分页
	 * 
	 * @return
	 */
	public void queryAjaxDocAssets() {

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
		SearchResult sr = docAssetsService.query(map, page);
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

	// 高级搜索安全管理文档
	public void queryExtDocAssets() {

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
		if (request.getParameter("docName") != null) {
			try {
				docName = java.net.URLDecoder.decode(docName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", docName);
		}
		if (request.getParameter("sysName") != null) {
			try {
				sysName = java.net.URLDecoder.decode(sysName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysName);
		}
		if (request.getParameter("imDegree") != null) {
			try {
				imDegree = java.net.URLDecoder.decode(imDegree, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", imDegree);
		}
		SearchResult sr = docAssetsService.queryPrecise(map, page);
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

	public DocAssetsService getDocAssetsService() {
		return docAssetsService;
	}

	public void setDocAssetsService(DocAssetsService docAssetsService) {
		this.docAssetsService = docAssetsService;
	}

	public int getDocAssetsCount() {
		return docAssetsCount;
	}

	public void setDocAssetsCount(int docAssetsCount) {
		this.docAssetsCount = docAssetsCount;
	}

	public List<DocAssets> getDocAssetsList() {
		return docAssetsList;
	}

	public void setDocAssetsList(List<DocAssets> docAssetsList) {
		this.docAssetsList = docAssetsList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public DocAssets getDocAssets() {
		return docAssets;
	}

	public void setDocAssets(DocAssets docAssets) {
		this.docAssets = docAssets;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getImDegree() {
		return imDegree;
	}

	public void setImDegree(String imDegree) {
		this.imDegree = imDegree;
	}
 

}
