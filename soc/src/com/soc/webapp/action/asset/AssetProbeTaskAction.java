package com.soc.webapp.action.asset;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetProbe;
import com.soc.model.asset.AssetProbeTask;
import com.soc.model.asset.ProbeState;
import com.soc.model.systemsetting.Collector;
import com.soc.model.user.User;
import com.soc.service.asset.AssetProbeService;
import com.soc.service.asset.AssetProbeTaskService;
import com.soc.service.asset.ProbeStateService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 资产任务增、删、改、查action
 * 
 * @author 张浩磊
 * 
 */
public class AssetProbeTaskAction extends BaseAction {
	
	// 审计业务管理类
	private AuditService auditManager;
	// 资产任务业务类
	private AssetProbeTaskService assetProbeTaskManager;
	// 探测任务实体类
	private AssetProbeTask assetProbeTask;

	// 资产信息实体类
	private AssetProbe assetProbe;
	private List<AssetProbe> assetProbeList;
	private Collector collector;
    //采集器
    private List<Collector> collectorList;
    
    //采集器业务管理类
    private SettingCollectorService collectorManager;
	private ProbeStateService probeStateServiceManager;
	// 资产的业务处理类
	private AssetProbeService assetProbeServiceManager;

	private String collectorMacs;// 采集器mac地址
	private int error;// 采集器运行状态
	private int result;// 采集器返回结果

	private String deviceType;// 资产设备类型
	private String deviceMemo;// 资产设备描述
	private String deviceIp;// 资产Ip地址
	private String deviceMac;// 资产MAC地址

	private int taskId;// 探测任务ID
	private String keyword;// 页面获得的查询参数
	private String taskName;// 任务名称
	private String collectorName;// 采集器名称
	private String collectorMac;// 采集器mac
	private String beginIp;// 开始IP
	private String endIp;// 结束IP
	private String medo;// 任务描述
	private String ids;// 页面获得的ids
	private List<Map<String,Object>> taskList  ; 

	private String selTaskName ; 
	private int selTaskState ; 
	
	
	/**
	 * 增加一条资产任务
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String addTask() {
		log.info("[AssetProbeTaskAction] Enter method addTask()...");
		AssetProbeTask aTask = new AssetProbeTask();

		aTask.setTaskName(taskName);
		aTask.setCollectorName(collectorName);
		aTask.setCollectorMac(collectorMac);
		aTask.setBeginIp(beginIp);
		aTask.setEndIp(endIp);
		aTask.setMedo(medo);
		// assetProbeTaskManager.insertTask(aTask);
		if (assetProbeTaskManager.insertTask(aTask) > 0) {
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(aTask.getTaskName() + "("
					+ aTask.getTaskName() + ")");
			// 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产探测任务", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增资产探测任务");
			return SUCCESS;
		}
		return INPUT;
	}

	/**
	 * 查询所有资产任务信息
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryTask() {
		/*HttpServletRequest request = super.getRequest();
		List<AssetProbeTask> tasklist = new ArrayList<AssetProbeTask>();

		Page page = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		SearchResult result = assetProbeTaskManager.queryProbe(page);
		if (tasklist != null) {
			request.setAttribute("Page", result.getPage());
			request.getSession().setAttribute("tasklist", result.getList());
			return SUCCESS;
		}
		return INPUT;*/
		Page page = null;
		HttpServletRequest request = super.getRequest();
		List<AssetProbeTask> tasklist = new ArrayList<AssetProbeTask>();
		
