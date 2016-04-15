package com.soc.model.conf;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.sf.ehcache.CacheManager;

import org.productivity.java.syslog4j.SyslogIF;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.asset.Asset;
import com.soc.model.events.Events;
import com.soc.model.events.Filter;
import com.soc.model.events.FilterByGroup;
import com.soc.model.events.SummaryEvents;
import com.soc.model.monitor.MonitorAlarm;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.SysInfo;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetService;
import com.util.drools.Drools;

public class GlobalConfig
{
    // Debug flag
    public static final boolean SYSTEM_FLAG_DEBUG = true;
    
    // CTX
    public static String ctx;
    
    // 漏扫IP
    public static String address;
    
    // 日期的格式化对象
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    // 通信配置
    // 线程池最大数量为11，一个请求监听线程，十个数据监听线程
    public static final int MAX_THREADPOOL_NUMBER = 40;
    
    // 状态配置
    // 锁定状态
    public static final int STATUS_LOCKED = 0;
    
    // 激活状态
    public static final int STATUS_ACTIVATE = 1;
    
    // 注销状态
    public static final int IS_DELETE = -1;
    
    //未删除
    public static final int NOT_DELETE = 0;
    
    // 显示标记
    public static final int DISPLAY_SIGN = 1;
    
    // 隐藏标记
    public static final int HIDEN_SIGN = 0;
    
    // 创建标识
    public static final int CREATE_SIGN = 0;
    
    // 标识不是所有
    public static final int IS_NOT_ALL = 0;
    
    // 标识所有
    public static final int IS_ALL = 1;
    
    // 临时标识
    public static final float IS_TEMP = -2;
    
    // 与采集器通信的端口
    public static int MONITOR_LISTENER_PORT;
    
    // 存储agent状态的缓存存储Element的最大个数
    public static final int CACHE_AGENT_NUM = 60;
    
    // 存储每秒接收事件的缓存存储Element的最大个数
    public static final int CACHE_EVENT_NUM = 3600;
    
    public static CacheManager cacheManager;
    
    // 解析后的数据
    public static final int ANALYTIC_FINISHED = 1;
    
    // 解析失败数据
    public static final int ANALYTIC_FAILED = 4;
    
    // 原始日志
    public static final int RAW_DATA = 2;
    
    // agent状态
    public static final int MONITOR_DATA = 3;
 // windiws 进程标示
 	public static final int WIN_PROGRESS = 6;
 	
 	// linux 进程标示
 	public static final int LINUX_PROGRESS = 13;
 	
 	// win 服务标示
 	public static final int WIN_SERVICE = 8;
 	
 	// linux 服务标示
 	public static final int LINUX_SERVICE = 15;
 	// win 软件标示
 	public static final int WIN_SOFT = 16;
    // 采集器版本信息
    public static final int COLLECTOR_VERSION = 5;
    
    public static int count;
    public static int count1;
    
    // 数据格式错误
    public static final int FORMAT_THE_WRONG_DATA  = -1;
    
    // 事件过滤规则List
    public static List<Filter> filterList;
    
    // 事件过滤规则List
    public static List<FilterByGroup> filterByGroupList;
    
    // 时间过滤规则的所有运算符
    public static List<String> operatorList;
    
    // 确定事件分类的属性集合
    public static Map<String, String> eventAttributes;
    
    // 存储接收的日志的队列
    public static ConcurrentLinkedQueue<List<String>> logQueue = new ConcurrentLinkedQueue<List<String>>();
    
    public static final int listMaxNum = 400;
    
    public static final long storeMessageInterval = 2000;
    
    public static long firstStoreMessageTime;
    
    // 存储事件的队列
    public static ConcurrentLinkedQueue<Events> eventsQueue = new ConcurrentLinkedQueue<Events>();
    
    // 存储事件的临时队列，供批处理使用
    public static ConcurrentLinkedQueue<Events> temporaryEventsQueue = new ConcurrentLinkedQueue<Events>();
    
    // 存储概要事件的队列
    public static ConcurrentLinkedQueue<SummaryEvents> summaryEventsQueue = new ConcurrentLinkedQueue<SummaryEvents>();
    
    // 存储概要事件的临时队列，供批处理使用
    public static ConcurrentLinkedQueue<SummaryEvents> temporarySummaryEventsQueue = new ConcurrentLinkedQueue<SummaryEvents>();
    
    // 存储原始日志的队列
    public static ConcurrentLinkedQueue<Map<String, Object>> rawLogQueue = new ConcurrentLinkedQueue<Map<String, Object>>();
    
    // 存储原始日志的临时队列，供批处理使用
    public static ConcurrentLinkedQueue<Map<String, Object>> temporaryRawLogQueue = new ConcurrentLinkedQueue<Map<String, Object>>();
    
    // 存储日志事件告警的队列
    public static ConcurrentLinkedQueue<AlertMessage> alertMessageQueue = new ConcurrentLinkedQueue<AlertMessage>();
    
    // 存储日志事件告警的临时队列，供批处理使用
    public static ConcurrentLinkedQueue<AlertMessage> temporaryAlertMessageQueue = new ConcurrentLinkedQueue<AlertMessage>();
    
