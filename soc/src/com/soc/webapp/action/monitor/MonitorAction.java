package com.soc.webapp.action.monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.soc.model.monitor.Monitor;
import com.soc.model.monitor.MonitorGroup;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.events.QueryEventsService;
import com.soc.service.monitor.MonitorGroupService;
import com.soc.service.monitor.MonitorService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <监控表的管理> 
 * <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-10-28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorAction extends BaseAction
{
    
    private JSONArray jsonArray;
    
    // 监控管理操作类
    private MonitorService monitorManager;
    
    // 监控组关联的监控列表
    private List<Monitor> monitorList;
    
    // 监控组的id
    private long monitorGroupId;
    
    // 所有监控表的list
    private List<Monitor> allMonitorList;
    
    // 监控表的id
    private int monitorId;
    
    // 监控实体类
    private Monitor monitor;
    
    // 监控组控制类
    private MonitorGroupService monitorGroupManager;
    
    // 监控组实体
    private MonitorGroup monitorGroup;
    
    // 监控表ids
    private String monitorIds;
    
    // 事件类型
    private int eventType;
    
    
    private String devType;
    //private String count;
    
    //查询条件的类型
    private String type;
    
  
	// sqlmap内的key
    private String sqlkey;
    
    //策略
    private String policy;
    
    // 事件查询业务类
    private QueryEventsService queryEventsManager;
    
    //内部审计
    private AuditService auditManager;
    
    //自定义监控topN
    private int topN;
    
    //自定义监控图表X轴显示
    private String chatX;
	// 用于查询的关键字
	private String keyword;

	// 传过来的字符串数组 用来批量删除
	private String ids;

	private String relMonitorId ; 
	
	// 判断是否是来自排序插件sortable的请求,如果 saveSortFlag=1 不进行页面跳转 
	private int saveSortFlag ; 
	
	private String msg ; 
	
    /**
	 * 查询自定义监控图表的list界面
	 * @return
	 */
	public String customMonitorList(){
		log.info("[MonitorGroupAction] enter method customMonitorList....");
		// 对前台获得的等级和关键字中文编码进行处理
		if (keyword!=null) {
			keyword = keyword.trim();	
		}
		
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;

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
		if(StringUtil.isNotEmpty(keyword)){
		try {
			keyword = java.net.URLDecoder.decode(keyword,"UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		map.put("keyword", keyword);
		sr = this.monitorManager.queryMonitorCustom(map, page);
		if (sr != null) {
			monitorList = (List<Monitor>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}

		return SUCCESS;
	}
	/**
     * <根据监控组id查询关联的监控表> 
     * <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryMonitor()
    {
        log.info("MonitorAction Enter method queryMonitor....");
        if (monitorGroupId == 0)
        {
            monitorGroupId = 2;
        }
        
        monitorGroup = monitorGroupManager.queryById(monitorGroupId);
        
        monitorList = new ArrayList<Monitor>();
         
        
        if (StringUtil.isNotBlank(monitorGroup.getRelMonitorIds()))
        {
            String[] checked = monitorGroup.getRelMonitorIds().split(",");
            String check;
            for (int i = 0; i < 9; i++)
            {
                check = checked[i];
                
                if (!check.equals("0"))
                {
                    monitor = monitorManager.queryById(Long.valueOf(check));
                    if (monitor != null)
                    {
                        monitor.setStation(i);
                        monitorList.add(monitor);
                    }
                }
                
            }
        }
        jsonArray = JSONArray.fromObject(monitorList);
        
        if (!monitorList.isEmpty())
        {
            monitorList.clear();
        }
        return SUCCESS;
    }
    
    /**
     * <编辑某个组的监控表> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String editMonitor()
    {
        
        log.info("MonitorAction Enter method editMonitor.....");
        
        monitorGroup = monitorGroupManager.queryById(monitorGroupId);
        
        if (StringUtil.isNotBlank(monitorGroup.getRelMonitorIds()))
        {
            String[] checked = monitorGroup.getRelMonitorIds().split(",");
            
            monitorList = new ArrayList<Monitor>();
            
            String check;
            
            for (int i = 0; i < 9; i++)
            {
                check = checked[i];
                if (!check.equals("0"))
                {
                    monitor = monitorManager.queryById(Long.valueOf(check));
                    if (monitor != null)
                    {
                        monitor.setStation(i);
                        // monitor.setObject(s);
                        monitorList.add(monitor);
                    }
                }
                
            }
        }
        
        jsonArray = JSONArray.fromObject(monitorList);
        
        monitorList.clear();
        
        //allMonitorList = monitorManager.queryAll();
        
        return SUCCESS;
    }
    
    /**
     * <刷新一个图表> 
     * <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void queryOneMonitor()
    {
        log.info("MonitorAction Enter method queryOneMonitor....");
        //定义一个json格式的变量
        JSONArray jsonArray = null;
        
        Map map = new HashMap();
        
        if(StringUtil.isNotEmpty(devType))
        {
            map.put("devType", devType);
        }
        
        if(StringUtil.isNotEmpty(policy))
        {
            map.put("policy", policy);
            
        }
        
        if(StringUtil.isNotEmpty(type))
        {
        	map.put("type", type);
        }
        
        map.put("value", System.currentTimeMillis()-24*60*60*1000);
        
        //获得图表的数据
        Object s = getObject(map);
        
        // 转换为JSON数据结构
        jsonArray = JSONArray.fromObject(s);
        
        // Ajax返回
        try
        {
            if (jsonArray != null)
            {
                
                getResponse().getWriter().write(jsonArray.toString());
            }
            else
            {
                getResponse().getWriter().write("");
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * <添加一个图表> 
     * <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void addOneMonitor()
    {
        log.info("MonitorAction Enter method addOneMonitor.....");
        //根据监控表的id查询监控表
        monitor = monitorManager.queryById(monitorId);
        
        //定义临时json格式的数据
        JSONArray jsonArray = null;
        
        monitorList = new ArrayList<Monitor>();
        
        monitorList.add(monitor);
        
        //转化成json格式的数据
        jsonArray = JSONArray.fromObject(monitorList);
        
        try
        {
            
            if (jsonArray != null)
            {
                log.info(jsonArray.toString());
                getResponse().getWriter().write(jsonArray.toString());
            }
            else
            {
                getResponse().getWriter().write("");
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * <保存监控组的设置> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveMonitor()
    {
        log.info("MonitorAction Enter method saveMonitor....");
        
        Map map = new HashMap();
        
        List<String> fieldList = new ArrayList<String>();
        
        MonitorGroup groupObject = monitorGroupManager.queryById(monitorGroupId);
        
        fieldList.add(groupObject.getMonitorGroupName() + "(" + groupObject.getMonitorGroupName() + ")");
        
        if (StringUtil.isNotBlank(monitorIds))
        {
            
            map.put("groupId", monitorGroupId);
            map.put("ids", monitorIds);
            monitorGroupManager.updateRelMonitors(map);
        }
        
        if(saveSortFlag == 1){
     	   return "savesort" ;
        }
        
        //内部审计
        auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
            "监控组",
            super.getRequest().getRemoteAddr(),
            fieldList);
        //syslog日志
       /* String logString = "";
        logString =
            "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:修改监控组";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改监控组");
        
       
        
        return SUCCESS;
    }
    
    /**
     * <获得监控表内的数据> <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Object getObject(Map map)
    {
        Object s = "";
        
        switch (eventType)
        {
        
           // 认证类的效果
            case 1:
                s = queryEventsManager.getMonitorCount(sqlkey, map);
                
                break;
            
            // 服务器类的事件
            case 2:
            s = queryEventsManager.getMonitorServerCount(sqlkey,map);
                
                break;
                
                // 自定义的
                case 3:
                	map.put("chatX", chatX);//要统计的东西
                	map.put("topN", topN);//top多少
           s = queryEventsManager.getMonitorCustomCount(map);
                    
                    break;
        }
        
        return s;
    }
    
    
    /**
     * <分页查询监控图表>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void queryMonitorAjax()
    {
        HttpServletRequest request = super.getRequest();
        Page page = null;
        SearchResult sr = null;
        String startIndex = request.getParameter("startIndex");
        String keyWord = request.getParameter("keyword");
        
        if (StringUtil.isNotBlank(startIndex)) {
            page = new Page(Page.DEFAULT_PAGE_SIZE,
                    Integer.valueOf(startIndex));
        } else {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }
        
        //page.setPageSize(8);

        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(keyWord)) {
            try {
                keyWord = java.net.URLDecoder.decode(keyWord, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("keyword", keyWord);
        }
        
        sr = monitorManager.queryMonitor(map, page);
        
        List<Monitor> monitorList = sr.getList();
        
       // List resPageList = new ArrayList();
        if (StringUtil.isNotBlank(startIndex) || StringUtil.isNotBlank(keyWord)) {

            try {
                // 转换为JSON数据结构
                JSONArray jsonArray = JSONArray.fromObject(monitorList);
                jsonArray.add(page);
                
                // Ajax返回
                getResponse().getWriter().write(jsonArray.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    /**
     * <增加自定义监控图表>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public String insertChar(){
    	//monitor = new Monitor();
    	monitor.setMonitorDataUrl("&chatX="+chatX+"&topN="+topN+"&eventType=3");//拼接成跟图表中一样的数据
    	//map.put("chatX", "&chatX="+chatX+"&topN="+topN);
    	//monitor.setMonitorName(chartTitle);
    	//map.put("chartTitle", chartTitle);
    	monitor.setRelChartLegendTitle(monitor.getMonitorName());
    	String userName = (String) super.getSession().getAttribute("userinfo");//当前登陆的用户的名字
    	monitor.setMonitorCreateUsername(userName);
    	monitor.setMonitorCreateDatetime(new Date());//创建时间
    	monitor.setMonitorUpdateDatetime(new Date());//更新时间
    	 List<String> fieldList = new ArrayList<String>();
     	  //fieldList.add("monitor.get");
     	 fieldList.add(monitor.getMonitorName() + "(" + monitor.getMonitorName()+ ")");
    	if (monitor.getMonitorId()==0) {//如果不是0说明是更新
    		monitorManager.insertChar(monitor);
    		//内部审计
            auditManager.insertByInsertOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
                "自定义监控图表",
                super.getRequest().getRemoteAddr(),
                fieldList);
            //syslog日志
          /*  String logString = "";
            logString =
                "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                    + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:删除自定义监控图表";
            
            logManager.writeSystemAuditLog(logString);*/
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增自定义监控图表");
      	  
		}else {
			monitorManager.updateChar(monitor);
			//内部审计
	      	 
            auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
                "自定义监控图表",
                super.getRequest().getRemoteAddr(),
                fieldList);
            //syslog日志
            /*String logString = "";
            logString =
                "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                    + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:删除自定义监控图表";
            
            logManager.writeSystemAuditLog(logString);*/
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改自定义监控图表");
		}
    	
    	
    	return SUCCESS;
    }
    /**
     * 删除自定义监控图表的方法
     * @return
     */
    public String deleteCustom(){
    	  log.info("MonitorAction Enter method deleteCustom.....");
    	  monitorManager.deleteCustom(ids);
    	  
          //内部审计
    	  List<String> fieldList = new ArrayList<String>();
    	  fieldList.add("删除自定义监控图表");
          auditManager.insertByDeleteOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
              "自定义监控图表",
              super.getRequest().getRemoteAddr(),
              fieldList);
          //syslog日志
         /* String logString = "";
          logString =
              "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                  + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:删除自定义监控图表";
          
          logManager.writeSystemAuditLog(logString);*/
          logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除自定义监控图表");
    	  
    	  
    	return SUCCESS;
    }
    /**
     * 根据id查询出自定义监控的图表
     * @return
     */
    public String selectMonitorById(){
  	  log.info("MonitorAction Enter method selectMonitorById.....");

    	Map map = new HashMap();
    	
    	map.put("monitorId", (long)monitorId);//这个监控id是int 数据库存的是long 坑啊
    	monitor = monitorManager.selectMonitorById(map);
    	String url = monitor.getMonitorDataUrl();//获得条件的字符串 拆分一下
    	String[] urlArr = url.split("&");
    	//this.chartTitle = monitor.getMonitorName();
		chatX = urlArr[1].substring(6);
		topN= Integer.parseInt(urlArr[2].substring(5));
    	return SUCCESS;
    }
    public MonitorService getMonitorManager()
    {
        return monitorManager;
    }
    
    public void setMonitorManager(MonitorService monitorManager)
    {
        this.monitorManager = monitorManager;
    }
    
    public List<Monitor> getMonitorList()
    {
        return monitorList;
    }
    
    public void setMonitorList(List<Monitor> monitorList)
    {
        this.monitorList = monitorList;
    }
    
    public JSONArray getJsonArray()
    {
        return jsonArray;
    }
    
    public void setJsonArray(JSONArray jsonArray)
    {
        this.jsonArray = jsonArray;
    }
    
    public long getMonitorGroupId()
    {
        return monitorGroupId;
    }
    
    public void setMonitorGroupId(long monitorGroupId)
    {
        this.monitorGroupId = monitorGroupId;
    }
    
    public List<Monitor> getAllMonitorList()
    {
        return allMonitorList;
    }
    
    public void setAllMonitorList(List<Monitor> allMonitorList)
    {
        this.allMonitorList = allMonitorList;
    }
    
    public int getMonitorId()
    {
        return monitorId;
    }
    
    public void setMonitorId(int monitorId)
    {
        this.monitorId = monitorId;
    }
    
    public Monitor getMonitor()
    {
        return monitor;
    }
    
    public void setMonitor(Monitor monitor)
    {
        this.monitor = monitor;
    }
    
    public MonitorGroupService getMonitorGroupManager()
    {
        return monitorGroupManager;
    }
    
    public void setMonitorGroupManager(MonitorGroupService monitorGroupManager)
    {
        this.monitorGroupManager = monitorGroupManager;
    }
    
    public MonitorGroup getMonitorGroup()
    {
        return monitorGroup;
    }
    
    public void setMonitorGroup(MonitorGroup monitorGroup)
    {
        this.monitorGroup = monitorGroup;
    }
    
    public String getMonitorIds()
    {
        return monitorIds;
    }
    
    public void setMonitorIds(String monitorIds)
    {
        this.monitorIds = monitorIds;
    }
    
    public int getEventType()
    {
        return eventType;
    }
    
    public void setEventType(int eventType)
    {
        this.eventType = eventType;
    }
    
    public QueryEventsService getQueryEventsManager()
    {
        return queryEventsManager;
    }
    
    public void setQueryEventsManager(QueryEventsService queryEventsManager)
    {
        this.queryEventsManager = queryEventsManager;
    }
    
    public String getSqlkey()
    {
        return sqlkey;
    }
    
    public void setSqlkey(String sqlkey)
    {
        this.sqlkey = sqlkey;
    }
    

    public String getDevType()
    {
        return devType;
    }

    public void setDevType(String devType)
    {
        this.devType = devType;
    }

    public String getPolicy()
    {
        return policy;
    }

    public void setPolicy(String policy)
    {
        this.policy = policy;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	  public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getTopN() {
			return topN;
		}

		public void setTopN(int topN) {
			this.topN = topN;
		}

		public String getChatX() {
			return chatX;
		}

		public void setChatX(String chatX) {
			this.chatX = chatX;
		}

		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		public String getRelMonitorId() {
			return relMonitorId;
		}
		public void setRelMonitorId(String relMonitorId) {
			this.relMonitorId = relMonitorId;
		}
		public int getSaveSortFlag() {
			return saveSortFlag;
		}
		public void setSaveSortFlag(int saveSortFlag) {
			this.saveSortFlag = saveSortFlag;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		

}
