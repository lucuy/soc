package com.soc.service.asset.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.asset.AssetGroupDao;
import com.soc.dao.risk.AssetRiskGroupDao;
import com.soc.dao.systemsetting.SettingCollectorDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.systemsetting.rules.AnalysisRulesDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.risk.AssetRiskEvaluation;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.risk.AssetRiskEvaluationService;
import com.topo.dao.device.CommonDao;
import com.topo.model.device.Device;
import com.topo.model.deviceCategory.DeviceCategory;
import com.util.IpConvert;
import com.util.ReadAndWriteFileUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetServiceImpl extends BaseServiceImpl implements AssetService {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片

	private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片

	// 资产管理业务类
	private AssetDao assetDao;

	private SettingCollectorDao settingCollectorDao;
	private AssetGroupDao assetGroupDao;
	private AnalysisRulesDao analysisDao;
	private AssetRiskGroupDao assetRiskGroupDao;
	private SettingDao settingDao;

	// 资产风险赋值业务类。
	private AssetRiskEvaluationService assetRiskEvaluationManager;

	private AssetGroupService assetGroupManager;
	
	//添加设备
	private CommonDao deviceDao;

	public AssetRiskGroupDao getAssetRiskGroupDao() {
		return assetRiskGroupDao;
	}

	public void setAssetRiskGroupDao(AssetRiskGroupDao assetRiskGroupDao) {
		this.assetRiskGroupDao = assetRiskGroupDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	public int count(Map map) {
		return assetDao.count(map);
	}

	public SearchResult query(Map map, Page page) {
		// 按Map中存储的条件查找用户列表
		String ips = String.valueOf(map.get("selAssetIps"));
		int rowsCount;
		List<Asset> list;
		if (ips != "null") {
			Map<String, Long> mapIps = new HashMap<String, Long>();
			long selAssetIp = IpConvert.iPTransition(ips);
			mapIps.put("selAssetIp", selAssetIp);
			rowsCount = assetDao.count(mapIps);
			list = assetDao.query(mapIps, page.getStartIndex(),
					page.getPageSize());
		} else {
			rowsCount = assetDao.count(map);
			list = assetDao
					.query(map, page.getStartIndex(), page.getPageSize());
		}
		page.setTotalCount(rowsCount);

		for (Asset asset : list) {
			asset.setAssetIps(IpConvert.IpString(asset.getAssetIp()));
		}

		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Asset> query(Map map) {

		try {
			String assetGroupId = map.get("assetGroupId").toString();
			long assetGroupIds = Integer.parseInt(assetGroupId);

			if (assetGroupIds == 1) {

				map.remove("assetGroupId");
			} else {

				String assetGroupIdss = assetGroupManager
						.getAllGroupIdByID(assetGroupIds);

				map.put("assetGroupId", assetGroupIdss);
			}

		} catch (Exception e) {

			//log.info("没有传递资产组的ID");
		}

		return assetDao.query(map);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Asset> queryAssetByAssetGroupId(long assetGroupId) {
		return assetDao.queryAssetByAssetGroupId(assetGroupId);
	}

	public void deleteAsset(long assetId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetId", assetId);
		map.put("assetUpdateTime", new Date());
		Asset asset = assetDao.queryByid(assetId);
		assetDao.deleteAssetGroupAssets(assetId);
		assetDao.deleteAssetValues(assetId);
		assetDao.deleteRiskValues(assetId);
		assetDao.deleteRiskVulnerabilityassessment(IpConvert.IpString(asset
				.getAssetIp()));

		assetDao.deleteAsset(map);
		GlobalConfig.assetGlobleMap.remove(assetId);
		createCollectorConfigFile(assetDao.queryByid(assetId));
	}

	/**
	 * {@inheritDoc}
	 */
	public Asset searchById(long id) {
		Asset asset = assetDao.queryByid(id);
		if (asset != null) {
			asset.setAssetIps(IpConvert.IpString(asset.getAssetIp()));
			asset.setAssetGateWays(IpConvert.IpString(asset.getAssetGateWay()));
		}

		return asset;
	}

	/**
	 * {@inheritDoc}
	 */
	public long updateAsset(Asset asset, String assetGroupIds,Device device) {
		
		long assetId = 0;

		asset.setAssetUpdateDateTime(new Date());

		asset.setAssetDeviceRules(assetDao.queryDeviceRules(asset
				.getAssetSupportDeviceId()));

		// 确定为编辑资产
		if (asset.getAssetId() != 0) {
			Map map = new HashMap();
			asset.setAssetIp(IpConvert.iPTransition(asset.getAssetIps()));
			asset.setAssetMac(asset.getAssetMac().toUpperCase());
			asset.setAssetGateWay(IpConvert.iPTransition(asset
					.getAssetGateWays()));
			asset.setAssetSegMent(Long.parseLong(Long.toBinaryString(IpConvert
					.iPTransition(asset.getAssetIps())
					& IpConvert.iPTransition(asset.getAssetGateWays())), 2));
			map.put("assetStatus", asset.getAssetStatus());
			map.put("assetId", asset.getAssetId());

			map.put("vAIp", IpConvert.IpString(asset.getAssetIp()));
			assetDao.updateAssetValuesStatus(map);
			assetDao.updateRiskValuesStatus(map);
			assetDao.updateRiskVulnerabilityassessmentStatus(map);
			assetDao.updateAsset(asset);
			GlobalConfig.assetGlobleMap.put(asset.getAssetId(), asset);
			device.setDevice_asset_id(asset.getAssetId());
			//网络拓扑begin==
			deviceDao.updateDeviceByAssetId(device);
			//网络拓扑end==
			
			// 判断资产是否下发过
			if (asset.getAssetIssued() == 1) {
				createCollectorConfigFile(asset);
			}

		}
		// 为添加资产
		else {
			asset.setAssetCreateDateTime(new Date());
			asset.setAssetIsDelete(0);
			asset.setAssetIp(IpConvert.iPTransition(asset.getAssetIps()));
			asset.setAssetGateWay(IpConvert.iPTransition(asset
					.getAssetGateWays()));
			asset.setAssetSegMent(Long.parseLong(Long.toBinaryString(IpConvert
					.iPTransition(asset.getAssetIps())
					& IpConvert.iPTransition(asset.getAssetGateWays())), 2));
			asset.setAssetMac(asset.getAssetMac().toUpperCase());
			// 如果为syslog方式
			if (asset.getAssetUnName() == -1) {
				// 设置监控文件为空
				asset.setDirectorise("");
				// 设置监控目录为空
				asset.setFile("");
				// 设置特权命令为空
				asset.setAssetPrivilegeCommand("");
				// 设置版本为空
				asset.setVersion("");
				// 设置团体名为空
				asset.setOrganizationName("");
			}
			if (asset.getAssetUnName() == 0) {
				// 设置监控文件为空
				asset.setDirectorise("");
				// 设置监控目录为空
				asset.setFile("");
				// 设置特权命令为空
				asset.setAssetPrivilegeCommand("");
			}

			assetId = assetDao.insertAsset(asset);
			GlobalConfig.assetGlobleMap.put(assetId, asset);
			//网络拓扑begin
			device.setDevice_asset_id(assetId);
			try {
				deviceDao.addObject(device);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//网络拓扑end

			try {
				// 为资产风险添加风险值。
				AssetRiskEvaluation assetRiskValue = new AssetRiskEvaluation();
				assetRiskValue.setAssetName(asset.getAssetName());
				assetRiskValue.setRelAssetId(assetId);
				assetRiskValue.setAssetValueUpdateTime(new Date());

				assetRiskEvaluationManager
						.addAssetRiskEvaluation(assetRiskValue);
			} catch (Exception e) {
				log.info("为资产赋值失败");
			}
		}

		// 判断程序是新增资产还是修改资产信息，将相应的值附加到assetID
		long assetID = 0;

		if (assetId != 0) {
			assetID = assetId;
		} else {
			assetID = asset.getAssetId();
		}

		// 添加资产和资产组的对应关系到中间表
		if (StringUtil.isNotBlank(assetGroupIds)) {
			assetGroupManager.delAssetLinked(assetID);
			String[] str = assetGroupIds.split(",");
			Map map = new HashMap<String, Object>();
			map.put("assetId", assetID);
			StringBuffer strBuf = new StringBuffer() ;
			for (int i = 0; i < str.length; i++) {
				if (StringUtil.isNotEmpty(str[i]) && str[i] != "") {
                    
					List<AssetGroup> listGroup = new ArrayList<AssetGroup>();
					assetGroupManager.setAssetGroupFather() ; 
					listGroup = assetGroupManager.queryAllFatherGroupId(Long
							.parseLong(str[i]));
            
					for (AssetGroup group : listGroup) {
						
						long groupId = group.getAssetGroupId() ; 
						
						if(strBuf.indexOf(String.valueOf(groupId)) != -1){
							continue ;
						}
						
						if(strBuf.length()==0){
							strBuf.append(group.getAssetGroupId());
						}else{
							strBuf.append(","+group.getAssetGroupId()) ; 
						}
						
						map.put("assetGroupId", group.getAssetGroupId());
						assetGroupManager.linkedAssetAssetGroup(map);
					}
	
				
				}
			}

		}

		return assetId;
	}

	public void deleteAssetGroup(long assetGroupid) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", 0);
		map.put("groupName", "不进行分组");
		map.put("assetGroupId", assetGroupid);
		// 删除资产所关联的组
		assetDao.deleteGroup(map);
	}

	/**
	 * {@inheritDoc}
	 */
	StringBuffer treeBuffs;

	@Override
	public String queryIpTree(String objectBath) {
		treeBuffs = new StringBuffer();
		List<Map> queryAssetList = assetDao.queryIpTree();

		treeBuffs.append("<ul>");
		treeBuffs
				.append("<li id=\"query_img_query_network"
						+ 1
						+ "\"><a href=\"javascript:action('"
						+ 1
						+ "','img_query_network');\"><img src=\""
						+ objectBath
						+ "/images/"
						+ PICTURE_NAME_CLOSE
						+ "\" id=\"img_query_network"
						+ 1
						+ "\"></a>&nbsp;<a href=\"javascript:void(0);\" ondblclick=\"action('"
						+ 1
						+ "','img_query_network')\"><span class=\"eventext\">"
						+ "网络拓扑" + "</span></a>");
		for (Map<String, Long> map : queryAssetList) {
			String name = IpConvert.IpString(map.get("segment"));
			String segments = Long.toBinaryString(map.get("gate"));
			int num = segments.lastIndexOf("1") + 1;
			treeBuffs.append("<ul  class=\"disply\">");
			treeBuffs
					.append("<li class=\"eventleft\"><a href=\"javascript:linkTo('"
							+ 1
							+ "','"
							+ map.get("segment")
							+ "');\"><img src=\""
							+ objectBath
							+ "/images/"
							+ PICTURE_NAME_SEED
							+ "\" ></a>&nbsp;<a href=\"javascript:linkTo('"
							+ 1
							+ "','"
							+ map.get("segment")
							+ "');\"><span class=\"eventext\">"
							+ name
							+ "/"
							+ num
							+ "("
							+ map.get("count")
							+ ")"
							+ "</span></a>");
			treeBuffs.append("</li>");
			treeBuffs.append("</ul>");
		}
		treeBuffs.append("</li>");
		treeBuffs.append("</ul>");
		return treeBuffs.toString();
	}

	public List<Asset> queryByName(String name) {

		return assetDao.queryByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryCategory() {
		return assetDao.queryCategory();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryCategoryService() {
		return assetDao.queryCategoryService();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryCategorys() {
		return assetDao.queryCategorys();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryCategory(long upsId) {
		return assetDao.queryCategory(upsId);
	}

	/**
	 * {@inheritDoc}
	 */
	public String queryDeviceById(long deviceId) {
		return assetDao.queryDeviceById(deviceId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> queryDevice(long deviceId) {
		return assetDao.queryDevice(deviceId);
	}

	/**
	 * 将Ip地址装换成二进制
	 * 
	 * @return ip String
	 * @see [类、类#方法、类#成员]
	 */
	/*
	 * public String getIpNum(String ip) { int ipNum = 0; String ipString="";
	 * String s=null;
	 * 
	 * if (ip != null && ip.trim().length() != 0) { String[] subips =
	 * ip.split("\\."); for (String str : subips) { StringBuffer result = new
	 * StringBuffer(); ipNum = Integer.parseInt(str); s =
	 * Integer.toBinaryString(ipNum); if(s.length() < 8){ for(int i = 0;i < 8 -
	 * s.length();i++){ result.insert(0, 0); }
	 * s=String.valueOf(result.toString()+s); } ipString+=s; } } return
	 * ipString; }
	 */

	// 字符串数组转换成long数组
	public static Long[] convertionToLong(String[] strs) {// 将String数组转换为Long类型数组
		Long[] longs = new Long[strs.length]; // 声明long类型的数组
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i]; // 将strs字符串数组中的第i个值赋值给str
			long thelong = Long.valueOf(str);// 将str转换为long类型，并赋值给thelong
			longs[i] = thelong;// 将thelong赋值给 longs数组中对应的地方
		}

		return longs; // 返回long数组
	}
    /**
     * 线程锁
     */
	public synchronized void createCollectorConfigFile(Asset a) {

		Collector collector = settingCollectorDao.queryById(a
				.getAssetCollectorId());

		if (collector != null) {
			StringBuffer strBuf = new StringBuffer();
			StringBuffer agentBuf = new StringBuffer();
			StringBuffer host = new StringBuffer();
			StringBuffer assets = new StringBuffer();
			StringBuffer rules = new StringBuffer();
			int j = 0;

			int m = 0;
			// 查询当前采集器的所有资产
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("assetCollectorId", collector.getCollectorId());
			List<Asset> allAsset = assetDao.query(map);

			// 得到配置信息
			for (int i = 0; i < allAsset.size(); i++) {
				Asset asset = allAsset.get(i);
				// 判断资产是否为启用状态
				if (asset.getAssetStatus() == 1) {

					// 得到所有资产的信息
					if (m == 0) {
						// strBuf.append("####################################################################################");
						// strBuf.append("\n");
						// strBuf.append("[asset]");
						strBuf.append("\n");

						assets.append("assets = " + asset.getAssetId() + ":"
								+ IpConvert.IpString(asset.getAssetIp())
								+ ":FF-FF-FF-FF-FF-FF" + ":"
								+ asset.getAssetDeviceRules());
					} else {
						assets.append("," + asset.getAssetId() + ":"
								+ IpConvert.IpString(asset.getAssetIp())
								+ ":FF-FF-FF-FF-FF-FF" + ":"
								+ asset.getAssetDeviceRules());
					}

					// 得到agent的配置信息
					if (StringUtil.equals(asset.getAssetCollectType(), "代理")) {
						// agentBuf.append("\n");
						agentBuf.append("\n");
						agentBuf.append("[agent " + asset.getAssetMac() + "]");// mac地址为FFFFF..的agent的配置
						agentBuf.append("\n");
						// agentBuf.append("ip = " +
						// IpConvert.IpString(asset.getAssetIp()));
						// agentBuf.append("ip = " +
						// collector.getCollectorIp());
						if (asset.getAssetPort() != null
								&& (!asset.getAssetPort().equals(""))) {
							agentBuf.append("\n");
							agentBuf.append("port = " + asset.getAssetPort());
						}
						if (asset.getAssetLog() != null
								&& (!asset.getAssetLog().equals(""))) {
							agentBuf.append("\n");
							agentBuf.append("interval=" + asset.getAssetLog());
						}

						if (asset.getAssetAPM() != null
								&& (!asset.getAssetAPM().equals(""))) {
							agentBuf.append("\n");
							agentBuf.append("apm_port=" + asset.getAssetAPM());
						}

						if (asset.getAssetAPMMonitor() != null
								&& (!asset.getAssetAPMMonitor().equals(""))) {
							agentBuf.append("\n");
							agentBuf.append("apm_interval="
									+ asset.getAssetAPMMonitor());
						}

						if (StringUtil.equals(asset.getAssetSupportDevice()
								.trim(), "Linux")) {
							if (!StringUtil.equals(
									asset.getAssetPrivilegeCommand(), "")) {
								agentBuf.append("\n");
								agentBuf.append("instruction_Ifo_files = "
										+ asset.getAssetPrivilegeCommand());// 特权命令

							}

							if (!StringUtil.equals(asset.getFile(), "")) {
								agentBuf.append("\n");
								agentBuf.append("Monitor_linux_files = "
										+ asset.getFile());// 监视文件(linux)

							}

							if (!StringUtil.equals(asset.getDirectorise(), "")) {
								agentBuf.append("\n");
								agentBuf.append("Monitor_linux_paths = "
										+ asset.getDirectorise());// 监视文件夹
							}

						} else if (StringUtil.equals(asset
								.getAssetSupportDevice().trim(), "Windows")) {
							if (!StringUtil.equals(asset.getFile(), "")) {
								agentBuf.append("\n");
								agentBuf.append("Monitor_linux_files = "
										+ asset.getFile());// 监视文件(linux)

							}

							if (!StringUtil.equals(asset.getDirectorise(), "")) {
								agentBuf.append("\n");
								agentBuf.append("Monitor_linux_paths = "
										+ asset.getDirectorise());// 监视文件夹
							}
						}
					}
					// 得到snmp的配置信息
					if (StringUtil.equals(asset.getAssetCollectType(), "snmp")) {
						if (j == 0) {
							host.append("host = "
									+ IpConvert.IpString(asset.getAssetIp())
									+ ":" + collector.getCollectorWalkPort()
									+ ":" + asset.getOrganizationName() + ":"
									+ asset.getVersion());
						} else {
							host.append(","
									+ IpConvert.IpString(asset.getAssetIp())
									+ ":" + collector.getCollectorWalkPort()
									+ ":" + asset.getOrganizationName() + ":"
									+ asset.getVersion());
						}
						j++;
					}
					m++;
				}

			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("analysisType", GlobalConfig.START_USE);
			List<AnalysisRules> analysisRulesList = analysisDao
					.queryAnalysis(paramMap);
			if (analysisRulesList != null) {
				for (int i = 0; i < analysisRulesList.size(); i++) {
					AnalysisRules analysisRules = analysisRulesList.get(i);
					if (i == (analysisRulesList.size() - 1)) {
						rules.append(analysisRules.getAnalysisName());
					} else {
						rules.append(analysisRules.getAnalysisName() + ",");
					}
				}
			}

			/*
			 * strBuf.append("[public]"); strBuf.append("\n");
			 */
			/*
			 * try { InetAddress ind = InetAddress.getLocalHost();
			 * strBuf.append(" ip = " + ind.getHostAddress());//采集器IP地址 } catch
			 * (UnknownHostException e2) { e2.printStackTrace(); }
			 */

			if (collector.getCollectNetwork() == 1) {
				strBuf.append("\n");
				strBuf.append("[forward]");

				// forwardIP
				String centerIp = settingDao.queryByKey("centerIp");

				// forwardPort
				String centerPort = settingDao.queryByKey("centerPort");

				if (StringUtil.isNotEmpty(centerIp)) {
					strBuf.append("\n");
					strBuf.append("ip = " + centerIp);

				}

				if (StringUtil.isNotEmpty(centerPort)) {
					strBuf.append("\n");
					strBuf.append("dst_port = " + centerPort);
				}
				// upgradeIp
				String upgradeIp = settingDao.queryByKey("centerWwwUpIp");

				// upgradePort
				String upgradePort = settingDao.queryByKey("centerWwwUpPort");

				strBuf.append("\n");
				strBuf.append("[upgrade]");

				if (StringUtil.isNotEmpty(upgradeIp)) {

					strBuf.append("\n");
					strBuf.append("ip= " + upgradeIp);
				}
				if (StringUtil.isNotEmpty(upgradePort)) {
					strBuf.append("\n");
					strBuf.append("port =" + upgradePort);
				}
			}
			// 内网
			else {
				strBuf.append("\n");
				strBuf.append("[forward]");

				String centerNatIp = settingDao.queryByKey("centerNatIp");

				String centerNatPort = settingDao.queryByKey("centerNatPort");

				if (StringUtil.isNotEmpty(centerNatIp)) {

					strBuf.append("\n");
					strBuf.append("ip = " + centerNatIp);

				}
				if (StringUtil.isNotEmpty(centerNatPort)) {
					strBuf.append("\n");
					strBuf.append("dst_port = " + centerNatPort);
				}

				String centerNatUpIp = settingDao.queryByKey("centerNatUpIp");

				String centerNatUpPort = settingDao
						.queryByKey("centerNatUpPort");

				strBuf.append("\n");
				strBuf.append("[upgrade]");

				if (StringUtil.isNotEmpty(centerNatUpIp)) {
					strBuf.append("\n");
					strBuf.append("ip= " + centerNatUpIp);

				}
				if (StringUtil.isNotEmpty(centerNatUpPort)) {
					strBuf.append("\n");
					strBuf.append("port =" + centerNatUpPort);
				}
			}
			if (agentBuf.length() > 0) {
				strBuf.append(agentBuf);
			}
			strBuf.append("\n");

			if (rules.length() > 0) {
				strBuf.append("[box]");
				strBuf.append("\n");
				strBuf.append("enable_rules = " + rules);// 开启规则
				strBuf.append("\n");
			}
			strBuf.append("\n");
			strBuf.append("[syslog]");
			strBuf.append("\n");
			strBuf.append("port = " + collector.getCollectorLog());// 采集器syslog端口
			strBuf.append("\n");
			strBuf.append("[snmptrap]");
			strBuf.append("\n");
			strBuf.append("port = " + collector.getCollectorTrip());// 采集器snmptrap端口
			strBuf.append("\n");
			strBuf.append("\n");
			strBuf.append("[snmpwalk]");
			strBuf.append("\n");
			strBuf.append("oid = " + collector.getCollectorOid());
			strBuf.append("\n");
			if (host.length() > 0) {
				strBuf.append(host);
			}

			strBuf.append("\n");
			strBuf.append("interval = " + collector.getCollectorTime());

			if (assets.length() > 0) {
				strBuf.append("\n");
				strBuf.append("[asset]");
				strBuf.append("\n");
				strBuf.append(assets);
			}
			
			StringBuffer collectBuffer = new StringBuffer();
			// 写文件
			try {

				// String path =
				// ServletActionContext.getServletContext().getRealPath("");

				OutputStream os = new FileOutputStream(
						"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
								+ collector.getCollectorMac() + ".conf");

				OutputStream collectVersion = new FileOutputStream(
						"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
								+ collector.getCollectorMac() + ".version");

				DataOutputStream dos = new DataOutputStream(os);

				DataOutputStream dosVersion = new DataOutputStream(
						collectVersion);
				
				//判断过滤器的规则文件是否存在
				String filterPath = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/filter.version";
                
				File filter = new File(filterPath);
				
				String filterDate="";
				
				if(filter.exists())
				{
					filterDate= ReadAndWriteFileUtil.readFileContent(filterPath);
				}
				
				File version = new File(
						"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/soc.version");
				if (version.exists()) {
					/*
					 * InputStream is = new FileInputStream(
					 * "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version"
					 * ); byte[] b = new byte[100]; try { is.read(b); String
					 * socVersionInfo = new String(b, "utf-8"); if
					 * (StringUtil.isNotBlank(socVersionInfo)) { String
					 * socVersion = socVersionInfo.substring(0,
					 * socVersionInfo.indexOf("$"));
					 * 
					 * collectBuffer.append(socVersion); }
					 * 
					 * } catch (IOException e) { // TODO Auto-generated catch
					 * block e.printStackTrace(); }
					 */

					String content = ReadAndWriteFileUtil
							.readFileContent("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/soc.version");
					collectBuffer.append(content);
					/*
					 * try { is.close(); } catch (IOException e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */
				}

				collectBuffer.append("\n");
				collectBuffer.append("conf="
						+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
								.format((new Date())));
				collectBuffer.append("\n");
				collectBuffer.append("ctl=" + collector.getCollectorStatus());
				collectBuffer.append("\n");
				collectBuffer.append(filterDate);

				// OutputStream os = new FileOutput
				/*
				 * OutputStream os = new FileOutputStream(
				 * "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/"
				 * +collector.getCollectorMac()+".conf"); InputStream is = new
				 * FileInputStream(
				 * "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version"
				 * ); OutputStream sos = new FileOutputStream(
				 * "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version"
				 * ); DataOutputStream dos = new DataOutputStream(os); try {
				 * dos.writeBytes(new String(strBuf.toString().getBytes(),
				 * "UTF-8")); //dos.writeUTF(strBuf.toString()); byte[] b = new
				 * byte[100]; is.read(b); String socVersionInfo = new String(b,
				 * "utf-8"); if(StringUtil.isNotBlank(socVersionInfo)) {
				 * if(socVersionInfo.indexOf("soc_version=") != -1) { String
				 * socVersion =
				 * socVersionInfo.substring(socVersionInfo.indexOf("soc_version="
				 * )); StringBuffer str = new StringBuffer();
				 * str.append("conf_version=" + new
				 * SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format((new
				 * Date()))); str.append(socVersion);
				 * sos.write(str.toString().getBytes()); } } } catch
				 * (IOException e1) { e1.printStackTrace(); }
				 */
				try {
					
					dos.writeBytes(new String(strBuf.toString().getBytes(),
							"UTF-8"));

					dosVersion.writeBytes(new String(collectBuffer.toString()
							.getBytes(), "UTF-8"));

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					os.close();
					collectVersion.close();

				} catch (IOException e) {
					//System.out.println(e);
				}
			} catch (FileNotFoundException e) {
			}
		}
	}

	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	public SettingCollectorDao getSettingCollectorDao() {
		return settingCollectorDao;
	}

	public void setSettingCollectorDao(SettingCollectorDao settingCollectorDao) {
		this.settingCollectorDao = settingCollectorDao;
	}

	/** {@inheritDoc} */

	@Override
	public List<Asset> checkMac(Map map) {
		// TODO Auto-generated method stub
		return assetDao.checkMac(map);
	}

	/** {@inheritDoc} */

	@Override
	public void updateStatus(Map map) {
		// TODO Auto-generated method stub
		int temp = Integer.valueOf(map.get("assetId").toString());
		Asset asset = assetDao.queryByid(temp);
		map.put("vAIp", IpConvert.IpString(asset.getAssetIp()));

		assetDao.updateAssetValuesStatus(map);
		assetDao.updateRiskValuesStatus(map);
		assetDao.updateRiskVulnerabilityassessmentStatus(map);
		assetDao.updateStatus(map);
		// 重写配置文件
		createCollectorConfigFile(assetDao.queryByid(temp));
	}

	/** {@inheritDoc} */

	@Override
	public List<Map> export(Map map) {
		// TODO Auto-generated method stub
		List<Map> mapList = new ArrayList<Map>();

		List<Map> map1List = new ArrayList<Map>();
		try {
			mapList = assetDao.export(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		if (!mapList.isEmpty()) {
			for (Map map1 : mapList) {

				Long assetIp = (Long) map1.get("ASSET_IP");
				map1.remove("ASSET_IP");
				map1.put("ASSET_IP", IpConvert.IpString(assetIp));
				double riskvalue = assetRiskGroupDao.avgRiskValue(Long
						.parseLong(map1.get("ASSET_ID").toString()));
				double vaValue = assetRiskGroupDao.avgVAValue(IpConvert
						.IpString(assetIp));
				map1.put("RISK_THREATVALUE", riskvalue);
				map1.put("VAVULNERABILITYVALUE", vaValue);
				Long assetGateway = (Long) map1.get("ASSET_GATEWAY");
				map1.remove("ASSET_GATEWAY");
				map1.put("ASSET_GATEWAY", IpConvert.IpString(assetGateway));

				Long assetIpSegment = (Long) map1.get("ASSET_IP_SEGMENT");
				map1.remove("ASSET_IP_SEGMENT");
				map1.put("ASSET_IP_SEGMENT", IpConvert.IpString(assetIpSegment));

				map1List.add(map1);
			}

			return map1List;

		} else {
			return mapList;
		}
	}

	/** {@inheritDoc} */

	@Override
	public int selectByGroupId(Map map) {
		// TODO Auto-generated method stub
		return assetDao.selectByGroupId(map);
	}

	public AnalysisRulesDao getAnalysisDao() {
		return analysisDao;
	}

	public void setAnalysisDao(AnalysisRulesDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	/** {@inheritDoc} */

	@Override
	public void insertAsset(Asset asset) {
		// TODO Auto-generated method stub
		asset.setAssetCreateDateTime(new Date());
		asset.setAssetUpdateDateTime(new Date());
		asset.setAssetMac(asset.getAssetMac().toUpperCase());
		// 如果为syslog方式
		if (asset.getAssetUnName() == -1) {
			// 设置监控文件为空
			asset.setDirectorise("");
			// 设置监控目录为空
			asset.setFile("");
			// 设置特权命令为空
			asset.setAssetPrivilegeCommand("");
			// 设置版本为空
			asset.setVersion("");
			// 设置团体名为空
			asset.setOrganizationName("");
		}
		if (asset.getAssetUnName() == 0) {
			// 设置监控文件为空
			asset.setDirectorise("");
			// 设置监控目录为空
			asset.setFile("");
			// 设置特权命令为空
			asset.setAssetPrivilegeCommand("");
		}

		long assetId = assetDao.insertAsset(asset);
        
		Device devicetemp = new Device();
		    devicetemp.setDevice_asset_id(assetId);
		    devicetemp.setDevice_locationX("147.15");
		    devicetemp.setDevice_locationY("52.9");
		    devicetemp.setDevice_status(1);
		    devicetemp.setDevice_association_id("1");
		    DeviceCategory deviceCategory = new DeviceCategory();
		    deviceCategory.setDeviceCategory_id(1L);
		    devicetemp.setDeviceCategory(deviceCategory);
		    devicetemp.setDevice_ip(asset.getAssetMac());
		    devicetemp.setDevice_name(asset.getAssetName());
		    devicetemp.setDevice_describe(asset.getAssetMemo());
		    devicetemp.setDevice_mark(0L);
		    devicetemp.setDevice_community_name("public");
		    try
		    {
		      this.deviceDao.addObject(devicetemp);
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		try {
			// 为资产风险添加风险值。
			AssetRiskEvaluation assetRiskValue = new AssetRiskEvaluation();
			assetRiskValue.setAssetName(asset.getAssetName());
			assetRiskValue.setRelAssetId(assetId);
			assetRiskValue.setAssetValueUpdateTime(new Date());

			assetRiskEvaluationManager
					.addAssetRiskEvaluation(assetRiskValue);
		} catch (Exception e) {
			log.info("为资产赋值失败");
		}
	}

	@Override
	public int queryVaNo(Map map) {
		return assetDao.queryVaNo(map);
	}

	public AssetRiskEvaluationService getAssetRiskEvaluationManager() {
		return assetRiskEvaluationManager;
	}

	public void setAssetRiskEvaluationManager(
			AssetRiskEvaluationService assetRiskEvaluationManager) {
		this.assetRiskEvaluationManager = assetRiskEvaluationManager;
	}

	@Override
	public List<Asset> queryAssetByGroupId(Map map) {
		return assetDao.queryAssetByGroupId(map);
	}

	@Override
	public void updateIssued(Asset a) {
		// TODO Auto-generated method stub
		assetDao.updateIssued(a);
	}

	@Override
	public void updateAnalysisRules(Map map) {
		// TODO Auto-generated method stub
		assetDao.updateAnalysisRules(map);
	}

	@Override
	public List<Asset> queryAllAssetByUserId(long id) {
		// 资产组业务处理类

		Map<String, Object> map = new HashMap<String, Object>();
		// 调用递归方法 查询出这个资产组和子组所有资产 的id 1,2,3,4,5
		String asserIds = assetGroupManager.getAllGroupIdByID(id);
		map.put("assetGroupId", asserIds);

		return this.queryAssetByGroupId(map);
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public List<Asset> queryByAssetSupportDevice(Map map) {

		return assetDao.query(map);
	}

	@Override
	// 修改，如果用户关联的资产组没有对应的资产，就给资产id赋0
	public String queryIDSByUser(long id) {

		Map map2 = new HashMap();
		if (id == 1) {

		} else {
			String assetGroupIdss = assetGroupManager.getAllGroupIdByID(id);
			map2.put("assetGroupId", assetGroupIdss);
		}

		StringBuffer sbf = new StringBuffer();
		int falg = 0;
		List<Integer> list = assetDao.queryAssetIdWorkOrder(map2);
	//List<Asset> listAsset = assetDao.query(map2);
		if (list.size() <= 0) {
			sbf.append("0");
		} else {
			for (int i : list) {
				if (falg == 0) {
					sbf.append(i);
				} else {
					sbf.append("," + i);
				}
				falg++;
			}
		}
		return sbf.toString();
	}

	@Override
	public String queryCollectorByUser(long id) {
		Map map2 = new HashMap();
		if (id == 1) {

		} else {
			String assetGroupIdss = assetGroupManager.getAllGroupIdByID(id);
			map2.put("assetGroupId", assetGroupIdss);
		}

		StringBuffer sbf = new StringBuffer();
		int falg = 0;
		List<Asset> listAsset = assetDao.query(map2);

		for (Asset asset : listAsset) {
			if (falg == 0) {
				sbf.append(asset.getAssetCollectorId());
			} else {
				sbf.append("," + asset.getAssetCollectorId());
			}
			falg++;
		}
		return sbf.toString();
	}

	@Override
	public void updateAssetVavulNerabilityValue(long assetId, Map map) {

		assetDao.updateAssetVavulNerabilityValue(map);
		Map map1 =new HashMap();
    	map1.put("assetId", assetId);
    	//根据资产id查询所管理的资产组
    	List<AssetGroup> assetGroup = assetGroupDao.queryGroupByAssetId(map1);
    	//迭代,得到每一个资产组
    	if(assetGroup.size()>0){
    		for (AssetGroup ag : assetGroup) {
    			AssetGroup ag1 = new AssetGroup();
    			Map map2 =new HashMap();
    			//如果是全部资产则不拼查询条件
    			if(ag.getAssetGroupId()!=1){
    				map2.put("assetGroupId",ag.getAssetGroupId());
    			}
    			//得到资产组所管理资产下所有资产的脆弱性
    			String vAVulnerabilityValue = assetRiskGroupDao.queryByName1(map2);
    			//如果威胁值为null就手动赋值0.0
    			if(StringUtil.isNotEmpty(vAVulnerabilityValue)){
    				ag1.setvAVulnerabilityValue(this.getStringtoNum(vAVulnerabilityValue));
    			}else{
    				ag1.setRiskThreatValue(0.0);
    			}
    			ag1.setAssetValue(ag.getAssetValue());
    			ag1.setRiskThreatValue(ag.getRiskThreatValue());
    			
    			ag1.setAssetGroupId(ag.getAssetGroupId());
    			//修改资产组的威胁值、脆弱性值、资产值
    			assetRiskGroupDao.updateGrouprisk(ag1);
			}
    	}
    	
	}
	/**
     * 取double类型小数点后后两位，第三位四舍五入
     * <功能详细描述>
     * @param String string
     * @return double
     */
	private double getStringtoNum(String str){
		return new BigDecimal(Double.parseDouble(str)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 根据IP查询资产是否存在
	 */
	@Override
	public Asset queryAssetByIp(long ip) {
		// TODO Auto-generated method stub
		return assetDao.queryAssetByIp(ip);
	}
	
	
	public AssetGroupDao getAssetGroupDao() {
		return assetGroupDao;
	}

	public void setAssetGroupDao(AssetGroupDao assetGroupDao) {
		this.assetGroupDao = assetGroupDao;
	}

	public CommonDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(CommonDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	public String queryCategoryByFatherId(long deviceId) {
		// TODO Auto-generated method stub
		return assetDao.queryCategoryByFatherId(deviceId);
	}

	@Override
	public String queryAssetIdByCategoryName(Map map) {
		return assetDao.queryAssetIdByCategoryName(map);
	}
	
	
}
