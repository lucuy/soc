package com.soc.webapp.action.scanTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.ScanTask.ScanTask;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.scanTask.ScanTaskService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.linkMethod.TransmitFile;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class ScanTaskAction extends BaseAction {
	private ScanTaskService scanTaskManager;
	private AuditService auditManager;
	private List<ScanTask> scanTaskList;
	private ScanTask scanTask;
	private String keyword;
	private int state;
	private String scanName;
	private long id;//查询ID
	private String ids;//执行删除的id串
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryAll(){
		log.info("[ScanTaskAction] Enter method query()...");
 		HttpServletRequest request = super.getRequest();
 		Page page = null;
 		SearchResult sr = null;
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");

				try {
					if (StringUtil.isNotBlank(startIndex)) {
						if (keyword==null || keyword.equals("")) {
							page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						}else {
							if (Page.getKeyword().equals(keyword)) {
								page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
								Page.setKeyword(keyword);
							}else{
								page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
								Page.setKeyword(keyword);
							}
						}
					} else {
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}			
				} catch (Exception e) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
					Page.setKeyword(keyword);
				}
			Map map = new HashMap();
		//模糊查询
			if (request.getParameter("keyword") != null) {
				try {
					keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				map.put("keyword", keyword);
			}
			sr = scanTaskManager.queryAllScanTasks(map, page);
			if (sr!=null) {
				scanTaskList = sr.getList();
				request.setAttribute("Page", sr.getPage());
			} else {
				request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
				
			}
		
		return SUCCESS;
	}
	//编辑
	public String editScanTask(){
		if(id!=0){
			scanTask  =scanTaskManager.queryScanTaskById(id);
		}
		
		return SUCCESS;
	}
	//检查名称是否重复
	 public void checkName(){
	    	

			// 标识此策略名是否存在
			String flag = "false";

			if (StringUtil.isNotBlank(scanName)) {
				scanTask = scanTaskManager.queryScanTaskByName(scanName);
				if (scanTask!=null) {
					flag = "true";
				}
			}
			// 将flag返回给页面
			try {
				getResponse().getWriter().write(flag);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	//新增/修改
	public String insertORUpdate(){
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(scanTask.getTaskName() + "("
				+ scanTask.getTaskName() + ")");
		User user= (User) this.getSession().getAttribute("SOC_LOGON_USER");
		if(scanTask.getId()!=0){
			scanTaskManager.updateScanTask(scanTask);
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "漏扫联动", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ user.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :修改漏扫联动";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改漏扫联动");
		}else{
			Date date = new Date();
			scanTask.setCreateDate(date);
			long times = date.getTime();
			scanTask.setUserName(user.getUserLoginName());
			scanTask.setFalgFileName(times+"_"+scanTask.getTaskName());
			scanTaskManager.insertScanTask(scanTask);
			auditManager.insertByInsertOperator(user.getUserId(), "漏扫联动", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ user.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :漏扫联动";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增漏扫联动");
			
		}
		
		return SUCCESS;
	}
	//删除
	public String delete(){
		if(StringUtil.isNotEmpty(ids)){
			String [] id = ids.split(",");
			for (String sid : id) {
				scanTask = scanTaskManager.queryScanTaskById(Long.parseLong(sid));
				scanTaskManager.deleteById(Long.parseLong(sid));
				List<String> fieldList = new ArrayList<String>();
    			fieldList.add(scanTask.getTaskName() + "("
    					+ scanTask.getTaskName() + ")");
    			auditManager.insertByDeleteOperator(((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(), "漏扫联动", super
    					.getRequest().getRemoteAddr(), fieldList);

    			// syslog
    		/*	String logString = "";

    			logString = "登录名:"
    					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
    							.getUserLoginName() + " 源IP:"
    					+ getRequest().getRemoteAddr() + " 操作时间:"
    					+ DateUtil.curDateTimeStr19() + " 操作类型 :删除漏扫联动";
    			logManager.writeSystemAuditLog(logString);*/
    			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
    					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除漏扫联动");
			}
		}
		return SUCCESS;
	}
	
	public String issuedMethod() {
		if(id!=0){
			scanTask = scanTaskManager.queryScanTaskById(id);
			TransmitFile.RunSCANTask(scanTask);
			scanTaskManager.updateStateById(id);
		}
		return SUCCESS;
	}
	
	
	
	
	
	public ScanTaskService getScanTaskManager() {
		return scanTaskManager;
	}
	public void setScanTaskManager(ScanTaskService scanTaskManager) {
		this.scanTaskManager = scanTaskManager;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public List<ScanTask> getScanTaskList() {
		return scanTaskList;
	}
	public void setScanTaskList(List<ScanTask> scanTaskList) {
		this.scanTaskList = scanTaskList;
	}
	public ScanTask getScanTask() {
		return scanTask;
	}
	public void setScanTask(ScanTask scanTask) {
		this.scanTask = scanTask;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getScanName() {
		return scanName;
	}
	public void setScanName(String scanName) {
		this.scanName = scanName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}
