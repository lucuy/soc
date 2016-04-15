package com.soc.webapp.action.alert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.Node;
import com.soc.model.user.User;
import com.soc.model.workorder.WorkOrder;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.alert.exportalertmasage.ExcelAlertMasage;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.MonitorNetworkTopologyService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.user.UserService;
import com.soc.service.workorder.WorkOrderService;
import com.soc.webapp.action.BaseAction;
import com.topo.model.device.Device;
import com.topo.service.device.DeviceManageService;
import com.topo.util.Ping;
import com.util.DateUtil;
import com.util.FreeMarkerUtil;
import com.util.StringUtil;
import com.util.email.WorkOrderSendMail;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AlertMessageAction extends BaseAction {

	// 告警信息管理类
	private AlertMessageSerive alertMessageManager;

	private AssetService assetManager;// 资产管理类
	// 设置的管理类
	private SettingService settingManager;

	// 派发工单时选择的用户管理类
	private UserService userManager;

	// 所有用户的list
	private List<User> userList;

	// 告警信息的列表
	private List<AlertMessage> alertMessageList;

	// 搜索关键字
	private String keyword;

	// 接受选中的id的字符串
	private String ids;

	// 用于导出的选中id的字符串
	private String checkids;

	// 告警信息接受者
	private String alertReceiver;

	// 告警信息发送方式
	private String alertSendway;
	// 用作拓扑界面过来的告警id 另一功能不明
	private int currentCount;

	// 工单实体类实例
	private WorkOrder workOrder;

	// 工单操作者Id
	private long workOrderHandleUserId;

	// 工单审核者Id
	private long workOrderAuditUserId;

	// 工单的service层
	private WorkOrderService workOrderManager;

	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
	// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
	private String reportType;

	private boolean exist;// 文件是否存在

	private long alertReportId;

	// 告警触发的资产
	private String rel_assetName;

	private String rel_eventType;

	private String rel_deviceType;
	private String alertRank;
	// 接收关联工单的ID
	private long alertID;

	private AuditService auditManager;
	// 网络拓扑模块界面跳到这个actio按照资产名字查询告警 但是返回的界面中有快速搜索栏 用这个变量判断是不是拓扑模块过来的
	// 1 表示是拓扑界面过来的
	private int isMonitorNetworkTopology;
	// 拓扑的Manager类
	private MonitorNetworkTopologyService monitorNetworkTopologyManager;
	// 拓扑图的url
	private String url;
	private DeviceManageService deviceManageService;
	// 设备种类名称
	private List<Map> categoryName;

	public String getAlertRank() {
		return alertRank;
	}

	public void setAlertRank(String alertRank) {
		this.alertRank = alertRank;
	}

	// 用来判断是查看还是添加
	private String type;

	private String queryFlag;

	public String alertMessageQuery() {

		LOG.info("[AlertMessageAction] enter method alertMessageQuery() ...");

		HttpServletRequest request = super.getRequest();

		Page page = null;
		SearchResult sr = null;
		// 处理数据分页的起始条数
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
		// 模糊查询

		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(rel_assetName)) {
			map.put("rel_assetName", rel_assetName);
		}
		if (StringUtil.isNotBlank(rel_eventType)) {
			map.put("rel_eventType", rel_eventType);
		}
		if (StringUtil.isNotEmpty(alertReceiver)) {
			map.put("userRealName", alertReceiver);
		}
		if (StringUtil.isNotEmpty(alertRank)) {
			map.put("alertRank", Integer.parseInt(alertRank));
		}
		if (StringUtil.isNotEmpty(alertSendway)) {
			map.put("sendMode", alertSendway);
		}
		// 解决最近告警列表的查看;
		if (request.getParameter("flag") != null) {
			long timstmp = System.currentTimeMillis() - 60000;

			map.put("timstmp", timstmp);
		}
		List<Long> ruleUserId = new ArrayList<Long>();
		ruleUserId
				.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserId());
		long groupid = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		// 当前用户关联的资产
		String assetId = assetManager.queryIDSByUser(groupid);
		/** 
		 * 判断是否选择高级查询的设备类型
		 */
		if (StringUtil.isNotBlank(rel_deviceType)) {
			// 根据用户选择的设备类型，去查询此设备类型下的子设备类型
			String info = assetManager.queryCategoryByFatherId(Long
					.parseLong(rel_deviceType));
			/**
			 * 如果查询出来的设备类型为空或者null，说明选择的设备类型没有关联任何资产。 如果不为空的话，就去查询选择的设备类型是否关联到资产
			 */
			if (StringUtil.isNotEmpty(info)) {
				// map.put("rel_deviceType", info);
				/**
				 * 将查询出来的设备类型数据重组
				 */
				StringBuilder sb = new StringBuilder();
				String[] infos = info.split(",");
				int falg = 0;
				for (String str : infos) {
					if (falg == 0) {
						sb.append("'").append(str).append("'");
					} else {
						sb.append(",'").append(str).append("'");
					}
					falg++;
				}
				Map assetMap = new HashMap();
				assetMap.put("categoryName", sb.toString());
				/**
				 * 权限控制，在用户关联的资产组下的所有资产去查询权限范围内关联选择设备类型的资产id串。
				 */
				assetMap.put("assetId", assetId);
				String alerAssetId = assetManager
						.queryAssetIdByCategoryName(assetMap);
				if (StringUtil.isNotEmpty(alerAssetId)) {
					/**
					 * 根据查询到的资产id串去查询告警
					 */
					map.put("alertAssetId", alerAssetId);
				} else {
					/**
					 * 没有查询资产id串就赋值为0，说明没有告警信息
					 */
					map.put("alertAssetId", 0);
				}
			} else {
				map.put("alertAssetId", 0);
			}
		} else {
			// 修改根据用户关联的资产组去查询资产，然后根据资产去查询告警
			if (groupid == 1) {
			} else {
				map.put("alertAssetId", assetId);
			}
		}

		Map<String, Long> mapRule = new HashMap<String, Long>();

		// 暂时屏蔽谁建立的告警规则，谁能看相应的告警！

		/*
		 * mapRule.put("thisUserId", ruleUserId.get(0)); List<Map> alertRule12 =
		 * alertMessageManager.quertAlertRuleByID(mapRule); String b = "" ;
		 * if(alertRule12.size()<1){ // return SUCCESS; } else {
		 * 
		 * 
		 * for(int j=0;j<alertRule12.size();j++) { Map map1 =
		 * alertRule12.get(j); if(j==alertRule12.size()-1) {
		 * 
		 * b = b+map1.get("alarm_rule_id"); } else { b =
		 * b+map1.get("alarm_rule_id")+","; } } } map.put("ruleId1",b);
		 */

		// 判断是拓扑图的请求把资产的id赋给alertAssetId 后台查询
		if (this.isMonitorNetworkTopology == 1) {
			map.put("alertAssetId", currentCount);
		}
		sr = alertMessageManager.alertQuery(map, page);

		if (sr != null) {
			List<AlertMessage> ruleList = (List<AlertMessage>) sr.getList();
			for (int i = 0; i < ruleList.size(); i++) {
				String name = ruleList.get(i).getAlertEventName();

				String type = ruleList.get(i).getAlertEventType();

				try {

					Long temp = Long.valueOf(name);
					ruleList.get(i).setAlertEventName(
							GlobalConfig.eventTypeTag.get(temp));
				} catch (Exception e) {

					log.info("转化异常");
				}
				try {
					ruleList.get(i).setAlertEventType(
							GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
				} catch (Exception e) {
					log.warn("转化错误");
				}
			}
			request.setAttribute("alertList", ruleList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		categoryName = assetManager.queryCategory();
		return SUCCESS;
	}

	/**
	 * <获得近期60秒内的告警信息条数> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void queryCurrent() {

		//long temp = System.currentTimeMillis() - 60000;

		// //System.out.println(temp);

		Map<String, Object> map = new HashMap<String, Object>();
//
//		List<Long> ruleUserId = new ArrayList<Long>();
//
//		ruleUserId
//				.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
//						.getUserId());
//		Map<String, Long> mapRule = new HashMap<String, Long>();
//		mapRule.put("thisUserId", ruleUserId.get(0));
//
//		List<Map> alertRule12 = new ArrayList<Map>();
//
//		alertRule12 = alertMessageManager.quertAlertRuleByID(mapRule);

		// System.out.println(alertRule12.size());

//		if (alertRule12.size() < 1) {
//
//			currentCount = 0;
//		} else {
//			String b = "";
//			for (int j = 0; j < alertRule12.size(); j++) {
//				Map map1 = alertRule12.get(j);
//
//				if (j == alertRule12.size() - 1) {
//
//					b = b + map1.get("alarm_rule_id");
//				} else {
//					b = b + map1.get("alarm_rule_id") + ",";
//				}
//			}
			long groupid = ((User) this.getSession().getAttribute(
					"SOC_LOGON_USER")).getAssetGroupid();
			String assetId = assetManager.queryIDSByUser(groupid);
			map.put("assetid", assetId);

		//	map.put("ruleId1", b);

		//	map.put("timeStmp", temp);

			currentCount = alertMessageManager.queryCurrent(map);

	//	}

		String temp1 = String.valueOf(currentCount);

		try {

			getResponse().getWriter().write(temp1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//查询未关闭告警信息
	public void queryNotCloseAlertMessage(){
		Map<String, Object> map = new HashMap<String, Object>();
		long groupid = ((User) this.getSession().getAttribute(
				"SOC_LOGON_USER")).getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
		map.put("assetid", assetId);
		map.put("isClose", 1);
		List<AlertMessage> list = alertMessageManager.queryNotCloseAlertMessage(map);
		StringBuilder sb = new StringBuilder();
		StringBuilder sId = new StringBuilder();
		int falgId =0;
		for (AlertMessage alertMessage : list) {
			if(falgId==0){
				sId.append(alertMessage.getAlertId());
			}else{
				sId.append(","+alertMessage.getAlertId());
				
			}
			falgId++;
			/*String name="";
			try {
				Long temp = Long.valueOf(alertMessage.getAlertEventName());
					name =GlobalConfig.eventTypeTag.get(temp);
			} catch (Exception e) {
				name = alertMessage.getAlertEventName();
				log.info("转化异常");
			}*/
			sb.append("<tr id=\"tr_"+alertMessage.getAlertId()+"\" class=\"back\" ><td align=\"center\" >"
					+ alertMessage.getAlertRank()
					+ "</td><td align=\"center\" >"
					+ alertMessage.getAlertId()
					+ "</td><td align=\"center\" >"
					+ alertMessage.getAlertEventName()
					+ "</td><td align=\"center\"  >"
					+ alertMessage.getAlertDeviceName()
					+"</td><td align=\"center\" ><a href=\"javascript:void(0);\" onclick=\"isClose("+alertMessage.getAlertId()+");\">关闭</a>"
					+ "</td></tr>");
		}
		try {
			// Ajax返回
			getResponse().getWriter().write(sb.toString()+"$$"+sId.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 
	 * 跳转到增加工单的页面
	 */
	public String toAddWorkorder() {
		HttpServletRequest request = super.getRequest();
		if (type != null && type.equals("detail")) {
			workOrder = workOrderManager.queryByAlarmId(alertID);
		}
		keyword = request.getParameter("keyword");
		if (StringUtil.isNotEmpty(keyword)) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
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
//查询告警工单
	public String queryWorkorderByAlertId(){
		workOrder = workOrderManager.queryByAlarmId(alertID);
		return SUCCESS;
	}
	// 根据告警ID验证工单是否存在
	public void checkWorkOrder() {
		WorkOrder wo = workOrderManager.queryByAlarmId(alertID);
		JSONObject json = JSONObject.fromObject(wo);
		try {
			getResponse().getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//关闭告警
	public void closeAlaemMessage(){
		Map map = new  HashMap();
		if(StringUtil.isNotEmpty(checkids)){
			map.put("alertIds", checkids);
		}
		if(alertID>0){
			map.put("alertId", alertID);
			
		}
		alertMessageManager.closeAlertMessageById(map);
	}
	// 增加工单的方法
	public String addWorkorder() {
		log.info("[AlertMessageAction] enter addWorkorder()");
		Map map = new HashMap();
		HttpServletRequest request = super.getRequest();
		map.put("alertID", workOrder.getAlarmID());
		workOrder.setProduceDate(new Date());
		int id= workOrderManager.insertWorkOrder(workOrder);
		workOrder.setWorkOrderId(id);
		List<String> fieldList1 = new ArrayList<String>();
		fieldList1.add(workOrder.getWorkOrderName() + "("
				+ workOrder.getWorkOrderName() + ")");
		String logString = "";
		auditManager.insertByInsertOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "工单", super
				.getRequest().getRemoteAddr(), fieldList1);
		logManager.writeSystemAuditLog(
				((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName(), getRequest().getRemoteAddr(),
				DateUtil.curDateTimeStr19(), "新增工单");
		//List<User> ulist = new ArrayList<User>();

		/*User AuditUser = userManager.queryByUserId(workOrderAuditUserId);
		User HandleUser = userManager.queryByUserId(workOrderHandleUserId);
		if (AuditUser != null) {
			// 添加工单审核者
			ulist.add(userManager.queryByUserId(workOrderAuditUserId));
		}
		if (HandleUser != null) {
			// 添加工单操作者
			ulist.add(userManager.queryByUserId(workOrderHandleUserId));
		}*/
		AlertMessage alertMessage = alertMessageManager.queryById(map);
		/*try {
			alertMessage.setAlertEventType(GlobalConfig.eventTypeTag.get(Long
					.valueOf(alertMessage.getAlertEventType())));
		} catch (Exception e) {
			log.warn("转化错误");
		}*/
		User HandleUser = userManager.queryByUserId(workOrderHandleUserId);
		if (HandleUser!=null&&workOrder.getSendMail()==1) {
			WorkOrderSendMail sendMail=WorkOrderSendMail.initialization(HandleUser, workOrder, settingManager);
			sendMail.emailHandling();
		}
			
		
		alertMessageManager.updateAlertMassage(map);
		try {
			keyword = java.net.URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 派发完工单，看该资产还存不存在没有处理的告警信息，有则继续告警，没有ping一下，并发送一次告警信息
		Device device = deviceManageService.queryDeviceByAssetId(alertMessage
				.getAlertAssetId());
		Map amap = new HashMap();
		amap.put("ids", device.getDevice_asset_id());
		amap.put("workorder", "01");
		List<AlertMessage> amList = new ArrayList<AlertMessage>();
		amList = alertMessageManager.queryByAssetId(amap);
		if (amList.size() != 0) {
			device.setDevice_status(amList.get(amList.size() - 1)
					.getAlertRank());
		} else {
			Ping ping = new Ping(device.getDevice_ip());
			String result = ping.pingIP();
			if (StringUtil.isNotBlank(result)) {
				device.setDevice_status(1);
			} else {
				device.setDevice_status(0);
			}
		}
		deviceManageService.updateDeviceByAssetId(device);

		int count = alertMessageManager.queryCountAlert();
		String centerNatIp = settingManager.queryByKey("centerNatIp");
		logString = centerNatIp + "::" + String.valueOf(count) + "::"
				+ device.getDevice_name();
		for (int i = 0; i <= 2; i++) {
			System.out.println(logString + "===========================");
			logManager.writeSystemAuditLog(logString);
		}
		// request.getParameter("keyword");
		return SUCCESS;
		
	}

	/**
	 * <导出excel表> <把告警信息导出excel>
	 * 
	 * @see [ExcelAudit,StringUtil]
	 */
	public void export() {
		LOG.info("[SEcurityBulletinAction] enter method export() ...");
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		ExcelAlertMasage excelAlertMasage = new ExcelAlertMasage();
		Map limit = new HashMap();
		if (StringUtil.isNotEmpty(request.getParameter("keyword"))) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			limit.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(rel_eventType)) {
			limit.put("rel_eventType", rel_eventType);
		}
		if (StringUtil.isNotBlank(rel_deviceType)) {
			limit.put("rel_deviceType", rel_deviceType);
		}
		if (StringUtil.isNotEmpty(alertReceiver)) {
			limit.put("userRealName", alertReceiver);
		}
		if (StringUtil.isNotEmpty(alertRank)) {
			limit.put("alertRank", Integer.parseInt(alertRank));
		}
		if (StringUtil.isNotEmpty(alertSendway)) {
			limit.put("sendMode", alertSendway);
		}
		if (StringUtil.isNotBlank(ids)) {
			limit.put("ids", ids);
		} else {
			long groupid = ((User) this.getSession().getAttribute(
					"SOC_LOGON_USER")).getAssetGroupid();
			String assetId = assetManager.queryIDSByUser(groupid);
			if (groupid == 1) {
			} else {
				limit.put("alertAssetId", assetId);
			}
		}

		excelAlertMasage.export(alertMessageManager.exportExcel(limit),
				"告警信息数据表");
		try {
			// 中文文件名需要编码
			String fileName = "alertMessagelog_" + DateUtil.curDateStr8();
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName + ".xls");
			OutputStream os = response.getOutputStream();
			excelAlertMasage.getGwb().write(os);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 导出doc
	public String ExportReport() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		HttpServletResponse resp = ServletActionContext.getResponse();
		boolean isPdf = false;// 判断此类型是不是pdf
		createReportFile(alertReportId, reportType, isPdf);

		OutputStream out;// 输出响应正文的输出流
		InputStream in;// 读取本地文件的输入流
		// 获得本地输入流
		String fileName = getReportNameById(alertReportId, reportType);
		File file = new File(path + "reportFile" + File.separator + fileName);
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

	private void createReportFile(long alertReportId, String reportType,
			boolean isPdf) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		if (alertReportId == 11) {
			Page page = null;
			HttpServletRequest request = super.getRequest();

			// 用来存放查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			if (request.getParameter("keyword") != null) {
				try {
					keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				map.put("keyword", keyword);
			}

			if (StringUtil.isNotBlank(rel_eventType)) {
				map.put("rel_eventType", rel_eventType);
			}
			if (StringUtil.isNotBlank(rel_deviceType)) {
				map.put("rel_deviceType", rel_deviceType);
			}
			if (StringUtil.isNotEmpty(alertReceiver)) {
				map.put("userRealName", alertReceiver);
			}
			if (StringUtil.isNotEmpty(alertRank)) {
				map.put("alertRank", Integer.parseInt(alertRank));
			}
			if (StringUtil.isNotEmpty(alertSendway)) {
				map.put("sendMode", alertSendway);
			}

			if (StringUtil.isNotEmpty(ids)) {
				map.put("ids", ids);
			} else {
				long groupid = ((User) this.getSession().getAttribute(
						"SOC_LOGON_USER")).getAssetGroupid();
				String assetId = assetManager.queryIDSByUser(groupid);
				if (groupid == 1) {
				} else {
					map.put("alertAssetId", assetId);
				}
			}

			alertMessageList = alertMessageManager.alertQueryDoc(map);

			for (int i = 0; i < alertMessageList.size(); i++) {
				String name = alertMessageList.get(i).getAlertEventName();

				if (alertMessageList.get(i).getWorkorder().equals("01")) {
					alertMessageList.get(i).setWorkorder("未派发");
				} else if (alertMessageList.get(i).getWorkorder().equals("02")) {
					alertMessageList.get(i).setWorkorder("已派发");
				}
				try {
					Long temp = Long.valueOf(name);
					alertMessageList.get(i).setAlertEventName(
							GlobalConfig.eventTypeTag.get(temp));
				} catch (Exception e) {

					log.info("转化异常");
				}
				try {
					String type = alertMessageList.get(i).getAlertEventType();
					alertMessageList.get(i).setAlertEventType(
							GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
				} catch (Exception e) {
					log.warn("转化错误");
				}
			}

			mapStatic.put("list", alertMessageList);
		}

		if (isPdf) {// 如果是pdf,需要加载html模板
			reportType = "html";
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path + "reportFile"
							+ File.separator
							+ getReportNameById(alertReportId, reportType))),
					"utf-8"));
			// map.put("image", new File(path +
			// "/reportFormImages/diag1.jpg"));//把图片加进去
			// 这里根据生成的文件类型加载不同的模板 因为以后report6到9导出doc的时候加载的是wrod模板 其他都是html模板
			// 这里要判断
			if (alertReportId >= 6 && alertReportId <= 11) {
				fm.loadTemplate("freemarkerAlertMessage" + reportType
						+ alertReportId + ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else {
				fm.loadTemplate("freemarkerhtml" + alertReportId + ".ftl",
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

	private String getReportNameById(long alertReportId, String reportType) {
		String fileName = null;
		try {
			switch ((int) alertReportId) {
			case 2:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25AE%25A1%25E7%2590%2586%25E5%25B9%25B3%25E5%258F%25B0%25E6%259C%2588%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 3:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E8%25BF%2590%25E8%25A1%258C%25E7%258A%25B6%25E6%2580%2581%25E6%258A%25A5%25E5%25AE%258C%25E6%2588%2590%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 4:
				fileName = java.net.URLDecoder
						.decode("%25E6%25BC%258F%25E6%25B4%259E%25E6%2589%25AB%25E6%258F%258F%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 5:
				fileName = java.net.URLDecoder
						.decode("%25E8%25AE%25BE%25E5%25A4%2587%25E6%2598%258E%25E7%25BB%2586%25E7%25BB%259F%25E8%25AE%25A1%25E6%2597%25A5%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 6:
				fileName = java.net.URLDecoder
						.decode("%25E4%25BA%258B%25E4%25BB%25B6%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E6%259C%2588%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 7:
				fileName = java.net.URLDecoder
						.decode("%25E5%259F%259F%25E9%25A3%258E%25E9%2599%25A9%25E6%2598%258E%25E7%25BB%2586%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 8:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E5%2591%25A8%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 9:
				fileName = java.net.URLDecoder
						.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E7%25B1%25BB%25E5%259E%258B%25E7%25BB%259F%25E8%25AE%25A1%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 10:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E5%2585%25AC%25E5%2591%258A%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 11:
				fileName = java.net.URLDecoder
						.decode("%25E5%2591%258A%25E8%25AD%25A6%25E4%25BF%25A1%25E6%2581%25AF%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	

	/**
	 * <拓扑图部分根据资产的id查询出告警信息> <如果有告警则图片变成gif>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryAlarmByAssetIdWhereWorkorderEqual01() {
		Map map = new HashMap();
		map.put("ids", super.getRequest().getParameter("id"));
		map.put("createDate", System.currentTimeMillis() - 12 * 60 * 60 * 1000);
		List<AlertMessage> alertMessage = this.alertMessageManager
				.queryByAssetId(map);
		// 根据id获得node类
		Node node = this.monitorNetworkTopologyManager.queryById(Integer
				.parseInt(map.get("ids").toString()));
		url = node.getUrl();
		// 如果不是空说明产生了新告警 改变图片
		if (alertMessage.isEmpty()) {
			// 获得他的url 然后操作
			url = url.replaceAll("giftopo", "available").replaceAll("gif",
					"png");
			if (node.getState() == 1) {
				/**
				 * //等于1说明是不通的设备 如果这里不判断一次 有告警的时候会变成彩色的gif闪动, 处理完告警以后会变成彩色图片
				 */
				url = url.replaceAll("giftopo", "unavailable").replaceAll(
						"gif", "png");
			} else {
				url = url.replaceAll("giftopo", "available").replaceAll("gif",
						"png");
			}
		} else {
			if (node.getState() == 1) {
				url = url.replaceAll("unavailable", "giftopo").replaceAll(
						"png", "gif");
			} else {
				url = url.replaceAll("available", "giftopo").replaceAll("png",
						"gif");
			}

		}
		return SUCCESS;
	}

	// 查看四个告警等级当天的记录数
	public String queryAlertRankCount() {
		HttpServletRequest request = super.getRequest();
		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		// 存放不同告警等级的记录数
		Integer count[] = new Integer[5];
		// 取得当前操作系统的文件分隔符
		String seperator = File.separator;
		// 取得配置文件的绝对路径
		String path = getRealPath() + "images" + seperator + "topo" + seperator
				+ "navigate" + seperator + "switch.xml";
		// 取得当天零点零分零秒的时间
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long timstmp = cal.getTimeInMillis();
		// 根据用户关联的资产组去查询资产
		long groupid = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
		if (groupid == 1) {
		} else {
			map.put("alertAssetId", assetId);
		}
		map.put("timstmp", timstmp);
		for (int i = 1; i < 6; i++) {
			map.put("alertRank", i);
			count[i - 1] = alertMessageManager.count(map);
		}
		try {
			// 文本的形式覆盖xml文件
			OutputStream output = new FileOutputStream(new File(path));
			BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(
					output, "UTF-8"));
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			str.append("<settings autoRotate=\"1\" autoRotateSpeed=\"4\" ");
			str.append("useSubtitle=\"1\" useTooltip=\"0\" useSecondCaption=\"1\" ");
			str.append("spanX=\"352\" spanY=\"40\" centerX=\"500\" centerY=\"300\" ");
			str.append("distanceValue=\"0\" perspectiveRatio=\"0.5\" minimumscale=\".1\" ");
			str.append("turningspeed=\"10\" rotationKind=\"1\" useFocalBlur=\"1\" ");
			str.append("focalBlurValue=\".6\" useMotionBlur=\"1\" motionBlurValue=\".25\" ");
			str.append("useFadeOnMouseOver=\"1\" mouseOverAlphaValue=\".5\" useReflection=\"1\" ");
			str.append("reflectionAlphaValue=\"0\"/>");
			str.append("<photos>");
			for (int i = 0; i < 3; i++) {
				str.append("<photo imageURL=\"../images/topo/0" + (i + 1)
						+ ".jpg\" ");
				// 图片链接
				str.append("linkType=\"URL\" linkTarget=\"_blank\" ");
				// 一级标题
				switch (i) {
				case 0:
					str.append("linkData=\"" + request.getContextPath()
							+ "/flexhtml/BusinessModel.html\" ");
					str.append("captionText=\"内网拓扑\" ");
					break;
				case 1:
					str.append("linkData=\"" + request.getContextPath()
							+ "/flexhtml/IntranetBusinessModel.html\" ");
					str.append("captionText=\"外网拓扑\" ");
					break;
				case 2:
					str.append("linkData=\"" + request.getContextPath()
							+ "/alertMessage/alertMessageQuery.action\" ");
					str.append("captionText=\"告警信息\" ");
					break;
				}
				// 告警信息
				str.append("captionText2=\"当日告警：");
				str.append("严重" + count[4] + "  重要 " + count[3] + "  一般 "
						+ count[2] + "  轻微 " + count[1] + "  \" />");
			}
			str.append("</photos>");
			buff.write(str.toString());
			buff.flush();
			output.close();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查询所有没有派发工单的告警信息（裴秀梅）
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryAllAlertInfo() throws Exception {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		List<AlertMessage> list = new ArrayList<AlertMessage>();
		// 取得当天零点零分零秒的时间
		/*
		 * Calendar calendar=Calendar.getInstance();
		 * calendar.set(Calendar.HOUR_OF_DAY,0);
		 * calendar.set(Calendar.MINUTE,0); calendar.set(Calendar.SECOND,0);
		 * calendar.set(Calendar.MILLISECOND,0);
		 * 
		 * long timstmp = calendar.getTimeInMillis();
		 */
		/*
		 * SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HHmmss"); Date
		 * curDate = calendar.getTime(); String str = sdf.format(curDate);
		 */
		// AlertMessage alertMessage=new AlertMessage();
		// alertMessage.setAlertCreateDatetime(timstmp);
		// alertMessage.setAlertMark(0);
		Map map = new HashMap();
		map.put("alertMark", 0);
		long groupid = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
		if (groupid == 1) {
		} else {
			map.put("alertAssetId", assetId);
		}
		list = alertMessageManager.queryAllAlertInfo(map);
		StringBuffer sb = new StringBuffer();
		for (AlertMessage bean : list) {
			sb.append("事件级别：");
			sb.append(String.valueOf(bean.getAlertRank()));
			sb.append(",事件名称：");
			sb.append(bean.getAlertEventName());
			sb.append(",事件类型：");
			sb.append(GlobalConfig.eventTypeTag.get(Long.valueOf(bean
					.getAlertEventType())));
			sb.append(",设备名称：");
			sb.append(bean.getAlertDeviceName());
			sb.append(",接收时间：");
			sb.append(bean.getDate());
		}
		// System.out.print(sb.toString());
		outPutString(request, response, sb);
	}

	public void outPutString(HttpServletRequest request,
			HttpServletResponse response, StringBuffer str) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(str.toString());
		out.flush();
	}

	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}

	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}

	public String getAlertReceiver() {
		return alertReceiver;
	}

	public void setAlertReceiver(String alertReceiver) {
		this.alertReceiver = alertReceiver;
	}

	public String getAlertSendway() {
		return alertSendway;
	}

	public void setAlertSendway(String alertSendway) {
		this.alertSendway = alertSendway;
	}

	public List<AlertMessage> getAlertMessageList() {
		return alertMessageList;
	}

	public void setAlertMessageList(List<AlertMessage> alertMessageList) {
		this.alertMessageList = alertMessageList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRel_assetName() {
		return rel_assetName;
	}

	public void setRel_assetName(String rel_assetName) {
		this.rel_assetName = rel_assetName;
	}

	public String getRel_eventType() {
		return rel_eventType;
	}

	public void setRel_eventType(String rel_eventType) {
		this.rel_eventType = rel_eventType;
	}

	public String getRel_deviceType() {
		return rel_deviceType;
	}

	public void setRel_deviceType(String rel_deviceType) {
		this.rel_deviceType = rel_deviceType;
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

	public long getAlertReportId() {
		return alertReportId;
	}

	public void setAlertReportId(long alertReportId) {
		this.alertReportId = alertReportId;
	}

	public long getAlertID() {
		return alertID;
	}

	public void setAlertID(long alertID) {
		this.alertID = alertID;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public WorkOrderService getWorkOrderManager() {
		return workOrderManager;
	}

	public void setWorkOrderManager(WorkOrderService workOrderManager) {
		this.workOrderManager = workOrderManager;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public long getWorkOrderHandleUserId() {
		return workOrderHandleUserId;
	}

	public void setWorkOrderHandleUserId(long workOrderHandleUserId) {
		this.workOrderHandleUserId = workOrderHandleUserId;
	}

	public long getWorkOrderAuditUserId() {
		return workOrderAuditUserId;
	}

	public void setWorkOrderAuditUserId(long workOrderAuditUserId) {
		this.workOrderAuditUserId = workOrderAuditUserId;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public int getIsMonitorNetworkTopology() {
		return isMonitorNetworkTopology;
	}

	public void setIsMonitorNetworkTopology(int isMonitorNetworkTopology) {
		this.isMonitorNetworkTopology = isMonitorNetworkTopology;
	}

	public MonitorNetworkTopologyService getMonitorNetworkTopologyManager() {
		return monitorNetworkTopologyManager;
	}

	public void setMonitorNetworkTopologyManager(
			MonitorNetworkTopologyService monitorNetworkTopologyManager) {
		this.monitorNetworkTopologyManager = monitorNetworkTopologyManager;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DeviceManageService getDeviceManageService() {
		return deviceManageService;
	}

	public void setDeviceManageService(DeviceManageService deviceManageService) {
		this.deviceManageService = deviceManageService;
	}

	public List<Map> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(List<Map> categoryName) {
		this.categoryName = categoryName;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	/*
	 * public static void main(String[] args) { AlertMessage alert =new
	 * AlertMessage(); alert.setAlertNumber(1); alert.setAlertRank(4);
	 * alert.setAlertSendWay("邮箱"); alert.setAlertState(0);
	 * alert.setAlertTitle("234"); alert.setAlertReceiver("123");
	 * alert.setAlertCreateDatetime(System.currentTimeMillis());
	 * 
	 * AlertMessageDaoIbatis manager =new AlertMessageDaoIbatis();
	 * manager.insertAlertMessage() }
	 */
}
