package com.soc.webapp.action.workorder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soc.model.role.Role;
import com.soc.model.user.User;
import com.soc.model.workorder.WorkOrder;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.user.UserService;
import com.soc.service.workorder.WorkOrderService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.email.WorkOrderSendMail;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <工单管理action> <功能详细描述>
 * 
 * @author liruifeng
 * @version [版本号, 2013-1-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WorkOrderAction extends BaseAction {
	private WorkOrderService workOrderManager;

	// 资产的业务处理类
	private AssetService assetManager;

	// 用户服务类
	private UserService userManager;

	// 告警信息管理类
	private AlertMessageSerive alertMessageManager;

	// 关键字
	private String keyword;

	// 查询出来的list
	private List listWork;

	// 批量删除的id
	private String result;

	// 工单实体类实例
	private WorkOrder workOrder;

	// 通过workOrderId查询
	private int workOrderId;

	// 通过派操作者查询
	private String selworkOrderHandle;

	// 通过派发者查询
	private String selworkOrderStart;

	// 通过名字查询
	private String selWorkOrderName;

	// 通过紧急程度查询
	private String selExigencyLevel;

	// 通过状态查询
	private String selStatus;

	// 查询不是没完成的工单
	private int manyStatus;

	// 排序Type
	private String sortType;

	// 要查询的字段
	private String field;
	private List<User> userList;
	// 审计业务
	private AuditService auditManager;
	private String falg;//标识是1、代表查询的是未处理工单。2、代表的是以处理工单。3、代表的是自己派发的工单。
	private long userId;//用来判断是不是管理员
	// 设置的管理类
		private SettingService settingManager;
		private String closeReason;
	/**
	 * <高级查询工单> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String query() {
		log.info("[WorkOrderAction] enter query()");
		HttpServletRequest request = super.getRequest();
		HttpSession session = this.getSession();

		Page page = null;
		SearchResult<WorkOrder> sr = null;

		// 处理数据分页的其实条数
		String startIndex = request.getParameter("startIndex");

		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword == null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE,
							Integer.valueOf(startIndex));
				} else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE,
								Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					} else {
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
		if (StringUtil.isNotBlank(selWorkOrderName)) {
			map.put("selWorkOrderName", selWorkOrderName);
		}
		if (StringUtil.isNotBlank(selExigencyLevel)) {
			map.put("selExigencyLevel", Integer.parseInt(selExigencyLevel));
		}
		if (StringUtil.isNotBlank(selStatus)) {
			map.put("selStatus", Integer.parseInt(selStatus));
		}
		if (StringUtil.isNotBlank(selworkOrderHandle)) {
			map.put("selworkOrderHandle", selworkOrderHandle);
		}
		if (StringUtil.isNotBlank(selworkOrderStart)) {
			map.put("selworkOrderStart", selworkOrderStart);
		}
		User user = (User) super.getSession().getAttribute("SOC_LOGON_USER");
		userId = user.getUserId();
		String dispath="";
		if(user.getUserId()==38){
			if("3".equals(falg)){
				dispath="disptch";	
			}
			if("2".equals(falg)){
				map.put("status", "1,2");
				dispath="finished";	
				
			}
			if("1".equals(falg)){
				map.put("status", 0);
				dispath="unfinished";	
			}
		}else {
		if("3".equals(falg)){
			map.put("workOrderStart", user.getUserLoginName());
			dispath="disptch";
		}else{
			map.put("workOrderHandle", user.getUserLoginName());
			if("1".equals(falg)){
				map.put("status", 0);
				dispath="unfinished";
			}else{
				map.put("status", "1,2");
				dispath="finished";	
			}
		}
		}
		//map.put("username", user.getUserLoginName());

		
		sr = workOrderManager.query(map, page);

		listWork = sr.getList();

		page = sr.getPage();

		request.setAttribute("Page", sr.getPage());
		request.setAttribute("listWork", listWork);
		/*
		 * selWorkOrderName=""; selExigencyLevel=""; selStatus="";
		 */
		return dispath;
	}
	/*public String query() {
		log.info("[WorkOrderAction] enter query()");
		HttpServletRequest request = super.getRequest();
		HttpSession session = this.getSession();

		Page page = null;
		SearchResult<WorkOrder> sr = null;

		// 处理数据分页的其实条数
		String startIndex = request.getParameter("startIndex");

		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword == null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE,
							Integer.valueOf(startIndex));
				} else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE,
								Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					} else {
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

		if (StringUtil.isNotBlank(selWorkOrderName)) {
			map.put("selWorkOrderName", selWorkOrderName);
		}
		if (StringUtil.isNotBlank(selExigencyLevel)) {
			map.put("selExigencyLevel", Integer.parseInt(selExigencyLevel));
		}
		if (StringUtil.isNotBlank(selStatus)) {
			map.put("selStatus", Integer.parseInt(selStatus));
		}
		if (StringUtil.isNotBlank(selworkOrderHandle)) {
			map.put("selworkOrderHandle", selworkOrderHandle);
		}
		if (StringUtil.isNotBlank(selworkOrderAudit)) {
			map.put("selworkOrderAudit", selworkOrderAudit);
		}

		// menu判断按照状态查询查询
		if (manyStatus == 3) {
			String can = "(3)";
			map.put("manyStatus", can);
		}
		if (manyStatus == 1) {
			String can = "(0,1,2)";
			map.put("manyStatus", can);
		}
		if (manyStatus == 2) {
			String can = "(0,1,2,3)";
			map.put("manyStatus", can);
		}
		User user = (User) super.getSession().getAttribute("SOC_LOGON_USER");

		map.put("username", user.getUserLoginName());

		// 查询关联角色
		List<Role> roleList = userManager.queryRelRole(user.getUserId());
		// 判断是否是工单管理员，现在没有工单管理员，暂时用ID是32的总管理员
		if (roleList.get(0).getRoleId() == 32) {

			long groupid = user.getAssetGroupid();

			// 根据用户关联的资产组id查询资产的id字符串
			String assetId = assetManager.queryIDSByUser(groupid);

			if ((assetId != null) && (!assetId.equals(""))) {
				map.put("ids", assetId);
			}

			// 根据资产ID查询告警
			
			 * Map mapOfAlert=new HashMap(); mapOfAlert.put("ids", assetId);
			 * List<AlertMessage>
			 * alertList=alertMessageManager.queryByAssetId(mapOfAlert);
			 * StringBuffer alertsb=new StringBuffer(); for (AlertMessage
			 * alertMessage : alertList) { if(!alertsb.toString().equals("")){
			 * alertsb.append(","); } alertsb.append(alertMessage.getAlertId());
			 * } String alertIds=""; if (!alertsb.equals("")) {
			 * alertIds=alertsb.toString(); } //根据告警ID查询工单 map.put("alertIds",
			 * alertIds);
			 
		}

		sr = workOrderManager.query(map, page);

		listWork = sr.getList();

		page = sr.getPage();

		request.setAttribute("Page", sr.getPage());
		request.setAttribute("listWork", listWork);
		
		 * selWorkOrderName=""; selExigencyLevel=""; selStatus="";
		 
		return "success";
	}*/

	/**
	 * <左侧查询方法> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryLeft() {

		log.info("[WorkOrderAction] enter queryLeft()");

		HttpServletRequest request = super.getRequest();
		HttpSession session = this.getSession();

		// int changeNum = 0;

		/*
		 * changeNum = session.getAttribute("CHANGENUM") == null ? 1 : (Integer)
		 * session.getAttribute("CHANGENUM");
		 */
		Page page = null;

		SearchResult<WorkOrder> sr = null;

		// 处理数据分页的其实条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}

		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();

		/*
		 * if (field != "" && field != null) {
		 * 
		 * int num = changeNum % 2;
		 * 
		 * if (num == 0) { map.put("sortType", "DESC"); } else {
		 * map.put("sortType", "ASC"); } if (sortType != null) {
		 * map.put("sortType", sortType); }
		 * 
		 * map.put("field", field);
		 * 
		 * field = (String) map.get("field");
		 * 
		 * sortType = (String) map.get("sortType");
		 * 
		 * String str = "ORDER BY"+" \"" + field + "\"" + " " + sortType;
		 * 
		 * map.put("str", str);
		 * 
		 * }
		 */

		// menu判断按照状态查询查询
		if (manyStatus == 3) {
			String can = "(3)";
			map.put("manyStatus", can);
		}
		if (manyStatus == 1) {
			String can = "(0,1,2)";
			map.put("manyStatus", can);
		}
		if (manyStatus == 2) {
			String can = "(0,1,2,3)";
			map.put("manyStatus", can);
		}

		User user = (User) super.getSession().getAttribute("SOC_LOGON_USER");

		map.put("username", user.getUserLoginName());

		// 查询关联角色
		List<Role> roleList = userManager.queryRelRole(user.getUserId());
		// 判断是否是工单管理员，现在没有工单管理员，暂时用ID是32的总管理员
		if (roleList.get(0).getRoleId() == 32) {

			long groupid = user.getAssetGroupid();

			// 根据用户关联的资产组id查询资产的id字符串
			String assetId = assetManager.queryIDSByUser(groupid);

			/*
			 * //根据资产ID查询告警 Map mapOfAlert=new HashMap(); mapOfAlert.put("ids",
			 * assetId); List<AlertMessage>
			 * alertList=alertMessageManager.queryByAssetId(mapOfAlert);
			 * StringBuffer alertsb=new StringBuffer(); for (AlertMessage
			 * alertMessage : alertList) { if(!alertsb.toString().equals("")){
			 * alertsb.append(","); } alertsb.append(alertMessage.getAlertId());
			 * } String alertIds=""; if (!alertsb.equals("")) {
			 * alertIds=alertsb.toString(); }
			 */

			// 根据告警ID查询工单
			if ((assetId != null) && (!assetId.equals(""))) {
				map.put("ids", assetId);
			}
		}

		sr = workOrderManager.query(map, page);

		listWork = sr.getList();

		page = sr.getPage();

		request.setAttribute("Page", sr.getPage());

		request.setAttribute("listWork", listWork);

		map.clear();
		/*
		 * changeNum++ ; session.setAttribute("CHANGENUM", changeNum);
		 */
		return "success";
	}
	/*
	 * 跳转至工单新增页面
	 */
	public String toAddWorkOrder(){
		 userList = userManager.queryAll();
		 /**
		  * 删除当前登录用户
		  */
		 String onLine=((User) this.getSession().getAttribute(
					"SOC_LOGON_USER")).getUserLoginName();
			for (User user : userList) {
				if(user.getUserLoginName().equals(onLine)){
					userList.remove(user);
					break;
				}
			}
		return SUCCESS;
	}

	/**
	 * <快速查询> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String quckQuery() {
		log.info("[WorkOrderAction] enter quckQuery()");

		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult<WorkOrder> sr = null;

		// 处理数据分页的其实条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
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
		// menu判断按照状态查询查询
					if (manyStatus == 3) {
						String can = "(3)";
						map.put("manyStatus", can);
					}
					if (manyStatus == 1) {
						String can = "(0,1,2)";
						map.put("manyStatus", can);
					}
					if (manyStatus == 2) {
						String can = "(0,1,2,3)";
						map.put("manyStatus", can);
					}
		User user = (User) super.getSession().getAttribute("SOC_LOGON_USER");
		map.put("username", user.getUserLoginName());

		// 查询关联角色
		List<Role> roleList = userManager.queryRelRole(user.getUserId());
		// 判断是否是工单管理员，现在没有工单管理员，暂时用ID是32的总管理员
		if (roleList.get(0).getRoleId() == 32) {
			long groupid = user.getAssetGroupid();

			// 根据用户关联的资产组id查询资产的id字符串
			String assetId = assetManager.queryIDSByUser(groupid);
			if ((assetId != null) && (!assetId.equals(""))) {
				map.put("ids", assetId);
			}

			// 根据资产ID查询告警
			/*
			 * Map mapOfAlert=new HashMap(); mapOfAlert.put("ids", assetId);
			 * List<AlertMessage>
			 * alertList=alertMessageManager.queryByAssetId(mapOfAlert);
			 * StringBuffer alertsb=new StringBuffer(); for (AlertMessage
			 * alertMessage : alertList) { if(!alertsb.toString().equals("")){
			 * alertsb.append(","); } alertsb.append(alertMessage.getAlertId());
			 * } String alertIds=""; if (!alertsb.equals("")) {
			 * alertIds=alertsb.toString(); }
			 * 
			 * //根据告警ID查询工单 map.put("alertIds", alertIds);
			 */
		}
		sr = workOrderManager.query(map, page);
		listWork = sr.getList();
		page = sr.getPage();

		request.setAttribute("Page", sr.getPage());
		request.setAttribute("listWork", listWork);
		return "success";
	}

	/**
	 * <删除工单> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String deleteById() {
		log.info("[WorkOrderAction] enter deleteById()");

		workOrderManager.deleteWorkOrder(result);
		//System.out.println(manyStatus);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName()
				+ "("
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + ")");
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
				.getRequest().getRemoteAddr(), fieldList);

		/*String logString = "";

		logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型：删除工单";*/

		/*
		 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) { for
		 * (String key : map.keySet()) { SyslogIF syslogIF = map.get(key);
		 * SysLogSender.sender(syslogIF, logString); } }
		 */
		//logManager.writeSystemAuditLog(logString);
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除工单");

		return "success";
	}
	/**
	 * 修改工单
	 */
	public String updateWordOrder(){
		log.info("[WorkOrderAction] enter updateWordOrder()");
		workOrderManager.updateWorkOrder(workOrder);
		falg="1";
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(workOrder.getWorkOrderName() + "("
				+ workOrder.getWorkOrderName() + ")");
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
				.getRequest().getRemoteAddr(), fieldList);
		
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改工单");
		return SUCCESS;
	}
	/**
	 * <新增工单> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String addWorkOrder() {
		
		log.info("[WorkOrderAction] enter addWorkOrder()");
			
			User user=userManager.queryByUserLoginName(workOrder.getWorkOrderHandle()).get(0);
			workOrder.setWorkOrderStart(((User) super.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName());
			workOrder.setProduceDate(new Date());
			int id=workOrderManager.insertWorkOrder(workOrder);
			falg="3";
			if(user!=null&&workOrder.getSendMail()==1){
				workOrder.setWorkOrderId(id);
				WorkOrderSendMail sendMail = WorkOrderSendMail.initialization(user, workOrder, settingManager);
				sendMail.emailHandling();
			}
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(workOrder.getWorkOrderName() + "("
					+ workOrder.getWorkOrderName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
					.getRequest().getRemoteAddr(), fieldList);
			
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增工单");
		
		return SUCCESS;
	}
	/*public String addWorkOrder() {

		log.info("[WorkOrderAction] enter addWorkOrder()");

		if (workOrder.getWorkOrderId() != 0) {
			workOrderManager.updateWorkOrder(workOrder);

			List<String> fieldList = new ArrayList<String>();
			fieldList.add(workOrder.getWorkOrderName() + "("
					+ workOrder.getWorkOrderName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
					.getRequest().getRemoteAddr(), fieldList);

			String logString = "";

			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型：更新工单";

			
			 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			 * for (String key : map.keySet()) { SyslogIF syslogIF =
			 * map.get(key); SysLogSender.sender(syslogIF, logString); } }
			 
			//logManager.writeSystemAuditLog(logString);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改工单");

			workOrder = new WorkOrder();
		} else {

			workOrderManager.insertWorkOrder(workOrder);

			List<String> fieldList = new ArrayList<String>();
			fieldList.add(workOrder.getWorkOrderName() + "("
					+ workOrder.getWorkOrderName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
					.getRequest().getRemoteAddr(), fieldList);

			String logString = "";

			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型：新增工单";

			
			 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			 * for (String key : map.keySet()) { SyslogIF syslogIF =
			 * map.get(key); SysLogSender.sender(syslogIF, logString); } }
			 
			logManager.writeSystemAuditLog(logString);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增工单");
		}
		return "success";
	}*/

	/**
	 * <通过id详细查看工单> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryById() {

		log.info("[WorkOrderAction] enter queryById()");

		workOrder = workOrderManager.queryById(workOrderId);

		// ServletActionContext.getRequest().setAttribute("queryId", wod);

		return "success";
	}
	//关闭工单
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void closeWorkOrder(){
		Map map = new HashMap();
		int closeStatus=-1;
		if(StringUtil.isNotEmpty(closeReason)&&workOrderId>0){
			try {
				closeReason = java.net.URLDecoder.decode(closeReason, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("closeReason", closeReason);
			map.put("workOrderId", workOrderId);
			closeStatus = workOrderManager.closeWorkOrder(map);
		}
		try {
			// Ajax返回
			getResponse().getWriter().write(closeStatus+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//查询关闭工单原因
	public void queryCloseReason(){
		try {
			// Ajax返回
			getResponse().getWriter().write(workOrderManager.queryWorkOrderCloseReason(workOrderId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//查询未处理工单总数
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryUnfinshWorkOrderCount(){
		Map map = new HashMap();
		map.put("workOrderHandle", ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName());
			map.put("selStatus", 0);
			int unfishedCount=workOrderManager.count(map);
			map.remove("workOrderHandle");
			map.put("workOrderStart", ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName());
			int dispathCount=workOrderManager.count(map);
			try {
				// Ajax返回
				getResponse().getWriter().write(unfishedCount+"$$"+dispathCount);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public WorkOrderService getWorkOrderManager() {
		return workOrderManager;
	}

	public void setWorkOrderManager(WorkOrderService workOrderManager) {
		this.workOrderManager = workOrderManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public int getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(int workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getSelWorkOrderName() {
		return selWorkOrderName;
	}

	public void setSelWorkOrderName(String selWorkOrderName) {
		this.selWorkOrderName = selWorkOrderName;
	}

	public String getSelExigencyLevel() {
		return selExigencyLevel;
	}

	public void setSelExigencyLevel(String selExigencyLevel) {
		this.selExigencyLevel = selExigencyLevel;
	}

	public String getSelStatus() {
		return selStatus;
	}

	public void setSelStatus(String selStatus) {
		this.selStatus = selStatus;
	}

	public int getManyStatus() {
		return manyStatus;
	}

	public void setManyStatus(int manyStatus) {
		this.manyStatus = manyStatus;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSelworkOrderHandle() {
		return selworkOrderHandle;
	}

	public void setSelworkOrderHandle(String selworkOrderHandle) {
		this.selworkOrderHandle = selworkOrderHandle;
	}

	

	public String getSelworkOrderStart() {
		return selworkOrderStart;
	}

	public void setSelworkOrderStart(String selworkOrderStart) {
		this.selworkOrderStart = selworkOrderStart;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}

	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
	

}
