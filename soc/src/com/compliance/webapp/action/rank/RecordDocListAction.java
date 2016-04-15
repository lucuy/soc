package com.compliance.webapp.action.rank;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.rank.RecordDocList;
import com.compliance.service.rank.RecordDocListService;
import com.compliance.service.rank.RecordService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.load.Load;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案集action
 * @author quyongkun
 *
 */
public class RecordDocListAction extends BaseAction{
	
	/**
	 * 备案集业务接口
	 */
	private RecordDocListService recordDocListService;
	
	/**
	 * 备案业务接口
	 */
	private RecordService recordService;
	
	/**
	 * 审计接口
	 */
//	private AuditService auditService;
	
	
	/**
	 * 
	 * 快速搜索
	 * @return
	 */
	public void recordDocListSearch() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
        String str=request.getParameter("recordId") ;
        try {
			str = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int recordId=Integer.parseInt(str);
        List<RecordDocList> recordDocLists=recordDocListService.queryByRecordId(recordId);
		List<Map> mapRecord = new ArrayList<Map>();
		for(RecordDocList recordDocList: recordDocLists)
		{
			Map<String, Object> sysRecMap = new HashMap<String, Object>();
			sysRecMap.put("recordDocListId", recordDocList.getRecordDocListId());
			sysRecMap.put("recordDocListDocmentName", recordDocList.getRecordDocListDocmentName());
			if(recordDocList.getRecordDocListProduceDate()!=null){
				String dateStr=DateUtil.putDateToTimeStr10(recordDocList.getRecordDocListProduceDate());
				sysRecMap.put("recordDocListProduceDate", dateStr);
			}
			if(recordDocList.getRecordDocListDownDate()!=null){
				String dateStr=DateUtil.putDateToTimeStr10(recordDocList.getRecordDocListDownDate());
				sysRecMap.put("recordDocListDownDate", dateStr);
			}				
			sysRecMap.put("recordDocListDownloads",recordDocList.getRecordDocListDownloads());
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
	 * 备案下载
	 */
	public void recordLoad()
	{
		Load load = new Load();
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		 
		 
		 String str = request.getParameter("recordDocListId");
		 int recordDocListId=Integer.parseInt(str);
		 RecordDocList recordDocList=recordDocListService.queryById(recordDocListId);
		 recordDocList.setRecordDocListDownDate(new Date());
		 recordDocList.setRecordDocListDownloads( recordDocList.getRecordDocListDownloads()+1);
		 recordDocListService.update(recordDocList);
		 String recordHistoryDocument=recordDocList.getRecordDocListDocmentName();
//		 String recordHistoryDocument = request.getParameter("recordDocListDocmentName");
		 //SysRecord sysR = sysRecordService.queryById(Integer.parseInt(Id));
//		 try {
//			 recordHistoryDocument = new String(recordHistoryDocument.getBytes("iso-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			/*Audit audit=new Audit();
			audit.setDetailed(recordHistoryDocument);
			audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
			audit.setObject("系统备案");recordHistoryDocument
			audit.setTime(DateUtil.curDateTimeStr19());
			audit.setType("文档下载");
			auditService.instert(audit);*/
		load.AttachmentDownload(response, recordDocList.getReRecordDocListDocmentName(),recordHistoryDocument);
	}
	

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public RecordDocListService getRecordDocListService() {
		return recordDocListService;
	}

	public void setRecordDocListService(RecordDocListService recordDocListService) {
		this.recordDocListService = recordDocListService;
	}

	/*public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/
	
	

}