		try{
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		}catch (Exception e) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			Page.setKeyword(keyword);
		}
		SearchResult result = assetProbeTaskManager.queryAllTask(page);
		if(result != null){
			request.setAttribute("Page", result.getPage());
			request.getSession().setAttribute("tasklist", result.getList());

			return SUCCESS;
		}
		
		
		return INPUT;
	}

	/**
	 * 根据名称快速查询探测任务
	 * 
	 */

	public String queryName() {
		HttpServletRequest request = super.getRequest();
		List<AssetProbeTask> tasklist = new ArrayList<AssetProbeTask>();

		Page page = null;

		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		SearchResult result = new SearchResult() ;  
		try {
			keyword = java.net.URLDecoder. decode ( keyword ,  "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = assetProbeTaskManager.queryName(page,keyword);
		if (result != null) {
			//page.setTotalCount(tasklist.size());
			request.setAttribute("Page", page);
			request.getSession().setAttribute("tasklist", result.getList());
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * 高级查询
	 * @return 
	 */
	public String proQuery(){
		HttpServletRequest request = super.getRequest();
		List<AssetProbeTask> tasklist = new ArrayList<AssetProbeTask>();

		Page page = null;

		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		
		Map map = new HashMap() ; 
		if(StringUtil.isNotEmpty(selTaskName)){
			map.put("taskName", selTaskName);
		}
		if(selTaskState != 10){
			if(selTaskState == -1){
				map.put("taskState", "未探测") ; 
			}else if(selTaskState == 0){
				map.put("taskState", "探测完成") ; 
			}else if(selTaskState == 1){
				map.put("taskState", "正在探测") ; 
			}
		}
		SearchResult result = new SearchResult();
		result = assetProbeTaskManager.queryPro(page, map);
		if(result!=null){
			request.setAttribute("Page", result.getPage());
			request.getSession().setAttribute("tasklist", result.getList());
            return SUCCESS ; 
			
		}
		return  INPUT  ;
	}
	

	/**
	 * 删除指定资产探测任务
	 * 
	 * @return
	 */
	public void deleteTask() {
		log.info("[AssetProbeTaskAction] Enter deleteTask()...");
		List<String> fieldList = new ArrayList<String>();

		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String check : checked) {
					assetProbeTaskManager.deleteTask(Integer.parseInt(check));
					//删除关联的资产探测信息
					probeStateServiceManager.deleteProbeState(Integer.parseInt(check)) ; 
					
					assetProbeServiceManager.deleteByTaskId(Integer.parseInt(check));
				}
			} else {
				assetProbeTaskManager.deleteTask(Integer.parseInt(ids));
				probeStateServiceManager.deleteProbeState(Integer.parseInt(ids)) ; 
				
				assetProbeServiceManager.deleteByTaskId(Integer.parseInt(ids));
			}
		}
		
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "资产探测任务", super
				.getRequest().getRemoteAddr(), fieldList);

		// syslog
		/*String logString = "";

		logString = "登录名:"
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + " 源IP:"
				+ getRequest().getRemoteAddr() + " 操作时间:"
				+ DateUtil.curDateTimeStr19() + " 操作类型 :删除资产";
		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除资产探测任务");

		// 刷新父页面
		try {

			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/assetGroup/showGroupTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ " '/soc/assetProbeTask/queryTask.action';" + "</script>";

			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return SUCCESS;
	}

	/**
	 * 根据ID查询探测任务信息
	 * 
	 * @return
	 */
	public String editAssetTask() {
		log.info("[AssetProbeTaskAction] enter editAssetTaskAction..");
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
		// 查询当前选定的探测任务
		if (taskId != 0) {
			assetProbeTask = assetProbeTaskManager.queryByIdTask(taskId);
			collectorList = collectorManager.queryCollector();
		}
		return SUCCESS;
	}

	/**
	 * 生成XML到指定目录供c程序使用并完成后续处理 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String exportXml() throws Exception {

		HttpServletResponse response = super.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		PrintWriter out = response.getWriter();
		//if (StringUtil.equals(OSUtil.getOSName().trim().toLowerCase(),
		 //"linux")) 
		 if(1==1){
		if (ids != null) {

			assetProbeTask = assetProbeTaskManager.queryByIdTask(Integer
					.parseInt(ids));
					ProbeState proState = new ProbeState();
					// 检测到相应MAC地址定的数据执行更新
					proState.setTaskId(Integer.parseInt(ids)) ; 
					proState.setCollectorMacs(assetProbeTask.getCollectorMac());
					//proState.setError(0);
					proState.setError(1);
					proState.setResult(1);
					ProbeState ps =  probeStateServiceManager.queryByTaskId(Integer.parseInt(ids)) ; 
					
					if(ps == null){
					    probeStateServiceManager.insertState(proState);
					}else{
						probeStateServiceManager.updateState(proState) ; 
					}

			try {
				long times = System.currentTimeMillis();
				String time = times + "";
				Document document = DocumentHelper.createDocument();
				Element compliance = document.addElement("compliance");// 添加跟节点
				compliance.addAttribute("modified", time);
				// 添加其他节点
				Element head = compliance.addElement("head");
				Element size = head.addElement("size");
				size.setText("1024");
				Element package_id = head.addElement("package_id");
				package_id.setText(ids);
				Element device_id = head.addElement("device_id");
				device_id.setText("GUID");
				Element ip_type = head.addElement("ip_type");
				ip_type.setText("ipv4");
				Element src_GUID = head.addElement("src_GUID");
				src_GUID.setText("GUID");
				Element src_module = head.addElement("src_module");
				src_module.setText("mod_box_asset");
				Element des_GUID = head.addElement("des_GUID");
				des_GUID.setText("GUID");
				Element des_module = head.addElement("des_module");
				des_module.setText("mod_box_configure");
				Element content = compliance.addElement("content");
				Element type = content.addElement("type");
				// 添加属性
				type.addAttribute("id", "GUID");
				type.addAttribute("status", "3004");
				type.addAttribute("error_code", "0");
				type.addAttribute("type", "4001");
				Element time_event = content.addElement("time_event");
				// 添加属性
				time_event.addAttribute("auto", "");
				time_event.addAttribute("time_segment_begin",
						"2012-03-24 18:00");
				time_event.addAttribute("time_segment_end", "2012-03-25 09:00");
				time_event.addAttribute("level", "1");
				Element asset_work = content.addElement("asset_work");
				Element cmd_list = asset_work.addElement("cmd_list");
				Element ip_segment = cmd_list.addElement("ip_segment");
				// 添加属性
				ip_segment.addAttribute("ip_type", "ipv4");
				ip_segment.addAttribute("begin", assetProbeTask.getBeginIp());
				ip_segment.addAttribute("end", assetProbeTask.getEndIp());

				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("utf-8");
				// 將document中的內容寫入文件
				XMLWriter writer = new XMLWriter(System.out, format);
				document.normalize();
				writer.write(document);
				writer.close();

				XMLWriter writer2 = new XMLWriter(new FileWriter(new File(
						"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/" + assetProbeTask.getCollectorMac() + ".xml")),
						format);
				writer2.write(document);
				writer2.close();
				
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}

			 } else {
			 // OS不符
			 try {
			 response.getWriter().write("error_os");
			 } catch (IOException e) {
			 e.printStackTrace();
			 }
			 }
		} else if (ids == null) {
			out.print("<script>alert('执行探测失败,请重新操作！')</script>");
			return ERROR;
		}
		return INPUT;
	}

	/**
	 * 调用采集器action执行采集 并验证结果
	 * 
	 * @return
	 */
	public String testResult() throws IOException {
	
		HttpServletResponse response = this.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		PrintWriter out = response.getWriter();
		if (ids != null) {
			Page page = null;
			SearchResult sr = null;
			// 处理数据分页的起始条数
			String startIndex = request.getParameter("startIndex");

			if (StringUtil.isNotBlank(startIndex)) {
				page = new Page(Page.DEFAULT_PAGE_SIZE,
						Integer.valueOf(startIndex));
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			}
			assetProbeTask = assetProbeTaskManager.queryByIdTask(Integer
					.parseInt(ids));
			try{
			ProbeState probeState =probeStateServiceManager.queryMac(assetProbeTask
					.getCollectorMac());
			// 判断采集器执行状态做下一步处理
			if (probeState.getCollectorMacs() !=null) {
	
				// 如果采集器未出现异常(0无异常|1异常)
				if (probeState.getError() == 0) {
					// 检测采集器运行结果是否完成(0完成|1未完成)
					if (probeState.getResult() == 0) {
						assetProbeList = assetProbeServiceManager
								.queryByMac(assetProbeTask.getCollectorMac());
						if (assetProbeList.size() > 0) {
							page.setTotalCount(assetProbeList.size());

							request.setAttribute("Page", page);
						}
						request.getSession().setAttribute("assetProbeList",
								assetProbeList);
						return SUCCESS;
					} else {
						return INPUT;
					}
				} else {
					out.print("<script>alert('采集器出现未知异常,请重新操作！')</script>");
					return ERROR;

				}
			}
			}catch(Exception e){
				e.printStackTrace() ; 
				out.print("<script>alert('采集器出现未知异常,请重新操作！')</script>");
				return ERROR;
			}

		}
		out.print("<script>alert('参数异常,请重新操作！')</script>");
		return NONE;
	}

	/**
	 * 封转表格传的信息到asset实体类
	 */
	public String byValue() {
		HttpServletRequest request = ServletActionContext.getRequest();

		List<Asset> assetslist = new ArrayList<Asset>();
		
		Asset asset = new Asset();
		
		asset.setAssetDeviceType(deviceType);

		asset.setAssetMemo(deviceMemo);

		asset.setAssetIps(deviceIp);

		asset.setAssetMac(deviceMac);

		assetslist.add(asset);
		//collectorList = collectorManager.queryCollector();//获取所有采集器信息
		request.getSession().setAttribute("assetslist", assetslist);
		return SUCCESS;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	public AssetProbeTaskService getAssetProbeTaskManager() {
		return assetProbeTaskManager;
	}

	public void setAssetProbeTaskManager(
			AssetProbeTaskService assetProbeTaskManager) {
		this.assetProbeTaskManager = assetProbeTaskManager;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public AssetProbeTask getAssetProbeTask() {
		return assetProbeTask;
	}

	public void setAssetProbeTask(AssetProbeTask assetProbeTask) {
		this.assetProbeTask = assetProbeTask;
	}

	public String getCollectorName() {
		return collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getCollectorMac() {
		return collectorMac;
	}

	public void setCollectorMac(String collectorMac) {
		this.collectorMac = collectorMac;
	}

	public String getBeginIp() {
		return beginIp;
	}

	public void setBeginIp(String beginIp) {
		this.beginIp = beginIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public String getMedo() {
		return medo;
	}

	public void setMedo(String medo) {
		this.medo = medo;
	}

	public String getids() {
		return ids;
	}

	public void setids(String ids) {
		this.ids = ids;
	}

	public List<Collector> getCollectorList() {
		return collectorList;
	}

	public void setCollectorList(List<Collector> collectorList) {
		this.collectorList = collectorList;
	}

	public SettingCollectorService getCollectorManager() {
		return collectorManager;
	}

	public void setCollectorManager(SettingCollectorService collectorManager) {
		this.collectorManager = collectorManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ProbeStateService getProbeStateServiceManager() {
		return probeStateServiceManager;
	}

	public void setProbeStateServiceManager(
			ProbeStateService probeStateServiceManager) {
		this.probeStateServiceManager = probeStateServiceManager;
	}

	public AssetProbeService getAssetProbeServiceManager() {
		return assetProbeServiceManager;
	}

	public void setAssetProbeServiceManager(
			AssetProbeService assetProbeServiceManager) {
		this.assetProbeServiceManager = assetProbeServiceManager;
	}

	public String getCollectorMacs() {
		return collectorMacs;
	}

	public void setCollectorMacs(String collectorMacs) {
		this.collectorMacs = collectorMacs;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceMemo() {
		return deviceMemo;
	}

	public void setDeviceMemo(String deviceMemo) {
		this.deviceMemo = deviceMemo;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public AssetProbe getAssetProbe() {
		return assetProbe;
	}

	public void setAssetProbe(AssetProbe assetProbe) {
		this.assetProbe = assetProbe;
	}

	public List<AssetProbe> getAssetProbeList() {
		return assetProbeList;
	}

	public void setAssetProbeList(List<AssetProbe> assetProbeList) {
		this.assetProbeList = assetProbeList;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public List<Map<String, Object>> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Map<String, Object>> taskList) {
		this.taskList = taskList;
	}

	public String getSelTaskName() {
		return selTaskName;
	}

	public void setSelTaskName(String selTaskName) {
		this.selTaskName = selTaskName;
	}

	public int getSelTaskState() {
		return selTaskState;
	}

	public void setSelTaskState(int selTaskState) {
		this.selTaskState = selTaskState;
	}
	

	
}
