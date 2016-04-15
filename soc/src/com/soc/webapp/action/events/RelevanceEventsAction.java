
package com.soc.webapp.action.events;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.asset.Asset;
import com.soc.model.events.Events;
import com.soc.model.events.NotAnalyticEvents;
import com.soc.model.user.User;
import com.soc.service.events.RelevanceEventsService;
import com.soc.webapp.action.BaseAction;
import com.util.GetDateUtil;
import com.util.IpConvert;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <关联后事件action>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RelevanceEventsAction extends BaseAction
{
	private RelevanceEventsService relevanceEventsManager; //关联后事件
//private AssetService assetManger;
private int id;
private List<Events> events;
/*//高级查询天数字段
private String selday;
//高级查询开始时间
private String selbeginTime;
//高级查询结束时间
private String selendTime;
//高级查询 资产名字
private String selnotAnalyticEventsAssetName;
//高级查询 采集器
private String selnotAnalyticEventsCollectorName;
//高级查询 时间详情
private String selnotAnalyticEventsContent;
//接收查看前几天的变量
private int day = 1;*/

//快速搜索关键字
private String keyword;
/**
 * <查询关联后事件>
 * <功能详细描述>
 * @return
 * @see [类、类#方法、类#成员]
 */
public String queryRelevanceEvents()
{
    
    LOG.info("[RelevanceEventsAction] enter method queryRelevanceEvents() ...");

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
   
   
   //这边先注释掉 也许要做权限
   
/*   long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		
		String assetId= assetManger.queryIDSByUser(groupid);
		if(assetId.equals("")){
			assetId="0";
		}
		map.put("assetId", assetId);*/
    
   /*if (StringUtil.isNotBlank(alertReceiver)) {
        map.put("alertReceiver", alertReceiver);
    }
    if (StringUtil.isNotBlank(alertSendway)) {
        map.put("alertSendway", alertSendway);
    }*/
    sr = relevanceEventsManager.query(map, page);
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
		sr =  relevanceEventsManager.queryAtnalyticEvents(map,page);
	    if (sr != null) {
	    	events = (List<Events>) sr.getList();
	    	request.setAttribute("Page", sr.getPage());
	    	sr.setPage(page);
	    } else {
	        request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
	    }
	    
	    return SUCCESS;
	
}
public RelevanceEventsService getRelevanceEventsManager() {
	return relevanceEventsManager;
}
public void setRelevanceEventsManager(
		RelevanceEventsService relevanceEventsManager) {
	this.relevanceEventsManager = relevanceEventsManager;
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