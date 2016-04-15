package com.soc.webapp.action.monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.http.HttpServletRequest;

import com.soc.model.monitor.MonitorTomcatTask;
import com.soc.model.monitor.servers.tomcat.TomcatDetail;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.MonitorTomcatTaskService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class MonitorTomcatTaskAction extends BaseAction {
	private MonitorTomcatTaskService mttManager;
	private AuditService auditManager;//内部审计
	private TomcatDetail tomDetail;
	
	private static String tomUrl="service:jmx:rmi:///jndi/rmi://$ip$:$port$/jmxrmi";//tomcat jmx url
	private List<MonitorTomcatTask> mttList;//监控对象合集
	private String keyword;//
	private String tomIp;//ip地址
	private String tomName;//任务名称
	private long tomId;
	private MonitorTomcatTask montt;
	private String username;//用户名
	private String pwd;//用户密码
	private int port;//端口
	private int stopORstart;//是否开启监控：0代表停止，1代表开始
	//private static Map<Long,MonitorTomcatTask> tomMap=new HashMap<Long, MonitorTomcatTask>();
	private String tomIdStr;//监控对象ID拼接字符串
	
	
	public String queryAll() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult<MonitorTomcatTask> sr = null;
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
				if (request.getParameter("keyword") != null) {
					try {
						keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					map.put("keyword", keyword);
				}
				if(StringUtil.isNotEmpty(tomIp)){
					map.put("tomIp", tomIp);
				}
				if(StringUtil.isNotEmpty(tomName)){
					map.put("tomName", tomName);
				}
				
				sr=mttManager.queryMonitorTomcatTaskS(map, page);
				if (sr != null) {
					mttList = sr.getList();
					StringBuffer sbf=new StringBuffer();
					//看服务器是否可用
					for(MonitorTomcatTask mtt:mttList){
						//tomMap.put(mtt.getTomId(), mtt);
						if(mtt.getTomTaskStatus()==1){
							mtt.setTomIsOnline(1);
						}
						else{
							mtt.setTomIsOnline(2);//未知状态，不显示任何信息
						}
						sbf.append(mtt.getTomId()+",");
					}
					tomIdStr=sbf.toString();
					request.setAttribute("Page", sr.getPage());
				} else {
					request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
				}
		return SUCCESS;
	}
	
	public String deleteTomTask(){
		if(tomId>0){
			mttManager.deleteMonitorTomcatTask(tomId);
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName()
					+ "("
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + ")");
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "中间件监控", super
					.getRequest().getRemoteAddr(), fieldList);
		
		}
		return SUCCESS;
	}
	public String toAddTomTask(){
		String result="";
		
			if(tomId>0){
				montt = mttManager.detailMonitorTomcatTask(tomId);
				result="success";
			}
		return result;
	}
	
	public String addOrUpdate(){
		List<String> fieldList = new ArrayList<String>();
		montt.setRegTime(new Date());
		montt.setTomUrl((tomUrl.replace("$ip$", montt.getTomIp())).replace("$port$", montt.getTomPort()+""));
		if(montt.getTomId()>0){
			mttManager.updateMonitorTomcatTask(montt);
			fieldList.add(montt.getTomName() + "("
					+ montt.getTomName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "中间件监控", super
					.getRequest().getRemoteAddr(), fieldList);

		}else{
			mttManager.insertMonitorTomcatTask(montt);
			fieldList.add(montt.getTomName() + "("
					+ montt.getTomName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "中间件监控", super
					.getRequest().getRemoteAddr(), fieldList);
	}	
		return SUCCESS;
	}
	public void testLink(){
		montt = new MonitorTomcatTask();
		montt.setTomIp(tomIp);
		montt.setTomPort(port);
		montt.setTomUserPass(pwd);
		montt.setTomUserName(username);
		montt.setTomUrl((tomUrl.replace("$ip$", tomIp)).replace("$port$", port+""));
		try {
			boolean falg=checkOnline(montt);
			getResponse().getWriter().write(falg+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//检查是否重名
	public void checkName(){
		String tmp ="";
		if(StringUtil.isNotEmpty(tomName)){
			tmp = mttManager.checkTomName(tomName);
		}
		if(tmp==null){
			tmp="";
		}
		try {
			getResponse().getWriter().write(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//启动/停止
	@SuppressWarnings({ })
	public void stopORstart(){
		Map<String, Number> map = new HashMap<String, Number>();
		map.put("tomTaskStatus", stopORstart);
		map.put("tomId", tomId);
		mttManager.stopMonitorTomcatTask(map);
	}
	
	public  boolean checkOnline (MonitorTomcatTask mtt) {
		boolean flag =false;
		try { 
			JMXConnector connector=connect(mtt);
        	flag=(connector.getConnectionId()!=""&&connector.getConnectionId()!=null);
		} catch (Exception e) {
		//	e.printStackTrace();
			System.out.println("无法连接");
		}
        return flag;
	}
	
	public void online(){
		montt=mttManager.detailMonitorTomcatTask(tomId);
		try{
		if(montt.getTomTaskStatus()==1){
		if(checkOnline(montt)){
		getResponse().getWriter().write("1");
		}
		else{
			getResponse().getWriter().write("0");
			}
		}else{
		getResponse().getWriter().write("2");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String detailTOM(){
		String result="";
		if(tomId>0){
				montt = mttManager.detailMonitorTomcatTask(tomId);
				if(montt.getTomTaskStatus()==1){				
						//if(checkOnline(montt))
							getMBean(montt);	
				}else{
				}
				result="success";
				return result;
		}
		return result;
	}
	public JMXConnector connect(MonitorTomcatTask mtt){
		JMXConnector connector = null;
		try {
			String jmxURL = mtt.getTomUrl();
			JMXServiceURL serviceURL;
			serviceURL = new JMXServiceURL(jmxURL);
			Map<String,String[]>map = new HashMap<String,String[]>();   
	        String[] credentials = new String[] { mtt.getTomUserName() , mtt.getTomUserPass() };   
	        map.put("jmx.remote.credentials", credentials);   
	         connector = JMXConnectorFactory.connect(serviceURL, map); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
        	return connector;
	}
	public void getMBean(MonitorTomcatTask mtt){
		MBeanServerConnection mbsc = null;
		
		try {
			mbsc=connect(mtt).getMBeanServerConnection();
			
			//时间相关
			ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");//服务器启动时间
			Date start = new Date((Long) mbsc.getAttribute(runtimeObjName, "StartTime")); 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            tomDetail=new TomcatDetail();
            tomDetail.setStartTime(df.format(start));//运行时间 
            Long timespan = (Long) mbsc.getAttribute(runtimeObjName, "Uptime"); 
            tomDetail.setTimeSpan(MonitorTomcatTaskAction.formatTimeSpan(timespan)); 
           
            //堆内存相关
            ObjectName heapObjName = new ObjectName("java.lang:type=Memory"); 
            MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName,"HeapMemoryUsage"));
            tomDetail.setMaxMemory(Math.ceil(heapMemoryUsage.getMax()/1000000));//堆内存最大   
            long commitMemory = heapMemoryUsage.getCommitted();//堆内存当前分配   
            long useMemory = heapMemoryUsage.getUsed(); 
            tomDetail.setUsedMemory("{name:'已使用',y:"+Math.ceil((double) useMemory*100/ commitMemory)+"}");//堆使用率
            tomDetail.setUnUsedMemory("{name:'未使用',y:"+Math.ceil((double)(commitMemory- useMemory)*100/ commitMemory)+"}");
            //线程相关
            ObjectName threadpoolObjName = new ObjectName("Catalina:type=ThreadPool,*"); 
            Set<ObjectName> portSet = mbsc.queryNames(threadpoolObjName, null); 
            String listport = "";
            String listCurrenThread="";
            String listBusyThread="";
            int i=0;
            for (ObjectName obj : portSet) {  
            	
                ObjectName objname = new ObjectName(obj.getCanonicalName());
               
            	if(i==0){
                listport+="'"+obj.getKeyProperty("name")+"'";
                listCurrenThread+=mbsc.getAttribute(objname, "currentThreadCount");
                listBusyThread+=mbsc.getAttribute(objname, "currentThreadsBusy");
               // System.out.println("会话数:" + mbsc.getAttribute(objname, "sessionCounter"));
                i+=1;
                }else {
                	listport+=",'"+obj.getKeyProperty("name")+"'";
                	listCurrenThread+=","+mbsc.getAttribute(objname, "currentThreadCount");
                	listBusyThread+=","+mbsc.getAttribute(objname, "currentThreadsBusy");
                	i+=1;
                }
               
               }
            tomDetail.setPortName(listport);
            tomDetail.setBusyThread(listBusyThread);
            tomDetail.setCurrentThread(listCurrenThread);
            
            //应用回话相关
            ObjectName managerObjName = new ObjectName("Catalina:type=Manager,*"); 
            Set<ObjectName> session = mbsc.queryNames(managerObjName, null); 
            String listProjectName = ""; 
            String listUseAble=""; 
            String listSessionUse=""; 
            String path="";
            String context="";
            int m=0;
            for (ObjectName obj : session) { 
            	ObjectName objname = new ObjectName(obj.getCanonicalName());
            	path=obj.getKeyProperty("path");
            	context=obj.getKeyProperty("context");
            	if(path==null&&context!=null){
            		path=context;	
            	}else{
            		path=path;
            	}
            	//System.out.println("************************"+obj.getKeyProperty("path"));
            	//System.out.println("************************"+obj.getKeyProperty("context"));
            	if(m==0){
            		listProjectName+="'/"+obj.getKeyProperty("host")+path+"'";
            		listUseAble+="1";
            		listSessionUse+=mbsc.getAttribute(objname, "activeSessions");
            		m+=1;
                }else {
                	listProjectName+=",'/"+obj.getKeyProperty("host")+path+"'";
                	listUseAble+=","+"1";
                	listSessionUse+=","+mbsc.getAttribute(objname, "activeSessions");
                	m+=1;
                }
            	
            	
            }
            tomDetail.setUseAble(listUseAble);
            tomDetail.setProjectName(listProjectName);
            tomDetail.setSessionUse(listSessionUse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//tomDetail.setUsedMemory(0);
			//e.printStackTrace();
			
		}
		
	}
	@SuppressWarnings("resource")
	public static String formatTimeSpan(long span) { 
        long minseconds = span % 1000; 

        span = span / 1000; 
        long seconds = span % 60; 

        span = span / 60; 
        long mins = span % 60; 

        span = span / 60; 
        long hours = span % 24; 

        span = span / 24; 
        long days = span; 
        return (new Formatter()).format("%1$d天 %2$02d:%3$02d:%4$02d.%5$03d", days, hours, mins, seconds, minseconds) 
                .toString(); 
    } 

	public MonitorTomcatTaskService getMttManager() {
		return mttManager;
	}
	public void setMttManager(MonitorTomcatTaskService mttManager) {
		this.mttManager = mttManager;
	}
	public List<MonitorTomcatTask> getMttList() {
		return mttList;
	}
	public void setMttList(List<MonitorTomcatTask> mttList) {
		this.mttList = mttList;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTomIp() {
		return tomIp;
	}
	public void setTomIp(String tomIp) {
		this.tomIp = tomIp;
	}
	public String getTomName() {
		return tomName;
	}
	public void setTomName(String tomName) {
		this.tomName = tomName;
	}
	public long getTomId() {
		return tomId;
	}
	public void setTomId(long tomId) {
		this.tomId = tomId;
	}
	public MonitorTomcatTask getMontt() {
		return montt;
	}
	public void setMontt(MonitorTomcatTask montt) {
		this.montt = montt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getStopORstart() {
		return stopORstart;
	}
	public void setStopORstart(int stopORstart) {
		this.stopORstart = stopORstart;
	}
	
	public TomcatDetail getTomDetail() {
		return tomDetail;
	}

	public void setTomDetail(TomcatDetail tomDetail) {
		this.tomDetail = tomDetail;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public String getTomIdStr() {
		return tomIdStr;
	}

	public void setTomIdStr(String tomIdStr) {
		this.tomIdStr = tomIdStr;
	}

	
}
