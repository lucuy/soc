package com.soc.webapp.quartz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.terracotta.agent.repkg.de.schlichtherle.io.FileInputStream;

import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.EventsUserLog;
import com.soc.model.events.SummaryEvents;
import com.soc.service.asset.AssetService;
import com.soc.service.events.EventsService;
import com.soc.service.events.EventsUserLogService;
import com.soc.service.events.SummaryEventsService;
import com.util.IpConvert;

/**
 * 或许双因素认证事件 添加到soc中
 * 
 * @author lc
 * 
 */
public class QueryFtotp {

	// private FtotpService FtotpManager = new FtotpServiceImpl();
	private EventsUserLogService eventsUserLogManager; // 远程Oracle数据库连接对象
	private EventsService eManager; // 当天事件表管理
	private SummaryEventsService sManager; // 小表管理
	private AssetService aManager; // 资产管理
	private Properties p = new Properties();
	Events event = null;
	SummaryEvents sEvents = null;
	HashMap<String, Object> map = null;
	Asset asset = null;
	ConcurrentLinkedQueue<Events> events = new ConcurrentLinkedQueue<Events>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	String identification = "";
	File file = null;
	FileOutputStream fos = null;
	// 格尔统一认证系统的IP
	private String devIP = null;

	public void queryFtotpAndSaveEvents() {

		// 加载properties文件
		this.initPropertiesReader();

		long numId = Long.parseLong(p.getProperty("NUM.ID"));
		// 从配置文件中读取格尔统一认证系统的IP
		devIP = p.getProperty("DEV.IP");

		// 根据ip查询统一账户认证系统的资产是否存在，当不存在时不执行任何操作
		asset = aManager.queryAssetByIp(IpConvert.iPTransition(devIP));

		// 查询格尔统一认证系统的日志
		List<EventsUserLog> list = eventsUserLogManager
				.queryAllUserLogByNumId(numId);

		if (list != null && list.size() > 0) {

			if (asset != null) {
				for (EventsUserLog ftotp : list) {
					event = new Events();
					sEvents = new SummaryEvents();
					map = new HashMap<String, Object>();

					event.setName("格尔统一认证系统");
					event.setSendTime(ftotp.getOperateTime());
					event.setReceptTime(new Date().getTime());
					event.setSuserName(ftotp.getUserName());

					// 操作类型
					int operateType = ftotp.getOperateType();
					switch (operateType) {

					case 0: {
						event.setAction("登陆");
						break;
					}
					case 1: {
						event.setAction("查询");
						break;
					}
					case 2: {
						event.setAction("新增");
						break;
					}
					case 3: {
						event.setAction("修改");
						break;
					}
					case 4: {
						event.setAction("删除");
						break;
					}

					}

					// event.setAction("登录系统");

					event.setAggregatedCount(1);
					if (ftotp.getOperateResult() == 1) {
						event.setResult("成功");
						event.setPriority(3);
						event.setOriPriority("3");
						event.setType("160001");

						sEvents.setEventsLevel(3);
						sEvents.setEventsType("160001");
						sEvents.setEventsTableType(0);
					} else {
						event.setResult("失败");
						event.setPriority(4);
						event.setOriPriority("4");
						event.setType("160002");

						sEvents.setEventsLevel(4);
						sEvents.setEventsType("160002");
						sEvents.setEventsTableType(1);
					}
					// event.setAggregatedCount(1);
					event.setCateGory("16");

					// 属性值待定
					event.setsAddr(IpConvert.iPTransition(ftotp.getTerminalID()));
					// event.setDevType("");
					// event.setDevVendor("");
					// event.setProgramType("");
					if (asset != null) {
						event.setAssetId(asset.getAssetId());
						event.setCustoms5(asset.getAssetName());
					}

					event.setdAddr(IpConvert.iPTransition(devIP));
					event.setDevAdd(IpConvert.iPTransition(devIP));
					event.setDevName("格尔统一认证系统");
					event.setDevVendor("格尔");

					event.setCustoms1(sdf1.format(new Date()));
					event.setDevproduct("格尔统一认证系统");
					identification = sdf1.format(new Date()) + ftotp.getNumID();
					event.setIdentification(Long.parseLong(identification));
					event.setDevType("/4/Security08");
					event.setTableName("tbl_" + sdf1.format(new Date()));
					event.setCustoms4(ftotp.getOperateCondition());

					events.add(event);

					// SummaryEvents待确定数据
					if (asset != null) {
						sEvents.setAssetId((int) asset.getAssetId());
						sEvents.setAssetName(asset.getAssetName());
					}

					sEvents.setEventsSourceAdd(IpConvert.iPTransition(ftotp
							.getTerminalID()));
					// sEvents.setEventsSourceAddT("");
					// sEvents.setEventsProgram("");
					// sEvents.setEventsProgramtype("");

					sEvents.setEventsTargetAdd(IpConvert.iPTransition(devIP));
					sEvents.setEventsSource("格尔统一认证系统");
					sEvents.setEventsDevvendor("格尔统一认证");
					sEvents.setEventsNumber(1);
					sEvents.setEventsTime(new Date(ftotp.getOperateTime()));
					sEvents.setEventsPutTime(new Date().getTime());
					sEvents.setEventsTargetAddT(devIP);
					sEvents.setEventsLogIdentification(Long
							.parseLong(identification));
					sEvents.setEventsName("格尔统一认证系统日志");
					sEvents.setEventsDevProduct("格尔统一认证系统");
					sEvents.setEventsDevType("/4/Security08");
					sEvents.setRelLogTableName(sdf1.format(new Date()));

					// 增加事件追溯表数据
					map.put("identification", Long.parseLong(identification));
					map.put("msg", ftotp.getOperateCondition());

					if (asset != null) {
						map.put("assetId", asset.getAssetId());
						map.put("assetName", asset.getAssetName());
					}

					map.put("tableName", "tbl_" + sdf1.format(new Date())
							+ "_original_log");
					// 将原始日志放入原始日志队列
					GlobalConfig.rawLogQueue.add(map);
					GlobalConfig.temporaryRawLogQueue
							.add(GlobalConfig.rawLogQueue.poll());
					eManager.insertEvents(events);
					// 入 认证系统小表
					sManager.insertSummaryEvents(
							"insert.authentication_system_events", sEvents);
					eManager.insertOriginLog(GlobalConfig.temporaryRawLogQueue);
				}

				int listSize = list.size();
				EventsUserLog event = list.get(listSize - 1);
				// 将NumID数据保存到oracleNumIdRecord.properties文件中
				String numIdFresh = String.valueOf(event.getNumID());
				p.setProperty("NUM.ID", numIdFresh);

				try {
					fos = new FileOutputStream(file);
					p.store(fos, null);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

	// 读取properties文件
	private void initPropertiesReader() {
		try {
			java.net.URL url = this.getClass().getResource(
					"/prop/oracleNumIdRecord.properties");
			file = new File(url.toURI());
			p.load(new FileInputStream(file));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void queryUnionAdmin() { URL url; URLConnection conn;
	 * 
	 * Document doc = null; StringBuffer data = new StringBuffer();
	 * 
	 * try { url = new URL("http://unionadmin.guoshi.com/logappapi.php?date=" +
	 * sdf2.format(new Date()));
	 * 
	 * System.out.println(url);
	 * 
	 * conn = url.openConnection(); conn.setDoOutput(true); InputStream in =
	 * null; in = conn.getInputStream(); String rLine = null; BufferedReader
	 * bReader = new BufferedReader(new InputStreamReader( in, "utf-8"));
	 * PrintWriter pw = null; while ((rLine = bReader.readLine()) != null) {
	 * data.append(rLine); }
	 * 
	 * System.out.println("接收到的xml" + data); // 将接收到的字符串进行xml解析 SAXReader rd =
	 * new SAXReader(); // 将字符串转为XML doc =
	 * DocumentHelper.parseText(data.subSequence(0, data.length() -
	 * 6).toString());
	 * 
	 * } catch (MalformedURLException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } catch (DocumentException e) {
	 * e.printStackTrace(); } Element rootElt = doc.getRootElement(); // 获取根节点
	 * 
	 * System.out.println(rootElt.getName());
	 * 
	 * Date afterDate = new Date(new Date().getTime() - 300000); Date nowDate =
	 * new Date();
	 * 
	 * if (rootElt.getName() != "" || rootElt.getName() != null) {
	 * 
	 * Iterator iter = rootElt.elementIterator("log"); // 获取根节点下的子节点account
	 * 
	 * // 遍历account节点 while (iter.hasNext()) {
	 * 
	 * event = new Events(); sEvents = new SummaryEvents(); map = new
	 * HashMap<String, Object>(); Element recordEle = (Element) iter.next();
	 * 
	 * try { if ((sdf.parse(recordEle.elementTextTrim("time")).getTime() <=
	 * afterDate .getTime()) || (sdf.parse(recordEle.elementTextTrim("time"))
	 * .getTime() > nowDate.getTime())) { continue; } else {
	 * event.setName("统一后台认证系统日志"); event.setDevVendor("统一认证"); try {
	 * event.setSendTime(sdf.parse( recordEle.elementTextTrim("time"))
	 * .getTime()); } catch (ParseException e) { e.printStackTrace(); }
	 * event.setReceptTime(new Date().getTime());
	 * event.setSuserName(recordEle.elementTextTrim("user"));
	 * event.setAction("登录系统"); event.setAggregatedCount(1); // if
	 * (ftotp.getLogcontent().substring(0, // 9).equals("AUTH_SUCC")) {
	 * event.setResult("成功"); event.setPriority(3); event.setOriPriority("3");
	 * event.setType("160001");
	 * 
	 * sEvents.setEventsLevel(3); sEvents.setEventsType("160001");
	 * sEvents.setEventsTableType(0);
	 * 
	 * }else { event.setResult("失败"); event.setPriority(4);
	 * event.setOriPriority("4"); event.setType("160002");
	 * 
	 * sEvents.setEventsLevel(4); sEvents.setEventsType("160002");
	 * sEvents.setEventsTableType(1); }
	 * 
	 * event.setAggregatedCount(1); event.setCateGory("16");
	 * 
	 * // 属性值待定 event.setsAddr(IpConvert.iPTransition(recordEle
	 * .elementTextTrim("ip"))); // event.setDevType(""); //
	 * event.setDevVendor(""); // event.setProgramType(""); if (asset != null) {
	 * event.setAssetId(asset.getAssetId());
	 * event.setCustoms5(asset.getAssetName()); }
	 * 
	 * event.setdAddr(IpConvert.iPTransition("10.1.4.74"));
	 * event.setDevAdd(IpConvert.iPTransition("10.1.4.74"));
	 * event.setDevName("统一后台认证系统");
	 * 
	 * event.setCustoms1(sdf1.format(new Date()));
	 * event.setDevproduct("统一后台认证系统"); identification = sdf1.format(new Date())
	 * + recordEle.elementTextTrim("logid");
	 * event.setIdentification(Long.parseLong(identification));
	 * event.setDevType("/4/Security08"); event.setTableName("tbl_" +
	 * sdf1.format(new Date())); event.setCustoms4("登录用户" +
	 * recordEle.elementTextTrim("user") + "登录系统" +
	 * recordEle.elementTextTrim("app") + "时间" +
	 * recordEle.elementTextTrim("time") + "登录Ip:" +
	 * recordEle.elementTextTrim("ip"));
	 * 
	 * events.add(event);
	 * 
	 * // SummaryEvents待确定数据 if (asset != null) { sEvents.setAssetId((int)
	 * asset.getAssetId()); sEvents.setAssetName(asset.getAssetName()); }
	 * sEvents.setEventsSourceAdd(IpConvert
	 * .iPTransition(recordEle.elementTextTrim("ip"))); //
	 * sEvents.setEventsSourceAddT(""); // sEvents.setEventsProgram(""); //
	 * sEvents.setEventsProgramtype("");
	 * 
	 * sEvents.setEventsTargetAdd(IpConvert .iPTransition("10.1.4.74"));
	 * sEvents.setEventsSource("统一后台认证系统日志");
	 * sEvents.setEventsDevvendor("统一认证"); sEvents.setEventsNumber(1); try {
	 * sEvents.setEventsTime(sdf.parse(recordEle .elementTextTrim("time"))); }
	 * catch (ParseException e) { e.printStackTrace(); }
	 * sEvents.setEventsPutTime(new Date().getTime());
	 * sEvents.setEventsTargetAddT("10.1.4.74");
	 * sEvents.setEventsLogIdentification(Long .parseLong(identification));
	 * sEvents.setEventsName("统一后台认证系统日志");
	 * sEvents.setEventsDevProduct("统一后台认证系统");
	 * sEvents.setEventsDevType("/4/Security08");
	 * sEvents.setRelLogTableName(sdf1.format(new Date()));
	 * 
	 * // 增加事件追溯表数据 map.put("identification", Long.parseLong(identification));
	 * map.put("msg", "登录用户" + recordEle.elementTextTrim("user") + "登录系统" +
	 * recordEle.elementTextTrim("app") + "时间" +
	 * recordEle.elementTextTrim("time") + "登录Ip:" +
	 * recordEle.elementTextTrim("ip"));
	 * 
	 * if (asset != null) { map.put("assetId", asset.getAssetId());
	 * map.put("assetName", asset.getAssetName()); }
	 * 
	 * map.put("tableName", "tbl_" + sdf1.format(new Date()) + "_original_log");
	 * // 将原始日志放入原始日志队列 GlobalConfig.rawLogQueue.add(map);
	 * GlobalConfig.temporaryRawLogQueue .add(GlobalConfig.rawLogQueue.poll());
	 * 
	 * eManager.insertEvents(events); sManager.insertSummaryEvents(
	 * "insert.safety_device_events", sEvents);
	 * eManager.insertOriginLog(GlobalConfig.temporaryRawLogQueue); } } catch
	 * (ParseException e) { e.printStackTrace(); }
	 * 
	 * // recordEle.elementTextTrim("user"); //
	 * recordEle.elementTextTrim("logid"); // recordEle.elementTextTrim("app");
	 * // recordEle.elementTextTrim("time");
	 * 
	 * } }
	 * 
	 * }
	 */

	public EventsService geteManager() {
		return eManager;
	}

	public void seteManager(EventsService eManager) {
		this.eManager = eManager;
	}

	public SummaryEventsService getsManager() {
		return sManager;
	}

	public void setsManager(SummaryEventsService sManager) {
		this.sManager = sManager;
	}

	public AssetService getaManager() {
		return aManager;
	}

	public void setaManager(AssetService aManager) {
		this.aManager = aManager;
	}

	public EventsUserLogService getEventsUserLogManager() {
		return eventsUserLogManager;
	}

	public void setEventsUserLogManager(
			EventsUserLogService eventsUserLogManager) {
		this.eventsUserLogManager = eventsUserLogManager;
	}

	public SummaryEvents getsEvents() {
		return sEvents;
	}

	public void setsEvents(SummaryEvents sEvents) {
		this.sEvents = sEvents;
	}

}
