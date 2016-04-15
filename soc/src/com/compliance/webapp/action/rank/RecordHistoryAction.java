package com.compliance.webapp.action.rank;

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

import com.compliance.model.rank.RecordHistory;
import com.compliance.service.rank.RecordHistoryService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.load.Load;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案历史action
 * @author quyongkun
 *
 */
public class RecordHistoryAction extends BaseAction{
	
	/**
	 * 备案历史业务接口
	 */
	private RecordHistoryService recordHistoryService;
	
	/**
	 * 备案历史集合
	 */
	private List<RecordHistory> recordHistorys;
	
	/**
	 * 页面传递数据
	 */
	private String keyword;
	/**
	 * 高级搜索页面参数系统名称，安全等级，备案文档名称，备案日期
	 */
	private String sysname;
	private String ranklevel;
	private String docname;
	private String recorddate;
	
	/**
	 * 审计接口
	 */
//	private AuditService auditService;
	
   /**
    * 查询
    * @return
    */
	public String query()
	{
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
		sr=recordHistoryService.query(mapper, page);
		recordHistorys = sr.getList();
		sr.setList(recordHistorys);
		sr.setPage(page);
		if (sr != null) {
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 
	 * 快速搜索
	 * @return
	 */
	public void jsonForAjax() {
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
		sr=recordHistoryService.query(map, page);
		this.recordHistorys = sr.getList();
		List<Map> mapRecord = new ArrayList<Map>();
		for(RecordHistory h : recordHistorys)
		{
			Map<String, Object> sysRecMap = new HashMap<String, Object>();
			sysRecMap.put("recordHistoryId", h.getRecordHistoryId());
			sysRecMap.put("sysInFoName", h.getSysInFoName());
			sysRecMap.put("rankGrade", h.getRankGrade());
			sysRecMap.put("recordHistoryDocument", h.getRecordHistoryDocument());
			if(h.getRecordHistoryDate()!=null){
				String dateStr=DateUtil.putDateToTimeStr10(h.getRecordHistoryDate());
				sysRecMap.put("recordHistoryDate", dateStr);
			}
			
			mapRecord.add(sysRecMap);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(mapRecord);
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
	 * 高级搜索备案历史
	 */
	public void precisequeryrecord(){
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
		if (request.getParameter("sysname") != null) {
			try {
				sysname = java.net.URLDecoder.decode(sysname, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", sysname);
		}
		if (! "0".equals(request.getParameter("ranklevel")) ) {
			try {
				ranklevel = java.net.URLDecoder.decode(ranklevel, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword2", ranklevel);
		}
		if (request.getParameter("docname") != null) {
			try {
				docname = java.net.URLDecoder.decode(docname, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword3", docname);
		}
		if (request.getParameter("recorddate") != null) {
			try {
				recorddate = java.net.URLDecoder.decode(recorddate, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword4", recorddate);
		}
		sr=recordHistoryService.precisequery(map, page);
		this.recordHistorys = sr.getList();
		List<Map> mapRecord = new ArrayList<Map>();
		for(RecordHistory h : recordHistorys)
		{
			Map<String, Object> sysRecMap = new HashMap<String, Object>();
			sysRecMap.put("recordHistoryId", h.getRecordHistoryId());
			sysRecMap.put("sysInFoName", h.getSysInFoName());
			sysRecMap.put("rankGrade", h.getRankGrade());
			sysRecMap.put("recordHistoryDocument", h.getRecordHistoryDocument());
			if(h.getRecordHistoryDate()!=null){
				String dateStr=DateUtil.putDateToTimeStr10(h.getRecordHistoryDate());
				sysRecMap.put("recordHistoryDate", dateStr);
			}
			
			mapRecord.add(sysRecMap);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(mapRecord);
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
	 * 备案历史下载
	 */
	public void recordLoad()
	{
		Load load = new Load();
		HttpServletRequest request = super.getRequest();
		 HttpServletResponse response = super.getResponse();
//		 String recordHistoryDocument = request.getParameter("recordHistoryDocument");
		 String recordHistoryId=request.getParameter("recordHistoryId");
		 RecordHistory recordHistory=recordHistoryService.queryById(Integer.parseInt(recordHistoryId));
		 String recordHistoryDocument=recordHistory.getRecordHistoryDocument();
		 //SysRecord sysR = sysRecordService.queryById(Integer.parseInt(Id));
//		 try {
//			 recordHistoryDocument = new String(recordHistoryDocument.getBytes("iso-8859-1"),"utf-8");
//			//System.out.println(recordHistoryDocument);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		/* Audit audit=new Audit();
			audit.setDetailed(recordHistoryDocument);
			audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
			audit.setObject("备案历史");
			audit.setTime(DateUtil.curDateTimeStr19());
			audit.setType("文档下载");
			auditService.instert(audit);*/
        	load.AttachmentDownload(response, recordHistory.getReRecordHistoryDocument(),recordHistoryDocument);
	}
	

	public RecordHistoryService getRecordHistoryService() {
		return recordHistoryService;
	}

	public void setRecordHistoryService(RecordHistoryService recordHistoryService) {
		this.recordHistoryService = recordHistoryService;
	}

	public List<RecordHistory> getRecordHistorys() {
		return recordHistorys;
	}

	public void setRecordHistorys(List<RecordHistory> recordHistorys) {
		this.recordHistorys = recordHistorys;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getRanklevel() {
		return ranklevel;
	}

	public void setRanklevel(String ranklevel) {
		this.ranklevel = ranklevel;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}



/*	public AuditService getAuditService() {
		return auditService;
	}



	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
	*/
	

	
}
