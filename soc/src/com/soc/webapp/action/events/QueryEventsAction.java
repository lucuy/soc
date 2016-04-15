package com.soc.webapp.action.events;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.soc.model.asset.Asset;
import com.soc.model.events.Events;
import com.soc.model.events.OriginalEvents;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.events.SummaryEvents;
import com.soc.model.eventtypetag.EventTypeTag;
import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.events.EventsService;
import com.soc.service.events.OriginalLogService;
import com.soc.service.events.QueryEventsGroupService;
import com.soc.service.events.QueryEventsService;
import com.soc.service.events.SummaryEventsService;
import com.soc.service.events.exporteventmasage.ExcelEventMasage;
import com.soc.service.eventtypetag.EventCategoryTagService;
import com.soc.service.eventtypetag.EventTypeTagService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.FreeMarkerUtil;
import com.util.IpConvert;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 事件管理Action类
 * 
 * @author 王纯
 * 
 */
public class QueryEventsAction extends BaseAction {
    
	private QueryEventsService queryEeventsManager; // 事件管理业务对象

	private QueryEventsGroupService queryEventsGroupManager;

	private EventsService eventsManager; // 事件业务对象

	private OriginalLogService originalLogManager; // 原始事件
	
	private SummaryEventsService summaryEventsManager;

    private UserService userManager ;  //用户业务对象
	
	private String treeBuff;

	private String message; // 返回信息

	private String reFresh = "false"; // 操作成功标记

	private String fresh; // 是否刷新组

	private long queryEventsId; // 查询条件ID

	private int queryEventsType = 0; // 查询条件类型 （0：近期事件 1：自定义查询组 2：关联分析 3：配置变更
										// 4：授权操作 5：系统资源 50:系统资源下的异常 6:内置组
										// 7：待告警事件 8：告警信息 9：告警配置 11：自定义查询规则）

	private List<SummaryEvents> eventsList = null;

	private List<QueryEvents> customEventsList = null;

	private List<Events> relevanceAnalysisList;

	private long customQueryEventsId; // 查询事件规则ID

	private String customQueryEventsName; // 查询事件规则名称

	private int[] threatValue; // 威胁值

	private String sourceAdd; // 源地址

	private String sourcePort; // 源端口

	private String targetAdd; // 目标地址

	private String targetPort; // 目标端口

	private String eventsType; //事件类别

    private String beginTime; // 开始时间

	private String endTime; // 结束时间

	private String protocol; // 协议

	private int timeRange; // 时间范围 1:最近一小时 10：最近十小时 24：最近二十四小时 7：最近7天 30：最近三十天
							// 12：本日 0：本月

	private long eventsLogIdentification; // 日志标识

	private String tableName; // 日志表表名

	private final int TYPE_RELEVANCE_ANALYSIS = 2; // 关联分析

	private String actionType; // 定义界面直接查询为 1 保存为0

	private String conditions; // 直接查询条件

	private List<OriginalEvents> originalEventsList;
	
	private List<Events> events;// 用来封装近期事件的list
	
	private String eventsTypeString;//因为这个type是中文 这里用字符串去接受一个标识 方便前台操作
	
	private String lastCustomQueryEventsName;//用作是否刷新左侧菜单 比较名字是否发生了变化

	
	// 用于导出的选中id的字符串
	private String checkids;
	
	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
	
	// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
	private String reportType;
	private boolean exist;// 文件是否存在
	private long eventReportId;
	
    //事件类别标签
    private EventCategoryTagService eventcategoryTagManager;
    
    //事件类型
    private EventTypeTagService eventTypeTagManager;
    
    //事件类别列表
    private List<eventcategorytag> eventCategoryTagList;
    
    //自定义查询规则关联的事件类别列表
    private List<eventcategorytag> eventRelTagList;
    
    //事件类型的所有列表
    private List<EventTypeTag> eventAllTypeList;
    
    //自定义查询规则对应的事件类型列表
    private List<EventTypeTag> eventRelTypeList;
    
    //构造资产组的树形结构
    private String assetBuf;
    
    private AssetGroupService assetGroupManager;
    
    //资产组的ID
    private String assetGroupId;
    
    private String keyword;
    

    private String selAssetName;
    
    private String selAssetIps;
    
    private AssetService assetManager;
    
    //资产列表
    private List<Asset> assetList;
    
     //资产ID
    private int assetId;
    
    //来源用户列表
    private List<String> relUserNames;
    
    //设备IP
    private String deviceIp;
    
    //查询的标示。
    private int flag;
    
    //操作指令
    private String  operateOrder;
    
    private User user; 
    //字段字符串
    private String str ; 
    
    private String strFaild ;
    
    private String categoryName ; 
    
    private String categoryKey ;
    
    //事件类型名称
    private String eventTypeName ; 
    
    private int eventsLevel;      //事件等级
	
    private String driverType;   //设备类型
    
    private int identification ;   //跳转标识
    
