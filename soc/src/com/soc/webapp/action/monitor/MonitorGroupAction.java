package com.soc.webapp.action.monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;

import com.soc.model.asset.Asset;
import com.soc.model.conf.AgentModel;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorAlarm;
import com.soc.model.monitor.MonitorGroup;
import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.CustomTrendService;
import com.soc.service.monitor.MonitorAlarmService;
import com.soc.service.monitor.MonitorGroupService;
import com.soc.webapp.action.BaseAction;
import com.util.Compare;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <监控组的管理,APM的查看> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-10-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorGroupAction extends BaseAction {

	private String htmlBuffer;
	private String customBuffer;
	// 监控组的管理类
	private MonitorGroupService monitorGroupManager;

	// 监控组实体类
	private MonitorGroup monitorGroup;

	// 监控组的list
	private List<MonitorGroup> monitorGroupList;

	// 监控组的名称
	private String groupName;

	// 监控组的描述
	private String groupMemo;

	private String ids;

	// 资产的树形实现代码
	private String assetTreeBuffer;

	// 资产组的业务处理类
	private AssetGroupService assetGroupManager;

	// 资产组Id
	private String assetGroupId;

	private List<Asset> assetList;

	//接收快速搜索的字符
	private String keyword;
	
	// 高级查询资产名
	private String selAssetName;

	// 高级查询资产Ip
	private String selAssetIps;
	
	// 传递得到的mac
	private String assetMac;

	private JSONArray jsonArray;

	// agent端的list
	private List<AgentModel> agentModelList;

	// agent端实体对象
	private AgentModel agent;

	// 记录上次获得agent的最晚时间
	public static long lastTime;

	// apm告警
	private MonitorAlarmService monitorAlarmManager;

	// apm告警列表
	private List<MonitorAlarm> monitorAlarmList;

	// 资产业务处理类
	private AssetService assetManager;

	// 资产名称
	private String assetName;

	// 内部审计
	private AuditService auditManager;

	// 监控win进程信息的list
	private List<WinProgressMsg> winProgressMsgs;

	// 监控linux进程信息的list
	private List<LinuxProgressMsg> LinuxProgressMsgs;
	// 监控win服务信息的list
	private List<WinServiceMsg> winServiceMsgs;
	// 监控linux服务信息的list
	private List<LinuxServiceMsg> linuxServiceMsgs;
	// 监控win软件信息的list
	private List<WinSoftMsg> softMsgs;
	// 操作系统
	private  int assetSupportDeviceId;
	
	public static Long time;
	
	public static long mtime;

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	// 资产Id
	private Long AssetId;
	private CustomTrendService customTrendManager;

	public String showGroupTree() {

		log.info("[MonitorGroupAction] enter method showGroupTree() ...");

		String objectPath = super.getRequest().getContextPath();

		htmlBuffer = monitorGroupManager.createTree(objectPath);
		customBuffer = customTrendManager.createCustomTree(objectPath);
		//把监控的apm信息挪到了资产模块下。
		//long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid() ; 

		//assetTreeBuffer = assetGroupManager.getTreeStyle(objectPath,groupId);

		return SUCCESS;
	}

	/**
	 * <显示自定义组内的组> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String showGroups() {
		log.info("[MonitorGroupAction] enter method showGroups() ...");

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

		// 得到查询结果
		sr = monitorGroupManager.queryAll(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			monitorGroupList = (List<MonitorGroup>) sr.getList();

			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * <添加自定义组>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void addGroup() {

		log.info("[MonitorGroupAction] enter method addGroup() ...");

		List<String> fieldList = new ArrayList<String>();

		monitorGroup = new MonitorGroup();

		monitorGroup.setMonitorGroupName(groupName);

		monitorGroup.setMonitorGroupMemo(groupMemo);

		monitorGroup.setMonitorGroupCreateDateTime(new Date());

		monitorGroup.setMonitorGroupUpdateDateTime(new Date());

		monitorGroup.setMonitorGroupParentId(7);

		fieldList.add(monitorGroup.getMonitorGroupName() + "("
				+ monitorGroup.getMonitorGroupName() + ")");

		User u = (User) super.getSession().getAttribute("SOC_LOGON_USER");
		monitorGroup.setMonitorGroupCreateUserLoginName(u.getUserRealName());
		monitorGroup.setRelMonitorIds("0,0,0,0,0,0,0,0,0");
		monitorGroup.setMonitorGroupIsDelete(0);

		monitorGroupManager.insertGroup(monitorGroup);

		// 内部审计
		auditManager.insertByInsertOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "监控组", super
				.getRequest().getRemoteAddr(), fieldList);

		// syslog
		/*String logString = "";
		logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型:添加监控组";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增监控组");
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/monitorGroup/showGroupTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ "'/soc/monitorGroup/showGroups.action;'" + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * <删除自定义组> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteGroups() {
		log.info("[MonitorGroupAction] enter deleteGroups....");
		List<String> fieldList = new ArrayList<String>();

		// 判断获得的id为一个或者多个，多个执行if内函数,一个执行else内函数
		if (ids.indexOf(",") > 0) {
			// 将获得的ids按照,拆分成数组
			String[] checked = ids.split(",");
			// 循环遍历标记删除时间策略
			for (String checkid : checked) {
				MonitorGroup groupObject = monitorGroupManager.queryById(Long
						.parseLong(checkid));
				fieldList.add(groupObject.getMonitorGroupName() + "("
						+ groupObject.getMonitorGroupName() + ")");

				monitorGroupManager.deleteGroup(Long.parseLong(checkid));
			}
		} else {

			MonitorGroup groupObject = monitorGroupManager.queryById(Long
					.parseLong(ids));
			fieldList.add(groupObject.getMonitorGroupName() + "("
					+ groupObject.getMonitorGroupName() + ")");
			monitorGroupManager.deleteGroup(Long.parseLong(ids));

		}
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "监控组", super
				.getRequest().getRemoteAddr(), fieldList);
		// 删除角色
		/*String logString = "";
		logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型:删除监控组";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除监控组");
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/monitorGroup/showGroupTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ "'/soc/monitorGroup/showGroups.action;'" + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * <显示某个资产组下的资产> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String showAsset() {

		log.info("[MonitorGroupAction] enter showAsset....");

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

		// 只查询启用的资产

		map.put("assetStatus", 1);

		if (request.getParameter("assetGroupId") != null
				&& !request.getParameter("assetGroupId").equals("")) {

			assetGroupId = request.getParameter("assetGroupId");
			if(Integer.parseInt(assetGroupId)!=1){
				map.put("assetGroupId", Integer.parseInt(assetGroupId));
			}
			
		}
		if (keyword!=null&&!keyword.equals("")) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		
		// 高级查询
		if (StringUtil.isNotBlank(selAssetName)) {
			try {
				selAssetName = java.net.URLDecoder.decode(selAssetName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("selAssetName", selAssetName);
		}

		if (StringUtil.isNotBlank(selAssetIps)) {

			map.put("selAssetIps", selAssetIps);
		}
		//map.put("assetDeviceType", "Server服务器");
		// 得到查询结果
		sr = assetManager.query(map, page);
		
		assetList = sr.getList();
	/*	for (Asset a : assetList) {
			List<AssetGroup> list = a.getAssetGrouplistt();
			for (AssetGroup assetGroup : list) {
				//System.out.println("=============="+assetGroup.getAssetGroupName()+"===============");
			}
		}
		*/
		if (sr != null) {
			
			request.setAttribute("Page", sr.getPage());
		}

		return SUCCESS;
	}

	/**
	 * <查询某个资产的详细信息> <根据mac查看资产的详细信息>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryInfo() {

		log.info("[MonitorGroupAction] enter method queryInfo....");

		agentModelList = new ArrayList<AgentModel>();

		Cache cache = null;

		// 内存信息
		Object memory = "";

		// cpu信息
		Object cpu = "";

		// 硬盘信息
		Object disk1 = "";

		// 上行流量
		Object flowUp = "";

		// 下行流量
		Object flowDown = "";
        
		String discInfo="";
		// 判断cache内是否有 该mac地址的cache
		if (GlobalConfig.cacheManager.cacheExists(assetMac)) {

			cache = GlobalConfig.cacheManager.getCache(assetMac);

			List list = cache.getKeys();

			Map<Object, Element> map = cache.getAll(list);

			Collection<Element> l = map.values();

			Iterator it = l.iterator();

			// 内存的临时变量
			StringBuffer tempMemory = new StringBuffer();

			// 存放cpu状态的临时变量
			StringBuffer tempCpu = new StringBuffer();

			// 存放临时性的上行流量
			StringBuffer tempFlowUp = new StringBuffer();

			// 存放临时性的下行流量
			StringBuffer tempFlowDown = new StringBuffer();

			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

			tempMemory.append("[");

			tempCpu.append("[");

			tempFlowUp.append("[");

			tempFlowDown.append("[");

			AgentModel agentTemp = new AgentModel();

			while (it.hasNext()) {
				// //System.out.println(it.next());
				Element temp = (Element) it.next();

				// 判断不是预占用的cache内的数据
				if (!temp.getKey().equals("cpu_last_time")
						&& (!temp.getKey().equals("ram_last_time"))
						&& (!temp.getKey().equals("hdd_last_time"))
						&& (!temp.getKey().equals("flow_down_last_time"))
						&& (!temp.getKey().equals("flow_up_last_time"))) {
					AgentModel agent = (AgentModel) temp.getObjectValue();

					agentModelList.add(agent);
				}
			}

			Compare compare = new Compare();

			// 对cache内的数据进行按照时间先后排序
			Collections.sort(agentModelList, compare);

			// 判断list的长度是否小于6
			if (agentModelList.size() < 6) {

				int count = 0;
				for (int i = 0; i < 6 - agentModelList.size(); i++) {
					if (count == 0) {
						//tempMemory.append("[' ',0]");
						tempMemory.append("0");
						tempCpu.append("0");
						tempFlowUp.append("0");
						tempFlowDown.append("0");
					} else {
						//tempMemory.append(",[' ',0]");
						tempMemory.append(",0");
						tempCpu.append(",0");
						tempFlowUp.append(",0");
						tempFlowDown.append(",0");
					}
					count++;
				}
				for (int i = 0; i < agentModelList.size(); i++) {

					agentTemp = agentModelList.get(i);

					String date = timeFormat.format(agentTemp.getSystemTime());

					// 获得内存的占用率
					double countMemory = agentTemp.getCacheOccupy();

					// 获得cpu的占用率
					int countCpu = agentTemp.getCpuOccupy();

					// 获得上行流量
					double flow = agentTemp.getTransmitFlow();

					// 获得下行流量
					double down = agentTemp.getReceiveFlow();

					// 拼接前台用的 内存展示的图表
					//tempMemory.append(",[' '," + countMemory + "]");
					tempMemory.append(","+countMemory);

					// 拼接前台用的cpu的展示的图表
					tempCpu.append(","+ countCpu);

					// 拼接前台用的流量的展示图表
					tempFlowUp.append("," + flow);

					// 拼接前台用的流量的展示图表
					tempFlowDown.append(","+ down);
				}

			} else {
				// 用于计数
				int count = 0;

				// 获得最新的6条数据
				for (int i = agentModelList.size() - 6; i < agentModelList
						.size(); i++) {

					agentTemp = agentModelList.get(i);

					String date = timeFormat.format(agentTemp.getSystemTime());

					double countMemory = agentTemp.getCacheOccupy();

					int countCpu = agentTemp.getCpuOccupy();

					// 获得上行流量
					double flow = agentTemp.getTransmitFlow();

					// 获得下行流量
					double down = agentTemp.getReceiveFlow();

					if (count == 0) {
						//tempMemory.append("[' '," + countMemory + "]");
						tempMemory.append(countMemory);
						tempCpu.append(countCpu);

						tempFlowUp.append(flow);

						tempFlowDown.append(down);
					} else {
						//tempMemory.append(",[' '," + countMemory + "]");
						tempMemory.append(","+countMemory);
						tempCpu.append(","+ countCpu);

						tempFlowUp.append("," + flow);

						tempFlowDown.append("," + down);
					}
					count++;

				}
			}

			// 获得agent的相关信息
			if(agentModelList.size()>0){
				
				agent = agentModelList.get(agentModelList.size() - 1);

				// 获得上次的时间
				lastTime = agent.getSystemTime();
				
				// 获得磁盘信息
				
				discInfo = agent.getDiscSize();

			}else{

				discInfo = "";

			}
			
            if(StringUtil.isNotEmpty(discInfo))
            {
			StringBuffer disk = new StringBuffer();
			
			// 存放临时性的硬盘名称
			StringBuffer diskname =new StringBuffer();

			disk.append("[");
			
			diskname.append("[");

			if (discInfo.indexOf("|") != -1) {
				
				String[] str = discInfo.split("\\|");
				
				int counttemp = 0;
				try{
				for (int i = 0; i < str.length; i++) {
					
					String[] info = str[i].split("_");
                   
					int length =info.length;
					
                    String temp1 ="";
                    
                    for(int j =0;j<length-2;j++)
                    {
                    	temp1 = temp1+info[j];
                    }
					
                    int tempi  = info.length -2;
                    
                    int tempi1 =  info.length -1;
                    
					double temp2 = Double.valueOf(info[tempi]);
					double temp3 = Double.valueOf(info[tempi1]);

					double temp4 = temp2 * 100 / temp3;

					int count = (int) temp4;

					if (counttemp == 0) {
						diskname.append("'" + temp1 + "'");
						disk.append("" + count);
					} else {

						diskname.append(",'" + temp1 + "'");
						disk.append("," + count);
					}

					counttemp++;
				}
				}catch(Exception e)
				{
					
				}
			}
			
			disk.append("]");
			diskname.append("]");

            disk1 = "[" + diskname +","+ disk + "]";
			}
			
			
			tempMemory.append("]");

			tempCpu.append("]");

			tempFlowUp.append("]");

			tempFlowDown.append("]");

			memory = "" + tempMemory + "";

			cpu = "" + tempCpu + "";

			flowUp = "" + tempFlowUp + "";

			flowDown = "" + tempFlowDown + "";
		}

		List<Object> objectList = new ArrayList<Object>();

		objectList.add(memory);

		objectList.add(cpu);
        
		if(StringUtil.isNotEmpty(discInfo))
		{
		    objectList.add(disk1);
		}
		else
		{
			String temp = "[]";
			objectList.add(temp);
		}

		objectList.add(flowUp);

		objectList.add(flowDown);

		//System.out.println(""+disk1);
		
		/*HttpServletRequest request = super.getRequest();

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

		// 根据mac地址查询资产的对象

		if (AssetId != 0) {
			
			map.put("relAssetId", AssetId);
		}

		// 得到查询结果
		sr = monitorAlarmManager.query(map, page);

		if (sr != null) {
			
			monitorAlarmList = (List<MonitorAlarm>) sr.getList();

			request.setAttribute("Page", sr.getPage());
		}
*/
		jsonArray = JSONArray.fromObject(objectList);
		
		//System.out.println("===============");
		//System.out.println(disk1.toString());
		
		if((assetSupportDeviceId !=40) && (assetSupportDeviceId !=41))
		{
		    return "netInfo";
		}
		////System.out.println("aaaa");
		return SUCCESS;
	}
	
	/**
	 * 显示最近的告警列表——所有阀值告警
	 */
	public String queryMonitorAlarmForList(){
		
		queryMonitorAlarm();
		
		return SUCCESS ; 
	}
	
	/**
	 * 显示最近的告警列表
	 */
	public void queryMonitorAlarm()
	{
		HttpServletRequest request = super.getRequest();

		Page page = null;
		
		SearchResult sr = null;
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		
		String keyWord = request.getParameter("keyword");
		
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		// 根据mac地址查询资产的对象
		if(StringUtil.isNotBlank(keyWord))
		{
			try {
				keyword = java.net.URLDecoder.decode(keyWord, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword",keyword);
		}
		
		if(AssetId != null){
			if (AssetId != 0) {
				
				map.put("relAssetId", AssetId);
			}

		}
		// 得到查询结果
		sr = monitorAlarmManager.query(map, page);

		if (sr != null) {
			
			monitorAlarmList = (List<MonitorAlarm>) sr.getList();
			
			for(int i =0;i<monitorAlarmList.size();i++)
			{
				Date tempDate =monitorAlarmList.get(i).getMonitorAlarmTime();
				String temp = DateUtil.formatDate(tempDate, "yyyy-MM-dd HH:mm:ss");
				monitorAlarmList.get(i).setMonitorAlarmTimes(temp);
			}
			
			JSONArray jsonArray = JSONArray.fromObject(monitorAlarmList);

			jsonArray.add(page);
			request.setAttribute("Page", page);

			// Ajax返回
			try {
				getResponse().getWriter().write(jsonArray.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//request.setAttribute("Page", sr.getPage());
		}
		
	}
	/**
	 * <获得软件信息> <得到最新的数据并且传递到前台页面>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String softMsg() {
		////System.out.println(assetSupportDeviceId);
		log.info("[MonitorGroupAction] enter method softMsg....");
		Cache cache = null;
		assetMac = assetMac + "soft";// 用来区分 跟angint 行再写注释
		if (GlobalConfig.cacheManager.cacheExists(assetMac)) {

			cache = GlobalConfig.cacheManager.getCache(assetMac);

			List list = cache.getKeys();

			Map<Object, Element> map = cache.getAll(list);

			Collection<Element> l = map.values();

			Iterator it = l.iterator();

			AgentModel agentTemp = new AgentModel();
			Element temp;
			this.softMsgs = new ArrayList<WinSoftMsg>();
			while (it.hasNext()) {

				temp = (Element) it.next();	
				WinSoftMsg msg = (WinSoftMsg) temp.getObjectValue();
					softMsgs.add(msg);
		}
		}
			return "soft";
	}
	/**
	 * <获得服务信息> <得到最新的数据并且传递到前台页面>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String serviceMsg() {
		////System.out.println(assetSupportDeviceId);
		log.info("[MonitorGroupAction] enter method serviceMsg....");
		Cache cache = null;
		assetMac = assetMac + "service";// 用来区分 跟angint 行再写注释
		if (GlobalConfig.cacheManager.cacheExists(assetMac)) {

			cache = GlobalConfig.cacheManager.getCache(assetMac);

			List list = cache.getKeys();

			Map<Object, Element> map = cache.getAll(list);

			Collection<Element> l = map.values();

			Iterator it = l.iterator();

			AgentModel agentTemp = new AgentModel();
			Element temp;
			linuxServiceMsgs = new ArrayList<LinuxServiceMsg>();
			winServiceMsgs = new ArrayList<WinServiceMsg>();
			while (it.hasNext()) {

				temp = (Element) it.next();
				if (assetSupportDeviceId == 40) {
					LinuxServiceMsg msg = (LinuxServiceMsg) temp
							.getObjectValue();
					linuxServiceMsgs.add(msg);
				} else {
					WinServiceMsg msg = (WinServiceMsg) temp.getObjectValue();
					winServiceMsgs.add(msg);
				}

			}

		}
		if (assetSupportDeviceId == 40) {
			return "linux";
		} else {
			return "win";
		}

	}

	/**
	 * <获得进程信息> <得到最新的数据并且传递到前台页面>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String progressMsg() {
		log.info("[MonitorGroupAction] enter method progressMsg....");
		Cache cache = null;
		assetMac = assetMac + "progress";// 用来区分 跟angint 行再写注释
		if (GlobalConfig.cacheManager.cacheExists(assetMac)) {

			cache = GlobalConfig.cacheManager.getCache(assetMac);

			List list = cache.getKeys();

			Map<Object, Element> map = cache.getAll(list);

			Collection<Element> l = map.values();

			Iterator it = l.iterator();

			AgentModel agentTemp = new AgentModel();
			Element temp;
			winProgressMsgs = new ArrayList<WinProgressMsg>();
			LinuxProgressMsgs = new ArrayList<LinuxProgressMsg>();
			while (it.hasNext()) {
				temp = (Element) it.next();
				if (assetSupportDeviceId == 40) {				
					LinuxProgressMsg msg = (LinuxProgressMsg) temp.getObjectValue();
					LinuxProgressMsgs.add(msg);
					//System.out.println(msg);
				}else {
					WinProgressMsg msg = (WinProgressMsg) temp.getObjectValue();
					winProgressMsgs.add(msg);
					
				}

			}

		}
		if (assetSupportDeviceId == 40) {
			return "linux";
		} else {
			return "win";
		}
	}

	/**
	 * <获得最新的数据> <得到最新的数据并且传递到前台页面>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void queryDynamic() {

		log.info("[MonitorGroupAction] enter method queryDynamic....");

		Cache cache = null;

		// 标识是否有最新数据
		int flag = 0;

		Object memory = "";
		Object cpu = "";
		Object disk1 = "";

		// 上行流量
		Object flowUp = "";

		// 下行流量
		Object flowDown = "";

		JSONArray json = new JSONArray();
		
		if (GlobalConfig.cacheManager.cacheExists(assetMac)) {

			cache = GlobalConfig.cacheManager.getCache(assetMac);

			List list = cache.getKeys();

			Map<Object, Element> map = cache.getAll(list);

			Collection<Element> l = map.values();

			Iterator it = l.iterator();
			// 内存的临时变量
			StringBuffer tempMemory = new StringBuffer();

			// 存放cpu状态的临时变量
			StringBuffer tempCpu = new StringBuffer();

			// 存放临时性的上行流量
			StringBuffer tempFlowUp = new StringBuffer();

			// 存放临时性的下行流量
			StringBuffer tempFlowDown = new StringBuffer();

			// 存放临时性的硬盘使用率
			StringBuffer disk = new StringBuffer();
			
			// 存放临时性的硬盘名称
			StringBuffer diskname =new StringBuffer();

			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

			// 获得当前时间的毫秒数
			// long dataTime = System.currentTimeMillis()-6000;

			List<Object> objectList = new ArrayList<Object>();
			
			while (it.hasNext()) {
				Element temp = (Element) it.next();

				// 判断不是预占用的cache内的数据
				if (!temp.getKey().equals("cpu_last_time")
						&& (!temp.getKey().equals("ram_last_time"))
						&& (!temp.getKey().equals("hdd_last_time"))
						&& (!temp.getKey().equals("flow_down_last_time"))
						&& (!temp.getKey().equals("flow_up_last_time"))) {
					AgentModel agent1 = (AgentModel) temp.getObjectValue();
					if (agent1.getSystemTime() > lastTime) {

						agent = agent1;

						lastTime = agent.getSystemTime();

						flag = 1;

						String date = timeFormat.format(agent.getSystemTime());

						double memoryCount = agent.getCacheOccupy();

						double cpuCount = agent.getCpuOccupy();

						double Down = agent.getReceiveFlow();

						double Up = agent.getTransmitFlow();

						tempMemory.append("["+memoryCount+"]");
                        
						tempCpu.append("["+ cpuCount+"]");

						tempFlowUp.append("["+ Up +"]");

						tempFlowDown.append("["+ Down +"]");

						String discInfo = agent.getDiscSize();

						if(StringUtil.isNotEmpty(discInfo)){
							
							if (discInfo.indexOf("|") != -1) {

								String[] str = discInfo.split("\\|");
						
								int counttemp = 0;

								for (int i = 0; i < str.length; i++) {

									String[] info = str[i].split("_");

									String temp1 = info[0];

									double temp2 = Double.valueOf(info[1]);

									double temp3 = Double.valueOf(info[2]);

									double temp4 = temp2 * 100 / temp3;

									int count = (int) temp4;

									if (counttemp == 0) {

										disk.append("[" + count);
										
										diskname.append("['" + temp1 + "'");
									} else {

										disk.append("," + count);
										
										diskname.append(",'" + temp1 + "'");
									}

									counttemp++;
								}
							}

							disk.append("]");
							
							diskname.append("]");

							disk1 = "[" + diskname+ "," + disk + "]";
						}
						

						memory = "" + tempMemory + "";

						cpu = "" + tempCpu + "";

						flowUp = "" + tempFlowUp + "";

						flowDown = "" + tempFlowDown + "";

						objectList.add(memory);

						objectList.add(cpu);
						if(StringUtil.isNotEmpty(discInfo)){
							objectList.add(disk1);
						}
						else
						{
							String temp1 ="[]";
							objectList.add(temp1);
							
						}
						

						objectList.add(flowUp);

						objectList.add(flowDown);

						// 系统的最新运行时间
						objectList.add(agent.getRuntime());
						
						objectList.add(agent.getVersionnumber());
						
						objectList.add(agent.getOperatingSystem());
						
						objectList.add(agent.getCpuType());
						
						objectList.add(agent.getMemoryTotal());
						
						objectList.add(agent.getAgentInstallPath());
						
						objectList.add(agent.getIp());
						
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						StringBuffer uptime=new StringBuffer();
						uptime.append(sdf.format(agent.getUptime()));
						objectList.add(uptime.toString());

						//如果接到新的信息就重置time的值，为后面判断采集器是否在线做准备
						if(lastTime>mtime){
							
							//System.out.println(mtime);
							
							mtime=lastTime;
							
							time=new Date().getTime();
							
						}
						
						break;

					}

				}

			}
			//判断是否在time的5分钟之后，如果是证明不在线，反之为在线
			String msg="";
			
			if(time!=null&&new Date().getTime()>time+300000){
				
				msg="不在线";
				
			}else{
				msg="在线";
			}
			
			objectList.add(msg);
			
			json = JSONArray.fromObject(objectList);
			
			try {
				
				//System.out.println("生成新的apm信息------");
				
				getResponse().getWriter().write(json.toString());
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}

//		if (flag == 0) {
//
//			Object temp = "[['1',1]]";
//
//			json = JSONArray.fromObject(temp);
//
//			try {
//
//				getResponse().getWriter().write(json.toString());
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public MonitorGroupService getMonitorGroupManager() {
		return monitorGroupManager;
	}

	public void setMonitorGroupManager(MonitorGroupService monitorGroupManager) {
		this.monitorGroupManager = monitorGroupManager;
	}

	public MonitorGroup getMonitorGroup() {
		return monitorGroup;
	}

	public void setMonitorGroup(MonitorGroup monitorGroup) {
		this.monitorGroup = monitorGroup;
	}

	public List<MonitorGroup> getMonitorGroupList() {
		return monitorGroupList;
	}

	public void setMonitorGroupList(List<MonitorGroup> monitorGroupList) {
		this.monitorGroupList = monitorGroupList;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupMemo() {
		return groupMemo;
	}

	public void setGroupMemo(String groupMemo) {
		this.groupMemo = groupMemo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAssetTreeBuffer() {
		return assetTreeBuffer;
	}

	public void setAssetTreeBuffer(String assetTreeBuffer) {
		this.assetTreeBuffer = assetTreeBuffer;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public String getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(String assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public String getAssetMac() {
		return assetMac;
	}

	public void setAssetMac(String assetMac) {
		this.assetMac = assetMac;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public List<AgentModel> getAgentModelList() {
		return agentModelList;
	}

	public void setAgentModelList(List<AgentModel> agentModelList) {
		this.agentModelList = agentModelList;
	}

	public AgentModel getAgent() {
		return agent;
	}

	public void setAgent(AgentModel agent) {
		this.agent = agent;
	}

	public MonitorAlarmService getMonitorAlarmManager() {
		return monitorAlarmManager;
	}

	public void setMonitorAlarmManager(MonitorAlarmService monitorAlarmManager) {
		this.monitorAlarmManager = monitorAlarmManager;
	}

	public List<MonitorAlarm> getMonitorAlarmList() {
		return monitorAlarmList;
	}

	public void setMonitorAlarmList(List<MonitorAlarm> monitorAlarmList) {
		this.monitorAlarmList = monitorAlarmList;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Long getAssetId() {
		return AssetId;
	}

	public void setAssetId(Long assetId) {
		AssetId = assetId;
	}

	public List<WinServiceMsg> getWinServiceMsgs() {
		return winServiceMsgs;
	}

	public void setWinServiceMsgs(List<WinServiceMsg> winServiceMsgs) {
		this.winServiceMsgs = winServiceMsgs;
	}

	public List<LinuxServiceMsg> getLinuxServiceMsgs() {
		return linuxServiceMsgs;
	}

	public void setLinuxServiceMsgs(List<LinuxServiceMsg> linuxServiceMsgs) {
		this.linuxServiceMsgs = linuxServiceMsgs;
	}

	

	public int getAssetSupportDeviceId() {
		return assetSupportDeviceId;
	}

	public void setAssetSupportDeviceId(int assetSupportDeviceId) {
		this.assetSupportDeviceId = assetSupportDeviceId;
	}

	public List<WinProgressMsg> getWinProgressMsgs() {
		return winProgressMsgs;
	}

	public void setWinProgressMsgs(List<WinProgressMsg> winProgressMsgs) {
		this.winProgressMsgs = winProgressMsgs;
	}

	public List<LinuxProgressMsg> getLinuxProgressMsgs() {
		return LinuxProgressMsgs;
	}

	public void setLinuxProgressMsgs(List<LinuxProgressMsg> linuxProgressMsgs) {
		LinuxProgressMsgs = linuxProgressMsgs;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSelAssetName() {
		return selAssetName;
	}

	public void setSelAssetName(String selAssetName) {
		this.selAssetName = selAssetName;
	}

	public String getSelAssetIps() {
		return selAssetIps;
	}

	public void setSelAssetIps(String selAssetIps) {
		this.selAssetIps = selAssetIps;
	}

	public String getCustomBuffer() {
		return customBuffer;
	}

	public void setCustomBuffer(String customBuffer) {
		this.customBuffer = customBuffer;
	}

	public CustomTrendService getCustomTrendManager() {
		return customTrendManager;
	}

	public void setCustomTrendManager(CustomTrendService customTrendManager) {
		this.customTrendManager = customTrendManager;
	}

	public List<WinSoftMsg> getSoftMsgs() {
		return softMsgs;
	}

	public void setSoftMsgs(List<WinSoftMsg> softMsgs) {
		this.softMsgs = softMsgs;
	}

}