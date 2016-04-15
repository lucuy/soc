package com.soc.webapp.action.monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.model.monitor.database.mysql.MysqlDetail;
import com.soc.model.monitor.database.orcale.OracleDetail;
import com.soc.model.monitor.database.sqlserver.SqlServerDetail;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.MonitorDatabaseTaskService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.monitorDatabase.JdbcConnectionWrapper;
import com.util.monitorDatabase.MonitorDatabaseUtils;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class MonitorDatabaseTaskAction extends BaseAction {
	private AuditService auditManager;//内部审计
	private MonitorDatabaseTaskService mdtManager;
	
	private List<MonitorDatabaseTask> mdtList;//数据库集合
	private String keyword;//快速搜索关键字
	private int dbType;//数据库类型 1：mysql，2：oracle，3，sql server
	private String dbIp;//数据库ip地址
	private String taskName;//任务名称
	private long taskId;
	private MonitorDatabaseTask mondbt;
	// 用户服务类
	private UserService userManager;
	private List<User> userList;
	private String username;
	private String pwd;
	private int port;
	private String db2iupdt;
	private int stopORstart;//0代表停止，1代表开始
	private OracleDetail oracleDetail;
	private MysqlDetail mysqlDetail;
	private String connTimes;
	private SqlServerDetail sqlServerDetail;
	private static Map<Long,MonitorDatabaseTask> tmpMap = new HashMap<Long,MonitorDatabaseTask>();//临时存放监控对象
	private String tmpID;//用来存放查询的监控对象Id
	public String queryAll(){
		LOG.info("[MonitorDatabaseTaskAction] enter method queryAll() ...");
		HttpServletRequest request = super.getRequest();
		Page page = null;
		tmpMap.clear();
		SearchResult<MonitorDatabaseTask> sr = null;
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
				if(StringUtil.isNotEmpty(dbIp)){
					map.put("dbIp", dbIp);
				}
				if(StringUtil.isNotEmpty(taskName)){
					map.put("taskName", taskName);
				}
				map.put("dbType", dbType);
				sr=mdtManager.queryMonitorDatabaseTaskS(map, page);
				if (sr != null) {
					mdtList = sr.getList();
					StringBuffer sb = new StringBuffer();
					//看数据库是否可用
					for(MonitorDatabaseTask mdt:mdtList){
						if(mdt.getTaskStatus()==1){
							mdt.setDbOnline(1);
						}else{
							mdt.setDbOnline(2);//未知状态，不显示任何信息
						}
						tmpMap.put(mdt.getTaskId(), mdt);
						sb.append(mdt.getTaskId()+",");
					}
					tmpID = sb.toString();
					tmpID.split(",");
					request.setAttribute("Page", sr.getPage());
				} else {
					request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
				}
		return SUCCESS;
	}
	public String deleteDBTask(){
		if(taskId>0){
			mdtManager.deleteMonitorDatabaseTask(taskId);
			tmpMap.remove(taskId);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName()
				+ "("
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + ")");
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "数据库监控", super
				.getRequest().getRemoteAddr(), fieldList);
//		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
//				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除数据库监控");
		}
		super.getRequest().setAttribute("dbType", dbType);
		return SUCCESS;
	}
	public String toAddDBTask(){
		String result="";
		if(dbType>0){
			if(taskId>0){
				mondbt = mdtManager.detailMonitorDatabaseTask(taskId);
			}
			userList = userManager.queryAll();
			switch (dbType) {
			case 1:
				result="MySql";
				break;
			case 2:
				result="SQLServer";
				break;
			case 3:
				result="Oracle";
				break;
			}
		}
		return result;
	}
	
	public String addOrUpdate(){
		List<String> fieldList = new ArrayList<String>();
		/*		switch (dbType) {
		case 1:
			mondbt.setDbUrl((mySqlUrl.replace("$ip$", dbIp)).replace("$port$", port+""));
			break;
		case 2:
			mondbt.setDbUrl((sqlServerUrl.replace("$ip$", dbIp)).replace("$port$", port+""));
			break;
		case 3:
			mondbt.setDbUrl(((oracleUrl.replace("$ip$", dbIp)).replace("$port$", port+"")).replace("$service$", db2iupdt));
			break;
		}*/
		mondbt.setDbUrl(MonitorDatabaseUtils.changURL(dbType, mondbt));
		mondbt.setUpdateTime(new Date());
		mondbt.setDbType(dbType);
		if(mondbt.getTaskId()>0){
			mdtManager.updateMonitorDatabaseTask(mondbt);
			fieldList.add(mondbt.getTaskName() + "("
					+ mondbt.getTaskName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "数据库监控", super
					.getRequest().getRemoteAddr(), fieldList);
			
//			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
//					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改工单");
		}else{
			mdtManager.insertMonitorDatabaseTask(mondbt);
			fieldList.add(mondbt.getTaskName() + "("
					+ mondbt.getTaskName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "数据库监控", super
					.getRequest().getRemoteAddr(), fieldList);
			
//			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
//					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增数据库监控");
		}
		super.getRequest().setAttribute("dbType", dbType);
		
		
		
		
		return SUCCESS;
	}
	public void testLink(){
		mondbt = new MonitorDatabaseTask();
		if(StringUtil.isNotEmpty(db2iupdt)){
			mondbt.setDb2iupdt(db2iupdt);
		}
		mondbt.setDbIp(dbIp);
		mondbt.setDbPort(port);
		mondbt.setDbPWD(pwd);
		mondbt.setDbUserName(username);
		mondbt.setDbType(dbType);
/*		switch (dbType) {
		case 1:
			mondbt.setDbUrl((mySqlUrl.replace("$ip$", dbIp)).replace("$port$", port+""));
			break;
		case 2:
			mondbt.setDbUrl((sqlServerUrl.replace("$ip$", dbIp)).replace("$port$", port+""));
			break;
		case 3:
			mondbt.setDbUrl(((oracleUrl.replace("$ip$", dbIp)).replace("$port$", port+"")).replace("$service$", db2iupdt));
			break;
		}*/
		mondbt.setDbUrl(MonitorDatabaseUtils.changURL(dbType, mondbt));
		boolean falg = MonitorDatabaseUtils.checkOnline(mondbt);
		try {
			getResponse().getWriter().write(falg+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void checkOnline(){
		if(taskId>0){
			mondbt = (MonitorDatabaseTask) tmpMap.get(taskId);
			//tmpMap.remove(taskId);
		if(mondbt.getTaskStatus()==1){
			boolean falg=MonitorDatabaseUtils.checkOnline(mondbt);
			try {
				getResponse().getWriter().write(falg+"");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				getResponse().getWriter().write("2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}
	//检查是否重名
	public void checkName(){
		String tmp ="";
		if(StringUtil.isNotEmpty(taskName)){
			tmp = mdtManager.checkTaskName(taskName);
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void stopORstart(){
		Map map = new HashMap();
		
			map.put("taskStatus", stopORstart);
		map.put("taskId", taskId);
		mondbt = tmpMap.get(taskId);
		mondbt.setTaskStatus(stopORstart);
		mdtManager.stopMonitorDatabaseTask(map);
		tmpMap.put(taskId, mondbt);
		
	}
	public String detailDB(){
		String result="";
		JdbcConnectionWrapper wrapper = null;
		if(dbType>0){
			if(taskId>0){
				mondbt = mdtManager.detailMonitorDatabaseTask(taskId);
			}
			switch (dbType) {
			case 1:
				if(mondbt.getTaskStatus()==1){
				wrapper=MonitorDatabaseUtils.creatConnection(mondbt);
				long start = System.currentTimeMillis();
				mysqlDetail = new MysqlDetail(wrapper.getConn());
				long end = System.currentTimeMillis();
				if(wrapper.getConn()!=null){
					connTimes = (end -start)+"ms";
				}
				wrapper.closeConnection();
				}
				result="MySql";
				break;
			case 2:
				if(mondbt.getTaskStatus()==1){
				wrapper=MonitorDatabaseUtils.creatConnection(mondbt);
				long start1 = System.currentTimeMillis();
				sqlServerDetail = new SqlServerDetail(wrapper.getConn());
				long end1 = System.currentTimeMillis();
				if(wrapper.getConn()!=null){
					connTimes = (end1 -start1)+"ms";
				}
				wrapper.closeConnection();
				}
				result="SQLServer";
				break;
			case 3:
				if(mondbt.getTaskStatus()==1){
				wrapper=MonitorDatabaseUtils.creatConnection(mondbt);
				oracleDetail = new OracleDetail(wrapper.getConn());
				wrapper.closeConnection();
				}
				result="Oracle";
				break;
			}
		}
		return result;
	}
	
	
	
	
	
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public MonitorDatabaseTask getMondbt() {
		return mondbt;
	}
	public void setMondbt(MonitorDatabaseTask mondbt) {
		this.mondbt = mondbt;
	}
	public List<MonitorDatabaseTask> getMdtList() {
		return mdtList;
	}
	public void setMdtList(List<MonitorDatabaseTask> mdtList) {
		this.mdtList = mdtList;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getDbType() {
		return dbType;
	}
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	public String getDbIp() {
		return dbIp;
	}
	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public MonitorDatabaseTaskService getMdtManager() {
		return mdtManager;
	}
	public void setMdtManager(MonitorDatabaseTaskService mdtManager) {
		this.mdtManager = mdtManager;
	}
	public UserService getUserManager() {
		return userManager;
	}
	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
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
	public String getDb2iupdt() {
		return db2iupdt;
	}
	public void setDb2iupdt(String db2iupdt) {
		this.db2iupdt = db2iupdt;
	}
	public int getStopORstart() {
		return stopORstart;
	}
	public void setStopORstart(int stopORstart) {
		this.stopORstart = stopORstart;
	}
	public OracleDetail getOracleDetail() {
		return oracleDetail;
	}
	public void setOracleDetail(OracleDetail oracleDetail) {
		this.oracleDetail = oracleDetail;
	}
	public MysqlDetail getMysqlDetail() {
		return mysqlDetail;
	}
	public void setMysqlDetail(MysqlDetail mysqlDetail) {
		this.mysqlDetail = mysqlDetail;
	}
	public String getConnTimes() {
		return connTimes;
	}
	public void setConnTimes(String connTimes) {
		this.connTimes = connTimes;
	}
	public SqlServerDetail getSqlServerDetail() {
		return sqlServerDetail;
	}
	public void setSqlServerDetail(SqlServerDetail sqlServerDetail) {
		this.sqlServerDetail = sqlServerDetail;
	}
	public String getTmpID() {
		return tmpID;
	}
	public void setTmpID(String tmpID) {
		this.tmpID = tmpID;
	}
	
	
}
