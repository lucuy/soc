package com.soc.webapp.listener;

import global.GlobalThreadPool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.serial.Serial;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.service.events.EventsService;
import com.soc.service.events.FilterByGroupService;
import com.soc.service.events.NotAnalyticEventsService;
import com.soc.service.events.OriginalLogService;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.thread.DroolsThread;
import com.soc.webapp.thread.QueueHandleThread;
import com.soc.webapp.thread.SyslogServerThread;
import com.util.EncodeUtil;
import com.util.StringUtil;

public class CommunicationContextListener implements ServletContextListener
{
    private transient Log Log = LogFactory.getLog(getClass());
    
    private SettingService settingManager;
    
    private SyslogServerIF syslogServerIF;
    
    private SettingArchive archive;
    
    SettingArchiveService archiveManager;
    
    public static int threadPoolId = 1;
    
    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent sce)
    {
        if(syslogServerIF != null) {
            syslogServerIF.shutdown();
        }
        
        if(GlobalConfig.cacheManager != null)
        {
            GlobalConfig.cacheManager.clearAll();
            GlobalConfig.cacheManager.shutdown();
        }
        
        // 关闭线程池
        GlobalThreadPool.pool.closePool();
    }
    
    /**
     * {@inheritDoc}
     */
    public void contextInitialized(ServletContextEvent event)
    {
        Log.info("Initializing soc contextInitialized...!");
        
        // 得到注入service类
        WebApplicationContext wac =
            WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        
        // 获得过滤Service
       /* FilterService filterManager = (FilterService)wac.getBean("filterManager");
        
        GlobalConfig.filterList = filterManager.queryFilter();*/
        
        FilterByGroupService filterByGroupManager = (FilterByGroupService)wac.getBean("filterByGroupManager");
        
        GlobalConfig.filterByGroupList = filterByGroupManager.queryFilterByGroup();
        
        // 过滤规则的所有运算符
        GlobalConfig.operatorList = new ArrayList<String>();
        GlobalConfig.operatorList.add("eq_str");
        GlobalConfig.operatorList.add("eq_str_ic");
        GlobalConfig.operatorList.add("matchesfilter");
        GlobalConfig.operatorList.add("and");
        GlobalConfig.operatorList.add("between");
        GlobalConfig.operatorList.add("or");
        GlobalConfig.operatorList.add("innumber");
        GlobalConfig.operatorList.add("in_ic");
        GlobalConfig.operatorList.add("contains_ic");
        GlobalConfig.operatorList.add("in");
        GlobalConfig.operatorList.add("startswith");
        GlobalConfig.operatorList.add("eq");
        GlobalConfig.operatorList.add("isnull");
        
        // 确定事件分类的事件的属性集合
        GlobalConfig.eventAttributes = new HashMap<String, String>();
        GlobalConfig.eventAttributes.put("devtype", "getDevType");
        GlobalConfig.eventAttributes.put("devproduct", "getDevproduct");
        GlobalConfig.eventAttributes.put("customd1", "getCustomd1");
        GlobalConfig.eventAttributes.put("devvendor", "getDevVendor");
        GlobalConfig.eventAttributes.put("rawid", "getRawId");
        GlobalConfig.eventAttributes.put("type", "getType");
        GlobalConfig.eventAttributes.put("program", "getProgram");
        GlobalConfig.eventAttributes.put("devname", "getDevName");
        GlobalConfig.eventAttributes.put("programtype", "getProgramType");
        GlobalConfig.eventAttributes.put("susername", "getSuserName");
        GlobalConfig.eventAttributes.put("action", "getAction");
        
        // 初始化事件存入队列的开始时间
        long time = new Date().getTime();
        GlobalConfig.storeEventsQueueTime = time;
        GlobalConfig.storeSummaryEventsQueueTime = time;
        GlobalConfig.storeAlertMessageQueueTime = time;
        GlobalConfig.storeMonitorAlarmQueueTime = time;
        GlobalConfig.storeRawLogQueueTime = time;
        
        //System.out.println(GlobalConfig.sqlId+"sql---------------");
        // 初始化采集器列表
        GlobalConfig.collectorList = ((SettingCollectorService)wac.getBean("settingLoggerManager")).queryCollector();
        
        EventsService eventsManager = (EventsService)wac.getBean("eventsManager");
        settingManager = (SettingService) wac.getBean("settingManager");
        /* 创建日志表 */
      //处理pgAdmin的数据库表的创建过程
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tableName = sdf.format(new Date());
        String saveTime=settingManager.queryByKey("save_Time");
        String oldTable="";
        int tmp = Integer.parseInt(tableName.substring(0, 4));
        if (StringUtil.isNotEmpty(saveTime)) {
        	oldTable=tmp-Integer.parseInt(saveTime)+tableName.substring(4, 8);
		}
        if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
        {
       if (StringUtil.isNotEmpty(oldTable)) {
		//删除时间表和序列
    	   eventsManager.deleteTable("tbl_" + oldTable);
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_seq");
    	   //删除未解析
    	   eventsManager.deleteTable("tbl_" + oldTable + "_not_analytic_events");
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_not_analytic_events_seq");
    	   //删除原始事件
    	   eventsManager.deleteTable("tbl_"+oldTable+"_original_log");
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_original_log_seq");
	}
        } 
        if(GlobalConfig.sqlId.equals("pgAdmin"))
        {
           int logTable = eventsManager.existsTable("tbl_" + tableName);
           if(logTable==0)
           {
               String seqName = "tbl_" + tableName + "_seq";
               if(StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
               {
                   eventsManager.createLogSeq(seqName);
               }
               
               Map<String, String> map = new HashMap<String,String>();
               map.put("tableName", "tbl_" + tableName);
               map.put("primaryKey", "PK_" + tableName);
               map.put("seqName", seqName);
               eventsManager.createLogTable(map);
           }
        }
        else if(GlobalConfig.sqlId.equals("sqlServer"))
        {
        	  int result = eventsManager.existsTable("tbl_" + tableName);
        	  if(result==0)
        	  {
        		  Map<String, String> map = new HashMap<String,String>();
        		  map.put("tableName", "tbl_" + tableName);
        		  eventsManager.createLogTable(map);
        	  }else
        	  {
        		  System.out.println("解析成功的表已经存在");
        	  }
        }
        	
        
       
        
        NotAnalyticEventsService notAnalyticEventsManager = (NotAnalyticEventsService)wac.getBean("notAnalyticEventsManager");
        
        /* 创建未解析事件表 */
        //处理pgAdmin的数据库表的创建过程
        if(GlobalConfig.sqlId.equals("pgAdmin"))
        {
           int notAnalyticEventsTable = notAnalyticEventsManager.existsTable("tbl_" + tableName + "_not_analytic_events");
        if(notAnalyticEventsTable==0)
        {
            String seqName = "tbl_" + tableName + "_not_analytic_events_seq";
            if(StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
            {
                notAnalyticEventsManager.createNotAnalyticEventsSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String,String>();
            map.put("tableName", "tbl_" + tableName + "_not_analytic_events");
            map.put("primaryKey", "PK_" + tableName + "_not_analytic_events");
            map.put("seqName", seqName);
            notAnalyticEventsManager.createNotAnalyticEventsTable(map);
        }
        }else if(GlobalConfig.sqlId.equals("sqlServer"))
        {
        	int result = notAnalyticEventsManager.existsTable("tbl_" + tableName + "_not_analytic_events");
        	if(result==0)
        	{
        		 Map<String, String> map = new HashMap<String,String>();
                 map.put("tableName", "tbl_" + tableName + "_not_analytic_events");
                 notAnalyticEventsManager.createNotAnalyticEventsTable(map);
                 
        	}else
        	{
        		System.out.println("表已经存在");
        	}
        }
        
        /*创建原始日志表*/
        OriginalLogService originalLogManager = (OriginalLogService)wac.getBean("originalLogManager");
        
        //处理pgAdmin的数据库表的创建过程
        if(GlobalConfig.sqlId.equals("pgAdmin"))
        {
        int originalLogTable = originalLogManager.existsTable("tbl_" + tableName + "_original_log");
        
        if (originalLogTable==0)
        {
            String seqName = "tbl_" + tableName + "_original_log_seq";
            
            if (StringUtil.isEmpty(originalLogManager.existsSeq(seqName)))
            {
                originalLogManager.createOriginalLogSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("tableName", "tbl_" + tableName + "_original_log");
            map.put("primarKey", "PK_" + tableName + "_original_log");
            map.put("seqName", seqName);
            originalLogManager.createOriginalLogTable(map);
            
            
            archiveManager = (SettingArchiveService)wac.getBean("archiveManager");
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(tableName);
        }
        }else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
        {
        	int result= originalLogManager.existsTable("tbl_" + tableName + "_original_log");
        	if(result== 0)
        	{
        		 Map<String, String> map = new HashMap<String, String>();
                 map.put("tableName", "tbl_" + tableName + "_original_log");
                 originalLogManager.createOriginalLogTable(map);
                 
                 archiveManager = (SettingArchiveService)wac.getBean("archiveManager");
                 
                 archive = new SettingArchive();
                 archive.setArchiveName(tableName);
                 archive.setArchiveStatus(1);
                 archive.setArchiveDate(new Date().getTime());
                 archiveManager.createArchive(tableName);
        	}
        	else
        	{
        		System.out.println("原始日志表已经存在");
        	}
        }
        
        
        settingManager = (SettingService)wac.getBean("settingManager");
        
        // 初始化与采集器通信的参数
        GlobalConfig.terracePort = settingManager.queryByKey("centerNatPort");
        
        // syslog服务器协议
        String syslogServerProtocol = settingManager.queryByKey("syslog_server_protocol");
        //此处做修改，判断是否运行licence程序
        if(GlobalConfig.keyFlag==0){
        	//重新读取lience控制文件
        	resolve();
        	
        	checkLicence();
        }
        
        if (StringUtil.isNotBlank(syslogServerProtocol))
        {
            syslogServerIF = SyslogServer.getInstance(syslogServerProtocol);
            
            // 启动与采集器通信的监听类
            if (syslogServerIF != null)
            {
                // 初始化线程池
                GlobalThreadPool.init();
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                GlobalConfig.cacheManager = CacheManager.create();
                
                GlobalThreadPool.pool.execute(new SyslogServerThread(syslogServerIF, syslogServerProtocol, GlobalConfig.cacheManager, event));
                GlobalThreadPool.pool.execute(DroolsThread.handleThread(event));
                GlobalThreadPool.pool.execute(QueueHandleThread.handleThread(GlobalConfig.cacheManager, event));
                //new SyslogServerThread(syslogServerIF, Integer.valueOf(syslogServerPort), syslogServerProtocol, GlobalConfig.cacheManager, event);
            }
            
        }
        
    }
 
    
 // 解析XML
    public static void resolve() {
		try {
			
			char[] name = {0x66, 0x6F, 0x72, 0x74, 0x2E, 0x6C, 0x69, 0x63, 0x65, 0x6E, 0x63, 0x65};
			File file = new File("/etc/" + String.valueOf(name));
			if(file.exists()) {
				FileInputStream in = null;
				        
				in = new FileInputStream(file);
				byte[] buf = new byte[4096];
				int len = in.read(buf, 0, 4096);
				in.close();
				
				byte[] buffer = new byte[len];
				for(int i = 0; i < len; i++) {
					buffer[i] = buf[i];
				}		
				
				
				char[] key1 = {0x3F, 0xEE, 0x3F, 0x5A, 0xAE, 0xFA, 0x1F, 0x0A};
				char[] key2 = {0x3D, 0xAE, 0x3A, 0x5B, 0x3F, 0x6A, 0x11, 0xAA};
				char[] key3 = {0x0B, 0x9E, 0xDF, 0x2A, 0xAA, 0xF0, 0x7D, 0x6E};
				
				buffer = EncodeUtil.xorDecode(buffer, key3);
		
				buffer = EncodeUtil.xorDecode(buffer, key2);
	
				buffer = EncodeUtil.xorDecode(buffer, key1);
				
				// 解析XML
				Document document = DocumentHelper.parseText(new String(buffer));
				Element root = document.getRootElement();
				
				if(root.getName().equals("reginfo")) {
					List<Element> elementsList = root.elements();
					Iterator iterator = elementsList.iterator();
					while(iterator.hasNext()) {
						Element element = (Element)iterator.next();
						//System.out.println(element.getName() + "： " + element.getText());
						if(element.getName().equals("sn")) {
							Serial.SERIAL_SN = element.getText(); 
						} else if(element.getName().equals("resource_num")) {
							Serial.SERIAL_RESOURCE_NUM = Integer.parseInt(element.getText()); 
						} else if(element.getName().equals("auth_day")) {
							Serial.SERIAL_AUTH_DAY = Integer.parseInt(element.getText());
						} else if(element.getName().equals("gen_time")) {
							Serial.SERIAL_GEN_TIME = Long.parseLong(element.getText());
						} else if(element.getName().equals("sign")) {
							Serial.SERIAL_SIGN = element.getText(); 
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
    }
    
    //核对licence，过期或者不正确，则不处理事件
    public static  void checkLicence()
    {
    	Process process;
        BufferedReader br = null;
        String hardwareCode ;
        try {
			process = Runtime.getRuntime().exec(GlobalConfig.keyOrFile);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			hardwareCode = br.readLine();
			 if(Serial.SERIAL_SN!=null && hardwareCode!=null &&(hardwareCode.equals(Serial.SERIAL_SN))&&((new Date().getTime() / 1000 - Serial.SERIAL_GEN_TIME) <= (Serial.SERIAL_AUTH_DAY * 24 * 60 * 60)))
		        {
				     GlobalConfig.lienceFlag = 1;
		        }
			 else
			 {
				  GlobalConfig.lienceFlag = 0;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    	
    }
    
    
    
    
    
}