    // 审计业务管理类
 	private AuditService auditManager;
    private String biaozhi;
    private String status;
    private String ip;
	/**
	 * <查询解析成功事件对应的原始事件> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryRecentEvents() {
		
		log.info("[QueryEventsAction] enter QueryEventsAction...");
		// 对前台获得的等级和关键字中文编码进行处理

		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = new SearchResult();

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
		// 用来存放查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "tbl_"+new SimpleDateFormat("yyyyMMdd").format(new Date()));
		

		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		if(groupid==1){
 			
 		}else{
 			String assetId= assetManager.queryIDSByUser(groupid);
 	 		if(assetId.equals("")){
 	 			assetId="0";
 	 		}
 	 		map.put("assetId", assetId);
 		}
 		
 		

		sr = queryEeventsManager.queryRecentEvents(map, page);
		
			events = (List<Events>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		

		return SUCCESS;

	}
	
	/**
	 * 查看当天的未定义事件
	 * @return
	 */
    public String queryUndefinedEvents()
    {
    	log.info("[QueryEventsAction] enter queryUndefinedEvents...");
    	Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = new SearchResult();

		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String tableName= "tbl_"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"_undefined_events";
		
		map.put("tableName", tableName);
		
		sr =queryEeventsManager.queryUndefinedEvents(map, page);
    	
		if(sr!= null)
		{
			events = (List<Events>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}
    	
    	return SUCCESS;
    }
    
	/**
	 * <查询解析成功事件对应的原始事件> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryOriginalEvent() {

		log.info("[QueryEventsAction] Enter queryOriginalEvent...");
		HttpServletRequest request = super.getRequest();

		Page page = null;

		SearchResult sr = null;

		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}

		if (StringUtil.isNotEmpty(tableName)) {
			map.put("tableName", "tbl_" + tableName + "_original_log");
		}
		if (eventsLogIdentification != 0) {
			map.put("identification", eventsLogIdentification);
		}

		if (StringUtil.isNotEmpty(tableName) && eventsLogIdentification != 0) {
			// 得到查询结果
			sr = originalLogManager.queryOriginalEvents(map, page);
		}
		if (sr != null) {
			// 将查询结果放到list内
			originalEventsList = (List<OriginalEvents>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;
	}

	/**
	 * 构造查询条件树
	 * 
	 * @return
	 */
	public String TreeMethod() {
	    
		String permissionIds = (String) super.getRequest().getSession()
				.getAttribute("SOC_LOGON_PERMISSIONS");
		String objectPath = super.getRequest().getContextPath();
		
		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		// 如果用户有事件管理权限
		if (permissionIds.indexOf(",11,") != -1) {
			
		    assetBuf = assetGroupManager.getTreeStyle(objectPath, groupId);
		    
			treeBuff = queryEventsGroupManager.createTrre(objectPath);
		}
		return SUCCESS;
	}

	/**
	 * 编辑自定义事件查询规则页面跳转
	 * 
	 * @return
	 */
	public String InsertCustom() {
		/**
		 * 如果编辑自定义规则成功将操作成功标记reFresh赋给fresh然后将操作成功标记置为false还原;
		 */
		//fresh = reFresh;
	    
		reFresh = "false";
		
		eventCategoryTagList = eventcategoryTagManager.query();
		
		eventAllTypeList = eventTypeTagManager.query();
		
		return SUCCESS;
	}

	/**
	 * 编辑自定义规则
	 * 
	 * @return
	 */
public String EditCustom() {   
	    
        QueryEvents customQueryEvents = new QueryEvents();
        
        customQueryEvents.setQueryEventsName(customQueryEventsName);
        customQueryEvents.setQueryEventsGroupId(183); // 默认级为自定义级
        customQueryEvents.setQueryEventsType(11); // 类型为自定义查询规则
        
        StringBuffer dataStr = new StringBuffer();
        addStr("count.custom_muchTable", dataStr);
        addStr("query.custom_muchTable", dataStr);
  
        
        if (threatValue == null) {
            addStr("1|2|3|4|5", dataStr);
        } else {
            StringBuffer temp = new StringBuffer();
            for (int i = 0; i < threatValue.length; i++) {
                if (temp.length() <= 0) {
                    temp.append(threatValue[i]);
                } else {
                    temp.append("|" + threatValue[i]);
                }
            }
            addStr(temp.toString(), dataStr);
        }

        addStr(sourceAdd, dataStr);
        
        addStr(String.valueOf(sourcePort), dataStr);
        
        addStr(targetAdd, dataStr);
        
        addStr(String.valueOf(targetPort), dataStr);
        
        if (eventsType == null || StringUtil.isEmpty(eventsType)) {
            addStr("", dataStr);
        } else {
            /*StringBuffer temp = new StringBuffer();
            
            
            try {
            for (int i = 0; i < eventsType.length; i++) {
                if (temp.length() <= 0) {
                    
                        temp.append(java.net.URLDecoder.decode(eventsType[i], "UTF-8"));
                    
                } else {
                    temp.append("|" +java.net.URLDecoder.decode(eventsType[i], "UTF-8") );
                }
            }
            
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            
            eventsType = eventsType.substring(0, eventsType.lastIndexOf("|"));
           
            addStr(eventsType.toString(), dataStr);
        }
        if(StringUtil.isNotEmpty(beginTime)){
        	
        	addStr(beginTime.replaceAll("-", ""), dataStr);
        }else{
        	addStr(beginTime, dataStr);
        }
        if(StringUtil.isNotEmpty(endTime)){
        	
        	addStr(endTime.replaceAll("-", ""), dataStr);
        }else{
        	addStr(endTime, dataStr);
        }
        
        
        if(protocol==null || StringUtil.isEmpty(protocol))
        {
            addStr("", dataStr);
        }
        else
        {
          protocol = protocol.substring(0,protocol.lastIndexOf("|"));
          
          addStr(protocol.toString(), dataStr);
        }
        addStr(String.valueOf(timeRange), dataStr);
        
        addStr(customQueryEventsName,dataStr);
        
        addStr("'", dataStr);
        
        if ("1".equals(actionType)) {
            
            conditions = dataStr.toString();

            return "query";
            
        } else {
            
            customQueryEvents.setQueryEventsConditions(dataStr.toString());
            
            long queryEventsId = queryEeventsManager
                    .insertQueryEventsRule(customQueryEvents);
            
            
            List<String> fieldList = new ArrayList<String>();
    		fieldList.add(customQueryEvents.getQueryEventsName() + "(" + customQueryEvents.getQueryEventsName() + ")");
            // 审计日志
    		auditManager.insertByInsertOperator(((User) this.getSession()
    				.getAttribute("SOC_LOGON_USER")).getUserId(), "自定义规则", super
    				.getRequest().getRemoteAddr(), fieldList);

    		
            
            if (queryEventsId != 0) {
                
                reFresh = "true";
               
                fresh = "true";
            }
        }

        return SUCCESS;
	}
	/**
	 * 查询自定义查询条件,修改用
	 * 
	 * @return
	 */



	
	//待修改
	
	public String queryEventsConditionsStr(){
		
	    
		String condition = queryEeventsManager.queryEventsConditionsStr(queryEventsId);
		eventAllTypeList = eventTypeTagManager.query();
		eventCategoryTagList = eventcategoryTagManager.query();
		
		//count.custom_muchTable,query.custom_muchTable,1|2|3|4|5,192.1.1.1,111,192.2.2.2,222,%E4%B,,,533,12
		//count.custom_muchTable,query.custom_muchTable,1|2|3,,111,,222,%E4%B,20130527,20130606,,12
		//按照内容拆分条件
		String []conditionArr = condition.split(",");
		
		String []threatValueTemp = conditionArr[2].split("\\|");
		//放等级
		threatValue = new int[5];
		
		if (threatValueTemp!=null) {
		    
			for (int i = 0; i < threatValueTemp.length; i++) {
				String temp = threatValueTemp[i];
			    if(StringUtil.isNotEmpty(temp)){
			    	threatValue[i] = Integer.parseInt(temp);
			    }
				
			}
		}
		
		//放ip
		this.sourceAdd = conditionArr[3];
		this.sourcePort = conditionArr[4];
		this.targetAdd = conditionArr[5];
		this.targetPort = conditionArr[6];
		
		//查询对应的事件列表
		eventsType = conditionArr[7];
		
		if(eventsType!=null && (!eventsType.equals("")))
		{
		    //String temp = eventsType.replaceAll("\\|", ",");
		    String temp[] = eventsType.split("\\|");
		    
		    StringBuffer temps = new StringBuffer();
		    for(int i=0;i<temp.length;i++)
		    {
		        if(i==0)
		        {
		            temps.append("'");
		            temps.append(temp[i]);
		            temps.append("'");
		        }
		        else
		        {
		            temps.append(",'");
                    temps.append(temp[i]);
                    temps.append("'");
		        }
		    }
		    
		    
		    eventRelTagList = eventcategoryTagManager.queryByKeys(temps.toString());
		}
		
		
		
//		
//		if(StringUtil.isNotEmpty(conditionArr[8])){
//	    
//			beginTime = conditionArr[8].substring(0, 4)+"-"+conditionArr[8].substring(4, 6)+"-"+conditionArr[8].substring(6);
//		}
//		if(StringUtil.isNotEmpty(conditionArr[9])){
//			String ti= conditionArr[9];
//			//System.out.println(ti);
//			endTime = conditionArr[9].substring(0, 4)+"-"+conditionArr[9].substring(4, 6)+"-"+conditionArr[9].substring(6);
//	}
//        
		
		//事件类型用的字段
         protocol = conditionArr[10];
         
         if(protocol!=null&&StringUtil.isNotEmpty(protocol))
         {
             String temp = protocol.replaceAll("\\|", ",");
             temp = "("+temp+")";
             
             eventRelTypeList  = eventTypeTagManager.queryByKeys(temp);    
         }
         String range = conditionArr[11];
         if(StringUtil.isNotEmpty(range)){
        	 timeRange = Integer.parseInt(range);
         }
         if(StringUtil.isNotEmpty(conditionArr[8])){
        	 beginTime=conditionArr[8];
         }
         if(StringUtil.isNotEmpty(conditionArr[9])){
        	 endTime=conditionArr[9];
         }
         if(StringUtil.isNotEmpty(conditionArr[12])){
        	 customQueryEventsName = conditionArr[12];
         }
         
	
		return SUCCESS;
	}
	/**
	 * 用来保存查询条件
	 * @return
	 */
	public String updateEventsConditions(){

		QueryEvents customQueryEvents = new QueryEvents();
		customQueryEvents.setQueryEventsName(customQueryEventsName);
		customQueryEvents.setQueryEventsId(queryEventsId); // 默认级为自定义级
		customQueryEvents.setQueryEventsType(11); // 类型为自定义查询规则
		StringBuffer dataStr = new StringBuffer();
		addStr("count.custom_muchTable", dataStr);
		addStr("query.custom_muchTable", dataStr);
		if (threatValue == null) {
			addStr("0", dataStr);
		} else {
			StringBuffer temp = new StringBuffer();
			for (int i = 0; i < threatValue.length; i++) {
				if (temp.length() <= 0) {
					temp.append(threatValue[i]);
				} else {
					temp.append("|" + threatValue[i]);
				}
			}
			addStr(temp.toString(), dataStr);
		}
		
		addStr(sourceAdd, dataStr);
		addStr(String.valueOf(sourcePort), dataStr);
		addStr(targetAdd, dataStr);
		addStr(String.valueOf(targetPort), dataStr);
		if (eventsType == null||eventsType.equals("")) {
			addStr("", dataStr);
		} else {
			/*StringBuffer temp = new StringBuffer();
			
			
			try {
			for (int i = 0; i < eventsType.length; i++) {
				if (temp.length() <= 0) {
					
						temp.append(java.net.URLDecoder.decode(eventsType[i], "UTF-8"));
					
				} else {
					temp.append("|" +java.net.URLDecoder.decode(eventsType[i], "UTF-8") );
				}
			}
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			addStr(temp.toString(), dataStr);*/
		    
		    eventsType = eventsType.substring(0, eventsType.lastIndexOf("|"));
		    
	           
            addStr(eventsType.toString(), dataStr);
		}
		addStr(beginTime.replaceAll("-", ""), dataStr);
		
		addStr(endTime.replaceAll("-", ""), dataStr);
		
		if(protocol==null || StringUtil.isEmpty(protocol))
		{
		    addStr("", dataStr);
		}
		else
		{
		   protocol = protocol.substring(0,protocol.lastIndexOf("|"));
		   
		   addStr(protocol, dataStr);
		}
		
		addStr(String.valueOf(timeRange), dataStr);
		
			customQueryEvents.setQueryEventsConditions(dataStr.toString()+","+customQueryEventsName);
			
			queryEventsId = queryEeventsManager
					.updateQueryEventsRule(customQueryEvents);
			 
			List<String> fieldList = new ArrayList<String>();
	    		fieldList.add(customQueryEvents.getQueryEventsName() + "(" + customQueryEvents.getQueryEventsName() + ")");
	            // 审计日志
	    		auditManager.insertByUpdateOperator(((User) this.getSession()
	    				.getAttribute("SOC_LOGON_USER")).getUserId(), "自定义规则", super
	    				.getRequest().getRemoteAddr(), fieldList);
			
	    		queryEventsType=11; 
		if (!lastCustomQueryEventsName.equals(customQueryEventsName)) {
			reFresh = "true";
		    fresh ="true";
		}else{
			reFresh = "false";
		}	
		
		return SUCCESS;
	}
	/**
	 * 自定义不保存查询
	 * 
	 * @return
	 */
	public String QueryCustonRule() {
		
		HttpServletRequest request = super.getRequest();
		Page page = null;
		int Num = 1;
		SearchResult<Events> sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds="0";
 			}
 			}
		sr = queryEeventsManager.queryCustomEventsRules(conditions, page,assetIds);


		if (sr != null) {
			relevanceAnalysisList = sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			relevanceAnalysisList = null;
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		request.setAttribute("Num", Num);
		
		if(identification == 0){
		    return SUCCESS;
		}else{
			eventAllTypeList = eventTypeTagManager.query();
			return "chart" ; 
		}
	}

	/**
	 * 将数据拼接为字符串
	 * 
	 * @param value
	 * @param strBuff
	 */
	public void addStr(String value, StringBuffer strBuff) {
		// 如果值为空添加单引号表示空
		if (StringUtil.isEmpty(value)) {
			// strBuff为空不添加豆号分隔
			if (strBuff.length() <= 0) {
				strBuff.append("");
			} else {
				strBuff.append("," + "");
			}
		} else {
			if (strBuff.length() <= 0) {
				strBuff.append(value);
			} else {
				strBuff.append("," + value);
			}
		}
	}

	/**
	 * 根据事件类型查询事件
	 * 
	 * @return
	 */
	public String QueryEvents() {
		log.info("[QueryEventsAction] Enter QueryEvents...");
		HttpServletRequest request = super.getRequest();
		Page page = null;
		int Num = 1;
		SearchResult<SummaryEvents> sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
		// eventCategoryTagList = eventcategoryTagManager.query();
		 Map map =new HashMap();
		 if(StringUtil.isNotEmpty(eventsType)){
			 map.put("eventsType", eventsType);
		 }
		 if (StringUtil.isNotEmpty(status)) {
			 if("1".equals(status)){
				 map.put("deviceIp", IpConvert.iPTransition(ip));
			 }
			 if("2".equals(status)){
				 map.put("sourceAdd", ip);
			 }
			 if("3".equals(status)){
				 map.put("targetAdd", ip);
			 }
		}
		 
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds="0";
 			}
 			}
		// 查询近期事件
		if (queryEventsType == 0) {
			sr = queryEeventsManager.queryRecentEvents(page);
		}
		
		// 查询内置的分类查询
		else {

			sr = queryEeventsManager.querySummaryEventsById(queryEventsId, page,checkids,assetIds,map);

		}

		if (sr != null) {
		    
			eventsList = (List<SummaryEvents>) sr.getList();

			/*for (int i = 0; i < eventsList.size(); i++) {
			    
				String name = eventsList.get(i).getEventsName();

				String type = eventsList.get(i).getEventsType();
				
				if (type!=null&&!type.equals("")) {
				    try {
				    	eventsList.get(i).setEventsCustomd1(Integer.parseInt(eventsList.get(i).getEventsType()));
					} catch (Exception e) {
						//log.info("事件类型转化失败 原因:事件类型原本是汉字无需转化");
					}
					
				}

				try {

					long temp = Long.valueOf(name);
                    String temp1 = GlobalConfig.eventTypeTag.get(temp);
                    if(!temp1.equals(null)&&!temp1.equals("")){
                    	eventsList.get(i).setEventsName(temp1);
                    }
					
				} catch (Exception e) {

					log.info("转化异常");

				}
				try {
				    
				    String temp =GlobalConfig.eventTypeTag.get(Long.valueOf(type));
				    if(!temp.equals(null)&&!temp.equals("")){
				    	eventsList.get(i).setEventsType(temp);
				    }
					
				} catch (Exception e) {
					log.warn("格式转换异常");
				}

			}*/
			request.setAttribute("Page", sr.getPage());
		} else {
			eventsList = null;
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		request.setAttribute("Num", Num);
		return SUCCESS;
	}

	/**
	 * 查询关联分析与自定义查询
	 * 
	 * @return
	 */
	public String QueryRelevanceAnalysis() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		int Num = 1;
		SearchResult<Events> sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds="0";
 			}
 			}
		if (queryEventsType == 11) {
			sr = queryEeventsManager
					.queryCustomEventsRules(queryEventsId, page,assetIds);
		} else {
			sr = queryEeventsManager
					.queryRelevanceAnalysis(queryEventsId, page);
		}

		if (sr != null) {
			relevanceAnalysisList = sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			relevanceAnalysisList = null;
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		request.setAttribute("Num", Num);
		return SUCCESS;
	}

	public String DelCustom() {
		switch (queryEventsType) {
		case 11:
			QueryEventsGroup queryeventsgroup = queryEeventsManager.queryCustomByID(queryEventsId);
			queryEeventsManager.delCustom(queryEventsId);
			
			List<String> fieldList = new ArrayList<String>();
    		fieldList.add(queryeventsgroup.getQueryEventsGroupName() + "(" + queryeventsgroup.getQueryEventsGroupName() + ")");
            // 审计日志
    		auditManager.insertByDeleteOperator(((User) this.getSession()
    				.getAttribute("SOC_LOGON_USER")).getUserId(), "自定义规则", super
    				.getRequest().getRemoteAddr(), fieldList);
			
			reFresh = "true"; // 标记操作成功刷新页面
			fresh = "true";
			break;
		}
		return SUCCESS;
	}

	/**
	 * 查询协议
	 */
	public void queryProtocol() {
		List<Map<String, Object>> protocolList = eventsManager.querProtocol();
		JSONArray json = JSONArray.fromObject(protocolList);
		try {
			getResponse().getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询事件名称
	 */
	public void queryEventDefinition(){
		List<Map<String, Object>> eventDefinitionList=summaryEventsManager.queryEventDefinition();
		JSONArray json = JSONArray.fromObject(eventDefinitionList);
		try {
			getResponse().getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询事件详情
	 * 
	 * @return
	 */
	public void queryEventsDetails() {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (tableName.equals("0")) {
			String temp = sdf.format(new Date());

			map.put("tableName", "tbl_" + temp);
		} else {
			map.put("tableName", "tbl_" + tableName);
		}
		map.put("identification", Long.valueOf(eventsLogIdentification));
		Events events = eventsManager.queryEvents(map);
		/*if (events != null) {
//			String name = events.getName();
//			
//			String type = events.getType();
			
			String categroy = events.getCateGory();
			try {

				long temp = Long.valueOf(name);
                String nametemp = GlobalConfig.eventTypeTag.get(temp);
                if(nametemp.equals(null)){
                	
                }else{
				events.setName(nametemp);
                }
			} catch (Exception e) {

				log.warn("类型转换错误！  ");

			}
			
			try {
			    String typeTemp =GlobalConfig.eventTypeTag.get(Long.valueOf(type));
				if(!typeTemp.equals(null)){
					events.setType(typeTemp);
				}
			    
			} catch (Exception e) {
				log.warn("类型转换错误！ ");
			}
			
			try{
			    String categroyTemp = GlobalConfig.eventCategoryTag.get(categroy);
			    events.setCateGory(categroyTemp);
			}catch(Exception e){
			   log.warn("类型转换错误!");   
			}
		}*/
		JSONObject json = JSONObject.fromObject(events);
		try {
			getResponse().getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <首页大屏展示显示事件的列表> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void queryEventScreen() {

		HttpServletRequest request = super.getRequest();
		Page page = null;
		int Num = 1;
		SearchResult<Events> sr = null;

		// 处理数据分页的起始条数

		String startIndex = request.getParameter("startIndex");
		String keyWord = request.getParameter("keyword");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}

		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtil.isNotBlank(keyWord)) {
			try {
				keyWord = java.net.URLDecoder.decode(keyWord, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyWord);
		}
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		if(groupid==1){
 			
 		}else{
 			String assetId= assetManager.queryIDSByUser(groupid);
 	 		if(assetId.equals("")){
 	 			assetId="0";
 	 		}
 	 		map.put("assetId", assetId);
 		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String tableName = sdf.format(new Date());

		map.put("tableName", "tbl_" + tableName);

		sr = eventsManager.queryRecentScreenEvents(map, page);

		List<Events> eventList = sr.getList();
		if (eventList != null) {
			eventList = (List<Events>) sr.getList();

			/*for (int i = 0; i < eventList.size(); i++) {
				String name = eventList.get(i).getName();
				String type = eventList.get(i).getType();

				try {

					long temp = Long.valueOf(name);
					
					String eventsName=GlobalConfig.eventTypeTag.get(temp);
					
					if(eventsName.equals(null)&&!eventsName.equals("")){
						eventList.get(i).setName(
								eventsName);
					}
					
				} catch (Exception e) {
					log.warn("格式转换异常");
				}
				try {

					eventList.get(i).setType(
							GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
				} catch (Exception e) {
					log.warn("格式转换异常");
				}
			}*/

		}
		if (StringUtil.isNotBlank(startIndex) || StringUtil.isNotBlank(keyWord)) {

			try {
				// 转换为JSON数据结构
				JSONArray jsonArray = JSONArray.fromObject(eventList);

				jsonArray.add(page);

				// Ajax返回
				getResponse().getWriter().write(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * if (sr != null)
		 * 
		 * { eventsList = (List<SummaryEvents>)sr.getList(); for(int i
		 * =0;i<eventsList.size();i++) { String name =
		 * eventsList.get(i).getEventsName(); String type =
		 * eventsList.get(i).getEventsType();
		 * 
		 * eventsList.get(i).setEventsName(GlobalConfig.eventTypeTag.get(Long.
		 * valueOf(name)));
		 * eventsList.get(i).setEventsType(GlobalConfig.eventTypeTag
		 * .get(Long.valueOf(type)));
		 * 
		 * } request.setAttribute("Page", sr.getPage()); }
		 */
	}
	
    
	/**
     * <根据传递的资产Id查询资产组下的资产>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryRelAssets()
    {
        log.info("[AssetAction] Enter method query()...");

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
        Map<String, Object> map = new HashMap<String, Object>();

        // 模糊查询
        if (request.getParameter("keyword") != null) {
            try {
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("keyword", keyword);
        }

        // 高级查询
        if (StringUtil.isNotBlank(selAssetName)) {
            
            map.put("selAssetName", selAssetName);
        }

        if (StringUtil.isNotBlank(selAssetIps)) {
            
            map.put("selAssetIps", selAssetIps);
        }

        if (request.getParameter("assetGroupId") != null
                && !request.getParameter("assetGroupId").equals("")) {
            if(Integer.parseInt(assetGroupId)==1){
                
            }else{
                 assetGroupId = request.getParameter("assetGroupId");
                 
                 map.put("assetGroupId", Integer.parseInt(assetGroupId));
            }
            
            
        }

            sr = assetManager.query(map, page);
        

        if (sr != null) {
            assetList = (List<Asset>) sr.getList();
            request.setAttribute("Page", sr.getPage());
        }

        return SUCCESS;

    }
    
    /**
     * <根据资产Id查询事件>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryByAssetEvents()
    {
        StringBuffer dataStr = new StringBuffer();
        addStr("count.queryEventByasset", dataStr);
        addStr("query.queryEventByasset", dataStr);
        
        if (threatValue == null) {
            addStr("1|2|3|4|5", dataStr);
        } else {
            StringBuffer temp = new StringBuffer();
            for (int i = 0; i < threatValue.length; i++) {
                if (temp.length() <= 0) {
                    temp.append(threatValue[i]);
                } else {
                    temp.append("|" + threatValue[i]);
                }
            }
            addStr(temp.toString(), dataStr);
        }
        
        if(assetId!=0)
        {
            addStr(String.valueOf(assetId),dataStr);
        }
        
       
    		
    		
    		
    	
    		if("2".equals(status)){
        		//	map.put("sourceAdd", ip);
    			addStr(ip, dataStr);
        		}else{
        			addStr(sourceAdd, dataStr);
        		}
        
        addStr(String.valueOf(sourcePort), dataStr);
        if("3".equals(status)){
			//map.put("targetAdd", ip);
        	addStr(ip, dataStr);
		}else{
			addStr(targetAdd, dataStr);
		}
        
        addStr(String.valueOf(targetPort), dataStr);
        
        //事件类别
        if (eventsType == null || StringUtil.isEmpty(eventsType)) {
            
            addStr("", dataStr);
       
        } else {
            
            if(eventsType.contains("|"))
            {
                eventsType = eventsType.substring(0, eventsType.lastIndexOf("|"));
            }
           
            addStr(eventsType.toString(), dataStr);
        }
        
        if(StringUtil.isNotEmpty(beginTime))
        {
          addStr(beginTime.replaceAll("-", ""), dataStr);
        }
        else
        {
            addStr("", dataStr);
        }
        if(StringUtil.isNotEmpty(endTime))
        {
        addStr(endTime.replaceAll("-", ""), dataStr);
        }
        else
        {
          addStr("", dataStr);
        }
        
        //事件类型
        if(protocol==null || StringUtil.isEmpty(protocol))
        {
            addStr("", dataStr);
        }
        else
        {
          if(protocol.contains("|"))
          {
             protocol = protocol.substring(0,protocol.lastIndexOf("|"));
          }
          addStr(protocol.toString(), dataStr);
        }
        
        addStr(String.valueOf(timeRange), dataStr);
        
        //添加设备Ip
        if("1".equals(status)){
			// map.put("deviceIp", IpConvert.iPTransition(ip));
        	addStr(ip,dataStr);
		}else{
			addStr(deviceIp,dataStr);
		}
        
        if(flag!=0)
        {
            addStr(String.valueOf(flag),dataStr);
        }
        else
        {
            addStr("0",dataStr);
        }
        
        addStr(operateOrder,dataStr);
        
        addStr("'", dataStr);
        
        conditions = dataStr.toString();
        
        return SUCCESS;
    }
    
    /**
     * <根据条件查询对应的eventlist>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryByConditionEvents()
    {
        HttpServletRequest request = super.getRequest();
        Page page = null;
        int Num = 1;
        SearchResult<Events> sr = null;
        
        assetId = Integer.valueOf(conditions.split(",")[3]);
        
        String queryFlag = conditions.split(",")[14];
        
        if(queryFlag.equals("1"))
        {
            sourceAdd = conditions.split(",")[4];
            
            targetAdd = conditions.split(",")[6];
            
            deviceIp  = conditions.split(",")[13];
            
            eventsType = conditions.split(",")[8];
            
            protocol = conditions.split(",")[11];
        }
        
        eventCategoryTagList = eventcategoryTagManager.query();
        
        eventAllTypeList = eventTypeTagManager.query();
        
        // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");

        if (StringUtil.isNotBlank(startIndex)) {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
            Num += Integer.valueOf(startIndex);
        } else {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }

        sr = queryEeventsManager.queryRelAssetEvents(conditions, page);
        
        //relUserNames = queryEeventsManager.queryRelUserNames(conditions);
        
        if (sr != null) {
            
            relevanceAnalysisList = sr.getList();
            
            request.setAttribute("Page", sr.getPage());
        } else {
            
            relevanceAnalysisList = null;
            
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        
        request.setAttribute("Num", Num);
        
        return SUCCESS;
    }
    
    /**
     * <高级搜索的查询条件>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String events_assetquery()
    {
        eventCategoryTagList = eventcategoryTagManager.query();
        
        eventAllTypeList = eventTypeTagManager.query();
        
        
        return SUCCESS;
    }
    
	/**
     * <导出excel表>
     * <把事件信息导出excel>
     * @see [ExcelAudit,StringUtil]
     */
    public void exportEventExcel()
    {
        LOG.info("[queryEventsAction] enter method exportEventExcel() ...");
        HttpServletRequest request = super.getRequest();
        HttpServletResponse response = super.getResponse();
        ExcelEventMasage excelEventMasage = new ExcelEventMasage();
        biaozhi=(String) request.getParameter("biaozhi");
        long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds=null;
 			}
 			}
        if("1".equals(biaozhi)){
        	conditions=queryEeventsManager.queryEventsConditionsStr(queryEventsId);
        	//relevanceAnalysisList=queryEeventsManager.queryExportEventsRules(queryEventsId, assetIds, checkids);
           //excelEventMasage.exportEvents(relevanceAnalysisList, "自定义查询事件信息数据");
           excelEventMasage.exportEvents12(queryEeventsManager, conditions, "自定义查询事件信息数据", assetIds, checkids); 
        }
        if("2".equals(biaozhi)){
        	//relevanceAnalysisList = queryEeventsManager.queryExportEventsByRules(conditions, assetIds, checkids);
        	//excelEventMasage.exportEvents(relevanceAnalysisList, "自定义查询事件信息数据");
        	excelEventMasage.exportEvents12(queryEeventsManager, conditions, "自定义查询事件信息数据", assetIds, checkids); 
        }
        if("3".equals(biaozhi)){
        	//relevanceAnalysisList = queryEeventsManager.queryExportRelAssetEvents(conditions, checkids);
        	//excelEventMasage.exportEvents(relevanceAnalysisList, "按资产查询事件信息数据");
        	excelEventMasage.exportEventsByAsset(queryEeventsManager, conditions, "按资产查询事件", checkids);
        }

        if(StringUtil.isEmpty(biaozhi)){
        	Map map = new HashMap();
        	if (StringUtil.isNotEmpty(checkids)) {
				map.put("ids", checkids);
			}else{
				if(StringUtil.isNotEmpty(eventsType)){
					 map.put("eventsType", Integer.parseInt(eventsType));
				 }
				
					map.put("assetId", assetIds);
					
				if (StringUtil.isNotEmpty(ip)) {
					
				
        	if (StringUtil.isNotEmpty(status)) {
   			 if("1".equals(status)){
   				 map.put("deviceIp", IpConvert.iPTransition(ip));
   			 }
   			 if("2".equals(status)){
   				 map.put("sourceAdd", ip);
   			 }
   			 if("3".equals(status)){
   				 map.put("targetAdd", ip);
   			 }
   		}
				}
			}
        eventsList=queryEeventsManager.queryEventForExport(queryEventsId,map);
        	/*for (int i = 0; i < eventsList.size(); i++) {
		    
			String name = eventsList.get(i).getEventsName();

			String type = eventsList.get(i).getEventsType();
			
			if (type!=null&&!type.equals("")) {
			    
				eventsList.get(i).setEventsCustomd1(Integer.parseInt(eventsList.get(i).getEventsType()));
			}

			try {

				long temp = Long.valueOf(name);
                String temp1 = GlobalConfig.eventTypeTag.get(temp);
				eventsList.get(i).setEventsName(temp1);

			} catch (Exception e) {

				log.info("转化异常");

			}
			try {
			    String typeTemp = GlobalConfig.eventTypeTag.get(Long.valueOf(type));
				eventsList.get(i).setEventsType(typeTemp);
			} catch (Exception e) {
				log.warn("格式转换异常");
			}

		}*/
        excelEventMasage.export(eventsList, "事件信息数据表",queryEeventsManager);
    }
		
        try
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
            // 中文文件名需要编码
            String fileName = "eventMessagelog_" + sdf.format(new Date());
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;Filename=" + fileName + ".xls");
            OutputStream os = response.getOutputStream();
            excelEventMasage.getGwb().write(os);
            os.flush();
            os.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	
		// 导出doc
    public String exportEvent() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		HttpServletResponse resp = ServletActionContext.getResponse();
		boolean isPdf = false;// 判断此类型是不是pdf
		HttpServletRequest request = super.getRequest();
		  long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
			String assetIds = null;
	 		if(groupid==1){
	 			}else{
	 			 assetIds= assetManager.queryIDSByUser(groupid);
	 			if(assetIds.equals("")){
	 				assetIds="0";
	 			}
	 			}
		createReportFile(eventReportId, reportType, isPdf,assetIds,biaozhi);
		OutputStream out;// 输出响应正文的输出流
		InputStream in;// 读取本地文件的输入流
		// 获得本地输入流
		String fileName = getReportNameById(eventReportId, reportType);
		File file = new File(path+"reportFile"+File.separator + fileName);
		try {
			in = new FileInputStream(file);
			// 设置响应正文的MIME类型
			resp.setContentType("Content-Disposition;charset=utf-8");
			resp.setHeader("Content-Disposition", "attachment;" + " filename="
					+ new String(file.getName().getBytes(), "ISO-8859-1"));
			// 把本地文件发送给客户端
			out = resp.getOutputStream();
			int byteRead = 0;
			byte[] buffer = new byte[1024];
			while ((byteRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteRead);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void createReportFile(long eventReportId, String reportType,
			boolean isPdf,String assetId,String biaozhi1 ) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		if (eventReportId==12) {
			Page page = null;
			HttpServletRequest request = super.getRequest();
			Map map = new HashMap();
			if (StringUtil.isNotEmpty(checkids)) {
				map.put("ids", checkids);
			}else{
				if(StringUtil.isNotEmpty(eventsType)){
					 map.put("eventsType", Integer.parseInt(eventsType));
				 }
        	map.put("assetId", assetId);
        	if (StringUtil.isNotEmpty(ip)) {
				
			
        	if (StringUtil.isNotEmpty(status)) {
   			 if("1".equals(status)){
   				 map.put("deviceIp", IpConvert.iPTransition(ip));
   			 }
   			 if("2".equals(status)){
   				 map.put("sourceAdd", ip);
   			 }
   			 if("3".equals(status)){
   				 map.put("targetAdd", ip);
   			 }
   		}}
			}
			eventsList = queryEeventsManager.queryEventForExport(queryEventsId, map);
			
				/*for (int i = 0; i < eventsList.size(); i++) {
				    
					String name = eventsList.get(i).getEventsName();

					String type = eventsList.get(i).getEventsType();
					
					if (type!=null&&!type.equals("")) {
						eventsList.get(i).setEventsCustomd1(Integer.parseInt(eventsList.get(i).getEventsType()));
					}

					try {

						long temp = Long.valueOf(name);
                        String temps = GlobalConfig.eventTypeTag.get(temp);
						eventsList.get(i).setEventsName(temps);

					} catch (Exception e) {

						log.info("转化异常");

					}
					try {
					    String typeTemp = GlobalConfig.eventTypeTag.get(Long.valueOf(type));
						eventsList.get(i).setEventsType(typeTemp);
					} catch (Exception e) {
						log.warn("格式转换异常");
					}
					if (eventsList.get(i).getEventsType()==null) {
						eventsList.get(i).setEventsType("未知类型");
					}

				}*/
			mapStatic.put("eventsList",eventsList);
		}else{
			if(biaozhi.equals("3")){
				List<List<Events>> allList=  queryEeventsManager.excelEventsByAsset(conditions, checkids);
	        	mapStatic.put("relevanceAnalysisList",allList);
	        }
			if(biaozhi1.equals("2")){
				 List<List<Events>> allList=  queryEeventsManager.queryCustomEventsRules1(conditions, assetId, checkids);
				mapStatic.put("relevanceAnalysisList",allList);
			}
			if(biaozhi1.equals("1"))	{
				conditions=queryEeventsManager.queryEventsConditionsStr(queryEventsId);
				List<List<Events>> allList=  queryEeventsManager.queryCustomEventsRules1(conditions, assetId, checkids);
				mapStatic.put("relevanceAnalysisList",allList);
			}
		}
		
		if (isPdf) {// 如果是pdf,需要加载html模板
			reportType = "html";
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path+"reportFile"+File.separator
							+ getReportNameById(eventReportId, reportType))),
					"utf-8"));
			// map.put("image", new File(path +
			// "/reportFormImages/diag1.jpg"));//把图片加进去
			// 这里根据生成的文件类型加载不同的模板 因为以后report6到9导出doc的时候加载的是wrod模板 其他都是html模板
			// 这里要判断
			if (eventReportId >= 6 && eventReportId <= 13) {
				fm.loadTemplate("freemarkerEvents" + reportType + eventReportId
						+ ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else {
				fm.loadTemplate("freemarkerhtml" + eventReportId + ".ftl",
						mapStatic, "template", out);// 生成文件的代码
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private String getReportNameById(long eventReportId, String reportType) {
		String fileName = null;
		try {
		switch ((int) eventReportId) {
		case 2:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25AE%25A1%25E7%2590%2586%25E5%25B9%25B3%25E5%258F%25B0%25E6%259C%2588%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 3:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E8%25BF%2590%25E8%25A1%258C%25E7%258A%25B6%25E6%2580%2581%25E6%258A%25A5%25E5%25AE%258C%25E6%2588%2590%25E8%25A1%25A8", "GBK")+"." + reportType;
			break;
		case 4:
			fileName = java.net.URLDecoder.decode("%25E6%25BC%258F%25E6%25B4%259E%25E6%2589%25AB%25E6%258F%258F%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+"." + reportType;
			break;
		case 5:
			fileName = java.net.URLDecoder.decode("%25E8%25AE%25BE%25E5%25A4%2587%25E6%2598%258E%25E7%25BB%2586%25E7%25BB%259F%25E8%25AE%25A1%25E6%2597%25A5%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 6:
			fileName = java.net.URLDecoder.decode("%25E4%25BA%258B%25E4%25BB%25B6%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E6%259C%2588%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 7:
			fileName =java.net.URLDecoder.decode("%25E5%259F%259F%25E9%25A3%258E%25E9%2599%25A9%25E6%2598%258E%25E7%25BB%2586%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 8:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E5%2591%25A8%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 9:
			fileName =java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E7%25B1%25BB%25E5%259E%258B%25E7%25BB%259F%25E8%25AE%25A1%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 10:
			fileName =java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E5%2585%25AC%25E5%2591%258A%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 11:
			fileName =java.net.URLDecoder.decode("%25E5%2591%258A%25E8%25AD%25A6%25E4%25BF%25A1%25E6%2581%25AF%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 12:
			fileName =java.net.URLDecoder.decode("%25E4%25BA%258B%25E4%25BB%25B6%25E4%25BF%25A1%25E6%2581%25AF%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 13:
			fileName =java.net.URLDecoder.decode("%25E8%2587%25AA%25E5%25AE%259A%25E4%25B9%2589%25E6%259F%25A5%25E8%25AF%25A2%25E4%25BA%258B%25E4%25BB%25B6%25E4%25BF%25A1%25E6%2581%25AF", "GBK")+ "." + reportType;
			break;
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * 查询用户根据用户Id
	 * @return String
	 */
	public String queryUserById(){
		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId() ;
		user = userManager.queryByUserId(groupId); 
		return SUCCESS ; 
	}
	
	/**
	 * 修改用户要求显示的字段
	 * @return String
	 */
	public String updateFaild(){
		long userId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId() ;
		Map map = new HashMap() ; 
		map.put("userId", userId);
		if(strFaild.equals("") && StringUtil.isEmpty(strFaild)){
			map.put("showEventFiled", "all");
		}else{
			map.put("showEventFiled", strFaild);
		}
		userManager.updateField(map);
		return SUCCESS ;
	}
	
	/**
	 * 根据传递过来的事件类别的名称，查询类型的Key
	 * @return
	 */
	public String queryKeyByCategoryName(){
		
		if(StringUtil.isNotEmpty(categoryName)){
			categoryKey  = eventcategoryTagManager.queryKeyBYCategoryName(categoryName) ; 
		}
		
		return SUCCESS ; 
	}
	
	/**
	 * 根据事件类型查询ID
	 * @return
	 */
	public String queryTypeIdByName(){
		
		if(StringUtil.isNotEmpty(eventTypeName)){
			protocol = String.valueOf(eventTypeTagManager.eventTypeIdByName(eventTypeName)) ; 
		}
		
		return SUCCESS ; 
		
	}
	
	/**
	 * Home图表页面中查询的方法
	 * @return
	 */
	public String queryEventToQueryPage(){
		
		log.info("[QueryEventsAction] enter QueryEventsAction...");
		// 对前台获得的等级和关键字中文编码进行处理

		// 对分页的处理
		Page page = null;
		int Num = 1 ; 
		HttpServletRequest request = super.getRequest();
		SearchResult sr = new SearchResult();

		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(15, 0);
		}
		// 用来存放查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "tbl_"+new SimpleDateFormat("yyyyMMdd").format(new Date()));
		
		
		if (eventsLevel != 0)
        {
            map.put("priority", eventsLevel);
        }
        if (StringUtil.isNotEmpty(driverType))
        {
            map.put("devproduct", driverType);
        }
        if (StringUtil.isNotEmpty(eventsType))
        {
            map.put("type", eventsType);
        }
        if(StringUtil.isNotEmpty(categoryName)){
        	map.put("category",categoryName);
        }


		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		
		if(groupid==1){
 			
 		}else{
 			String assetId= assetManager.queryIDSByUser(groupid);
 	 		if(assetId.equals("")){
 	 			assetId="0";
 	 		}
 	 		map.put("assetId", assetId);
 		}
		sr = queryEeventsManager.queryRecentEvents(map, page);
		
		//sr = queryEeventsManager.queryCustomEventsRules(conditions, page,assetIds);
		
		
		relevanceAnalysisList = (List<Events>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		request.setAttribute("Num", Num);
			
		eventAllTypeList = eventTypeTagManager.query();

		return SUCCESS;

	
	}
	

	public String getTreeBuff() {
		return treeBuff;
	}

	public void setTreeBuff(String treeBuff) {
		this.treeBuff = treeBuff;
	}

	public long getQueryEventsId() {
		return queryEventsId;
	}

	public void setQueryEventsId(long queryEventsId) {
		this.queryEventsId = queryEventsId;
	}

	public List<SummaryEvents> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<SummaryEvents> eventsList) {
		this.eventsList = eventsList;
	}

	public int getQueryEventsType() {
		return queryEventsType;
	}

	public List<Asset> getAssetList()
    {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList)
    {
        this.assetList = assetList;
    }

    public void setQueryEventsType(int queryEventsType) {
		this.queryEventsType = queryEventsType;
	}

	public String getSourceAdd() {
		return sourceAdd;
	}

	public void setSourceAdd(String sourceAdd) {
		this.sourceAdd = sourceAdd;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getTargetAdd() {
		return targetAdd;
	}

	public void setTargetAdd(String targetAdd) {
		this.targetAdd = targetAdd;
	}

	public String getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
	}

	public List<QueryEvents> getCustomEventsList() {
		return customEventsList;
	}

	public void setCustomEventsList(List<QueryEvents> customEventsList) {
		this.customEventsList = customEventsList;
	}

	public long getCustomQueryEventsId() {
		return customQueryEventsId;
	}

	public void setCustomQueryEventsId(long customQueryEventsId) {
		this.customQueryEventsId = customQueryEventsId;
	}

	public String getCustomQueryEventsName() {
		return customQueryEventsName;
	}

	public void setCustomQueryEventsName(String customQueryEventsName) {
		this.customQueryEventsName = customQueryEventsName;
	}

	public int[] getThreatValue() {
		return threatValue;
	}

	public void setThreatValue(int[] threatValue) {
		this.threatValue = threatValue;
	}


	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(int timeRange) {
		this.timeRange = timeRange;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReFresh() {
		return reFresh;
	}

	public void setReFresh(String reFresh) {
		this.reFresh = reFresh;
	}

	public String getFresh() {
		return fresh;
	}

	public void setFresh(String fresh) {
		this.fresh = fresh;
	}

	public QueryEventsService getQueryEeventsManager() {
		return queryEeventsManager;
	}

	public void setQueryEeventsManager(QueryEventsService queryEeventsManager) {
		this.queryEeventsManager = queryEeventsManager;
	}

	public QueryEventsGroupService getQueryEventsGroupManager() {
		return queryEventsGroupManager;
	}

	public void setQueryEventsGroupManager(
			QueryEventsGroupService queryEventsGroupManager) {
		this.queryEventsGroupManager = queryEventsGroupManager;
	}

	public EventsService getEventsManager() {
		return eventsManager;
	}

	public void setEventsManager(EventsService eventsManager) {
		this.eventsManager = eventsManager;
	}

	public long getEventsLogIdentification() {
		return eventsLogIdentification;
	}

	public void setEventsLogIdentification(long eventsLogIdentification) {
		this.eventsLogIdentification = eventsLogIdentification;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public OriginalLogService getOriginalLogManager() {
		return originalLogManager;
	}

	public void setOriginalLogManager(OriginalLogService originalLogManager) {
		this.originalLogManager = originalLogManager;
	}

	public List<OriginalEvents> getOriginalEventsList() {
		return originalEventsList;
	}

	public void setOriginalEventsList(List<OriginalEvents> originalEventsList) {
		this.originalEventsList = originalEventsList;
	}

	public List<Events> getRelevanceAnalysisList() {
		return relevanceAnalysisList;
	}

	public void setRelevanceAnalysisList(List<Events> relevanceAnalysisList) {
		this.relevanceAnalysisList = relevanceAnalysisList;
	}

	public SummaryEventsService getSummaryEventsManager() {
		return summaryEventsManager;
	}

	public void setSummaryEventsManager(SummaryEventsService summaryEventsManager) {
		this.summaryEventsManager = summaryEventsManager;
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public String getEventsTypeString() {
		return eventsTypeString;
	}

	public void setEventsTypeString(String eventsTypeString) {
		this.eventsTypeString = eventsTypeString;
	}

	public String getLastCustomQueryEventsName() {
		return lastCustomQueryEventsName;
	}

	public void setLastCustomQueryEventsName(String lastCustomQueryEventsName) {
		this.lastCustomQueryEventsName = lastCustomQueryEventsName;
	}


	public String getCheckids() {
		return checkids;
	}

	public void setCheckids(String checkids) {
		this.checkids = checkids;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public long getEventReportId() {
		return eventReportId;
	}

	public void setEventReportId(long eventReportId) {
		this.eventReportId = eventReportId;
	}

    public List<eventcategorytag> getEventRelTagList()
    {
        return eventRelTagList;
    }

    public void setEventRelTagList(List<eventcategorytag> eventRelTagList)
    {
        this.eventRelTagList = eventRelTagList;
    }

    public EventTypeTagService getEventTypeTagManager()
    {
        return eventTypeTagManager;
    }

    public void setEventTypeTagManager(EventTypeTagService eventTypeTagManager)
    {
        this.eventTypeTagManager = eventTypeTagManager;
    }

    public List<EventTypeTag> getEventAllTypeList()
    {
        return eventAllTypeList;
    }

    public void setEventAllTypeList(List<EventTypeTag> eventAllTypeList)
    {
        this.eventAllTypeList = eventAllTypeList;
    }

    public List<EventTypeTag> getEventRelTypeList()
    {
        return eventRelTypeList;
    }

    public void setEventRelTypeList(List<EventTypeTag> eventRelTypeList)
    {
        this.eventRelTypeList = eventRelTypeList;
    }
	
    public EventCategoryTagService getEventcategoryTagManager()
    {
        return eventcategoryTagManager;
    }

    public void setEventcategoryTagManager(EventCategoryTagService eventcategoryTagManager)
    {
        this.eventcategoryTagManager = eventcategoryTagManager;
    }

    public List<eventcategorytag> getEventCategoryTagList()
    {
        return eventCategoryTagList;
    }

    public void setEventCategoryTagList(List<eventcategorytag> eventCategoryTagList)
    {
        this.eventCategoryTagList = eventCategoryTagList;
    }

    public void setEventsType(String eventsType)
    {
        this.eventsType = eventsType;
    }

    public String getAssetBuf()
    {
        return assetBuf;
    }

    public void setAssetBuf(String assetBuf)
    {
        this.assetBuf = assetBuf;
    }

    public AssetGroupService getAssetGroupManager()
    {
        return assetGroupManager;
    }

    public void setAssetGroupManager(AssetGroupService assetGroupManager)
    {
        this.assetGroupManager = assetGroupManager;
    }

    public String getAssetGroupId()
    {
        return assetGroupId;
    }

    public void setAssetGroupId(String assetGroupId)
    {
        this.assetGroupId = assetGroupId;
    }
    
    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getSelAssetName()
    {
        return selAssetName;
    }

    public void setSelAssetName(String selAssetName)
    {
        this.selAssetName = selAssetName;
    }

    public String getSelAssetIps()
    {
        return selAssetIps;
    }

    public void setSelAssetIps(String selAssetIps)
    {
        this.selAssetIps = selAssetIps;
    }

    public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public String getEventsType()
    {
        return eventsType;
    }
    
    public int getAssetId()
    {
        return assetId;
    }

    public void setAssetId(int assetId)
    {
        this.assetId = assetId;
    }

    public List<String> getRelUserNames()
    {
        return relUserNames;
    }

    public void setRelUserNames(List<String> relUserNames)
    {
        this.relUserNames = relUserNames;
    }

    public String getDeviceIp()
    {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp)
    {
        this.deviceIp = deviceIp;
    }

    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    public String getOperateOrder()
    {
        return operateOrder;
    }

    public void setOperateOrder(String operateOrder)
    {
        this.operateOrder = operateOrder;
    }

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrFaild() {
		return strFaild;
	}

	public void setStrFaild(String strFaild) {
		this.strFaild = strFaild;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public int getEventsLevel() {
		return eventsLevel;
	}

	public void setEventsLevel(int eventsLevel) {
		this.eventsLevel = eventsLevel;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getBiaozhi() {
		return biaozhi;
	}

	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
    
    
}
