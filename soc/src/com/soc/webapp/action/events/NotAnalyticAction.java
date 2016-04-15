/*
 * 文 件 名:  NotAnalyticAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2013-1-16
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
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
import com.soc.model.events.NotAnalyticEvents;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.events.NotAnalyticEventsService;
import com.soc.webapp.action.BaseAction;
import com.util.GetDateUtil;
import com.util.IpConvert;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <未解析事件的管理>
 * <主要实现未解析事件的列表展示>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2013-1-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NotAnalyticAction extends BaseAction
{
    
    private NotAnalyticEventsService notAnalyticManager; //未解析事件
    private AssetService assetManger;
    
    private List<NotAnalyticEvents> notAnayticList;
    //高级查询天数字段
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
    private int day = 1;
    
    //快速搜索关键字
    private String keyword;
    /**
     * <未解析事件查询>
     * <主要查询未解析的事件>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryNotAnalysis()
    {
        
        LOG.info("[NotAnalyticAction] enter method queryNotAnalysis() ...");

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
        
        map.put("tableName", "tbl_"+tableName+"_not_analytic_events");
        
       
       if (request.getParameter("keyword") != null) {
            try {
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("keyword", keyword);
        }
       long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		
 		
 		String assetId= assetManger.queryIDSByUser(groupid);
 		if(assetId.equals("")){
 			assetId="0";
 		}
 		map.put("assetId", assetId);
        
       /*if (StringUtil.isNotBlank(alertReceiver)) {
            map.put("alertReceiver", alertReceiver);
        }
        if (StringUtil.isNotBlank(alertSendway)) {
            map.put("alertSendway", alertSendway);
        }*/
        
        sr = notAnalyticManager.query(map, page);

        if (sr != null) {
            List<NotAnalyticEvents> notAnayticList = (List<NotAnalyticEvents>) sr.getList();
            request.setAttribute("notAnayticList", notAnayticList);
            request.setAttribute("Page", sr.getPage());
        } else {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        
        return SUCCESS;
    }
    /**
     * <未解析事件高级查询>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryAllNotAnaly(){
    	 LOG.info("[NotAnalyticAction] enter method queryAllNotAnaly() ...");
    	 HttpServletRequest request = super.getRequest();
    	  Page page = null;
          SearchResult sr = null;

          // 处理数据分页的起始条数
          String startIndex = request.getParameter("startIndex");

          if (StringUtil.isNotBlank(startIndex)) {
              page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
          } else {
              page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
          }

          // 接收查询条件，并存储到map中
          Map<String, Object> map = new HashMap<String, Object>();
          long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
  		
  		
  		String assetId= assetManger.queryIDSByUser(groupid);
  		if(assetId.equals("")){
  			assetId="0";
  		}
  		map.put("assetId", assetId);
          List<String> sl=new ArrayList<String>();
          selday=request.getParameter("selday");
          if(StringUtil.isNotEmpty(selday)){
          	
          	sl=GetDateUtil.getDate2(Integer.parseInt(selday));
          	
          	map.put("tableName", this.getDate4(sl));
          }
          selbeginTime = request.getParameter("selbeginTime");
          selendTime =request.getParameter("selendTime");
          if(StringUtil.isNotEmpty(selbeginTime)&&StringUtil.isNotEmpty(selendTime)){
        		sl=GetDateUtil.getDate3(selbeginTime, selendTime);
        		
                	map.put("tableName", this.getDate4(sl));
        	}
          selnotAnalyticEventsAssetName = request.getParameter("selnotAnalyticEventsAssetName");
          if(StringUtil.isNotEmpty(selnotAnalyticEventsAssetName)){
        	  map.put("selnotAnalyticEventsAssetName", selnotAnalyticEventsAssetName);
          }
          selnotAnalyticEventsCollectorName = request.getParameter("selnotAnalyticEventsCollectorName");
          if(StringUtil.isNotEmpty(selnotAnalyticEventsCollectorName)){
        	  map.put("selnotAnalyticEventsCollectorName", selnotAnalyticEventsCollectorName);
          }
          selnotAnalyticEventsContent = request.getParameter("selnotAnalyticEventsContent");
          if(StringUtil.isNotEmpty(selnotAnalyticEventsContent)){
        	  map.put("selnotAnalyticEventsContent", selnotAnalyticEventsContent);
          }
          sr = notAnalyticManager.queryAllNotAnaly(map, page);
          if (sr != null) {
              List<NotAnalyticEvents> notAnayticList = (List<NotAnalyticEvents>) sr.getList();
              request.setAttribute("notAnayticList", notAnayticList);
              request.setAttribute("Page", sr.getPage());
          } else {
              request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
          }
    	return SUCCESS;
    }
    public String queryAssetListOutOfEvent(){
    	LOG.info("[NotAnalyticAction] enter method queryAssetListOutOfEvent() ...");

        HttpServletRequest request = super.getRequest();
 
        Page page = null;
        SearchResult sr = null;

        // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");

        if (StringUtil.isNotBlank(startIndex)) {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
        } else {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }

        // 接收查询条件，并存储到map中
        Map<String, Object> map = new HashMap<String, Object>();
        // 模糊查询
        
       map.put("day", day);
       long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetId= assetManger.queryIDSByUser(groupid);
		if(assetId.equals("")){
  			assetId="0";
  		}
		map.put("assetIds", assetId); 
       if (request.getParameter("keyword") != null) {
            try {
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("keyword", keyword);
        }
            
        sr = notAnalyticManager.queryAssetList(map, page);

        if (sr != null) {
            List<Asset> assetList = (List<Asset>) sr.getList();
            for (Asset asset : assetList) {
				asset.setAssetIps(IpConvert.IpString(asset.getAssetIp()));
			}
            request.setAttribute("assetList", assetList);
            request.setAttribute("Page", sr.getPage());
        } else {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        
    	return SUCCESS;
    }

    public NotAnalyticEventsService getNotAnalyticManager()
    {
        return notAnalyticManager;
    }

    public void setNotAnalyticManager(NotAnalyticEventsService notAnalyticManager)
    {
        this.notAnalyticManager = notAnalyticManager;
    }

    public List<NotAnalyticEvents> getNotAnayticList()
    {
        return notAnayticList;
    }

    public void setNotAnayticList(List<NotAnalyticEvents> notAnayticList)
    {
        this.notAnayticList = notAnayticList;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getSelday() {
		return selday;
	}

	public void setSelday(String selday) {
		this.selday = selday;
	}

	public String getSelbeginTime() {
		return selbeginTime;
	}

	public void setSelbeginTime(String selbeginTime) {
		this.selbeginTime = selbeginTime;
	}

	public String getSelendTime() {
		return selendTime;
	}

	public void setSelendTime(String selendTime) {
		this.selendTime = selendTime;
	}

	public String getSelnotAnalyticEventsAssetName() {
		return selnotAnalyticEventsAssetName;
	}

	public void setSelnotAnalyticEventsAssetName(
			String selnotAnalyticEventsAssetName) {
		this.selnotAnalyticEventsAssetName = selnotAnalyticEventsAssetName;
	}

	public String getSelnotAnalyticEventsCollectorName() {
		return selnotAnalyticEventsCollectorName;
	}

	public void setSelnotAnalyticEventsCollectorName(
			String selnotAnalyticEventsCollectorName) {
		this.selnotAnalyticEventsCollectorName = selnotAnalyticEventsCollectorName;
	}

	public String getSelnotAnalyticEventsContent() {
		return selnotAnalyticEventsContent;
	}

	public void setSelnotAnalyticEventsContent(String selnotAnalyticEventsContent) {
		this.selnotAnalyticEventsContent = selnotAnalyticEventsContent;
	}
	private  String getDate4(List<String> list){
		 long falg=0;
	 	StringBuffer sbuffer =new StringBuffer();
	 	for (String string : list) {
	 		  int tblname=notAnalyticManager.existsTable(string);
	    	  if(tblname!=0){
	    		  if(falg==0){
	    			 sbuffer.append(string);
	    		  }else{
	    			 sbuffer.append(","+string); 
	    		  }
	    		 
	    	  }
	    	  falg++;
			}
	 	
	 	return sbuffer.toString();
	}
	public AssetService getAssetManger() {
		return assetManger;
	}
	public void setAssetManger(AssetService assetManger) {
		this.assetManger = assetManger;
	}
	
	
}
