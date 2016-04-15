package com.soc.service.systemsetting.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.struts2.ServletActionContext;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.systemsetting.SettingCollectorDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.systemsetting.rules.AnalysisRulesDao;
import com.soc.dao.systemsetting.rules.AssociationRulesDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.CpuData;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SettingCollectorService;
import com.util.IpConvert;
import com.util.ReadAndWriteFileUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <采集器的配置> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2012-11-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SettingCollectorServiceImpl extends BaseServiceImpl implements
		SettingCollectorService {
	private SettingCollectorDao settingCollectorDao;

	private AssetDao assetDao;

	private SettingDao settingDao;

	private AnalysisRulesDao analysisDao;

	private List<Collector> collectorList;

	private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片

	private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片

	/**
	 * {保存数据}
	 */

	@Override
	public void setCollector(Collector collectorData) {
		settingCollectorDao.setCollector(collectorData);
		GlobalConfig.collectorList.add(collectorData);
		createCollectorConfigFile(collectorData);
	}

	/**
	 * {查询数据}
	 */

	@Override
	public List<Collector> queryCollector() {
		return settingCollectorDao.queryCollector();
	}

	/**
	 * {查询树}
	 */

	@Override
	public List<Collector> queryCollectorTree(Map<String, Integer> treeDataId) {
		return settingCollectorDao.queryCollectorTree(treeDataId);
	}

	/**
	 * {更新数据}
	 */

	@Override
	public void updateId(Collector updateData) {
		Collector collector = new Collector();

		collector = settingCollectorDao.queryById(updateData.getCollectorId());
		updateData.setCollectorReceiveEvents(collector
				.getCollectorReceiveEvents());
		updateData.setCollectorIsOnline(collector.getCollectorIsOnline());
		updateData.setCollectorIsSafe(collector.getCollectorIsSafe());

		GlobalConfig.collectorList.remove(collector);
		settingCollectorDao.updateId(updateData);
		collector = settingCollectorDao.queryById(updateData.getCollectorId());
		GlobalConfig.collectorList.add(collector);
		// 生成配置文件
		createCollectorConfigFile(updateData);
	}

	@Override
	public void updateById(ConcurrentLinkedQueue<Collector> updateData) {
		settingCollectorDao.updateById(updateData);
	}

	/**
	 * {删除数据}
	 */
	@Override
	public void delId(int collectorId) {
		Collector collector = new Collector();

		collector = settingCollectorDao.queryById(collectorId);

		settingCollectorDao.delId(collectorId);

		GlobalConfig.collectorList.remove(collector);

		// 将该采集器的配置文件删除
		removeCollectorConfigFile(collector);

		settingCollectorDao.delId(collectorId);
	}

	@Override
	public int selectAsset(int collectorId) {
		Collector collector = new Collector();
		collector = settingCollectorDao.queryById(collectorId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("collectorMac", collector.getCollectorMac());
		int assetNo = assetDao.count(map);
		return assetNo;
	}

	/**
	 * {分页}
	 */
	@Override
	public SearchResult queryPage(Map map, Page page) {
		int rowsCount = settingCollectorDao.countPage(map);
		page.setTotalCount(rowsCount);
		List<Collector> list = settingCollectorDao.queryPage(map,
				page.getStartIndex(), page.getPageSize());

		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/*
	 * StringBuffer treeBuff;
	 * 
	 * @Override public String queryTree(String objectBath) { treeBuff = new
	 * StringBuffer(); Map<String, Long> map = new HashMap<String, Long>();
	 * map.put("parentId", Long.valueOf(0));
	 * 
	 * List<Collector> queryEventsGroupList =
	 * settingCollectorDao.queryCollector(); treeBuff.append("<ul>");
	 * treeBuff.append("<li id=\"query_"+3+"\"><a href=\"javascript:action('"+3+
	 * "','img');\"><img src=\""
	 * +objectBath+"/images/"+PICTURE_NAME_CLOSE+"\" id=\"img_query_"
	 * +3+"\"></a>&nbsp;<a href=\"javascript:void(0);\" ondblclick=\"action('"
	 * +3+"','img')\"><span class=\"eventext\">"+"外联采集器"+"</span></a>"); for
	 * (Collector collector : queryEventsGroupList) {
	 * treeBuff.append("<ul  class=\"disply\">");
	 * treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:linkTo('"
	 * +collector
	 * .getCollectorId()+"');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_SEED
	 * +
	 * "\" ></a>&nbsp;<a href=\"javascript:linkTo('"+collector.getCollectorId()+
	 * "');\"><span class=\"eventext\">"
	 * +collector.getCollectorBasic()+"</span></a>"); treeBuff.append("</li>");
	 * treeBuff.append("</ul>"); } return treeBuff.toString(); }
	 */

	StringBuffer treeBuffs;

	public SettingCollectorDao getSettingCollectorDao() {
		return settingCollectorDao;
	}

	public void setSettingCollectorDao(SettingCollectorDao settingCollectorDao) {
		this.settingCollectorDao = settingCollectorDao;
	}

	@Override
	public String queryCollectorTree(String objectBath) {
		treeBuffs = new StringBuffer();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("parentId", Long.valueOf(0));

		List<Collector> queryCollectorList = settingCollectorDao
				.queryCollectorTree();

		treeBuffs.append("<ul>");
		treeBuffs
				.append("<li id=\"query_img_query_collector3\"><a href=\"javascript:action('"
						+ 3
						+ "','img_query_collector');\"><img src=\""
						+ objectBath
						+ "/images/"
						+ PICTURE_NAME_CLOSE
						+ "\" id=\"img_query_collector"
						+ 3
						+ "\"></a>&nbsp;<a href=\"javascript:void(0);\" ondblclick=\"action('"
						+ 3
						+ "','img_query_collector')\"><span class=\"eventext\">"
						+ "监控域" + "</span></a>");
		for (Collector collector : queryCollectorList) {
			treeBuffs.append("<ul  class=\"disply\">");
			treeBuffs
					.append("<li class=\"eventleft\"><a href=\"javascript:linkTo('"
							+ 3
							+ "','"
							+ collector.getCollectorId()
							+ "');\"><img src=\""
							+ objectBath
							+ "/images/"
							+ PICTURE_NAME_SEED
							+ "\" ></a>&nbsp;<a href=\"javascript:linkTo('"
							+ 3
							+ "','"
							+ collector.getCollectorId()
							+ "');\"><span class=\"eventext\">"
							+ collector.getCollectorBasic() + "</span></a>");
			treeBuffs.append("</li>");
			treeBuffs.append("</ul>");
		}
		treeBuffs.append("</li>");
		treeBuffs.append("</ul>");
		return treeBuffs.toString();
	}

	public Collector queryById(long collectorId) {
		return settingCollectorDao.queryById(collectorId);
	}

	/**
	 * <建立采集器的配置文件> <功能详细描述>
	 * 加入静态锁
	 * 
	 * @param collector
	 *            Collector
	 * @see [类、类#方法、类#成员]
	 */
	
	public synchronized void  createCollectorConfigFile(Collector collector) {
		
			StringBuffer strBuf = new StringBuffer();
			StringBuffer agentBuf = new StringBuffer();
			StringBuffer host = new StringBuffer();
			StringBuffer assets = new StringBuffer();
			StringBuffer rules = new StringBuffer();
			int j = 0;

			int m = 0;
			// 查询当前采集器的所有资产
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("assetCollectorMac", collector.getCollectorMac());
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
				// 由conf改为fuse
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
					// System.out.println(e);
				}
			} catch (FileNotFoundException e) {
			}
		
	}

	/**
	 * <删除配置文件> <删除采集器时，删除对应的配置文件>
	 * 
	 * @param collector
	 *            Collector
	 * @see [类、类#方法、类#成员]
	 */
	public void removeCollectorConfigFile(Collector collector) {
		File file = new File(
				"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
						+ collector.getCollectorMac() + ".conf");
		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
		}

		File file2 = new File(
				"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
						+ collector.getCollectorMac() + ".version");
		if (file2 != null) {
			if (file2.exists()) {
				file2.delete();
			}
		}
	}

	@Override
	public void updateColloectorCount(Map<String, Long> map) {
		if (!map.isEmpty()) {
			Iterator iter = map.keySet().iterator();

			while (iter.hasNext()) {
				// Map.Entry entry = (Map.Entry)iter.next();
				Object key = iter.next();
				Object val = map.get(key);

				String sql = "update \"tbl_system_collector\"  SET \"COLLECTOR_RECEIVE_EVENTS_COUNT\"="
						+ val + " WHERE \"COLLECTOR_MAC\"='" + key + "'";

				// System.out.println(sql);

				settingCollectorDao.updateCollectorCount(sql);
			}
		}

	}

	@Override
	public void updateCollectorIsOnline(Map<String, Integer> map) {
		// TODO Auto-generated method stub

		List<String> temp = new ArrayList<String>();
		if (!map.isEmpty()) {
			Iterator iter = map.keySet().iterator();

			while (iter.hasNext()) {
				Object key = iter.next();
				Object val = map.get(key);

				String sql = "update \"tbl_system_collector\"  SET \"COLLECTOR_IS_ONLINE\"="
						+ val
						+ ", \"COLLECTOR_IS_SAFE\"="
						+ val
						+ " WHERE \"COLLECTOR_MAC\"='" + key + "'";

				settingCollectorDao.updateCollectorIsOnLine(sql);

				// System.out.println("刘延旭采集器mac---"+key);

				// GlobalConfig.collectorIsOnline.remove(key.toString());

				// GlobalConfig.collectorIsOnline.put(key.toString(), 0);
				temp.add(key.toString());

			}

			// GlobalConfig.collectorIsOnline.clear();

			// System.out.println("采集器清空");

			for (int i = 0; i < temp.size(); i++) {
				GlobalConfig.collectorIsOnline.put(temp.get(i), 0);

				// System.out.println("更新采集器状态为0....");
			}
		}

	}

	@Override
	public void updateLoggerStatus(Collector collectorList) {
		settingCollectorDao.updateLoggerStatus(collectorList);

		// 根据Id查询采集器对象
		collectorList = settingCollectorDao.queryById(collectorList
				.getCollectorId());

		// 更改采集器的配置文件
		updateCollectVersionFile(collectorList);
		
	}

	public void updateCollectVersionFile(Collector collector) {
		try {
			String path = ServletActionContext.getServletContext().getRealPath(
					"");

			InputStream is = new FileInputStream(
					"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
							+ collector.getCollectorMac() + ".version");

			byte[] b = new byte[100];

			try {
				is.read(b);
				
				String socVersionInfo = new String(b, "utf-8");

				if (StringUtil.isNotBlank(socVersionInfo)) {
					if (socVersionInfo.indexOf("ctl=") != -1) {
						String socVersion1 = socVersionInfo.substring(0,
								socVersionInfo.indexOf("ctl="));

						// String socVersion2 =
						// socVersionInfo.substring(socVersionInfo.indexOf("ctl="));
						StringBuffer str = new StringBuffer();
						str.append(socVersion1);

						str.append("ctl=" + collector.getCollectorStatus());

						OutputStream sos = new FileOutputStream(
								"/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
										+ collector.getCollectorMac()
										+ ".version");

						// str.append(socVersion2);
						// //System.out.println();
						sos.write(str.toString().getBytes());
						sos.close();
					}

				}

				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	@Override
	public List<Collector> selectMac(String addressName) {
		return settingCollectorDao.selectMac(addressName);
	}

	public AnalysisRulesDao getAnalysisDao() {
		return analysisDao;
	}

	public void setAnalysisDao(AnalysisRulesDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	@Override
	public List<Collector> queryCollectors(Map map) {

		return settingCollectorDao.queryCollectors(map);
	}

	/** {@inheritDoc} */

}