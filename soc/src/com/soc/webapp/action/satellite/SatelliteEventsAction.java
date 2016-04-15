
package com.soc.webapp.action.satellite;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.satellite.Events;
import com.soc.service.satellite.SatelliteEventsService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <直播星action>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2014-05-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SatelliteEventsAction extends BaseAction
{
	private SatelliteEventsService satelliteEventsManager; //直播星事件
//private AssetService assetManger;
private int id;
private List<Events> events;


//快速搜索关键字
private String keyword;
/**
 * <查询关联后事件>
 * <功能详细描述>
 * @return
 * @see [类、类#方法、类#成员]
 */
public String queryEvents()
{
    
    LOG.info("[RelevanceEventsAction] enter method querySatelliteEvents() ...");

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

    // 接收查询条件，并存储到map中
    Map<String, Object> map = new HashMap<String, Object>();
    // 模糊查询
  
  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String tableName = sdf.format(new Date());
    
    map.put("tableName", "tbl_relevance_events");
    
   
   if (request.getParameter("keyword") != null) {
        try {
            keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("keyword", keyword);
    }
   
   

    sr = satelliteEventsManager.query(map, page);
    if (sr != null) {
    	events = (List<Events>) sr.getList();
    	request.setAttribute("Page", sr.getPage());
    	sr.setPage(page);
    } else {
        request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
    }
    
    return SUCCESS;
}
/**
 * 根据id查询出关联后的事件类 再找出产生他的解析后事件
 * <功能详细描述>
 * @return
 * @see [类、类#方法、类#成员]
 */
public String queryAtnalyticEvents(){
		LOG.info("[RelevanceEventsAction] enter method queryAtnalyticEvents() ...");

	    HttpServletRequest request = super.getRequest();

	    Page page = null;
	    SearchResult sr = null;

	    // 处理数据分页的起始条数
	    String startIndex = request.getParameter("startIndex");
	    Map map = new HashMap();
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
		map.put("id", id);
		map.put("tableName", "tbl_relevance_events");
		sr =  satelliteEventsManager.query(map,page);
	    if (sr != null) {
	    	events = (List<Events>) sr.getList();
	    	request.setAttribute("Page", sr.getPage());
	    	sr.setPage(page);
	    } else {
	        request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
	    }
	    
	    return SUCCESS;
	
}


public SatelliteEventsService getSatelliteEventsManager() {
	return satelliteEventsManager;
}
public void setSatelliteEventsManager(
		SatelliteEventsService satelliteEventsManager) {
	this.satelliteEventsManager = satelliteEventsManager;
}
public List<Events> getEvents() {
	return events;
}

public void setEvents(List<Events> events) {
	this.events = events;
}

public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


}