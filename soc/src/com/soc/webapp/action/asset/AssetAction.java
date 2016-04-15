/*
 * 文 件 名:  AssetAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.action.asset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.asset.AutoToPo;
import com.soc.model.asset.HistoryAsset;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.serial.Serial;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.Setting;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.asset.export.ExcelAsset;
import com.soc.service.asset.importAsset.ImportAsset;
import com.soc.service.asset.system.AssetSystemService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.rules.AnalysisRulesService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.topo.model.device.Device;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.service.device.DeviceManageService;
import com.topo.service.deviceCategory.DeviceCategoryManageService;
import com.topo.util.Ping;
import com.util.AutoToPoUtils;
import com.util.CheckBox;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.IpConvert;
import com.util.MyException;
import com.util.Span;
import com.util.StringUtil;
import com.util.linkMethod.SSHMode;
import com.util.linkMethod.TelnetMode;
import com.util.page.Page;
import com.util.page.SearchResult;
import com.util.topoSearch.TopoSearch;
import com.util.treeview.AssetGroupTree;

/**
 * 资产管理Action 实现资产增加，删除，查询，快速查询，高级查询
 * 
 * @author liuyanxu
 * @version [V100R001C001, 2012-8-22]
 * @see com.soc.service.asset.AssetService
 * @since [任务管理/资产管理/V100R001C001]
 */
public class AssetAction extends BaseAction {
	private String fileName;

	private String ips;
	// 资产的业务处理类
	private AssetService assetManager;

	// 资产组业务处理类
	private AssetGroupService assetGroupManager;

	// 审计业务管理类
	private AuditService auditManager;

	// 采集器实体类
	private Collector collector;

	// 资产列表
	private List<Asset> assetList;

	// 历史资产列表
	private List<HistoryAsset> historyAssetList;
	// 用户资产组列表
	private List<AssetGroup> assetGroupList;
	// 资产实体类
	private Asset asset;

	private String info2;

	private String keyword;

	// 高级查询资产名
	private String selAssetName;

	// 高级查询资产Ip
	private String selAssetIps;

	// 高级查询资产重要性
	private String selAssetImportance;

	// 页面获得的 ids
	private String ids;

	//
	private String htmlBuffer;

	public String getSelAssetCollector() {
		return selAssetCollector;
	}

	public void setSelAssetCollector(String selAssetCollector) {
		this.selAssetCollector = selAssetCollector;
	}

	// 资产id
	private int assetId;

	// 资产名称
	private String assetName;

	// 资产组Id
	private String assetGroupId;

	private String groupId;

	// 资产组Id串
	private String assetGroupIds;

	// 历史资产id
	private int historyAssetId;

	// 数据
	private String data;

	// 采集器
	private List<Collector> collectorList;

	// 采集器业务管理类
	private SettingCollectorService collectorManager;

	private String collectorIds;

	private String collectorId;

	private long assetSegMent;

	private long assetSeg;

	private String colId;

	// 资产遍历之后存入的list
	private String assetListStr;

	// 设备种类名称
	private List<Map> categoryName;

	// 设备子种类
	private List<Map> categorys;

	// 设备父Id
	private long upsId;

	private long categoryF;

	private long categoryB;

	private long bigCateId;

	private long smallCateId;

	private long snmp;

	private long syslog;

	private String disAll;

	private String fireAll;

	private List directoriseList;

	private List fireList;

	private String commandAll;

	private List commandList;

	private String deviceType;

	private String supportDevice;

	// mac地址
	private String mac;

	// private long collectorId;
	private String uName;

	private String deviceTypeId;

	private String supportDeviceId;

	// 资产的状态
	private int status;

	// 上传的文件
	private File upTar;

	// 文件名称
	private String upTarFileName;

	// 资产的可用性价值
	private int use;

	// 请求的action字符串
	private String actionStr = "queryAsset.action";

	// 排序Type
	private String sortType;

	// 要查询的字段
	private String field = "ASSET_UPDATE_DATE_TIME";

	// 返回的结果字符串
	private String info;

	// 用户的集合
	private List<User> userlist;

	// 用户管理类
	private UserService userManager;

	// 解析规则类
	private AnalysisRulesService analysisManager;

	// 解析规则List
	private List<AnalysisRules> rulesList;
	// 网络拓扑模块界面跳到这个actio按照资产名字查询告警 但是返回的界面中有快速搜索栏 用这个变量判断是不是拓扑模块过来的
	// 1 表示是拓扑界面过来的
	private int isMonitorNetworkTopology;
	private int errorMessageflg;// 是否有错误信息 因为诸多原因 当errorMessageflg是1的时候表示有错误

	private int securityPort;// 端口号
	private String securityUserName; // 用户名
	private String securityPWD; // 密码
	private String securityLinkeThod; // 连接方式

	private String actionMessage;
	
	//高级查询资产采集器id
	private String selAssetCollector;
	
	
	
	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public String getTopoStatus() {
		return topoStatus;
	}

	public void setTopoStatus(String topoStatus) {
		this.topoStatus = topoStatus;
	}

	//系统设置类
	private SettingService settingManager;
	
	//显示拓扑自动发现的字段
	private String topoStatus;
	

	public int getErrorMessageflg() {
		return errorMessageflg;
	}

	public void setErrorMessageflg(int errorMessageflg) {
		this.errorMessageflg = errorMessageflg;
	}