    // 存储监控告警的队列
    public static ConcurrentLinkedQueue<MonitorAlarm> monitorAlarmQueue = new ConcurrentLinkedQueue<MonitorAlarm>();
    
    // 存储监控告警的临时队列，供批处理使用
    public static ConcurrentLinkedQueue<MonitorAlarm> temporaryMonitorAlarmQueue = new ConcurrentLinkedQueue<MonitorAlarm>();
    
    // 事件入库的间隔，即：执行事务的时间
    public static final long storeDataInterval = 1000;
    
    // 队列元素的最大个数
    public static final long QUEUE_MAX_NUMBER = 100;
    
    // 记录队列被清空的时间
    public static long storeEventsQueueTime;
    
    public static long storeSummaryEventsQueueTime;
    
    public static long storeRawLogQueueTime;
    
    public static long storeAlertMessageQueueTime;
    
    public static long storeMonitorAlarmQueueTime;
  // 存储接收的日志的队列 关联规则用
    public static ConcurrentLinkedQueue<List<String>> logDroolsQueue = new ConcurrentLinkedQueue<List<String>>();
    //系统设置
    public static ArrayList<SysInfo> sysInfoList = new ArrayList<SysInfo>();
    
    public static List<String> networkList = new ArrayList<String>();
    public static int handleCount;
    
    // 规则状态-启用
    public static final int START_USE = 0;
    
    // 规则状态-停用
    public static final int BLOCK_UP = 1;
    
    // 接收日志端口
    public static String terracePort;
    
    // 所有采集器KEY-MAC VALUE-COUNT 
    public static Map<String,Long> collector = new HashMap<String, Long>();
    
    //表明采集器是否在线的标识
    public static Map<String,Integer> collectorIsOnline = new HashMap<String,Integer>();
    
    // 采集器列表
    public static List<Collector> collectorList;
    
    //资产的可用性值
    public static double assetUse;
    
    //资产的完整性
    public static double assetComplete;
    
    //资产的保密性
    public static double assetSecret;
    
    public static String installTime;
    
    public static Map<Long,String> eventTypeTag = new HashMap<Long,String>();
     
    //事件分类对应关系。
    public static Map<String,String> eventCategoryTag = new HashMap<String,String>();
    
    //配置syslog的map
    public static List<Map<String,SyslogIF>> SYSLOG_OBJECT = new ArrayList<Map<String,SyslogIF>>();
    
    //存储全局的风险值
    public static List<Map<Long,Double>> EVENTCOUNTMAP = new ArrayList<Map<Long,Double>>();
    //规则引擎
    public static Drools drools;
    //关联规则
    public static RelevanceRuleGroup relevanceRuleGroup;
    //存放关联规则的名字
	public static List<String> ruleNameList;
	// 资产的业务处理类 关联规则需要用它查询设备名称,根据父找叶子
	public static AssetService assetManager;
	//处理告警的服务类
	public static AlertRuleService alertRuleManager;
	//全局资产
	public static Map<Long, Asset> assetGlobleMap = new Hashtable<Long, Asset>(); 
	
	//数据库类型的标识:目前只允许为pgAdmin或者sqlServer数据库

	public static String sqlId;
	
	//判断lience是否到期或者没有,灌机的时候需要修改成0
	public static int lienceFlag = 1;
	
	//作为控制是否需要输入lience的控制 0需要输入1不需要输入
	public static int keyFlag =1;
	//作为读key还是读文件,/usr/local/gensn_key是代表读key，/usr/local/gensn代表读文件,根据需求更改此处
	public static final String keyOrFile="/usr/local/gensn";
	//安全策略文件存放路径
	public static String securityPolicyFilePath="";
	//漏扫联动扫描返回结果存放目录
	public static String scanTaskPath="";
	
	//磁盘告警默认是true，如果告警就置为false，不再接收任何时间
	public static boolean diskFalg=true;
	
			
	static {
		
		 Properties prop = new Properties(); 
		 InputStream in  = GlobalConfig.class.getResourceAsStream("/prop/jdbc.properties");
	        try { 
	            prop.load(in);  
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	        sqlId = prop.getProperty("sqlid").trim(); 
	}
	
	/**
	 * 漏扫信息信息配置
	 */
	public final static int SCAN_PORT = 22 ; 
	public final static String SCAN_IP = "198.9.9.40" ;
	public final static String SCAN_USERNAME = "root";
	public final static String SCAN_PASSWORD = "Scan@j1d1sec.c0m";  
	public final static String SCAN_PATH = "/home/scanData/socData/";  
	
	/**
	 * SOC链接信息
	 */
	public final static String SOC_IP= "198.9.9.14" ;
	public final static String SOC_USRENAME= "root" ;
	public final static String SOC_PASSWORD= "root123" ;
	public final static String SOC_PORT= "22" ;
	public final static String SOC_PATH = "/usr/local/tomcat-upgrade/" ; 

	
	/*
	 * 磁盘空间标识
	 * true 磁盘空间已满 ； false 磁盘空间未满
	 */
	public static boolean diskSpaceFlag = false ; 
	/**
	 * 处理超长日志采用tcp/socket协议接收，端口
	 */
	public static int SOCKET_PORT=8585;
	
	//在线时间点记录
		public static Map<String,Date>AssetTimeNote=new HashMap<String, Date>();
}
