package com.soc.webapp.action.knowledgemanger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.soc.model.events.EventLibrary;
import com.soc.service.events.EventLibraryService;
import com.soc.service.events.EventTreeService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;



public class EventLibraryAction extends BaseAction {
	private EventLibraryService eventLibraryService;
	private EventTreeService eventTreeService;
	//查看详细信息 的标题
	private String title;
	private List<EventLibrary> eventLibrarys=null;
	private EventLibrary eventLibrary;
	private String ids;
	private String eventLibraryType;
	private String detail;
	private String keyword;
	//事件编号
	private String eventLibraryId  ; 
	//中文名称
	private String eventLibraryName;
	//设备类型
	private String deviceType  ; 
	//摘要
	private String discription ; 
	
	
	public String queryList(){
		
		//System.out.println(eventLibraryId+"  "+eventLibraryName +" "+deviceType+"  "+ discription);
		LOG.info("[EventLibraryAction] enter method queryList() ...");
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = null;
		
		
		
		
		/*eventLibraryType = request.getParameter("eventLibraryType");
		String eventLibraryId = request.getParameter("eventLibraryId");		
		eventLibraryName = request.getParameter("eventLibraryName");		
		
		deviceType = request.getParameter("deviceType");		
		discription = request.getParameter("discription");
		detail = request.getParameter("detail");*/

		//如果 不是空则以  “详细信息界面”显示
		if(StringUtil.isNotBlank(detail)){
			//显示详细信息
			if("info".equals(detail))
				if(StringUtil.isNotBlank(eventLibraryId)&&!"undefined".equals(eventLibraryId.toString())){
					queryListById(eventLibraryId);
				}
			//显示添加窗口
			if("add".equals(detail)){
				int type=Integer.parseInt(eventLibraryType);
				int id=0;
				queryListByType(type);
//				EventLibrary e;
				if(eventLibrarys.size()>0){
					id=Integer.parseInt(eventLibrarys.get(0).getEventLibraryId());
				}else{
//					e=new EventLibrary();
				}
				//添加库类型
				if(id<10000*type){
					id=type*10000;
				}
				id=id+1;
//				e=new EventLibrary();
//				e.setEventLibraryId(String.valueOf(id));
//				e.setEventLibraryType(type);
				eventLibrary=new EventLibrary();
				eventLibrary.setEventLibraryId(String.valueOf(id));
				eventLibrary.setEventLibraryType(type);
				eventLibrarys.clear();
//				eventLibrarys.add(e);
				title= eventTreeService.queryEventTreeById(type).getName();
			}
			return "detail";
		}
		
		
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
		
		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(eventLibraryType)) {
			map.put("eventLibraryType", eventLibraryType);
		}
		if(StringUtil.isNotBlank(eventLibraryId)&&!"undefined".equals(eventLibraryId.toString())){
			map.put("eventLibraryId", eventLibraryId);
		}
		if(StringUtil.isNotBlank(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword , "UTF-8") ; 
				map.put("keyword", keyword);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(StringUtil.isNotEmpty(eventLibraryName)){
			map.put("eventLibraryName",eventLibraryName ) ; 
		}
		
		if(StringUtil.isNotBlank(deviceType)){
			map.put("deviceType", deviceType);
		}
		if(StringUtil.isNotBlank(discription)){
			map.put("discription", discription);
		}
		
		sr = eventLibraryService.queryEventLibrary(map, page);
		
		// 对查找的结果为分页赋值
		if (sr != null) {
			eventLibrarys = (List<EventLibrary>) sr.getList();
			//System.out.println(eventLibrarys);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		
		return SUCCESS;
	}
	public String deleteLibrary(){
		LOG.info("[EventLibraryAction] enter method deleteLibrary() ...");
		String[] checked = ids.split(",");
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventLibraryId", checked[0]);
		EventLibrary e=eventLibraryService.queryEventLibraryById(map);
		int type=e.getEventLibraryType();
		eventLibraryService.deleteEventLibraryById(checked);
		
		 queryListByType(type);
		 return SUCCESS;
	}
	/**
	 * 根据ID查询 事件库 对象
	 * @param eventLibraryId
	 */
	public void queryListById(String eventLibraryId){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtil.isNotBlank(eventLibraryId)&&!"undefined".equals(eventLibraryId.toString())){
			map.put("eventLibraryId", eventLibraryId);
		}
		eventLibrary=eventLibraryService.queryEventLibraryById(map);
	}
	//根据类型显示列表
	private void queryListByType(int type){
		Page page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;
		// 接收查询条件，并存储到map中
		Map<String, String> map = new HashMap<String, String>();
		if (type>0) {
			map.put("eventLibraryType",Integer.toString(type));
		}
		if(eventLibraryService.count(map)>0){}
		sr = eventLibraryService.queryEventLibrary(map, page);
		// 对查找的结果为分页赋值
		if (sr != null) {
			eventLibrarys = (List<EventLibrary>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
	}
	
	public String saveLibrary(){
		LOG.info("[EventLibraryAction] enter method saveLibrary() ...");
		HttpServletRequest request = super.getRequest();
		String detail = request.getParameter("detail");
		//System.out.println(detail);
		int type=eventLibrary.getEventLibraryType();
		//System.out.println(type);
		//添加李长红修改
		try {
			if("add".equals(detail)){
				eventLibraryService.addEventLibrary(eventLibrary);
					//修改信息
			}else if("info".equals(detail)){
				if (StringUtil.isNotBlank(eventLibraryType))
				eventLibraryService.modifyEventLibrary(eventLibrary);
					////System.out.println("修改成功");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		queryListByType(type);
		return SUCCESS;
	}
	
	public void checkEventLibrary(){
		queryListById(eventLibraryId);
		JSONObject json = JSONObject.fromObject(eventLibrary);
		try {
			getResponse().getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EventLibraryService getEventLibraryService() {
		return eventLibraryService;
	}
	public void setEventLibraryService(EventLibraryService eventLibraryService) {
		this.eventLibraryService = eventLibraryService;
	}
	public List<EventLibrary> getEventLibrarys() {
		return eventLibrarys;
	}
	public void setEventLibrarys(List<EventLibrary> eventLibrarys) {
		this.eventLibrarys = eventLibrarys;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		
		this.title = title;
	}
	public EventTreeService getEventTreeService() {
		return eventTreeService;
	}
	public void setEventTreeService(EventTreeService eventTreeService) {
		this.eventTreeService = eventTreeService;
	}
	public EventLibrary getEventLibrary() {
		return eventLibrary;
	}
	public void setEventLibrary(EventLibrary eventLibrary) {
		this.eventLibrary = eventLibrary;
	}
	public String getEventLibraryType() {
		return eventLibraryType;
	}
	public void setEventLibraryType(String eventLibraryType) {
		this.eventLibraryType = eventLibraryType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEventLibraryName() {
		return eventLibraryName;
	}
	public void setEventLibraryName(String eventLibraryName) {
		this.eventLibraryName = eventLibraryName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getEventLibraryId() {
		return eventLibraryId;
	}
	public void setEventLibraryId(String eventLibraryId) {
		this.eventLibraryId = eventLibraryId;
	}
	
	
	
}