	public int getUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = use;
	}

	public List<AnalysisRules> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<AnalysisRules> rulesList) {
		this.rulesList = rulesList;
	}

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public AnalysisRulesService getAnalysisManager() {
		return analysisManager;
	}

	public void setAnalysisManager(AnalysisRulesService analysisManager) {
		this.analysisManager = analysisManager;
	}

	// 资产的保密性价值
	private int secret;

	// 资产的完整性价值
	private int complete;

	// 资产系统管理类
	private AssetSystemService asManager;
	// 资产系统集合
	private List<AssetSystem> assetsystemlist;
	private List<AssetSystem> assetsystembrandlist;
	private int assetSystemId;

	private String assetSystemName; // 系统、版本、版本号
	private String assetSystemComment; // 系统备注
	private String systemOptions[]; // 系统品牌集合
	private String versionOptions[]; // 系统版本
	private String versionNoOptions[]; // 系统版本号集合

	private ImportAsset importAsset;

	private List<AssetSystem> SystemVersionList;

	private List<AssetSystem> SystemVersionNoList;

	private List<AssetSystem> SystemBrandList;

	// 添加设备信息用到的（开始）
	private DeviceCategoryManageService deviceCategoryService;
	private DeviceManageService deviceManageService;
	long deviceMarkID;
	long deviceCategoryId;
	// String deviceCategoryName;
	// 用来保存用户选择的关联设备
	private List<Device> saveDeviceList;
	// 设备类型集合
	private List<DeviceCategory> deviceCategoryList;
	private Device device;
	// 当设备属于外网时，用来保存用户输入的登陆名称
	private String netOutLoginName;
	// SNMP社区名称
	private String snmpCommunityName;

	// 结束=============

	/**
	 * 资产的查询 实现资产的高级查询，快速查询，全部查询
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetService#query(Map, Page)
	 */
	public String query() {

		log.info("[AssetAction] Enter method query()...");

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
			try {
				selAssetName = java.net.URLDecoder
						.decode(selAssetName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("selAssetName", selAssetName);
		}

		if (StringUtil.isNotBlank(selAssetIps)) {

			map.put("selAssetIps", selAssetIps);
		}
		if(StringUtil.isNotBlank(selAssetCollector)){
			map.put("selAssetCollector", Long.parseLong(selAssetCollector.trim()));
		}

		long groupId = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();

		StringBuffer sb = new StringBuffer();
		rulesList = analysisManager.queryAnalysis();
		if (assetGroupId != null && !assetGroupId.equals("")) {

			if (Integer.parseInt(assetGroupId) != 1) {
				String str = assetGroupManager.getAllGroupIdByID(Integer
						.parseInt(assetGroupId));
				map.put("assetGroupId", str);
				// map.put("assetGroupId", Integer.parseInt(assetGroupId));
			}
		} else {
			sb.append(groupId);
			assetGroupId = sb.toString();
			if (groupId != 1) {
				String str = assetGroupManager.getAllGroupIdByID(groupId);
				map.put("assetGroupId", str);
			}
			/*
			 * 
			 * if(groupId!=1){ map.put("assetGroupId", groupId); }else{
			 * 
			 * }
			 */
		}

		// 查看角色关联的资产
		/*
		 * Role role = (Role)
		 * request.getSession().getAttribute("SOC_LOGON_ROLE");
		 * 
		 * if (StringUtil.isNotBlank(role.getAssetIds())) { if
		 * (!role.getAssetIds().equals("0")) { map.put("roleAssetIds",
		 * role.getAssetIds()); } }
		 * 
		 * // 查看角色关联的资产组 if (StringUtil.isNotBlank(role.getAssetGroupIds())) {
		 * if (!role.getAssetGroupIds().equals("-1")) {
		 * map.put("roleAssetGroupIds", role.getAssetGroupIds()); } }
		 */

		if (StringUtil.isNotBlank(collectorId)) {

			map.put("collectorId", Long.parseLong

			(collectorId.trim()));
		}
		if (StringUtil.isNotBlank(collectorId)) {

			map.put("collectorId", Long.parseLong(collectorId.trim()));
		}
		if (assetSegMent != 0) {
			// assetList = assetManager.queryAssetByIp(assetSegMent);
			map.put("assetSegMent", assetSegMent);
		}

		if (StringUtil.isNotEmpty(sortType)) {
			map.put("sortType", sortType);
		}

		// 得到查询结果
		sr = assetManager.query(map, page);

		assetList = sr.getList();
		//查看资产时间与资产在线最后记录时间差，判断资产是否已下线
				Date dateNow=new Date();
				for(Asset asset:assetList){
					if(GlobalConfig.AssetTimeNote.get(asset.getAssetIps())!=null&&(dateNow.getTime()-GlobalConfig.AssetTimeNote.get(asset.getAssetIps()).getTime())<900000){
						asset.setIsOnLine(true);
						}
					else
						asset.setIsOnLine(false);	
					}

		List<AssetGroup> alist = null;

		if (assetList != null && assetList.size() > 0) {
			Asset aset = assetList.get(0);
			alist = aset.getAssetGrouplistt();
			info2 = "have";
			/*
			 * for (AssetGroup assetGroup : alist) {
			 * //System.out.println(assetGroup.getAssetGroupName());; }
			 */

		}

		if (sr != null) {
			request.setAttribute("Page", sr.getPage());

		}

		return SUCCESS;

	}

	public void queryAjax() {
		LOG.info("[AssetAction] enter method queryAjax() ...");

		JSONArray jsonArray = null;
		List<Asset> assetList = new ArrayList<Asset>();

		String type = getRequest().getParameter("type");

		if (StringUtil.isNotEmpty(type)) {
			if (StringUtil.equals(type, "noSearch")) {
				if (StringUtil.isNotEmpty(assetGroupIds)) {
					String[] ids = assetGroupIds.split(",");
					for (String id : ids) {
						List<Asset> list = new ArrayList<Asset>();
						boolean flag = false;
						if (StringUtil.isNotEmpty(id)) {
							list = assetManager.queryAssetByAssetGroupId(Long
									.parseLong(id));
							if (list.size() > 0) {
								flag = true;
							}
						}

						if (flag) {
							assetList.addAll(list);
						}
					}
				}
			} else {

				Map<String, Object> map = new HashMap<String, Object>();
				// 模糊查询
				if (StringUtil.isNotEmpty(keyword)) {
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

				if (StringUtil.isNotBlank(selAssetImportance)) {
					map.put("selAssetImportance", selAssetImportance);
				}
				if (StringUtil.isNotBlank(selAssetCollector)) {
					map.put("selAssetCollector", Long.parseLong(selAssetCollector.trim()));
				}
				assetList = assetManager.query(map);
			}
		}

		// 转换为JSON数据结构
		jsonArray = JSONArray.fromObject(assetList);

		// Ajax返回
		try {
			if (jsonArray != null) {
				log.info(jsonArray.toString());

				getResponse().getWriter().write(jsonArray.toString());
			} else {
				getResponse().getWriter().write("");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除功能 标记删除资产
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetService#deleteAsset(long)
	 */
	public String deleteAsset() {
		log.info("[AssetAction] Enter deleteAsset...");
		List<String> fieldList = new ArrayList<String>();

		colId = collectorId;
		assetSeg = assetSegMent;
		groupId = assetGroupId;
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String check : checked) {
					Asset assetObject = assetManager.searchById(Long
							.parseLong(check));
					if (assetObject == null) {
						return "errorMessage";
					} else {
						fieldList.add(assetObject.getAssetName() + "("
								+ assetObject.getAssetName() + ")");
						assetManager.deleteAsset(Long.parseLong(check));
					}
				}
			} else {
				Asset assetObject = assetManager
						.searchById(Long.parseLong(ids));
				if (assetObject == null) {
					return "errorMessage";
				} else {
					fieldList.add(assetObject.getAssetName() + "("
							+ assetObject.getAssetName() + ")");
					// assetManager.deleteRelCollector(Long.parseLong(ids));
					assetManager.deleteAsset(Long.parseLong(ids));
				}
			}
		}

		// 删除设备信息
		if (StringUtil.isNotBlank(ids)) {
			String[] strs = ids.split(",");
			int[] num = new int[strs.length];
			for (int i = 0; i < num.length; i++) {
				if (StringUtil.isNotBlank(strs[i])) {
					num[i] = Integer.parseInt(strs[i]);
				}
			}
			deviceManageService.deleteDeviceByAssetIds(num);
		}

		// 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "资产", super
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
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除资产");

		// 刷新父页面
		try {

			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/assetGroup/showGroupTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ " '/soc/asset/queryAsset.action?ids="
					+ ids
					+ "&assetSegMent="
					+ assetSegMent
					+ "&collectorId="
					+ collectorId
					+ "&assetGroupId="
					+ assetGroupId
					+ "';"
					+ "</script>";

			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * <更新资产状态> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateStatus() {
		log.info("[AssetAction] Enter updateStatus...");

		List<String> fieldList = new ArrayList<String>();

		Map<String, Object> map = new HashMap<String, Object>();

		String statuStr = "";

		if (status == 0) {
			statuStr = "停用资产";
		} else {
			statuStr = "启用资产";
		}

		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				for (String check : checked) {
					Asset assetObject = assetManager.searchById(Long
							.parseLong(check));
					fieldList.add(assetObject.getAssetName() + "("
							+ assetObject.getAssetName() + ")");
					map.put("assetStatus", status);
					map.put("assetId", Long.parseLong(check));
					assetManager.updateStatus(map);
					map.clear();
				}
			} else {
				map.put("assetStatus", status);
				map.put("assetId", Long.parseLong(ids));
				assetManager.updateStatus(map);
				Asset assetObject = assetManager
						.searchById(Long.parseLong(ids));
				fieldList.add(assetObject.getAssetName() + "("
						+ assetObject.getAssetName() + ")");
			}
		}

		// 审计日志
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), statuStr, super
				.getRequest().getRemoteAddr(), fieldList);

		// syslog
		/*String logString = "";

		logString = "登录名:"
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + " 源IP:"
				+ getRequest().getRemoteAddr() + " 操作时间:"
				+ DateUtil.curDateTimeStr19() + " 操作类型 :更改资产状态";
		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改资产状态");
		return SUCCESS;
	}

	/**
	 * 资产信息的查看 进入编辑查看
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetService#searchById(long)
	 */
	public String editAsset() {
		log.info("[AssetAction] enter editAssetAction..");
		long groupId = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		assetGroupList = new ArrayList<AssetGroup>();

		// 添加设备用到的begin===
		try {
			deviceCategoryList = deviceCategoryService.queryAllDeviceCategory();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 添加设备用到的end===
		// 查询资产对应的资产组Id
		if (assetId != 0) {
			Map map = new HashMap();
			map.put("assetId", assetId);
			assetGroupList = assetGroupManager.queryGroupByAssetId(map);
		}

		if (assetGroupList.isEmpty()) {
			AssetGroup group = assetGroupManager.queryById(groupId);
			assetGroupList.add(group);
		}

		try {
			AssetGroupTree assetGroupTree = new AssetGroupTree(
					assetGroupManager, super.getRequest().getContextPath());
			Span span = new Span();
			span.setServerVar("assetGroupId");
			span.setServerTextVar("assetGroupName");
			span.setActionParam("assetGroupId");
			assetGroupTree.setSpan(span);

			/*
			 * Radio radio = new Radio(); radio.setVarName("assetGroupId");
			 * radio.setServerVar("assetGroupId");
			 */
			CheckBox checkBox = new CheckBox();
			checkBox.setVarName("assetGroupId");
			checkBox.setServerVar("assetGroupId");
			assetGroupTree.setCheckBox(checkBox);

			// 李长红修改20140529
			this.setHtmlBuffer(assetGroupTree.displayTree(groupId));

		} catch (UnsupportedEncodingException e) {
			log.error("[assetGroupAction] unsupport encoding exception.", e);
		} catch (IOException e) {
			log.error("[assetGroupAction] io excepion.", e);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		// 查询当前选定的资产
		if (assetId != 0) {
			asset = assetManager.searchById(assetId);
			String[] discAll = asset.getDirectorise().split(",");
			String[] firecAll = asset.getFile().split(",");
			directoriseList = Arrays.asList(discAll);
			fireList = Arrays.asList(firecAll);
			String[] commandAll = asset.getAssetPrivilegeCommand().split(",");
			commandList = Arrays.asList(commandAll);
			// 判断是否为代理类的
			if (asset.getAssetUnName() == 1) {
				categoryName = assetManager.queryCategoryService();

				categorys = assetManager.queryCategory(39);

			} else {
				// 查询所有的父级设备
				categoryName = assetManager.queryCategory();

				// 查询所有的设备
				categorys = assetManager.queryCategorys();

			}
			// userlist = userManager.queryUserListByAsset(assetId);
			userlist = userManager.query(null);
			SystemVersionList = asManager.queryVersionList(1,
					asset.getAsset_system_id());
			SystemVersionNoList = asManager.queryVersionList(2,
					asset.getAsset_system_id());
			SystemBrandList = asManager.queryVersionList(3,
					asset.getAsset_system_id());

			// 设备编辑begin
			try {
				device = new Device();
				this.device = deviceManageService.queryDeviceByAssetId(assetId);
				if (device != null) {
					// deviceCategoryName=this.device.getDevice_deviceCategory_name();
					deviceCategoryId = this.device.getDeviceCategory()
							.getDeviceCategory_id();
					// deviceCategoryName=this.device.getDeviceCategory().getDeviceCategory_name();
					deviceMarkID = this.device.getDevice_mark();
					netOutLoginName = this.device.getDevice_loginName();
					snmpCommunityName = this.device.getDevice_community_name();
					if (StringUtil
							.isNotBlank(device.getDevice_association_id())) {
						saveDeviceList = new ArrayList<Device>();
						String[] strIds = device.getDevice_association_id()
								.split(",");
						if (strIds.length != 0) {
							for (String strDeviceId : strIds) {
								this.saveDeviceList.add(deviceManageService
										.queryDeviceById(Long
												.parseLong(strDeviceId)));
							}
						}
					} else {
						saveDeviceList = new ArrayList<Device>();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 设备编辑end
		}

		else {
			// asset.setAssetUnName(4);
			// 查询所有的父级设备
			categoryName = assetManager.queryCategory();

			// 查询所有的设备
			categorys = assetManager.queryCategorys();

			userlist = userManager.query(null);
			map.put("id", 0);
			map.put("level", 0);

			// 设备编辑begin
			device = new Device();
			// deviceCategoryName="";
			deviceCategoryId = 0;
			deviceMarkID = 0;
			saveDeviceList = new ArrayList<Device>();
			netOutLoginName = "";
			snmpCommunityName = "";
			// 设备编辑begin

		}
		// 查看所有的采集器List
		collectorList = collectorManager.queryCollector();
		assetsystemlist = asManager.queryAssetSystem(map);

		return SUCCESS;
	}

	public void queryCategory() {

		/**
		 * 控制下拉单
		 */
		if (upsId != 0) {
			categorys = assetManager.queryCategory(upsId);
			// Gson json = new Gson();
			JSONArray jsonarray = new JSONArray();
			// data = json.toJson(categorys);
			jsonarray = JSONArray.fromObject(categorys);
			HttpServletResponse respons = super.getResponse();
			try {
				respons.getWriter().write(jsonarray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 控制下拉单
		 */
		if (snmp != 0) {
			categoryName = assetManager.queryCategory();
			categorys = assetManager.queryCategorys();
			JSONArray jsonarray = new JSONArray();
			jsonarray = JSONArray.fromObject(categoryName);
			HttpServletResponse respons = super.getResponse();
			try {
				respons.getWriter().write(jsonarray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 控制下拉单
		 */
		if (syslog != 0) {
			categoryName = assetManager.queryCategory();
			categorys = assetManager.queryCategorys();
			JSONArray jsonarray = new JSONArray();
			jsonarray = JSONArray.fromObject(categoryName);
			HttpServletResponse respons = super.getResponse();
			try {
				respons.getWriter().write(jsonarray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 控制下拉单
		 */
		if (bigCateId != 0) {

			categorys = assetManager.queryCategoryService();
			JSONArray jsonarray = new JSONArray();
			jsonarray = JSONArray.fromObject(categorys);
			HttpServletResponse respons = super.getResponse();
			try {
				respons.getWriter().write(jsonarray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 控制下拉单
		 */
		if (smallCateId != 0) {
			categorys = assetManager.queryCategory(smallCateId);
			JSONArray jsonarray = new JSONArray();
			jsonarray = JSONArray.fromObject(categorys);
			HttpServletResponse respons = super.getResponse();
			try {
				respons.getWriter().write(jsonarray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新资产 资产的修改，添加
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetService#updateAsset(Asset)
	 */
	public void updateAsset() {

		log.info("[AssetAction] enter updateAsset....");

		List<String> fieldList = new ArrayList<String>();

		fieldList.add(asset.getAssetName() + "(" + asset.getAssetName() + ")");

		// 修改开始（=======================pxm）
		// device=new Device();
		if (asset.getAssetId() == 0) {
			device = new Device();
			device.setDevice_locationX("147.15");
			device.setDevice_locationY("52.9");
			Ping ping = new Ping(asset.getAssetIps());
			String result = ping.pingIP();
			if (StringUtil.isNotBlank(result)) {
				device.setDevice_status(1);
			} else {
				device.setDevice_status(0);
			}
		} else {
			this.device = deviceManageService.queryDeviceByAssetId(asset
					.getAssetId());
		}
		HttpServletRequest request = super.getRequest();
		device.setDevice_mac(asset.getAssetMac());
		String strDeviceCategoryId = request.getParameter("deviceCategoryId");
		String deviceAssociation = request.getParameter("deviceIds");
		String deviceMarkid = request.getParameter("deviceMarkId");
		String deviceLoginName = request.getParameter("netOutLoginName");
		String snmpCommunityName = request.getParameter("snmpCommunityName");
		if (StringUtil.isNotBlank(deviceAssociation)) {
			String deviceIds = deviceAssociation.substring(0,
					deviceAssociation.length() - 1);
			device.setDevice_association_id(deviceIds);
		} else {
			device.setDevice_association_id(deviceAssociation);
		}
		DeviceCategory deviceCategory = new DeviceCategory();
		deviceCategory
				.setDeviceCategory_id(Long.parseLong(strDeviceCategoryId));
		device.setDeviceCategory(deviceCategory);
		device.setDevice_ip(asset.getAssetIps());
		device.setDevice_name(asset.getAssetName());
		device.setDevice_describe(asset.getAssetMemo());
		if (StringUtil.isNotBlank(deviceMarkid)) {
			device.setDevice_mark(Long.parseLong(deviceMarkid));
		}
		if (StringUtil.isNotBlank(deviceLoginName)) {
			device.setDevice_loginName(deviceLoginName);
		}
		if (StringUtil.isNotBlank(snmpCommunityName)) {
			device.setDevice_community_name(snmpCommunityName);
		}

		// 修改结束（=======================pxm）

		// 将资产的mac字段赋值为ip
		asset.setAssetMac(asset.getAssetIps());

		// 判断是否为添加资产或者修改资产
		if (asset.getAssetId() == 0) {

			// 获得session内存储的user对象
			User u = (User) super.getSession().getAttribute("SOC_LOGON_USER");

			asset.setAssetUserLoginName(u.getUserLoginName());
			// 为所属于的节点赋值
			asset.setAssetNodeId(1);

		}

		if (asset.getAssetUnName() == -1) {
			asset.setAssetCollectType("syslog");
		}
		if (asset.getAssetUnName() == 0) {
			asset.setAssetCollectType("snmp");
		}
		if (asset.getAssetUnName() == 1) {
			asset.setAssetCollectType("代理");
		}

		// Map<String, Long> map = new HashMap<String, Long>();
		// map.put("assetId", assetId);

		/*
		 * String[] employeeArray = collectorIds.split(",");
		 * 
		 * for (int i = 0; i < employeeArray.length; i++) { if
		 * (StringUtil.isNotEmpty(employeeArray[i])) {
		 * 
		 * } }
		 */
		/*
		 * asset.setAssetCollectorId(Long.valueOf(collectorIds)); if
		 * (!collectorIds.equals("0")) { collector =
		 * collectorManager.queryById(Long.valueOf(collectorIds));
		 * asset.setAssetCollectorName(collector.getCollectorBasic());
		 * asset.setAssetCollectorMac(collector.getCollectorMac()); }
		 */
		Map<String, Object> deviceType = assetManager.queryDevice(asset
				.getAssetDeviceTypeId());
		Map<String, Object> supportDevice = assetManager.queryDevice(asset
				.getAssetSupportDeviceId());

		// 监视目录
		asset.setDirectorise(disAll);

		// 监视文件
		asset.setFile(fireAll);

		// 特权命令
		asset.setAssetPrivilegeCommand(commandAll);

		asset.setAssetSupportDevice((String) supportDevice.get("devicename"));

		asset.setAssetSupportDeviceTypeCode((String) supportDevice
				.get("devicecategorycode"));

		asset.setAssetDeviceType((String) deviceType.get("devicename"));

		asset.setAssetDeviceTypeCode((String) deviceType
				.get("devicecategorycode"));

		asset.setAsset_pessonsibility_userName(userManager
				.queryUserNameById(asset.getAsset_pessponsibility_user_id()));

		asset.setAsset_answer_userName(userManager.queryUserNameById(asset
				.getAsset_answer_user_id()));

		if (asset.getAsset_system_id() == 0) {

			asset.setAsset_system_name("");
		} else {

			asset.setAsset_system_name(asManager.querySystemNameById(asset
					.getAsset_system_id()));
		}

		if (asset.getAsset_system_version() == 0) {
			asset.setAsset_system_version_name("");

		} else {
			asset.setAsset_system_version_name(asManager
					.querySystemNameById(asset.getAsset_system_version()));
		}

		if (asset.getAsset_system_versionno() == 0) {
			asset.setAsset_system_versionno_name("");
		} else {
			asset.setAsset_system_versionno_name(asManager
					.querySystemNameById(asset.getAsset_system_versionno()));
		}

		if (asset.getAsset_system_brand() == 0) {
			asset.setAsset_system_brand_name("");
		} else {
			asset.setAsset_system_brand_name(asManager
					.querySystemNameById(asset.getAsset_system_brand()));
		}

		// int message = 0;
		// 判断资产个数是否达到了注册的资产个数
		if (asset.getAssetId() == 0) {
			Map map = new HashMap();
			if (GlobalConfig.keyFlag != 0) {
				assetManager.updateAsset(asset, assetGroupIds, device);
				// 审计日志
				auditManager.insertByInsertOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "资产",
						super.getRequest().getRemoteAddr(), fieldList);

				// syslog
				/*String logString = "";

				logString = "登录名:"
						+ ((User) this.getSession().getAttribute(
								"SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
						+ getRequest().getRemoteAddr() + " 操作时间:"
						+ DateUtil.curDateTimeStr19() + " 操作类型 :添加资产";
				logManager.writeSystemAuditLog(logString);*/
				logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增资产");
				actionMessage = "1";
			} else {
				int count = assetManager.count(map);
				if (count >= Serial.SERIAL_RESOURCE_NUM) {
					actionMessage = "0";
				} else {
					assetManager.updateAsset(asset, assetGroupIds, device);
					// 审计日志
					auditManager.insertByInsertOperator(((User) this
							.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserId(), "资产", super.getRequest()
							.getRemoteAddr(), fieldList);

					// syslog
					//String logString = "";

					/*logString = "登录名:"
							+ ((User) this.getSession().getAttribute(
									"SOC_LOGON_USER")).getUserLoginName()
							+ " 源IP:" + getRequest().getRemoteAddr() + " 操作时间:"
							+ DateUtil.curDateTimeStr19() + " 操作类型 :添加资产";
					logManager.writeSystemAuditLog(logString);*/
					logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增资产");
					actionMessage = "1";

				}
			}
		} else {
			assetManager.updateAsset(asset, assetGroupIds, device);
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :修改资产";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改资产");
			actionMessage = "1";
		}
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/assetGroup/showGroupTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ "'/soc/asset/queryAsset.action?actionMessage="
					+ actionMessage + ";'" + "</script>";
			System.out.println(script);
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return SUCCESS;
	}

	/**
	 * 核对资产名是否存在 根据前台传入的信息验证
	 * 
	 * @see com.soc.service.asset.AssetService#queryByName(String)
	 */
	public void checkAssetName() {
		log.info("[AssetAction] enter checkAssetName...");

		// 标识此策略名是否存在
		String flag = "false";

		if (StringUtil.isNotBlank(assetName)) {
			List<Asset> list = assetManager.queryByName(assetName);
			if (list.size() > 0) {
				flag = "true";
			}
		}
		// 将flag返回给页面
		try {
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <验证mac地址> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void checkMac() {

		log.info("[AssetAction] enter checkMac...");

		Map map = new HashMap();

		// 将mac地址内的内容转换为大写
		// mac = mac.toUpperCase();

		map.put("mac", mac);

		// map.put("collectorId", Long.valueOf(collectorId));

		// map.put("uName", Long.valueOf(uName));

		// map.put("deviceTypeId", Long.valueOf(deviceTypeId));

		// map.put("supportDeviceId", Long.valueOf(supportDeviceId));

		List<Asset> assetList = new ArrayList<Asset>();

		assetList = assetManager.checkMac(map);

		// 标识此策略名是否存在
		String flag = "false";

		if (assetList.size() > 0) {
			flag = "false";
		} else {
			flag = "true";
		}
		try {
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 测试通信
	 * 
	 */
	public void testLinked() {
		String message = "";
		try {
			if (StringUtil.isNotEmpty(securityUserName)
					&& StringUtil.isNotEmpty(securityPWD)&&
					securityPort>0
					&& StringUtil.isNotEmpty(securityPort + "")
					&& StringUtil.isNotEmpty(securityLinkeThod)
					&& StringUtil.isNotEmpty(mac)) {
				securityUserName = java.net.URLDecoder.decode(securityUserName,
						"UTF-8");
				if (securityLinkeThod.equals("SSH")) {
					SSHMode sshMode = new SSHMode();
					message = sshMode.link(securityUserName, securityPWD, mac,
							securityPort, null);
				}else{
					TelnetMode telnetMode = new TelnetMode();
				if (securityLinkeThod.equals("Telnet")) {
					message = telnetMode.link(securityUserName, securityPWD, mac, securityPort, null);
				}else{
					message = "无此连接方式，请重新选择！";
				}
				}
			} else {
				message = "连接信息不正确，请重新填写！";
			}
			getResponse().getWriter().write(message);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void export() {
		log.info("[AssetAction] enter export...");

		HttpServletResponse response = super.getResponse();
		ExcelAsset excelasset = new ExcelAsset();
		Map limit = new HashMap();
		HttpServletRequest request = super.getRequest();
		// long groupid=((User)
		// this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		// assetGroupId=request.getParameter("assetGroupId");
		if (StringUtil.isNotEmpty(request.getParameter("keyword"))) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			limit.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(ids)) {
			limit.put("ids", ids);
		} else {
			if (Integer.parseInt(assetGroupId) != 1) {
				String str = assetGroupManager.getAllGroupIdByID(Integer
						.parseInt(assetGroupId));
				limit.put("assetGroupIds", str);
			}

		}
		// 高级查询
		if (StringUtil.isNotBlank(selAssetName)) {
			try {
				selAssetName = java.net.URLDecoder
						.decode(selAssetName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limit.put("selAssetName", selAssetName);
		}

		if (StringUtil.isNotBlank(selAssetIps)) {

			limit.put("selAssetIp", IpConvert.iPTransition(selAssetIps));
		}
		if (StringUtil.isNotBlank(selAssetCollector)) {

			limit.put("selAssetCollector", Long.parseLong(selAssetCollector.trim()));
		}

		excelasset.export(assetManager.export(limit), "资产Excel报表",
				collectorManager);
		try {
			// 中文文件名需要编码
			String fileName = "asset_" + DateUtil.curDateStr8();
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName + ".xls");
			OutputStream os = response.getOutputStream();
			excelasset.getGwb().write(os);

			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * <资产的导入> <功能详细描述>已经判断完成了(待验证)
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String importAsset() {

		log.info("[AssetAction] Enter method importAsset.....");
		log.info(upTar);
		if (upTar != null && upTarFileName != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importExcel = new File(new File(path), upTarFileName);

			try {
				FileUtil.copyFile(upTar, importExcel);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Asset> list = new ArrayList<Asset>();

			try {

				list = importAsset.readRead(upTar);
				Map map = new HashMap();
				// 判断全局变量是否有lience控制
				if (GlobalConfig.keyFlag != 0) {
					for (int i = 0; i < list.size(); i++) {
						// List<Asset> list1 =
						// assetManager.queryByName(list.get(i).getAssetName());
						// map.put("mac", list.get(i).getAssetIp());
						// List<Asset> list2 = assetManager.checkMac(map);

						// if (list1 == null || list1.isEmpty() ||
						// list2.size()<0) {
						// 插入资产
						assetManager.insertAsset(list.get(i));

						List<String> fieldList = new ArrayList<String>();
						fieldList.add(list.get(i).getAssetName() + "("
								+ list.get(i).getAssetName() + ")");
						auditManager.insertByInsertOperator(
								((User) this.getSession().getAttribute(
										"SOC_LOGON_USER")).getUserId(), "资产",
								super.getRequest().getRemoteAddr(), fieldList);
						// }
					}
					logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增资产");
				} else {
					// 判断当前的资产数量
					int count = assetManager.count(map);

					for (int i = 0; i < list.size(); i++) {
						// List<Asset> list1 =
						// assetManager.queryByName(list.get(i).getAssetName());
						// map.put("mac", list.get(i).getAssetIp());
						// List<Asset> list2 = assetManager.checkMac(map);

						// if (list1 == null || list1.isEmpty() ||
						// list2.size()<0) {
						// 插入资产
						if (count < Serial.SERIAL_RESOURCE_NUM) {

							assetManager.insertAsset(list.get(i));

							count++;

							List<String> fieldList = new ArrayList<String>();
							fieldList.add(list.get(i).getAssetName() + "("
									+ list.get(i).getAssetName() + ")");
							auditManager.insertByInsertOperator(
									((User) this.getSession().getAttribute(
											"SOC_LOGON_USER")).getUserId(),
									"资产", super.getRequest().getRemoteAddr(),
									fieldList);
						} else {
							info = "full";
							break;
						}
					}
				}
			} catch (MyException e) {

				info = e.getMessage();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				info = "alert";
			} catch (IOException e) {
				e.printStackTrace();
				info = "alert";
			}

		}
		return SUCCESS;
	}

	/**
	 * 漏扫资产导入
	 * 
	 * @return
	 */
	public String importScanAsset() {
		this.log.info("[AssetAction] Enter method importScanAsset.....");
		this.log.info(this.upTar);
		if ((this.upTar != null) && (this.upTarFileName != null)) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importExcel = new File(new File(path), this.upTarFileName);
			try {
				FileUtil.copyFile(this.upTar, importExcel);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Asset> list = new ArrayList<Asset>();
			try {

				list = this.importAsset.readRead1(this.upTar);
				Map map = new HashMap();

				// 判断全局变量是否有lience控制
				if (GlobalConfig.keyFlag != 0) {
					for (int i = 0; i < list.size(); i++) {
						// List<Asset> list1 =
						// assetManager.queryByName(list.get(i).getAssetName());
						// map.put("mac", list.get(i).getAssetIp());
						// List<Asset> list2 = assetManager.checkMac(map);

						// if (list1 == null || list1.isEmpty() ||
						// list2.size()<0) {
						// 插入资产
						assetManager.insertAsset(list.get(i));

						List<String> fieldList = new ArrayList<String>();
						fieldList.add(list.get(i).getAssetName() + "("
								+ list.get(i).getAssetName() + ")");
						auditManager.insertByInsertOperator(
								((User) this.getSession().getAttribute(
										"SOC_LOGON_USER")).getUserId(), "资产",
								super.getRequest().getRemoteAddr(), fieldList);
						// }
					}
				} else {
					// 判断当前的资产数量
					int count = assetManager.count(map);

					for (int i = 0; i < list.size(); i++) {
						// List<Asset> list1 =
						// assetManager.queryByName(list.get(i).getAssetName());
						// map.put("mac", list.get(i).getAssetIp());
						// List<Asset> list2 = assetManager.checkMac(map);

						// if (list1 == null || list1.isEmpty() ||
						// list2.size()<0) {
						// 插入资产
						if (count < Serial.SERIAL_RESOURCE_NUM) {

							assetManager.insertAsset(list.get(i));

							count++;

							List<String> fieldList = new ArrayList<String>();
							fieldList.add(list.get(i).getAssetName() + "("
									+ list.get(i).getAssetName() + ")");
							auditManager.insertByInsertOperator(
									((User) this.getSession().getAttribute(
											"SOC_LOGON_USER")).getUserId(),
									"资产", super.getRequest().getRemoteAddr(),
									fieldList);
						} else {
							info = "full";
							break;
						}
					}
				}
			} catch (MyException e) {
				this.info = e.getMessage();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				this.info = "alert";
			} catch (IOException e) {
				e.printStackTrace();
				this.info = "alert";
			}
		}

		return "success";
	}

	/**
	 * <计算资产的风险值> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void completeAssetValue() {
		log.info("[AssetAction] Enter method completeAssetValue.....");

		double s = Math.scalb(3, 2);

		double temp = ((Math.scalb((GlobalConfig.assetUse), use)
				+ Math.scalb((GlobalConfig.assetComplete), complete) + Math
				.scalb((GlobalConfig.assetSecret), secret))) / 3;

		double finalvalue = Math.log(temp) / (Math.log((double) 2));

		int completevalue = (int) finalvalue;

		try

		{
			getResponse().getWriter().write(String.valueOf(completevalue));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * <AJAX方法，查询组对应的资产>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String queryAssetByGroupID() {
		HttpServletRequest request = super.getRequest();

		Map map = new HashMap();

		if (request.getParameter("assetGroupId") != null
				&& !request.getParameter("assetGroupId").equals("")) {
			if (Integer.parseInt(assetGroupId) == 1) {

			} else {
				assetGroupId = request.getParameter("assetGroupId");

				map.put("assetGroupId", Integer.parseInt(assetGroupId));
			}
		}

		assetList = assetManager.queryAssetByGroupId(map);
		/*
		 * else{ for (AssetGroup assetgroup : assetGroupList) { //
		 * assetGroupIds.add(assetgroup.getAssetGroupId());
		 * sb.append(assetgroup.getAssetGroupId()); sb.append(","); }
		 * map.put("assetGroupIds", sb.toString().substring(0,
		 * sb.toString().length()-1)); }
		 */
		return SUCCESS;
	}

	public void systemChangeAjax1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", assetSystemId);
		map.put("level", 3);
		assetsystembrandlist = asManager.queryAssetSystem(map);
		JSONArray jsonarray = new JSONArray();
		jsonarray = JSONArray.fromObject(assetsystembrandlist);
		HttpServletResponse respons = super.getResponse();
		try {
			respons.getWriter().write(jsonarray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void systemChangeAjax2() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", assetSystemId);
		map.put("level", 1);
		assetsystemlist = asManager.queryAssetSystem(map);
		JSONArray jsonArray2 = new JSONArray();
		jsonArray2 = JSONArray.fromObject(assetsystemlist);
		HttpServletResponse respons = super.getResponse();
		try {
			respons.getWriter().write(jsonArray2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void systemChangeAjax3() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", assetSystemId);
		map.put("level", 2);
		assetsystemlist = asManager.queryAssetSystem(map);
		JSONArray jsonArray3 = new JSONArray();
		jsonArray3 = JSONArray.fromObject(assetsystemlist);
		HttpServletResponse respons = super.getResponse();
		try {
			respons.getWriter().write(jsonArray3.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String insertAssetSystem() {
		HashMap<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("name", assetSystemName != null ? assetSystemName.trim()
				: assetSystemName);
		map.put("comment",
				assetSystemComment != null ? assetSystemComment.trim()
						: assetSystemComment);
		map.put("level", 0);
		map.put("nolevel", 0);
		if (assetSystemId == 0) {
			assetSystemId = (int) asManager.insertAssetSystem(map);
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(map.get("name") + "(" + map.get("name") + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
					.getRequest().getRemoteAddr(), fieldList);
		}
		if (assetSystemId > 0) {
			if (systemOptions != null && systemOptions.length > 0) {
				for (int i = 0; i < systemOptions.length; i++) {
					if (systemOptions[i] != "") {
						map = new HashMap<String, Object>();
						map.put("brandName", systemOptions[i]);
						map.put("brandid", assetSystemId);
						asManager.insertAssetSystemBrand(map);
						List<String> fieldList = new ArrayList<String>();
						fieldList.add(map.get("brandName") + "("
								+ map.get("brandName") + ")");
						auditManager.insertByInsertOperator(
								((User) this.getSession().getAttribute(
										"SOC_LOGON_USER")).getUserId(), "资产系统",
								super.getRequest().getRemoteAddr(), fieldList);
					}
				}
			}
			if (versionOptions != null && versionOptions.length > 0) {
				for (int i = 0; i < versionOptions.length; i++) {
					if (versionOptions[i] != "") {
						map = new HashMap<String, Object>();
						map.put("name", versionOptions[i]);
						map.put("level", assetSystemId);
						map.put("comment", "");
						map.put("nolevel", 1);
						asManager.insertAssetSystem(map);
						List<String> fieldList = new ArrayList<String>();
						fieldList.add(map.get("name") + "(" + map.get("name")
								+ ")");
						auditManager.insertByInsertOperator(
								((User) this.getSession().getAttribute(
										"SOC_LOGON_USER")).getUserId(), "资产系统",
								super.getRequest().getRemoteAddr(), fieldList);
					}
				}
			}
			if (versionNoOptions != null && versionNoOptions.length > 0) {
				for (int i = 0; i < versionNoOptions.length; i++) {
					if (versionNoOptions[i] != "") {
						map = new HashMap<String, Object>();
						map.put("name", versionNoOptions[i]);
						map.put("level", assetSystemId);
						map.put("comment", "");
						map.put("nolevel", 2);
						asManager.insertAssetSystem(map);
						List<String> fieldList = new ArrayList<String>();
						fieldList.add(map.get("name") + "(" + map.get("name")
								+ ")");
						auditManager.insertByInsertOperator(
								((User) this.getSession().getAttribute(
										"SOC_LOGON_USER")).getUserId(), "资产系统",
								super.getRequest().getRemoteAddr(), fieldList);
					}
				}
			}
		}

		return SUCCESS;
	}

	// Ajax 根据Asset ID查询资产信息
	public String queryAssetById() {
		if (StringUtil.isNotEmpty(mac)) {
			long id = Long.parseLong(mac);
			asset = GlobalConfig.assetGlobleMap.get(id);
			return SUCCESS;
		}
		return null;
	}

	// 资产下发功能实现
	public String issuedMethod() {
		if (assetId != 0) {
			asset = assetManager.searchById(assetId);
			assetManager.createCollectorConfigFile(asset);
			asset.setAssetIssued(1);
			assetManager.updateIssued(asset);
			info = "资产下发成功！";

		}
		return SUCCESS;
	}

	// 修改资产关联的规则
	public String updateAnalysisRules() {

		if (assetId != 0 && StringUtil.isNotEmpty(info)) {
			Map map = new HashMap();
			map.put("relRules", info);
			map.put("assetId", assetId);
			assetManager.updateAnalysisRules(map);
			List<String> fieldList = new ArrayList<String>();
			fieldList
					.add(map.get("relRules") + "(" + map.get("relRules") + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "关联规则", super
					.getRequest().getRemoteAddr(), fieldList);
		}

		return SUCCESS;
	}
    
	
	//判断拓扑当前的状态
	public String showTopo()
	{
		
		topoStatus = settingManager.queryByKey("topo_status");
		
		//System.out.println("1234");
		
	
		
		return SUCCESS;
	}
	
	
	/**
	 * 执行拓扑探测
	 */
	public void doTopoSearch()
	{
		List<String> finalIP = new ArrayList<String>();
		
		//得到本机所有的ip列表
		finalIP=TopoSearch.getHostIp();
		
		//得到所有的在线的ip
		for(String ipTemp:finalIP)
		{
			TopoSearch.pingHost(ipTemp);
		}
		
		//循环判断得出交换机
		for(String tempSearchIp:TopoSearch.nmapIpList)
		{
			//得到探测到的段内有多少台机器不是主机，是交换机路由器
			TopoSearch.testIfSwitch(tempSearchIp);
		}
		
		List<String> arpTempList = new ArrayList<String>();
		
		List<AutoToPo> autoTopoList = new ArrayList<AutoToPo>();
		
		AutoToPoUtils autoToPoTemp = new AutoToPoUtils(assetManager, deviceManageService);
		
		if(TopoSearch.switchList.size()>0)
		{
			for(int i=0;i<TopoSearch.switchList.size();i++)
			{
				arpTempList=TopoSearch.getArpList(TopoSearch.switchList.get(i));
				
				if(arpTempList.size()>1)
				{
					System.out.println("得到IP"+arpTempList.get(0));
					
					String routeIP = arpTempList.get(0);
					
					AutoToPo routeTopo = new AutoToPo(); 
					routeTopo.setIp(routeIP);
					routeTopo.setType(1);
					autoTopoList.add(routeTopo);
					
					for(int j =1;j<arpTempList.size();j++)
					{
						System.out.println("得到IP"+arpTempList.get(j));
						
						AutoToPo temp = new AutoToPo();
						temp.setIp(arpTempList.get(j).trim());
						temp.setSuperIp(routeIP);
						temp.setType(2);
						
						System.out.println("本机ip为:"+temp.getIp()+"上级IP为:"+temp.getSuperIp()+"类型为"+temp.getType());
						
						autoTopoList.add(temp);
					}
				
				}
				//插入一个交换机的地址
				autoToPoTemp.autoInsert(autoTopoList);
				arpTempList.clear();
				autoTopoList.clear();
			}
		}		
	}
	
	//启动拓扑的探测
	public  void  updateTopo()
	{
	   Setting setting =new Setting();
	   setting.setKey("topo_status");
	   setting.setValue("2");
	  
	  // doTopoSearch();
	  
	   
	   settingManager.updateByKey("topo_status", setting);
	   
	   String result="探测完成!!";
	   try {
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void exportTEMP() {
		HttpServletResponse response = super.getResponse();
		String path = super.getSession().getServletContext()
				.getRealPath("/riskfile");
		String fileName = "asset_templet.xls";
		File file = new File(path, "/" + fileName);
		try {
			InputStream inputStream = new FileInputStream(file);
			// 中文文件名需要编码
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) > 0) {
				os.write(b, 0, len);

			}

			inputStream.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//传参数到前台利用ajax查看所有采集器
	public String queryAllCollector(){
		// 查看所有的采集器List
				collectorList = collectorManager.queryCollector();
				return SUCCESS;
	}
     
	
	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
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

	public String getSelAssetImportance() {
		return selAssetImportance;
	}

	public void setSelAssetImportance(String selAssetImportance) {
		this.selAssetImportance = selAssetImportance;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(String assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getAssetGroupIds() {
		return assetGroupIds;
	}

	public void setAssetGroupIds(String assetGroupIds) {
		this.assetGroupIds = assetGroupIds;
	}

	public List<HistoryAsset> getHistoryAssetList() {
		return historyAssetList;
	}

	public void setHistoryAssetList(List<HistoryAsset> historyAssetList) {
		this.historyAssetList = historyAssetList;
	}

	public int getHistoryAssetId() {
		return historyAssetId;
	}

	public void setHistoryAssetId(int historyAssetId) {
		this.historyAssetId = historyAssetId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
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

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public String getCollectorIds() {
		return collectorIds;
	}

	public void setCollectorIds(String collectorIds) {
		this.collectorIds = collectorIds;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public long getAssetSegMent() {
		return assetSegMent;
	}

	public void setAssetSegMent(long assetSegMent) {
		this.assetSegMent = assetSegMent;
	}

	public long getAssetSeg() {
		return assetSeg;
	}

	public void setAssetSeg(long assetSeg) {
		this.assetSeg = assetSeg;
	}

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<Map> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(List<Map> categoryName) {
		this.categoryName = categoryName;
	}

	public List<Map> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Map> categorys) {
		this.categorys = categorys;
	}

	public long getUpsId() {
		return upsId;
	}

	public void setUpsId(long upsId) {
		this.upsId = upsId;
	}

	public long getCategoryF() {
		return categoryF;
	}

	public void setCategoryF(long categoryF) {
		this.categoryF = categoryF;
	}

	public long getCategoryB() {
		return categoryB;
	}

	public void setCategoryB(long categoryB) {
		this.categoryB = categoryB;
	}

	public long getBigCateId() {
		return bigCateId;
	}

	public void setBigCateId(long bigCateId) {
		this.bigCateId = bigCateId;
	}

	public long getSmallCateId() {
		return smallCateId;
	}

	public void setSmallCateId(long smallCateId) {
		this.smallCateId = smallCateId;
	}

	public long getSnmp() {
		return snmp;
	}

	public void setSnmp(long snmp) {
		this.snmp = snmp;
	}

	public long getSyslog() {
		return syslog;
	}

	public void setSyslog(long syslog) {
		this.syslog = syslog;
	}

	public String getDisAll() {
		return disAll;
	}

	public void setDisAll(String disAll) {
		this.disAll = disAll;
	}

	public String getFireAll() {
		return fireAll;
	}

	public void setFireAll(String fireAll) {
		this.fireAll = fireAll;
	}

	public List getDirectoriseList() {
		return directoriseList;
	}

	public void setDirectoriseList(List directoriseList) {
		this.directoriseList = directoriseList;
	}

	public List getFireList() {
		return fireList;
	}

	public void setFireList(List fireList) {
		this.fireList = fireList;
	}

	public String getCommandAll() {
		return commandAll;
	}

	public void setCommandAll(String commandAll) {
		this.commandAll = commandAll;
	}

	public List getCommandList() {
		return commandList;
	}

	public void setCommandList(List commandList) {
		this.commandList = commandList;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getSupportDevice() {
		return supportDevice;
	}

	public void setSupportDevice(String supportDevice) {
		this.supportDevice = supportDevice;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getSupportDeviceId() {
		return supportDeviceId;
	}

	public void setSupportDeviceId(String supportDeviceId) {
		this.supportDeviceId = supportDeviceId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public File getUpTar() {
		return upTar;
	}

	public void setUpTar(File upTar) {
		this.upTar = upTar;
	}

	public String getUpTarFileName() {
		return upTarFileName;
	}

	public void setUpTarFileName(String upTarFileName) {
		this.upTarFileName = upTarFileName;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
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

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public List<AssetGroup> getAssetGroupList() {
		return assetGroupList;
	}

	public void setAssetGroupList(List<AssetGroup> assetGroupList) {
		this.assetGroupList = assetGroupList;
	}

	public AssetSystemService getAsManager() {
		return asManager;
	}

	public void setAsManager(AssetSystemService asManager) {
		this.asManager = asManager;
	}

	public List<AssetSystem> getAssetsystemlist() {
		return assetsystemlist;
	}

	public void setAssetsystemlist(List<AssetSystem> assetsystemlist) {
		this.assetsystemlist = assetsystemlist;
	}

	public int getAssetSystemId() {
		return assetSystemId;
	}

	public void setAssetSystemId(int assetSystemId) {
		this.assetSystemId = assetSystemId;
	}

	public String[] getSystemOptions() {
		return systemOptions;
	}

	public void setSystemOptions(String[] systemOptions) {
		this.systemOptions = systemOptions;
	}

	public String[] getVersionOptions() {
		return versionOptions;
	}

	public void setVersionOptions(String[] versionOptions) {
		this.versionOptions = versionOptions;
	}

	public String[] getVersionNoOptions() {
		return versionNoOptions;
	}

	public void setVersionNoOptions(String[] versionNoOptions) {
		this.versionNoOptions = versionNoOptions;
	}

	public String getAssetSystemName() {
		return assetSystemName;
	}

	public void setAssetSystemName(String assetSystemName) {
		this.assetSystemName = assetSystemName;
	}

	public String getAssetSystemComment() {
		return assetSystemComment;
	}

	public void setAssetSystemComment(String assetSystemComment) {
		this.assetSystemComment = assetSystemComment;
	}

	public String getAssetListStr() {
		return assetListStr;
	}

	public void setAssetListStr(String assetListStr) {
		this.assetListStr = assetListStr;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ImportAsset getImportAsset() {
		return importAsset;
	}

	public void setImportAsset(ImportAsset importAsset) {
		this.importAsset = importAsset;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public List<AssetSystem> getAssetsystembrandlist() {
		return assetsystembrandlist;
	}

	public void setAssetsystembrandlist(List<AssetSystem> assetsystembrandlist) {
		this.assetsystembrandlist = assetsystembrandlist;
	}

	public List<AssetSystem> getSystemVersionList() {
		return SystemVersionList;
	}

	public void setSystemVersionList(List<AssetSystem> systemVersionList) {
		SystemVersionList = systemVersionList;
	}

	public List<AssetSystem> getSystemVersionNoList() {
		return SystemVersionNoList;
	}

	public void setSystemVersionNoList(List<AssetSystem> systemVersionNoList) {
		SystemVersionNoList = systemVersionNoList;
	}

	public List<AssetSystem> getSystemBrandList() {
		return SystemBrandList;
	}

	public void setSystemBrandList(List<AssetSystem> systemBrandList) {
		SystemBrandList = systemBrandList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public int getIsMonitorNetworkTopology() {
		return isMonitorNetworkTopology;
	}

	public void setIsMonitorNetworkTopology(int isMonitorNetworkTopology) {
		this.isMonitorNetworkTopology = isMonitorNetworkTopology;
	}

	public DeviceCategoryManageService getDeviceCategoryService() {
		return deviceCategoryService;
	}

	public void setDeviceCategoryService(
			DeviceCategoryManageService deviceCategoryService) {
		this.deviceCategoryService = deviceCategoryService;
	}

	public DeviceManageService getDeviceManageService() {
		return deviceManageService;
	}

	public void setDeviceManageService(DeviceManageService deviceManageService) {
		this.deviceManageService = deviceManageService;
	}

	public long getDeviceMarkID() {
		return deviceMarkID;
	}

	public void setDeviceMarkID(long deviceMarkID) {
		this.deviceMarkID = deviceMarkID;
	}

	public long getDeviceCategoryId() {
		return deviceCategoryId;
	}

	public void setDeviceCategoryId(long deviceCategoryId) {
		this.deviceCategoryId = deviceCategoryId;
	}

	public List<Device> getSaveDeviceList() {
		return saveDeviceList;
	}

	public void setSaveDeviceList(List<Device> saveDeviceList) {
		this.saveDeviceList = saveDeviceList;
	}

	public List<DeviceCategory> getDeviceCategoryList() {
		return deviceCategoryList;
	}

	public void setDeviceCategoryList(List<DeviceCategory> deviceCategoryList) {
		this.deviceCategoryList = deviceCategoryList;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getNetOutLoginName() {
		return netOutLoginName;
	}

	public void setNetOutLoginName(String netOutLoginName) {
		this.netOutLoginName = netOutLoginName;
	}

	public String getSnmpCommunityName() {
		return snmpCommunityName;
	}

	public void setSnmpCommunityName(String snmpCommunityName) {
		this.snmpCommunityName = snmpCommunityName;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public int getSecurityPort() {
		return securityPort;
	}

	public void setSecurityPort(int securityPort) {
		this.securityPort = securityPort;
	}

	public String getSecurityUserName() {
		return securityUserName;
	}

	public void setSecurityUserName(String securityUserName) {
		this.securityUserName = securityUserName;
	}

	public String getSecurityPWD() {
		return securityPWD;
	}

	public void setSecurityPWD(String securityPWD) {
		this.securityPWD = securityPWD;
	}

	public String getSecurityLinkeThod() {
		return securityLinkeThod;
	}

	public void setSecurityLinkeThod(String securityLinkeThod) {
		this.securityLinkeThod = securityLinkeThod;
	}

}
